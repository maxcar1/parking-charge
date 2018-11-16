package com.maxcar.user.entity;

import java.io.Serializable;
import java.util.Date;

public class OutMarketManage implements Serializable {
    private String id;

    private String marketId;

    private String outMarketType; // 控制的类型,只有当为控制的时候  允许出场时间才有意义

    private Double outMarketTime; // 允许出场的时间

    private String outMarketApproval; // 出场审批预留

    private String remarks;

    private Integer isvalid;

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
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getOutMarketType() {
        return outMarketType;
    }

    public void setOutMarketType(String outMarketType) {
        this.outMarketType = outMarketType == null ? null : outMarketType.trim();
    }

    public Double getOutMarketTime() {
        return outMarketTime;
    }

    public void setOutMarketTime(Double outMarketTime) {
        this.outMarketTime = outMarketTime;
    }

    public String getOutMarketApproval() {
        return outMarketApproval;
    }

    public void setOutMarketApproval(String outMarketApproval) {
        this.outMarketApproval = outMarketApproval == null ? null : outMarketApproval.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
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