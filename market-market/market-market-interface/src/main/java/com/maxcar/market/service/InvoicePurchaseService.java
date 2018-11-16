package com.maxcar.market.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.market.pojo.InvoicePurchase;
import com.maxcar.market.vo.InvoicePurchaseRequest;

import java.util.List;

/**
 * Created by zhufengbo on 2018/10/11.
 */
public interface InvoicePurchaseService extends BaseService<InvoicePurchase, String> {

    public InterfaceResult selectTicketList(InvoicePurchaseRequest invoicePurchaseRequest);

    public InterfaceResult insertTicket(InvoicePurchase record);

    public InterfaceResult updateTicketById(String id);

    public InterfaceResult selectTicketName(String marketId);

    List<InvoicePurchase> selectInvoicePurchase(String marketId);
}
