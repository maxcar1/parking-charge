package com.maxcar.weixin.model;

/**
 * 自定义菜单--view类型
 */
public class ViewButton extends Button{
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
