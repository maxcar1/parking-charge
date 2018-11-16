package com.maxcar.stock.entity;

import java.io.Serializable;

/**
 * 接受录车app首页传入参数
 */
public class CarChecks implements Serializable {
    private static final long serialVersionUID = 1L;
    private String market;
    private String tenant;
    private Integer contract_num;
    private Integer shop_num;
    private Integer car_num;
    private Integer count_type;
    private String rfid;
    private String vin;
    private String carNo;
    private String staffId;//员工id
    private int type;//车辆类型

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public CarChecks() {
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Integer getContract_num() {
        return contract_num;
    }

    public void setContract_num(Integer contract_num) {
        this.contract_num = contract_num;
    }

    public Integer getShop_num() {
        return shop_num;
    }

    public void setShop_num(Integer shop_num) {
        this.shop_num = shop_num;
    }

    public Integer getCar_num() {
        return car_num;
    }

    public void setCar_num(Integer car_num) {
        this.car_num = car_num;
    }

    public Integer getCount_type() {
        return count_type;
    }

    public void setCount_type(Integer count_type) {
        this.count_type = count_type;
    }


    @Override
    public String toString() {
        return "ContractCount [market=" + market + ", tenant=" + tenant + ", contract_num=" + contract_num
                + ", shop_num=" + shop_num + ", car_num=" + car_num + ", count_type=" + count_type + "]";
    }


} 
