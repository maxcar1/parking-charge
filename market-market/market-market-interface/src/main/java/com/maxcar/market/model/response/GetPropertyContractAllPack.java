package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetPropertyContractAllPack implements Serializable {

    //合同编号
    private String contractNo;

    // 商户名称
    private String tenantName;

    // 商户类型
    private String tenantType;

    // 商户负责人
    private String tenantContactName;

    // 商户负责人 手机号
    private String tenantContactMobile;

    // 车位编号
    private String carSpaceNos;

    // 办公室编号
    private String officeNos;

    // 展厅编号
    private String exhibitionNos;

    // 合同状态
    private String propertyContractStatusName;


}
