package com.maxcar.base.util.wechat.entity;

import com.maxcar.base.util.wechat.TokenThread;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MenuUtil {
    /**
     * 创建自定义菜单ICar管家(每天限制1000次)
     */
    public static JSONObject createMenu(Menu menu) {
        String jsonMenu = JSONObject.fromObject(menu).toString();
        //创建菜单接口 Icar管家
        String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + TokenThread.accessTokenIcar.getAccess_token();
        try {
            URL url = new URL(path);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.connect();
            OutputStream os = http.getOutputStream();
            os.write(jsonMenu.getBytes("UTF-8"));
            os.close();

            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] bt = new byte[size];
            is.read(bt);
            String message = new String(bt, "UTF-8");
            JSONObject jsonMsg = JSONObject.fromObject(message);
            return jsonMsg;
        } catch (MalformedURLException e) {
            Logger.getRootLogger().error(e, e);
        } catch (IOException e) {
            Logger.getRootLogger().error(e, e);
        }
        return null;
    }

    /**
     * 创建自定义菜单商管家(每天限制1000次)
     *
     * @param menu
     * @return
     */
    public static JSONObject createMenu2(Menu menu) {
        String jsonMenu = JSONObject.fromObject(menu).toString();
        //创建菜单接口 商管家
        String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + TokenThread.accessTokenTenant.getAccess_token();
        try {
            URL url = new URL(path);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.connect();
            OutputStream os = http.getOutputStream();
            os.write(jsonMenu.getBytes("UTF-8"));
            os.close();

            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] bt = new byte[size];
            is.read(bt);
            String message = new String(bt, "UTF-8");
            JSONObject jsonMsg = JSONObject.fromObject(message);
            return jsonMsg;
        } catch (MalformedURLException e) {
            Logger.getRootLogger().error(e, e);
        } catch (IOException e) {
            Logger.getRootLogger().error(e, e);
        }
        return null;
    }

    /**
     * 删除自定义菜单
     */
    public static void deleteMenu(Menu menu) {
        String jsonMenu = JSONObject.fromObject(menu).toString();
        //删除菜单接口
        String path = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + TokenThread.accessTokenIcar.getAccess_token();
        try {
            JSONObject json = WeixinUtil.httpRequest(path, "post", jsonMenu);
            Logger.getRootLogger().info(json.toString());
            Logger.getRootLogger().info(json.getInt("errcode") + ":" + json.getString("errmsg"));
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
        }
    }

    /**
     * 封装菜单数据(Icar)
     *
     * @return
     */
    public static Menu getMenu() {
        //现在为了测试 state的值 暂设market为337的市场
        //地图测试
        String dtcs = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdIcar + "&redirect_uri=http://" + TokenThread.URL + "/weixin_customer/template/map/fmMap.html?response_type=code&scope=snsapi_base#wechat_redirect";
        //我要找车
        String wyzc = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdIcar + "&redirect_uri=http://" + TokenThread.URL + "/weixin_customer/template/index/carIndex.html?response_type=code&scope=snsapi_base#wechat_redirect";
        //String ccjl="http://"+TokenThread.URL+"/weixin/template/outrecord/outrecordindex.html";
        //车险服务
        String cxfw = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdIcar + "&redirect_uri=http://" + TokenThread.URL + "/weixin_customer/template/insuranceOrder/orderIndex.html?response_type=code&scope=snsapi_base#wechat_redirect";

        //测试1
        ViewButton vb = new ViewButton();
        vb.setName("交易须知");
        vb.setType("view");
        vb.setUrl(dtcs);
        ComplexButton cx_1 = new ComplexButton();
        cx_1.setName("交易须知");
        cx_1.setSub_button(new Button[]{vb});
        //测试2
        ViewButton vb_2 = new ViewButton();
        vb_2.setName("我要找车");
        vb_2.setType("view");
        vb_2.setUrl(wyzc);
        ComplexButton cx_2 = new ComplexButton();
        cx_2.setName("我要找车");
        cx_2.setSub_button(new Button[]{vb_2});
        //我的服务
        ViewButton vb_3 = new ViewButton();
        vb_3.setName("车险服务");
        vb_3.setType("view");
        vb_3.setUrl(cxfw);
        ComplexButton cx_3 = new ComplexButton();
        cx_3.setName("我的服务");
        cx_3.setSub_button(new Button[]{vb_3});
        //创建三个菜单数据
        Menu menu = new Menu();
        menu.setButton(new ComplexButton[]{cx_1, cx_2, cx_3});
        return menu;
    }

    /**
     * 封装菜单数据(商管家)
     */
    public static Menu getMenu2() {
        //出场记录
        String ccjl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdTenant + "&redirect_uri=http://" + TokenThread.URL + "/weixin/maxcar/fragment/monitors.html?response_type=code&scope=snsapi_userinfo&connect_redirect=1#wechat_redirect";
        //String ccjl="http://"+TokenThread.URL+"/weixin/template/outrecord/outrecordindex.html";
        //出场申请
        String ccsq = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdTenant + "&redirect_uri=http://" + TokenThread.URL + "/weixin/maxcar/fragment/exits.html?response_type=code&scope=snsapi_userinfo&connect_redirect=1#wechat_redirect";
        //String ccsq="http://"+TokenThread.URL+"/weixin/template/applyout/applyoutindex.html";
        //商品车辆
        String spcl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdTenant + "&redirect_uri=http://" + TokenThread.URL + "/weixin/maxcar/fragment/products.html?response_type=code&scope=snsapi_userinfo&connect_redirect=1#wechat_redirect";
        //String cllb="http://"+TokenThread.URL+"/weixin/template/carManager/carManageindex.html+";
        //新增车辆
        String xzcl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdTenant + "&redirect_uri=http://" + TokenThread.URL + "/weixin/maxcar/fragment/productAddCode.html?response_type=code&scope=snsapi_userinfo&connect_redirect=1#wechat_redirect";
        //员工管理
        String yggl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdTenant + "&redirect_uri=http://" + TokenThread.URL + "/weixin/maxcar/fragment/members.html?response_type=code&scope=snsapi_userinfo&connect_redirect=1#wechat_redirect";
        //个人信息
        String grxx = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + TokenThread.appIdTenant + "&redirect_uri=http://" + TokenThread.URL + "/weixin/maxcar/fragment/userInfo.html?response_type=code&scope=snsapi_userinfo&connect_redirect=1#wechat_redirect";
        //String yggl="http://"+TokenThread.URL+"/weixin/template/personManager/personManager.html";
        //资讯菜单
        ViewButton vb = new ViewButton();
        vb.setName("出场申请");
        vb.setType("view");
        vb.setUrl(ccsq);
        ViewButton vb_1 = new ViewButton();
        vb_1.setName("出场记录");
        vb_1.setType("view");
        vb_1.setUrl(ccjl);
        ComplexButton cx_1 = new ComplexButton();
        cx_1.setName("出场");
        cx_1.setSub_button(new Button[]{vb, vb_1});

        //车辆服务
        ViewButton vb_2 = new ViewButton();
        vb_2.setName("商品车辆");
        vb_2.setType("view");
        vb_2.setUrl(spcl);
        ViewButton vb_2_2 = new ViewButton();
        vb_2_2.setName("新增车辆");
        vb_2_2.setType("view");
        vb_2_2.setUrl(xzcl);
        ComplexButton cx_2 = new ComplexButton();
        cx_2.setName("车辆");
        cx_2.setSub_button(new Button[]{vb_2, vb_2_2});
        //服务
        ViewButton vb_3 = new ViewButton();
        vb_3.setName("员工管理");
        vb_3.setType("view");
        vb_3.setUrl(yggl);
        ViewButton vb_4 = new ViewButton();
        vb_4.setName("个人信息");
        vb_4.setType("view");
        vb_4.setUrl(grxx);
        ComplexButton cx_3 = new ComplexButton();
        cx_3.setName("我的");
        cx_3.setSub_button(new Button[]{vb_4, vb_3});
        //创建三个菜单数据
        Menu menu = new Menu();
        menu.setButton(new ComplexButton[]{cx_1, cx_2, cx_3});

        return menu;
    }
}
