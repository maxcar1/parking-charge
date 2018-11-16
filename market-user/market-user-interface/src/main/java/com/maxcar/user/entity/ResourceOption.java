package com.maxcar.user.entity;

import java.io.Serializable;
import java.util.Date;

public class ResourceOption implements Serializable{
    private String resourceOptionId;

    private String resourceId;

    private String optionName;

    private Integer optionType;

    private String optionUrl;

    private Integer ind;

    private Integer isvalid;

    private Integer version;

    private Boolean checked;//临时变量，用来判定用户是否已拥有该菜单

    private Date registerTime;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getResourceOptionId() {
        return resourceOptionId;
    }

    public void setResourceOptionId(String resourceOptionId) {
        this.resourceOptionId = resourceOptionId == null ? null : resourceOptionId.trim();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    public Integer getOptionType() {
        return optionType;
    }

    public void setOptionType(Integer optionType) {
        this.optionType = optionType;
    }

    public String getOptionUrl() {
        return optionUrl;
    }

    public void setOptionUrl(String optionUrl) {
        this.optionUrl = optionUrl == null ? null : optionUrl.trim();
    }

    public Integer getInd() {
        return ind;
    }

    public void setInd(Integer ind) {
        this.ind = ind;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}