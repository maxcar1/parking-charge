package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class DealResponse implements Serializable {
    private static final long serialVersionUID = 4383656998824229862L;
    // 车辆ID
    private String id;
    //  车辆编号
    private String carNo;
    //  开票车架号
    private String ivin;
    //  库存车架号
    private String cvin;
    //  商户id
    private String tenantId;
    //  商户名
    private String tenantName;
    //  是否是新车
    private String isNewCar;
    //  交易状态  1:在场 2:在内场 3:出场 4:已删除
    private String stockStatus;
        //  交易时间
    private String billTime;
    //车辆来源
    private String  carStatus;
    //  库存天数
    private String registerDay;
    //  入库时间
    private String registerTime;
    //  品牌车系
    private String series;
    //  车型
    private String seriesName;
    //  首次上牌时间
    private String initialLicenceTime;
    //  里程数
    private String mileage;
    //  估价  万元
    private double evaluatePrice;
    //  交易价格  万元
    private double price;
    //  开票编号
    private String invoiceNo;
}
