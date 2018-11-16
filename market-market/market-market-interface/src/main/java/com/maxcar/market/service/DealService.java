package com.maxcar.market.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.market.model.request.DealRequest;
import com.maxcar.market.model.request.SelectInvoice;
import com.maxcar.market.model.response.DealResponse;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface DealService {
    Map<String,Object> selectDeal(DealRequest deal);

    List<DealResponse> selectByVin(Map map);

    PageInfo selectInvoice(SelectInvoice invoice) throws ParseException;

    int countInvoice(SelectInvoice invoice);

    List<DealResponse> selectDealExport(DealRequest deal);

    List<Map> selectInvoiceExport(SelectInvoice invoice) throws ParseException;

    int countByVin(String vin);
}
