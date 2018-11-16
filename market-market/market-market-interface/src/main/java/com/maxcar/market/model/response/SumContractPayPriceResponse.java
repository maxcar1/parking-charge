package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class SumContractPayPriceResponse implements Serializable {

    private static final long serialVersionUID = -8714943450207938969L;
    private Integer price;

    private Integer completePrice;

}
