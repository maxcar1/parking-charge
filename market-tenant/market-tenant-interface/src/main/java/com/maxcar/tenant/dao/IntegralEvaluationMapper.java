package com.maxcar.tenant.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.tenant.pojo.IntegralEvaluation;
import com.maxcar.tenant.pojo.IntegralEvaluationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegralEvaluationMapper extends BaseDao<IntegralEvaluation,String> {
    int countByExample(IntegralEvaluationExample example);

    int deleteByExample(IntegralEvaluationExample example);

    int deleteByPrimaryKey(String id);

    int insert(IntegralEvaluation record);

    int insertSelective(IntegralEvaluation record);

    List<IntegralEvaluation> selectByExample(IntegralEvaluationExample example);

    IntegralEvaluation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IntegralEvaluation record, @Param("example") IntegralEvaluationExample example);

    int updateByExample(@Param("record") IntegralEvaluation record, @Param("example") IntegralEvaluationExample example);

    int updateByPrimaryKeySelective(IntegralEvaluation record);

    int updateByPrimaryKey(IntegralEvaluation record);
}