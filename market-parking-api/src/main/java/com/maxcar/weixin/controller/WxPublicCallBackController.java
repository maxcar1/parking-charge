package com.maxcar.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.util.wechat.SignUtil;
import com.maxcar.base.util.wechat.WeiXinUtils;
import com.maxcar.weixin.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 微信公共事件推送控制器
 * yangshujun
 * 废弃控制器,代码暂时保留
 */
@Deprecated
@RestController
@RequestMapping("/api/wx/public")
public class WxPublicCallBackController {

    private static final Logger logger = LoggerFactory.getLogger(WxPublicCallBackController.class);

    @Autowired
    private WeiXinService weiXinService;
    @Value("${weixin.token}")
    private String token;

    @RequestMapping("/event/icar")
    public String fromWeIcar(HttpServletRequest request) throws Exception {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戮
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        //刷新token的类别
//        String refreshToken = request.getParameter("refresh");
        // 随机字符串 初次验证时需要用到
        String echostr = request.getParameter("echostr");
        logger.info("===>进入了事件推送{},{},{},{}", signature, timestamp, nonce, echostr);
        String xml = WeiXinUtils.getXmlRequest(request);
        String result = "null information";
        /** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
        if (null != echostr && echostr.length() > 1) {
            result = echostr;
            return result;
        }
        //判断是否微信服务器发来的消息
        if (null != signature && null != timestamp && null != nonce) {
            if (SignUtil.checkSignature(signature, timestamp, nonce, token)) {
                //正常的微信处理流程
                JSONObject json = weiXinService.handlePublicMsg(xml, WeiXinUtils.WeiXinPublicNum.ICAR);
                result = json.getString("result");
            }
        }
        return result;
    }
}
