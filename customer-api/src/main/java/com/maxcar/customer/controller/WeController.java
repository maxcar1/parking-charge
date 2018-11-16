package com.maxcar.customer.controller;

import com.maxcar.BaseController;
import com.maxcar.base.util.RedisUtil;
import com.maxcar.base.util.SerializeUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.base.util.dasouche.ResultCode;
import com.maxcar.base.util.dasouche.TimeStampUtils;
import com.maxcar.base.util.wechat.SHAUtil;
import com.maxcar.base.util.wechat.WeChatProcess;
import com.maxcar.base.util.wechat.entity.ExtAccessToken;
import com.maxcar.base.util.wechat.entity.ResponseContent;
import com.maxcar.base.util.wechat.entity.SignUtil;
import com.maxcar.base.util.wechat.entity.WeixinUtil;
import com.maxcar.customer.service.WeChatService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.service.MarketService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Icar管家/商管家微信鉴权
 *
 * @author huangxu
 * @ClassName: WeController
 * @date 2018年1月18日 下午9:08:08
 */
@RestController
@RequestMapping("/we")
public class WeController extends BaseController {
    @Autowired
    private MarketService marketService;
    @Autowired
    private WeChatService weChatService;

    /**
     * 供微信调用接口
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public String fromWe(HttpServletRequest request) throws IOException {
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
        /** 读取接收到的xml消息 */
        StringBuffer sb = new StringBuffer();
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String xml = sb.toString(); //此为接收到微信端发送过来的xml数据  
        String result = "null information";
        /** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
        if (null != echostr && echostr.length() > 1) {
            result = echostr;
        }
        //判断是否微信服务器发来的消息
        if (null != signature && null != timestamp && null != nonce) {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                //正常的微信处理流程
                if (xml != null && !xml.equals(""))
                    result = new WeChatProcess().processWechatMag(xml);
            }
        }
        return result;
    }

    //微信公众号登录授权
    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public Result getToken(HttpServletRequest request) {
        Logger.getRootLogger().info("开始执行tokens");

        String marketId = request.getParameter("market");
        String customer = request.getHeader("customer");
        String code = request.getParameter("code");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("market", marketId);
        jsonObject.put("code", code);
        jsonObject.put("customer", customer);
        JSONObject result = weChatService.getOpenId(jsonObject);
        if (result == null) {
            Logger.getRootLogger().info(result);
            return Result.error(1, "获取openId失败");
        }

        if (result.containsKey("param_Error")) {
            Logger.getRootLogger().info(result.getString("param_Error"));
            return Result.error(1, result.getString("param_Error"));
        }

        if (result.containsKey("data_Error")) {
            Logger.getRootLogger().info(result.getString("data_Error"));
            return Result.error(1, result.getString("data_Error"));
        }

        if (result.containsKey("subscribe") && (result.getString("subscribe").equals("0"))) {
            return Result.ok(200, "OK", result);
        }
        if (result.containsKey("openid")) {
            String token = weChatService.getToken(request.getParameter("code"), result.getString("openid"));
            if (StringUtils.isBlank(token)) {
                return Result.error(1, "获取token失败");
            }
            result.put("token", token);
            Logger.getRootLogger().info(result.getString("token"));
            result.put("expire", System.currentTimeMillis());
        } else {
            return Result.error(1, "获取openId失败");
        }
        Logger.getRootLogger().info("tokens执行完成");
        return renderSuccessItem(result, "success", ResultCode.GET_SUCCESS.getCode());
    }

    /**
     * 微信端请求jsapi签名 Icar
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jsapi", method = RequestMethod.GET)
    public Object getJsApiForIcar(HttpServletRequest request) throws Exception {
//		Logger.getRootLogger().info("getJsApi--params》"+params.toString());
        ResponseContent content = new ResponseContent();
        String url = null;
        String market = null;
        ExtAccessToken ext = new ExtAccessToken();
        try {
            url = request.getParameter("url");
            url = url.trim();
            Logger.getRootLogger().info("url->" + url);
        } catch (Exception e) {
            content.setMessage("url地址缺失!");
            content.setResultCode(1);
            return content;
        }
        try {
            market = request.getParameter("market");
            Logger.getRootLogger().info("market->" + market);
        } catch (Exception e) {
            content.setMessage("market值缺失!");
            content.setResultCode(1);
            return content;
        }
        Market marketEntity = marketService.getMarketById(market);
        if (marketEntity != null && StringUtils.isNotBlank(marketEntity.getAppid2())
                && StringUtils.isNotBlank(marketEntity.getAppsecret2())) {
            ext.setAppid2(marketEntity.getAppid2());
            ext.setApp_secret2(marketEntity.getAppsecret2());
        } else {
            return ResponseContent.error(1, "未查询到当前市场信息");
        }
        String token = "we_customer_" + market;
        byte[] tokenByte = SerializeUtil.serialize(token);
        if (tokenByte == null) {
            return ResponseContent.error(1, "序列化失败");
        }
        String timestamp = TimeStampUtils.getTimeM();
        //自定义随机字符串
        String noncestr = "suiJiZiFuChuanMaxCar";
        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=");
        byte[] extByte = RedisUtil.getInstance().strings().get(tokenByte);
        if (extByte == null) {
            return ResponseContent.error(1, "未查询到登录信息");
        }
        ext = (ExtAccessToken) SerializeUtil.unserialize(extByte);
        if (ext == null) {
            return ResponseContent.error(1, "反序列化异常");
        }
        String accessToken = ext.getAccess_token2();
        String jsapiKey = "we_customer_jsapi_" + market;
        String jsapiValue = RedisUtil.getInstance().strings().get(jsapiKey);
        if (StringUtils.isBlank(jsapiValue)) {
            String jsApiTicket = WeixinUtil.getJSApiTicket(accessToken);
            if (StringUtils.isBlank(jsApiTicket)) {
                return ResponseContent.error(1, "获取jsApiTicket失败");
            }
            Logger.getRootLogger().info("刚获取的jsApiTicket->" + jsApiTicket);
            RedisUtil.getInstance().strings().setEx(jsapiKey, 3600, jsApiTicket);
        }
        Logger.getRootLogger().info("======jsapiTicket未过期======");
        Logger.getRootLogger().info("jsapi: " + jsapiValue);
        sb.append(jsapiValue);
        sb.append("&noncestr=");
        sb.append(noncestr);
        sb.append("&timestamp=");
        sb.append(timestamp);
        sb.append("&url=");
        sb.append(url);
        Logger.getRootLogger().info("加密前的组装：" + sb.toString());
        String signature = SHAUtil.getSha1(sb.toString());
        Logger.getRootLogger().info("加密后的签名：" + signature);
        JSONObject obj = new JSONObject();
        obj.put("noncestr", noncestr);
        obj.put("signature", signature);
        obj.put("timestamp", timestamp);
        if (StringUtils.isNotBlank(ext.getAppid2())) {
            obj.put("appId", ext.getAppid2());
        } else {
            content.setMessage("当前市场尚未配置appId");
            content.setResultCode(2);
            return content;
        }
        Logger.getRootLogger().info("Icar签完名的数据-->" + obj.toString());
        content.setDatas(obj);
        return content;
    }

    /**
     * 微信端请求jsapi签名 Tenant
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jsapi/tenant", method = RequestMethod.GET)
    public Object getJsApiForTenant(HttpServletRequest request) throws Exception {
//		Logger.getRootLogger().info("getJsApi--params》"+params.toString());
        ResponseContent content = new ResponseContent();
        String url = null;
        String market = null;
        ExtAccessToken ext = new ExtAccessToken();
        try {
            url = request.getParameter("url");
            url = url.trim();
            Logger.getRootLogger().info("url->" + url);
        } catch (Exception e) {
            content.setMessage("url地址缺失!");
            content.setResultCode(1);
            return content;
        }
        try {
            market = request.getParameter("market");
            Logger.getRootLogger().info("market->" + market);
        } catch (Exception e) {
            content.setMessage("market值缺失!");
            content.setResultCode(1);
            return content;
        }
        Market marketEntity = marketService.getMarketById(market);
        if (marketEntity != null && StringUtils.isNotBlank(marketEntity.getAppid())
                && StringUtils.isNotBlank(marketEntity.getAppsecret())) {
            ext.setAppid(marketEntity.getAppid());
            ext.setApp_secret(marketEntity.getAppsecret());
        } else {
            return ResponseContent.error(1, "未查询到当前市场信息");
        }
        String token = "we_tenant_" + market;
        byte[] tokenByte = SerializeUtil.serialize(token);
        if (tokenByte == null) {
            return ResponseContent.error(1, "序列化失败");
        }
        String timestamp = TimeStampUtils.getTimeM();
        //自定义随机字符串
        String noncestr = "suiJiZiFuChuanMaxCar";
        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=");
        byte[] extByte = RedisUtil.getInstance().strings().get(tokenByte);
        if (extByte == null) {
            return ResponseContent.error(1, "未查询到登录信息");
        }
        ext = (ExtAccessToken) SerializeUtil.unserialize(extByte);
        if (ext == null) {
            return ResponseContent.error(1, "反序列化异常");
        }
        String accessToken = ext.getAccess_token();
        String jsapiKey = "we_tenant_jsapi_" + market;
        String jsapiValue = RedisUtil.getInstance().strings().get(jsapiKey);
        if (StringUtils.isBlank(jsapiValue)) {
            String jsApiTicket = WeixinUtil.getJSApiTicket(accessToken);
            if (StringUtils.isBlank(jsApiTicket)) {
                return ResponseContent.error(1, "获取jsApiTicket失败");
            }
            Logger.getRootLogger().info("刚获取的jsApiTicket->" + jsApiTicket);
            RedisUtil.getInstance().strings().setEx(jsapiKey, 3600, jsApiTicket);
        }
        Logger.getRootLogger().info("======jsapiTicket未过期======");
        Logger.getRootLogger().info("jsapi: " + jsapiValue);
        sb.append(jsapiValue);
        sb.append("&noncestr=");
        sb.append(noncestr);
        sb.append("&timestamp=");
        sb.append(timestamp);
        sb.append("&url=");
        sb.append(url);
        Logger.getRootLogger().info("加密前的组装：" + sb.toString());
        String signature = SHAUtil.getSha1(sb.toString());
        Logger.getRootLogger().info("加密后的签名：" + signature);
        JSONObject obj = new JSONObject();
        obj.put("noncestr", noncestr);
        obj.put("signature", signature);
        obj.put("timestamp", timestamp);
        if (StringUtils.isNotBlank(ext.getAppid())) {
            obj.put("appId", ext.getAppid());
        } else {
            content.setMessage("当前市场尚未配置appId");
            content.setResultCode(2);
            return content;
        }
        Logger.getRootLogger().info("商管家签完名的数据-->" + obj.toString());
        content.setDatas(obj);
        return content;
    }

    @RequestMapping(value = "/apis/{token}", method = RequestMethod.GET)
    public Result check(@PathVariable("token") String token, HttpServletRequest request) {
        //有判断是否合法,根据token作为key 取 redis中对应的值
        String str = "";
        try {
            str = RedisUtil.getInstance().strings().get(token);
        } catch (Exception e) {
        }
        if (StringUtils.isNotBlank(str)) {
            return renderSuccess(ResultCode.GET_SUCCESS.getCode());
        }
        return renderError("token错误", ResultCode.TOKEN_ERRO.getCode());
    }
}

