package com.maxcar.market.service;

import com.maxcar.base.service.BaseService;
import com.maxcar.market.pojo.ParkingFeeIntegral;

/**
 * @Author sunyazhou
 * @Date 2018/11/8 14:56
 * @desc
 */
public interface ParkingFeeIntegralService extends BaseService<ParkingFeeIntegral,String> {

    ParkingFeeIntegral selectIntegralByDetailId(String parkingFeeDetailId);
}
