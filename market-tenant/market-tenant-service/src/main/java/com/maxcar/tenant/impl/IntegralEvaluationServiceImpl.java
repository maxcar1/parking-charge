package com.maxcar.tenant.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.tenant.dao.IntegralEvaluationMapper;
import com.maxcar.tenant.dao.UserTenantMapper;
import com.maxcar.tenant.pojo.IntegralEvaluation;
import com.maxcar.tenant.pojo.IntegralEvaluationExample;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.IntegralEvaluationService;
import com.maxcar.tenant.service.UserTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 池彦龙 on 2018/8/16.
 */
@Service("integralEvaluationService")
public class IntegralEvaluationServiceImpl extends BaseServiceImpl<IntegralEvaluation,String> implements IntegralEvaluationService {

    @Autowired
    private IntegralEvaluationMapper integralEvaluationMapper;



    @Override
    public BaseDao<IntegralEvaluation, String> getBaseMapper() {
        return integralEvaluationMapper;
    }

    @Override
    public InterfaceResult addIntegralEvaluation(IntegralEvaluation integralEvaluation) {
        InterfaceResult interfaceResult=new InterfaceResult();
        int count=0;
        count=integralEvaluationMapper.insertSelective(integralEvaluation);
        if (count>0){
            interfaceResult.InterfaceResult200("新增成功");
        }else {
            interfaceResult.InterfaceResult600("新增失败");
        }
        return interfaceResult;
    }

    @Override
    public InterfaceResult updateOrDelIntegralEvaluation(IntegralEvaluation integralEvaluation) {
        InterfaceResult interfaceResult=new InterfaceResult();
        int count=0;
        if (null != integralEvaluation.getIsvalid()) {//逻辑删除需传isvalid为0和更新
            count = integralEvaluationMapper.updateByPrimaryKeySelective(integralEvaluation);
            if (count>0){
                interfaceResult.InterfaceResult200("更新成功");
            }else {
                interfaceResult.InterfaceResult600("无更新记录");
            }
        }else {
            count=integralEvaluationMapper.deleteByPrimaryKey(integralEvaluation.getId());
            if (count>0){
                interfaceResult.InterfaceResult200("删除成功");
            }else {
                interfaceResult.InterfaceResult600("无删除记录");
            }
        }
        return interfaceResult;
    }

    @Override
    public PageInfo selectIntegralEvaluationList(IntegralEvaluation integralEvaluation) {
        IntegralEvaluationExample integralEvaluationExample=new IntegralEvaluationExample();
        integralEvaluationExample.createCriteria().andIsvalidEqualTo(1);
        PageHelper.startPage(integralEvaluation.getCurrentPage(), integralEvaluation.getPageSize());
        List<IntegralEvaluation> lists=integralEvaluationMapper.selectByExample(integralEvaluationExample);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }
}
