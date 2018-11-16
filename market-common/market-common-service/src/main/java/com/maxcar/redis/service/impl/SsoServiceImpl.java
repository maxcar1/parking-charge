package com.maxcar.redis.service.impl;


import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.redis.dao.impl.JedisDaoImpl;
import com.maxcar.redis.service.SsoService;
import com.maxcar.redis.util.ProResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: maxcarredismaxcar-redis
 * @description: 用户登录，注销，验证码等服务实现类
 * @author: 罗顺锋
 * @create: 2018-04-19 16:50
 **/
@Service("ssoService")
public class SsoServiceImpl implements SsoService {

    @Autowired
    JedisDaoImpl jedisSingleDao;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private String SSO_SESSION_EXPIRE;

    //验证码
    @Value("${REDIS_SMS_SESSION_KEY}")
    private String REDIS_SMS_SESSION_KEY;

    @Value("${SMS_SESSION_EXPIRE}")
    private String SMS_SESSION_EXPIRE;

    /**
     * 单点登录
     */
    @Override
    public InterfaceResult login(String userId, Integer time) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //生成token
        String token = UuidUtils.generateIdentifier();
        String key = REDIS_USER_SESSION_KEY + ":" + token;
        //把用户信息写入redis
        jedisSingleDao.set(key, userId);
        //设置session的过期时间
//        jedisSingleDao.expire(REDIS_USER_SESSION_KEY + ":" + token, Integer.parseInt(SSO_SESSION_EXPIRE));
        jedisSingleDao.expire(key, Integer.parseInt(SSO_SESSION_EXPIRE));
        if(time == -1)
            jedisSingleDao.persist(key);
        interfaceResult.InterfaceResult200(token);
        return interfaceResult;
    }

    /**
     * 更具token获取用户信息
     */
    @Override
    public InterfaceResult getUserByToken(String token) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        String json = jedisSingleDao.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isEmpty(json)) {
            interfaceResult.InterfaceResult600("session已经过期，请重新登录");
            return interfaceResult;
        }
        //更新过期时间
        jedisSingleDao.expire(REDIS_USER_SESSION_KEY + ":" + token, Integer.parseInt(SSO_SESSION_EXPIRE));
        interfaceResult.InterfaceResult200(json);
        return interfaceResult;
    }

    /**
     * 更具token删除用户信息
     */
    @Override
    public InterfaceResult clearUserByToken(String token) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        String json = jedisSingleDao.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isEmpty(json)) {
            interfaceResult.InterfaceResult600("session已经过期");
            return interfaceResult;
        }
        //更新过期时间
        jedisSingleDao.del(REDIS_USER_SESSION_KEY + ":" + token);
        interfaceResult.InterfaceResult200("session清空");
        return interfaceResult;
    }

    @Override
    public InterfaceResult setStringKey(String key, String value, Integer time) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //把信息写入redis
        jedisSingleDao.set(key, value);
        //设置session的过期时间,如果未传入，按照用户session时间一样
        jedisSingleDao.expire(key, time == -1 ? Integer.parseInt(SSO_SESSION_EXPIRE) : time);
        return interfaceResult;
    }

    @Override
    public InterfaceResult getStringKey(String key) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        String json = jedisSingleDao.get(key);
        if (StringUtils.isEmpty(json)) {
            interfaceResult.InterfaceResult600("session已经过期，请重新登录");
            return interfaceResult;
        }
        interfaceResult.InterfaceResult200(json);
        return interfaceResult;
    }

    @Override
    public InterfaceResult setKeyTime(String key, Integer time) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        //设置session的过期时间,如果未传入，按照用户session时间一样
        jedisSingleDao.expire(REDIS_USER_SESSION_KEY + ":" + key, time == -1 ? Integer.parseInt(SSO_SESSION_EXPIRE) : time);
//		return new ProResult("200",null);
        return interfaceResult;
    }

    /**
     * 会员登录入口
     *
     * @param phone
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public InterfaceResult setValidCode(String phone, String code) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //生成token
        String token = UuidUtils.generateIdentifier();
        //把用户信息写入redis
        jedisSingleDao.set(REDIS_SMS_SESSION_KEY + ":" + phone, code);
        //设置session的过期时间
        jedisSingleDao.expire(REDIS_SMS_SESSION_KEY + ":" + phone, Integer.parseInt(SMS_SESSION_EXPIRE));
//		return new ProResult("200",token);
        interfaceResult.InterfaceResult200(token);
        return interfaceResult;
    }

    @Override
    public InterfaceResult getValidCode(String phone) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        String json = jedisSingleDao.get(REDIS_SMS_SESSION_KEY + ":" + phone);
        if (StringUtils.isEmpty(json)) {
            interfaceResult.InterfaceResult600("验证码已经过期，请重新发送");
        }
        interfaceResult.InterfaceResult200(json);
        return interfaceResult;
    }

}
