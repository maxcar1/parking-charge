package com.maxcar.redis.service;

import com.maxcar.base.pojo.InterfaceResult;

public interface VCodeService {

    InterfaceResult sendLoginVCode(String phoneNum, String ip) throws Exception;
}
