package com.maxcar.weixin.model;

import java.util.List;

/**
 * 自定义菜单--公共按钮类
 */
public class Button {
    private String type;
    private String name;
    private List<Button> sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }
}
