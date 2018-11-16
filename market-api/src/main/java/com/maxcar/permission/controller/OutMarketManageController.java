package com.maxcar.permission.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.OutMarketManage;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OutMarketManageService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author sunyazhou
 * @Date 2018/10/17 17:51
 * @desc 出场时间管理
 */
@RestController
public class OutMarketManageController extends BaseController {

    @Autowired
    private OutMarketManageService outMarketManageService;

    @RequestMapping("/add/outMarketManage")
    @OperationAnnotation(title = "保存出场时间配置")
    public InterfaceResult addOutMarketManage(@RequestBody OutMarketManage marketManage,  HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        if (marketManage.getMarketId() == null){
            marketManage.setMarketId(user.getMarketId());
        }
        marketManage.setInsertOperator(user.getUserId());
        marketManage.setUpdateOperator(user.getUserId());
        OutMarketManage outMarketManage = outMarketManageService.selectByMarket(marketManage.getMarketId());
        int i = 0;
        if (outMarketManage == null){
            i = outMarketManageService.insertSelective(marketManage);
        }else {
            marketManage.setId(outMarketManage.getId());
            i = outMarketManageService.updateByMarketId(marketManage);
        }
        if (i == 1){
            interfaceResult.setMsg("保存成功");
        }else {
            interfaceResult.setMsg("保存失败");
        }
        return interfaceResult;
    }

}
