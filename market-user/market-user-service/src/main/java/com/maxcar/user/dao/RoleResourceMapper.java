package com.maxcar.user.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.Resource;
import com.maxcar.user.entity.RoleResource;
import com.maxcar.user.entity.RoleResourceExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleResourceMapper extends BaseDao<RoleResource,String>{
    /**
     * @Description: 根据用户id获取用户菜单
     * @Param: [userId]
     * @return: java.util.List<com.maxcar.permission.pojo.Resource>
     * @Author: 罗顺锋
     * @Date: 2018/4/27
     */
    List<Resource> findMenuByUserId(Map map);
    /**
     * @Description: 根据角色id获取角色拥有菜单权限
     * @Param: [map]
     * @return: java.util.List<com.maxcar.permission.pojo.Resource>
     * @Author: 罗顺锋
     * @Date: 2018/5/11
     */
    List<Resource> findMenuByRoleId(Map map);

    int countByExample(RoleResourceExample example);

    int deleteByExample(RoleResourceExample example);

    int deleteByPrimaryKey(String rolePermissionsId);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    List<RoleResource> selectByExample(RoleResourceExample example);

    RoleResource selectByPrimaryKey(String rolePermissionsId);

    int updateByExampleSelective(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByExample(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
}