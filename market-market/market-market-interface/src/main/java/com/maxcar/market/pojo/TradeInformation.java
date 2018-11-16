package com.maxcar.market.pojo;

import com.maxcar.base.util.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class TradeInformation implements Serializable {

    private static final long serialVersionUID = -4039290596415465166L;

    private String purchacerName = "";       // 买方姓名  1

    private String purchacerType = "";      //  买方是否企业  2

    private String isPurchacerCode = "";    // 买方是否有统一代码    3

    private String purchacerNationality = "";// 买方国籍   4

    private String purchacerTaxNo = "";          // 买方统一社会信用代码  5

    private String purchacerNo = "";         // 买方组织机构代码   6

    private String purchacerIdCard = "";     // 买方身份证号  7

    private String purchacerPassport = "";   // 买方护照号   8

    private String purchacerNation = "";     // 买方民族  9

    private String purchacerAddress = "";    // 买方地址  10

    private String purchacerMobile = "";     // 买方手机号  11

    private String sellerName = "";          // 卖方姓名  12

    private String sellerType = "";         // 卖方是否单位  13

    private String isSellerCode = "";       // 卖方是否有统一代码  14

    private String sellerNationality = "";   // 卖方国籍  15

    private String sellerTaxNo = "";         // 卖方统一社会信用代码  16

    private String sellerNo = "";            // 卖方组织机构代码  17

    private String sellerIdCard = "";        // 卖方身份证号  18

    private String sellerPassport = "";      //卖方护照号  19

    private String sellerNation = "";        //卖方民族  20

    private String sellerAddress = "";   //卖方地址  21

    private String sellerMobile = "";   // 卖方手机号  22

    private String isAgent = "";        // 是否代理人  23

    private String agentName = "";   // 代理人姓名  24

    private String agentType = "";  //代理人个人还是单位  25

    private String isAgentCode = ""; //是否有统一代码  26

    private String agentNationality = "";     //代理人国籍   27

    private String agentTaxNo = ""; // 代理人统一社会信用代码  28

    private String agentNo = "";  // 代理人组织机构代码  29

    private String agentIdCard = "";  // 代理人身份证号  30

    private String agentPassport = "";  // 代理人护照号  31

    private String agentNation = "";  // 代理人民族  32

    private String agentAddress = "";  // 代理人住址  33

    private String agentMobile = "";  // 代理人手机  34

    private String carNo = "";  // 车牌号  35

    private String certificateNumber = "";  // 车辆登记证号  36

    private String vin = "";             // 车辆车架号  37

    private String factoryPlate = "";        //厂牌型号  38

    private String level = "";       // 车辆类型  39

    private String isNewEnergy = "";        // 是否新能源  40

    private String office = "";                  // 转入地车管所  41

    private Integer price = 0;                      // 开票价格  42

    private String auction = "";         // 拍卖单位  43

    private String auctionAddress = "";  //拍卖单位地址  44

    private String auctionTaxNo = "";    //  拍卖行纳税人识别号  45    暂缺字段

    private String marketName = "";      //  二手车市场名称  46

    private String marketAddress = "";       // 二手车市场地址  47

    private String marketTaxno = "";     // 二手市场纳税人识别号  48

    private Date billTime = DateUtils.parseDate("0000-01-01", DateUtils.DATE_FORMAT_DATEONLY);              // 开票时间   49

    private Date dealTime = DateUtils.parseDate("0000-01-01", DateUtils.DATE_FORMAT_DATEONLY);         //  车辆交易时间  50  字段暂缺

    private Date initialLicenceTime = DateUtils.parseDate("0000-01-01", DateUtils.DATE_FORMAT_DATEONLY);  // 初次上牌时间  51

    private Double newPrice = 0.0;        //  新车价格  52

    private Integer mileage = 0;     // 表显里程  53

    public TradeInformation() throws ParseException {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPurchacerName() {
        return purchacerName;
    }

    public void setPurchacerName(String purchacerName) {
        this.purchacerName = purchacerName;
    }

    public String getPurchacerType() {
        return purchacerType;
    }

    public void setPurchacerType(String purchacerType) {
        this.purchacerType = purchacerType;
    }

    public String getIsPurchacerCode() {
        return isPurchacerCode;
    }

    public void setIsPurchacerCode(String isPurchacerCode) {
        this.isPurchacerCode = isPurchacerCode;
    }

    public String getPurchacerNationality() {
        return purchacerNationality;
    }

    public void setPurchacerNationality(String purchacerNationality) {
        this.purchacerNationality = purchacerNationality;
    }

    public String getPurchacerTaxNo() {
        return purchacerTaxNo;
    }

    public void setPurchacerTaxNo(String purchacerTaxNo) {
        this.purchacerTaxNo = purchacerTaxNo;
    }

    public String getPurchacerNo() {
        return purchacerNo;
    }

    public void setPurchacerNo(String purchacerNo) {
        this.purchacerNo = purchacerNo;
    }

    public String getPurchacerIdCard() {
        return purchacerIdCard;
    }

    public void setPurchacerIdCard(String purchacerIdCard) {
        this.purchacerIdCard = purchacerIdCard;
    }

    public String getPurchacerPassport() {
        return purchacerPassport;
    }

    public void setPurchacerPassport(String purchacerPassport) {
        this.purchacerPassport = purchacerPassport;
    }

    public String getPurchacerNation() {
        return purchacerNation;
    }

    public void setPurchacerNation(String purchacerNation) {
        this.purchacerNation = purchacerNation;
    }

    public String getPurchacerAddress() {
        return purchacerAddress;
    }

    public void setPurchacerAddress(String purchacerAddress) {
        this.purchacerAddress = purchacerAddress;
    }

    public String getPurchacerMobile() {
        return purchacerMobile;
    }

    public void setPurchacerMobile(String purchacerMobile) {
        this.purchacerMobile = purchacerMobile;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getIsSellerCode() {
        return isSellerCode;
    }

    public void setIsSellerCode(String isSellerCode) {
        this.isSellerCode = isSellerCode;
    }

    public String getSellerNationality() {
        return sellerNationality;
    }

    public void setSellerNationality(String sellerNationality) {
        this.sellerNationality = sellerNationality;
    }

    public String getSellerTaxNo() {
        return sellerTaxNo;
    }

    public void setSellerTaxNo(String sellerTaxNo) {
        this.sellerTaxNo = sellerTaxNo;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getSellerIdCard() {
        return sellerIdCard;
    }

    public void setSellerIdCard(String sellerIdCard) {
        this.sellerIdCard = sellerIdCard;
    }

    public String getSellerPassport() {
        return sellerPassport;
    }

    public void setSellerPassport(String sellerPassport) {
        this.sellerPassport = sellerPassport;
    }

    public String getSellerNation() {
        return sellerNation;
    }

    public void setSellerNation(String sellerNation) {
        this.sellerNation = sellerNation;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getIsAgentCode() {
        return isAgentCode;
    }

    public void setIsAgentCode(String isAgentCode) {
        this.isAgentCode = isAgentCode;
    }

    public String getAgentNationality() {
        return agentNationality;
    }

    public void setAgentNationality(String agentNationality) {
        this.agentNationality = agentNationality;
    }

    public String getAgentTaxNo() {
        return agentTaxNo;
    }

    public void setAgentTaxNo(String agentTaxNo) {
        this.agentTaxNo = agentTaxNo;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getAgentIdCard() {
        return agentIdCard;
    }

    public void setAgentIdCard(String agentIdCard) {
        this.agentIdCard = agentIdCard;
    }

    public String getAgentPassport() {
        return agentPassport;
    }

    public void setAgentPassport(String agentPassport) {
        this.agentPassport = agentPassport;
    }

    public String getAgentNation() {
        return agentNation;
    }

    public void setAgentNation(String agentNation) {
        this.agentNation = agentNation;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public String getAgentMobile() {
        return agentMobile;
    }

    public void setAgentMobile(String agentMobile) {
        this.agentMobile = agentMobile;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFactoryPlate() {
        return factoryPlate;
    }

    public void setFactoryPlate(String factoryPlate) {
        this.factoryPlate = factoryPlate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIsNewEnergy() {
        return isNewEnergy;
    }

    public void setIsNewEnergy(String isNewEnergy) {
        this.isNewEnergy = isNewEnergy;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAuction() {
        return auction;
    }

    public void setAuction(String auction) {
        this.auction = auction;
    }

    public String getAuctionAddress() {
        return auctionAddress;
    }

    public void setAuctionAddress(String auctionAddress) {
        this.auctionAddress = auctionAddress;
    }

    public String getAuctionTaxNo() {
        return auctionTaxNo;
    }

    public void setAuctionTaxNo(String auctionTaxNo) {
        this.auctionTaxNo = auctionTaxNo;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public String getMarketTaxno() {
        return marketTaxno;
    }

    public void setMarketTaxno(String marketTaxno) {
        this.marketTaxno = marketTaxno;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Date getInitialLicenceTime() {
        return initialLicenceTime;
    }

    public void setInitialLicenceTime(Date initialLicenceTime) {
        this.initialLicenceTime = initialLicenceTime;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
}
