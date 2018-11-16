package com.maxcar.market.pojo;

import com.maxcar.base.pojo.PageBean;

import java.math.BigInteger;
import java.util.Date;

public class InvoicePurchase extends PageBean {
    private String id;

    private String invoiceId;

    private String userId;

    private String userName;

    private BigInteger invoiceCode;

    private Integer invoiceNo;

    private String buyTicketName;

    private Integer pollAll;

    private Integer pollResidue;

    private Date insertTime;

    private String insertOperator;

    private Date updateTime;

    private String updateOperator;

    private Integer isvalid;

    private Integer status;

    private Integer invoiceStartNo;

    private Integer invoiceEndNo;

    private Date billTime;

    private String marketId;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId == null ? null : invoiceId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigInteger getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(BigInteger invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getBuyTicketName() {
        return buyTicketName;
    }

    public void setBuyTicketName(String buyTicketName) {
        this.buyTicketName = buyTicketName == null ? null : buyTicketName.trim();
    }

    public Integer getPollAll() {
        return pollAll;
    }

    public void setPollAll(Integer pollAll) {
        this.pollAll = pollAll;
    }

    public Integer getPollResidue() {
        return pollResidue;
    }

    public void setPollResidue(Integer pollResidue) {
        this.pollResidue = pollResidue;
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

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInvoiceStartNo() {
        return invoiceStartNo;
    }

    public void setInvoiceStartNo(Integer invoiceStartNo) {
        this.invoiceStartNo = invoiceStartNo;
    }

    public Integer getInvoiceEndNo() {
        return invoiceEndNo;
    }

    public void setInvoiceEndNo(Integer invoiceEndNo) {
        this.invoiceEndNo = invoiceEndNo;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }
}