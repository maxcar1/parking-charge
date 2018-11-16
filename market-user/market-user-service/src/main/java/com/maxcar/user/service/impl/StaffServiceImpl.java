package com.maxcar.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.StringUtils;
import com.maxcar.user.dao.OrganizationsMapper;
import com.maxcar.user.dao.StaffMapper;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.StaffExample;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.vo.StaffUser;
import com.maxcar.user.vo.StaffVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl extends BaseServiceImpl<Staff,String>  implements StaffService {
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private OrganizationsMapper organizationsMapper;

    @Override
    public InterfaceResult getAllMarketStaff(String marketId) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        List<Staff> staffs = staffMapper.getAllMarketStaff(1,marketId);
        if (null != staffs) {
            interfaceResult.InterfaceResult200(staffs);
        }else{
            //直接新建,空数据直接返回
            staffs = new ArrayList<>();
            interfaceResult.InterfaceResult200(staffs);
        }
        return interfaceResult;
    }

    @Override
    public List<Staff> selectUserTenantById(String id){
        StaffExample example = new StaffExample();
        StaffExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(id);
        criteria.andStaffTypeEqualTo(2);
        return staffMapper.selectByExample(example);
    }

    @Override
    public InterfaceResult addStaffTenant (Staff staff){
        InterfaceResult interfaceResult = new InterfaceResult();
        int count=0;
        count = staffMapper.insertSelective(staff);
        if(count>0){
            interfaceResult.InterfaceResult200(staff);
        }else{
            interfaceResult.InterfaceResult600("新增失败");
        }
        return interfaceResult;
    }

    @Override
    public Staff selectByPrimaryId(String staffId) {
        return staffMapper.selectByPrimaryId(staffId);
    }

    @Override
    public List<Staff> selectByStaffId(String staffId) {

        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria().andIdEqualTo(staffId).andIsValidEqualTo(1);
        return staffMapper.selectByExample(staffExample);
    }

    @Override
    public InterfaceResult updateStaffTenantById(Staff staff){
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = staffMapper.updateByPrimaryKeySelective(staff);
        if(count>0){
            interfaceResult.InterfaceResult200("更新成功");
        }else {
            interfaceResult.InterfaceResult200("更新失败");
        }
        return interfaceResult;
    }

    @Override
    public InterfaceResult deleteStaffTenantById(String id,int isValid){
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        Staff staff= staffMapper.selectByPrimaryKey(id);
        staff.setIsValid(0);
        if(isValid==0){
            count= staffMapper.updateByPrimaryKey(staff);
        }else{
            count = staffMapper.deleteByPrimaryKey(id);
        }
        if(count>0){
            interfaceResult.InterfaceResult200("删除成功");
        }else {
            interfaceResult.InterfaceResult600("无删除数据");
        }
        return interfaceResult;
    }

    @Override
    public List<Staff> getMarketStaff(int staffType,String marketId) {
        return staffMapper.getAllMarketStaff(staffType,marketId);
    }

    @Override
    public List<Staff> getMarketStaffByOrgId(int staffType,String marketId,String orgId) {
        StaffExample example = new StaffExample();
        example.createCriteria().andStaffTypeEqualTo(staffType)
                .andIsValidEqualTo(1).andGroupIdEqualTo(orgId);
        return staffMapper.selectByExample(example);
    }

    @Override
    public Staff selectStaffByPhone(String staffPhone) {
        StaffExample example = new StaffExample();
        StaffExample.Criteria criteria = example.createCriteria();
        criteria.andStaffPhoneEqualTo(staffPhone).andStaffTypeEqualTo(1);
        if (staffMapper.selectByExample(example)!=null&&staffMapper.selectByExample(example).size()>0){
            return staffMapper.selectByExample(example).get(0);
        }else {
            return null;
        }
    }


    @Override
    public  List<Staff> selectStaffByName(String staffName){
        StaffExample example = new StaffExample();
        StaffExample.Criteria criteria = example.createCriteria();
        criteria.andStaffNameEqualTo(staffName).andStaffTypeEqualTo(1);
        criteria.andIsValidEqualTo(1);
        List<Staff> staffList = staffMapper.selectByExample(example);
        return staffList;
    }

    @Override
    public PageInfo selectStaffTenantList(StaffUser staffUser){
        PageHelper.startPage(staffUser.getCurrentPage(), staffUser.getPageSize());
        List<PageInfo> list = staffMapper.selectStaffTenantList(staffUser);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Staff> selectByExample(Staff staff) {
        StaffExample staffExample = new StaffExample();
        StaffExample.Criteria criteria = staffExample.createCriteria();
        criteria.andIsValidEqualTo(1);
        if (StringUtils.isNotBlank(staff.getStaffType())) {
            criteria.andStaffTypeEqualTo(staff.getStaffType());
        }
        if (StringUtils.isNotBlank(staff.getMarketId())) {
            criteria.andMarketIdEqualTo(staff.getMarketId());
        }
        if (StringUtils.isNotBlank(staff.getGroupId())) {
            criteria.andGroupIdEqualTo(staff.getGroupId());
        }
        if (StringUtils.isNotBlank(staff.getOpenId())) {
            criteria.andOpenIdEqualTo(staff.getOpenId());
        }
        if (StringUtils.isNotBlank(staff.getIdCard())) {
            criteria.andIdCardEqualTo(staff.getIdCard());
        }
        List<Staff> list = staffMapper.selectByExample(staffExample);
        return list;
    }

    @Override
    public List<Staff> selectByExample(StaffExample staffExample) {
        return staffMapper.selectByExample(staffExample);
    }

    @Override
    public int updateAdmin(Staff staff) {
        return staffMapper.updateAdmin(staff);
    }


    @Override
    public List<Staff> getStaffByPhone(String phoneNum, int staffType) {
        return staffMapper.getStaffByPhone(phoneNum, staffType);
    }

    @Override
	public InterfaceResult selectStaffList(Staff staff) {
    	InterfaceResult interfaceResult = new InterfaceResult();
        PageHelper.startPage(staff.getCurrentPage(), 100);
        List<Staff> list = staffMapper.selectStaffList(staff);
        PageInfo<Staff> pageInfo = new PageInfo(list);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
	}

    @Override
    public BaseDao<Staff, String> getBaseMapper() {
        return staffMapper;
    }

    @Override
    public PageInfo<StaffVo> getStafflist(StaffVo staffVo) {
        PageHelper.startPage(staffVo.getCurrentPage(), staffVo.getPageSize());
        List<StaffVo> staffVos=staffMapper.getStafflist(staffVo);
        PageInfo<StaffVo> pageInfo=new PageInfo<>(staffVos);
        return pageInfo;
    }

    @Override
    public List<Staff> getTenantAdminByPhone(String phoneNum) {
        return staffMapper.getTenantAdminByPhone(phoneNum);
    }

    @Override
    public Staff getStaffByPhoneTenant(String phone, String tenantId) {
        return staffMapper.getStaffByPhoneTenant(phone, tenantId);
    }

    @Override
    public void updateStaffNameById(String staffName, String staffId) {
        staffMapper.updateStaffNameById(staffName, staffId);
    }

    @Override
    public PageInfo<StaffVo> getCheckStaffRoleList(StaffVo staffVo) {
        PageHelper.startPage(staffVo.getCurrentPage(), staffVo.getPageSize());
        List<StaffVo> staffVos=staffMapper.getCheckStaffRoleList(staffVo);
        PageInfo<StaffVo> pageInfo=new PageInfo<>(staffVos);
        return pageInfo;
    }

    @Override
    public void updateStaff(Staff staff) {
        staffMapper.updateByPrimaryKeySelective(staff);
    }

}
