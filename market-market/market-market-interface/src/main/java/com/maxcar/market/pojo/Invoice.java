package com.maxcar.market.pojo;

import com.maxcar.base.pojo.PageBean;

import java.util.Date;

public class Invoice extends PageBean {
    private String id;

    private String marketId;

    private Date billTime;

    private String invoiceNo;

    private String currentNo;

    private String contract;

    private String purchacerTaxNo;

    private String purchacerNationality;

    private String purchacerName;

    private Integer purchacerType;

    private String purchacerPassport;

    private String purchacerIdCard;

    private Integer isPurchacerCode;

    private String purchacerNo;

    private String purchacerAddress;

    private String purchacerNation;

    private String purchacerMobile;

    private String tenantId;

    private Integer isSellerCode;

    private String sellerNationality;

    private String sellerTaxNo;

    private String sellerIdCard;

    private String sellerPassport;

    private String sellerNation;

    private String sellerName;

    private Integer sellerType;

    private String sellerNo;

    private String sellerAddress;

    private String sellerMobile;

    private Integer transferType;

    private String factoryPlate;

    private String certificateNumber;

    private Integer invoicePortof;

    private Integer invoiceStatus;

    private String invoiceCode;

    private Integer carSources;

    private String carId;

    private String carNo;

    private Integer price;

    private String registrationNo;

    private Integer type;

    private String vin;

    private String series;

    private String seriesName;

    private String operater;

    private Integer status;

    private Integer version;

    private String taxno;

    private String address;

    private String bank;

    private String account;

    private String operatorName;

    private String auction;

    private String auctionAddress;

    private String auctionNo;

    private String auctionBank;

    private String auctionMobile;

    private String marketTaxno;

    private String marketAddress;

    private String marketBank;

    private String marketPhone;

    private String comment;

    private String ticketOperator;

    private String ticketMarket;

    private String office;

    private Date syncTime;

    private Date insertTime;

    private Date updateTime;

    private String payType;

    private Integer isNewEnergy;

    private String agentIdCard;

    private String agentName;

    private Integer agentType;

    private String agentAddress;

    private String agentTaxNo;

    private String agentMobile;

    private String agentNationality;

    private String agentNo;

    private String agentPassport;

    private String agentNation;

    private Integer isAgentCode;

    private Integer isAgent;

    private String carInvoiceType;//车辆开票类型

    private Date initialRegistrationDate;//初次登记日期

    private String invoicePurchaseId;//购票id

    private Integer carStockStatus;//车辆库存状态


    private String currentTime;
    private String tenantName;
    private String contactName;
    private String tenantArea;
    private Integer invoiceCounts;
    private Integer totalCounts;
    private Integer totalPrice;
    private String billTimeStart;
    private String billTimeEnd;


    public Integer getCarStockStatus() {
        return carStockStatus;
    }

    public void setCarStockStatus(Integer carStockStatus) {
        this.carStockStatus = carStockStatus;
    }

    public Date getInitialRegistrationDate() {
        return initialRegistrationDate;
    }

    public void setInitialRegistrationDate(Date initialRegistrationDate) {
        this.initialRegistrationDate = initialRegistrationDate;
    }

    public String getInvoicePurchaseId() {
        return invoicePurchaseId;
    }

    public void setInvoicePurchaseId(String invoicePurchaseId) {
        this.invoicePurchaseId = invoicePurchaseId;
    }

    public String getCarInvoiceType() {
        return carInvoiceType;
    }

    public void setCarInvoiceType(String carInvoiceType) {
        this.carInvoiceType = carInvoiceType;
    }

    public String getBillTimeStart() {
        return billTimeStart;
    }

    public void setBillTimeStart(String billTimeStart) {
        this.billTimeStart = billTimeStart;
    }

    public String getBillTimeEnd() {
        return billTimeEnd;
    }

    public void setBillTimeEnd(String billTimeEnd) {
        this.billTimeEnd = billTimeEnd;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTenantArea() {
        return tenantArea;
    }

    public void setTenantArea(String tenantArea) {
        this.tenantArea = tenantArea;
    }

    public Integer getInvoiceCounts() {
        return invoiceCounts;
    }

    public void setInvoiceCounts(Integer invoiceCounts) {
        this.invoiceCounts = invoiceCounts;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
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
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public String getCurrentNo() {
        return currentNo;
    }

    public void setCurrentNo(String currentNo) {
        this.currentNo = currentNo == null ? null : currentNo.trim();
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    public String getPurchacerTaxNo() {
        return purchacerTaxNo;
    }

    public void setPurchacerTaxNo(String purchacerTaxNo) {
        this.purchacerTaxNo = purchacerTaxNo == null ? null : purchacerTaxNo.trim();
    }

    public String getPurchacerNationality() {
        return purchacerNationality;
    }

    public void setPurchacerNationality(String purchacerNationality) {
        this.purchacerNationality = purchacerNationality == null ? null : purchacerNationality.trim();
    }

    public String getPurchacerName() {
        return purchacerName;
    }

    public void setPurchacerName(String purchacerName) {
        this.purchacerName = purchacerName == null ? null : purchacerName.trim();
    }

    public Integer getPurchacerType() {
        return purchacerType;
    }

    public void setPurchacerType(Integer purchacerType) {
        this.purchacerType = purchacerType;
    }

    public String getPurchacerPassport() {
        return purchacerPassport;
    }

    public void setPurchacerPassport(String purchacerPassport) {
        this.purchacerPassport = purchacerPassport == null ? null : purchacerPassport.trim();
    }

    public String getPurchacerIdCard() {
        return purchacerIdCard;
    }

    public void setPurchacerIdCard(String purchacerIdCard) {
        this.purchacerIdCard = purchacerIdCard == null ? null : purchacerIdCard.trim();
    }

    public Integer getIsPurchacerCode() {
        return isPurchacerCode;
    }

    public void setIsPurchacerCode(Integer isPurchacerCode) {
        this.isPurchacerCode = isPurchacerCode;
    }

    public String getPurchacerNo() {
        return purchacerNo;
    }

    public void setPurchacerNo(String purchacerNo) {
        this.purchacerNo = purchacerNo == null ? null : purchacerNo.trim();
    }

    public String getPurchacerAddress() {
        return purchacerAddress;
    }

    public void setPurchacerAddress(String purchacerAddress) {
        this.purchacerAddress = purchacerAddress == null ? null : purchacerAddress.trim();
    }

    public String getPurchacerNation() {
        return purchacerNation;
    }

    public void setPurchacerNation(String purchacerNation) {
        this.purchacerNation = purchacerNation == null ? null : purchacerNation.trim();
    }

    public String getPurchacerMobile() {
        return purchacerMobile;
    }

    public void setPurchacerMobile(String purchacerMobile) {
        this.purchacerMobile = purchacerMobile == null ? null : purchacerMobile.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Integer getIsSellerCode() {
        return isSellerCode;
    }

    public void setIsSellerCode(Integer isSellerCode) {
        this.isSellerCode = isSellerCode;
    }

    public String getSellerNationality() {
        return sellerNationality;
    }

    public void setSellerNationality(String sellerNationality) {
        this.sellerNationality = sellerNationality == null ? null : sellerNationality.trim();
    }

    public String getSellerTaxNo() {
        return sellerTaxNo;
    }

    public void setSellerTaxNo(String sellerTaxNo) {
        this.sellerTaxNo = sellerTaxNo == null ? null : sellerTaxNo.trim();
    }

    public String getSellerIdCard() {
        return sellerIdCard;
    }

    public void setSellerIdCard(String sellerIdCard) {
        this.sellerIdCard = sellerIdCard == null ? null : sellerIdCard.trim();
    }

    public String getSellerPassport() {
        return sellerPassport;
    }

    public void setSellerPassport(String sellerPassport) {
        this.sellerPassport = sellerPassport == null ? null : sellerPassport.trim();
    }

    public String getSellerNation() {
        return sellerNation;
    }

    public void setSellerNation(String sellerNation) {
        this.sellerNation = sellerNation == null ? null : sellerNation.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public Integer getSellerType() {
        return sellerType;
    }

    public void setSellerType(Integer sellerType) {
        this.sellerType = sellerType;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo == null ? null : sellerNo.trim();
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress == null ? null : sellerAddress.trim();
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile == null ? null : sellerMobile.trim();
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public String getFactoryPlate() {
        return factoryPlate;
    }

    public void setFactoryPlate(String factoryPlate) {
        this.factoryPlate = factoryPlate == null ? null : factoryPlate.trim();
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber == null ? null : certificateNumber.trim();
    }

    public Integer getInvoicePortof() {
        return invoicePortof;
    }

    public void setInvoicePortof(Integer invoicePortof) {
        this.invoicePortof = invoicePortof;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public Integer getCarSources() {
        return carSources;
    }

    public void setCarSources(Integer carSources) {
        this.carSources = carSources;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo == null ? null : registrationNo.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series == null ? null : series.trim();
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName == null ? null : seriesName.trim();
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater == null ? null : operater.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno == null ? null : taxno.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getAuction() {
        return auction;
    }

    public void setAuction(String auction) {
        this.auction = auction == null ? null : auction.trim();
    }

    public String getAuctionAddress() {
        return auctionAddress;
    }

    public void setAuctionAddress(String auctionAddress) {
        this.auctionAddress = auctionAddress == null ? null : auctionAddress.trim();
    }

    public String getAuctionNo() {
        return auctionNo;
    }

    public void setAuctionNo(String auctionNo) {
        this.auctionNo = auctionNo == null ? null : auctionNo.trim();
    }

    public String getAuctionBank() {
        return auctionBank;
    }

    public void setAuctionBank(String auctionBank) {
        this.auctionBank = auctionBank == null ? null : auctionBank.trim();
    }

    public String getAuctionMobile() {
        return auctionMobile;
    }

    public void setAuctionMobile(String auctionMobile) {
        this.auctionMobile = auctionMobile == null ? null : auctionMobile.trim();
    }

    public String getMarketTaxno() {
        return marketTaxno;
    }

    public void setMarketTaxno(String marketTaxno) {
        this.marketTaxno = marketTaxno == null ? null : marketTaxno.trim();
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress == null ? null : marketAddress.trim();
    }

    public String getMarketBank() {
        return marketBank;
    }

    public void setMarketBank(String marketBank) {
        this.marketBank = marketBank == null ? null : marketBank.trim();
    }

    public String getMarketPhone() {
        return marketPhone;
    }

    public void setMarketPhone(String marketPhone) {
        this.marketPhone = marketPhone == null ? null : marketPhone.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getTicketOperator() {
        return ticketOperator;
    }

    public void setTicketOperator(String ticketOperator) {
        this.ticketOperator = ticketOperator == null ? null : ticketOperator.trim();
    }

    public String getTicketMarket() {
        return ticketMarket;
    }

    public void setTicketMarket(String ticketMarket) {
        this.ticketMarket = ticketMarket == null ? null : ticketMarket.trim();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office == null ? null : office.trim();
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Integer getIsNewEnergy() {
        return isNewEnergy;
    }

    public void setIsNewEnergy(Integer isNewEnergy) {
        this.isNewEnergy = isNewEnergy;
    }

    public String getAgentIdCard() {
        return agentIdCard;
    }

    public void setAgentIdCard(String agentIdCard) {
        this.agentIdCard = agentIdCard == null ? null : agentIdCard.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress == null ? null : agentAddress.trim();
    }

    public String getAgentTaxNo() {
        return agentTaxNo;
    }

    public void setAgentTaxNo(String agentTaxNo) {
        this.agentTaxNo = agentTaxNo == null ? null : agentTaxNo.trim();
    }

    public String getAgentMobile() {
        return agentMobile;
    }

    public void setAgentMobile(String agentMobile) {
        this.agentMobile = agentMobile == null ? null : agentMobile.trim();
    }

    public String getAgentNationality() {
        return agentNationality;
    }

    public void setAgentNationality(String agentNationality) {
        this.agentNationality = agentNationality == null ? null : agentNationality.trim();
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo == null ? null : agentNo.trim();
    }

    public String getAgentPassport() {
        return agentPassport;
    }

    public void setAgentPassport(String agentPassport) {
        this.agentPassport = agentPassport == null ? null : agentPassport.trim();
    }

    public String getAgentNation() {
        return agentNation;
    }

    public void setAgentNation(String agentNation) {
        this.agentNation = agentNation == null ? null : agentNation.trim();
    }

    public Integer getIsAgentCode() {
        return isAgentCode;
    }

    public void setIsAgentCode(Integer isAgentCode) {
        this.isAgentCode = isAgentCode;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }
}