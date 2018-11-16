package com.maxcar.weixin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信token类
 */
public class AccessToken implements Serializable {
    private String access_token;
    private Integer expires_in;
    private String errcode;
    private String errmsg;
    private Date ApplyTime;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public Date getApplyTime() {
        return ApplyTime;
    }

    public void setApplyTime(Date applyTime) {
        ApplyTime = applyTime;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", ApplyTime=" + ApplyTime +
                '}';
    }
}
