package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CarVehicle implements Serializable {
    private String src;

    private List srcList;

    private String modelName;

    private String modelCode;

    private Double marketPrice;

    private Date initialLicenceTime;

    private int mileage;

    private Double evaluatePrice;

    private  String tenantId;
//
   private String tenantName;
//
    private String tenantPhone;

    private String environmentalStandards;

    private  String gearBox;

    private String engineVolumeUnitl;

    private String color;

    private String brandName;

    private String brandCode;

    private String seriesName;

    private String seriesCode;

    private  long libraryAge;//库龄

    private Date registerTime;//录入时间

    private int stockStatus;

    public int getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(int stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public long getLibraryAge() {
        return libraryAge;
    }

    public void setLibraryAge(long libraryAge) {
        this.libraryAge = libraryAge;
    }

    public List getSrcList() {
        return srcList;
    }

    public void setSrcList(List srcList) {
        this.srcList = srcList;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Date getInitialLicenceTime() {
        return initialLicenceTime;
    }

    public void setInitialLicenceTime(Date initialLicenceTime) {
        this.initialLicenceTime = initialLicenceTime;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Double getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(Double evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }
    public String getEnvironmentalStandards() {
        return environmentalStandards;
    }

    public void setEnvironmentalStandards(String environmentalStandards) {
        this.environmentalStandards = environmentalStandards;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getEngineVolumeUnitl() {
        return engineVolumeUnitl;
    }

    public void setEngineVolumeUnitl(String engineVolumeUnitl) {
        this.engineVolumeUnitl = engineVolumeUnitl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }
}
