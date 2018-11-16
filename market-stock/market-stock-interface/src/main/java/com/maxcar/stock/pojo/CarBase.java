package com.maxcar.stock.pojo;

import com.maxcar.base.pojo.PageBean;

import java.io.Serializable;
import java.util.Date;

public class CarBase extends PageBean {
    private String id;

    private String brandName;

    private String brandCode;

    private String seriesName;

    private String seriesCode;

    private String modelYear;

    private String modelName;

    private String modelCode;

    private Integer mileage;

    private Double evaluatePrice;

    private Double newPrice;

    private  String marketId;

    private Date initialLicenceTime;

    private Double marketPrice;

    private String color;

    private Double engineVolumeUnitl;

    private String environmentalStandards;

    private String gearBox;

    private Integer seatNumber;

    private String fuelForm;

    private Integer fuelNumber;

    private String level;

    private String checkCompanyId;

    private String insterOperator;

    private String updateOperator;

    private Date insertTime;

    private Date updateTime;

    private Integer accidentType;

    private Double reservePrice;

    private String tenantName;

    private Integer attribution;//车辆归属地

    public Integer getAttribution() {
        return attribution;
    }

    public void setAttribution(Integer attribution) {
        this.attribution = attribution;
    }

    private Double marketPriceStart;
    private Double marketPriceEnd;

    private Double mileageStart;
    private Double mileageEnd;

    private  Integer autoAgeStart;
    private  Integer autoAgeEnd;


    public Integer getAutoAgeStart() {
        return autoAgeStart;
    }

    public void setAutoAgeStart(Integer autoAgeStart) {
        this.autoAgeStart = autoAgeStart;
    }

    public Integer getAutoAgeEnd() {
        return autoAgeEnd;
    }

    public void setAutoAgeEnd(Integer autoAgeEnd) {
        this.autoAgeEnd = autoAgeEnd;
    }

    public Double getMileageStart() {
        return mileageStart;
    }

    public void setMileageStart(Double mileageStart) {
        this.mileageStart = mileageStart;
    }

    public Double getMileageEnd() {
        return mileageEnd;
    }

    public void setMileageEnd(Double mileageEnd) {
        this.mileageEnd = mileageEnd;
    }

    private String mileageSelect;

    public String getMileageSelect() {
        return mileageSelect;
    }

    public void setMileageSelect(String mileageSelect) {
        this.mileageSelect = mileageSelect;
    }

    public Double getMarketPriceStart() {
        return marketPriceStart;
    }

    public void setMarketPriceStart(Double marketPriceStart) {
        this.marketPriceStart = marketPriceStart;
    }

    public Double getMarketPriceEnd() {
        return marketPriceEnd;
    }

    public void setMarketPriceEnd(Double marketPriceEnd) {
        this.marketPriceEnd = marketPriceEnd;
    }


    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }


    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Object getDaSouChe() {
        return DaSouChe;
    }

    public void setDaSouChe(Object daSouChe) {
        DaSouChe = daSouChe;
    }

    private Object DaSouChe;//临时参数，大搜车详细信息
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName == null ? null : seriesName.trim();
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode == null ? null : seriesCode.trim();
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear == null ? null : modelYear.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode == null ? null : modelCode.trim();
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Double getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(Double evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Double getEngineVolumeUnitl() {
        return engineVolumeUnitl;
    }

    public void setEngineVolumeUnitl(Double engineVolumeUnitl) {
        this.engineVolumeUnitl = engineVolumeUnitl;
    }

    public String getEnvironmentalStandards() {
        return environmentalStandards;
    }

    public void setEnvironmentalStandards(String environmentalStandards) {
        this.environmentalStandards = environmentalStandards == null ? null : environmentalStandards.trim();
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox == null ? null : gearBox.trim();
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getFuelForm() {
        return fuelForm;
    }

    public void setFuelForm(String fuelForm) {
        this.fuelForm = fuelForm;
    }

    public Integer getFuelNumber() {
        return fuelNumber;
    }

    public void setFuelNumber(Integer fuelNumber) {
        this.fuelNumber = fuelNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getCheckCompanyId() {
        return checkCompanyId;
    }

    public void setCheckCompanyId(String checkCompanyId) {
        this.checkCompanyId = checkCompanyId == null ? null : checkCompanyId.trim();
    }

    public String getInsterOperator() {
        return insterOperator;
    }

    public void setInsterOperator(String insterOperator) {
        this.insterOperator = insterOperator == null ? null : insterOperator.trim();
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
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

    public Integer getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(Integer accidentType) {
        this.accidentType = accidentType;
    }

    public Double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(Double reservePrice) {
        this.reservePrice = reservePrice;
    }
    public Date getInitialLicenceTime() {
        return initialLicenceTime;
    }

    public void setInitialLicenceTime(Date initialLicenceTime) {
        this.initialLicenceTime = initialLicenceTime;
    }
}