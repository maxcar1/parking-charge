package com.maxcar.market.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.dao.InvoiceMapper;
import com.maxcar.market.model.request.DealRequest;
import com.maxcar.market.model.request.SelectInvoice;
import com.maxcar.market.model.response.DealResponse;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import com.maxcar.market.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("dealService")
public class DealServiceImpl implements DealService {

    @Autowired
    InvoiceMapper invoiceMapper;

    @Override
    public Map<String,Object> selectDeal(DealRequest deal) {
        Integer curPage = deal.getCurPage();
        Integer pageSize = deal.getPageSize();

        if(null == curPage | "".equals(curPage) || curPage.intValue()<=1){
            curPage = 0;
        }else {
            curPage = (curPage-1)*pageSize;
        }

        deal.setCurPage(curPage);

        //  已取消该功能
        String carNo = deal.getCarNo();
        if(carNo != null && (!"".equals(carNo))){
            String trim = carNo.trim();
            deal.setCarNo("%"+ trim + "%");
        }

        String vin = deal.getVin();
        if(vin != null && (!"".equals(vin))){
            String trim = vin.trim();
            deal.setVin("%"+ trim + "%");
        }

        String dealTimeEnd = deal.getDealTimeEnd();

        if(dealTimeEnd != null){
            try {
                Date date = DateUtils.parseDate(dealTimeEnd, "yyyy-MM-dd");
                Date addDay = DateUtils.addDay(date, 1);
                deal.setDealTimeEnd(DateUtils.formatDate(addDay));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<Integer> carStatus = deal.getCarStatus();
        if(carStatus != null && carStatus.size() > 0){
            for(int car : carStatus){
                if(1 == car){
                    deal.setUnsoldCar(1);
                }else if(2 == car){
                    deal.setTenantCar(2);
                }else if(3 == car){
                    deal.setElseCar(3);
                }
            }
        }

        List<DealResponse> dealResponses = invoiceMapper.selectDeal(deal);
        int total = invoiceMapper.countDeal(deal);
        stockStatus(dealResponses);
        //  市场总交易量
        int countDealNum = invoiceMapper.countDealNum(deal);
        //  交易总价值
        Double sumDeal = invoiceMapper.sumDealMoney(deal);
        double sumDealMoney = (null == sumDeal?0:sumDeal) / 10000;
        //  库存车交易量
        int countStockCarNum = invoiceMapper.countStockCarNum(deal);
        //  库存车交易总价值
        Double sumStockCar = invoiceMapper.sumStockCarMoney(deal);

        double sumStockCarMoney = (null == sumStockCar?0:sumStockCar) / 10000;

        //  开票排名
        Double ranking = 0.0;
        String tenantId = deal.getTenantId();
        if(tenantId != null && (!"".equals(tenantId))){
            Map<String, Object> rankingMap = invoiceMapper.selectRanking(tenantId,deal.getMarketId());
            if(rankingMap != null){
                ranking = (Double) rankingMap.get("rownum");
            }
        }

        Hashtable<String, Object> map = new Hashtable<>();
        map.put("ranking",ranking);
        map.put("countDealNum" ,  countDealNum);
        map.put("sumDealMoney" , sumDealMoney );
        map.put("countStockCarNum" ,  countStockCarNum);
        map.put("sumStockCarMoney" ,  sumStockCarMoney);
        map.put("list" ,  dealResponses);
        map.put("total",total);

        return map;
    }

    @Override
    public List<DealResponse> selectByVin(Map map) {
        Integer curPage = (Integer) map.get("curPage");
        Integer pageSize = (Integer) map.get("pageSize");

        if(null == curPage | "".equals(curPage) || curPage.intValue()<=1){
            curPage = 0;
        }else {
            curPage = (curPage-1)*pageSize;
        }
        map.put("curPage",curPage);

        List<DealResponse> list = invoiceMapper.selectByVin(map);
        stockStatus(list);
        return list;
    }

    @Override
    public PageInfo selectInvoice(SelectInvoice invoice) throws ParseException {
        String vin = invoice.getVin();
        String billTimeStart = invoice.getBillTimeStart();
        String billTimeEnd = invoice.getBillTimeEnd();
        String invoiceNo = invoice.getInvoiceNo();
        if(StringUtil.isNotEmpty(vin)){
            vin= vin.trim();
            vin = "%" + vin +"%";
            invoice.setVin(vin);
        }
        if(StringUtil.isNotEmpty(billTimeStart)){
            Date dateEnd = DateUtils.parseDate( billTimeEnd,DateUtils.DATE_FORMAT_DATEONLY);
            Date dayEnd = DateUtils.getDayEnd(dateEnd);
            Date dayStart = DateUtils.parseDate(billTimeStart,DateUtils.DATE_FORMAT_DATEONLY );
            billTimeEnd = DateUtils.formatDate(dayEnd,DateUtils.DATE_FORMAT_DATETIME);
            billTimeStart = DateUtils.formatDate(dayStart,DateUtils.DATE_FORMAT_DATETIME);
            invoice.setBillTimeStart(billTimeStart);
            invoice.setBillTimeEnd(billTimeEnd);
        }
        if(StringUtil.isNotEmpty(invoiceNo)){
            invoiceNo = invoiceNo.trim();
            invoiceNo = "%" + invoiceNo + "%";
            invoice.setInvoiceNo(invoiceNo);
        }
        PageHelper.startPage(invoice.getCurPage(), invoice.getPageSize());
        List<Invoice> invoices = invoiceMapper.selectInvoice2(invoice);
        PageInfo pageInfo = new PageInfo(invoices);
        return pageInfo;
    }

    private void DatePackage(SelectInvoice invoice) {
        String billTimeEnd = invoice.getBillTimeEnd();
        if(billTimeEnd != null){
            try {
                Date date = DateUtils.parseDate(billTimeEnd, "yyyy-MM-dd");
                Date addDay = DateUtils.getDayEnd(date);
                invoice.setBillTimeEnd(DateUtils.formatDate(addDay));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int countInvoice(SelectInvoice invoice) {
        String vin = invoice.getVin();
        if(vin != null && (!"".equals(vin))){
            String trim = vin.trim();
            invoice.setVin("%"+ trim + "%");
        }
        String invoiceNo = invoice.getInvoiceNo();
        if(invoiceNo != null && (!"".equals(invoiceNo))){
            String trim = invoiceNo.trim();
            invoice.setInvoiceNo("%"+ trim + "%");
        }
        //  封装结束查询时间
        DatePackage(invoice);
        return invoiceMapper.countInvoice(invoice);
    }



    @Override
    public List<DealResponse> selectDealExport(DealRequest deal) {
        String carNo = deal.getCarNo();
        if(carNo != null && (!"".equals(carNo))){
            deal.setCarNo("%"+ carNo + "%");
        }

        String vin = deal.getVin();
        if(vin != null && (!"".equals(vin))){
            deal.setVin("%"+ vin + "%");
        }

        List<Integer> carStatus = deal.getCarStatus();
        if(carStatus != null && carStatus.size() > 0){
            for(int car : carStatus){
                if(1 == car){
                    deal.setUnsoldCar(1);
                }else if(2 == car){
                    deal.setTenantCar(2);
                }else if(3 == car){
                    deal.setElseCar(3);
                }
            }
        }

        List<DealResponse> list = invoiceMapper.selectDealExport(deal);

        stockStatus(list);

        List<DealResponse> dealResponses = invoiceMapper.selectDeal(deal);
        stockStatus(dealResponses);
        return list;
    }

    @Override
    public List<Map> selectInvoiceExport(SelectInvoice invoice)  throws ParseException{
        PageInfo pageInfo = selectInvoice(invoice);
        List<Invoice> list = pageInfo.getList();
        List<Map> list1 = new ArrayList<>();
        Map<String, Object> map = null;
        for(Invoice i :list){
            map = new LinkedHashMap<>();
            map.put("vin",i.getVin());
            if(i.getTenantName() == null){
                map.put("tenantName","");
            }else {
                map.put("tenantName",i.getTenantName());
            }
            map.put("invoiceNo",i.getInvoiceNo());
            map.put("billTime",i.getBillTime());
            list1.add(map);
        }
        return list1;
    }

    @Override
    public int countByVin(String vin) {
        return invoiceMapper.countByVin(vin);

    }


    /**
     * 判断交易车辆来源
     * @param list
     */
    private void stockStatus(List<DealResponse> list) {
        for(DealResponse deal : list){
            if(deal.getCvin() != null && deal.getCvin() != ""){
                deal.setCarStatus("库存车");

            }else if(deal.getTenantId() != null && !deal.getTenantId().equals("0") && !deal.getTenantId().equals("") ){
                deal.setCarStatus("挂靠车商");
            } else {
                deal.setCarStatus("非车商");
            }
        }
    }
}
