package com.maxcar.market.model.request;

import com.maxcar.market.model.base.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetProPerContractPayAllRequest extends BaseRequest implements Serializable {
    private static final long serialVersionUID = -7570950525565322623L;

    // 市场ID
    private String marketId;

    // 商户名称
    private String tenantName;

    // 合同 编号
    private String contractNo;

    // 缴费状态
    private Integer payStatus;

}
