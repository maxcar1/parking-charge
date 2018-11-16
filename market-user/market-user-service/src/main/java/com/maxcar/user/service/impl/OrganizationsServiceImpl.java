package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.user.dao.OrganizationsMapper;
import com.maxcar.user.dao.StaffMapper;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.OrganizationsExample;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.StaffExample;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.user.service.StaffService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: maxcar-util
 * @description: 机构接口实现类
 * @author: 罗顺锋
 * @create: 2018-05-16 15:32
 **/
@Service("organizationsService")
public class OrganizationsServiceImpl extends BaseServiceImpl<Organizations,String> implements OrganizationsService{
    @Autowired
    private OrganizationsMapper organizationsMapper;
    @Autowired
    private  StaffMapper staffMapper;


    @Override
    public List<Organizations> searchOrganizations(Organizations organizations) throws Exception {
        OrganizationsExample example = new OrganizationsExample();
        example.setDistinct(false);//去除重复，boolean型，true为选择不重复的记录。
        example.setOrderByClause("register_time desc"); //升序排列，desc为降序排列。
        OrganizationsExample.Criteria criteria = example.createCriteria();
        criteria.andIsvalidEqualTo(1);
        if(organizations.getManagerFlag()!=null && organizations.getManagerFlag()==1) {
            criteria.andMarketIdEqualTo(organizations.getMarketId());
        }
        if(StringUtils.isNotEmpty(organizations.getOrgName())){
            organizations.setOrgName("%" + organizations.getOrgName() + "%");
            criteria.andOrgNameLike(organizations.getOrgName());
        }
        if(StringUtils.isNotEmpty(organizations.getOrgId())){
            criteria.andOrgIdEqualTo(organizations.getOrgId());
        }
        return organizationsMapper.selectByExample(example);
    }

    @Override
    public InterfaceResult saveOrUpdateOrganizations(Organizations organizations) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        String orgId = organizations.getOrgId();
        //新增
        if(StringUtils.isBlank(orgId)){
            OrganizationsExample example = new OrganizationsExample();
            example.createCriteria().andOrgNameEqualTo(organizations.getOrgName()).andIsvalidEqualTo(1);
            List<Organizations> organizations1 = organizationsMapper.selectByExample(example);
            if(organizations1!=null && organizations1.size()>0){
                interfaceResult.InterfaceResult600("部门名已存在");
                return interfaceResult;
            }else {
            	example = new OrganizationsExample();
                example.createCriteria().andOrgCodeEqualTo(organizations.getOrgCode());

                organizations1 = organizationsMapper.selectByExample(example);
                if(organizations1!=null && organizations1.size()>0){
                    interfaceResult.InterfaceResult600("部门编号已存在");
                    return interfaceResult;

                }else{
                	orgId = UuidUtils.generateIdentifier();
                    organizations.setOrgId(orgId);
                    count = organizationsMapper.insertSelective(organizations);
                }

            }
        }else{//修改
            count = organizationsMapper.updateByPrimaryKeySelective(organizations);
        }
        return interfaceResult;
    }
//
//	@Override
//	public InterfaceResult delOrganizations(String id) throws Exception {
//		 InterfaceResult interfaceResult = new InterfaceResult();
//		 int count = 0;
//	     count = organizationsMapper.deleteByPrimaryKey(id);
//	     if(count!=0){
//	    	 interfaceResult.InterfaceResult200("删除成功");
//	    	 
//	     }else{
//	    	 interfaceResult.InterfaceResult600("删除失败");
//	     }
//	       
//	        return interfaceResult;
//	}
//
//	@Override
//	public InterfaceResult updateByOrgId(String orgId) throws Exception {
//		 InterfaceResult interfaceResult = new InterfaceResult();
//		 int count = 0;
//	     count = organizationsMapper.updateByOrgId(orgId);
//	     if(count!=0){
//	    	 interfaceResult.InterfaceResult200("更新成功");
//	    	 
//	     }else{
//	    	 interfaceResult.InterfaceResult600("更新失败");
//	     }
//	       
//	        return interfaceResult;
//	}
    @Override
    public InterfaceResult delOrUpdateOrgId(String id,int isvalid) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria()
                .andIsValidEqualTo(1)
                .andStaffTypeEqualTo(1)
                .andGroupIdEqualTo(id);
        List<Staff> staffList = staffMapper.selectByExample(staffExample);
        if(staffList != null && staffList.size()>0){
            interfaceResult.InterfaceResult600("请先解除市场员工和部门的关联在进行删除");
            return interfaceResult;
        }else {
            if (isvalid == 1) {
                organizationsMapper.deleteByPrimaryKey(id);
                return interfaceResult;
            }if (isvalid == 0){
                organizationsMapper.updateByOrgId(id);
                return interfaceResult;
            }
        }
        return interfaceResult;
    }

    @Override
    public BaseDao<Organizations, String> getBaseMapper() {
        return organizationsMapper;
    }


    @Override
    public List<Organizations> selectOrganizationsById(String id){
        OrganizationsExample example = new OrganizationsExample();
        OrganizationsExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdNotEqualTo(id);
        return organizationsMapper.selectByExample(example);
    }

    @Override
    public List<Organizations> getOrganizationsByMarket(String marketId) {
        return organizationsMapper.getOrganizationsByMarket(marketId);
    }

    @Override
    public List<Organizations> getOrgByCodeAndMarketId(String marketId) {
        return organizationsMapper.getOrgByCodeAndMarketId(marketId);
    }

    @Override
    public List<Organizations> getOrganizationsByMarketAndCode(String marketId, String code) {
        OrganizationsExample example = new OrganizationsExample();
        example.createCriteria().andMarketIdEqualTo(marketId).andOrgCodeEqualTo(code);
        return organizationsMapper.selectByExample(example);
    }
}