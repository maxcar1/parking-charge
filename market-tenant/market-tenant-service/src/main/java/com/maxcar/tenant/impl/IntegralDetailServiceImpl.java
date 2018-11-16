package com.maxcar.tenant.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.dao.IntegralDetailMapper;
import com.maxcar.tenant.dao.UserTenantMapper;
import com.maxcar.tenant.pojo.*;
import com.maxcar.tenant.service.IntegralDetailService;
import com.maxcar.tenant.service.UserTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chiyanlong on 2018/8/16.
 */
@Service("integralDetailService")
public class IntegralDetailServiceImpl extends BaseServiceImpl<IntegralDetail,String> implements IntegralDetailService {

    @Autowired
    private IntegralDetailMapper integralDetailMapper;

    @Override
    public BaseDao<IntegralDetail, String> getBaseMapper() {
        return integralDetailMapper;
    }

    @Override
    public InterfaceResult addIntegralDetail(List<IntegralDetail> integralDetails) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {

            for (IntegralDetail i:integralDetails) {
                if (null != i.getScoresAddSubtract() && i.getScoresAddSubtract() == 2){
                    i.setCheckScore(-i.getCheckScore());
                }
                integralDetailMapper.insertSelective(i);
            }
            interfaceResult.setMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500("服务器异常");
        }
        return interfaceResult;
    }

    @Override
    public PageInfo selectByTenantNo(IntegralDetail integralDetail) throws ParseException {
        PageHelper.startPage(integralDetail.getCurrentPage(), integralDetail.getPageSize());
        List<IntegralDetail> lists = integralDetailMapper.selectByTenantNo(integralDetail);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }
}
