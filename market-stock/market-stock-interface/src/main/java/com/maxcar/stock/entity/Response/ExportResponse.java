package com.maxcar.stock.entity.Response;

import java.io.Serializable;
import java.math.BigDecimal;


public class ExportResponse implements Serializable {


    private static final long serialVersionUID = -7652291797365202611L;
    // 车辆编号
    private String carNo;

    // vin
    private String vin;

    // 区域
    private String areaName;

    // 商户
    private String tenantName;

    // 是否新车
    private String isNewCar;

    // 车辆状态
    private String carStatus;

    // 库存天数
    private String stockDay;

    // 入库时间
    private String registerTime;

    // 品牌车系
    private String seriesName;

    // 车型
    private String modelName;

    // 首次上牌时间
    private String initialLicenceTime;

    // 表显里程
    private Integer mileage;

    // 售价
    private BigDecimal marketPrice;

    // 估价
    private BigDecimal evaluatePrice;

    // 库存状态
    private String stockStatus;

    // 备注
    private String  remark;


    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getIsNewCar() {
        return isNewCar;
    }

    public void setIsNewCar(String isNewCar) {
        this.isNewCar = isNewCar;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getStockDay() {
        return stockDay;
    }

    public void setStockDay(String stockDay) {
        this.stockDay = stockDay;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getInitialLicenceTime() {
        return initialLicenceTime;
    }

    public void setInitialLicenceTime(String initialLicenceTime) {
        this.initialLicenceTime = initialLicenceTime;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(BigDecimal evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
