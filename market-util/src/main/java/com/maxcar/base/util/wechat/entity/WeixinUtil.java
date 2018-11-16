package com.maxcar.base.util.wechat.entity;

import com.maxcar.base.util.RedisUtil;
import com.maxcar.base.util.SerializeUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.wechat.MyX509TrustManager;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

/**
 * 微信公众平台 请求工具类
 *
 * @author ldc
 */
public class WeixinUtil {
    // 获取openid请求
    public final static String openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";

    // 获取基础access_token请求
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    // 获取jsapi_ticket请求
    public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESSTOKEN&type=jsapi";

    // 获取用户信息请求
    public final static String userinfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESSTOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 第三方公众号获取openid 根据code值 基础信息授权版本(商户版本)
     *
     * @param code
     * @return
     */
    public static String getBaseInfoTenant(String market, String code, ExtAccessToken ext) {
        String weToken = "we_tenant_" + market;
        byte[] weTokenByte;
        try {
            weTokenByte = SerializeUtil.serialize(weToken);
        } catch (Exception e) {
            Logger.getRootLogger().error("1-程序异常===>" + e);
            return "";
        }
        byte[] extByte = RedisUtil.getInstance().strings().get(weTokenByte);
        // redis中无授权信息则刷新
        if (extByte == null) {
            Logger.getRootLogger().info("==准备刷新redis==");
            Logger.getRootLogger().info("==appid=="+ext.getAppid());
            Logger.getRootLogger().info("==appSecret=="+ext.getApp_secret());

            String accessToken = getAccessToken(ext.getAppid(), ext.getApp_secret());

            Logger.getRootLogger().info("==accessToken=="+accessToken);
            if (StringUtils.isBlank(accessToken)) {
                return "";
            }
            Logger.getRootLogger().info("accessToken获取成功==>" + accessToken);
            ext.setAccess_token(accessToken);
            Logger.getRootLogger().info("==开始刷新redis==");
            try {
                Logger.getRootLogger().info("刷新ext: appid===>" + ext.getAppid());
                Logger.getRootLogger().info("刷新ext: appsecret===>" + ext.getApp_secret());
                Logger.getRootLogger().info("刷新ext: accessToken===>" + ext.getAccess_token());
                extByte = SerializeUtil.serialize(ext);
                RedisUtil.getInstance().strings().setEx(weTokenByte, 7000, extByte);
                Logger.getRootLogger().info("==刷新redis完成==");
            } catch (Exception e) {
                Logger.getRootLogger().error("2-程序异常===>" + e);
                return "";
            }
        }

        // 发起GET请求获取凭证
        JSONObject certificate = getBaseInfoByCode(code, ext.getAppid(), ext.getApp_secret());
        if (certificate != null) {
            Logger.getRootLogger().info("获取openId请求Info===>" + certificate.toString());
            String openid = certificate.getString("openid");
            String refresh_token = certificate.getString("refresh_token");
            try {
                ext = (ExtAccessToken) SerializeUtil.unserialize(extByte);
            } catch (Exception e) {
                Logger.getRootLogger().error("反序列化extByte异常===>" + e);
                return "";
            }
            Logger.getRootLogger().info("取出ext: accessToken===>" + ext.getAccess_token());
            Logger.getRootLogger().info("user: openid===>" + openid);
            JSONObject userInfo = getUserInfo(ext.getAccess_token(), openid);
            int count = 0;
            while (userInfo == null) {
                Logger.getRootLogger().info("==重新获取accessToken==");
                String accessToken = getAccessToken(ext.getAppid(), ext.getApp_secret());
                if (StringUtils.isBlank(accessToken)) {
                    return "";
                }
                ext.setAccess_token(accessToken);
                Logger.getRootLogger().info("==开始刷新redis==");
                try {
                    extByte = SerializeUtil.serialize(ext);
                    RedisUtil.getInstance().strings().setEx(weTokenByte, 7000, extByte);
                    Logger.getRootLogger().info("==刷新redis完成==");
                    userInfo = getUserInfo(ext.getAccess_token(), openid);
                    Logger.getRootLogger().info("获取用户的信息为:===>"+userInfo);
                    count++;
                    Logger.getRootLogger().info("获取用户的信息次数为:===>"+count);
                    if (count == 3) {
                        return "";
                    }
                } catch (Exception e) {
                    Logger.getRootLogger().error("2-程序异常===>" + e);
                    return "";
                }
            }
            Logger.getRootLogger().info("获取用户信息Info===>" + userInfo.toString());
            userInfo.put("refresh_token", refresh_token);
            return userInfo.toString();
        }
        return "";
    }

    /**
     * 第三方公众号获取openid 根据code值 基础信息授权版本(消费者版本)
     *
     * @param code
     * @return
     */
    public static String getBaseInfoIcar(String market, String code, ExtAccessToken ext) {
        String weToken = "we_customer_" + market;
        byte[] weTokenByte;
        try {
            weTokenByte = SerializeUtil.serialize(weToken);
        } catch (Exception e) {
            Logger.getRootLogger().error("1-程序异常===>" + e);
            return "";
        }
        // 第三方公众号 access_token刷新
        byte[] extByte = RedisUtil.getInstance().strings().get(weTokenByte);
        // redis中无授权信息则刷新
        if (extByte == null) {
            Logger.getRootLogger().info("==准备刷新redis==");
            String accessToken = getAccessToken(ext.getAppid2(), ext.getApp_secret2());
            if (StringUtils.isBlank(accessToken)) {
                return "";
            }
            Logger.getRootLogger().info("accessToken获取成功==>" + accessToken);
            ext.setAccess_token2(accessToken);
            Logger.getRootLogger().info("==开始刷新redis==");
            try {
                Logger.getRootLogger().info("刷新ext: appid2===>" + ext.getAppid2());
                Logger.getRootLogger().info("刷新ext: appsecret2===>" + ext.getApp_secret2());
                Logger.getRootLogger().info("刷新ext: accessToken2===>" + ext.getAccess_token2());
                extByte = SerializeUtil.serialize(ext);
                RedisUtil.getInstance().strings().setEx(weTokenByte, 7000, extByte);
                Logger.getRootLogger().info("==刷新redis完成==");
            } catch (Exception e) {
                Logger.getRootLogger().error("2-程序异常===>" + e);
                return "";
            }
        }
        // 通过code换取openId请求
        Logger.getRootLogger().info("code===>" + code);
        JSONObject certificate = getBaseInfoByCode(code, ext.getAppid2(), ext.getApp_secret2());
        if (null != certificate) {
            Logger.getRootLogger().info("获取openId请求Info===>" + certificate.toString());
            String openid = certificate.getString("openid");
            String refresh_token = certificate.getString("refresh_token");
            try {
                ext = (ExtAccessToken) SerializeUtil.unserialize(extByte);
            } catch (Exception e) {
                Logger.getRootLogger().error("反序列化extByte异常===>" + e);
                return "";
            }
            Logger.getRootLogger().info("取出ext: accessToken2===>" + ext.getAccess_token2());
            Logger.getRootLogger().info("user: openid===>" + openid);
            JSONObject userInfo = getUserInfo(ext.getAccess_token2(), openid);
            int count = 0;
            while (userInfo == null) {
                Logger.getRootLogger().info("==重新获取accessToken==");
                String accessToken = getAccessToken(ext.getAppid2(), ext.getApp_secret2());
                if (StringUtils.isBlank(accessToken)) {
                    return "";
                }
                ext.setAccess_token2(accessToken);
                Logger.getRootLogger().info("==开始刷新redis==");
                try {
                    extByte = SerializeUtil.serialize(ext);
                    RedisUtil.getInstance().strings().setEx(weTokenByte, 7000, extByte);
                    Logger.getRootLogger().info("==刷新redis完成==");
                    userInfo = getUserInfo(ext.getAccess_token2(), openid);
                    count++;
                    if (count == 3) {
                        return "";
                    }
                } catch (Exception e) {
                    Logger.getRootLogger().error("2-程序异常===>" + e);
                    return "";
                }

            }
            Logger.getRootLogger().info("获取用户信息Info===>" + userInfo.toString());
            userInfo.put("refresh_token", refresh_token);
            return userInfo.toString();
        }
        return "";
    }

    /**
     * 公众号获取用户基础信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public static JSONObject getUserInfo(String accessToken, String openId) {
        String requestUrl = userinfo_url.replace("ACCESSTOKEN", accessToken).replace("OPENID", openId);

        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        Logger.getRootLogger().info("accessToken==>" + accessToken);
        Logger.getRootLogger().info("openId==>" + openId);
        Logger.getRootLogger().info("jsonObject==>" + jsonObject);

        if (null != jsonObject) {
            int count = 0;
            while (!jsonObject.containsKey("subscribe")) {
                Logger.getRootLogger().error("循环： 微信获取基础信息错误==>" + jsonObject.toString());
                Logger.getRootLogger().error("count==>" + count);
                jsonObject = httpRequest(requestUrl, "GET", null);
                count++;
                if (count == 10) {
                    Logger.getRootLogger().error("获取基础信息错误==>" + jsonObject.toString());
                    return null;
                }
            }
            return jsonObject;
        }
        Logger.getRootLogger().error("===请求失败===");
        return null;
    }

    /**
     * 公众号获取用户OpenId及网页授权access_token
     * 用code获取
     *
     * @param code
     * @return
     */
    public static JSONObject getBaseInfoByCode(String code, String appId, String appSecret) {
        String requestUrl = openid_url.replace("APPID", appId).replace("APPSECRET", appSecret).replace("CODE", code);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            if (jsonObject.containsKey("openid")) {
                return jsonObject;
            } else {
                // 获取openId失败
                Logger.getRootLogger().error("获取openId错误==>" + jsonObject.toString());
                return null;
            }
        }
        Logger.getRootLogger().error("===请求失败===");
        return null;
    }

    /**
     * 获取基础AccessToken
     *
     * @param appid
     * @param appSecret
     * @return access_token
     */
    public static String getAccessToken(String appid, String appSecret) {
        Logger.getRootLogger().info(appid+">>>>"+appSecret);
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appSecret);
        String access_token = "";
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        Logger.getRootLogger().info("====>"+jsonObject.toString());

        if (null != jsonObject) {
            if (jsonObject.containsKey("access_token")) {
                access_token = jsonObject.getString("access_token");
                return access_token;
            } else {
                // 获取token失败
                Logger.getRootLogger().error("获取accessToken错误==>" + jsonObject.toString());
                return access_token;
            }
        }
        Logger.getRootLogger().error("===请求失败===");
        return access_token;
    }

    /***
     * 获取jsapiTicket
     * @param accessToken
     * @return ticket
     */
    public static String getJSApiTicket(String accessToken) {
        String requestUrl = jsapi_ticket_url.replace("ACCESSTOKEN", accessToken);
        String ticket = "";
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && jsonObject.getString("errcode").equals("0")) {
                ticket = jsonObject.getString("ticket");
                Logger.getRootLogger().info("获取的jsapiTicketTenant：" + ticket);
                return ticket;
            } else {
                // 获取jsapiTicket失败
                Logger.getRootLogger().info("获取jsapiTicket错误==>" + jsonObject.toString());
                return ticket;
            }
        }
        Logger.getRootLogger().error("===请求失败===");
        return ticket;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            Logger.getRootLogger().error("微信连接超时");
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
        }
        return jsonObject;
    }

    /**
     * 全车通商管家公众号获取openid 根据code值(静默授权方法)
     *
     * @param code 凭证 
     * @return
     */  
   /* public static String getOpenid(String code) {  
        String openid = null;   
        String openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";         
        String requestUrl = openid_url.replace("APPID", TokenThread.appIdTenant).replace("APPSECRET", TokenThread.appSecretTenant).replace("CODE", code);  
        // 发起GET请求获取凭证  
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
  
        if (null != jsonObject) {  
            try {  
                openid=jsonObject.getString("openid");  
            } catch (JSONException e) {  
                openid = null;  
                // 获取openid失败  
                Logger.getRootLogger().error(jsonObject.toString());
            }  
        }  
        return openid;  
    }*/

    /**
     * 全车通Icar管家公众号获取openid 根据code值 静默授权版
     * @param code
     * @return
     */
    /*public static String getOpenid2(String code) {  
        String openid = null;   
        String openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";         
        String requestUrl = openid_url.replace("APPID", TokenThread.appIdIcar).replace("APPSECRET", TokenThread.appSecretIcar).replace("CODE", code);  
        // 发起GET请求获取凭证  
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        if (null != jsonObject) {  
            try {  
                openid=jsonObject.getString("openid");
            } catch (JSONException e) {  
                openid = null;  
                // 获取openid失败  
                Logger.getRootLogger().error(jsonObject.toString());
            }  
        }  
        return openid;  
    }*/

    /**
     * 第三方静默授权版本
     *
     * @param
     * @param
     * @param
     * @return
     */
    /*public static String getOpenid(String code,String ext_appid,String ext_appsecret) {  
        String openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";         
        String requestUrl = openid_url.replace("APPID", ext_appid).replace("APPSECRET", ext_appsecret).replace("CODE", code);  
        // 发起GET请求获取凭证  
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        if (null != jsonObject) {  
            try {  
                return jsonObject.getString("openid");  
            } catch (JSONException e) {  
                // 获取openid失败  
                Logger.getRootLogger().error(jsonObject.toString());
            }  
        }  
        return null;  
    }*/
    public static void main(String[] args) {
        String weToken = "we_tenant_001";

        byte[] weTokenByte = SerializeUtil.serialize(weToken);
        RedisUtil.getInstance().keys().del(weTokenByte);

        String weToken1 = "we_customer_001";

        byte[] weTokenByte1 = SerializeUtil.serialize(weToken1);
        RedisUtil.getInstance().keys().del(weTokenByte1);
//    	System.out.println(RedisUtil.getInstance().strings().get(weTokenByte) == null);
//    	byte[] extByte = RedisUtil.getInstance().strings().get(weTokenByte);
//    	ExtAccessToken ext = (ExtAccessToken) SerializeUtil.unserialize(extByte);
//    	System.out.println(ext.getAccess_token2());
//    	class Man implements Serializable{
//    		public String getName() {
//    			return name;
//    		}
//    		
//    		public void setName(String name) {
//    			this.name = name;
//    		}
//    		
//    		String name;
//    		
//    	}
//    	 Man man = new Man();
//    	 man.setName("lx");
//    	 RedisUtil.getInstance().strings().setEx(SerializeUtil.serialize("123456"), 20, SerializeUtil.serialize(man));
//		 Man man1 = (Man) SerializeUtil.unserialize( RedisUtil.getInstance().strings().get(SerializeUtil.serialize("123456")));
//		 System.out.println(man1);
//    	 RedisUtil.getInstance().strings().set("test1", "212312");
//		 RedisUtil.getInstance().keys().expire("test1", 60);
//		 System.out.println(RedisUtil.getInstance().keys().exists("test1"));
//		 System.out.println(  RedisUtil.getInstance().strings().get("weixin_ceeustomer_token")== null?1:0); 
//		 System.out.println(RedisUtil.getInstance().keys().exists("weixine_customer_token")); 
//		 if(RedisUtil.getInstance().keys().exists("") && null != ""){
//			 System.out.println(1);
//		 }
//    	 System.out.println(RedisUtil.getInstance().keys().exists("weixin_tenant_token"));
//		 RedisUtil.getInstance().keys().del("weixin_tenant_token");
//		 RedisUtil.getInstance().keys().del("weixin_tenant_jsapi");
//		 System.out.println(RedisUtil.getInstance().keys().keys("weixin_*"));
    }

} 
