package com.maxcar.market.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.TopicService;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.*;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.base.util.wechat.ReceiveXmlEntity;
import com.maxcar.base.util.wechat.ReceiveXmlProcess;
import com.maxcar.base.util.wechat.WeiXinUtils;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.market.dao.OpenBarrierRecordMapper;
import com.maxcar.market.dao.ParkingFeeDetailMapper;
import com.maxcar.market.dao.ParkingFeeIntegralMapper;
import com.maxcar.market.dao.ParkingFeeMapper;
import com.maxcar.market.model.request.*;
import com.maxcar.market.model.response.AllParkingFeePack;
import com.maxcar.market.model.response.AllParkingFeeResponse;
import com.maxcar.market.model.response.SumByParkingFeeDetailIdResponse;
import com.maxcar.market.pojo.*;
import com.maxcar.market.service.ParkingFeeRuleService;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.market.service.mapperService.ParkingFeeDetailMapperService;
import com.maxcar.market.utils.ToolUtils;
import com.maxcar.redis.service.RedisService;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserService;

import com.maxcar.weixin.model.TargetMessage;
import com.maxcar.weixin.model.Text;
import com.maxcar.weixin.model.UserInfo;
import com.maxcar.weixin.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("parkingFeeService")
public class ParkingFeeServiceImpl extends BaseServiceImpl<ParkingFee, String> implements ParkingFeeService {

    static Logger logger = LoggerFactory.getLogger(ParkingFeeServiceImpl.class);
    @Autowired
    private ParkingFeeMapper parkingFeeMapper;

    @Autowired
    private ParkingFeeDetailMapperService parkingFeeDetailMapperService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationsService organizationsService;

    @Autowired
    private ParkingFeeDetailMapper parkingFeeDetailMapper;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ParkingFeeRuleService parkingFeeRuleService;

    @Autowired
    private ParkingFeeIntegralMapper parkingFeeIntegralMapper;


    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private BarrierService barrierService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private OpenBarrierRecordMapper openBarrierRecordMapper;

    @Autowired
    private WeiXinService weiXinService;
    @Value("${paibo_app_id}")
    private String paiboAppId;

    @Value("${paibo_app_secret_weixin}")
    private String paiboAppSecret;



    private String REDIS_ZSET_PARKING_FEE = "PARKING_FEE_LIST";

    @Override
    public BaseDao<ParkingFee, String> getBaseMapper() {
        return parkingFeeMapper;
    }

    /**
     * param:
     * describe: 车辆入库
     * create_date:  lxy   2018/8/30  10:36
     **/
    @Override
    public boolean addParkingFeeDetail(AddParkingFeeDetailRequest request) {

        ParkingFeeDetail ParkingFeeDetail = new ParkingFeeDetail();
        ParkingFee parkingFee = parkingFeeMapper.getCurrentNewRecord(request.getBarrierId(), request.getMaxketId());
        ParkingFeeDetail.setId(UuidUtils.generateIdentifier());
        ParkingFeeDetail.setParkingFeeId(null == parkingFee ? "" : parkingFee.getId());
        ParkingFeeDetail.setMarketId(request.getMaxketId());
        ParkingFeeDetail.setUnionId(request.getUnionId());
        ParkingFeeDetail.setCardNo(request.getCardNo());
        ParkingFeeDetail.setBeforeImage(request.getBeforeImage());
        ParkingFeeDetail.setBeforeTime(new Date());
        ParkingFeeDetail.setInsertOperator(request.getOperator());

        return parkingFeeDetailMapperService.insertSelective(ParkingFeeDetail);

    }


    /**
     * param:
     * describe: 车辆出库
     * create_date:  lxy   2018/8/30  10:45
     **/
    @Override
    public boolean updateParkingFeeDetail(ParkingFeeDetail parkingFeeDetail) {
        return parkingFeeDetailMapperService.updateByPrimaryKeySelective(parkingFeeDetail);

    }

    /**
     * param:
     * describe: 更换班次
     * create_date:  lxy   2018/8/28  19:19
     **/
    @Override
    public ParkingFee updateParkingFee(UpdateParkingFeeRequest request) {

        ParkingFee parkingFeeById = parkingFeeMapper.selectByPrimaryKey(request.getParkingFeeId());
        if (null == parkingFeeById) {
            return null;
        }

        // 更新班次的换岗时间
        ParkingFee updateParkingFee = new ParkingFee();
        updateParkingFee.setId(parkingFeeById.getId());
        updateParkingFee.setLeaveTime(new Date());
        updateParkingFee.setUpdateTime(new Date());
        updateParkingFee.setUpdateOperator(request.getBrakeId());

        if (1 <= parkingFeeMapper.updateByPrimaryKeySelective(updateParkingFee)) {
            ParkingFee addParkingFee = new ParkingFee();
            addParkingFee.setId(UuidUtils.generateIdentifier());
            addParkingFee.setMarketId(request.getMarketId());
            addParkingFee.setShift(ToolUtils.gettimeFormart(parkingFeeById.getShift()));
            addParkingFee.setBrakeId(request.getBrakeId());
            addParkingFee.setEmployeesId(request.getEmployeesId());
            addParkingFee.setInsertOperator(request.getBrakeId());

            if (ToolUtils.isOperationSuccess(parkingFeeMapper.insertSelective(addParkingFee))) {
                return addParkingFee;
            }
        }

        return null;
    }

    /**
     * param:
     * describe: 新建班次
     * create_date:  lxy   2018/8/29  10:32
     **/
    @Override
    public ParkingFee addParkingFee(AddParkingFeeRequest request) {

        ParkingFee maxParkingFee = parkingFeeMapper.getMaxParkingFee();

        ParkingFee parkingFee = new ParkingFee();
        parkingFee.setId(UuidUtils.generateIdentifier());
        parkingFee.setMarketId(request.getMarketId());
        parkingFee.setShift(null == maxParkingFee ? ToolUtils.gettimeFormart(null) : ToolUtils.gettimeFormart(maxParkingFee.getShift()));
        parkingFee.setBrakeId(request.getBrakeId());
        parkingFee.setEmployeesId(request.getEmployeesId());
        parkingFee.setInsertOperator(request.getBrakeId());

        if (ToolUtils.isOperationSuccess(parkingFeeMapper.insertSelective(parkingFee))) {
            return parkingFee;
        }

        return null;
    }

    /**
     * param:
     * describe: 查看指定班次收费详情
     * create_date:  lxy   2018/8/29  10:23
     **/
    @Override
    public SumByParkingFeeDetailIdResponse getSumByParkingFeeDetailId(String parkingFeeId) {
        return parkingFeeDetailMapperService.sumByParkingFeeDetailId(null, parkingFeeId);
    }


    /**
     * param:
     * describe: 查看停车收费详情列表
     * create_date:  lxy   2018/8/28  19:00
     **/
    @Override
    public PageInfo<ParkingFeeDetail> allParkingFeeDetail(AllParkingFeeDetailRequest request) {

        PageHelper.startPage(request.getCurPage(), request.getPageSize());
        PageInfo<ParkingFeeDetail> pageInfo = new PageInfo<>(parkingFeeDetailMapperService.allParkingFeeDetail(request));

        if (null != pageInfo.getList() && !pageInfo.getList().isEmpty()) {
            pageInfo.getList().forEach(x -> {
                if (null != x.getBeforeTime() && null != x.getAfterTime()) {
                    Map map = DateUtils.getHMS(x.getAfterTime(), x.getBeforeTime());
                    x.setHms(map.get("hour") + "小时" + map.get("minute") + "分");
                }
            });
        }

        return pageInfo;
    }

    @Override
    public InterfaceResult goToWork(JSONObject params) throws Exception{
        InterfaceResult result = new InterfaceResult();
        String userId = params.getString("userId");
        String marketId = params.getString("marketId");
        String remoteAddr = params.getString("remoteAddr");
        JSONObject json = new JSONObject();
        ParkingFee fee = new ParkingFee();
        //根据mac地址读取道闸id
        Barrier barrier = barrierService.selectBarrierByRemoteHost(remoteAddr,marketId);
        if (null != barrier){
            fee.setBrakeId(barrier.getBarrierId());
            fee.setEmployeesId(userId);
            fee.setMarketId(marketId);
            //先查询用户是否有未下班记录
            User user = userService.selectByPrimaryKey(userId);
            Staff staff = staffService.selectByPrimaryId(user.getStaffId());
            ParkingFee parking = parkingFeeMapper.selectEmployeeNewRecord(fee);
            boolean flag = false;
            if (null == parking) {
                parking = new ParkingFee();
                parking.setId(UuidUtils.generateIdentifier());
                parking.setShift(WeiXinUtils.gettimeFormart());
                parking.setEmployeesId(userId);
                parking.setMarketId(marketId);
                parking.setInsertOperator(userId);
                parking.setArrivalTime(Calendar.getInstance().getTime());
                parking.setBrakeId(barrier.getBarrierId());
                parkingFeeMapper.insertSelective(parking);
                flag = true;
            }
            Map map = DateUtils.getHMS(Calendar.getInstance().getTime(), parking.getArrivalTime());
            json.put("hour", map.get("hour"));
            json.put("minute", map.get("minute"));
            json.put("second", map.get("second"));
            json.put("staffName",staff.getStaffName());
            json.put("position",barrier.getBarrierPosition());
            if (flag){
                json.put("money","0");
            }else{
                ParkingFeeDetail detail = new ParkingFeeDetail();
                detail.setParkingFeeId(parking.getId());
                detail.setMarketId(marketId);
                List<ParkingFeeDetail> parkingFeeDetails = parkingFeeDetailMapper.getThisShiftRecord(detail);
                int total = 0;
                for(ParkingFeeDetail parkingFeeDetail:parkingFeeDetails){
                    total +=parkingFeeDetail.getChargePrice();
                };
                json.put("money",total);
            }
            result.InterfaceResult200(json);
        }else{
            result.InterfaceResult600("道闸配置错误!");
        }
        return result;
    }

    @Override
    public InterfaceResult workDetail(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        JSONObject json = new JSONObject();
        String marketId = params.getString("marketId");
        String userId = params.getString("userId");
        String remoteAddr = params.getString("remoteAddr");
        Integer pageNum = params.getInteger("pageNum");
        Integer pageSize = params.getInteger("pageSize");

        Barrier barrier = barrierService.selectBarrierByRemoteHost(remoteAddr,marketId);
        ParkingFee parking = new ParkingFee();
        parking.setEmployeesId(userId);
        parking.setMarketId(marketId);
        parking.setBrakeId(barrier.getBarrierId());
        ParkingFee parkingFee = parkingFeeMapper.selectEmployeeNewRecord(parking);
        if (null != parkingFee){
            Date systemTime = Calendar.getInstance().getTime();
            json.put("arrivalTime",DateUtils.LONG_DATE_FORMAT.format(parkingFee.getArrivalTime()));
            json.put("systemTime",DateUtils.LONG_DATE_FORMAT.format(systemTime));
            Map map1 = DateUtils.getHMS(systemTime, parkingFee.getArrivalTime());
            StringBuilder sb = new StringBuilder();
            sb.append(map1.get("hour"));
            sb.append("时");
            sb.append(map1.get("minute"));
            sb.append("分");
            sb.append(map1.get("second"));
            sb.append("秒");
            json.put("parkingTime",sb.toString());
            ParkingFeeDetail detail = new ParkingFeeDetail();
            detail.setParkingFeeId(parkingFee.getId());
            detail.setMarketId(marketId);
            PageHelper.startPage(pageNum,pageSize);
            List<ParkingFeeDetail> parkingFeeDetails = parkingFeeDetailMapper.getThisShiftRecord(detail);
            int totalMoney = 0;
            //现金
            int totalCash = 0;
            //公众号
            int totalPublic = 0;
            //微信
            int totalWeixin = 0;
            //支付宝
            int totalIpay = 0;
            for(ParkingFeeDetail parkingFeeDetail:parkingFeeDetails){
                totalMoney +=parkingFeeDetail.getChargePrice();
                Map map2 = DateUtils.getHMS(parkingFeeDetail.getAfterTime(), parkingFeeDetail.getBeforeTime());
                StringBuilder sb1 = new StringBuilder();
                sb1.append(map2.get("hour"));
                sb1.append("时");
                sb1.append(map2.get("minute"));
                sb1.append("分");
                sb1.append(map2.get("second"));
                sb1.append("秒");
                parkingFeeDetail.setParkingTime(sb1.toString());
                switch (parkingFeeDetail.getPayType()){
                    case 0:
                        totalIpay += parkingFeeDetail.getChargePrice();
                        break;
                    case 1:
                        totalWeixin += parkingFeeDetail.getChargePrice();
                        break;
                    case 2:
                        totalCash += parkingFeeDetail.getChargePrice();
                        break;
                    case 3:
                        totalPublic += parkingFeeDetail.getChargePrice();
                        break;
                }
            };
            json.put("totalIpay",totalIpay);
            json.put("totalWeixin",totalWeixin);
            json.put("totalCash",totalCash);
            json.put("totalPublic",totalPublic);
            json.put("totalMoney",totalMoney);
            PageInfo parkingPage = new PageInfo<>(parkingFeeDetails);
            json.put("parkingList",parkingPage);
            result.InterfaceResult200(json);
        }
        return result;
    }

    @Override
    public InterfaceResult goOffWork(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        ParkingFee parking = new ParkingFee();
        String marketId = params.getString("marketId");
        String userId = params.getString("userId");
        String remoteAddr = params.getString("remoteAddr");
        Barrier barrier = barrierService.selectBarrierByRemoteHost(remoteAddr,marketId);
        parking.setEmployeesId(userId);
        parking.setMarketId(marketId);
        parking.setBrakeId(barrier.getBarrierId());
        ParkingFee parkingFee = parkingFeeMapper.selectEmployeeNewRecord(parking);
        parkingFee.setLeaveTime(Calendar.getInstance().getTime());
        parkingFeeMapper.updateByPrimaryKeySelective(parkingFee);
        return result;
    }

    @Override
    public List<ParkingFeeDetail> selectParkingFee(ParkingFeeDetail feeDetail) {
        return parkingFeeDetailMapper.selectParkingFee(feeDetail);
    }

    @Override
    public ParkingFeeDetail selectParkingFeeDetail(ParkingFeeDetail parkingFeeDetail) {
        ParkingFeeDetail detail = parkingFeeDetailMapperService.getParkingFeeDetailByUnionIdOrCardNo(
                parkingFeeDetail.getUnionId(), parkingFeeDetail.getCardNo());

        return detail;
    }

    @Override
    public ParkingFeeDetail selectInParkingRecord(ParkingFeeDetail detail) {
        return parkingFeeDetailMapper.getRecordByCardNoOrUnionId(detail);
    }


    @Override
    public ParkingFeeDetail selectDetail(ParkingFeeDetail detail) {
        return parkingFeeDetailMapper.selectDetail(detail);
    }

    @Override
    public InterfaceResult saveInParking(JSONObject params) throws Exception {
        String marketId = params.getString("marketId");
        String num = params.getString("cardNo");
        String barrierId = params.getString("barrierId");
        String imageUrl = params.getString("imageUrl");
        Integer type = params.getInteger("type");
        InterfaceResult result = new InterfaceResult();
        ParkingFeeDetail feeDetail = new ParkingFeeDetail();
        feeDetail.setMarketId(marketId);
        String orderNo = WeiXinUtils.createOrderNo();
        switch (type) {
            case 0:
                feeDetail.setCardNo(num);
                break;
            case 1:
                feeDetail.setUnionId(num);
                feeDetail.setCardNo(orderNo);
                break;
            default:
                break;
        }
        List<ParkingFeeDetail> details = parkingFeeDetailMapper.selectParkingFee(feeDetail);
        Barrier ba = barrierService.selectByBarrierId(barrierId);
        if (null == details || details.size() == 0) {
            ParkingFeeDetail detail = new ParkingFeeDetail();
            detail.setId(UuidUtils.generateIdentifier());
            detail.setBeforeTime(Calendar.getInstance().getTime());

            switch (type) {
                case 0:
                    detail.setInType(1);
                    detail.setCardNo(num);
                    break;
                case 1:
                    detail.setInType(0);
                    detail.setCardNo(orderNo);
                    detail.setUnionId(num);
                    break;
                default:
                    break;
            }
            detail.setMarketId(marketId);
            detail.setBeforeImage(imageUrl);
            detail.setInsertTime(Calendar.getInstance().getTime());
            int code = parkingFeeDetailMapper.insertSelective(detail);
            if (code == 1) {
                //下发欢迎光临
                sendMessage(marketId, ba, -2);
            }
        } else {
            //下发禁止重复入场
            sendMessage(marketId, ba, -3);
        }
        return result;
    }

    @Override
    public InterfaceResult charge(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        if (null != params && !params.isEmpty()) {
            String userId = params.getString("userId");
            String remoteAddr = params.getString("remoteAddr");
            ParkingFeeDetail detail = JSONObject.toJavaObject(params, ParkingFeeDetail.class);
            Barrier ba = barrierService.selectBarrierByRemoteHost(remoteAddr,detail.getMarketId());
            ParkingFee parking = new ParkingFee();
            parking.setMarketId(detail.getMarketId());
            parking.setBrakeId(ba.getBarrierId());
            parking.setEmployeesId(userId);
            ParkingFee parkingFee = parkingFeeMapper.selectEmployeeNewRecord(parking);

            detail.setParkingFeeId(null == parkingFee ? "" : parkingFee.getId());
            int code = parkingFeeDetailMapper.updateByPrimaryKeySelective(detail);
            if (code == 1) {
                sendMessage(detail.getMarketId(), ba, -1);
                ParkingFeeDetail parkingFeeDetail = new ParkingFeeDetail();
                parkingFeeDetail.setParkingFeeId(parking.getId());
                parkingFeeDetail.setMarketId(detail.getMarketId());
                List<ParkingFeeDetail> parkingFeeDetails = parkingFeeDetailMapper.getThisShiftRecord(parkingFeeDetail);
                int total = 0;
                for(ParkingFeeDetail detail1:parkingFeeDetails){
                    total +=detail1.getChargePrice();
                };
                JSONObject json = new JSONObject();
                json.put("money",total);
                json.put("barrierId",ba.getBarrierId());
                //前端推送识别 2为刷卡推送
                json.put("type",2);
                result.InterfaceResult200(json);
            } else {
                result.InterfaceResult600("收费失败!");
            }
        }
        return result;
    }

    @Override
    @Deprecated
    public InterfaceResult getShift(String barrierId, String marketId, String userId, Integer pageNum, Integer pageSize) throws Exception {
        InterfaceResult result = new InterfaceResult();
        JSONObject json = new JSONObject();
        Date date = Calendar.getInstance().getTime();

        //获取当前道闸最后一条记录
        ParkingFee parkingFee = parkingFeeMapper.getCurrentNewRecord(barrierId, marketId);
        if (null != parkingFee) {
            json.put("isFirst", "0");
            //查询离岗时间为null，当前道闸的上岗记录
            ParkingFee parkingRecord = parkingFeeMapper.getCurrentBarrierRecord(barrierId, marketId);
            if (parkingRecord != null) {
                Date leave = Calendar.getInstance().getTime();
                String leaveStr = DateUtils.getLongDateStr(leave);
                String arriveStr = DateUtils.getLongDateStr(parkingRecord.getArrivalTime());
                parkingRecord.setLeaveTime(Calendar.getInstance().getTime());
                json.put("id", parkingRecord.getId());
                json.put("shift", parkingRecord.getShift());
                json.put("leaveTime", leaveStr);
                json.put("arriveDate", arriveStr);
                //计算上岗时长时分秒
                Map map = DateUtils.getHMS(leave, parkingRecord.getArrivalTime());
                json.put("hour", map.get("hour"));
                json.put("minute", map.get("minute"));
                json.put("second", map.get("second"));
                //收费总金额,根据收费班次和市场id查询
                Integer fee = parkingFeeDetailMapper.sumFeeByThisShift(marketId, parkingRecord.getId());
                json.put("totalFee", null == fee ? 0 : fee.intValue());//
                ParkingFeeDetail parkingFeeDetail = new ParkingFeeDetail();
                parkingFeeDetail.setMarketId(marketId);
                parkingFeeDetail.setParkingFeeId(parkingRecord.getId());
                parkingFeeDetail.setCurrentPage(pageNum);
                parkingFeeDetail.setPageSize(pageSize);
                PageHelper.startPage(pageNum, pageSize);
                List<ParkingFeeDetail> parkingFeeDetails = parkingFeeDetailMapper.getThisShiftRecord(parkingFeeDetail);
                for (ParkingFeeDetail parkingFeeDe : parkingFeeDetails) {
                    Map map1 = DateUtils.getHMS(parkingFeeDe.getAfterTime(), parkingFeeDe.getBeforeTime());
                    StringBuilder sb = new StringBuilder();
                    sb.append(map1.get("hour"));
                    sb.append("时");
                    sb.append(map1.get("minute"));
                    sb.append("分");
                    sb.append(map1.get("second"));
                    sb.append("秒");
                    parkingFeeDe.setParkingTime(sb.toString());
                }
                PageInfo pageInfoDetail = new PageInfo(parkingFeeDetails);
                json.put("list", pageInfoDetail);
                result.InterfaceResult200(json);
            }
        } else {
            //查询不到记录,系统首次部署设置为首班
            ParkingFee parkingFee1 = new ParkingFee();
            Date d = Calendar.getInstance().getTime();
            parkingFee1.setId(UuidUtils.generateIdentifier());
            parkingFee1.setBrakeId(barrierId);
            parkingFee1.setMarketId(marketId);
            parkingFee1.setShift(WeiXinUtils.gettimeFormart());
            parkingFee1.setInsertTime(Calendar.getInstance().getTime());
            parkingFee1.setInsertOperator(userId);
            parkingFee1.setLeaveTime(d);
            json = (JSONObject) JSONObject.toJSON(parkingFee1);
            json.put("isFirst", "1");
            json.put("hour", 0);
            json.put("minute", 0);
            json.put("second", 0);
            json.put("totalFee", 0);
            json.put("arriveDate", DateUtils.getLongDateStr(d));
            json.put("leaveTime", "-");
            result.InterfaceResult200(json);

        }
        return result;
    }

    @Override
    @Deprecated
    public InterfaceResult getAllOrgByBaoAn(String marketId) {
        InterfaceResult result = new InterfaceResult();
        List<Organizations> organizationsList = organizationsService.getOrgByCodeAndMarketId(marketId);
        Staff staff = new Staff();
        staff.setIsValid(1);
        staff.setGroupId(organizationsList.get(0).getOrgId());
        staff.setStaffType(1);
        staff.setMarketId(marketId);
        List<Staff> staffList = staffService.selectByExample(staff);
        result.InterfaceResult200(staffList);
        return result;
    }

    @Override
    @Deprecated
    public InterfaceResult changeSure(JSONObject params) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        String shift = "";
        if (null != params && !params.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String barrierId = params.getString("barrierId");
            String marketId = params.getString("marketId");
            Integer isFirst = params.getInteger("isFirst");
            //换班人的id
            String personId = params.getString("personId");
            shift = params.getString("shift");
            String userId = params.getString("userId");
            String leaveTime = params.getString("leaveTime");
            String parkingFeeId = params.getString("parkingFeeId");
            ParkingFee parkingFee = new ParkingFee();
            if (isFirst == 1) {
                parkingFee.setId(parkingFeeId);
                if (StringUtils.equals(leaveTime, "-")) {
                    parkingFee.setArrivalTime(Calendar.getInstance().getTime());
                } else {
                    parkingFee.setArrivalTime(sdf.parse(leaveTime));
                }
                parkingFee.setEmployeesId(personId);
                parkingFee.setShift(shift);
                parkingFee.setMarketId(marketId);
                parkingFee.setBrakeId(barrierId);
                parkingFee.setInsertTime(Calendar.getInstance().getTime());
                parkingFee.setInsertOperator(userId);
                int re = parkingFeeMapper.insertSelective(parkingFee);
                if (re == 1) {
                    interfaceResult.InterfaceResult200(shift);
                } else {
                    interfaceResult.InterfaceResult600("换班失败!");
                }
            } else {
                parkingFee.setId(parkingFeeId);
                parkingFee.setLeaveTime(sdf.parse(leaveTime));
                parkingFee.setUpdateTime(Calendar.getInstance().getTime());
                parkingFee.setUpdateOperator(userId);
                parkingFee.setEmployeesId(personId);
                int code = parkingFeeMapper.updateByPrimaryKeySelective(parkingFee);
                if (code == 1) {
                    //保存成功,新建班次
                    ParkingFee fee = new ParkingFee();
                    fee.setId(UuidUtils.generateIdentifier());
                    fee.setInsertOperator(userId);
                    fee.setArrivalTime(Calendar.getInstance().getTime());
                    fee.setBrakeId(barrierId);
                    fee.setEmployeesId(personId);
                    fee.setShift(WeiXinUtils.gettimeFormart());
                    fee.setMarketId(marketId);
                    fee.setInsertTime(Calendar.getInstance().getTime());
                    int re = parkingFeeMapper.insertSelective(fee);
                    if (re == 1) {
                        interfaceResult.InterfaceResult200(shift);
                    } else {
                        interfaceResult.InterfaceResult600("换班失败！");
                    }
                } else {
                    interfaceResult.InterfaceResult600("换班失败！");
                }
            }

        } else {
            interfaceResult.InterfaceResult406();
        }
        return interfaceResult;
    }

    @Override
    public int updateAfterTime(ParkingFeeDetail detail) {
        return parkingFeeDetailMapper.updateByPrimaryKeySelective(detail);
    }

    @Override
    public InterfaceResult updateParkingDetail(String marketId, String key, String barrierId, Integer type) throws Exception {
        InterfaceResult result = new InterfaceResult();
        Barrier barrier = barrierService.selectByBarrierId(barrierId);
        ParkingFeeDetail parkingFeeDetail = new ParkingFeeDetail();
        parkingFeeDetail.setMarketId(marketId);
        JSONObject json = new JSONObject();
        switch (type){
            case 0:
                //刷卡
                parkingFeeDetail.setCardNo(key);
                ParkingFeeDetail parkingFeeDe = doCard(marketId,barrier,parkingFeeDetail);
                json = (JSONObject)JSONObject.toJSON(parkingFeeDe);
                json.put("type",5);
                break;
            case 1:
                doWeixinEvent(key,barrier);
                json.put("type",6);
                break;
             default:
                 break;
        }
        result.InterfaceResult200(json);
        return result;
    }

    private ParkingFeeDetail doCard(String marketId,Barrier barrier,ParkingFeeDetail parkingFeeDetail) throws Exception{
        Date endDate = Calendar.getInstance().getTime();
        //获取当前卡号最早的一条记录
        ParkingFeeDetail parkingFeeDe = parkingFeeDetailMapper.getRecordByCardNoOrUnionId(parkingFeeDetail);
        parkingFeeDe.setAfterTime(endDate);
        if (null != parkingFeeDe) {
            // 判断该记录是否是会员记录
            if (parkingFeeDe.getIsVip() == 1 && StringUtils.equals(marketId,"007")){
                long l = endDate.getTime() - parkingFeeDe.getBeforeTime().getTime();
                // 四小时以内直接开闸
                if (l <= Constants.VIP_PARK_TIMEOUT){
                    String hmsToString = DateUtils.getHMSToString(endDate, parkingFeeDe.getBeforeTime());
                    BigDecimal totalFee = parkingFeeRuleService.figureOutParkingFee(
                            parkingFeeDe.getBeforeTime(), endDate, marketId, 2);
                    parkingFeeDe.setPrice(null == totalFee ? 0 : totalFee.intValue());
                    parkingFeeDe.setReduction(totalFee.intValue());// 四小时以内会员减免和正常停车费用相同
                    parkingFeeDe.setIntegral(0);
                    parkingFeeDe.setAlreadyPaid(0);
                    parkingFeeDe.setChargeFee(0);
                    parkingFeeDe.setOverTimeFee(0);
                    parkingFeeDe.setParkingTime(hmsToString);
                    //0元开闸
                    sendMessage(marketId, barrier, -1);
                    return parkingFeeDe;
                }else {
                    // 超过四小时免费时间,缴过费
                    if (null != parkingFeeDe.getPayTime()){
                        long time = parkingFeeDe.getPayTime().getTime() + 15*60*1000 - endDate.getTime();
                        ParkingFeeIntegral feeIntegral = parkingFeeIntegralMapper.selectIntegralByDetailId(parkingFeeDe.getId());
                        if (time <= 0){
                            // 未超出15分钟免费停留时间
                            String hmsToString = DateUtils.getHMSToString(parkingFeeDe.getPayTime(), parkingFeeDe.getBeforeTime());
                            BigDecimal totalFee = parkingFeeRuleService.figureOutParkingFee(
                                    parkingFeeDe.getBeforeTime(), parkingFeeDe.getPayTime(), marketId, 2);
                            parkingFeeDe.setPrice(null == totalFee ? 0 : totalFee.intValue());// 正常停车费
                            parkingFeeDe.setParkingTime(hmsToString);
                            parkingFeeDe.setReduction(20);
                            if (null != feeIntegral){
                                parkingFeeDe.setIntegral(null == feeIntegral.getIntegral()? 0 : feeIntegral.getIntegral());
                                parkingFeeDe.setAlreadyPaid(null == feeIntegral.getPrice()? 0 : feeIntegral.getPrice());
                            }
                            parkingFeeDe.setChargeFee(0);
                            parkingFeeDe.setOverTimeFee(0);
                            //0元开闸
                            sendMessage(marketId, barrier, -1);
                            return parkingFeeDe;
                        }else {
                            // 超出15分钟免费停留时间
                            String hmsToString = DateUtils.getHMSToString(endDate, parkingFeeDe.getBeforeTime());
                            // 第一阶段产生的费用
                            BigDecimal totalFee1 = parkingFeeRuleService.figureOutParkingFee(
                                    parkingFeeDe.getBeforeTime(), parkingFeeDe.getPayTime(), marketId, 2);
                            // 第二阶段产生的费用
                            BigDecimal totalFee2 = parkingFeeRuleService.figureOutParkingFee(
                                    parkingFeeDe.getBeforeTime(), parkingFeeDe.getPayTime(), marketId, 2,2);
                            parkingFeeDe.setParkingTime(hmsToString);
                            parkingFeeDe.setPrice(totalFee1.intValue()+totalFee2.intValue());
                            parkingFeeDe.setReduction(20);
                            if (null != feeIntegral){
                                parkingFeeDe.setIntegral(null == feeIntegral.getIntegral()? 0 : feeIntegral.getIntegral());
                                parkingFeeDe.setAlreadyPaid(null == feeIntegral.getPrice()? 0 : feeIntegral.getPrice());
                            }
                            // 超时时间
                            Calendar c = Calendar.getInstance();
                            c.setTimeInMillis(parkingFeeDe.getPayTime().getTime()+15*60*1000);
                            parkingFeeDe.setOverTime(DateUtils.getHMSToString(endDate, c.getTime()));
                            parkingFeeDe.setOverTimeFee(totalFee2.intValue());
                            parkingFeeDe.setChargeFee(totalFee2.intValue());
                            int type1 = parkingFeeDe.getChargeFee() == 0 ? -1 : parkingFeeDe.getChargeFee();
                            //0元开闸
                            sendMessage(marketId, barrier, type1);
                            return parkingFeeDe;
                        }
                    }else {
                        // 未缴过费
                        String hmsToString = DateUtils.getHMSToString(endDate, parkingFeeDe.getBeforeTime());
                        BigDecimal totalFee = parkingFeeRuleService.figureOutParkingFee(
                                parkingFeeDe.getBeforeTime(), endDate, marketId, 2);
                        parkingFeeDe.setPrice(null == totalFee ? 0 : totalFee.intValue());
                        parkingFeeDe.setReduction(0);
                        parkingFeeDe.setIntegral(0);
                        parkingFeeDe.setAlreadyPaid(0);
                        parkingFeeDe.setChargeFee(totalFee.intValue());
                        parkingFeeDe.setOverTimeFee(0);
                        parkingFeeDe.setParkingTime(hmsToString);
                        int type1 = parkingFeeDe.getPrice() == 0 ? -1 : parkingFeeDe.getPrice();
                        //0元开闸
                        sendMessage(marketId, barrier, type1);
                        return parkingFeeDe;
                    }

                }

            }else {
                // 非会员
                //取规则计算
                BigDecimal totalFee = parkingFeeRuleService.figureOutParkingFee(
                        parkingFeeDe.getBeforeTime(), endDate, marketId, 0);

//            StringBuilder sb = new StringBuilder();
//            Map map1 = DateUtils.getHMS(parkingFeeDe.getAfterTime(), parkingFeeDe.getBeforeTime());
//            sb.append(map1.get("hour"));
//            sb.append("小时");
//            sb.append(map1.get("minute"));
//            sb.append("分");
//            sb.append(map1.get("second"));
//            sb.append("秒");
                String hmsToString = DateUtils.getHMSToString(parkingFeeDe.getAfterTime(), parkingFeeDe.getBeforeTime());
                parkingFeeDe.setPrice(null == totalFee ? 0 : totalFee.intValue());// 正常停车费
                parkingFeeDe.setParkingTime(hmsToString);
                parkingFeeDe.setReduction(0);// 四小时以内会员减免和正常停车费用相同
                parkingFeeDe.setIntegral(0);
                parkingFeeDe.setAlreadyPaid(0);
                parkingFeeDe.setChargeFee(totalFee.intValue());// 应收费用
                parkingFeeDe.setOverTimeFee(0);
                int type1 = parkingFeeDe.getPrice() == 0 ? -1 : parkingFeeDe.getPrice();
                //0元开闸
                sendMessage(marketId, barrier, type1);
            }
        }
        return parkingFeeDe;
    }

    private void doWeixinEvent(String key,Barrier barrier) throws Exception{
        //扫码上行的key,根据key查询微信信息
        String  returnXml = String.valueOf(redisService.get(key));
        // 将xml数据转换为map
        ReceiveXmlEntity receiveXmlEntity = ReceiveXmlProcess.getMsgEntity(returnXml);
        ParkingFeeDetail detail = new ParkingFeeDetail();
        String openId = receiveXmlEntity.getFromUserName();

        UserInfo userInfo = weiXinService.getUserInfo(openId, paiboAppId, paiboAppSecret, "paibo");
        String mark = receiveXmlEntity.getEventKey().replace("qrscene_", "").trim();
        String[] market = mark.split("-");
        detail.setUnionId(userInfo.getUnionid());
        detail.setMarketId(market[0]);
        ParkingFeeDetail parkingFeeDetail = selectInParkingRecord(detail);
        if (null != parkingFeeDetail) {
            //找到入场记录
            Date afterTime = Calendar.getInstance().getTime();
            parkingFeeDetail.setPayType(3);
            //计算
            BigDecimal totalFee = parkingFeeRuleService.figureOutParkingFee(
                    parkingFeeDetail.getBeforeTime(), afterTime, parkingFeeDetail.getMarketId(), 0);

            parkingFeeDetail.setPrice(null == totalFee ? 0 : totalFee.intValue());
            parkingFeeDetail.setChargePrice(null == totalFee ? 0 : totalFee.intValue());
            parkingFeeDetail.setAfterTime(afterTime);

            if (parkingFeeDetail.getPrice() == 0){
                //金额为0，更新所有数据
                updateAfterTime(parkingFeeDetail);
            }
            //金额时间更新成功后,先微信对话框推送,再websocket推送

            Map map1 = DateUtils.getHMS(parkingFeeDetail.getAfterTime(), parkingFeeDetail.getBeforeTime());
            JSONObject parking = (JSONObject) JSONObject.toJSON(parkingFeeDetail);
            parking.put("beforeTime", parkingFeeDetail.getBeforeTime().getTime());
            parking.put("afterTime", parkingFeeDetail.getAfterTime().getTime());
            parking.put("barrierId", barrier.getBarrierId());
            parking.put("hour", map1.get("hour"));
            parking.put("minute", map1.get("minute"));
            parking.put("second", map1.get("second"));
            String result = weiXinService.doResponseByPaiBo(receiveXmlEntity, 2, parking);
            String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
            String accessToken = weiXinService.cacheTokenInRedis(paiboAppId,paiboAppSecret,"paibo");
            String httpUrl = url.replace("ACCESS_TOKEN",accessToken);
            Text text = new Text();
            text.setContent(result);
            TargetMessage targetMessage = new TargetMessage();
            targetMessage.setMsgtype("text");
            targetMessage.setTouser(openId);
            targetMessage.setText(text);
            String res = HttpClientUtils.sendPost(httpUrl,JSONObject.toJSONString(targetMessage));
            logger.info("客服消息请求响应:{}",res);
        }
    }

    @Override
    public InterfaceResult getRecordByCardNo(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        String marketId = params.getString("marketId");
        String cardNo = params.getString("cardNo");
        String remoteAddr = params.getString("remoteAddr");
        Barrier barrier = barrierService.selectBarrierByRemoteHost(remoteAddr,marketId);
        //下发消息判断是否压地感
        String topic = topicService.getTopic(marketId);
        PostParam postParam = new PostParam();
        StringBuilder url = new StringBuilder();
        url.append("/barrier/check/");
        url.append(barrier.getMqttTopic());
        url.append("/");
        url.append(barrier.getBarrierId());
        url.append("/");
        //-4标识下发检测是否压地感
        url.append(-5);
        url.append("/");
        url.append(cardNo);
        postParam.setUrl(url.toString());
        postParam.setMarket(marketId);
        postParam.setOnlySend(false);
        postParam.setMethod("get");
        postParam.setMessageTime(Constants.dateformat.format(new Date()));
        logger.info("下发开闸信息开始==>{}", JsonTools.toJson(postParam));
        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

        return result;
    }
/*
    private String getPicture(String barrierId,String marketId) throws Exception{
        logger.info("进入了网络摄像机拍照，市场为==>{},道闸id==>{}",marketId,barrierId);
        List<Configuration> configurations = configurationService.searchConfiguration("in_or_out_image_url");
        if (null != configurations && configurations.size() == 1){
            //保存服务器的路径
            BarrierCamera barrierCamera = new BarrierCamera();
            barrierCamera.setBarrierId(barrierId);
            barrierCamera.setMarketId(marketId);
            List<BarrierCamera> barrierCameras = barrierCameraService.selectCameraByBarrierId(barrierCamera);
            String path = configurations.get(0).getConfigurationValue();
            if (null != barrierCameras && barrierCameras.size() == 1){
                logger.info("网络摄像机连接开始========");
                logger.info("ip==>{}",barrierCameras.get(0).getDeviceIp());
                logger.info("port==>{}",barrierCameras.get(0).getDevicePort());
                logger.info("username==>{}",barrierCameras.get(0).getUserName());
                logger.info("password==>{}",barrierCameras.get(0).getPassword());
                logger.info("图片存储路径==>{}",path);
                BarrierCamera barrierCamera1 = barrierCameras.get(0);
                barrierCamera1.setPath(path);
                barrierCamera1.setMarketId(marketId);
                Map map = HaikangSdkUtil.requestDll(barrierCamera1);
                logger.info("网络摄像机拍照状态返回结果通知==>{}",map);
                boolean result = (Boolean) map.get("result");
                if (result){
                    String yuming = "http://wxsf-t.maxcar.com.cn/";
                    String imageName = (String)map.get("imageName");
                    return yuming+imageName;
                }
            }
        }
        return "";
    }*/

    public void sendMessage(String marketId, Barrier ba, int type) {
        String topic = topicService.getTopic(marketId);
        PostParam postParam = new PostParam();
        StringBuilder url = new StringBuilder();
        url.append("/barrier/open/");
        url.append(ba.getMqttTopic());
        url.append("/");
        url.append(ba.getBarrierId());
        url.append("/");
        //刷卡0元直接出场
        url.append(type);//出场
        postParam.setUrl(url.toString());
        postParam.setMarket(marketId);
        postParam.setOnlySend(false);
        postParam.setMethod("get");
        postParam.setMessageTime(Constants.dateformat.format(new Date()));
        logger.info("下发开闸信息开始==>{}", JsonTools.toJson(postParam));
        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
    }

    /**
     * param:
     * describe: 查看停车收费列表
     * create_date:  lxy   2018/8/28  14:54
     **/
    @Override
    public AllParkingFeeResponse allParkingFee(AllParkingFeeRequest request) {

        AllParkingFeeResponse response = new AllParkingFeeResponse();
        List<AllParkingFeePack> list = new ArrayList<>();
        //String key = REDIS_ZSET_PARKING_FEE + "_" + request.getMarketId();


/*

        SumByParkingFeeDetailIdResponse sumByParkingFeeDetailIdResponse = parkingFeeDetailMapperService.sumByParkingFeeDetailId(null);

        // 检测key 是否存在
        if (redisService.hasKey(key)) {
            request.setTotal(redisService.getLengthOfZSet(key).intValue());
            request = ToolUtils.getStartRowAndEndRow(request);

            response.setPageNum(request.getCurPage());
            response.setPageSize(request.getPageSize());
            response.setTotal(redisService.getLengthOfZSet(key).intValue());

            Set<AllParkingFeePack> set = redisService.getObjectValueZSet(key, request.getStartRow().longValue(), request.getEndRow().longValue());
            List<AllParkingFeePack> list = new ArrayList<>(set);

            response.setList(list);
            response.setSumByParkingFeeDetailIdResponse(sumByParkingFeeDetailIdResponse);

            return response;
        }*/
        PageHelper.startPage(request.getCurPage(), request.getPageSize());
        PageInfo<ParkingFee> pageInfo = new PageInfo<>(parkingFeeMapper.allParkingFee(request));


        List<ParkingFee> allParkingFee = pageInfo.getList();

        if (null == allParkingFee || allParkingFee.isEmpty()) {
            response.setPageNum(pageInfo.getPageNum());
            response.setPageSize(pageInfo.getPageSize());
            response.setTotal((int) pageInfo.getTotal());
            response.setList(list);
            return response;
        }

        list = getAllParkingFeePackList(request.getMarketId(), allParkingFee);

       /* if (redisService.hasKey(key)) {
            request.setTotal(redisService.getLengthOfZSet(key).intValue());
            request = ToolUtils.getStartRowAndEndRow(request);

            response.setPageNum(request.getCurPage());
            response.setPageSize(request.getPageSize());
            response.setTotal(redisService.getLengthOfZSet(key).intValue());

            Set<AllParkingFeePack> set = redisService.getObjectValueZSet(key, request.getStartRow().longValue(), request.getEndRow().longValue());
            List<AllParkingFeePack> listSet = new ArrayList<>(set);
            response.setList(listSet);
            response.setSumByParkingFeeDetailIdResponse(sumByParkingFeeDetailIdResponse);

            return response;
        }*/


       /* request.setTotal(list.size());
        request = ToolUtils.getStartRowAndEndRow(request);*/

        response.setPageNum(pageInfo.getPageNum());
        response.setPageSize(pageInfo.getPageSize());
        response.setTotal((int) pageInfo.getTotal());
        response.setList(list);
        response.setSumByParkingFeeDetailIdResponse(parkingFeeDetailMapperService.sumByParkingFeeDetailId(request.getMarketId(), null));

        return response;
    }


    /**
     * param:
     * describe: 封转停车收费数据
     * create_date:  lxy   2018/8/28  16:42
     **/
    private List<AllParkingFeePack> getAllParkingFeePackList(String marketId, List<ParkingFee> allParkingFee) {

        //String key = REDIS_ZSET_PARKING_FEE + "_" + marketId;

        List<AllParkingFeePack> list = new ArrayList<>(allParkingFee.size());

        allParkingFee.forEach(x -> {

            AllParkingFeePack allParkingFeePack = new AllParkingFeePack();
            allParkingFeePack.setParkingFeeId(x.getId());
            allParkingFeePack.setMarketId(x.getMarketId());
            allParkingFeePack.setShift(x.getShift());
            allParkingFeePack.setBrakeId(x.getBrakeId());
            allParkingFeePack.setEmployeesId(x.getEmployeesId());
            allParkingFeePack.setArrivalTime(x.getArrivalTime());
            allParkingFeePack.setLeaveTime(x.getLeaveTime());

            // 获取统计信息
            SumByParkingFeeDetailIdResponse sumByParkingFeeDetailIdResponse = parkingFeeDetailMapperService.sumByParkingFeeDetailId(marketId, x.getId());

            allParkingFeePack.setCarCount(null == sumByParkingFeeDetailIdResponse ? 0 : sumByParkingFeeDetailIdResponse.getCarCount());
            allParkingFeePack.setPayCash(null == sumByParkingFeeDetailIdResponse ? 0 : sumByParkingFeeDetailIdResponse.getPayCash());
            allParkingFeePack.setPayOnline(null == sumByParkingFeeDetailIdResponse ? 0 : sumByParkingFeeDetailIdResponse.getPayOnline());
            allParkingFeePack.setPayParkingVolume(null == sumByParkingFeeDetailIdResponse ? 0 : sumByParkingFeeDetailIdResponse.getParkingVolume());
            allParkingFeePack.setPaySum(null == sumByParkingFeeDetailIdResponse ? 0 : sumByParkingFeeDetailIdResponse.getPaySum());
            allParkingFeePack.setPayWeixin(null == sumByParkingFeeDetailIdResponse ? 0 : sumByParkingFeeDetailIdResponse.getPayWeixin());
            // 获取收费员信息
            User user = userService.selectByPrimaryKey(x.getEmployeesId());
            allParkingFeePack.setEmployeesName(null == user ? "查无此人" : user.getTrueName());

            // 获取道闸信息
            allParkingFeePack.setBrakeName("查无此道闸");

            try {
                Barrier ba = barrierService.selectByBarrierId(allParkingFeePack.getBrakeId());
                allParkingFeePack.setBrakeName((null != ba && null != ba.getBarrierPosition()) ? ba.getBarrierPosition() : "查无此道闸");
            } catch (Exception e) {
                logger.debug(e.getMessage());
            }

            list.add(allParkingFeePack);

           /* if (!redisService.addObjectValueZSet(key, allParkingFeePack, Double.valueOf(x.getShift()))) {
                redisService.del(key);
            }*/

        });

        return list;
    }

    @Override
    public InterfaceResult getParkingFeeDetails(String id) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        FeeDetails feeDetails = new FeeDetails();
        ParkingFeeDetail parkingFeeDetail = parkingFeeDetailMapper.selectByPrimaryKey(id);
        if(null != parkingFeeDetail && null != parkingFeeDetail.getParkingFeeId()){
            ParkingFee parkingFee = parkingFeeMapper.selectByPrimaryKey(parkingFeeDetail.getParkingFeeId());
            if(null !=parkingFee && null != parkingFee.getBrakeId()){
                Barrier barrier = barrierService.selectByBarrierId(parkingFee.getBrakeId());
                if(null !=barrier && null !=parkingFee.getEmployeesId()){
                    User user = userService.selectByPrimaryKey(parkingFee.getEmployeesId());
                    if(null !=user && null != user.getStaffId()){
                        Staff staff = staffService.selectByPrimaryId(user.getStaffId());
                    }
                }
                feeDetails.setBarrierPosition(barrier.getBarrierPosition());
                feeDetails.setEmployeesId(parkingFee.getEmployeesId());
                feeDetails.setCardNo(parkingFeeDetail.getCardNo());
                feeDetails.setBeforeTime(parkingFeeDetail.getBeforeTime());
                feeDetails.setAfterTime(parkingFeeDetail.getAfterTime());
                feeDetails.setPayType(parkingFeeDetail.getPayType());
                feeDetails.setInType(parkingFeeDetail.getInType());
                feeDetails.setPrice(parkingFeeDetail.getPrice());
                feeDetails.setChargePrice(parkingFeeDetail.getChargePrice());
                feeDetails.setBeforeImage(parkingFeeDetail.getBeforeImage());
                feeDetails.setAfterImage(parkingFeeDetail.getAfterImage());
                feeDetails.setReason(parkingFeeDetail.getReason());
                Map map1 = DateUtils.getHMS(parkingFeeDetail.getAfterTime(),parkingFeeDetail.getBeforeTime());
                feeDetails.setStopTime(map1.get("hour")+"时"+map1.get("minute")+"分"+map1.get("second"));
            }
        }
        interfaceResult.InterfaceResult200(feeDetails);
        return interfaceResult;
    }

    @Override
    public InterfaceResult getEmergencyRelease(String marketId) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<DetailsEmergency> list = openBarrierRecordMapper.selectByPrimaryMarketId(marketId);
        for(DetailsEmergency openBarrierRecord : list){
            Barrier barrier = barrierService.selectByBarrierId(openBarrierRecord.getBarrierId());
            if (null !=barrier){
                ParkingFeeDetail parkingFeeDetail = parkingFeeDetailMapper.selectByPrimaryKey(openBarrierRecord.getParkingDetailId());
                if (null !=parkingFeeDetail){
                    ParkingFee parkingFee = parkingFeeMapper.selectByPrimaryKey(parkingFeeDetail.getParkingFeeId());
                    if(parkingFee !=null && null !=parkingFee.getEmployeesId()){
                        User user = userService.selectByPrimaryKey(parkingFee.getEmployeesId());
                        if(user !=null && null !=user.getStaffName()){
                            openBarrierRecord.setStaffName(user.getStaffName());
                        }
                    }
                    openBarrierRecord.setAfterTime(parkingFeeDetail.getAfterTime());
                    openBarrierRecord.setCardNo(parkingFeeDetail.getCardNo());
                    openBarrierRecord.setBeforeTime(parkingFeeDetail.getBeforeTime());
                    Map map1 = DateUtils.getHMS(parkingFeeDetail.getAfterTime(),parkingFeeDetail.getBeforeTime());
                    openBarrierRecord.setStopTime(map1.get("hour")+"时"+map1.get("minute")+"分"+map1.get("second"));
                }
            }
            if(barrier != null && null != barrier.getBarrierPosition()){
                openBarrierRecord.setBarrierPosition(barrier.getBarrierPosition());
            }
        }
        interfaceResult.InterfaceResult200(list);
        return interfaceResult;
    }
}
