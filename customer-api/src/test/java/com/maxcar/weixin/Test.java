package com.maxcar.weixin;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.weixin.model.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    private static final String APP_ID = "wxff551e845e12e07c";
    private static final String APP_SECRET = "df64feec406058be81aca5ae3969e68d";
    private static ConcurrentHashMap<String,AccessToken> tokenMap = new ConcurrentHashMap<>();

    private static String access_toen_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static String menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static String upload_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=image";
    private static String add_news_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

    private static String add_other_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

    private static String delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    private static String get_media ="https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";

    private static String getAccessToken(){
        StringBuilder tokenUrl = new StringBuilder(access_toen_url);
        tokenUrl.append("&appid=").append(APP_ID).append("&secret=").append(APP_SECRET);
        String response = HttpClientUtils.sendGet(tokenUrl.toString());
        JSONObject json = JSONObject.parseObject(response);
        AccessToken accessToken = JSONObject.toJavaObject(json, AccessToken.class);
        tokenMap.put("accessToken",accessToken);
        System.out.println(accessToken.getAccess_token());
        return accessToken.getAccess_token();
    }

    private static void createMenu(){
        String aToken = "";
        AccessToken accessToken = tokenMap.get("accessToken");
        if (null == accessToken){
            aToken = getAccessToken();
        }
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
        /*String newUrl = upload_url.replace("ACCESS_TOKEN",aToken);
        News news = new News();
        news.setTitle("玉林派渤及“车派宝”简介");
        news.setContent(getContent());
        news.setDigest();
        news.setContent_source_url("https://mp.weixin.qq.com/s?__biz=MzI4MTk4NDA1Ng==&tempkey=OTc0XzN3RHJHOU5WUGN0YmdGQno1VmpHWno0dy1oOVlXemZ1cVNSUjZCQk9SYmFNd1NHdGNBN2hrRmpBUmUwUUhjMjVBaXhEUVdKVXlfSXQ5RFYzaEhoNnZrWXZveWpzRjM3REJRZ2h5aWpPVFFDbWYzdDIzakdnN0RPSmtCbm1IWjJNZTktcGdPazNrV01yZWs3NnJDeFROOWVRTWdJQk4yU0JhbVlEcUF%2Bfg%3D%3D&chksm=6ba1ac515cd62547bfc8c571c2ae4fad98cead766f2ed6791ca4294ef96db33feb90556c420e#rd");
        String res = HttpClientUtils.sendPost(newUrl,"");
        JSONObject json1 = JSONObject.parseObject(res);*/
    //    b9.setMedia_id("");
        b9.setType("click");
        b9.setKey("123");

        b7.setName("黄金商铺火热招商");
        b7.setUrl("https://e.eqxiu.com/s/NYBVqPeh");
        b7.setType("view");

        b8.setName("车商贷");
        /*String newUrl1 = upload_url.replace("ACCESS_TOKEN",aToken);
        File file1 = new File("C:\\Users\\Administrator\\Desktop\\0.jpg");
        String res1 = HttpClientUtils.sendPost(newUrl1,file1);
        JSONObject json2 = JSONObject.parseObject(res1);
        String url =json2.getString("url");*/
        //http://mmbiz.qpic.cn/mmbiz_jpg/OAgboP3fnPJ15JTMjb5xOnpOU39SxnzZ4z2ictzym8kcTqrSZBNqibty1uM3H1B4fu9Q0uvmeGjT2KAvPSk9zdjg/0
        b8.setKey("1234");
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

    private static void addPic(){
        String aToken = "";
        AccessToken accessToken = tokenMap.get("accessToken");
        if (null == accessToken){
            aToken = getAccessToken();
        }
        String newUrl1 = upload_url.replace("ACCESS_TOKEN",aToken);
        File file1 = new File("C:\\Users\\Administrator\\Desktop\\2.jpg");
        String res1 = HttpClientUtils.sendPost(newUrl1,file1);
        JSONObject json2 = JSONObject.parseObject(res1);
        System.out.println(json2);
//{"media_id":"14Q8F8nn91hZ7NAV5faNNPZyetSvoM9XcuN7nnlTTN8","url":"http://mmbiz.qpic.cn/mmbiz_jpg/OAgboP3fnPJhba6GupdYBibbWUyibMr9LL5cicSSe4DHQrouTfMs07aiceDkf6dbbSCIxTggDyibvpWcmaaBejflPpQ/0?wx_fmt=jpeg"}
        //缩略
        //{"media_id":"14Q8F8nn91hZ7NAV5faNNGiYxXb13UTYbR0iP2st6Cc","url":"http://mmbiz.qpic.cn/mmbiz_jpg/OAgboP3fnPLeH0vxIH1AnnsKgUWyibCuaPfIibZrzibg03Iwj75icRpTaH9jGuSa8QXLVxlDV5hicfJjW3J8QbiaqxlQ/0?wx_fmt=jpeg"}
        //图文
        //{"media_id":"14Q8F8nn91hZ7NAV5faNNGc-2bwQN7oCjZGCA0kobxg","url":"http://mmbiz.qpic.cn/mmbiz_jpg/OAgboP3fnPLeH0vxIH1AnnsKgUWyibCuaFyzzykTe24anBic1oIXu6qWFFKWGyfibrebzibibmX3angPUKfDGRIm7KQ/0?wx_fmt=jpeg"}
    }
    public static void getMedia(){
        String aToken = "";
        AccessToken accessToken = tokenMap.get("accessToken");
        if (null == accessToken){
            aToken = getAccessToken();
        }
        JSONObject json = new JSONObject();
        json.put("media_id","14Q8F8nn91hZ7NAV5faNNPZyetSvoM9XcuN7nnlTTN8");
        String res = HttpClientUtils.sendPost(get_media.replace("ACCESS_TOKEN",aToken),json.toJSONString());

        System.out.println(res);
    }

    public static void main(String[] args){
        //   addPic();
        createMenu();
    }


}
