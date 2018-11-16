package com.maxcar.user.service;


import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.Resource;
import com.maxcar.user.entity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role,String> {
    /**
    * @Description: 角色搜索
    * @Param: [user]
    * @return: java.util.List<com.maxcar.permission.pojo.Role>
    * @Author: 罗顺锋
    * @Date: 2018/5/3
    */
    public List<Role> searchRole(Role role)throws Exception;

    /**
    * @Description: 新增或者修改角色，传入资源id，数据权限id
    * @Param: [role]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/4
    */
    public InterfaceResult saveOrUpdateRole(Role role)throws Exception;

    /**
     * 从底层获取市场信息
     * @param syncMarket
     * @return
     * @throws Exception
     */
//    public List<SyncMarket> searchMarket(SyncMarket syncMarket)throws Exception;

    /**
    * @Description: 根据id查询角色以及权限,同时传入当前登录用户角色id，分配当前登录用户所拥有的权限
    * @Param: [id]
    * @return: java.util.List<com.maxcar.permission.pojo.Role>
    * @Author: 罗顺锋
    * @Date: 2018/5/4
    */
    public Role selectRoleById(String id,String parentRoleId)throws Exception;

    /**
     * 根据角色id获取资源权限列表
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<Resource> getResourcesByRoleId(String roleId,String parentRoleId)throws Exception;

    /**
     * 递归查询获取子集合
     * @param resources
     * @return
     */
    public List<Resource> getChildResources(List<Resource> resources, String roleId,String parentRoleId) throws Exception;

    /**
     * @Description: 修改用户状态，删除用户，启用，弃用用户，-1物理删除，0弃用，1启用
     * @Param: [status,id]
     * @return: boolean
     * @Author: 罗顺锋
     * @Date: 2018/4/25
     */
    public InterfaceResult updateRoleStatus(String id, Integer status)throws Exception;
}