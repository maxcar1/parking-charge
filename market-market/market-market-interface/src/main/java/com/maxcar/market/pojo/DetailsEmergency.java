package com.maxcar.market.pojo;

import java.io.Serializable;
import java.util.Date;

public class DetailsEmergency implements Serializable {
    private String id;

    private String barrierPosition;

    private String cardNo;

    private Date beforeTime;

    private Date afterTime;

    private String stopTime;//停车时长

    private String staffName;

    private String reason;

    private String marketId;

    private String employeesId;

    private String barrierId;

    private String parkingDetailId;

    public String getParkingDetailId() {
        return parkingDetailId;
    }

    public void setParkingDetailId(String parkingDetailId) {
        this.parkingDetailId = parkingDetailId;
    }

    public String getBarrierId() {
        return barrierId;
    }

    public void setBarrierId(String barrierId) {
        this.barrierId = barrierId;
    }

    public String getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(String employeesId) {
        this.employeesId = employeesId;
    }

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
        this.id = id;
    }

    public String getBarrierPosition() {
        return barrierPosition;
    }

    public void setBarrierPosition(String barrierPosition) {
        this.barrierPosition = barrierPosition;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Date getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(Date beforeTime) {
        this.beforeTime = beforeTime;
    }

    public Date getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(Date afterTime) {
        this.afterTime = afterTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
