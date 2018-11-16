package com.maxcar.market.controller;


import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.service.CarStaffService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 员工车辆管理控制器
 * yangsj
 */
@RestController
@RequestMapping("/api/staff")
public class CarStaffController extends BaseController {
    @Autowired
    private CarStaffService carStaffService;

    @PostMapping("/list")
    @OperationAnnotation(title = "查询所有商户或部门的员工车列表")
    public Object getCarStaffList(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        params.put("marketId", user.getMarketId());
        InterfaceResult interfaceResult = carStaffService.getCarList(params);
        return interfaceResult;
    }

    @PostMapping("/info")
    @OperationAnnotation(title = "查询某个商户或部门的员工车列表")
    public Object getDetailInfo(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        params.put("marketId", user.getMarketId());
        InterfaceResult result = carStaffService.getDetailInfo(params);
        return result;
    }

}
