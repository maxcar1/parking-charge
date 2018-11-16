package com.maxcar.base.util.wechat;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 类名: OAuthServlet </br>
 * 描述: 基础授权后的回调请求处理(暂弃用) </br>
 */
public class OAuthServlet extends HttpServlet {
    private static final long serialVersionUID = -1847238807216447030L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 用户同意授权后，能获取到code
        //String code = request.getParameter("code");
        //String state = request.getParameter("state");

        // 用户同意授权
       /* if (!"authdeny".equals(code)) {
            // 获取网页授权access_token  appid  appsercert
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(TokenThread.appId, TokenThread.appSecret, code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
            Logger.getRootLogger().info(snsUserInfo.getHeadImgUrl()+":"+snsUserInfo.getNickname()+":"+snsUserInfo.getOpenId());
            // 设置要传递的参数
            request.setAttribute("snsUserInfo", snsUserInfo);
            request.setAttribute("state", state);
        }*/
        try {
            OutputStream os = response.getOutputStream();
            os.write(request.toString().getBytes("utf-8"));
            os.flush();
            os.close();
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置接收任何请求,解决前端跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        doGet(request, response);
    }
}