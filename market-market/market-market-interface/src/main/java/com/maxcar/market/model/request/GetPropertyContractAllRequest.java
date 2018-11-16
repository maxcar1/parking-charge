package com.maxcar.market.model.request;

import com.maxcar.market.model.base.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetPropertyContractAllRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = -6135339562625083270L;

    // 市场ID
    private String marketId;

    // 商户ID
    private String tenantId;

    // 商户名称
    private String tenantName;

    // 商户类型
    private String tenantType;

    // 合同状态
    private Integer propertyContractStatus;

    // 商户负责人
    private String tenantContactName;

    // 商户负责人 手机号
    private String tenantContactMobile;


}
