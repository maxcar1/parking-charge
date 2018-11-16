package com.maxcar.tool;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.weixin.model.*;

import java.util.ArrayList;
import java.util.List;

public class ICarMenu {

    private static final String APP_ID = "wx035d31d28ad83427";
    private static final String APP_SECRET = "7aa135a141333deffb1ea99a634d1ce5";

    private static String access_toen_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static String menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static String delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    private static String getAccessToken(){
        StringBuilder tokenUrl = new StringBuilder(access_toen_url);
        tokenUrl.append("&appid=").append(APP_ID).append("&secret=").append(APP_SECRET);
        String response = HttpClientUtils.sendGet(tokenUrl.toString());
        JSONObject json = JSONObject.parseObject(response);
        AccessToken accessToken = JSONObject.toJavaObject(json, AccessToken.class);
        System.out.println(accessToken.getAccess_token());
        return accessToken.getAccess_token();
    }

    /**
     * icar自定义菜单
     */
    private static void createMenu(){
        String aToken = getAccessToken();

        StringBuilder sb1 = new StringBuilder(delete_url.replace("ACCESS_TOKEN",aToken));
        String res = HttpClientUtils.sendGet(sb1.toString());
        System.out.println(res);
        StringBuilder sb = new StringBuilder(menu_url.replace("ACCESS_TOKEN",aToken));
        ViewButton b1 = new ViewButton();
        ViewButton b2 = new ViewButton();
        b1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx035d31d28ad83427&redirect_uri=http://xfz-t.maxcar.com.cn?market=010&v=0.1&response_type=code&scope=snsapi_userinfo&state=010#wechat_redirect");
        b1.setType("view");
        b1.setName("商品车列表");
        b2.setName("关于我们");
        b2.setType("view");
        b2.setUrl("http://www.maxcar.com.cn");
        Menu menu = new Menu();
        List<Button> buttonList = new ArrayList<>(2);
        buttonList.add(b1);
        buttonList.add(b2);
        menu.setButton(buttonList);
        JSONObject json = (JSONObject) JSONObject.toJSON(menu);
        System.out.println(json.toJSONString());
        String response = HttpClientUtils.sendPost(sb.toString(),json.toJSONString());
        System.out.println(response);
    }

    public static void main(String[] args) {
        createMenu();
    }
}
