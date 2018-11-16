package com.maxcar.tenant.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.tenant.pojo.IntegralStatistics;
import com.maxcar.tenant.pojo.IntegralStatisticsExample;
import java.util.List;

import com.maxcar.tenant.pojo.UserTenant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface IntegralStatisticsMapper extends BaseDao<IntegralStatistics,String> {
    int countByExample(IntegralStatisticsExample example);

    int deleteByExample(IntegralStatisticsExample example);

    int deleteByPrimaryKey(String id);

    int insert(IntegralStatistics record);

    int insertSelective(IntegralStatistics record);

    List<IntegralStatistics> selectByExample(IntegralStatisticsExample example);

    IntegralStatistics selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IntegralStatistics record, @Param("example") IntegralStatisticsExample example);

    int updateByExample(@Param("record") IntegralStatistics record, @Param("example") IntegralStatisticsExample example);

    int updateByPrimaryKeySelective(IntegralStatistics record);

    int updateByPrimaryKey(IntegralStatistics record);

    IntegralStatistics selectTenantRowno(IntegralStatistics integralStatistics);

    IntegralStatistics selectAreaRowno(IntegralStatistics integralStatistics);

    int selectCountScore(IntegralStatistics integralStatistics);

    Integer deleteMonthList(List list);

    Integer insertMonthList(List list);
}