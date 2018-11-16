package com.maxcar.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;

public class SmsUtil {

    private static final Logger log = LoggerFactory.getLogger(SmsUtil.class);

    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";
    private static final String accessKeyId = "LTAICiWi9EI7jW7Y";
    private static final String accessKeySecret = "6zoFcTjrfrrMqqBX4HdlQ04JOGsawk";

    private static final String signName = "阿里云短信测试专用";

    /**
     * 发短信
     *
     * @param phoneNum     手机号
     * @param templateCode 模板code
     * @param vars         模板变量
     * @return
     */
    public static boolean sendSMS(String phoneNum, String templateCode, Map<String, Object> vars) {

        if (StringUtils.isBlank(phoneNum)) {
            throw new IllegalArgumentException("phone number 不能为空");
        }

        if (StringUtils.isBlank(templateCode)) {
            throw new IllegalArgumentException("template code 不能为空");
        }

        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);

        request.setPhoneNumbers(phoneNum);

        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(toJson(vars));

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return true;
            }
        } catch (ClientException e) {
            log.error("send sms error. phoneNum:{}, templateCode:{} error:", phoneNum, templateCode, e);
        }

        return false;
    }

    /**
     * 获取四位数的手机验证码
     *
     * @return
     */
    public static String getMobileCode() {
        StringBuffer code = new StringBuffer();
        Random random = new Random();

        while (code.length() < 4) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
    }
}
