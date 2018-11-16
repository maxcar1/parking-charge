package com.maxcar.stock.entity.Response;

import java.io.Serializable;
import java.util.Date;

public class CarDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String carNo;

    private String vin;

    private String rfid;

    private String tenant;

    private String areaId;

    private Integer isNewCar;

    private Integer stockStatus;

    private Integer carStatus;

    private Integer carType;

    private Date registerTime;

    private Date initialLicenceTime;

    private Integer isvalid;

    private String staffId;

    private String marketId;

    private String updateOperator;

    private String insterOperator;

    private Date insertTime;

    private String insertTimeStr;

    private Date updateTime;

    private String ownerName;//车主姓名

    private String ownerIdcard;//车主身份证号码

    private String no;//车牌号

    private String remark;

    private Integer limitStatus;//限制出场状态 1:不限制 2:限制

    private Integer accidentType;//事故车类型 1-正常 2-火烧车 3-水泡车 4-重大事故车

    private Integer car_status;
    
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

    private Double reservePrice;

    private String tenantName;
    
    private String car_id;

    private String src;

    private Integer type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getIsNewCar() {
		return isNewCar;
	}

	public void setIsNewCar(Integer isNewCar) {
		this.isNewCar = isNewCar;
	}

	public Integer getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	public Integer getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getInitialLicenceTime() {
		return initialLicenceTime;
	}

	public void setInitialLicenceTime(Date initialLicenceTime) {
		this.initialLicenceTime = initialLicenceTime;
	}

	public Integer getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getUpdateOperator() {
		return updateOperator;
	}

	public void setUpdateOperator(String updateOperator) {
		this.updateOperator = updateOperator;
	}

	public String getInsterOperator() {
		return insterOperator;
	}

	public void setInsterOperator(String insterOperator) {
		this.insterOperator = insterOperator;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getInsertTimeStr() {
		return insertTimeStr;
	}

	public void setInsertTimeStr(String insertTimeStr) {
		this.insertTimeStr = insertTimeStr;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerIdcard() {
		return ownerIdcard;
	}

	public void setOwnerIdcard(String ownerIdcard) {
		this.ownerIdcard = ownerIdcard;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLimitStatus() {
		return limitStatus;
	}

	public void setLimitStatus(Integer limitStatus) {
		this.limitStatus = limitStatus;
	}

	public Integer getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(Integer accidentType) {
		this.accidentType = accidentType;
	}

	public Integer getCar_status() {
		return car_status;
	}

	public void setCar_status(Integer car_status) {
		this.car_status = car_status;
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

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
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
		this.color = color;
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
		this.environmentalStandards = environmentalStandards;
	}

	public String getGearBox() {
		return gearBox;
	}

	public void setGearBox(String gearBox) {
		this.gearBox = gearBox;
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
		this.level = level;
	}

	public String getCheckCompanyId() {
		return checkCompanyId;
	}

	public void setCheckCompanyId(String checkCompanyId) {
		this.checkCompanyId = checkCompanyId;
	}

	public Double getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(Double reservePrice) {
		this.reservePrice = reservePrice;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
