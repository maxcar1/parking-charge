package com.maxcar.market.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.market.model.request.*;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.model.response.GetPropertyContractAllPack;
import com.maxcar.market.model.response.GetPropertyContractAllResponse;
import com.maxcar.market.model.response.SumContractPayPriceResponse;
import com.maxcar.market.service.PropertyContractPayService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class PropertyContractController extends BaseController {

    @Autowired
    private PropertyContractService propertyContractService;

    @Autowired
    private PropertyContractPayService propertyContractPayService;

    @RequestMapping("/test")
    public InterfaceResult getPropertyContractAll(@RequestBody @Valid GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest request) throws Exception {

        GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse response = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(request);

        return getInterfaceResult("200", response);
    }

    /**
     * param:
     * describe: 获取合同列表
     * create_date:  lxy   2018/8/16  16:31
     **/
    @OperationAnnotation(title = "获取合同列表")
    @RequestMapping("/property/getPropertyContractAll")
    public InterfaceResult getPropertyContractAll(@RequestBody @Valid GetPropertyContractAllRequest getPropertyContractAllRequest, BindingResult result,
                                                  HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);
        getPropertyContractAllRequest.setMarketId(user.getMarketId());


        // 导出合同
        if (getPropertyContractAllRequest.isExport()) {
            List<GetPropertyContractAllPack> getPropertyContractAllPacks = propertyContractService.pageInfoGetPropertyContractAllResponse(getPropertyContractAllRequest);
            return getInterfaceResult("200", getPropertyContractAllPacks);
        }

        PageInfo<GetPropertyContractAllResponse> pageInfo = propertyContractService.getPropertyContractAll(getPropertyContractAllRequest);
        return getInterfaceResult("200", pageInfo);
    }

    /**
     * param:
     * describe: 新增合同
     * create_date:  lxy   2018/8/16  16:30
     **/

    @RequestMapping("/property/addPropertyContract")
    @OperationAnnotation(title = "新增合同")
    public InterfaceResult addPropertyContract(@RequestBody @Valid AddContractRequest addContractRequest, BindingResult result,
                                               HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        addContractRequest.getPropertyContract().setMarketId(user.getMarketId());
        addContractRequest.getPropertyContract().setInsertOperator(user.getUserId());
        addContractRequest.getPropertyContract().setUpdateOperator(user.getUserId());
        addContractRequest.getPropertyContract().setUpdateTime(new Date());

        InterfaceResult interfaceResult = isAddContractRequest(addContractRequest);

        if ("600".equals(interfaceResult.getCode())) {
            return interfaceResult;
        }

        if (propertyContractService.addPropertyContract(addContractRequest)) {
            // 生成合同缴费记录
            propertyContractPayService.addPropertyContractPay();
            return getInterfaceResult("200", null);
        }

        return getInterfaceResult("600", "添加失败");
    }

    /**
     * param:
     * describe: 终止合同
     * create_date:  lxy   2018/8/16  19:33
     **/
    @OperationAnnotation(title = "终止合同")
    @RequestMapping("/property/endPropertyContract")
    public InterfaceResult endPropertyContract(@RequestBody @Valid UpdatePropertyContractRequest updatePropertyContractRequest, BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        // 入参判断
       /* if (tIsEmpty(updatePropertyContractRequest.getTerminationMessage()) || null == updatePropertyContractRequest.getTerminationTime()) {
            return getInterfaceResult("406", null);
        }*/

        User user = getCurrentUser(request);

        updatePropertyContractRequest.setUpdateOperator(user.getUserId());
        updatePropertyContractRequest.setPropertyContractStatus(Magic.CONTRACT_STATUS_END);

        if (propertyContractService.updatePropertyContract(updatePropertyContractRequest)) {
            return getInterfaceResult("200", null);
        }

        return getInterfaceResult("600", "终止合同失败,请检查合同是否正常或参数是否传入");
    }

    /**
     * param:
     * describe: 修改合同信息  上传相关照片
     * create_date:  lxy   2018/8/17  11:30
     **/
    @OperationAnnotation(title = "修改合同信息  上传相关照片")
    @RequestMapping("/property/updatePropertyContract")
    public InterfaceResult updatePropertyContract(@RequestBody @Valid UpdatePropertyContractRequest updatePropertyContractRequest,
                                                  BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        updatePropertyContractRequest.setUpdateOperator(user.getUserId());
        updatePropertyContractRequest.setPropertyContractStatus(null);

        if (propertyContractService.updatePropertyContract(updatePropertyContractRequest)) {
            return getInterfaceResult("200", null);
        }

        return getInterfaceResult("600", "修改合同失败,请检查合同是否正常");
    }

    /**
     * param:
     * describe: 查看合同详情
     * create_date:  lxy   2018/8/17  14:57
     **/
    @OperationAnnotation(title = "查看合同详情")
    @RequestMapping("/property/getPropertyContract")
    public InterfaceResult getPropertyContract(@RequestBody @Valid GetPropertyContractRequest request, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        return getInterfaceResult("200", propertyContractService.getPropertyContract(request));
    }

    /**
     * param:
     * describe: 查看合同缴费列表
     * create_date:  lxy   2018/8/21  14:15
     **/
    @OperationAnnotation(title = "查看合同缴费列表")
    @RequestMapping("/property/getPropertyContractPayAll")
    public InterfaceResult getPropertyContractPayAll(@RequestBody @Valid GetProPerContractPayAllRequest getProPerContractPayAllRequest, BindingResult result,
                                                     HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);
        getProPerContractPayAllRequest.setMarketId(user.getMarketId());

        return getInterfaceResult("200", propertyContractPayService.getPropertyContractPayAll(getProPerContractPayAllRequest));
    }

    /**
     * param:
     * describe: 查看合同缴费记录和需要缴费的列表
     * create_date:  lxy   2018/8/21  14:18
     **/
    @OperationAnnotation(title = "查看合同缴费记录和需要缴费的列表")
    @RequestMapping("/property/getAllPayDetail")
    public InterfaceResult getAllPayDetail(@RequestBody @Valid GetAllPayDetailRequest request, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        return getInterfaceResult("200", propertyContractPayService.getAllPayDetail(request));
    }

    /**
     * param:
     * describe: 合同缴费
     * create_date:  lxy   2018/8/21  14:34
     **/
    @OperationAnnotation(title = " 合同缴费")
    @RequestMapping("/property/updatePayDetail")
    public InterfaceResult updatePayDetail(@RequestBody @Valid UpdatePayDetailRequest updatePayDetailRequest, BindingResult result, HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return getInterfaceResult("600", error.getDefaultMessage());
            }
        }

        User user = getCurrentUser(request);

        updatePayDetailRequest.setUserId(user.getUserId());

        if (propertyContractPayService.updatePayDetail(updatePayDetailRequest)) {
            return getInterfaceResult("200", null);
        } else {
            return getInterfaceResult("600", "缴费失败");
        }
    }


    /**
     * param:
     * describe: 统计合同缴费
     * create_date:  lxy   2018/8/30  18:03
     **/
    @OperationAnnotation(title = " 统计合同缴费")
    @GetMapping("/property/sumContractPayPrice")
    public InterfaceResult sumContractPayPrice(HttpServletRequest request) throws Exception {

        User user = getCurrentUser(request);


        SumContractPayPriceResponse response = propertyContractPayService.sumContractPayPrice(user.getMarketId());
        if (null == response) {
            getInterfaceResult("600", "查无数据");
        }
        return getInterfaceResult("200", response);
    }
}

