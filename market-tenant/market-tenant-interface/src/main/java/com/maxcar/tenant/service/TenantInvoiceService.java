package com.maxcar.tenant.service;

import java.text.ParseException;
import java.util.Map;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.tenant.entity.TenantInvoiceRequest;

public interface TenantInvoiceService  {
    Map selectList(TenantInvoiceRequest tenant) throws ParseException;

    Map<String,Object> selectListExport(TenantInvoiceRequest tenant) throws ParseException;

    InterfaceResult selectDetails(String id,Integer CurPage,Integer PageSize);
}
