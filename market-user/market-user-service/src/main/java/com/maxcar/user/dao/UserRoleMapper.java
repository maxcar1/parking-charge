package com.maxcar.user.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.Role;
import com.maxcar.user.entity.UserRole;
import com.maxcar.user.entity.UserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper extends BaseDao<UserRole,String>{
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(String userRoleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(String userRoleId);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    /**
     * @Description: 根据用户id，获取角色列表
     * @Param: [userId]
     * @return: java.util.List<com.maxcar.permission.pojo.Role>
     * @Author: 罗顺锋
     * @Date: 2018/5/3
     */
    List<Role> selectRoleByUserId(String userId);

}