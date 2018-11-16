package com.maxcar.market.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.model.request.ParkingFeeDetailListRequest;
import com.maxcar.market.model.response.ParkingFeeDetailListResponse;
import com.maxcar.market.model.response.ParkingFeePayDetailResponse;
import com.maxcar.market.service.ParkingFeeDetailService;
import com.maxcar.base.util.StringUtils;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserService;
import com.maxcar.websocket.server.WebSocketServer;
import com.maxcar.weixin.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api-p/parking")
public class ParkingFeeController extends BaseController {

    @Autowired
    private BarrierService barrierService;
    @Autowired
    private ParkingFeeService parkingFeeService;
    @Autowired
    private WeiXinService weiXinService;
    @Autowired
    private ParkingFeeDetailService parkingFeeDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private OrganizationsService organizationsService;

    private static String BAOAN_ORG_CODE="000";//保安部code

    /**
     * 停车收费列表搜索信息接口
     *
     * @return
     */
    @GetMapping("/searchinfo")
    @Deprecated
    public Object getParkingFreeSearch(HttpServletRequest request) throws Exception {
        InterfaceResult result = new InterfaceResult();
        Map<String,Object> data=new HashMap<>();
        List<Map<String,Object>> barrierList=new ArrayList<>();
        Map<String,Object> barriersInfo;
        List<Map<String,Object>> employlist=new ArrayList<>();
        Map<String,Object> employsInfo;

        User user = getCurrentUser(request);

        List<Barrier> barriers = barrierService.selectBarrierByMarketId(user.getMarketId());
        for (Barrier barrier:barriers){
            barriersInfo=new HashMap<>();
            barriersInfo.put("barrierId",barrier.getBarrierId());
            barriersInfo.put("barrierPosition",barrier.getBarrierPosition());
            barrierList.add(barriersInfo);
        }

        //获取保安部信息
        List<Organizations> organizations=organizationsService.getOrganizationsByMarketAndCode(user.getMarketId(),BAOAN_ORG_CODE);
        for (Organizations org:organizations){
            List<User> users=userService.getUserList(user.getMarketId(),org.getOrgId());
            for (User user1:users){
                Staff staff = staffService.selectByPrimaryId(user1.getStaffId());
                if (staff!=null){
                    employsInfo=new HashMap<>();
                    employsInfo.put("userId",user.getUserId());
                    employsInfo.put("empName",staff.getStaffName());
                    employlist.add(employsInfo);
                }
            }
        }
        data.put("barriers",barrierList);
        data.put("employs",employlist);
        result.InterfaceResult200(data);
        return result;
    }


    /**
     * 停车收费列表
     *
     * @return
     */
    @PostMapping("/list")
    public Object getParkingFreeList(@RequestBody ParkingFeeDetailListRequest parkingFeeDetailListRequest, HttpServletRequest request) throws Exception {
        InterfaceResult result = new InterfaceResult();
        Map<String,Object> map=new HashMap<>();
        User user = getCurrentUser(request);

        parkingFeeDetailListRequest.setMarketId(user.getMarketId());
        PageInfo<ParkingFeeDetailListResponse> pageInfo=parkingFeeDetailService.getParkingFreeList(parkingFeeDetailListRequest);
        pageInfo.getList().forEach(parkingFeeDetailListResponse -> {
            //获取道闸位置
            if (parkingFeeDetailListResponse.getBrakeId()!=null){
                Barrier barrier = barrierService.selectByBarrierId2(parkingFeeDetailListResponse.getBrakeId());
                if (barrier!=null){
                    parkingFeeDetailListResponse.setBarrierPosition(barrier.getBarrierPosition());
                }
            }
            //获取收费员
            if (parkingFeeDetailListResponse.getEmployeesId()!=null){
                User user1=userService.selectByPrimaryKey(parkingFeeDetailListResponse.getEmployeesId());
                if (user1!=null){
                    Staff staff = staffService.selectByPrimaryId(user.getStaffId());
                    if (staff!=null){
                        parkingFeeDetailListResponse.setEmployeeName(staff.getStaffName());
                    }
                }
            }

        });

        ParkingFeePayDetailResponse parkingFeePayDetail=parkingFeeDetailService.getParkingFreePaySum(parkingFeeDetailListRequest);
        map.put("list",pageInfo);
        map.put("payTotal",parkingFeePayDetail);

        result.InterfaceResult200(map);
        return result;
    }

    /**
     * 道闸列表接口
     *
     * @return
     */
    @GetMapping("/barriers")
    @Deprecated
    public Object getBarriers(HttpServletRequest request) throws Exception {
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        List<Barrier> barriers = barrierService.selectBarrierByMarketId(user.getMarketId());
        result.InterfaceResult200(barriers);
        return result;
    }

    /**
     * 根据卡号读取费用
     *
     * @param request
     * @param cardNo
     * @return
     */
    @GetMapping("/{cardNo}")
    public Object getRecord(HttpServletRequest request, @PathVariable("cardNo") String cardNo) throws Exception {
        JSONObject json = new JSONObject();
        User user = getCurrentUser(request);
        json.put("marketId", user.getMarketId());
        json.put("cardNo", cardNo);
        json.put("remoteAddr",getRemoteHost(request));
        InterfaceResult result = parkingFeeService.getRecordByCardNo(json);
        return result;
    }

    /**
     * 轮班接口
     *
     * @return
     */
    @GetMapping("/shift/{barrierId}/{pageNum}/{pageSize}")
    @Deprecated
    public Object getShift(HttpServletRequest request, @PathVariable("barrierId") String barrierId,
                           @PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeService.getShift(barrierId, user.getMarketId(),
                user.getUserId(), pageNum, pageSize);
        return result;
    }

    /**
     * 确认换班接口
     *
     * @return
     */
    @PostMapping("/sure")
    @Deprecated
    public Object changeSure(HttpServletRequest request, @RequestBody JSONObject params) throws Exception {
        User user = getCurrentUser(request);
        params.put("marketId", user.getMarketId());
        params.put("userId", user.getUserId());
        InterfaceResult result = parkingFeeService.changeSure(params);
        return result;
    }

    /**
     * 岗亭人员接口
     *
     * @return
     */
    @PostMapping("/baoan")
    @Deprecated
    public Object getBaoAn(HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeService.getAllOrgByBaoAn(user.getMarketId());
        return result;
    }

    /**
     * 在线支付，现金支付
     * @param params
     * @param request
     * @return
     * @throws Exception
     */
    @PutMapping("/fee")
    public Object charge(@RequestBody JSONObject params,HttpServletRequest request) throws Exception{
        User user = getCurrentUser(request);
        //0支付宝支付  1  微信支付 2 现金支付 3公众号支付
        params.put("userId",user.getUserId());
        params.put("remoteAddr",getRemoteHost(request));
        InterfaceResult result = parkingFeeService.charge(params);
        if (StringUtils.equals(result.getCode(),"200")){
            JSONObject json = (JSONObject)JSONObject.toJSON(result.getData());
            String barrierId = json.getString("barrierId");
            WebSocketServer.sendInfo(json.toJSONString(),barrierId);
        }
        return result;
    }

    /**
     * 应急开闸
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/kz")
    public Object escapeHatch(HttpServletRequest request,@RequestBody JSONObject params)
            throws Exception{
        User user = getCurrentUser(request);
        params.put("marketId",user.getMarketId());
        params.put("remoteAddr",getRemoteHost(request));
        InterfaceResult result = weiXinService.escapeHatch(params);
        return  result;
    }

    /**
     * 上班登录
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/work/in")
    public InterfaceResult goToWork(HttpServletRequest request) throws Exception{
        User user = getCurrentUser(request);
        JSONObject params = new JSONObject();

        if (null != user){
            params = (JSONObject) JSONObject.toJSON(user);
            params.put("remoteAddr",getRemoteHost(request));
        }
        InterfaceResult result = parkingFeeService.goToWork(params);
        return result;
    }

    /**
     * 下班详情
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @GetMapping("/work/detail/{pageNum}/{pageSize}")
    public InterfaceResult workDetail(HttpServletRequest request,@PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize)throws Exception{
        User user = getCurrentUser(request);
        JSONObject params = new JSONObject();
        //得到来访者的IP地址
        String remoteAddr = request.getRemoteAddr();
        logger.info("remoteAddr:{}",remoteAddr);
        if (null != user){
            params = (JSONObject) JSONObject.toJSON(user);
            params.put("remoteAddr",getRemoteHost(request));
        }
        params.put("pageNum",pageNum);
        params.put("pageSize",pageSize);
        InterfaceResult result = parkingFeeService.workDetail(params);
        return result;
    }

    /**
     * 下班接口
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/work/off")
    public InterfaceResult goOffWork(HttpServletRequest request) throws Exception{
        User user = getCurrentUser(request);
        JSONObject params = new JSONObject();
        if (null != user){
            params = (JSONObject) JSONObject.toJSON(user);
            params.put("remoteAddr",getRemoteHost(request));
        }
        InterfaceResult result = parkingFeeService.goOffWork(params);
        return result;
    }

    /**
     * websocket推送卡号
     * @param orderNo
     * @param barrierId
     * @return
     */
    @GetMapping("/{orderNo}/{barrierId}")
    public InterfaceResult getOrderNo(@PathVariable("orderNo") String orderNo,
                                      @PathVariable("barrierId") String barrierId){
        InterfaceResult result = new InterfaceResult();
        JSONObject json = new JSONObject();
        json.put("orderNo",orderNo);
        // 推送订单号
        json.put("type",3);
        WebSocketServer.sendInfo(JSON.toJSONString(json),barrierId);
        return result;
    }
}
