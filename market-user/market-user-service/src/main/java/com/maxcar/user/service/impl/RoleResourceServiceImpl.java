package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.user.dao.ResourceMapper;
import com.maxcar.user.dao.RoleResourceMapper;
import com.maxcar.user.entity.Resource;
import com.maxcar.user.entity.RoleResource;
import com.maxcar.user.entity.RoleResourceExample;
import com.maxcar.user.service.ResourceService;
import com.maxcar.user.service.RoleResourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: 角色资源服务层
* @Param: 
* @return: 
* @Author: 罗顺锋
* @Date: 2018/4/24
*/
@Service("roleResourceService")
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource,String> implements RoleResourceService {
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceService resourceService;
    @Override
    public BaseDao<RoleResource, String> getBaseMapper() {
        return roleResourceMapper;
    }

    @Override
    public Integer ValidRoleResource(String roleId, String resource) {
        String resourceId = resourceService.getResourceIdByUrl(resource);
        //该链接不在资源时，提示用户无盖请求资源，404
        if(StringUtils.isEmpty(resourceId)) {
            return 0;
        }
        //再验证改角色是否有此资源访问权限
        RoleResourceExample example = new RoleResourceExample();
        example.createCriteria().andResourceIdEqualTo(resourceId);
        List<RoleResource> resources = roleResourceMapper.selectByExample(example);
        return (resources!=null&&resources.size()>0)?1:2;
    }

    @Override
    public List<Resource> getResourcesByUserId(String userId,Boolean ifAll) {
        Map map = new HashMap();
        map.put("userId",userId);
        map.put("ifAll",ifAll);
        map.put("level",1);//一级菜单
        List<Resource> resources = roleResourceMapper.findMenuByUserId(map);
        resources = getChildResources(resources,userId);
        return resources;
    }

    @Override
    public List<Resource> getChildResources(List<Resource> resources,String userId) {
        for(Resource resource:resources) {
            Map map = new HashMap();
            map.put("userId",userId);
            map.put("parentId",resource.getResourceId());//一级菜单
//            ResourceExample resourceExample = new ResourceExample();
//            resourceExample.createCriteria().andParentIdEqualTo(resource.getResourceId()).
//                    andResourceTypeEqualTo(0);
//            List<Resource> childRes =  resourceMapper.selectByExample(resourceExample);
            List<Resource> childRes = roleResourceMapper.findMenuByUserId(map);
            resource.setChildList(childRes);
            if(childRes!=null) {
                getChildResources(childRes,userId);
            }
        }
        return resources;
    }


}