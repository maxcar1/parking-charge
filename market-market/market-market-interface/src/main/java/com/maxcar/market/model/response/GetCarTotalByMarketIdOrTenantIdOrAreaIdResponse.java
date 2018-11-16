package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse implements Serializable {

    private static final long serialVersionUID = 3223580920375663925L;
    private  Integer carNumber;

    private  Integer exhibitionNumber;

    private  Integer  office;

    private  Integer carTotal;
}
