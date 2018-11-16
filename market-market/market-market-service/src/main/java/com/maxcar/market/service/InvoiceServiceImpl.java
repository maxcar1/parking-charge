package com.maxcar.market.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DateUtils;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.pojo.TradeInformation;
import com.maxcar.tenant.pojo.UserTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chiyanlong on 2018/8/24.
 */
@Service("invoiceService")
public class InvoiceServiceImpl extends BaseServiceImpl<Invoice, String> implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public BaseDao<Invoice, String> getBaseMapper() {
        return invoiceMapper;
    }

    @Override
    public PageInfo selectInvoiceCountList(Invoice invoice) {
        PageHelper.startPage(invoice.getCurrentPage(), invoice.getPageSize());
        List<Invoice> lists = invoiceMapper.selectInvoiceCountList(invoice);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public PageInfo selectInvoiceDetail(Invoice invoice) {
        PageHelper.startPage(invoice.getCurrentPage(), invoice.getPageSize());
        List<Invoice> lists = invoiceMapper.selectInvoiceDetail(invoice);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public Invoice selectInvoiceTotalCount() {
        return invoiceMapper.selectInvoiceTotalCount();
    }


    @Override
    public PageInfo getInvoiceList(Invoice invoice) {
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
        if (null != invoice.getPurchacerName() && !invoice.getPurchacerName().equals("")) {
            criteria.andPurchacerNameLike(invoice.getPurchacerName());
        }
        if (null != invoice.getSellerName() && !invoice.getSellerName().equals("")) {
            criteria.andSellerNameLike(invoice.getSellerName());
        }
        if (null != invoice.getInvoiceNo() && !invoice.getInvoiceNo().equals("")) {
            criteria.andInvoiceNoLike(invoice.getInvoiceNo());
        }
        if (null != invoice.getBillTimeStart() && null != invoice.getBillTimeEnd()) {
            criteria.andBillTimeBetween(DateUtils.getDateFromString(invoice.getBillTimeStart(), "yyyy-MM-dd"), DateUtils.getDateFromString(invoice.getBillTimeEnd(), "yyyy-MM-dd"));
        }
        if (null != invoice.getInvoicePortof()) {
            criteria.andInvoicePortofEqualTo(invoice.getInvoicePortof());
        }
        if (null != invoice.getInvoiceStatus()) {
            criteria.andInvoiceStatusEqualTo(invoice.getInvoiceStatus());
        }
        if (null != invoice.getCarSources()) {
            criteria.andCarSourcesEqualTo(invoice.getCarSources());
        }
        invoiceExample.setOrderByClause("insert_time desc");
        PageHelper.startPage(invoice.getCurrentPage(), invoice.getPageSize());
        List<Invoice> lists = invoiceMapper.selectByExample(invoiceExample);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public Invoice selectInvoiceDetailById(String id) {
        return invoiceMapper.selectInvoiceDetailById(id);
    }

    @Override
    public List<TradeInformation> detailsExcel(Invoice invoice) {
        List<TradeInformation> tradeInformations = invoiceMapper.detailsExcel(invoice);
        return tradeInformations;
    }

}
