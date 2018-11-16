package com.maxcar.market.model.request;

import com.maxcar.base.pojo.PageBean;
import lombok.Data;

import java.io.Serializable;

/**
 * @author songxuefeng
 * @create 2018-10-25 9:59
 * @description: ${description}
 **/
@Data
public class ParkingFeeDetailListRequest extends PageBean implements Serializable {

    private static final long serialVersionUID = -1115898861204688017L;

    private String marketId;
    private String barrierId;
    private String staffId;
    private String userId;
    private Integer payType;
    private String beforeTimeStart;
    private String beforeTimeEnd;
    private String afterTimeStart;
    private String afterTimeEnd;
}
