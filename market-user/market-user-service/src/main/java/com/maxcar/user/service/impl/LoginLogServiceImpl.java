package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.user.dao.LoginLogMapper;
import com.maxcar.user.entity.LoginLog;
import com.maxcar.user.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginLogService")
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog,String> implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public BaseDao<LoginLog, String> getBaseMapper() {
        return loginLogMapper;
    }
}
