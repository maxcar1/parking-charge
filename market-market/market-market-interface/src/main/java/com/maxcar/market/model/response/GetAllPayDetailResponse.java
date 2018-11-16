package com.maxcar.market.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maxcar.market.pojo.PropertyContractPayDetail;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class GetAllPayDetailResponse implements Serializable {
    //合同缴费记录表主键
    private String id;

    private String marketId;

    private String propertyContractId;
    //合同详情ID
    private String propertyContractDetail;

    private Date payTime;

    private Integer status;

    private String payPrice;

    // 待缴纳金额
    private String notPayPrice;

    //物业类型
    private Integer contractCategory;
    //物业数量
    private Double contractCategoryTotal;

    // 操作人
    private String updateOperator;
    // 操作人名字
    private String updateOperatorName;

    private String price;

    private String preferentialPrice;

    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date statusTme;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date insertTime;

}
