package com.maxcar.base.model;

import java.io.Serializable;
import java.util.List;

public class VehicleBrand implements Serializable {

    private String name;

    private String code;

    private String logoUrl;

    private List<VehicleBrand> children;

    public List<VehicleBrand> getChildren() {
        return children;
    }

    public void setChildren(List<VehicleBrand> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
