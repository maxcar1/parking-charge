package com.maxcar.stock.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.maxcar.base.pojo.PageBean;
import com.maxcar.stock.pojo.CarPic;

public class CarVo extends PageBean implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String carNo; // 车辆编号

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    private String staffId;

    private String vin; // vin码

    private String rfid;

    private String tenant;

    private String tenantName;

    private String areaId; // 区域

    private Integer isNewCar;
    private Integer carType;

    private Integer stockStatus;

    private Integer carStatus;

    private Date registerTime;

    private Integer isvalid;

    private String marketId;
    private String brandName;

    private String brandCode;

    private String seriesName;

    private String seriesCode;

    private String modelYear;

    private String modelName;

    private String modelCode;

    private Integer mileage;

    private BigDecimal evaluatePrice;

    private BigDecimal newPrice;

    private String color;

    private Double engineVolumeUnitl;

    private String environmentalStandards;

    private String gearBox;

    private String ownerName;

    private Integer seatNumber;

    private String fuelForm;

    private Integer fuelNumber;

    private String checkCompanyId;


    private String updateOperator;

    private String insterOperator;

    private Date insertTime;

    private Date updateTime;

    private String level;

    private Integer carAge;
    private Integer stockDays;

    private String registerTimeStart;
    private String registerTimeEnd;
    private String initialLicenceTime;//初次上牌时间
    private String  remark;
    private String taobaoId;
    private String taobaoUrl;
    private String seats;

    private Integer  accidentType;

    private List<CarPic> listCarPic;

    private Integer shelfStatus;//上架状态 传参
    private String channelId;//平台渠道id 传参
    private Integer shelfNum;//该车上架量

    private String carChannelId;//车辆渠道关系id

    private Date initialLicenceTimeStart;
    private Date initialLicenceTimeEnd;
    private Integer yearStart;
    private Integer yearEnd;
    private Integer mileageStart;
    private Integer mileageEnd;

    private String src;

    private BigDecimal marketPrice;

    private Integer orderType;
    private Integer priceType;
    private Integer disType;

    private String airConditionerControlType;
    private String displacement1;
    private String displacement2;

    public String getDisplacement1() {
        return displacement1;
    }

    public void setDisplacement1(String displacement1) {
        this.displacement1 = displacement1;
    }

    public String getDisplacement2() {
        return displacement2;
    }

    public void setDisplacement2(String displacement2) {
        this.displacement2 = displacement2;
    }

    public Integer getDisType() {
        return disType;
    }

    public void setDisType(Integer disType) {
        this.disType = disType;
    }

    public String getAirConditionerControlType() {
        return airConditionerControlType;
    }

    public void setAirConditionerControlType(String airConditionerControlType) {
        this.airConditionerControlType = airConditionerControlType;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Integer getMileageStart() {
        return mileageStart;
    }

    public void setMileageStart(Integer mileageStart) {
        this.mileageStart = mileageStart;
    }

    public Integer getMileageEnd() {
        return mileageEnd;
    }

    public void setMileageEnd(Integer mileageEnd) {
        this.mileageEnd = mileageEnd;
    }

    private List<CarChannelVo> carChannelVos;


    public Date getInitialLicenceTimeStart() {
        return initialLicenceTimeStart;
    }

    public void setInitialLicenceTimeStart(Date initialLicenceTimeStart) {
        this.initialLicenceTimeStart = initialLicenceTimeStart;
    }

    public Date getInitialLicenceTimeEnd() {
        return initialLicenceTimeEnd;
    }

    public void setInitialLicenceTimeEnd(Date initialLicenceTimeEnd) {
        this.initialLicenceTimeEnd = initialLicenceTimeEnd;
    }

    public Integer getYearStart() {
        return yearStart;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public Integer getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }

    private Object daSouChe;//临时参数，大搜车详细信息

    public String getCarChannelId() {
        return carChannelId;
    }

    public void setCarChannelId(String carChannelId) {
        this.carChannelId = carChannelId;
    }

    public String getTaobaoId() {
        return taobaoId;
    }

    public void setTaobaoId(String taobaoId) {
        this.taobaoId = taobaoId;
    }

    public String getTaobaoUrl() {
        return taobaoUrl;
    }

    public void setTaobaoUrl(String taobaoUrl) {
        this.taobaoUrl = taobaoUrl;
    }

    public Integer getShelfNum() {
        return shelfNum;
    }

    public void setShelfNum(Integer shelfNum) {
        this.shelfNum = shelfNum;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<CarChannelVo> getCarChannelVos() {
        return carChannelVos;
    }

    public void setCarChannelVos(List<CarChannelVo> carChannelVos) {
        this.carChannelVos = carChannelVos;
    }

    public String getRegisterTimeStart() {
        return registerTimeStart;
    }

    public void setRegisterTimeStart(String registerTimeStart) {
        this.registerTimeStart = registerTimeStart;
    }

    public String getRegisterTimeEnd() {
        return registerTimeEnd;
    }

    public void setRegisterTimeEnd(String registerTimeEnd) {
        this.registerTimeEnd = registerTimeEnd;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getCarAge() {
        return carAge;
    }

    public void setCarAge(Integer carAge) {
        this.carAge = carAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
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

    public BigDecimal getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(BigDecimal evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
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

    public String getCheckCompanyId() {
        return checkCompanyId;
    }

    public void setCheckCompanyId(String checkCompanyId) {
        this.checkCompanyId = checkCompanyId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getInitialLicenceTime() {
        return initialLicenceTime;
    }

    public void setInitialLicenceTime(String initialLicenceTime) {
        this.initialLicenceTime = initialLicenceTime;
    }

    public List<CarPic> getListCarPic() {
        return listCarPic;
    }

    public void setListCarPic(List<CarPic> listCarPic) {
        this.listCarPic = listCarPic;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getStockDays() {
        return stockDays;
    }

    public void setStockDays(Integer stockDays) {
        this.stockDays = stockDays;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    public Object getDaSouChe() {
        return daSouChe;
    }

    public void setDaSouChe(Object daSouChe) {
        daSouChe = daSouChe;
    }


    public Integer getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(Integer accidentType) {
        this.accidentType = accidentType;
    }
}