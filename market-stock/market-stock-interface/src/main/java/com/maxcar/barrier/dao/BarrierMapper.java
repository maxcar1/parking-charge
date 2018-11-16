package com.maxcar.barrier.dao;

import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.BarrierExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BarrierMapper {

    Barrier selectByBarrierId(String barrierId);

    List<Barrier> selectBarrierList();

    List<Barrier> selectByExample(BarrierExample barrier);


    int updateByBarrierId(Barrier barrier);
    int updateByPrimaryKeySelective(Barrier barrier);

    int deleteByBarrierId(String barrierId);

    List<Barrier> selectBarrierByMarketId(String marketId);

    Barrier selectBarrierByIdAndMarketId(Barrier barrier);

    public Barrier selectBarrierByRemoteHost(@Param("remoteHost") String remoteHost
            ,@Param("marketId") String marketId);
}