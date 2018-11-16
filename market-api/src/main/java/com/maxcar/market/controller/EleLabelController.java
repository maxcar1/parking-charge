package com.maxcar.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.pojo.EleLabel;
import com.maxcar.market.service.EleLabelService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.StaffService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物业管理模块-电子标签管理
 * yangsj
 */
@RestController
@RequestMapping("/api/ele")
public class EleLabelController extends BaseController {

    @Autowired
    private EleLabelService eleLabelService;

    @Autowired
    private StaffService staffService;
    @Autowired
    private UserTenantService userTenantService;


    /**
     * 获取领用人接口
     *
     * @return
     */
    @GetMapping("/staff")
    @OperationAnnotation(title = "查询领用人列表")
    public Object getUserInfo(HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        InterfaceResult interfaceResult = staffService.getAllMarketStaff(user.getMarketId());
        return interfaceResult;
    }

    @GetMapping("/tenant")
    @OperationAnnotation(title = "查询所有商户列表")
    public Object getTenant(HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        List<UserTenant> userTenants = userTenantService.getAllTenantName(user.getMarketId());
        interfaceResult.InterfaceResult200(userTenants);

        return interfaceResult;
    }

    /**
     * 保存领取张数
     *
     * @param params
     * @return
     */
    @PostMapping("")
    @OperationAnnotation(title = "保存领取张数")
    public Object useEleLabel(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        if (null != params && !params.isEmpty()) {
            EleLabel eleLabel = JSONObject.toJavaObject(params, EleLabel.class);
            User user = getCurrentUser(request);
            String userId = user.getUserId();
            String marketId = user.getMarketId();
            interfaceResult = eleLabelService.saveEleLabel(eleLabel, userId, marketId);
        } else {
            interfaceResult.InterfaceResult406();
        }
        return interfaceResult;
    }

    /**
     * 报废处理接口
     *
     * @param params
     * @return
     */
    @PutMapping("")
    @OperationAnnotation(title = "报废处理接口")
    public Object blankOut(@RequestBody JSONObject params) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        String msg = "";
        if (null != params && !params.isEmpty()) {
            EleLabel eleLabel = JSONObject.toJavaObject(params, EleLabel.class);
            interfaceResult = eleLabelService.updateEleLabel(eleLabel);
        } else {
            interfaceResult.InterfaceResult406();
        }
        return interfaceResult;
    }

    /**
     * 电子标签管理列表
     *
     * @param params
     * @return
     */
    @PostMapping("/list")
    @OperationAnnotation(title = "电子标签管理列表")
    public Object listEleLabel(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        //在查询之前传入当前页，然后多少记录
        //  PageHelper.startPage(pn,5);
        //startPage后紧跟的这个查询就是分页查询
        //  List<Employee> emps = employeeService.getAll();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        //  PageInfo pageInfo = new PageInfo<>(emps,5);
        //pageINfo封装了分页的详细信息，也可以指定连续显示的页数
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        params.put("marketId", user.getMarketId());
        result = eleLabelService.getEleLabel(params);
        return result;
    }

    /**
     * 电子标签记录
     *
     * @param params
     * @return
     */
    @PostMapping("/record")
    @OperationAnnotation(title = "电子标签记录列表")
    public Object getRecord(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        User user = getCurrentUser(request);
        params.put("marketId", user.getMarketId());
        InterfaceResult interfaceResult = eleLabelService.getRecordList(params);
        return interfaceResult;
    }

}
