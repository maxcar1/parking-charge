package com.maxcar.market.pojo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 */
public class PropertyContract implements Serializable {

    private static final long serialVersionUID = 9081693751045137616L;

    private String id;

    private String marketId;

    private String contractNo;

    private String tenantId;

    private Integer margin;

    private Integer status;

    private String contractRemark;

    private String contractImage;

    private String guaranteeImage;

    private String commitmentImage;

    private Integer isvalid;

    private String remark;

    private Date insertTime;

    private Date updateTime;

    private String insertOperator;

    private String updateOperator;

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
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContractRemark() {
        return contractRemark;
    }

    public void setContractRemark(String contractRemark) {
        this.contractRemark = contractRemark == null ? null : contractRemark.trim();
    }

    public String getContractImage() {
        return contractImage;
    }

    public void setContractImage(String contractImage) {
        this.contractImage = contractImage == null ? null : contractImage.trim();
    }

    public String getGuaranteeImage() {
        return guaranteeImage;
    }

    public void setGuaranteeImage(String guaranteeImage) {
        this.guaranteeImage = guaranteeImage == null ? null : guaranteeImage.trim();
    }

    public String getCommitmentImage() {
        return commitmentImage;
    }

    public void setCommitmentImage(String commitmentImage) {
        this.commitmentImage = commitmentImage == null ? null : commitmentImage.trim();
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
}