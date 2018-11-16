package com.maxcar.market.model.request;

import com.maxcar.market.model.base.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetAllPayDetailRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 6266926999438972521L;
    @NotNull(message = "缴费状态")
    private Integer payDetailStatus;

    // 合同详情主键
    private String propertyContractDetailId;

    @NotNull(message = "合同主键不能为null")
    private String  propertyContractId;
}
