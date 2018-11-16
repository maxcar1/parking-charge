package com.maxcar.market.model.request;

import com.maxcar.market.model.base.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class AllParkingFeeRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1224349187687200465L;

    private String marketId;

    private String arrivalTimeStart;

    private String arrivalTimeEnd;

    private String leaveTimeStart;

    private String leaveTimeEnd;

}
