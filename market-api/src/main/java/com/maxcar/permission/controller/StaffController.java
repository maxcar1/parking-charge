/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StaffController
 * Author:   Administrator
 * Date:     2018/8/22 18:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.maxcar.permission.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.pojo.IntegralStatistics;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserService;
import com.maxcar.user.vo.StaffUser;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉车商员工管理<br>
 * 〈〉
 *
 * @author hanling
 * @create 2018/8/22
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/staffTenant")
public class StaffController extends BaseController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationsService organizationsService;

    @GetMapping(value = "/{id}")
    @OperationAnnotation(title = "根据商户id查询员工列表")
    public InterfaceResult getStaffTenant(@PathVariable(value = "id") String id) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<Staff> list = staffService.selectUserTenantById(id);
        interfaceResult.InterfaceResult200(list);
        return interfaceResult;
    }

    @RequestMapping(value = "/addStaffTenant")
    @OperationAnnotation(title = "新增市场员工")
    public InterfaceResult addStaffTenant(@RequestBody Staff staff, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        User user = super.getCurrentUser(request);
        if (StringUtils.isNotBlank(staff.getStaffPhone()) && staffService.selectStaffByPhone(staff.getStaffPhone()) == null) {
            staff.setId(UuidUtils.generateIdentifier());
            staff.setInsertTime(new Date());
            staff.setStaffType(1);
            staff.setInsertOperator(user.getUserId());
            if (StringUtils.isNotBlank(staff.getMarketId())) {
                staff.setMarketId(staff.getMarketId());
            } else {
                staff.setMarketId(user.getMarketId());
            }
            interfaceResult = staffService.addStaffTenant(staff);
        } else {
            interfaceResult.InterfaceResult600("手机号码已存在");
        }

        return interfaceResult;
    }


    @RequestMapping(value = "/checkStaffName")
    @OperationAnnotation(title = "校验是否重名")
    public InterfaceResult checkStaffName(@RequestBody Staff staff, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<Staff> staffList = staffService.selectStaffByName(staff.getStaffName());
        if (staffList.size() > 0) {
            interfaceResult.InterfaceResult600("系统中已存在该员工姓名");
        }
        return interfaceResult;
    }

    @RequestMapping(value = "/updateStaffTenantById")
    @OperationAnnotation(title = "编辑")
    public InterfaceResult updateStaffTenantById(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        Staff staff = JSONObject.toJavaObject(params, Staff.class);
        Staff staff1 = staffService.selectByPrimaryKey(staff.getId());
        if (staff1.getStaffPhone().equals(staff.getStaffPhone())) {
            User user = super.getCurrentUser(request);
            staff.setInsertTime(new Date());
            staff.setInsertOperator(user.getUserId());
            interfaceResult = staffService.updateStaffTenantById(staff);
        } else {
            if (StringUtils.isNotBlank(staff.getStaffPhone()) && staffService.selectStaffByPhone(staff.getStaffPhone()) == null) {
                User user = super.getCurrentUser(request);
                staff.setInsertTime(new Date());
                staff.setInsertOperator(user.getUserId());
                interfaceResult = staffService.updateStaffTenantById(staff);
            } else {
                interfaceResult.InterfaceResult600("该员工电话号码已存在");
            }
        }


        return interfaceResult;
    }

    @RequestMapping(value = "/deleteStaffTenantById/{staffId}/{isValid}")
    @OperationAnnotation(title = "删除")
    public InterfaceResult deleteStaffTenantById(@PathVariable(value = "staffId") String staffId, @PathVariable(value = "isValid") int isValid) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        User user = new User();
        user.setStaffId(staffId);
        List<User> list = userService.getUserList(user);
        if (list.size() > 0) {
            interfaceResult.InterfaceResult600("该员工下的账号未解绑无法删除");
            return interfaceResult;
        }
        interfaceResult = staffService.deleteStaffTenantById(staffId, isValid);

        return interfaceResult;
    }

    @RequestMapping(value = "/getStaffTenantList")
    @OperationAnnotation(title = "查询市场员工列表")
    public InterfaceResult getStaffTenantList(@RequestBody StaffUser staffUser, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        User user = super.getCurrentUser(request);
        staffUser.setMarketId(user.getMarketId());
        staffUser.setManagerFlag(user.getManagerFlag());
        PageInfo pageInfo = staffService.selectStaffTenantList(staffUser);
        interfaceResult.InterfaceResult200(pageInfo);

        return interfaceResult;
    }

    @PostMapping(value = "/organizationsList")
    @OperationAnnotation(title = "查询市场机构列表")
    public InterfaceResult getStaffTenantList(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        User user = super.getCurrentUser(request);
        Organizations organizations = JSONObject.toJavaObject(params, Organizations.class);
        organizations.setMarketId(user.getMarketId());
        organizations.setManagerFlag(user.getManagerFlag());
        List<Organizations> list = organizationsService.searchOrganizations(organizations);
        interfaceResult.InterfaceResult200(list);

        return interfaceResult;
    }
}
