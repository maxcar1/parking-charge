package com.maxcar.market.model.response;

import com.maxcar.market.model.base.BaseResponse;

import java.util.Date;

/**
 * @Author sunyazhou
 * @Date 2018/10/15 10:19
 * @desc
 */
public class TransactionListResponse extends BaseResponse {

    private Date bill_time;
    private String purchacerName;//买家
    private String sellerName;//卖家
    private String vin;
    private String carNo; //车牌照号
    private String factoryPlate;//厂牌型号

    public String getFactoryPlate() {
        return factoryPlate;
    }

    public void setFactoryPlate(String factoryPlate) {
        this.factoryPlate = factoryPlate;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Date getBill_time() {
        return bill_time;
    }

    public void setBill_time(Date bill_time) {
        this.bill_time = bill_time;
    }

    public String getPurchacerName() {
        return purchacerName;
    }

    public void setPurchacerName(String purchacerName) {
        this.purchacerName = purchacerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }


}
