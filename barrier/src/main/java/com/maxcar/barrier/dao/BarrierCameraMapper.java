package com.maxcar.barrier.dao;

import com.maxcar.barrier.pojo.BarrierCamera;
import com.maxcar.barrier.pojo.BarrierCameraExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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