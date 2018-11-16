package com.maxcar.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.market.model.request.AllParkingFeeDetailRequest;
import com.maxcar.market.model.request.AllParkingFeeRequest;
import com.maxcar.market.model.request.ParkingFeeDetailListRequest;
import com.maxcar.market.model.response.ParkingFeeDetailListResponse;
import com.maxcar.market.model.response.ParkingFeePayDetailResponse;
import com.maxcar.market.service.OpenBarrierRecordService;
import com.maxcar.market.service.ParkingFeeDetailService;
import com.maxcar.market.service.ParkingFeeRuleService;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParkingFeeController extends BaseController {

    @Autowired
    private ParkingFeeService parkingFeeService;

    @Autowired
    private ParkingFeeRuleService parkingFeeRuleService;

    @Autowired
    private BarrierService barrierService;
    @Autowired
    private ParkingFeeDetailService parkingFeeDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private OrganizationsService organizationsService;
    @Autowired
    private OpenBarrierRecordService openBarrierRecordService;


    private static String BAOAN_ORG_CODE="000";//保安部code

    /**
     * 停车收费列表搜索信息接口
     *
     * @return
     */
    @GetMapping("/parkingFee/searchinfo")
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
     * 停车收费详情列表
     *
     * @return
     */
    @PostMapping("/parkingFee/list")
    public Object getParkingFreeList(@RequestBody ParkingFeeDetailListRequest parkingFeeDetailListRequest, HttpServletRequest request) throws Exception {
        InterfaceResult result = new InterfaceResult();
        Map<String,Object> map=new HashMap<>();
        User user = getCurrentUser(request);

        parkingFeeDetailListRequest.setMarketId(user.getMarketId());
        PageInfo<ParkingFeeDetailListResponse> pageInfo=parkingFeeDetailService.getParkingFreeList(parkingFeeDetailListRequest);
        pageInfo.getList().forEach(parkingFeeDetailListResponse -> {
            Map hms = DateUtils.getHMS(parkingFeeDetailListResponse.getAfterTime(), parkingFeeDetailListResponse.getBeforeTime());
            parkingFeeDetailListResponse.setParkingTime(hms.get("hour")+"时"+hms.get("minute")+"分"+hms.get("second")+"秒");
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
                    Staff staff = staffService.selectByPrimaryId(user1.getStaffId());
                    if (staff!=null){
                        parkingFeeDetailListResponse.setEmployeeName(staff.getStaffName());
                    }
                }
            }

        });

        ParkingFeePayDetailResponse parkingFeePayDetail=parkingFeeDetailService.getParkingFreePaySum(parkingFeeDetailListRequest);
        parkingFeePayDetail.setOnlinePay(parkingFeePayDetail.getAliPay()+parkingFeePayDetail.getWeChatPay());
        parkingFeePayDetail.setOpenBarrierCount(openBarrierRecordService.getOpenBarrierCount(user.getMarketId()));

        map.put("list",pageInfo);
        map.put("payTotal",parkingFeePayDetail);


        result.InterfaceResult200(map);
        return result;
    }


    @OperationAnnotation(title = "获取停车收费列表")
    @RequestMapping("/parkingFee/allParkingFee")
    public InterfaceResult allParkingFee(@RequestBody @Valid AllParkingFeeRequest allParkingFeeRequest, BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }
        User user = getCurrentUser(request);

        if (null == user || tIsEmpty(user.getMarketId())) {
            return getInterfaceResult("600", "市场不确定,无法查询");
        }
        allParkingFeeRequest.setMarketId(user.getMarketId());

        return getInterfaceResult("200", parkingFeeService.allParkingFee(allParkingFeeRequest));
    }

    @OperationAnnotation(title = "查看停车收费详情列表")
    @RequestMapping("/parkingFee/allParkingFeeDetail")
    public InterfaceResult allParkingFeeDetail(@RequestBody @Valid AllParkingFeeDetailRequest allParkingFeeDetailRequest, BindingResult result, HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }
        User user = getCurrentUser(request);

        if (null == user || tIsEmpty(user.getMarketId())) {
            return getInterfaceResult("600", "市场不确定,无法查询");
        }
        allParkingFeeDetailRequest.setMarketId(user.getMarketId());

        return getInterfaceResult("200", parkingFeeService.allParkingFeeDetail(allParkingFeeDetailRequest));
    }

    /**
     * 获取当前市场的总规则配置以及当前收费时段配置
     *
     * @return
     */
    @OperationAnnotation(title = "查看当前市场的总规则配置以及收费时段配置")
    @GetMapping("/total/rule")
    public Object getTotalRule(HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeRuleService.getTotalRule(user.getMarketId(), user.getUserId());
        return result;
    }

    /**
     * 修改当前市场的总规则配置以及当前收费时段配置
     *
     * @return
     */
    @OperationAnnotation(title = "修改当前市场的总规则配置以及收费时段配置")
    @PutMapping("/total/rule")
    public Object editTotalRule(HttpServletRequest request, @RequestBody JSONObject params) throws Exception {
        User user = getCurrentUser(request);
        params.put("userId", user.getUserId());
        InterfaceResult result = parkingFeeRuleService.editTotalRule(params);
        return result;
    }

    /**
     * 新增收费时段
     *
     * @return
     */
    @OperationAnnotation(title = "新增收费时段")
    @PostMapping("/fee/time")
    public Object addFeePeriodTime(HttpServletRequest request, @RequestBody JSONObject params) throws Exception {
        User user = getCurrentUser(request);
        params.put("userId", user.getUserId());
        params.put("marketId", user.getMarketId());
        InterfaceResult result = parkingFeeRuleService.addFeePeriodTime(params);
        return result;
    }

    /**
     * 删除收费时段
     *
     * @return
     */
    @OperationAnnotation(title = "删除收费时段")
    @DeleteMapping("/fee/time/{feePeriodTimeId}")
    public Object deleteFeePeriodTime(HttpServletRequest request
            , @PathVariable("feePeriodTimeId") String feePeriodTimeId) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeRuleService.deleteFeePeriodTime(user.getMarketId(), feePeriodTimeId, user.getUserId());
        return result;
    }

    /**
     * 修改收费时段
     *
     * @return
     */
    @OperationAnnotation(title = "修改收费时段")
    @PutMapping("/fee/time")
    public Object editFeePeriodTime(HttpServletRequest request
            , @RequestBody JSONObject params) throws Exception {
        User user = getCurrentUser(request);
        params.put("userId", user.getUserId());
        params.put("marketId", user.getMarketId());
        InterfaceResult result = parkingFeeRuleService.editFeePeriodTime(params);
        return result;
    }

    /**
     * 编辑查看收费时段
     *
     * @return
     */
    @OperationAnnotation(title = "编辑查看收费时段")
    @GetMapping("/fee/time/{feePeriodTimeId}")
    public Object getFeePeriodTime(HttpServletRequest request
            , @PathVariable("feePeriodTimeId") String feePeriodTimeId) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult result = parkingFeeRuleService.getFeePeriodTime(user.getMarketId(), feePeriodTimeId, user.getUserId());
        return result;
    }

    @OperationAnnotation(title = "收费详情")
    @GetMapping("/fee/getParkingFeeDetails/{id}")
    public InterfaceResult getParkingFeeDetails(@PathVariable("id")String id,HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = parkingFeeService.getParkingFeeDetails(id);
        return interfaceResult;
    }

    @OperationAnnotation(title = "应急开闸列表记录")
    @RequestMapping("/fee/getEmergencyRelease")
    public InterfaceResult getEmergencyRelease(HttpServletRequest request) throws Exception{
        User user = getCurrentUser(request);
        InterfaceResult interfaceResult = parkingFeeService.getEmergencyRelease(user.getMarketId());
        return interfaceResult;
    }
}
