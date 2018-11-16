package com.maxcar.market.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetPropertyContractAllResponse implements Serializable {

    private static final long serialVersionUID = 7686611281142563293L;
    //合同表主键
    private String propertyContractId;
    //合同编号
    private String contractNo;
    // 合同状态
    private Integer propertyContractStatus;
    // 商户ID
    private String tenantUId;
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
    // 展厅编号
    private String exhibitionNos;
    // 办公室编号
    private String officeNos;

    //车位数量
    private Double carSpaceTotal;

    // 展厅数量
    private Double exhibitionTotal;

    // 办公室数量
    private Double officeTotal;
    // 仓库
    private String warehouseNos;
    private Double warehouseTotal;
    // 临时车位
    private String temporaryCarSpaceNos;
    private Double temporaryCarSpaceTotal;
    // 其他
    private String otherNos;
    private Double otherTotal;


    // 终止合同备注
    private String terminationRemark;

    // 终止合同原因
    private String terminationMessage;

    // 终止合同时间
    private Date terminationTime;


    private Boolean isCategory;

}
