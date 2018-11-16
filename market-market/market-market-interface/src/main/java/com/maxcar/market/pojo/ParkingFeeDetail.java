package com.maxcar.market.pojo;

import com.maxcar.base.pojo.PageBean;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ParkingFeeDetail extends PageBean implements Serializable {

    private static final long serialVersionUID = -5488943520311233241L;
    private String id;

    private String marketId;

    private String parkingFeeId;

    private String unionId;

    private String cardNo;

    private Integer chargePrice;

    private Integer parkingVolume;

    private Integer payType;

    private Integer price;

    private Date beforeTime;

    private Date afterTime;

    private String beforeImage;

    private String afterImage;

    private Integer inType;

    private String reason;

    private Integer isValid;

    private String remark;

    private Date insertTime;

    private Date updateTime;

    private String insertOperator;

    private String updateOperator;

    private String parkingTime;

    private String hms;

    private Integer isVip;

    private Date payTime;

    private Integer reduction;//会员减免

    private Integer integral;// 积分兑换

    private Integer alreadyPaid;// 公众号已经支付

    private String overTime;// 超时时间

    private Integer overTimeFee;// 超时停车费

    private Integer chargeFee;//应收费用


}
