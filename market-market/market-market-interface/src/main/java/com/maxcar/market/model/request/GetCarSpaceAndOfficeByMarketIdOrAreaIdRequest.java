package com.maxcar.market.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest implements Serializable {

    private static final long serialVersionUID = -8188535155933155449L;
    private String marketId;

    private String tenantId;

    private String areaId;


}
