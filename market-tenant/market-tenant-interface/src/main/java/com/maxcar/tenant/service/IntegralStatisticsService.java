package com.maxcar.tenant.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.tenant.pojo.IntegralEvaluation;
import com.maxcar.tenant.pojo.IntegralStatistics;
import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by 池彦龙 on 2018/8/17.
 */
public interface IntegralStatisticsService extends BaseService<IntegralStatistics,String> {

    Map selectIntegralStatistics(IntegralStatistics integralStatistics) throws ParseException;

    InterfaceResult addIntegralStatistics(IntegralStatistics integralStatistics);

    InterfaceResult updateOrDelIntegralStatistics(IntegralStatistics integralStatistics);

    InterfaceResult scheduleAdd();
}
