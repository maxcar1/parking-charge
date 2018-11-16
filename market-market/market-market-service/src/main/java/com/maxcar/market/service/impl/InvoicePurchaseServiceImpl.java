package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.dao.InvoicePurchaseMapper;
import com.maxcar.market.pojo.InvoicePurchase;
import com.maxcar.market.pojo.InvoicePurchaseExample;
import com.maxcar.market.service.InvoicePurchaseService;
import com.maxcar.market.vo.InvoicePurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("invoicePurchaseService")
public class InvoicePurchaseServiceImpl extends BaseServiceImpl<InvoicePurchase, String> implements InvoicePurchaseService {

    @Autowired
    private InvoicePurchaseMapper invoicePurchaseMapper;

    @Override
    public BaseDao<InvoicePurchase, String> getBaseMapper() {
        // TODO Auto-generated method stub
        return invoicePurchaseMapper;
    }

    @Override
    public InterfaceResult selectTicketList(InvoicePurchaseRequest invoicePurchaseRequest) {
        InterfaceResult interfaceResult = new InterfaceResult();

        if (invoicePurchaseRequest.getCurPage() != null && invoicePurchaseRequest.getPageSize() != null && invoicePurchaseRequest.getPageSize() != 0) {
            PageHelper.startPage(invoicePurchaseRequest.getCurPage(), invoicePurchaseRequest.getPageSize());
        }
        if (invoicePurchaseRequest.getInvoiceCode() != null) {
            invoicePurchaseRequest.setInvoiceCode(invoicePurchaseRequest.getInvoiceCode());
        }
        if (invoicePurchaseRequest.getBuyTicketName() != null) {
            //去字符串全部空格
            String buyTicketName = invoicePurchaseRequest.getBuyTicketName().replaceAll("\\s*", "");
            invoicePurchaseRequest.setBuyTicketName(buyTicketName);
        }
        if (invoicePurchaseRequest.getBillTime() != null) {
            invoicePurchaseRequest.setBillTime(invoicePurchaseRequest.getBillTime());
        }

        List<InvoicePurchase> list = invoicePurchaseMapper.selectTicketList(invoicePurchaseRequest);
        PageInfo<InvoicePurchase> pageInfo = new PageInfo(list);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    @Override
    public InterfaceResult insertTicket(InvoicePurchase record) {
        InterfaceResult interfaceResult = new InterfaceResult();
        record.setId(UuidUtils.getUUID());
        record.setInvoiceNo(record.getInvoiceStartNo());
        if (record.getInvoiceEndNo() > record.getInvoiceStartNo()) {
            record.setPollAll(record.getInvoiceEndNo() + 1 - record.getInvoiceStartNo());
        }
        if (record.getInvoiceEndNo() > record.getInvoiceNo()) {
            record.setPollResidue(record.getInvoiceEndNo() + 1 - record.getInvoiceNo());
        }
        Integer num = invoicePurchaseMapper.insertSelective(record);
        if (num == 1) {
            interfaceResult.InterfaceResult200("插入成功");
        }
        return interfaceResult;
    }

    @Override
    public InterfaceResult updateTicketById(String id) {
        InterfaceResult interfaceResult = new InterfaceResult();
        Integer num = invoicePurchaseMapper.updateTicketById(id);
        if (num == 1) {
            interfaceResult.InterfaceResult200("作废成功");
        } else {
            interfaceResult.InterfaceResult500("作废失败");
        }

        return interfaceResult;
    }

    @Override
    public InterfaceResult selectTicketName(String marketId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<InvoicePurchase> list = invoicePurchaseMapper.selectTicketName(marketId);
        PageInfo<InvoicePurchase> pageInfo = new PageInfo(list);
        Map<String, Object> map = new HashMap<>();
        map.put("list", pageInfo.getTotal());
        map.put("list", pageInfo.getList());
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }

    @Override
    public List<InvoicePurchase> selectInvoicePurchase(String marketId) {
        InvoicePurchaseExample invoicePurchaseExample = new InvoicePurchaseExample();
        invoicePurchaseExample.createCriteria().andMarketIdEqualTo(marketId).andStatusEqualTo(1);
        return invoicePurchaseMapper.selectByExample(invoicePurchaseExample);

    }


}
