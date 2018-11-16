package com.maxcar.permission.controller;


import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.PageBean;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.OperationRecord;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.user.service.OperationRecordService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @Description: 操作记录接口
* @Param:
* @return:
* @Author: 罗顺锋
* @Date: 2018/5/18
*/
@RestController
@RequestMapping("/api/operation")
public class OperationRecordController extends BaseController {
    @Autowired
    private OperationRecordService operationRecordService;

    /**
    * @Description: 查看操作日志列表
    * @Param: [operationRecord, request, response]
    * @return: com.maxcar.core.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/18
    */
    @RequestMapping(value="/list")
    @OperationAnnotation(title = "查看操作日志列表")
    public InterfaceResult list(@RequestBody OperationRecord operationRecord, HttpServletRequest request, HttpServletResponse response) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        PageBean pageBean = operationRecord.getPageBean();
        pageBean = pageBean==null?new PageBean():pageBean;
        operationRecord.setPageBean(pageBean);
        operationRecord.setMarketId(user.getMarketId());
        operationRecord.setManagerFlag(user.getManagerFlag());
        pageBean = operationRecordService.searchOperationRecord(operationRecord);
        interfaceResult.InterfaceResult200(pageBean);
        return interfaceResult;
    }
}
