package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.user.dao.*;
import com.maxcar.user.entity.*;
import com.maxcar.user.service.RoleResourceOptionService;
import com.maxcar.user.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role,String> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceOptionService roleResourceOptionService;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private RoleResourceOptionMapper roleResourceOptionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseDao<Role, String> getBaseMapper() {
        return roleMapper;
    }

    @Override
    public List<Role> searchRole(Role role) throws Exception {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andIsvalidEqualTo(1);
        if(role.getManagerFlag()!=null && role.getManagerFlag()!=0) {
            criteria.andMarketIdEqualTo(role.getMarketId());
        }
//                .andRoleIdNotEqualTo("18091109452880497444").andMarketIdEqualTo(role.getMarketId());

        if(StringUtils.isNotBlank(role.getRoleName())){
            role.setRoleName("%" + role.getRoleName() + "%");
            criteria.andRoleNameLike(role.getRoleName());
        }

        example.setOrderByClause(" register_time desc");
        return roleMapper.selectByExample(example);
    }


    public InterfaceResult saveOrUpdateRole(Role role) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        String roleId = role.getRoleId();
        //新增
        if(StringUtils.isBlank(roleId)){
            // 插入之前验证用户名是否已存在
            RoleExample example = new RoleExample();
            RoleExample.Criteria criteria = example.createCriteria();
            criteria.andRoleNameEqualTo(role.getRoleName()).andIsvalidEqualTo(1);
            List<Role> roles = roleMapper.selectByExample(example);
            //如果用户名存在，返回
            if(roles!=null && roles.size()>0) {
                interfaceResult.InterfaceResult600("角色名已存在");
                return interfaceResult;
            }
            else {
                roleId = UuidUtils.generateIdentifier();
                role.setRoleId(roleId);
                roleMapper.insertSelective(role);
            }
        }else{//修改
            roleMapper.updateByPrimaryKeySelective(role);
            //删除角色权限
            removeRoleResource(roleId);
        }
        List<Resource> resources = role.getResources();//获取资源权限集合
//        List<ResourceData> resourceDatas = role.getResourceData();//获取资源权限集合
        List<ResourceOption> resourceOptions = role.getResourceOptions();//获取资源权限集合
        //新增资源权限和数据权限
        if(resources!=null&&resources.size()>0)
            saveRoleResource(roleId,resources);
//        if(resourceDatas!=null&&resourceDatas.size()>0)
//            saveRoleResourceData(roleId,resourceDatas);
        if(resourceOptions!=null&&resourceOptions.size()>0)
            saveRoleResourceOption(roleId,resourceOptions);
        return interfaceResult;
    }
    //删除角色权限
    protected void removeRoleResource(String roleId){
        //删除角色权限关系
        RoleResourceExample userRoleExample = new RoleResourceExample();
        userRoleExample.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceMapper.deleteByExample(userRoleExample);
        //删除角色对应的资源操作权限
        RoleResourceOptionExample roleResourceOptionExample = new RoleResourceOptionExample();
        roleResourceOptionExample.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceOptionMapper.deleteByExample(roleResourceOptionExample);
    }
    //新增资源权限和数据权限
    protected void saveRoleResource(String roleId,List<Resource> resources){
        for(Resource resource:resources) {

            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setRolePermissionsId(UuidUtils.generateIdentifier());
            roleResource.setResourceId(resource.getResourceId());
            roleResourceMapper.insertSelective(roleResource);
            //查询其他资源，也绑定给用户
            ResourceExample resourceExample = new ResourceExample();
            resourceExample.createCriteria().andParentIdEqualTo(resource.getResourceId()).andResourceTypeEqualTo(1);
            List<Resource> childResource = resourceMapper.selectByExample(resourceExample);
            for(Resource child:childResource){
                RoleResource childRoleResource = new RoleResource();
                childRoleResource.setRoleId(roleId);
                childRoleResource.setRolePermissionsId(UuidUtils.generateIdentifier());
                childRoleResource.setResourceId(child.getResourceId());
                roleResourceMapper.insertSelective(childRoleResource);
            }
        }
    }

    //新增资源权限和数据权限
    protected void saveRoleResourceOption(String roleId,List<ResourceOption> resources){
        for(ResourceOption resource:resources) {
            RoleResourceOption roleResourceOption = new RoleResourceOption();
            roleResourceOption.setRoleId(roleId);
            roleResourceOption.setRoleResourceOptionId(UuidUtils.generateIdentifier());
            roleResourceOption.setResourceOptionId(resource.getResourceOptionId());
            roleResourceOptionMapper.insertSelective(roleResourceOption);
        }
    }

    @Override
    public Role selectRoleById(String id,String parentRoleId) throws Exception {
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role!=null) {
            List<Resource> resources = getResourcesByRoleId(id,parentRoleId);
            role.setResources(resources);
        }
        return role;
    }

    @Override
    public List<Resource> getResourcesByRoleId(String roleId,String parentRoleId) throws Exception{
        Map map = new HashMap();
        map.put("roleId",roleId);
        map.put("parentRoleId",parentRoleId);//查询自己拥有所有资源
        map.put("level",1);//一级菜单
        List<Resource> resources = roleResourceMapper.findMenuByRoleId(map);
        resources = getChildResources(resources,roleId,parentRoleId);
        return  resources;
    }
    //递归查询菜单子菜单
    @Override
    public List<Resource> getChildResources(List<Resource> resources,String roleId,String parentRoleId)throws Exception {
        for(Resource resource:resources) {
            Map map = new HashMap();
            map.put("roleId",roleId);
            map.put("parentId",resource.getResourceId());//查询自己拥有所有资源
            map.put("parentRoleId",parentRoleId);//查询自己拥有所有资源
            List<Resource> childRes = roleResourceMapper.findMenuByRoleId(map);
//            ResourceExample resourceExample = new ResourceExample();
//            resourceExample.createCriteria().andParentIdEqualTo(resource.getResourceId()).
//                    andResourceTypeEqualTo(0);
//            List<Resource> childRes =  resourceMapper.selectByExample(resourceExample);
//            for(Resource res:childRes){
//                RoleResourceExample roleResourceExample = new RoleResourceExample();
//                roleResourceExample.createCriteria().andRoleIdEqualTo(roleId).andResourceIdEqualTo(res.getResourceId());
//                List<RoleResource> roleResources = roleResourceMapper.selectByExample(roleResourceExample);
//            }
            resource.setChildList(childRes);
            //插入角色资源操作信息
            List<ResourceOption> roleResourceOptions = roleResourceOptionService.getResourceOptionByRoleIdAndResourceId(roleId,parentRoleId,resource.getResourceId());
            resource.setResourceOptions(roleResourceOptions);
            if(childRes!=null) {
                getChildResources(childRes,roleId,parentRoleId);
            }
        }
        return resources;
    }


    @Override
    public InterfaceResult updateRoleStatus(String ids,Integer status) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        Role role = new Role();
        String roleId = role.getRoleId();
        boolean flag = true;
        for(String id:ids.split(",")) {
            UserRoleExample example = new UserRoleExample();
            example.createCriteria().andRoleIdEqualTo(id);
            List<UserRole> userRoles = userRoleMapper.selectByExample(example);
            if(userRoles != null && userRoles.size() > 0){
                flag = false;
                break;
            }

            if (status == -1) {
                count = roleMapper.deleteByPrimaryKey(id);

            } else {
                Role user = roleMapper.selectByPrimaryKey(id);
                if (user != null) {
                    user.setIsvalid(status);
                    count = roleMapper.updateByPrimaryKeySelective(user);
                }
            }
        }
        if (!flag){
            interfaceResult.InterfaceResult600("该角色下的账号未解绑！");
            return interfaceResult;
        }
        if (count==1){
            interfaceResult.InterfaceResult200(null);
        }

        return interfaceResult;
    }

}