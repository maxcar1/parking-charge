package com.maxcar.tenant.pojo;

import com.maxcar.base.pojo.PageBean;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserTenant extends PageBean {
    private String id;

    private String tenantNo;//暂时作废

    @NotNull(message="商户名称不能为空")
    private String tenantName;

    @NotNull(message="商户类型不能为空")
    private String tenantType;

    private Date registerTime;//  商户注册时间

    private Date enterSystemTime;// 录入系统时间

    private String corporateName;// 法人姓名

    private String corporateIdCard;// 法人身份证

    private String licenceNo;// 营业执照编号

    private String tenantAddress;

    private String tenantPhone;

    private String contactName;//商户联系人

    private String contactMobile;

    private String contactDuty; // 职务

    private String contactEmail;

    private String copyBusinessLicense;

    private String company;

    private String marketId;

    private String integral;//积分

    private String tenantShopId; // 商铺编号

    private Date checkInTime; //初次入住时间

    private Integer isvalid;

    private String remark;

    private Date insertTime;

    private String insertOperator;

    private Date updateTime;

    private String updateOperator;

    private String area;

    private Integer integralStart;//积分开始

    private Integer integralEnd;//积分结束

    private Integer tenantAge;//商户年限0：1年以下,1:1年到2年,2:2年到3年,3:3年到4年,4:4年到5年,5：5年以上

    private String shopNo;

    private String shopName;

    private String shopArea;//区域

    private Integer countScore;//算出的总积分

    private String corporatePhotoFace;//法人正面照片

    private String corporatePhotoBack;//法人反面照片

    private String corporatePhone;//法人联系方式

    private String contactPhotoFace;//负责人正面照片

    private String contactPhotoBack;//负责人反面照片

    private String contactIdCard;//负责人身份证号码

    private String tenantShopName;//商铺名称

    @NotNull(message="商户所属区域不能为空")
    private String tenantArea;//商户所在区域

    private String status;//商户状态1：正常，2：终止，4：已到期',
    
    private String name;

    private String cateringImage;

    private String healthImage;

    public String getTenantNo() {
        return tenantNo;
    }

    public void setTenantNo(String tenantNo) {
        this.tenantNo = tenantNo;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContactIdCard() {
        return contactIdCard;
    }

    public void setContactIdCard(String contactIdCard) {
        this.contactIdCard = contactIdCard;
    }

    public String getTenantShopName() {
        return tenantShopName;
    }

    public void setTenantShopName(String tenantShopName) {
        this.tenantShopName = tenantShopName;
    }

    public String getTenantArea() {
        return tenantArea;
    }

    public void setTenantArea(String tenantArea) {
        this.tenantArea = tenantArea;
    }

    public String getCorporatePhotoFace() {
        return corporatePhotoFace;
    }

    public void setCorporatePhotoFace(String corporatePhotoFace) {
        this.corporatePhotoFace = corporatePhotoFace;
    }

    public String getCorporatePhotoBack() {
        return corporatePhotoBack;
    }

    public void setCorporatePhotoBack(String corporatePhotoBack) {
        this.corporatePhotoBack = corporatePhotoBack;
    }

    public String getCorporatePhone() {
        return corporatePhone;
    }

    public void setCorporatePhone(String corporatePhone) {
        this.corporatePhone = corporatePhone;
    }

    public String getContactPhotoFace() {
        return contactPhotoFace;
    }

    public void setContactPhotoFace(String contactPhotoFace) {
        this.contactPhotoFace = contactPhotoFace;
    }

    public String getContactPhotoBack() {
        return contactPhotoBack;
    }

    public void setContactPhotoBack(String contactPhotoBack) {
        this.contactPhotoBack = contactPhotoBack;
    }

    public Integer getCountScore() {
        return countScore;
    }

    public void setCountScore(Integer countScore) {
        this.countScore = countScore;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopArea() {
        return shopArea;
    }

    public void setShopArea(String shopArea) {
        this.shopArea = shopArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getIntegralStart() {
        return integralStart;
    }

    public void setIntegralStart(Integer integralStart) {
        this.integralStart = integralStart;
    }

    public Integer getIntegralEnd() {
        return integralEnd;
    }

    public void setIntegralEnd(Integer integralEnd) {
        this.integralEnd = integralEnd;
    }

    public Integer getTenantAge() {
        return tenantAge;
    }

    public void setTenantAge(Integer tenantAge) {
        this.tenantAge = tenantAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName == null ? null : tenantName.trim();
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType == null ? null : tenantType.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getEnterSystemTime() {
        return enterSystemTime;
    }

    public void setEnterSystemTime(Date enterSystemTime) {
        this.enterSystemTime = enterSystemTime;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName == null ? null : corporateName.trim();
    }

    public String getCorporateIdCard() {
        return corporateIdCard;
    }

    public void setCorporateIdCard(String corporateIdCard) {
        this.corporateIdCard = corporateIdCard == null ? null : corporateIdCard.trim();
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo == null ? null : licenceNo.trim();
    }

    public String getTenantAddress() {
        return tenantAddress;
    }

    public void setTenantAddress(String tenantAddress) {
        this.tenantAddress = tenantAddress == null ? null : tenantAddress.trim();
    }

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone == null ? null : tenantPhone.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public String getContactDuty() {
        return contactDuty;
    }

    public void setContactDuty(String contactDuty) {
        this.contactDuty = contactDuty == null ? null : contactDuty.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getCopyBusinessLicense() {
        return copyBusinessLicense;
    }

    public void setCopyBusinessLicense(String copyBusinessLicense) {
        this.copyBusinessLicense = copyBusinessLicense == null ? null : copyBusinessLicense.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral == null ? null : integral.trim();
    }

    public String getTenantShopId() {
        return tenantShopId;
    }

    public void setTenantShopId(String tenantShopId) {
        this.tenantShopId = tenantShopId == null ? null : tenantShopId.trim();
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getInsertOperator() {
        return insertOperator;
    }

    public void setInsertOperator(String insertOperator) {
        this.insertOperator = insertOperator == null ? null : insertOperator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    public String getCateringImage() {
        return cateringImage;
    }

    public void setCateringImage(String cateringImage) {
        this.cateringImage = cateringImage;
    }

    public String getHealthImage() {
        return healthImage;
    }

    public void setHealthImage(String healthImage) {
        this.healthImage = healthImage;
    }
}