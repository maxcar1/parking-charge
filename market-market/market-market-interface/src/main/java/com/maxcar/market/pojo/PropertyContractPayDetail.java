package com.maxcar.market.pojo;

import java.util.Date;

public class PropertyContractPayDetail {
    private String id;

    private String marketId;

    private String propertyContractId;

    private String propertyContractDetail;

    private Date payTime;

    private Integer status;

    private Double payPrice;

    private Integer isvalid;

    private String remark;

    private Date insertTime;

    private Date updateTime;

    private String insertOperator;

    private String updateOperator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getPropertyContractId() {
        return propertyContractId;
    }

    public void setPropertyContractId(String propertyContractId) {
        this.propertyContractId = propertyContractId == null ? null : propertyContractId.trim();
    }

    public String getPropertyContractDetail() {
        return propertyContractDetail;
    }

    public void setPropertyContractDetail(String propertyContractDetail) {
        this.propertyContractDetail = propertyContractDetail == null ? null : propertyContractDetail.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getInsertOperator() {
        return insertOperator;
    }

    public void setInsertOperator(String insertOperator) {
        this.insertOperator = insertOperator == null ? null : insertOperator.trim();
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }
}