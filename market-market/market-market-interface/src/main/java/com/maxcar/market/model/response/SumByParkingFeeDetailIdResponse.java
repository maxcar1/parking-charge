package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class SumByParkingFeeDetailIdResponse implements Serializable {

    private static final long serialVersionUID = 3360923941223316072L;
    // 进出车辆数
    private Integer carCount;

    // 收费总金额
    private Integer paySum;

    // 停车券总额
    private Integer parkingVolume;

    // 现金支付总额
    private Integer payCash;

    // 在线支付总额
    private Integer payOnline;

    // 在线支付总额
    private Integer payWeixin;

}
