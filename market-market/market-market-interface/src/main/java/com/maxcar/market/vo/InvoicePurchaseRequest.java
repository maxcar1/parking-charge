package com.maxcar.market.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class InvoicePurchaseRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private BigInteger invoiceCode;

    private String buyTicketName;

    private Date billTime;

    private String startTime;

    private String endTime;

    private Integer curPage;

    private Integer pageSize;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public BigInteger getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(BigInteger invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getBuyTicketName() {
        return buyTicketName;
    }

    public void setBuyTicketName(String buyTicketName) {
        this.buyTicketName = buyTicketName;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


}
