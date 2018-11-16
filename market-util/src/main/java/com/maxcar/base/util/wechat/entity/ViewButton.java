package com.maxcar.base.util.wechat.entity;

/**
 * view类型的菜单对象
 */
public class ViewButton extends Button {
    private String type;        //菜单类型
    private String url;            //view路径值

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
