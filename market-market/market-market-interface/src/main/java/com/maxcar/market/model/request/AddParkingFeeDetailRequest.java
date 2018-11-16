package com.maxcar.market.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AddParkingFeeDetailRequest implements Serializable {


    private static final long serialVersionUID = -124141420510696526L;

    private String maxketId;

    // 微信 unionId
    private String unionId;

    // 卡号
    private String cardNo;

    // 入场时间
    private String beforeImage;

    // 操作人
    private String operator;
    //道闸id
    private String barrierId;

}
