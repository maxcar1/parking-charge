package com.maxcar.customer.controller;

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

@RestController
@RequestMapping("/we/tenant")
public class TenantController {

    static Logger LOGGER = LoggerFactory.getLogger(ICarController.class);

    @Value("${tenant.token}")
    private String token;

    @Autowired
    private WeiXinService weiXinService;

    @GetMapping
    public Object checkToken(@RequestParam("signature") String signature,
                             @RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce,
                             @RequestParam("echostr") String echostr){
        if (SignUtil.checkSignature(signature, timestamp, nonce,token)) {
            return echostr;
        }
        return "token check error";
    }

    @PostMapping
    public Object handleWeiXin(HttpServletRequest request){
        String result ="";
        try {
            String res = WeiXinUtils.getXmlRequest(request);
            LOGGER.info("来自微信的推送消息==>{}",res);
            JSONObject json = weiXinService.handlePublicMsg(res,WeiXinUtils.WeiXinPublicNum.TENANT);
            result = json.getString("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
