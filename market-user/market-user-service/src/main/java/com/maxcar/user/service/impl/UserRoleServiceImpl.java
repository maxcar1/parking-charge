package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.user.dao.UserRoleMapper;
import com.maxcar.user.entity.Role;
import com.maxcar.user.entity.UserRole;
import com.maxcar.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole,String> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseDao<UserRole, String> getBaseMapper() {
        return userRoleMapper;
    }


    @Override
    public List<Role> selectRoleByUserId(String userId) {
        return userRoleMapper.selectRoleByUserId(userId);
    }
}