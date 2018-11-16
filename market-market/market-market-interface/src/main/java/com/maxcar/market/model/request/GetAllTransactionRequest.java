package com.maxcar.market.model.request;

import com.maxcar.base.pojo.PageBean;

import java.io.Serializable;

/**
 * @Author sunyazhou
 * @Date 2018/10/15 9:32
 * @desc
 */
public class GetAllTransactionRequest extends PageBean implements Serializable {

    private String marketId; // 市场ID
    private String sellerName;//卖家姓名
    private String purchacerName;//买家姓名
    private String billTimeStart;// 开票开始时间
    private String billTimeEnd;//开票结束时间

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPurchacerName() {
        return purchacerName;
    }

    public void setPurchacerName(String purchacerName) {
        this.purchacerName = purchacerName;
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
}
