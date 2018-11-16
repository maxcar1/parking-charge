package com.maxcar.stock.entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by chiyanlong on 2018/9/4.
 * 接受录车app详情页传入参数
 */
public class CarParams implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;//临时id

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private boolean flag;//删除，新增标记
    @NotNull(message="rfid不能为空")
    private String rfid;

    @NotNull(message="所属商户不能为空")
    private String tenant;

    @NotNull(message="vin不能为空")
    private String vin;

    @NotNull(message="市场编号不能为空")
    private String market;

    @NotNull(message="所属车辆类别不能为空")
    private int type;
    private String carNo;//车辆编号

    private Integer isNewCar;//0：新车 1：旧车

    private String attribution;//车辆归属地

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    @NotNull(message="座位数不能为空")
    private int seats;//座位数
    @NotNull(message="车辆颜色不能为空")
    private String color;//车辆颜色
    @NotNull(message="汽车排量不能为空")
    private Double displacement;//排量
    @NotNull(message="档位类型不能为空")
    private String airConditionerControlType;//档位类型手动档 自动档


    @NotNull(message="车身尺寸不能为空")
    private String level;//车身尺寸
    @NotNull(message="排放标准不能为空")
    private String environmentalStandards;//排放标准
    @NotNull(message="上牌时间不能为空")
    private String initialLicenceTimeStr;//上牌时间
    @NotNull(message="车型名称不能为空")
    private String modelName;
    @NotNull(message="车型Code不能为空")
    private String modelCode;
    @NotNull(message="品牌不能为空")
    private String brandName;
    @NotNull(message="品牌Code不能为空")
    private String brandCode;
    @NotNull(message="车系code不能为空")
    private String series;
    @NotNull(message="车系名称不能为空")
    private String seriesName;

    @NotNull(message="表显里程不能为空")
    private Integer mileage;//表显里程
    private String ownerName;//车主姓名
    private String ownerIdcard;//车主身份证号码
    private String no;//车牌号

    @NotNull(message="检查类型不能为空")
    private int accidentType;//事故车类型  1-正常 2-火烧车 3-水泡车 4-重大事故车

    private String remark;//备注

    @NotNull(message="新车价不能为空")
    private Double newcarPrice;//新车价
    @NotNull(message="评估价不能为空")
    private Double evaluatePrice;//评估价

    @NotNull(message="售价不能为空")
    private Double marketPrice;//市场销售价
    private Double reservePrice;//底价
    private String picList;//图片
    private String staffId;//员工id
    private Integer status;//车辆状态
    private String fuelForm;//燃油方式
    private Integer carStatus;//车辆状态 1质押 2 非质押

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public String getFuelForm() {
        return fuelForm;
    }

    public void setFuelForm(String fuelForm) {
        this.fuelForm = fuelForm;
    }
    //    private String newRfid;//新rfid
//    private String fuelForm;//燃料类型 汽油  燃油 纯电动 油电混动 其他




    //    private String engineNo;//发动机编号
//    private int version;
//    private String attribution;//城市id

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPicList() {
        return picList;
    }

    public void setPicList(String picList) {
        this.picList = picList;
    }

    public Integer getIsNewCar() {
        return isNewCar;
    }

    public void setIsNewCar(Integer isNewCar) {
        this.isNewCar = isNewCar;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getNewcarPrice() {
        return newcarPrice;
    }

    public void setNewcarPrice(Double newcarPrice) {
        this.newcarPrice = newcarPrice;
    }

    public String getEnvironmentalStandards() {
        return environmentalStandards;
    }

    public void setEnvironmentalStandards(String environmentalStandards) {
        this.environmentalStandards = environmentalStandards;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(int accidentType) {
        this.accidentType = accidentType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getAirConditionerControlType() {
        return airConditionerControlType;
    }

    public void setAirConditionerControlType(String airConditionerControlType) {
        this.airConditionerControlType = airConditionerControlType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getInitialLicenceTimeStr() {
        return initialLicenceTimeStr;
    }

    public void setInitialLicenceTimeStr(String initialLicenceTimeStr) {
        this.initialLicenceTimeStr = initialLicenceTimeStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(Double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public Double getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(Double evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOwnerIdcard() {
        return ownerIdcard;
    }

    public void setOwnerIdcard(String ownerIdcard) {
        this.ownerIdcard = ownerIdcard;
    }



    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
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
}
