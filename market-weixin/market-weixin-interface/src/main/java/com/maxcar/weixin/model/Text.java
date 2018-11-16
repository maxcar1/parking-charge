package com.maxcar.weixin.model;

import java.io.Serializable;
/**
 * yangshujun
 * 客服消息推送实体
 */
public class Text implements Serializable {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
