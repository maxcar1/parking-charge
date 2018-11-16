package com.maxcar.redis.service.impl;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.redis.dao.JedisSingleDao;
import com.maxcar.redis.service.VCodeService;
import com.maxcar.redis.util.CacheKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service("vCodeService")
public class VCodeServiceImpl implements VCodeService{

    @Autowired
    private JedisSingleDao jedisSingleDao;

    @Override
    public InterfaceResult sendLoginVCode(String phoneNum, String ip) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        String ipKey = MessageFormat.format(CacheKey.IP_SMS_CODE_TIMES, ip);
        Long times = jedisSingleDao.getLongValue(ipKey);
        if (times != null && times >= Constants.IP_SEND_TIMES ) {
            interfaceResult.InterfaceResult600("短信发送频繁，请稍后再试。");
            return interfaceResult;
        }

        Map<String, Object> vars = new HashMap<>(2);
//        String code = SmsUtil.getMobileCode();
        String code = "1234";
        vars.put("code", code);
//        SmsUtil.sendSMS(phoneNum, "SMS_140705020", vars);

        String key = MessageFormat.format(CacheKey.LOGIN_PHONE_CODE, phoneNum);
        jedisSingleDao.set(key, code);
        jedisSingleDao.expire(key, 120);
        jedisSingleDao.increment(ipKey, 1);
        jedisSingleDao.expire(key, 300);

        return interfaceResult;
    }
}
