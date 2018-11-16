package com.maxcar.market.pojo;

import java.io.Serializable;
import java.util.Date;

public class EleLabelRecord implements Serializable {
    private String id;

    private String carNumber;

    private String marketId;

    private String vin;

    private String ownedTenant;

    private String oldRfid;

    private String newRfid;

    private Date operationTime;

    private String operator;

    private Integer isValid;

    private Date insertTime;

    private String insertOperator;

    private String remark;

    private String beginDate;

    private String endDate;

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getOwnedTenant() {
        return ownedTenant;
    }

    public void setOwnedTenant(String ownedTenant) {
        this.ownedTenant = ownedTenant == null ? null : ownedTenant.trim();
    }

    public String getOldRfid() {
        return oldRfid;
    }

    public void setOldRfid(String oldRfid) {
        this.oldRfid = oldRfid == null ? null : oldRfid.trim();
    }

    public String getNewRfid() {
        return newRfid;
    }

    public void setNewRfid(String newRfid) {
        this.newRfid = newRfid == null ? null : newRfid.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}