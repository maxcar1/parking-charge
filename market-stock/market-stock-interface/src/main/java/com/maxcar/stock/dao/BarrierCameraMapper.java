package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.BarrierCamera;
import com.maxcar.stock.pojo.BarrierCameraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BarrierCameraMapper {
    int countByExample(BarrierCameraExample example);

    int deleteByPrimaryKey(String id);

    int insert(BarrierCamera record);

    int insertSelective(BarrierCamera record);

    List<BarrierCamera> selectByExample(BarrierCameraExample example);

    BarrierCamera selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BarrierCamera record, @Param("example") BarrierCameraExample example);

    int updateByExample(@Param("record") BarrierCamera record, @Param("example") BarrierCameraExample example);

    int updateByPrimaryKeySelective(BarrierCamera record);

    int updateByPrimaryKey(BarrierCamera record);
}