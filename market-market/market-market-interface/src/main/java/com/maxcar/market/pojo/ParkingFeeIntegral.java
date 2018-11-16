package com.maxcar.market.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ParkingFeeIntegral implements Serializable {
    private String id;

    private String parkingFeeDetailId;

    @NotNull(message = "卡号不能为null")
    private String cardNo;

    @NotNull(message = "消费者不能为null")
    private String openid;

    @Min(value = 0,message = "参数有误")
    private Integer price;

    @Min(value = 0,message = "参数有误")
    private Integer integral;

    private int status;// 支付状态

    private Integer isValid;

    private String remark;

    private Date insertTime;

    private Date updateTime;

    private String payTime;

    private String insertOperator;

    private String updateOperator;

    public String getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParkingFeeDetailId() {
        return parkingFeeDetailId;
    }

    public void setParkingFeeDetailId(String parkingFeeDetailId) {
        this.parkingFeeDetailId = parkingFeeDetailId == null ? null : parkingFeeDetailId.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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