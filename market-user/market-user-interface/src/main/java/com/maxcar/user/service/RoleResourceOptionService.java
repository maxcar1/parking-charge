package com.maxcar.user.service;

import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.ResourceOption;
import com.maxcar.user.entity.RoleResourceOption;

import java.util.List;

/**
 * @program: maxcar-util
 * @description: 角色拥有的操作权限
 * @author: 罗顺锋
 * @create: 2018-05-11 14:14
 **/
public interface RoleResourceOptionService extends BaseService<RoleResourceOption,String> {
    /**
    * @Description: 根据角色和资源获取，某个资源中角色对应的操作权限
    * @Param: [roleId, resourceId]
    * @return: java.util.List<com.maxcar.permission.pojo.ResourceOption>
    * @Author: 罗顺锋
    * @Date: 2018/5/11
    */
    public List<ResourceOption> getResourceOptionByRoleIdAndResourceId(String roleId,String parentRoleId, String resourceId)throws Exception;
}
