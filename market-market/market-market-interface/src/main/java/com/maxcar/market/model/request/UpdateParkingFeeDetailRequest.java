package com.maxcar.market.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateParkingFeeDetailRequest implements Serializable {

    // 班次
    private String parkingFeeId;

    // 微信 unionId
    private String unionId;

    // 卡号
    private String cardNo;

    // 收费金额
    private Integer chargePrice;

    // 停车券
    private Integer parkingVolume;


    // 付款方式 1  在线支付 2 现金支付
    private Integer payType;

    // 实付金额
    private Integer price;

    // 车场照片
    private String afterImage;

    // 操作人
    private String operator;

}
