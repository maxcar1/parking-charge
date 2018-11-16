package com.maxcar.market.dao;

import com.maxcar.market.pojo.ParkingFeeTotal;
import com.maxcar.market.pojo.ParkingFeeTotalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParkingFeeTotalMapper {
    int countByExample(ParkingFeeTotalExample example);

    int deleteByExample(ParkingFeeTotalExample example);

    int deleteByPrimaryKey(String id);

    int insert(ParkingFeeTotal record);

    int insertSelective(ParkingFeeTotal record);

    List<ParkingFeeTotal> selectByExample(ParkingFeeTotalExample example);

    ParkingFeeTotal selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ParkingFeeTotal record, @Param("example") ParkingFeeTotalExample example);

    int updateByExample(@Param("record") ParkingFeeTotal record, @Param("example") ParkingFeeTotalExample example);

    int updateByPrimaryKeySelective(ParkingFeeTotal record);

    int updateByPrimaryKey(ParkingFeeTotal record);
}