package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.dao.PropertyContractPayMapper;
import com.maxcar.base.pojo.Magic;
import com.maxcar.market.model.request.*;
import com.maxcar.market.model.response.*;
import com.maxcar.market.pojo.PropertyContractDetail;
import com.maxcar.market.pojo.PropertyContractPay;
import com.maxcar.market.pojo.PropertyContractPayDetail;
import com.maxcar.market.service.PropertyContractPayService;
import com.maxcar.market.service.mapperService.PropertyContractDetailMapperService;
import com.maxcar.market.service.mapperService.PropertyContractPayDetailMapperService;
import com.maxcar.market.utils.ToolUtils;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("propertyContractPayService")
public class PropertyContractPayServiceImpl extends BaseServiceImpl<PropertyContractPay, String> implements PropertyContractPayService {

    @Autowired
    private PropertyContractPayMapper propertyContractPayMapper;

    @Autowired
    private PropertyContractDetailMapperService propertyContractDetailMapperService;

    @Autowired
    private PropertyContractPayDetailMapperService propertyContractPayDetailMapperService;

    @Autowired
    private UserService userService;

    @Override
    public BaseDao<PropertyContractPay, String> getBaseMapper() {
        return propertyContractPayMapper;
    }

    private DecimalFormat df = new DecimalFormat("#0.00");


    /**
     * param:
     * describe: 统计缴费记录
     * create_date:  lxy   2018/8/30  18:01
     **/
    @Override
    public SumContractPayPriceResponse sumContractPayPrice(String marketId) {
        return propertyContractPayMapper.sumContractPayPrice(marketId);
    }


    /**
     * param:
     * describe:  合同缴费
     * create_date:  lxy   2018/8/21  14:41
     **/
    @Override
    public boolean updatePayDetail(UpdatePayDetailRequest request) {
        // 开始缴费喽
        for (UpdatePayDetailPack updatePayDetailPack : request.getList()) {
            //合同详情表
            PropertyContractDetail propertyContractDetailById = propertyContractDetailMapperService.getPropertyContractDetailById(updatePayDetailPack.getPropertyContractDetail());
            // 合同缴费表
            PropertyContractPay propertyContractPay = propertyContractPayMapper.selectByPropertyContractId(propertyContractDetailById.getPropertyContractId());
            // 合同缴费记录表
            PropertyContractPayDetail propertyContractPayDetail = propertyContractPayDetailMapperService.selectByPrimaryKey(updatePayDetailPack.getPropertyContractPayDetailId());

            if (null == propertyContractDetailById || null == propertyContractPay || null == propertyContractPayDetail) {
                // return 相当于 continue
                return false;
            }

            if (!Magic.CONTRACT_PAY_COST.equals(propertyContractPayDetail.getStatus())) {

                PropertyContractPayDetail updatePropertyContractPayDetail = new PropertyContractPayDetail();

                Double payPrice = propertyContractPayDetail.getPayPrice() + updatePayDetailPack.getPayPrice();

                Double price = propertyContractDetailById.getPreferentialPrice() * propertyContractDetailById.getAreaTotal();

               /* if (Magic.CONTRACT_DETAIL_TYPE_MONTH.equals(propertyContractDetailById.getType())) {
                    price = price * 1;
                } else if (Magic.CONTRACT_DETAIL_TYPE_SEASON.equals(propertyContractDetailById.getType())) {
                    price = price * 3;
                } else if (Magic.CONTRACT_DETAIL_TYPE_YEARS.equals(propertyContractDetailById.getType())) {
                    price = price * 12;
                } else {
                    price = 0.00;
                }
*/
                // 缴纳金额 大于 合同金额 返货 false
                if (payPrice > price) {
                    return false;
                }

                Integer status = payPrice >= price ? Magic.CONTRACT_PAY_COST : Magic.CONTRACT_PAY_NOTCOST;

                updatePropertyContractPayDetail.setId(propertyContractPayDetail.getId());
                updatePropertyContractPayDetail.setPayPrice(payPrice);
                updatePropertyContractPayDetail.setPayTime(new Date());
                updatePropertyContractPayDetail.setStatus(status);

                updatePropertyContractPayDetail.setUpdateOperator(request.getUserId());
                updatePropertyContractPayDetail.setUpdateTime(new Date());


                if (propertyContractPayDetailMapperService.updateByPrimaryKeySelective(updatePropertyContractPayDetail)) {

                    Double propertyContractPayPrice = propertyContractPay.getPrice() - updatePayDetailPack.getPayPrice();
                    Double completePrice = (null == propertyContractPay.getCompletePrice() ? 0 : propertyContractPay.getCompletePrice()) + updatePayDetailPack.getPayPrice();

                    Integer propertyContractPayStatus = (propertyContractPayPrice <= 0) ? 2 : 1;
                    PropertyContractPay updatePropertyContractPay = new PropertyContractPay();

                    updatePropertyContractPay.setId(propertyContractPay.getId());
                    updatePropertyContractPay.setPrice(propertyContractPayPrice);
                    updatePropertyContractPay.setCompletePrice(completePrice);
                    updatePropertyContractPay.setStatus(propertyContractPayStatus);
                    updatePropertyContractPay.setUpdateOperator(request.getUserId());
                    updatePropertyContractPay.setUpdateTime(new Date());

                    propertyContractPayMapper.updateByPrimaryKeySelective(updatePropertyContractPay);
                }
            }

        }
       /* request.getList().forEach(x -> {


        });*/
        return true;
    }

    /**
     * param:
     * describe: 查看合同缴费记录
     * create_date:  lxy   2018/8/21  10:32
     **/
    @Override
    public PageInfo<GetAllPayDetailResponse> getAllPayDetail(GetAllPayDetailRequest request) {

        PageInfo<GetAllPayDetailResponse> pageInfo = new PageInfo();

        PageHelper.startPage(request.getCurPage(), request.getPageSize());

        List<PropertyContractPayDetail> allPayDetail = propertyContractPayDetailMapperService.getAllPayDetail(request);
        if (allPayDetail == null || allPayDetail.isEmpty()) {
            pageInfo.setPageNum(request.getCurPage());
            pageInfo.setPageSize(request.getPageSize());
            pageInfo.setSize(request.getPageSize());
            pageInfo.setStartRow(0);
            pageInfo.setEndRow(0);
            pageInfo.setTotal(0);
            pageInfo.setPages(0);

            return pageInfo;
        }

        PageInfo<PropertyContractPayDetail> allPageInfo = new PageInfo<>(allPayDetail);

        List<GetAllPayDetailResponse> list = new ArrayList<>(allPayDetail.size());

        allPayDetail.forEach(x -> {

            GetAllPayDetailResponse response = new GetAllPayDetailResponse();
            response.setId(x.getId());
            response.setMarketId(x.getMarketId());
            response.setPropertyContractId(x.getPropertyContractId());
            response.setStatus(x.getStatus());
            response.setPayPrice(df.format(x.getPayPrice()));
            response.setPayTime(x.getPayTime());
            response.setUpdateOperator(x.getUpdateOperator());
            response.setInsertTime(x.getInsertTime());
            //获取操作人名字
            User user = userService.selectByPrimaryKey(x.getUpdateOperator());

            response.setUpdateOperatorName(null == user ? "查无此人" : user.getTrueName());

            response.setPropertyContractDetail(x.getPropertyContractDetail());

            PropertyContractDetail propertyContractDetailById = propertyContractDetailMapperService.getPropertyContractDetailById(x.getPropertyContractDetail());

            if (null != propertyContractDetailById) {
                response.setContractCategory(propertyContractDetailById.getContractCategory());

                response.setContractCategoryTotal(propertyContractDetailById.getAreaTotal());
                // 获取 应缴纳金额
                Double price = propertyContractDetailById.getPreferentialPrice() * propertyContractDetailById.getAreaTotal();

               /* if (Magic.CONTRACT_DETAIL_TYPE_MONTH.equals(propertyContractDetailById.getType())) {
                    price = price * 1;
                } else if (Magic.CONTRACT_DETAIL_TYPE_SEASON.equals(propertyContractDetailById.getType())) {
                    price = price * 3;
                } else if (Magic.CONTRACT_DETAIL_TYPE_YEARS.equals(propertyContractDetailById.getType())) {
                    price = price * 12;
                } else {
                    price = 0.00;
                }*/
                response.setNotPayPrice(df.format(price - Double.valueOf(response.getPayPrice())));

                response.setPrice(df.format(propertyContractDetailById.getPrice()));
                response.setPreferentialPrice(df.format(propertyContractDetailById.getPreferentialPrice()));
                response.setType(propertyContractDetailById.getType());
                response.setStatusTme(propertyContractDetailById.getStatusTme());
                response.setEndTime(propertyContractDetailById.getEndTime());

            }
            list.add(response);

        });

        pageInfo.setPageNum(allPageInfo.getPageNum());
        pageInfo.setPageSize(allPageInfo.getPageSize());
        pageInfo.setSize(allPageInfo.getSize());
        pageInfo.setStartRow(allPageInfo.getStartRow());
        pageInfo.setEndRow(allPageInfo.getEndRow());
        pageInfo.setTotal(allPageInfo.getTotal());
        pageInfo.setPages(allPageInfo.getPages());
        pageInfo.setList(list);

        return pageInfo;
    }


    /**
     * param:
     * describe: 查看合同缴费列表
     * create_date:  lxy   2018/8/21  9:56
     **/
    @Override
    public PageInfo<GetProPerContractPayAllResponse> getPropertyContractPayAll(GetProPerContractPayAllRequest request) {

        PageHelper.startPage(request.getCurPage(), request.getPageSize());

        List<GetProPerContractPayAllResponse> payAll = propertyContractPayMapper.getPayAll(request);

        if (payAll == null || payAll.isEmpty()) {

            PageInfo<GetProPerContractPayAllResponse> pageInfo = new PageInfo<>(payAll);

            return pageInfo;
        }

        GetPropertyContractDetailListRequest getPropertyContractDetailListRequest = new GetPropertyContractDetailListRequest();

        payAll.forEach(x -> {
            getPropertyContractDetailListRequest.setPropertyContractId(x.getPropertyContractId());
            List<PropertyContractDetail> list = propertyContractDetailMapperService.getPropertyContractDetailList(getPropertyContractDetailListRequest);
            if (null != list) {
                list.forEach(contractDetail -> {
                    // 根据区域id 查询区域信息 暂时展示ID 待联调是 展示区域名字
                    if (Magic.CONTRACT_CATEGORY_CAR_SPACE.equals(contractDetail.getContractCategory())) {
                        x.setCarSpaceNos(contractDetail.getAreaName());
                        x.setCarSpaceTotal(contractDetail.getAreaTotal());
                    } else if (Magic.CONTRACT_CATEGORY_EXHIBITION.equals(contractDetail.getContractCategory())) {
                        x.setExhibitionNos(contractDetail.getAreaName());
                        x.setExhibitionTotal(contractDetail.getAreaTotal());
                    } else if (Magic.CONTRACT_CATEGORY_OFFICE.equals(contractDetail.getContractCategory())) {
                        x.setOfficeNos(contractDetail.getAreaName());
                        x.setOfficeTotal(contractDetail.getAreaTotal());
                    }

                });
            }
        });

        PageInfo<GetProPerContractPayAllResponse> pageInfo = new PageInfo<>(payAll);

        return pageInfo;
    }

    //-----------------------以下是生成合同缴费记录相关-------------

    /**
     * param:
     * describe: 生成需要缴费的合同缴费记录
     * create_date:  lxy   2018/8/18  22:06
     **/
    @Override
    public void addPropertyContractPay() {

        GetPropertyContractDetailListRequest request = new GetPropertyContractDetailListRequest();
        request.setPropertyContractDetailStatus(Magic.CONTRACT_STATUS_NORMAL);

      /*  //终止时间 查询七天后在正常时间范围内的
        request.setEndTime(ToolUtils.getSevenDaysLater());*/
        request.setEndTime(new Date());
        // 查看正常的合同详情记录
        List<PropertyContractDetail> propertyContractDetailList = propertyContractDetailMapperService.getPropertyContractDetailList(request);

        if (null == propertyContractDetailList || propertyContractDetailList.isEmpty()) {
            return;
        }
        //循环合同详情
        propertyContractDetailList.forEach(x -> {
            //查看是否生成过合同缴费 没有新增合同缴费
            PropertyContractPay propertyContractPay = propertyContractPayMapper.selectByPropertyContractId(x.getPropertyContractId());
            if (propertyContractPay == null) {
                //新增合同缴费详情
                PropertyContractPay record = new PropertyContractPay();
                record.setId(UuidUtils.generateIdentifier());
                record.setMarketId(x.getMarketId());
                record.setTenantId(x.getTenantId());
                record.setPropertyContractId(x.getPropertyContractId());

                if (ToolUtils.isOperationFail(propertyContractPayMapper.insertSelective(record))) {
                    return;
                }
                propertyContractPay = propertyContractPayMapper.selectByPropertyContractId(record.getId());
            }

            if (propertyContractPay == null) {
                return;
            }
            // 更新合同缴费状态及记录
            updatePay(propertyContractPay, x);
        });
    }

    /**
     * param:
     * describe: 更新合同缴费记录
     * create_date:  lxy   2018/8/20  14:38
     **/
    private void updatePay(PropertyContractPay propertyContractPay, PropertyContractDetail propertyContractDetail) {
        Double payPrice = propertyContractPay.getPrice();

        PropertyContractPay updatePropertyContractPay = new PropertyContractPay();
        updatePropertyContractPay.setId(propertyContractPay.getId());

        // 合同缴费状态为终止
        if (Magic.CONTRACT_PAY_END.equals(propertyContractPay.getStatus())) {
            return;
        }

        GetAllPayDetailRequest getAllPayDetailRequest = new GetAllPayDetailRequest();
        //getAllPayDetailRequest.setPayDetailStatus(Magic.CONTRACT_PAY_NOT);
        getAllPayDetailRequest.setPropertyContractDetailId(propertyContractDetail.getId());
        //查看该合同最近一次缴费记录
        List<PropertyContractPayDetail> allPayDetail = propertyContractPayDetailMapperService.getAllPayDetail(getAllPayDetailRequest);

        PropertyContractPayDetail propertyContractPayDetail;
        if (allPayDetail == null || allPayDetail.isEmpty()) {
            //没有缴费过 添加合同缴费记录
            PropertyContractPayDetail record = new PropertyContractPayDetail();
            record.setId(UuidUtils.generateIdentifier());
            record.setMarketId(propertyContractDetail.getMarketId());
            record.setPropertyContractId(propertyContractDetail.getPropertyContractId());
            record.setPropertyContractDetail(propertyContractDetail.getId());
            record.setInsertTime(propertyContractDetail.getStatusTme());

            updatePropertyContractPay.setStatus(Magic.CONTRACT_PAY_NOTCOST);

            Double preferentialPrice = propertyContractDetail.getPreferentialPrice();

            PropertyContractDetail propertyContractDetailById = propertyContractDetailMapperService.getPropertyContractDetailById(propertyContractDetail.getId());
            if (null != propertyContractDetailById && null != propertyContractDetailById.getAreaTotal()) {
                preferentialPrice = preferentialPrice * propertyContractDetailById.getAreaTotal();
            }

           /* if (Magic.CONTRACT_DETAIL_TYPE_MONTH.equals(propertyContractDetail.getType())) {
                preferentialPrice = preferentialPrice * 1;
            } else if (Magic.CONTRACT_DETAIL_TYPE_SEASON.equals(propertyContractDetail.getType())) {
                preferentialPrice = preferentialPrice * 3;
            } else if (Magic.CONTRACT_DETAIL_TYPE_YEARS.equals(propertyContractDetail.getType())) {
                preferentialPrice = preferentialPrice * 12;
            } else {
                return;
            }*/

            payPrice += preferentialPrice;

            updatePropertyContractPay.setPrice(payPrice);
            // 新增合同缴费记录
            addPropertyContractPayDetail(record, updatePropertyContractPay);
            //return;
            propertyContractPayDetail = record;
        } else {
            propertyContractPayDetail = allPayDetail.get(0);
        }

        Date endTime = propertyContractDetail.getEndTime();

        if (null != propertyContractDetail.getTerminationTime()) {
            endTime = propertyContractDetail.getTerminationTime().getTime() < endTime.getTime() ? propertyContractDetail.getTerminationTime() : endTime;
        }

        LocalDate today = LocalDate.now();

        Calendar cal = Calendar.getInstance();

        LocalDate beforeTime = today;

        cal.setTime(endTime);

        LocalDate afterTime = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DATE));
        //查看合同距到期或终止日期是否小于 三天 小于七天返回 做一个缓冲
        if (ToolUtils.getIntervalDay(beforeTime, afterTime) < 3) {
            return;
        }

        cal.setTime(propertyContractPayDetail.getInsertTime());

        beforeTime = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DATE));
        afterTime = today;

        // 按 付费方式查看 今天是否需要生成缴费记录
        if (Magic.CONTRACT_DETAIL_TYPE_MONTH.equals(propertyContractDetail.getType())) {
            // 月付 查看上次缴费日期距离今天是否 大于等于一个月
            add(1, beforeTime, afterTime, cal, propertyContractDetail, updatePropertyContractPay, payPrice);
            return;
        } else if (Magic.CONTRACT_DETAIL_TYPE_SEASON.equals(propertyContractDetail.getType())) {
            // 季付 查看上次缴费日期距离合同到期是否 大于等于3个月
            add(3, beforeTime, afterTime, cal, propertyContractDetail, updatePropertyContractPay, payPrice);
            return;
        } else if (Magic.CONTRACT_DETAIL_TYPE_YEARS.equals(propertyContractDetail.getType())) {
            // 年付 查看上次缴费日期距离合同到期是否 大于等于12个月
            add(12, beforeTime, afterTime, cal, propertyContractDetail, updatePropertyContractPay, payPrice);
            return;
        }
        return;
    }

    /**
     * param:
     * describe: 添加缴费
     * create_date:  lxy   2018/8/20  18:27
     **/
    public boolean add(int x, LocalDate beforeTime, LocalDate afterTime, Calendar cal, PropertyContractDetail propertyContractDetail,
                       PropertyContractPay updatePropertyContractPay, Double payPrice) {
        // 查看上次缴费日期距离合同到期是否 大于等于x个月
        System.out.println(ToolUtils.getMonth(beforeTime, afterTime));
        if (ToolUtils.getMonth(beforeTime, afterTime) >= x) {
            // 大于一个月 生成补缴记录
            for (int i = 1; i <= ToolUtils.getMonth(beforeTime, afterTime) / x; i++) {

                cal.add(Calendar.MONTH, x);

                PropertyContractPayDetail addPropertyContractPayDetail = new PropertyContractPayDetail();

                addPropertyContractPayDetail.setId(UuidUtils.generateIdentifier());
                addPropertyContractPayDetail.setInsertTime(cal.getTime());

                addPropertyContractPayDetail.setMarketId(propertyContractDetail.getMarketId());
                addPropertyContractPayDetail.setPropertyContractId(propertyContractDetail.getPropertyContractId());
                addPropertyContractPayDetail.setPropertyContractDetail(propertyContractDetail.getId());

                updatePropertyContractPay.setStatus(Magic.CONTRACT_PAY_NOTCOST);

                Double preferentialPrice = propertyContractDetail.getPreferentialPrice();

                PropertyContractDetail propertyContractDetailById = propertyContractDetailMapperService.getPropertyContractDetailById(propertyContractDetail.getId());
                if (null != propertyContractDetailById && null != propertyContractDetailById.getAreaTotal()) {
                    preferentialPrice = preferentialPrice * propertyContractDetailById.getAreaTotal();

                }
                // payPrice += preferentialPrice * x;
                payPrice += preferentialPrice;
                updatePropertyContractPay.setPrice(payPrice);

                addPropertyContractPayDetail(addPropertyContractPayDetail, updatePropertyContractPay);
            }
            return true;
        }
        return false;
    }

    /**
     * param:
     * describe: 新增合同缴费记录了
     * create_date:  lxy   2018/8/20  18:05
     **/
    private boolean addPropertyContractPayDetail(PropertyContractPayDetail record, PropertyContractPay updatePropertyContractPay) {
        if (propertyContractPayDetailMapperService.insertSelective(record)) {
            return ToolUtils.isOperationSuccess(propertyContractPayMapper.updateByPrimaryKeySelective(updatePropertyContractPay));
        }
        return false;
    }
}