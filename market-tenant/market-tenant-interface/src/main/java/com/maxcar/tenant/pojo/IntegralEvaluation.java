package com.maxcar.tenant.pojo;

import com.maxcar.base.pojo.PageBean;

import java.io.Serializable;
import java.util.Date;

public class IntegralEvaluation extends PageBean implements Serializable {
    private String id;

    private String checkItems; // 具体标准

    private Integer scoresAddSubtract;// 1加   2减

    private String scopeUp;

    private String scopeDown;

    private String marketId;

    private Integer status;

    private String remark;

    private Integer isvalid;

    private Date insertTime;

    private String insertOperator;

    private Date updateTime;

    private String updateOperator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(String checkItems) {
        this.checkItems = checkItems == null ? null : checkItems.trim();
    }

    public Integer getScoresAddSubtract() {
        return scoresAddSubtract;
    }

    public void setScoresAddSubtract(Integer scoresAddSubtract) {
        this.scoresAddSubtract = scoresAddSubtract;
    }

    public String getScopeUp() {
        return scopeUp;
    }

    public void setScopeUp(String scopeUp) {
        this.scopeUp = scopeUp == null ? null : scopeUp.trim();
    }

    public String getScopeDown() {
        return scopeDown;
    }

    public void setScopeDown(String scopeDown) {
        this.scopeDown = scopeDown == null ? null : scopeDown.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getInsertOperator() {
        return insertOperator;
    }

    public void setInsertOperator(String insertOperator) {
        this.insertOperator = insertOperator == null ? null : insertOperator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }
}