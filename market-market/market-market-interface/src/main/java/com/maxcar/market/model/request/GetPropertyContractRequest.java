package com.maxcar.market.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetPropertyContractRequest implements Serializable {

    private static final long serialVersionUID = -2781723313401217204L;
    @NotNull(message = "合同主键不能为null")
    private String propertyContractId;


}
