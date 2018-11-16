package com.maxcar.market.model.request;

import lombok.Data;

import java.io.Serializable;


@Data
public class AddParkingFeeRequest implements Serializable{


    private static final long serialVersionUID = -4673082950098894302L;
    private String marketId;

    private String brakeId;

    private String employeesId;


}
