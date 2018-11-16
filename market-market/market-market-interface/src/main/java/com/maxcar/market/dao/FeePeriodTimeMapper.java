package com.maxcar.market.dao;

import com.maxcar.market.pojo.FeePeriodTime;
import com.maxcar.market.pojo.FeePeriodTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeePeriodTimeMapper {
    int countByExample(FeePeriodTimeExample example);

    int deleteByExample(FeePeriodTimeExample example);

    int deleteByPrimaryKey(String id);

    int insert(FeePeriodTime record);

    int insertSelective(FeePeriodTime record);

    List<FeePeriodTime> selectByExample(FeePeriodTimeExample example);

    FeePeriodTime selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FeePeriodTime record, @Param("example") FeePeriodTimeExample example);

    int updateByExample(@Param("record") FeePeriodTime record, @Param("example") FeePeriodTimeExample example);

    int updateByPrimaryKeySelective(FeePeriodTime record);

    int updateByPrimaryKey(FeePeriodTime record);
}