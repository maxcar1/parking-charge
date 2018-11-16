package com.maxcar.base.util.wechat;

import com.maxcar.base.util.wechat.entity.AccessToken;
import com.maxcar.base.util.wechat.entity.ExtAccessToken;
import com.maxcar.base.util.wechat.entity.WeixinUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;


public class TokenThread implements Runnable {
    /**
     * 获取基础授权的 access_token
     * 定时版本
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    private final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //Icar管家微信
    public static String appIdIcar = "wx035d31d28ad83427";
    public static String appSecretIcar = "f02e9f87742db10b46baecd56548713d";
    //商管家微信
    public static String appIdTenant = "wx66cc7db87a6bb651";
    public static String appSecretTenant = "c69c16d24c683f0d786abdde7912a4b6";
    //accessToken
    public static AccessToken accessTokenIcar = null;//iCar管家
    public static AccessToken accessTokenTenant = null;//商管家
    //jsapi_ticket_tenant
    public static String jsapi_ticket_tenant = null;
    //jsapi_ticket_icar
    public static String jsapi_ticket_icar = null;

    //第三方公众号相关存储信息
    public static HashMap<String, ExtAccessToken> extAaccessToken = new HashMap<String, ExtAccessToken>();


    //域名
    //public static String URL = "116.62.20.142";
    public static String URL = "www.lets-top.com";

    //public static String URL2 = "www.maxcar.com.cn";
    public void run() {
//    	refreshToken();
    }

    /**获取token及对应的jsapi_ticket
     *
     */
    /*public static void refreshToken(){
        try {
			accessTokenIcar = getAccessToken(appIdIcar,appSecretIcar);
			accessTokenTenant = getAccessToken(appIdTenant,appSecretTenant);
			//获取完商管家token之后 获取 jsapi_ticket
			if(null != accessTokenTenant.getAccess_token()){
				jsapi_ticket_tenant = getJSApiTicket(accessTokenTenant.getAccess_token());
			}
			if(null != accessTokenIcar.getAccess_token()){
				jsapi_ticket_icar = getJSApiTicket2(accessTokenIcar.getAccess_token());
			}
			//refreshExtToken();
    		//第三方公众号数据刷新
			refreshExtToken();
		} catch (Exception e) {
			Logger.getRootLogger().info("发生异常：" + e.getMessage());
			Logger.getRootLogger().error(e, e);
		}
    }*/

    /**
     * 第三方公众号 access_token服务以及jsapi数据刷新
     */
    /*public static void refreshExtToken(){
    	//定时任务的循环 map
		Iterator<Map.Entry<String, ExtAccessToken>> iterator = extAaccessToken.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ExtAccessToken> entry = iterator.next();
			Logger.getRootLogger().info("刷新时市场id-->"+entry.getKey());
			//根据appid 和 appsecret 获取token
			if(null != entry.getValue().getAppid() && !("".equals(entry.getValue().getAppid()))){
				entry.getValue().setAccess_token(getAccessTokenStr(entry.getValue().getAppid(),entry.getValue().getApp_secret()));
				//根据access_token获取jsapi
				if(null != entry.getValue().getAccess_token() && !("".equals(entry.getValue().getAccess_token()))){
				entry.getValue().setJsapi_ticket(getJSApiTicket(entry.getValue().getAccess_token()));
				}
				Logger.getRootLogger().info("商户版本：access_token->"+entry.getValue().getAccess_token()+" jsapi->"+entry.getValue().getJsapi_ticket());
			}
			
			//根据appid 和 appsecret 获取token			
			if(null != entry.getValue().getAppid2() && !("".equals(entry.getValue().getAppid2()))){
				entry.getValue().setAccess_token2(getAccessTokenStr(entry.getValue().getAppid2(),entry.getValue().getApp_secret2()));
				//根据access_token获取jsapi
				if(null != entry.getValue().getAccess_token2() && !("".equals(entry.getValue().getAccess_token2()))){
				entry.getValue().setJsapi_ticket2(getJSApiTicket(entry.getValue().getAccess_token2()));
				}
				Logger.getRootLogger().info("消费者版：access_token->"+entry.getValue().getAccess_token2()+" jsapi->"+entry.getValue().getJsapi_ticket2());
			}
			
		}
    }*/
    @SuppressWarnings("unchecked")
    public static void refeshMarket() {/*
    	//获取市场里面填写的 appid 和  appsercet 进行自动授权 
		try {
			Object ServiceBean = InventoryService.serviceBean("userMarketDao");
			Method method = MethodUtils.getMatchingAccessibleMethod(
				   ServiceBean.getClass(), "getAll",new Class[] {UserMarket.class });
			UserMarket market = new UserMarket();
			//暂不考虑延迟太高和市场数超过100个
			market.setCurPage(0);
			market.setPageSize(100);
			List<UserMarket> list = (List<UserMarket>)method.invoke(ServiceBean, market);
			for(UserMarket m:list){
				extAccessToken ext = null;
				if(null != m.getAppId() && !("".equals(m.getAppId()))){
					ext = new extAccessToken();
					ext.setAppid(m.getAppId());
				}
				if(null != m.getAppId2() && !("".equals(m.getAppId2()))){
					if(null == ext){
						ext = new extAccessToken();
					}
					else{
						ext.setAppid2(m.getAppId2());
					}
				}
				if(null != m.getAppSecret() && !("".equals(m.getAppSecret()))){
					if(null == ext){
						ext = new extAccessToken();
					}
					else{
						ext.setApp_secret(m.getAppSecret());
					}
				}
				if(null != m.getAppSecret2() && !("".equals(m.getAppSecret2()))){
					if(null == ext){
						ext = new extAccessToken();
					}
					else{
						ext.setApp_secret2(m.getAppSecret2());
					}
				}
				if(null != ext){
					extAaccessToken.put(m.getId(), ext);
				}
			}
		}catch (Exception e) {
			Logger.getRootLogger().error(e,e);
		}
    */
    }

    /**
     * 获取access_token
     *
     * @return
     */
    private static AccessToken getAccessToken(String appid1, String appSecret1) {
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appid1).replace("APPSECRET", appSecret1);
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败  
                Logger.getRootLogger().error("获取token失败 errcode:" + jsonObject.getInt("errcode") + "：errmsg:" + jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    /**
     * 单独获取AccessToken字符串
     *
     * @param appid1
     * @param appSecret1
     * @return
     */
    public static String getAccessTokenStr(String appid1, String appSecret1) {
        String requestUrl = access_token_url.replace("APPID", appid1).replace("APPSECRET", appSecret1);
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                return jsonObject.getString("access_token");
            } catch (JSONException e) {
                // 获取token失败  
                Logger.getRootLogger().error("获取access_token失败 errcode:" + jsonObject.getInt("errcode") + "：errmsg:" + jsonObject.getString("errmsg"));
            }
        }
        return "";
    }

    /***
     * 获取jsapiTicket 商管家
     * @return
     */
    public static String getJSApiTicket(String accessToken) {
        String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        JSONObject jsonObject = WeixinUtil.httpRequest(urlStr, "GET", null);
        String ticket = "";
        if (jsonObject.getString("errcode").equals("0")) {
            ticket = jsonObject.getString("ticket");
            Logger.getRootLogger().info("获取的jsapiTicketTenant：" + ticket);
            return ticket;
        }
        return ticket;
    }

    /***
     * 获取jsapiTicket icar管家
     * @return
     */
    public static String getJSApiTicket2(String accessToken) {
        String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        JSONObject jsonObject = WeixinUtil.httpRequest(urlStr, "GET", null);
        String ticket = "";
        if (jsonObject.getString("errcode").equals("0")) {
            ticket = jsonObject.getString("ticket");
            Logger.getRootLogger().info("获取的jsapiTicketIcar：" + ticket);
            return ticket;
        }
        return ticket;

    }

}