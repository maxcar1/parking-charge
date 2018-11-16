package com.maxcar.weixin.model;

import java.io.Serializable;

/**
 * yangshujun
 * 客服消息推送实体
 */
public class TargetMessage implements Serializable {
    private String touser;
    private String msgtype;
    private Text text;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
