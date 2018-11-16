package com.maxcar.barrier.dao;

import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.BarrierExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BarrierMapper {

    Barrier selectByBarrierId(String barrierId);
//
//    List<Barrier> selectBarrierList();
//
//    int updateByBarrierId(Barrier barrier);
//
    int deleteByBarrierId(String barrierId);

    int countByExample(BarrierExample example);

    int deleteByExample(BarrierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Barrier record);

    int insertSelective(Barrier record);

    List<Barrier> selectByExample(BarrierExample example);

    Barrier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Barrier record, @Param("example") BarrierExample example);

    int updateByExample(@Param("record") Barrier record, @Param("example") BarrierExample example);

    int updateByPrimaryKeySelective(Barrier record);

    int updateByPrimaryKey(Barrier record);

    public Barrier getBarrierInfoLike(@Param("barrierId") String barrierId, @Param("topic")String topic);
}