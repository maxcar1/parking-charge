package com.maxcar.user.service;

import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.Resource;
import com.maxcar.user.entity.RoleResource;

import java.util.List;
/**
* @Description: 角色资源服务层
* @Param: 
* @return: 
* @Author: 罗顺锋
* @Date: 2018/4/24
*/
public interface RoleResourceService extends BaseService<RoleResource,String> {

    /**
    * @Description: 根据用户角色验证，是否有访问某资源权限
    * @Param: [roleId, resource]
    * @return: java.lang.Integer,返回0请求无效，1有权限，2无权限
    * @Author: 罗顺锋
    * @Date: 2018/4/27
    */
    public Integer ValidRoleResource(String roleId, String resource);

    /**
    * @Description: 根据用户id获取用户菜单
     * false，获取用户所有菜单，区分选中未选中，true，只取选中菜单
    * @Param: [userId, ifAll]
    * @return: java.util.List<com.maxcar.permission.pojo.Resource>
    * @Author: 罗顺锋
    * @Date: 2018/4/28
    */
    public List<Resource> getResourcesByUserId(String userId, Boolean ifAll);

    /**
     * 递归查询获取子集合
     * @param resources
     * @return
     */
    public List<Resource> getChildResources(List<Resource> resources,String userId);
}