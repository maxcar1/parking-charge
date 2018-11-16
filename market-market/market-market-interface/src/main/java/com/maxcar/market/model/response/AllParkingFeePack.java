package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AllParkingFeePack implements Serializable{

    private static final long serialVersionUID = 1411581248249750660L;
    private String parkingFeeId;

    private String marketId;

    private String shift;

    private String brakeId;

    private String employeesId;

    private Date arrivalTime;

    private Date leaveTime;

    // 道闸名称
    private  String brakeName;

    // 收费人员名称
    private String employeesName;

    // 进出车辆数
    private Integer carCount;

    // 现金支付
    private Integer payCash;

    // 在线支付
    private Integer payOnline;

    // 停车券
    private Integer payParkingVolume;

    // 收费总额
    private Integer paySum;

    // 在线支付总额
    private Integer payWeixin;

}
