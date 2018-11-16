package com.maxcar.tenant.entity;

import java.io.Serializable;


public class TenantInvoiceResponse implements Serializable {
    private static final long serialVersionUID = -390794344573579850L;

    private String tenantNo = "";
    private String tenantName = "";
    private String tenantHead = "";
    private String tenantArea = "";
    private String invoiceNum = "";
    private String business = "";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTenantNo() {
        return tenantNo;
    }

    public void setTenantNo(String tenantNo) {
        this.tenantNo = tenantNo;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantHead() {
        return tenantHead;
    }

    public void setTenantHead(String tenantHead) {
        this.tenantHead = tenantHead;
    }

    public String getTenantArea() {
        return tenantArea;
    }

    public void setTenantArea(String tenantArea) {
        this.tenantArea = tenantArea;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
