package com.maxcar.tenant.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.pojo.IntegralEvaluation;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.IntegralEvaluationService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by 池彦龙 on 2018/8/17.
 */
@RestController
public class IntegralEvaluationController extends BaseController {

    @Autowired
    private IntegralEvaluationService integralEvaluationService;

    /**
     * 新增积分配置表 POST请求
     *
     * @param integralEvaluation 配置表实体类
     * @param request
     * @return
     */
    @RequestMapping(value = "/integralEvaluation/add")
    @OperationAnnotation(title = "新增积分配置表")
    public InterfaceResult add(@RequestBody IntegralEvaluation integralEvaluation, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();

            User user = super.getCurrentUser(request);
            integralEvaluation.setId(UuidUtils.generateIdentifier());
            integralEvaluation.setInsertTime(new Date());
            integralEvaluation.setMarketId(user.getMarketId());
            integralEvaluation.setInsertOperator(user.getUserId());
            interfaceResult=integralEvaluationService.addIntegralEvaluation(integralEvaluation);
        return interfaceResult;
    }

    /**
     * 修改或删除配置表信息
     * @param integralEvaluation 配置表实体类 isvalid 传0为逻辑删除
     * @param request
     * @return
     */
    @RequestMapping(value = "/integralEvaluation/updateOrDel")
    @OperationAnnotation(title = "修改或删除积分配置表")
    public InterfaceResult updateOrDel(@RequestBody IntegralEvaluation integralEvaluation, HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
            User user = getCurrentUser(request);
            integralEvaluation.setUpdateTime(new Date());
            integralEvaluation.setUpdateOperator(user.getUserId());
            integralEvaluation.setMarketId(user.getMarketId());
            interfaceResult=integralEvaluationService.updateOrDelIntegralEvaluation(integralEvaluation);

        return interfaceResult;
    }

    /**
     * 查询积分配置列表
     * @param integralEvaluation 积分配置实体类
     * @param request
     * @return
     */
    @RequestMapping(value = "/integralEvaluation/getIntegralEvaluationList")
    @OperationAnnotation(title = "查询积分配置列表")
    public InterfaceResult getIntegralEvaluationList(@RequestBody IntegralEvaluation integralEvaluation, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
            PageInfo pageInfo=integralEvaluationService.selectIntegralEvaluationList(integralEvaluation);
            interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

}
