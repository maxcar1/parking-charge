package com.maxcar.tenant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class InvoiceDetailsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	@NotNull(message="商户名称不能为空")
	private String tenantName;
	
	private String corporateName;// 法人姓名

	private String name;//区域名字

	private String vin;

	private String carType;//车辆类型

	private Date initialLicenceTime;//上牌时间

	private String brandName;//品牌名字

	private Date billTime;//开票时间

	private Integer price;//开票价格

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Date getInitialLicenceTime() {
		return initialLicenceTime;
	}

	public void setInitialLicenceTime(Date initialLicenceTime) {
		this.initialLicenceTime = initialLicenceTime;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Date getBillTime() {
		return billTime;
	}

	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
