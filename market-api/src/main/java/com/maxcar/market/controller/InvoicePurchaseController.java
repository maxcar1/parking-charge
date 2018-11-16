package com.maxcar.market.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.pojo.InvoicePurchase;
import com.maxcar.market.service.InvoicePurchaseService;
import com.maxcar.market.vo.InvoicePurchaseRequest;
import com.maxcar.user.entity.User;

/**
 * @author zhufengbo
 * @ClassName: InvoicePurchaseController
 * @Description: 购票接口
 * @date 2018年10月11日
 */
@RestController
@RequestMapping(value = "/api/InvoicePurchase")
public class InvoicePurchaseController extends BaseController {

    @Autowired
    private InvoicePurchaseService invoicePurchaseService;

    /**
     * @Description: 购票列表
     * @Param: InvoicePurchaseRequest
     * @return:
     * @Author: zhufengbo
     */
    @RequestMapping(value = "/selectTicketList")
    public InterfaceResult selectTicketList(@RequestBody InvoicePurchaseRequest invoicePurchaseRequest, HttpServletRequest request) throws Exception {
        User currentUser = getCurrentUser(request);
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult = invoicePurchaseService.selectTicketList(invoicePurchaseRequest);
        return interfaceResult;
    }

    /**
     * @Description: 新增购票
     * @Param: TicketPurchase
     * @return:
     * @Author: zhufengbo
     */
    @RequestMapping(value = "/insertTicket")
    public InterfaceResult insertTicket(@RequestBody InvoicePurchase record, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User currentUser = getCurrentUser(request);
        record.setMarketId(currentUser.getMarketId());
        List<InvoicePurchase> list = invoicePurchaseService.selectInvoicePurchase(currentUser.getMarketId());
        if (null != list && list.size() > 0) {
            interfaceResult.InterfaceResult600("已开发票未用完,请用完之后新建!");
            return interfaceResult;
        }
        interfaceResult = invoicePurchaseService.insertTicket(record);
        return interfaceResult;
    }

    /**
     * @Description: 单条购票作废
     * @Param: id
     * @return:
     * @Author: zhufengbo
     */
    @RequestMapping(value = "/deleteTicket/{id}")
    public InterfaceResult updateTicketById(@PathVariable String id) throws Exception {
        InterfaceResult interfaceResult = invoicePurchaseService.updateTicketById(id);
        return interfaceResult;
    }

    /**
     * @Description: 根据用户市场id获取购票人名称
     * @Param: id
     * @return:
     * @Author: zhufengbo
     */
    @RequestMapping(value = "/selectTicketName")
    public InterfaceResult selectTicketName(HttpServletRequest request) throws Exception {
        User currentUser = getCurrentUser(request);
        InterfaceResult interfaceResult = invoicePurchaseService.selectTicketName(currentUser.getMarketId());
        return interfaceResult;
    }

    /**
     * @Description: 查询可开发票
     * @Param: request
     * @return:
     * @Author: chiyanlong
     */
    @RequestMapping(value = "/selectInvoicePurchase")
    public InterfaceResult selectInvoicePurchase(HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User currentUser = getCurrentUser(request);
        List<InvoicePurchase> list = invoicePurchaseService.selectInvoicePurchase(currentUser.getMarketId());
        if (null != list && list.size() > 0) {
            interfaceResult.InterfaceResult200(list.get(0));
            return interfaceResult;
        } else {
            interfaceResult.InterfaceResult600("请先购票");
            return interfaceResult;
        }
    }

}
