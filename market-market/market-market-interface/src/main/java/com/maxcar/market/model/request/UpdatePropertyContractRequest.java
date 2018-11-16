package com.maxcar.market.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class UpdatePropertyContractRequest implements Serializable {

    //合同表主键
    @NotNull(message = "合同表主键不能为null")
    private String propertyContractId;
    // 合同状态
    private Integer propertyContractStatus;
    // 终止合同备注
    private String terminationRemark;

    // 终止合同原因
    private String terminationMessage;

    // 终止合同时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date terminationTime;


    //营业执照
    private String copyBusinessLicense;

    //租赁合同照片
    private String contractImage;
    //保证书照片
    private String guaranteeImage;
    //承诺书照片
    private String commitmentImage;

    private String updateOperator;

    //法人身份证正面
    private String corporatePhotoFace;

    //法人身份证反面
    private String corporatePhotoBack;

    //负责人身份证正面
    private String contactPhotoFace;

    //负责人身份证反面
    private String contactPhotoBack;

    // 餐饮服务许可证
    private String  cateringImage;

    // 健康证
    private String  healthImage;

}
