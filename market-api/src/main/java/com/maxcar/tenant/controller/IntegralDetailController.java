package com.maxcar.tenant.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.tenant.pojo.IntegralDetail;
import com.maxcar.tenant.service.IntegralDetailService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/17.
 */
@RestController
public class IntegralDetailController extends BaseController {
    @Autowired
    private IntegralDetailService integralDetailService;

    /**
     * 新增商户积分详情表
     *
     * @param integralDetails 积分详情表实体类
     * @param request
     * @return
     */
    @RequestMapping(value = "/integralDetail/add")
    @OperationAnnotation(title = "新增积分详情表")
    public InterfaceResult add(@RequestBody List<IntegralDetail> integralDetails, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        for (int i = 0; i < integralDetails.size(); i++) {
            IntegralDetail integralDetail = integralDetails.get(i);
            integralDetail.setId(UuidUtils.generateIdentifier());
            integralDetail.setCheckTime(new Date());
            integralDetail.setInsertOperator(user.getUserId());
            integralDetail.setMarketId(user.getMarketId());
            if (null == integralDetail.getCheckScore() || "".equals(integralDetail.getCheckScore())) {
                integralDetails.remove(i);
                i--;
            }
        }
        interfaceResult = integralDetailService.addIntegralDetail(integralDetails);

        return interfaceResult;
    }

    /**
     * 查询商户积分详情列表
     *
     * @param tenantNo  商户编号
     * @param checkTime 时间 格式为 2018-08
     * @param request
     * @return
     */
    @RequestMapping(value = "/integralDetail/getByTenantNo")
    @OperationAnnotation(title = "查询商户积分详情列表")
    public InterfaceResult getByTenantNo(@RequestBody IntegralDetail integralDetail, HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();

        PageInfo pageInfo = integralDetailService.selectByTenantNo(integralDetail);
        interfaceResult.InterfaceResult200(pageInfo);

        return interfaceResult;
    }


}
