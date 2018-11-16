package com.maxcar.tenant.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.tenant.pojo.IntegralEvaluation;

/**
 * Created by Administrator on 2018/8/16.
 */
public interface IntegralEvaluationService extends BaseService<IntegralEvaluation,String> {


    InterfaceResult addIntegralEvaluation(IntegralEvaluation integralEvaluation);

    InterfaceResult updateOrDelIntegralEvaluation(IntegralEvaluation integralEvaluation);

    PageInfo selectIntegralEvaluationList(IntegralEvaluation integralEvaluation);

}
