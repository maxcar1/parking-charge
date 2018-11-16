package com.maxcar.market.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class UpdatePayDetailRequest implements Serializable {


    private static final long serialVersionUID = -1861616633352858427L;

    private String userId;
    // 合同主键ID
    @NotNull(message = "合同主键ID不能为null")
    private String propertyContractId;
    // 缴费物业集合
    @NotNull(message = "缴费物业列表不能为null")
    private List<UpdatePayDetailPack> list;

}
