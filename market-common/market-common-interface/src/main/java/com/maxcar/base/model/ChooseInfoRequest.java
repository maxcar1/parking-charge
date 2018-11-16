package com.maxcar.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * 封装选择层级请求
 */
public class ChooseInfoRequest implements Serializable {
    private String value;
    private String label;
    private List<ChooseInfoRequest> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ChooseInfoRequest> getChildren() {
        return children;
    }

    public void setChildren(List<ChooseInfoRequest> children) {
        this.children = children;
    }
}
