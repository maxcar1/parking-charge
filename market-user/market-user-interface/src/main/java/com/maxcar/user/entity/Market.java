package com.maxcar.user.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Market implements Serializable{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    private String id;

    @NotNull(message="市场编号不能为空")
    private String marketNo;
    @NotNull(message="市场名称不能为空")
    private String name;
    @NotNull(message="市场简称不能为空")
    private String simpleName;

    private String description;

    private String corporationName;

    private String corporationId;
    @NotNull(message="市县不能为空")
    private Integer city;
    @NotNull(message="详细地址不能为空")
    private String address;
    @NotNull(message="开户行不能为空")
    private String bank;
    @NotNull(message="开户行账号不能为空")
    private String account;
    @NotNull(message="纳税人识别号不能为空")
    private String taxno;

    private String cityName;

    private Double ltLon;

    private Double ltLat;

    private Double rbLon;

    private Double rbLat;

    private Integer isvalid;

    private Integer version;
    @NotNull(message="联系人不能为空")
    private String contacts1Name;
    @NotNull(message="联系方式不能为空")
    private String contacts1Mobile;

    private String phone;

    private String contacts2Name;

    private String contacts2Mobile;

    private String contacts3Name;

    private String contacts3Mobile;

    private String contacts1Post;

    private String contacts2Post;

    private String contacts3Post;

    private String contacts1Mail;

    private String contacts2Mail;

    private String contacts3Mail;

    private String taxName;

    private String taxPhone;

    private Date registerTime;

    private String picture;

    private Integer export;

    private String appid;

    private String appsecret;

    private String appid2;

    private String appsecret2;

    private String fmapid;


    private Double eLon;

    private Double eLat;
    @NotNull(message="检测单位不能为空")
    private Integer market_test;

//    private String tenantAppId;
    private String tenantappid;
    
    /**
     * 支付
     * @return
     */
    private String payWeChatKey;
    private String payWechatMchId;
    private String payWechatAppId;

    private String marketappid;

    private String marketappsecret;

    private Integer province;

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMarketappid() {
		return marketappid;
	}

	public void setMarketappid(String marketappid) {
		this.marketappid = marketappid;
	}

	public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getAppid2() {
        return appid2;
    }

    public void setAppid2(String appid2) {
        this.appid2 = appid2;
    }

    public String getAppsecret2() {
        return appsecret2;
    }

    public void setAppsecret2(String appsecret2) {
        this.appsecret2 = appsecret2;
    }

    public String getFmapid() {
        return fmapid;
    }

    public void setFmapid(String fmapid) {
        this.fmapid = fmapid;
    }

    public String getTenantappid() {
        return tenantappid;
    }

    public void setTenantappid(String tenantappid) {
        this.tenantappid = tenantappid;
    }

    public String getTenantappsecret() {
        return tenantappsecret;
    }

    public void setTenantappsecret(String tenantappsecret) {
        this.tenantappsecret = tenantappsecret;
    }

    //    private String tenantAppSecret;
    private String tenantappsecret;

    private String logo;
    @NotNull(message="车辆牌照不能为空")
    private String carNumber;
    @NotNull(message="车管所名称不能为空")
    private String carManageName;

    private String copyFile;
    
    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketNo() {
        return marketNo;
    }

    public void setMarketNo(String marketNo) {
        this.marketNo = marketNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    public Double getLtLon() {
        return ltLon;
    }

    public void setLtLon(Double ltLon) {
        this.ltLon = ltLon;
    }

    public Double getLtLat() {
        return ltLat;
    }

    public void setLtLat(Double ltLat) {
        this.ltLat = ltLat;
    }

    public Double getRbLon() {
        return rbLon;
    }

    public void setRbLon(Double rbLon) {
        this.rbLon = rbLon;
    }

    public Double getRbLat() {
        return rbLat;
    }

    public void setRbLat(Double rbLat) {
        this.rbLat = rbLat;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getContacts1Name() {
        return contacts1Name;
    }

    public void setContacts1Name(String contacts1Name) {
        this.contacts1Name = contacts1Name;
    }

    public String getContacts1Mobile() {
        return contacts1Mobile;
    }

    public void setContacts1Mobile(String contacts1Mobile) {
        this.contacts1Mobile = contacts1Mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContacts2Name() {
        return contacts2Name;
    }

    public void setContacts2Name(String contacts2Name) {
        this.contacts2Name = contacts2Name;
    }

    public String getContacts2Mobile() {
        return contacts2Mobile;
    }

    public void setContacts2Mobile(String contacts2Mobile) {
        this.contacts2Mobile = contacts2Mobile;
    }

    public String getContacts3Name() {
        return contacts3Name;
    }

    public void setContacts3Name(String contacts3Name) {
        this.contacts3Name = contacts3Name;
    }

    public String getContacts3Mobile() {
        return contacts3Mobile;
    }

    public void setContacts3Mobile(String contacts3Mobile) {
        this.contacts3Mobile = contacts3Mobile;
    }

    public String getContacts1Post() {
        return contacts1Post;
    }

    public void setContacts1Post(String contacts1Post) {
        this.contacts1Post = contacts1Post;
    }

    public String getContacts2Post() {
        return contacts2Post;
    }

    public void setContacts2Post(String contacts2Post) {
        this.contacts2Post = contacts2Post;
    }

    public String getContacts3Post() {
        return contacts3Post;
    }

    public void setContacts3Post(String contacts3Post) {
        this.contacts3Post = contacts3Post;
    }

    public String getContacts1Mail() {
        return contacts1Mail;
    }

    public void setContacts1Mail(String contacts1Mail) {
        this.contacts1Mail = contacts1Mail;
    }

    public String getContacts2Mail() {
        return contacts2Mail;
    }

    public void setContacts2Mail(String contacts2Mail) {
        this.contacts2Mail = contacts2Mail;
    }

    public String getContacts3Mail() {
        return contacts3Mail;
    }

    public void setContacts3Mail(String contacts3Mail) {
        this.contacts3Mail = contacts3Mail;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxPhone() {
        return taxPhone;
    }

    public void setTaxPhone(String taxPhone) {
        this.taxPhone = taxPhone;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getExport() {
        return export;
    }

    public void setExport(Integer export) {
        this.export = export;
    }


    public Double geteLon() {
        return eLon;
    }

    public void seteLon(Double eLon) {
        this.eLon = eLon;
    }

    public Double geteLat() {
        return eLat;
    }

    public void seteLat(Double eLat) {
        this.eLat = eLat;
    }

    public Integer getMarket_test() {
        return market_test;
    }

    public void setMarket_test(Integer market_test) {
        this.market_test = market_test;
    }
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarManageName() {
        return carManageName;
    }

    public void setCarManageName(String carManageName) {
        this.carManageName = carManageName;
    }

    public String getCopyFile() {
        return copyFile;
    }

    public void setCopyFile(String copyFile) {
        this.copyFile = copyFile;
    }

    public Integer getStockControls() {
        return stockControls;
    }

    public void setStockControls(Integer stockControls) {
        this.stockControls = stockControls;
    }

    @NotNull(message="库存管制不能为空")
    private Integer stockControls;
//    @NotNull(message="检测单位不能为空")
//    private String monitoringUnit;


	public String getPayWeChatKey() {
		return payWeChatKey;
	}

	public void setPayWeChatKey(String payWeChatKey) {
		this.payWeChatKey = payWeChatKey;
	}

	public String getPayWechatMchId() {
		return payWechatMchId;
	}

	public void setPayWechatMchId(String payWechatMchId) {
		this.payWechatMchId = payWechatMchId;
	}

	public String getPayWechatAppId() {
		return payWechatAppId;
	}

	public void setPayWechatAppId(String payWechatAppId) {
		this.payWechatAppId = payWechatAppId;
	}
    public String getMarketappsecret() {
        return marketappsecret;
    }

    public void setMarketappsecret(String marketappsecret) {
        this.marketappsecret = marketappsecret == null ? null : marketappsecret.trim();
    }
}