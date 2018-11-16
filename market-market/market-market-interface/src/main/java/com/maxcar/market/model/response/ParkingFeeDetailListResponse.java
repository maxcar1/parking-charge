package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author songxuefeng
 * @create 2018-10-25 13:32
 * @description: ${description}
 **/
@Data
public class ParkingFeeDetailListResponse implements Serializable {
    private static final long serialVersionUID = -2910338317252997929L;
    private String id;

    private String cardNo;

    private Integer chargePrice;

    private Integer payType;

    private Integer price;

    private Date beforeTime;

    private Date afterTime;

    private Integer inType;

    private String employeeName;//收费员
    private String employeesId;//收费员id
    private String barrierPosition;//道闸位置
    private String brakeId;//道闸id
    private String parkingTime;
}
