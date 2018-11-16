package com.maxcar.tool;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.weixin.model.*;

import java.util.ArrayList;
import java.util.List;
/**
 * 玉林派勃公众号自定义菜单
 * yangsj
 */
public class PaiBoMenu {

    private static final String APP_ID = "wx880aa7250a756969";
    private static final String APP_SECRET = "f0103f4f490096af95ecb766a27b3077";

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
     * 派勃自定义菜单
     */
    private static void createMenu(){
        String aToken = getAccessToken();

        StringBuilder sb1 = new StringBuilder(delete_url.replace("ACCESS_TOKEN",aToken));
        String res = HttpClientUtils.sendGet(sb1.toString());
        System.out.println(res);
        StringBuilder sb = new StringBuilder(menu_url.replace("ACCESS_TOKEN",aToken));
        ViewButton b1 = new ViewButton();
        ViewButton b2 = new ViewButton();
        ViewButton b3 = new ViewButton();
        ViewButton b4 = new ViewButton();
        ViewButton b5 = new ViewButton();
        ViewButton b6 = new ViewButton();
        ViewButton b7 = new ViewButton();
        ClickButton b8 = new ClickButton();
        ClickButton b9 = new ClickButton();

        b9.setName("关于我们");
        b9.setType("click");
        b9.setKey("gywm");

        b7.setName("黄金商铺火热招商");
        b7.setUrl("https://e.eqxiu.com/s/NYBVqPeh");
        b7.setType("view");

        b8.setName("车商贷");
        b8.setKey("csd");
        b8.setType("click");

        b3.setName("招商优惠");
        b3.setType("view");
        List<Button> buttons = new ArrayList<>(2);
        buttons.add(b7);
        buttons.add(b8);
        b3.setSub_button(buttons);

        b2.setName("购车送油卡ing");
        b2.setUrl("https://mp.weixin.qq.com/s?__biz=MzI4MTk4NDA1Ng==&mid=2247483753&idx=1&sn=3a896efdf5dc720ed7397cf37ac79c2c&chksm=eba1ac3edcd625280ac68520de01353bf7fceb51e095e4abf4e2882e86af9a703ce45dd8bad8&scene=18#rd");
        b2.setType("view");

        b4.setName("车商评选活动");
        b4.setUrl("http://mp.weixin.qq.com/s?__biz=MzI4MTk4NDA1Ng==&mid=2247483692&idx=1&sn=740aaf98247bf3c6bf96cdcfb054b1e2&chksm=eba1ac7bdcd6256d943aba2319999868ef456f1bcbadfa6eb454c29e67b8beb96cedc1c63dd3&scene=18#wechat_redirect");
        b4.setType("view");

        b5.setName("年会特辑");
        b5.setUrl("http://mp.weixin.qq.com/s?__biz=MzI4MTk4NDA1Ng==&mid=2247483715&idx=1&sn=d275f0326a0dffeb350b0b7c932b669d&chksm=eba1ac14dcd6250238297b4a5178983d52531e90a42703152811ded38ff8b70901453651331b&scene=18#wechat_redirect");
        b5.setType("view");

        b6.setName("汽车新城");
        b6.setUrl("http://www.maxcar.com.cn/vux-customer/?market=010&customer=1#/");
        b6.setType("view");


        b1.setName("最新活动");
        b1.setType("view");
        List<Button> buttons1 = new ArrayList<>(4);
        buttons1.add(b2);
        buttons1.add(b4);
        buttons1.add(b5);
        buttons1.add(b6);
        b1.setSub_button(buttons1);
        Menu menu = new Menu();
        List<Button> buttonList = new ArrayList<>(3);
        buttonList.add(b1);
        buttonList.add(b3);
        buttonList.add(b9);
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
