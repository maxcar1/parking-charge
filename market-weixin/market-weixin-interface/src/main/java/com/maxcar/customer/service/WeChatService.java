package com.maxcar.customer.service;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信调用服务
 *
 * @author huangxu
 * @ClassName: WeWeChatService
 * @date 2018年1月20日 上午11:55:50
 */
public interface WeChatService {
    /**
     * 或者自定义secret
     *
     * @param request
     * @return
     */
    JSONObject getOpenId(JSONObject request);

    String getToken(String code, String openId);
}
