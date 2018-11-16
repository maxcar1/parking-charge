package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.CarChannel;
import com.maxcar.stock.pojo.CarChannelExample;
import java.util.List;

import com.maxcar.stock.vo.CarChannelVo;
import org.apache.ibatis.annotations.Param;

public interface CarChannelMapper {
    int countByExample(CarChannelExample example);

    int deleteByExample(CarChannelExample example);

    int deleteByPrimaryKey(String id);

    int insert(CarChannel record);

    int insertSelective(CarChannel record);

    List<CarChannel> selectByExample(CarChannelExample example);

    CarChannel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CarChannel record, @Param("example") CarChannelExample example);

    int updateByExample(@Param("record") CarChannel record, @Param("example") CarChannelExample example);

    int updateByPrimaryKeySelective(CarChannel record);

    int updateByPrimaryKey(CarChannel record);

    List<CarChannelVo> getCarchannel(String carId);

    List<CarChannelVo> getCarShelfNum(String marketId);

    List<CarChannelVo> getCarchannelsByCar(String carId);
}