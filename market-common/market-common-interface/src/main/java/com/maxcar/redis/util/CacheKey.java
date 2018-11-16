package com.maxcar.redis.util;

public interface CacheKey {

    /**
     * staff_token 缓存 staff_id
     */
    String STAFF_TOKEN_UID = "kv:token:{0}";

    /**
     * staff_token 缓存 手机号码
     */
    String STAFF_TOKEN_PHONE = "kv:{0}:phone";

    /**
     * 登录手机验证码
     */
    String LOGIN_PHONE_CODE = "kv:login:code:{0}";

    /**
     * 限制 IP 手机验证码发送次数
     */
    String IP_SMS_CODE_TIMES = "kv:ip:{0}:code:t";

}
