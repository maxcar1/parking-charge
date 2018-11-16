package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.market.pojo.ParkingFeeIntegral;
import com.maxcar.market.pojo.ParkingFeeIntegralExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkingFeeIntegralMapper extends BaseDao<ParkingFeeIntegral,String> {
    int countByExample(ParkingFeeIntegralExample example);

    int deleteByExample(ParkingFeeIntegralExample example);

    int deleteByPrimaryKey(String id);

    int insert(ParkingFeeIntegral record);

    int insertSelective(ParkingFeeIntegral record);

    List<ParkingFeeIntegral> selectByExample(ParkingFeeIntegralExample example);

    ParkingFeeIntegral selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ParkingFeeIntegral record, @Param("example") ParkingFeeIntegralExample example);

    int updateByExample(@Param("record") ParkingFeeIntegral record, @Param("example") ParkingFeeIntegralExample example);

    int updateByPrimaryKeySelective(ParkingFeeIntegral record);

    int updateByPrimaryKey(ParkingFeeIntegral record);

    ParkingFeeIntegral selectIntegralByDetailId(@Param("parkingFeeDetailId") String parkingFeeDetailId);
}