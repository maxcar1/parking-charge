package com.maxcar.market.model.request;

import com.maxcar.market.model.base.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class AllParkingFeeDetailRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = -7016729899164831980L;
    private String parkingFeeId;

    private String marketId;

    private String beforeTimeStart;

    private String beforeTimeEnd;

    private String afterTimeStart;

    private String afterTimeEnd;



}
