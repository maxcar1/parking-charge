package com.maxcar.base.util.wechat;

import com.maxcar.base.util.wechat.entity.WeixinUserInfo;
import com.maxcar.base.util.wechat.entity.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台获取微信关注用户列表及其信息
 *
 * @author ldc
 */
public class WeChatUserInfoService {
    /**
     * 根据open_id获取用户信息
     *
     * @param accessToken 接口访问凭证
     * @param openId      用户标识
     * @return WeixinUserInfo
     */
    public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
        WeixinUserInfo weixinUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    Logger.getRootLogger().error("用户已取消关注：" + weixinUserInfo.getOpenId());
                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    Logger.getRootLogger().error("获取用户信息失败 errcode:" + errorCode + " errmsg:" + errorMsg);
                }
            }
        }
        return weixinUserInfo;
    }

    /**
     * 后台获取微信公众号的所有关注者的open_id
     * nextopenid 第一次获取的时候传空就行 默认拉取10000个 后续再传入拉取之后的
     *
     * @param access_token
     * @param nextopenid
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> getUserList(String access_token, String nextopenid) {
        List<String> users = new ArrayList<String>();
        Integer total = 0;//关注该公众账号的总用户数
        Integer count = 0;//拉取的OPENID个数，最大值为10000
        String next_openid = "";//拉取列表的最后一个用户的OPENID
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token).replace("NEXT_OPENID", nextopenid);
        // 获取用户列表
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                total = jsonObject.getInt("total");
                count = jsonObject.getInt("count");
                if (count > 0) {
                    next_openid = jsonObject.getJSONObject("data").getString("next_openid");
                    Logger.getRootLogger().info("总关注数:" + total + " 此次拉取用户数:" + count + " 最后一个open_id:" + next_openid);
                    JSONArray arr = jsonObject.getJSONObject("data").getJSONArray("openid");
                    users = JSONArray.toList(arr, new String(), new JsonConfig());
                } else {
                    Logger.getRootLogger().info("用户数为空!");
                }

            } catch (Exception e) {
                Logger.getRootLogger().error(jsonObject.toString());
                Logger.getRootLogger().error(e, e);
            }
        }
        return users;
    }
}