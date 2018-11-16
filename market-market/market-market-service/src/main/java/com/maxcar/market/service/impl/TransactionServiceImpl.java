package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.model.request.GetAllTransactionRequest;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @Author sunyazhou
 * @Date 2018/10/15 10:28
 * @desc
 */

@Service("transactionService")
public class TransactionServiceImpl extends BaseServiceImpl<Invoice, String> implements TransactionService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public BaseDao<Invoice, String> getBaseMapper() {
        return invoiceMapper;
    }

    /**
     * 交易列表
     *
     * @param request
     * @return
     */
    @Override
    public PageInfo getAllTransaction(GetAllTransactionRequest request) throws ParseException {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();

        String marketId = request.getMarketId();
        criteria.andMarketIdEqualTo(marketId);

        if (StringUtil.isNotEmpty(request.getSellerName())) {
            criteria.andSellerNameLike("%" + request.getSellerName() + "%");
        }
        if (StringUtil.isNotEmpty(request.getPurchacerName())) {
            criteria.andPurchacerNameLike("%" + request.getPurchacerName() + "%");
        }

        String start = request.getBillTimeStart();
        String end = request.getBillTimeEnd();
//        SimpleDateFormat format  = new SimpleDateFormat(DateUtils.SHORT_DATE_FORMAT);

        Date billTimeStart = null;
        Date billTimeEnd = null;
        if (StringUtil.isNotEmpty(start)) {
            billTimeStart = DateUtils.parseDate(start, "yyyy-MM-dd");
        }

        if (StringUtil.isNotEmpty(end)) {
            billTimeEnd = DateUtils.parseDate(end, "yyyy-MM-dd");
            billTimeEnd = DateUtils.getDayEnd(billTimeEnd);
        }

        if (null != billTimeStart && null != billTimeEnd) {
            criteria.andBillTimeBetween(billTimeStart, billTimeEnd);
        } else if (null != billTimeStart && null == billTimeEnd) {
            criteria.andBillTimeGreaterThanOrEqualTo(billTimeStart);
        } else if (null == billTimeStart && null != billTimeEnd) {
            criteria.andBillTimeLessThanOrEqualTo(billTimeEnd);
        }

        invoiceExample.setOrderByClause("insert_time desc");

        List<Invoice> invoices = invoiceMapper.selectByExample(invoiceExample);
        PageInfo pageInfo = new PageInfo(invoices);
        return pageInfo;
    }

    @Override
    public List<Map> getAllTransactionExcel(GetAllTransactionRequest request) throws ParseException {
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();

        String marketId = request.getMarketId();
        criteria.andMarketIdEqualTo(marketId);

        if (StringUtil.isNotEmpty(request.getSellerName())) {
            criteria.andSellerNameLike("%" + request.getSellerName() + "%");
        }
        if (StringUtil.isNotEmpty(request.getPurchacerName())) {
            criteria.andPurchacerNameLike("%" + request.getPurchacerName() + "%");
        }

        String start = request.getBillTimeStart();
        String end = request.getBillTimeEnd();

        Date billTimeStart = null;
        Date billTimeEnd = null;
        if (StringUtil.isNotEmpty(start)) {
            billTimeStart = DateUtils.parseDate(start, "yyyy-MM-dd");
        }

        if (StringUtil.isNotEmpty(end)) {
            billTimeEnd = DateUtils.parseDate(end, "yyyy-MM-dd");
            billTimeEnd = DateUtils.getDayEnd(billTimeEnd);
        }

        if (null != billTimeStart && null != billTimeEnd) {
            criteria.andBillTimeBetween(billTimeStart, billTimeEnd);
        } else if (null != billTimeStart && null == billTimeEnd) {
            criteria.andBillTimeGreaterThanOrEqualTo(billTimeStart);
        } else if (null == billTimeStart && null != billTimeEnd) {
            criteria.andBillTimeLessThanOrEqualTo(billTimeEnd);
        }

        invoiceExample.setOrderByClause("insert_time desc");

        List<Invoice> invoices = invoiceMapper.selectByExample(invoiceExample);

        List<Map> backList = new ArrayList<>();
        Map<String, Object> map = null;
        for (Invoice invoice : invoices) {
            map = new LinkedHashMap<>();
            map.put("billTime", invoice.getBillTime());
            map.put("purchacerName", invoice.getPurchacerName());
            map.put("sellerName", invoice.getSellerName());
            map.put("vin", invoice.getVin());
            map.put("factoryPlate", invoice.getFactoryPlate());
            map.put("carNo", invoice.getCarNo());
            backList.add(map);
        }
        return backList;
    }

    @Override
    public Invoice getTransactionDetails(String marketId, String transactionId) {
        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria criteria = invoiceExample.createCriteria();

        criteria.andMarketIdEqualTo(marketId);

        criteria.andIdEqualTo(transactionId);

        List<Invoice> invoices = invoiceMapper.selectByExample(invoiceExample);

        return (invoices.size()>0? invoices.get(0) : null);
    }


}
