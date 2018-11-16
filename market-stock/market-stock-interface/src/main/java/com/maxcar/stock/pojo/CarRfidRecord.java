package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.Date;

public class CarRfidRecord implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String newRfid;

    private String oldRfid;

    private String newEquipid;

    private String oldEquipid;

    private Integer type;

    private Integer action;

    private String car;

    private String vin;

    private Date registerTime;

    private String operator;

    private String reason;

    private Integer version;

    private Date insertTime;

    private Date updateTime;

    private String market;

    private String staffId;//员工Id

    private String tenant;//商户id

    private Integer stockStatus;//库存状态 -1 删除 1:在场 2:在内场 3:出场 4:售出未出场 5 售出已出场

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNewRfid() {
        return newRfid;
    }

    public void setNewRfid(String newRfid) {
        this.newRfid = newRfid == null ? null : newRfid.trim();
    }

    public String getOldRfid() {
        return oldRfid;
    }

    public void setOldRfid(String oldRfid) {
        this.oldRfid = oldRfid == null ? null : oldRfid.trim();
    }

    public String getNewEquipid() {
        return newEquipid;
    }

    public void setNewEquipid(String newEquipid) {
        this.newEquipid = newEquipid == null ? null : newEquipid.trim();
    }

    public String getOldEquipid() {
        return oldEquipid;
    }

    public void setOldEquipid(String oldEquipid) {
        this.oldEquipid = oldEquipid == null ? null : oldEquipid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car == null ? null : car.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
}