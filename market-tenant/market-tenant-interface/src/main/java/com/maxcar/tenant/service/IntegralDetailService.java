package com.maxcar.tenant.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.tenant.pojo.IntegralDetail;

import java.text.ParseException;
import java.util.List;

/**
 * Created by 池彦龙 on 2018/8/17.
 */
public interface IntegralDetailService extends BaseService<IntegralDetail,String> {

    InterfaceResult addIntegralDetail(List<IntegralDetail> integralDetails);

    PageInfo selectByTenantNo(IntegralDetail integralDetails) throws ParseException;

}
