package com.maxcar.market.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 */
public class PropertyContractDetail implements Serializable {
    private static final long serialVersionUID = 79228840313467301L;
    private String id;

    private String marketId;

    private String propertyContractId;

    private String tenantId;

    @NotNull(message = "物业编号不能为null")
    private String areaId;

    // 区域ID
    private String area;

    //物业租赁状态
    private Integer areaStatus;

    @NotNull(message = "物业类型不能为null")
    private Integer contractCategory;

    private Double price;

    private Double preferentialPrice;

    @NotNull(message = "付款方式不能为null")
    private Integer type;

    @NotNull(message = "租赁开始日期不能为null")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date statusTme;

    @NotNull(message = "租赁结束日期不能为null")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    private String terminationRemark;

    private String terminationMessage;

    private Date terminationTime;

    private Integer isvalid;

    private String remark;

    private Date insertTime;

    private Date updateTime;

    private String insertOperator;

    private String updateOperator;
    // 合同状态
    private Integer propertyContractDetailStatus;

    //物业区域名字
    private String areaName;
    //物业区域数量
    private Double areaTotal;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(Double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public Integer getPropertyContractDetailStatus() {
        return propertyContractDetailStatus;
    }

    public void setPropertyContractDetailStatus(Integer propertyContractDetailStatus) {
        this.propertyContractDetailStatus = propertyContractDetailStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getPropertyContractId() {
        return propertyContractId;
    }

    public void setPropertyContractId(String propertyContractId) {
        this.propertyContractId = propertyContractId == null ? null : propertyContractId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public Integer getContractCategory() {
        return contractCategory;
    }

    public void setContractCategory(Integer contractCategory) {
        this.contractCategory = contractCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(Double preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStatusTme() {
        return statusTme;
    }

    public void setStatusTme(Date statusTme) {
        this.statusTme = statusTme;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTerminationRemark() {
        return terminationRemark;
    }

    public void setTerminationRemark(String terminationRemark) {
        this.terminationRemark = terminationRemark == null ? null : terminationRemark.trim();
    }

    public String getTerminationMessage() {
        return terminationMessage;
    }

    public void setTerminationMessage(String terminationMessage) {
        this.terminationMessage = terminationMessage == null ? null : terminationMessage.trim();
    }

    public Date getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(Date terminationTime) {
        this.terminationTime = terminationTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getInsertOperator() {
        return insertOperator;
    }

    public void setInsertOperator(String insertOperator) {
        this.insertOperator = insertOperator == null ? null : insertOperator.trim();
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAreaStatus() {
        return areaStatus;
    }

    public void setAreaStatus(Integer areaStatus) {
        this.areaStatus = areaStatus;
    }
}