package com.maxcar.stock.pojo;



import com.maxcar.base.util.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class DpCar  implements Serializable {
          
	private String id;
	private List<String>ids;
	
	//扩展属性外加字段
	private String series;
	private String seriesName;
	private String brandCode;
	private String displacement;//排量
    private String modelYear;//年款
    private String modelName;//款型名称
    private String modelCode;//modelCode
    private Integer isVendorCertificated;//认证
    private String airConditionerControlType;//手动挡
    private String fuelForm;//燃料
    private String environmentalStandards;//排放标准
    private String level;//轿车级别
    private String mileage;//里程
    private Date initialLicenceTime;//初次上牌时间
    private String initialLicenceTimeStr;//初次上牌时间
    private String brand;//品牌
    private String tenant;//商户
    private String attribution;//车辆归属地
    private String firstResgister;//初次上牌时间
    //条件查询字段
    private Integer carStatus;// 1 场内 2 场外
    private Integer initialLicenceTimeInt;//车龄
    private String orderType;
    private Float marketPrice;//展厅报价
    private Float newcarPrice;//新车市场价
    private String seriesCode;//车型名称
    private String gearBox;//手动挡，自动挡
    private Float minPrice;
    private Float maxPrice;
    private Integer minMileage;
    private Integer maxMileage;
    private String minCarTime;
    private String maxCarTime;
    private String tenantName;//商户名称
    private Integer browses;//浏览次数
    private List  highlights;
    private List<String> images;//车辆详情图片
    private String mainImages;//车辆主图
    private String minImages;//缩略图
    private String taoBaoId;
    private String marketId;
    private String checkUrl;//检测图片地址
    private String taobaoUrl;//淘宝店铺url
    private String vin;
	private Integer curPage;
	private Integer pageSize;

	    
		public String getTenant() {
			return tenant;
		}
		public void setTenant(String tenant) {
			this.tenant = tenant;
		}
		public String getTenantName() {
			return tenantName;
		}
		public void setTenantName(String tenantName) {
			this.tenantName = tenantName;
		}
		
		public String getSeriesCode() {
			return seriesCode;
		}
		public void setSeriesCode(String seriesCode) {
			this.seriesCode = seriesCode;
		}
		public String getGearBox() {
			return gearBox;
		}
		public void setGearBox(String gearBox) {
			this.gearBox = gearBox;
		}
		public Float getMinPrice() {
			return minPrice;
		}
		public void setMinPrice(Float minPrice) {
			this.minPrice = minPrice;
		}
		public Float getMaxPrice() {
			return maxPrice;
		}
		public void setMaxPrice(Float maxPrice) {
			this.maxPrice = maxPrice;
		}
		public Integer getMinMileage() {
			return minMileage;
		}
		public void setMinMileage(Integer minMileage) {
			this.minMileage = minMileage;
		}
		public Integer getMaxMileage() {
			return maxMileage;
		}
		public void setMaxMileage(Integer maxMileage) {
			this.maxMileage = maxMileage;
		}
		public String getMinCarTime() {
			return minCarTime;
		}
		public void setMinCarTime(String minCarTime) {
			this.minCarTime = minCarTime;
		}
		public String getMaxCarTime() {
			return maxCarTime;
		}
		public void setMaxCarTime(String maxCarTime) {
			this.maxCarTime = maxCarTime;
		}
		public String getAirConditionerControlType() {
			return airConditionerControlType;
		}
		public void setAirConditionerControlType(String airConditionerControlType) {
			this.airConditionerControlType = airConditionerControlType;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		public String getBrandCode() {
			return brandCode;
		}
		public void setBrandCode(String brandCode) {
			this.brandCode = brandCode;
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
		public Integer getIsVendorCertificated() {
			return isVendorCertificated;
		}
		public void setIsVendorCertificated(Integer isVendorCertificated) {
			this.isVendorCertificated = isVendorCertificated;
		}
		public String getFuelForm() {
			return fuelForm;
		}
		public void setFuelForm(String fuelForm) {
			this.fuelForm = fuelForm;
		}
		public String getEnvironmentalStandards() {
			return environmentalStandards;
		}
		public void setEnvironmentalStandards(String environmentalStandards) {
			this.environmentalStandards = environmentalStandards;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public Date getInitialLicenceTime() {
			return initialLicenceTime;
		}
		public void setInitialLicenceTime(Date initialLicenceTime) {
			this.initialLicenceTime = initialLicenceTime;
		}
		public String getInitialLicenceTimeStr() {
			return initialLicenceTimeStr;
		}
		public void setInitialLicenceTimeStr(String initialLicenceTimeStr) {
			this.initialLicenceTimeStr = initialLicenceTimeStr;
		}
		public Integer getCarStatus() {
			return carStatus;
		}
		public void setCarStatus(Integer carStatus) {
			this.carStatus = carStatus;
		}
		public Integer getInitialLicenceTimeInt() {
			return initialLicenceTimeInt;
		}
		public void setInitialLicenceTimeInt(Integer initialLicenceTimeInt) {
			this.initialLicenceTimeInt = initialLicenceTimeInt;
		}
		public String getOrderType() {
			return orderType;
		}
		public void setOrderType(String orderType) {
			this.orderType = orderType;
		}
		
		public Float getMarketPrice() {
			return marketPrice;
		}
		public void setMarketPrice(Float marketPrice) {
			this.marketPrice = marketPrice;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public String getAttribution() {
			return attribution;
		}
		public void setAttribution(String attribution) {
			this.attribution = attribution;
		}
		public String getFirstResgister() {
			return firstResgister;
		}
		public void setFirstResgister(String firstResgister) {
			this.firstResgister = firstResgister;
		}
		public Float getNewcarPrice() {
			return newcarPrice;
		}
		public void setNewcarPrice(Float newcarPrice) {
			this.newcarPrice = newcarPrice;
		}
		public Integer getBrowses() {
			return browses;
		}
		public void setBrowses(Integer browses) {
			this.browses = browses;
		}
		public List getHighlights() {
			return highlights;
		}
		public void setHighlights(List highlights) {
			this.highlights = highlights;
		}
		public List<String> getImages() {
			return images;
		}
		public void setImages(List<String> images) {
			this.images = images;
		}
		public String getMainImages() {
			return mainImages;
		}
		public void setMainImages(String mainImages) {
			this.mainImages = mainImages;
		}
		public String getMinImages() {
			return minImages;
		}
		public void setMinImages(String minImages) {
			this.minImages = minImages;
		}
		public List<String> getIds() {
			return ids;
		}
		public void setIds(List<String> ids) {
			this.ids = ids;
		}
		public String getTaoBaoId() {
			return taoBaoId;
		}
		public void setTaoBaoId(String taoBaoId) {
			this.taoBaoId = taoBaoId;
		}
		public String getMarketId() {
			return marketId;
		}
		public void setMarketId(String marketId) {
			this.marketId = marketId;
		}
		public String getCheckUrl() {
			return checkUrl;
		}
		public void setCheckUrl(String checkUrl) {
			this.checkUrl = checkUrl;
		}
		public String getTaobaoUrl() {
			return taobaoUrl;
		}
		public void setTaobaoUrl(String taobaoUrl) {
			this.taobaoUrl = taobaoUrl;
		}
		public String getDisplacement() {
			return displacement;
		}
		public void setDisplacement(String displacement) {
			this.displacement = displacement;
		}
		public String getMileage() {
			return mileage;
		}
		public void setMileage(String mileage) {
			this.mileage = mileage;
		}
		public String getVin() {
			return vin;
		}
		public void setVin(String vin) {
			this.vin = vin;
		}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
