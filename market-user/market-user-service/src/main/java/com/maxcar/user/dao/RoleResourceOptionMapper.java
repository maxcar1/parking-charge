package com.maxcar.user.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.ResourceOption;
import com.maxcar.user.entity.RoleResourceOption;
import com.maxcar.user.entity.RoleResourceOptionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleResourceOptionMapper extends BaseDao<RoleResourceOption,String>{
    int countByExample(RoleResourceOptionExample example);

    int deleteByExample(RoleResourceOptionExample example);

    int deleteByPrimaryKey(String roleResourceOptionId);

    int insert(RoleResourceOption record);

    int insertSelective(RoleResourceOption record);

    List<RoleResourceOption> selectByExample(RoleResourceOptionExample example);

    RoleResourceOption selectByPrimaryKey(String roleResourceOptionId);

    int updateByExampleSelective(@Param("record") RoleResourceOption record, @Param("example") RoleResourceOptionExample example);

    int updateByExample(@Param("record") RoleResourceOption record, @Param("example") RoleResourceOptionExample example);

    int updateByPrimaryKeySelective(RoleResourceOption record);

    int updateByPrimaryKey(RoleResourceOption record);
    /**
     * @Description: 根据角色资源查询，该资源下某角色拥有的操作权限，或者某角色下所有操作权限（当resourceId为null）
     * @Param: [map]
     * @return: java.util.List<com.maxcar.permission.pojo.ResourceOption>
     * @Author: 罗顺锋
     * @Date: 2018/5/11
     */
    List<ResourceOption> getResourceOptionaByRoleIdOrResourceId(Map map);
}