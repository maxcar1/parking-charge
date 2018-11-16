package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.CarChannelRel;
import com.maxcar.stock.pojo.CarChannelRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarChannelRelMapper {
    int countByExample(CarChannelRelExample example);

    int deleteByExample(CarChannelRelExample example);

    int deleteByPrimaryKey(String id);

    int insert(CarChannelRel record);

    int insertSelective(CarChannelRel record);

    List<CarChannelRel> selectByExample(CarChannelRelExample example);

    CarChannelRel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CarChannelRel record, @Param("example") CarChannelRelExample example);

    int updateByExample(@Param("record") CarChannelRel record, @Param("example") CarChannelRelExample example);

    int updateByPrimaryKeySelective(CarChannelRel record);

    int updateByPrimaryKey(CarChannelRel record);

    int updataChanelByCarId(CarChannelRel carChannelRel);
}