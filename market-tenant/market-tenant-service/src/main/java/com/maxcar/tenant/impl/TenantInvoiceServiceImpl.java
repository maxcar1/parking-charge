package com.maxcar.tenant.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.util.DateUtils;
import com.maxcar.tenant.dao.UserTenantMapper;
import com.maxcar.tenant.entity.InvoiceDetailsResponse;
import com.maxcar.tenant.entity.TenantInvoiceRequest;
import com.maxcar.tenant.entity.TenantInvoiceResponse;
import com.maxcar.tenant.service.TenantInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service("tenantInvoiceService")
public class TenantInvoiceServiceImpl implements TenantInvoiceService{

    @Autowired
    private UserTenantMapper userTenantMapper;

    @Override
    public Map selectList(TenantInvoiceRequest tenant) throws ParseException {
        String time = tenant.getTime();
        int length = time.length();
        Map<String, Object> map = new HashMap<>();
        map.put("marketId",tenant.getMarketId());
        String tenantName = tenant.getTenantName();
        if(tenantName != null){
            tenantName = "%" + tenantName + "%";
        }
        map.put("tenantName",tenantName);
        Map<String, Object> returnMap = new HashMap<>();
        // 按照年查询
        if(length == 4){
            time = time + "-01-01 00:00:00";
            Date firstTime = DateUtils.parseDate(time, DateUtils.DATE_FORMAT_DATETIME);
            Date lastTime = DateUtils.getYearEnd(firstTime);

            String first = DateUtils.formatDate(firstTime);
            String last = DateUtils.formatDate(lastTime);

            map.put("first",first);
            map.put("last",last);
        }else if(length == 7){
            //  按月查询
            time = time + "-01 00:00:00";
            Date firstTime = DateUtils.parseDate(time, DateUtils.DATE_FORMAT_DATETIME);
            Date lastTime = DateUtils.getMonthEnd(firstTime);

            String first = DateUtils.formatDate(firstTime);
            String last = DateUtils.formatDate(lastTime);

            map.put("first",first);
            map.put("last",last);
        }else if(length == 10){
            //  按天查询
            time = time + " 00:00:00";
            Date firstTime = DateUtils.parseDate(time, DateUtils.DATE_FORMAT_DATETIME);
            Date lastTime = DateUtils.getDayEnd(firstTime);

            String first = DateUtils.formatDate(firstTime);
            String last = DateUtils.formatDate(lastTime);

            map.put("first",first);
            map.put("last",last);
        }

        PageHelper.startPage(tenant.getCurPage(),tenant.getPageSize());

        List<TenantInvoiceResponse> list = userTenantMapper.selectTenantInvoice(map);
        String sum = userTenantMapper.selectTenantInvoiceSum(map);
        returnMap.put("sum",sum);

        if (list == null || list.size()==0){
            returnMap.put("total",0);
            returnMap.put("list",null);
            return returnMap;
        }
        PageInfo<TenantInvoiceResponse> pageInfo = new PageInfo(list);
        returnMap.put("total",pageInfo.getTotal());
        returnMap.put("list",pageInfo.getList());
        return returnMap;
    }

    @Override
    public Map<String, Object> selectListExport(TenantInvoiceRequest tenant) throws ParseException {
        return null;
    }

    @Override
    public InterfaceResult selectDetails(String id, Integer CurPage, Integer PageSize) {
        InterfaceResult interfaceResult = new InterfaceResult();
        PageHelper.startPage(CurPage,PageSize);
        Map<String, Object> map = new HashMap<>();
        List<InvoiceDetailsResponse> list = userTenantMapper.selectDetails(id, CurPage, PageSize);
        if (list == null || list.size()==0){
            map.put("total",0);
            map.put("list",null);
            interfaceResult.InterfaceResult200(map);
            return interfaceResult;
        }
        for (InvoiceDetailsResponse invo : list) {
            String carType = invo.getCarType();
            if(carType != null){
	            if(Integer.parseInt(carType) ==Magic.CAR_TYPE_MERCHANDISE_CAR){
	                invo.setCarType("商品车");
	            }
	            if(Integer.parseInt(carType) == Magic.CAR_TYPE_MARKET_CAR){
	                invo.setCarType("市场车");
	            }
	            if(Integer.parseInt(carType) == Magic.CAR_TYPE_TENANT_CAR){
	                invo.setCarType("商户车");
	            }
            } 
        }

        PageInfo<InvoiceDetailsResponse> pageInfo = new PageInfo(list);
        map.put("total",pageInfo.getTotal());
        map.put("list",pageInfo.getList());
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }
}

