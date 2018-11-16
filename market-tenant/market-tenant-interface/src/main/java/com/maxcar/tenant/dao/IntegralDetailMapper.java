package com.maxcar.tenant.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.tenant.pojo.IntegralDetail;
import com.maxcar.tenant.pojo.IntegralDetailExample;
import java.util.List;

import com.maxcar.tenant.pojo.IntegralEvaluation;
import com.maxcar.tenant.pojo.IntegralStatistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface IntegralDetailMapper extends BaseDao<IntegralDetail,String> {
    int countByExample(IntegralDetailExample example);

    int deleteByExample(IntegralDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(IntegralDetail record);

    int insertSelective(IntegralDetail record);

    List<IntegralDetail> selectByExample(IntegralDetailExample example);

    IntegralDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IntegralDetail record, @Param("example") IntegralDetailExample example);

    int updateByExample(@Param("record") IntegralDetail record, @Param("example") IntegralDetailExample example);

    int updateByPrimaryKeySelective(IntegralDetail record);

    int updateByPrimaryKey(IntegralDetail record);

    List<IntegralDetail> selectByTenantNo(IntegralDetail integralDetail);

    List<IntegralStatistics> selectByNowMonth();
}