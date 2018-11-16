package com.maxcar.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.wechat.FormatXmlProcess;
import com.maxcar.base.util.wechat.WeiXinUtils;
import com.maxcar.websocket.server.WebSocketServer;
import com.maxcar.weixin.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * 微信支付回调和扫码回调
 * yangshujun
 */
@RestController
@RequestMapping("/api-p/wx")
public class WeiXinPayCallBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinPayCallBackController.class);
    @Autowired
    private WeiXinService weiXinService;

    private static final String FAIL = "FAIL";
    private static final String SYSTEM_ERROR = "系统错误";

    @PostMapping("/public/order")
    public Object doOrder(@RequestBody JSONObject params,HttpServletRequest request) throws Exception{
        LOGGER.info("进入了公众号支付---ip==>{}",request.getRemoteAddr());
        params.put("ip",request.getRemoteAddr());
        InterfaceResult response = weiXinService.doOrder(params);
        return response;
    }

    @RequestMapping("/callback/pay")
    public Object payCallBack(HttpServletRequest request) {
        LOGGER.info("进入了支付回调通知---");
        String response = FormatXmlProcess.formatXmlResponse(FAIL, SYSTEM_ERROR);
        try {
            String xml = WeiXinUtils.getXmlRequest(request);
            JSONObject result = weiXinService.payCallBack(xml);
            response = result.getString("result");
            String barrierId = result.getString("barrierId");
            if (result.containsKey("parking")){
                Date b = new Date();
                JSONObject json = result.getJSONObject("parking");
                b = json.getDate("beforeTime");
                json.put("beforeTime",DateUtils.formatDate(b));
                b = json.getDate("afterTime");
                json.put("afterTime",DateUtils.formatDate(b));
                //前端推送识别 1为微信回调
                json.put("type",1);
                //websocket传输数据到收费页面
                WebSocketServer.sendInfo(json.toJSONString(),barrierId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
