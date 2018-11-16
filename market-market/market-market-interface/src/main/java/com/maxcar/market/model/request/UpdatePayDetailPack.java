package com.maxcar.market.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdatePayDetailPack  implements Serializable{

    private static final long serialVersionUID = -3886350576484368529L;
    //合同详情ID
    @NotNull(message = "合同详情主键ID 不能为null")
    private String propertyContractDetail;

    //合同缴费记录表主键
    @NotNull(message = "合同记录表主键 不能为null")
    private String propertyContractPayDetailId;

    @NotNull(message = "缴费金额 不能为null")
    private Double payPrice;
}
