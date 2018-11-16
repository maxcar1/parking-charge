package com.maxcar.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.wechat.ReceiveXmlEntity;
import com.maxcar.base.util.wechat.WeiXinUtils;
import com.maxcar.weixin.model.UserInfo;

public interface WeiXinService {
    /**
     * 获取停车进场二维码
     * @param codeType 是否永久
     * @param sceneStr 场景id
     * @return
     * @throws Exception
     */
    public String getTicket(Integer codeType, String sceneStr,String publicNum) throws Exception;

    /**
     * 处理公共推送事件逻辑
     * @param xml
     * @return
     * @throws Exception
     */
    JSONObject handlePublicMsg(String xml, WeiXinUtils.WeiXinPublicNum type) throws Exception;

    /**
     * 生成支付二维码，应用于出场
     * @param productId
     * @return
     * @throws Exception
     */
    InterfaceResult getPayQRCode(String productId) throws Exception;

    /**
     * 处理微信统一下单请求
     * @param params
     * @return
     */
    InterfaceResult doOrder(JSONObject params) throws Exception;

    JSONObject payCallBack(String xml) throws Exception;

    InterfaceResult getJsApi() throws Exception;

    InterfaceResult escapeHatch(JSONObject params) throws Exception;

    UserInfo getUserInfo(String openId, String appId, String appSecret, String key) throws Exception;

    String doResponseByPaiBo(ReceiveXmlEntity receiveXmlEntity, int type, JSONObject parking) throws Exception;

    String cacheTokenInRedis(String appId, String appSecret, String key) throws Exception;

}
