package com.maxcar.market.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateParkingFeeRequest implements Serializable {

    private static final long serialVersionUID = 3980236619529565623L;

    private String parkingFeeId;

    private String marketId;

    private String brakeId;

    private String employeesId;
}
