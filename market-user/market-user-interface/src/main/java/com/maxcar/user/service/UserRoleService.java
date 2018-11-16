package com.maxcar.user.service;

import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.Role;
import com.maxcar.user.entity.UserRole;

import java.util.List;

public interface UserRoleService  extends BaseService<UserRole,String> {
    /**
     * @Description: 根据用户id，获取角色列表
     * @Param: [userId]
     * @return: java.util.List<com.maxcar.permission.pojo.Role>
     * @Author: 罗顺锋
     * @Date: 2018/5/3
     */
    List<Role> selectRoleByUserId(String userId);
}