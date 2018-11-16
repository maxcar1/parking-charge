package com.maxcar.stock.service;

import com.maxcar.stock.pojo.Car;

import java.util.List;

/**
 * @Author sunyazhou
 * @Date 2018/10/18 13:48
 * @desc 出场车辆管理
 */
public interface OutMarketCarService {

    /**
     * 获取某个市场下所有的出场状态的车辆
     * @param marketId
     * @return
     */
    List<Car> getAllOutMarketCar(String marketId,String[] status);


    /**
     * 定时扫描删除车辆超过允许的出场时间
     * @throws Exception
     */
    void delCarByOutMarketTime() throws Exception;


    /**
     * 定时扫描状态为删除和售出的车辆进行下架淘宝
     * @throws Exception
     */
    void downTaoBao() throws Exception;


}
