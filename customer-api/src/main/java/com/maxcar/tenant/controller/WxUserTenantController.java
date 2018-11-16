package com.maxcar.tenant.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.CollectionUtil;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.ValidateUtils;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.StaffExample;
import com.maxcar.user.service.StaffService;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/9/20.
 */
@RestController
@RequestMapping("/we/tenant")
public class WxUserTenantController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserTenantService userTenantService;
    @Autowired
    private StaffService staffService;

 /*   *//**
     * 微信个人信息
     *
     * @param userTenant 商户实体类(传商户id)
     * @param request
     * @return
     *//*
    @RequestMapping(value = "/getUserTenant")
    public InterfaceResult getUserTenant(@RequestBody UserTenant userTenant, HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
//        try {
            UserTenant tenant = userTenantService.selectUserTenant(userTenant);
            if (tenant == null) {
                interfaceResult.InterfaceResult600("无查询记录");
            } else {
                interfaceResult.InterfaceResult200(tenant);
            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
//        }
        return interfaceResult;
    }*/

    /**
     * 微信员工管理
     *
     * @param staff 商户id
     * @param
     * @return获取员工列表
     */
    @RequestMapping(value = "/getStaffList")
    public InterfaceResult getStaffList(@RequestBody Staff staff) {
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult = staffService.selectStaffList(staff);
        return interfaceResult;
    }

    /**
     * 添加员工
     *
     * @param staff
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addStaff", method = RequestMethod.POST)
    public InterfaceResult addStaff(@RequestBody Staff staff) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
//            User user = super.getCurrentUser(request);
            if (null == staff.getStaffName() || "".equals(staff.getStaffName())) {
                interfaceResult.InterfaceResult600("员工姓名不能为空");
                return interfaceResult;
            } else {
                if (null != staff.getStaffName() && !(ValidateUtils.isName(staff.getStaffName()))) {
                    interfaceResult.InterfaceResult600("姓名格式不合法，姓名格式2-5个汉字 或 2-10个字母");
                    return interfaceResult;
                }
            }
            if (null == staff.getIdCard() || "".equals(staff.getIdCard())) {
                interfaceResult.InterfaceResult600("员工身份证不能为空");
                return interfaceResult;
            } else {
                if (null != staff.getIdCard() && !(ValidateUtils.isIDCard(staff.getIdCard()))) {
                    interfaceResult.InterfaceResult600("员工身份证不合法，请重新输入，支持18位");
                    return interfaceResult;
                }
            }

            if (null == staff.getStaffPhone() || "".equals(staff.getStaffPhone())) {
                interfaceResult.InterfaceResult600("手机号不能为空");
                return interfaceResult;
            } else {
                if (null != staff.getStaffPhone() && !(ValidateUtils.isMobile(staff.getStaffPhone()))) {
                    interfaceResult.InterfaceResult600("手机号格式不正确");
                    return interfaceResult;
                }
            }
            if (null == staff.getStaffSex() || "".equals(staff.getStaffSex())) {
                interfaceResult.InterfaceResult600("性别不能为空");
                return interfaceResult;
            }
            if (null == staff.getStaffType() || "".equals(staff.getStaffType())) {
                interfaceResult.InterfaceResult600("员工类型不能为空");
                return interfaceResult;
            }
            if (null == staff.getOpenId() || "".equals(staff.getOpenId())) {
                interfaceResult.InterfaceResult600("openId不能为空");
                return interfaceResult;
            }
            if (null == staff.getGroupId() || "".equals(staff.getGroupId())) {
                interfaceResult.InterfaceResult600("商户id不能为空");
                return interfaceResult;
            }
            Staff openIdStaff = new Staff();
            openIdStaff.setMarketId(staff.getMarketId());
            openIdStaff.setOpenId(staff.getOpenId());
            openIdStaff.setStaffType(staff.getStaffType());
            List<Staff> list = staffService.selectByExample(openIdStaff);
            String tenantName = userTenantService.selectByTenanId(staff.getGroupId());
            if (CollectionUtil.listIsNotEmpty(list)) {
                interfaceResult.InterfaceResult600("该微信号已绑定： " + tenantName + " 商户,请先解除与原商户的绑定!");
                return interfaceResult;
            }
            Staff idCardStaff = new Staff();
            idCardStaff.setMarketId(staff.getMarketId());
            idCardStaff.setIdCard(staff.getIdCard());
            idCardStaff.setStaffType(staff.getStaffType());
            List<Staff> list2 = staffService.selectByExample(idCardStaff);
            if (CollectionUtil.listIsNotEmpty(list2)) {
                interfaceResult.InterfaceResult600("该身份证已存在，请重新输入");
                return interfaceResult;
            } else {
                staff.setId(UuidUtils.generateIdentifier());
                staff.setInsertTime(new Date());
                staff.setStaffType(2);//新增商户员工
                staff.setMarketId(staff.getMarketId());
                interfaceResult = staffService.addStaffTenant(staff);
                staff.setGroupName(tenantName);
                interfaceResult.InterfaceResult200(staff);
                return interfaceResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
            return interfaceResult;
        }
    }

    /**
     * 微信员工管理员工详情
     *
     * @param staffId 员工id
     * @param
     * @return
     */
    @RequestMapping(value = "getStaffDetail/{id}")
    public InterfaceResult getStaffDetail(@PathVariable(value = "id") String staffId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Staff staff = staffService.selectByPrimaryId(staffId);
            if (staff == null) {
                interfaceResult.InterfaceResult600("无查询记录");
            } else {
                interfaceResult.InterfaceResult200(staff);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;
    }

    /**
     * 微信员工管理删除员工（只可删除非管理员的）
     *
     * @param staffId 员工id
     * @param
     * @return
     */
    @RequestMapping(value = "/delStaff/{staffId}")
    public InterfaceResult delStaff(@PathVariable(value = "staffId") String staffId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Staff staff = staffService.selectByPrimaryId(staffId);
            if (staff != null && staff.getIsAdmin().equals(1)) {
                interfaceResult.InterfaceResult200("该账户为商户管理员，不允许删除");
            } else {
                interfaceResult = staffService.deleteStaffTenantById(staffId, 0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;
    }


    /**
     * 查询商户详情
     *
     * @param id 商户id
     * @param
     * @return
     */
    @RequestMapping(value = "/getTenantDetail/{id}")
    public InterfaceResult getTenantDetail(@PathVariable(value = "id") String id) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
            UserTenant usertenant = userTenantService.selectByPrimaryKey(id);
            if (usertenant == null) {
                interfaceResult.InterfaceResult600("无查询记录");
            } else {
                interfaceResult.InterfaceResult200(usertenant);
            }
        return interfaceResult;
    }
    
    
    /**
	 * 根据openid和员工id校验,同时更改商户管理员
	 *
     * @param staff
     * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/addOrUpdateAdmin")
    public InterfaceResult addOrUpdateAdmin(@RequestBody Staff staff, HttpServletRequest req) {
        InterfaceResult interfaceResult = new InterfaceResult();
		logger.info("changeAdmin | params = {}", staff);	
		JSONObject obj = null;
		try {
			String token = req.getHeader("Token");		
			String json = com.maxcar.base.util.RedisUtil.getInstance().strings().get(token);
			obj = JSONObject.fromObject(json);
            if (obj == null || obj.size() == 0) {
                interfaceResult.InterfaceResult600("未登陆用户");
                return interfaceResult;
            } else if (null == obj.getString("openId") || obj.getString("openId").equals("")) {
                interfaceResult.InterfaceResult600("openId缺失");
                return interfaceResult;
            }
        } catch (Exception e) {
            interfaceResult.InterfaceResult600("token缺失");
            return interfaceResult;
		}
        if (null == staff.getGroupId() || "".equals(staff.getGroupId())) {
            interfaceResult.InterfaceResult600("商户Id值缺失");
            return interfaceResult;
        }
        try {
            UserTenant userTenant = userTenantService.selectByPrimaryKey(staff.getGroupId());
            if (userTenant == null) {
			interfaceResult.InterfaceResult600("查询不到商户信息，请核实");
			return interfaceResult;
		}
            Staff staff3 = new Staff();
            staff3.setOpenId(staff.getOpenId());
            staff3.setIsValid(1);
            staff3.setMarketId(staff.getMarketId());
            List<Staff> list1 = staffService.selectByExample(staff3);
            if (null != list1 && list1.size() > 0 && !list1.get(0).getGroupId().equals(staff.getGroupId())) {
                interfaceResult.InterfaceResult600("该员工已绑定商户，请确认");
                return interfaceResult;
            }
            Staff staff1 = new Staff();
            staff1.setIsAdmin(0);
            staff1.setGroupId(staff.getGroupId());
            staffService.updateAdmin(staff1);//根据商户将管理员更新为员工
            Staff staff2 = new Staff();
            staff2.setOpenId(staff.getOpenId());
            staff2.setGroupId(staff.getGroupId());
            staff2.setIsValid(1);
            staff2.setMarketId(staff.getMarketId());
            List<Staff> list = staffService.selectByExample(staff2);
            if (null != list && list.size() > 0) {
                staff.setIsAdmin(1);
                staffService.updateAdmin(staff);
                staff.setId(list.get(0).getId());
            } else {
                staff.setId(UuidUtils.generateIdentifier());
                staff.setInsertTime(new Date());
                staff.setStaffType(2);//新增商户员工
                staff.setMarketId(staff.getMarketId());
                staff.setIsAdmin(1);
                staffService.addStaffTenant(staff);
            }
            staff.setGroupName(userTenant.getTenantName());
            interfaceResult.InterfaceResult200(staff);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
	}
	
}
