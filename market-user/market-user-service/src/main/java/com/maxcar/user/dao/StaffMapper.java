package com.maxcar.user.dao;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.StaffExample;
import com.maxcar.user.vo.StaffUser;
import com.maxcar.user.vo.StaffVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper extends BaseDao<Staff,String> {
    int countByExample(StaffExample example);

    int deleteByExample(StaffExample example);

    int deleteByPrimaryKey(String id);

    int insert(Staff record);

    int insertSelective(Staff record);

    List<Staff> selectByExample(StaffExample example);
    
    List<Staff> selectStaffList(Staff staff);

    Staff selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Staff record, @Param("example") StaffExample example);

    int updateByExample(@Param("record") Staff record, @Param("example") StaffExample example);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    List<Staff> getAllMarketStaff(@Param("staffType") int staffType,@Param("marketId") String marketId);

    List<PageInfo> selectStaffTenantList(StaffUser staffUser);

    Staff selectByPrimaryId(String stafId);

    int updateAdmin(Staff record);

    List<StaffVo> getStafflist(StaffVo staffVo);
    List<Staff> getStaffByPhone(@Param("phoneNum") String phoneNum, @Param("staffType") int staffType);

    List<Staff> getTenantAdminByPhone(String phoneNum);

    Staff getStaffByPhoneTenant(@Param("phone") String phone, @Param("tenantId") String tenantId);

    void updateStaffNameById(@Param("staffName") String staffName, @Param("staffId") String staffId);

    List<StaffVo> getCheckStaffRoleList(StaffVo staffVo);
}