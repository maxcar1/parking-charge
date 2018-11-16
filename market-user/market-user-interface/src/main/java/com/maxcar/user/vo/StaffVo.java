package com.maxcar.user.vo;

import com.maxcar.base.pojo.PageBean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author songxuefeng
 * @create 2018-10-12 13:31
 * @description: ${description}
 **/
public class StaffVo extends PageBean implements Serializable {
    private String id;
    private String staffName;
    private String staffPhone;
    private String roleName;//只关联一个角色
    private Integer state;//审批状态 -1.拒绝 0.待审批 1.同意
    private String staffCheckId;
    private String refuseReason;//拒绝原因

    private String idCard;
    private Date insertTime;
    private Date updateTime;

    private String nameAndPhone;
    private String marketId;
    private String tenantId;
    private Integer isRelation;//岗位是否被关联 0 未关联 1全部
    private Integer isCheck;//是否被审批 0 未审批 1 已审批

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStaffCheckId() {
        return staffCheckId;
    }

    public void setStaffCheckId(String staffCheckId) {
        this.staffCheckId = staffCheckId;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNameAndPhone() {
        return nameAndPhone;
    }

    public void setNameAndPhone(String nameAndPhone) {
        this.nameAndPhone = nameAndPhone;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getIsRelation() {
        return isRelation;
    }

    public void setIsRelation(Integer isRelation) {
        this.isRelation = isRelation;
    }
}
