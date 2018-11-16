package com.maxcar.market.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserTenantPack implements Serializable {

    private static final long serialVersionUID = -1305052760477462975L;
    @NotNull(message = "商户名称不能为null")
    private String tenantName;
    @NotNull(message = "商户类型不能为null")
    private String tenantType;

    @NotNull(message = "商铺名称不能为null")
    private String shopName;

    @NotNull(message = "商户所属区域不能为null")
    private String shopArea;//区域

    // 商户负责人名称
    @NotNull(message = "商户负责人不能为null")
    private String contactName;

    // 商户负责人手机号
    @NotNull(message = "商户负责人手机号不能为null")
    private String contactMobile;

    // 负责人身份证号
    @NotNull(message = "商户负责人身份证不能为null")
    private String contactIdCard;
    // 营业执照编号
    private String licenceNo;

    //营业执照复印件
    private String copyBusinessLicense;

    private String corporateName;

    private String corporateIdCard;

    private String corporatePhone;


    //法人身份证正面
    private String corporatePhotoFace;

    //法人身份证反面
    private String corporatePhotoBack;

    //负责人身份证正面
    private String contactPhotoFace;

    //负责人身份证反面
    private String contactPhotoBack;

    private String cateringImage;

    private String healthImage;
}
