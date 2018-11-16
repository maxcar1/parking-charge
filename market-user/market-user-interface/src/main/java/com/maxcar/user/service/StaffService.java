package com.maxcar.user.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.StaffExample;
import com.maxcar.user.vo.StaffUser;
import com.maxcar.user.vo.StaffVo;

import java.util.List;

public interface StaffService extends BaseService<Staff,String>{
    InterfaceResult getAllMarketStaff(String marketId) throws Exception;

    PageInfo selectStaffTenantList(StaffUser staffUser);

    List<Staff> selectUserTenantById(String id);
    
    InterfaceResult selectStaffList(Staff staff);

    InterfaceResult addStaffTenant(Staff staff);

    InterfaceResult updateStaffTenantById(Staff staff);

    InterfaceResult deleteStaffTenantById(String id,int isValid);

    List<Staff> getMarketStaff(int staffType,String marketId);

    List<Staff> getMarketStaffByOrgId(int staffType,String marketId,String orgId);

    Staff selectStaffByPhone(String staffPhone);

    List<Staff> selectStaffByName(String staffName);

    Staff selectByPrimaryId(String staffId);
    List<Staff> selectByStaffId(String staffId);


    /**
     * 获取市场员工列表或车商员工列表
     *
     * @param staff
     * @return
     */
    List<Staff> selectByExample(Staff staff);
    List<Staff> selectByExample(StaffExample staffExample);

    int updateAdmin(Staff staff);

    List<Staff> getStaffByPhone(String phoneNum, int staffType);
    PageInfo<StaffVo> getStafflist(StaffVo staffVo);

    List<Staff> getTenantAdminByPhone(String phoneNum);

    Staff getStaffByPhoneTenant(String phone, String tenantId);

    void updateStaffNameById(String staffName, String staffId);

    PageInfo<StaffVo> getCheckStaffRoleList(StaffVo staffVo);

    void updateStaff(Staff staff);
}
