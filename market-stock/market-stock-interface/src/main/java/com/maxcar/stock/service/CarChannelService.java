package com.maxcar.stock.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.CarChannel;
import com.maxcar.stock.pojo.CarChannelRel;
import com.maxcar.stock.vo.CarChannelVo;

import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-08-21 15:47
 * @description: ${description}
 **/
public interface CarChannelService {
    List<CarChannelVo> getCarchannel(String carId);

    List<CarChannelVo> getCarchannelsByCar(String carId);

    void upCar(String carId, String channelId);

    InterfaceResult downCar(String carId, String channelId);

    List<CarChannelVo> getCarShelfNum(String marketId);

    List<CarChannel> findAll(String marketId);

    CarChannelRel getCarchannelRel(String carId, String id);

    void insertChannel(CarChannelRel carChannelRel);

     void updateChanelByCarId(CarChannelRel carChannelRel);
}
