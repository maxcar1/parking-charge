package com.maxcar.market.model.response;

import com.maxcar.market.model.request.UserTenantPack;
import com.maxcar.market.pojo.PropertyContract;
import com.maxcar.market.pojo.PropertyContractDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class GetPropertyContractResponse implements Serializable{

    private static final long serialVersionUID = -2060708462388388429L;
    private PropertyContract propertyContract;

    private List<PropertyContractDetail> propertyContractDetailList;

    private UserTenantPack userTenantPack;

    /*//法人身份证正面
    private String corporatePhotoFace;

    //法人身份证反面
    private String corporatePhotoBack;

    //负责人身份证正面
    private String contactPhotoFace;

    //负责人身份证反面
    private String contactPhotoBack;*/


    // 终止合同备注
    private String terminationRemark;

    // 终止合同原因
    private String terminationMessage;

    // 终止合同时间
    private Date terminationTime;
}
