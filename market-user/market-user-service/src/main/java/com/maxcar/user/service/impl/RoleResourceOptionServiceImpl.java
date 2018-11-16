package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.user.dao.RoleResourceOptionMapper;
import com.maxcar.user.entity.ResourceOption;
import com.maxcar.user.entity.RoleResourceOption;
import com.maxcar.user.service.RoleResourceOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: maxcar-util
 * @description: 角色拥有的操作权限
 * @author: 罗顺锋
 * @create: 2018-05-11 14:14
 **/
@Service("roleResourceOptionService")
public class RoleResourceOptionServiceImpl extends BaseServiceImpl<RoleResourceOption,String> implements RoleResourceOptionService {
    @Autowired
    private RoleResourceOptionMapper roleResourceOptionMapper;

    @Override
    public BaseDao<RoleResourceOption, String> getBaseMapper() {
        return roleResourceOptionMapper;
    }

    @Override
    public List<ResourceOption> getResourceOptionByRoleIdAndResourceId(String roleId, String parentRoleId,String resourceId) throws Exception{
        Map map = new HashMap();
        map.put("roleId",roleId);
        map.put("resourceId",resourceId);
        map.put("parentRoleId",parentRoleId);
        return roleResourceOptionMapper.getResourceOptionaByRoleIdOrResourceId(map);
    }
}
