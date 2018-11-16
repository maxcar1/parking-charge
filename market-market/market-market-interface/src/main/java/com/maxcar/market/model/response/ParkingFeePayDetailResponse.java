package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author songxuefeng
 * @create 2018-10-26 11:25
 * @description: 停车收费 各类支付汇总
 **/
@Data
public class ParkingFeePayDetailResponse implements Serializable {
    private static final long serialVersionUID = 3243519250716845334L;
    private Integer carCount;//车辆总数
    private Integer paySum;//支付总额
    private Integer aliPay;//支付宝支付
    private Integer weChatPay;//微信支付
    private Integer cashPay;//现金支付
    private Integer publicPay;//公众号支付

    private Integer onlinePay;//网上支付
    private Integer openBarrierCount;//应急开闸次数
}
