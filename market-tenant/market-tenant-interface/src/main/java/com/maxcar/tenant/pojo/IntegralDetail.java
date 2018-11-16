package com.maxcar.tenant.pojo;

import com.maxcar.base.pojo.PageBean;

import java.io.Serializable;
import java.util.Date;

public class IntegralDetail extends PageBean implements Serializable {
    private String id;

    private String tenantNo;

    private String tenantName;

    private Integer scoresAddSubtract;// 分数加减,1加2减

    private Double checkScore; // 新增分数

    private String checkItems; // 具体事项

    private Date checkTime; //

    private String marketId;

    private String region; // 区域id

    private String checkId;

    private Integer type;

    private Integer baseScore;

    private String status;

    private String remark;

    private Integer isvalid;

    private Date insertTime;

    private String insertOperator;

    private Date updateTime;

    private String updateOperator;

    private String checkTimeString;

    public Integer getScoresAddSubtract() {
        return scoresAddSubtract;
    }

    public void setScoresAddSubtract(Integer scoresAddSubtract) {
        this.scoresAddSubtract = scoresAddSubtract;
    }

    public String getCheckTimeString() {
        return checkTimeString;
    }

    public void setCheckTimeString(String checkTimeString) {
        this.checkTimeString = checkTimeString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTenantNo() {
        return tenantNo;
    }

    public void setTenantNo(String tenantNo) {
        this.tenantNo = tenantNo == null ? null : tenantNo.trim();
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName == null ? null : tenantName.trim();
    }

    public Double getCheckScore() {
        return checkScore;
    }

    public void setCheckScore(Double checkScore) {
        this.checkScore = checkScore;
    }

    public String getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(String checkItems) {
        this.checkItems = checkItems == null ? null : checkItems.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId == null ? null : checkId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(Integer baseScore) {
        this.baseScore = baseScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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