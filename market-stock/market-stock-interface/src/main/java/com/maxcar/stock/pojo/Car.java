package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Car implements Serializable{
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

    private Integer attriBution;//车辆归属地

    private String tenantName;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    private Integer checkResult;

    private String taobaoId;

    public String getTaobaoId() {
        return taobaoId;
    }

    public void setTaobaoId(String taobaoId) {
        this.taobaoId = taobaoId;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public Integer getAttriBution() {
        return attriBution;
    }

    public void setAttriBution(Integer attriBution) {
        this.attriBution = attriBution;
    }

    public String getInsertTimeStr() {
        return insertTimeStr;
    }

    public void setInsertTimeStr(String insertTimeStr) {
        this.insertTimeStr = insertTimeStr;
    }

    public Integer getCar_status() {
        return car_status;
    }

    public void setCar_status(Integer car_status) {
        this.car_status = car_status;
    }

    public Integer getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(Integer accidentType) {
        this.accidentType = accidentType;
    }

    public Integer getLimitStatus() {
        return limitStatus;
    }

    public void setLimitStatus(Integer limitStatus) {
        this.limitStatus = limitStatus;
    }

    private List<CarPic> carPic;


    public CarRecord getCarRecord() {
        return carRecord;
    }

    public void setCarRecord(CarRecord carRecord) {
        this.carRecord = carRecord;
    }

    private CarRecord carRecord;//临时出场状态


    public CarBase getCarBase() {
        return carBase;
    }

    public void setCarBase(CarBase carBase) {
        this.carBase = carBase;
    }

    private CarBase carBase;

    public List<CarPic> getCarPic() {
        return carPic;
    }

    public void setCarPic(List<CarPic> carPic) {
        this.carPic = carPic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant == null ? null : tenant.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
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
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    public String getInsterOperator() {
        return insterOperator;
    }

    public void setInsterOperator(String insterOperator) {
        this.insterOperator = insterOperator == null ? null : insterOperator.trim();
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerIdcard() {
        return ownerIdcard;
    }

    public void setOwnerIdcard(String ownerIdcard) {
        this.ownerIdcard = ownerIdcard == null ? null : ownerIdcard.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}