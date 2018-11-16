package com.maxcar.market.pojo;

import java.io.Serializable;
import java.util.Date;

public class ParkingFeeTotal implements Serializable {
    private String id;

    private String marketId;

    private Integer carType;

    private Integer freeTime;

    private Integer isFree;

    private Integer beyondTime;

    private Integer beyondFee;

    private Integer isBeyond;

    private Integer feeLimit;

    private Integer isLimit;

    private Integer isValid;

    private Date insertTime;

    private String insertOperator;

    private Date updateTime;

    private String updateOperator;

    private String remark;

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

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Integer freeTime) {
        this.freeTime = freeTime;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getBeyondTime() {
        return beyondTime;
    }

    public void setBeyondTime(Integer beyondTime) {
        this.beyondTime = beyondTime;
    }

    public Integer getBeyondFee() {
        return beyondFee;
    }

    public void setBeyondFee(Integer beyondFee) {
        this.beyondFee = beyondFee;
    }

    public Integer getIsBeyond() {
        return isBeyond;
    }

    public void setIsBeyond(Integer isBeyond) {
        this.isBeyond = isBeyond;
    }

    public Integer getFeeLimit() {
        return feeLimit;
    }

    public void setFeeLimit(Integer feeLimit) {
        this.feeLimit = feeLimit;
    }

    public Integer getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(Integer isLimit) {
        this.isLimit = isLimit;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}