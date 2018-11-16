package com.maxcar.base.util.wechat.entity;

import java.io.Serializable;

public class ExtAccessToken implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //商管家用
    private String access_token;
    private String jsapi_ticket;
    private String appid;
    private String app_secret;
    //导购用
    private String access_token2;
    private String jsapi_ticket2;
    private String appid2;
    private String app_secret2;

    public ExtAccessToken() {
    }

    public ExtAccessToken(String appid, String app_secret) {
        super();
        this.appid = appid;
        this.app_secret = app_secret;
    }

    public ExtAccessToken(String appid2, String app_secret2, String access_token2) {
        super();
        this.appid2 = appid2;
        this.app_secret2 = app_secret2;
        this.access_token2 = access_token2;
    }

    public ExtAccessToken(String access_token, String jsapi_ticket,
                          String appid, String app_secret, String access_token2,
                          String jsapi_ticket2, String appid2, String app_secret2) {
        super();
        this.access_token = access_token;
        this.jsapi_ticket = jsapi_ticket;
        this.appid = appid;
        this.app_secret = app_secret;
        this.access_token2 = access_token2;
        this.jsapi_ticket2 = jsapi_ticket2;
        this.appid2 = appid2;
        this.app_secret2 = app_secret2;
    }

    public String getAccess_token2() {
        return access_token2;
    }

    public void setAccess_token2(String access_token2) {
        this.access_token2 = access_token2;
    }

    public String getJsapi_ticket2() {
        return jsapi_ticket2;
    }

    public void setJsapi_ticket2(String jsapi_ticket2) {
        this.jsapi_ticket2 = jsapi_ticket2;
    }

    public String getAppid2() {
        return appid2;
    }

    public void setAppid2(String appid2) {
        this.appid2 = appid2;
    }

    public String getApp_secret2() {
        return app_secret2;
    }

    public void setApp_secret2(String app_secret2) {
        this.app_secret2 = app_secret2;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getJsapi_ticket() {
        return jsapi_ticket;
    }

    public void setJsapi_ticket(String jsapi_ticket) {
        this.jsapi_ticket = jsapi_ticket;
    }


}
