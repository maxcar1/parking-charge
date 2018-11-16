package com.maxcar.tenant.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.tenant.entity.TenantInvoiceRequest;
import com.maxcar.tenant.service.TenantInvoiceService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/TenantInvoice")
public class TenantInvoiceController extends BaseController {

    @Autowired
    private TenantInvoiceService tenantInvoiceService;

    @PostMapping("/list")
    @OperationAnnotation(title = "根据商户名和时间查询商户开票统计")
    public InterfaceResult list(@RequestBody TenantInvoiceRequest tenant, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        String marketId = null;
        if (user != null) {
            marketId = user.getMarketId();
            tenant.setMarketId(marketId);
        }
        Map<String, Object> map = tenantInvoiceService.selectList(tenant);
        interfaceResult.InterfaceResult200(map);
        interfaceResult.setMsg("商户查询开票信息成功");

        return interfaceResult;
    }

    @PostMapping("/listExport")
    @OperationAnnotation(title = "根据商户名和时间查询商户开票统计")
    public InterfaceResult listExport(@RequestBody TenantInvoiceRequest tenant, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        String marketId = null;
        if (user != null) {
            marketId = user.getMarketId();
            tenant.setMarketId(marketId);
        }
        Map<String, Object> map = tenantInvoiceService.selectListExport(tenant);
        interfaceResult.InterfaceResult200(map);
        interfaceResult.setMsg("商户查询开票信息成功");

        return interfaceResult;
    }

    @GetMapping("/InvoiceDetails/{id}/{CurPage}/{PageSize}")
    @OperationAnnotation(title = "根据商户Id和查询商户开票详情")
    public InterfaceResult InvoiceDetails(@PathVariable String id, @PathVariable Integer CurPage,
                                          @PathVariable Integer PageSize) throws Exception {
        InterfaceResult interfaceResult = tenantInvoiceService.selectDetails(id, CurPage, PageSize);

        return interfaceResult;

    }
}
