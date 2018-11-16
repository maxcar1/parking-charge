package com.maxcar.market.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ParkingFee implements Serializable{

    private static final long serialVersionUID = -8754611830026662666L;
    private String id;

    private String marketId;

    private String shift;

    private String brakeId;

    private String employeesId;

    private Date arrivalTime;

    private Date leaveTime;

    private Integer isValid;

    private String remark;

    private Date insertTime;

    private Date updateTime;

    private String insertOperator;

    private String updateOperator;

}
