package com.maxcar.market.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;

import java.math.BigDecimal;
import java.util.Date;

public interface ParkingFeeRuleService {
    public BigDecimal figureOutParkingFee(Date begin,Date end,String marketId,Integer carType)  throws Exception;

    /**
     * 南通二次缴费逻辑
     * @param begin
     * @param payTime
     * @param marketId
     * @param carType
     * @param num 2表示第二阶段产生的费用
     * @return
     * @throws Exception
     */
    public BigDecimal figureOutParkingFee(Date begin,Date payTime,String marketId,Integer carType,int num)  throws Exception;
    InterfaceResult getTotalRule(String marketId,String userId);

    InterfaceResult addFeePeriodTime(JSONObject params) throws Exception;

    InterfaceResult deleteFeePeriodTime(String marketId,String feePeriodTimeId,String userId);

    InterfaceResult getFeePeriodTime(String marketId,String feePeriodTimeId,String userId) throws Exception;

    InterfaceResult editFeePeriodTime(JSONObject params) throws Exception;

    InterfaceResult editTotalRule(JSONObject params);
}
