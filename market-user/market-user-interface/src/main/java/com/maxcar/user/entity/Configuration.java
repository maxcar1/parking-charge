package com.maxcar.user.entity;

import java.io.Serializable;
import java.util.Date;

public class Configuration implements Serializable{
    private String configurationId;

    private String marketId;

    private String marketName;

    private String merantsId;

    private String merantsName;

    private String configurationName;

    private String configurationKey;

    private String configurationValue;

    private String configurationDesc;

    private Integer isvalid;

    private Integer version;

    private Date registerTime;

    private String unit;

    public Short getManagerFlag() {
        return managerFlag;
    }

    public void setManagerFlag(Short managerFlag) {
        this.managerFlag = managerFlag;
    }

    private Short managerFlag;//临时，用户是否为管理员属性

    public String getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId == null ? null : configurationId.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName == null ? null : marketName.trim();
    }

    public String getMerantsId() {
        return merantsId;
    }

    public void setMerantsId(String merantsId) {
        this.merantsId = merantsId == null ? null : merantsId.trim();
    }

    public String getMerantsName() {
        return merantsName;
    }

    public void setMerantsName(String merantsName) {
        this.merantsName = merantsName == null ? null : merantsName.trim();
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName == null ? null : configurationName.trim();
    }

    public String getConfigurationKey() {
        return configurationKey;
    }

    public void setConfigurationKey(String configurationKey) {
        this.configurationKey = configurationKey == null ? null : configurationKey.trim();
    }

    public String getConfigurationValue() {
        return configurationValue;
    }

    public void setConfigurationValue(String configurationValue) {
        this.configurationValue = configurationValue == null ? null : configurationValue.trim();
    }

    public String getConfigurationDesc() {
        return configurationDesc;
    }

    public void setConfigurationDesc(String configurationDesc) {
        this.configurationDesc = configurationDesc == null ? null : configurationDesc.trim();
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
}