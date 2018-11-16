package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetProPerContractPayAllResponse implements Serializable {

    private static final long serialVersionUID = 504432280395029615L;
    // 合同缴费表主键
    private String proPerContractPayId;
    // 合同编号
    private String contractNo;
    // 合同主键ID
    private String propertyContractId;
    // 合同缴费状态
    private Integer payStatus;

    private Double price;

    // 商户ID
    private String tenantUId;
    // 商户名称
    private String tenantName;

    // 车位编号
    private String carSpaceNos;
    //车位数量
    private Double carSpaceTotal;
    // 展厅编号
    private String exhibitionNos;
    // 展厅数量
    private Double exhibitionTotal;
    // 办公室编号
    private String officeNos;
    // 办公室数量
    private Double officeTotal;


}
