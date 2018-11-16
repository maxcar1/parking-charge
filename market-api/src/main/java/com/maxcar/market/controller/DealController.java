package com.maxcar.market.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.model.request.DealRequest;
import com.maxcar.market.model.request.SelectInvoice;
import com.maxcar.market.model.response.DealResponse;
import com.maxcar.market.service.DealService;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/api/deal")
public class DealController extends BaseController {

    @Autowired
    DealService dealService;

    @Autowired
    UserTenantService userTenantService;

    @Autowired
    CarBaseService carBaseService;

    @PostMapping("/list")
    //@OperationAnnotation(title = "查询交易记录")
    public InterfaceResult listDeal(@RequestBody DealRequest deal, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        if (user != null) {
            deal.setMarketId(user.getMarketId());
        }
        Map<String, Object> map = dealService.selectDeal(deal);
        int total = (int) map.get("total");
        map.remove("total");
        interfaceResult.setTotal(total);

        List<DealResponse> list = (List<DealResponse>) map.get("list");
        for (DealResponse backDeal : list) {
            double price = backDeal.getPrice();
            backDeal.setPrice(price / 10000);
            double evaluatePrice = backDeal.getEvaluatePrice();
            backDeal.setEvaluatePrice(evaluatePrice / 10000);
            String tenantId = backDeal.getTenantId();
            if (tenantId != null && (!tenantId.equals(""))) {
                UserTenant userTenant = userTenantService.selectByPrimaryKey(tenantId);
                if (userTenant != null) {
                    String tenantName = userTenant.getTenantName();
                    backDeal.setTenantName(tenantName);
                }
            }
            String id = backDeal.getId();
            if (id != null && (!id.equals(""))) {
                CarBase carBase = carBaseService.selectByPrimaryKey(id);
                if (carBase != null) {
                    String brandName = carBase.getBrandName();
                    String seriesName = carBase.getSeriesName();
                    String modelName = carBase.getModelName();
                    backDeal.setSeries(brandName + "-" + seriesName);
                    backDeal.setSeriesName(modelName);
                }

            }
            String registerDay = backDeal.getRegisterDay();
            if (registerDay == null) {
                backDeal.setRegisterDay("");
            }
        }
        interfaceResult.InterfaceResult200(map);
        interfaceResult.setMsg("查询交易记录成功");

        return interfaceResult;
    }

    /**
     * 交易记录  导出
     *
     * @param deal
     * @return
     */
    @PostMapping("/listExport")
    //@OperationAnnotation(title = "查询交易记录  导出表格")
    public InterfaceResult listDealExport(@RequestBody DealRequest deal, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        if (user != null) {
            deal.setMarketId(user.getMarketId());
        }
        List<DealResponse> list = dealService.selectDealExport(deal);

        List<Map> deals = new ArrayList<>();
        HashMap<String, Object> dealMap = null;

        for (DealResponse backDeal : list) {
            double price = backDeal.getPrice();
            backDeal.setPrice(price / 10000);
            double evaluatePrice = backDeal.getEvaluatePrice();
            backDeal.setEvaluatePrice(evaluatePrice / 10000);
            dealMap = new LinkedHashMap<>();

            String id = backDeal.getId();
//                if(id != null && (!id.equals(""))){
//                    dealMap.put("carNo", backDeal.getCarNo());  // 1
//                }else {
//                    dealMap.put("carNo", "");
//                }
            dealMap.put("vin", backDeal.getIvin());   //  2


            //  挂靠车商
            String tenantId = backDeal.getTenantId();
            if (tenantId != null && (!tenantId.equals(""))) {
                UserTenant userTenant = userTenantService.selectByPrimaryKey(tenantId);
                if (userTenant != null) {
                    String tenantName = userTenant.getTenantName();
                    dealMap.put("tenantName", tenantName);  // 3
                } else {
                    dealMap.put("tenantName", "");
                }
            } else {
                dealMap.put("tenantName", "");
            }
            if (id != null && (!id.equals(""))) {
                String isNewCar = backDeal.getIsNewCar();
                if (isNewCar != null && (!"".equals(isNewCar))) {
                    switch (isNewCar) {
                        case "0":
                            dealMap.put("isNewCar", "新车");  //  4
                            break;
                        case "1":
                            dealMap.put("isNewCar", "二手车");  //  4
                            break;
                    }
                } else {
                    dealMap.put("isNewCar", "");
                }

            } else {
                dealMap.put("isNewCar", "");
            }

            dealMap.put("carStatus", backDeal.getCarStatus());  //  5
            dealMap.put("billTime", backDeal.getBillTime());  // 6

            if (id != null && (!id.equals(""))) {
                String stockStatus = backDeal.getStockStatus();
                switch (stockStatus) {
                    case "-1":
                        dealMap.put("stockStatus", "已删除");  // 7
                        break;
                    case "1":
                        dealMap.put("stockStatus", "在场");  // 7
                        break;
                    case "2":
                        dealMap.put("stockStatus", "在内场");  // 7
                        break;
                    case "3":
                        dealMap.put("stockStatus", "出场");  // 7
                        break;
                    case "4":
                        dealMap.put("stockStatus", "售出未出场");  // 7
                        break;
                    case "5":
                        dealMap.put("stockStatus", "售出已出场");  // 7
                        break;
                }
                dealMap.put("registerDay", backDeal.getRegisterDay());  // 8
                dealMap.put("registerTime", backDeal.getRegisterTime());  //  9
            } else {
                dealMap.put("stockStatus", "");
                dealMap.put("registerDay", "");
                dealMap.put("registerTime", "");
            }

            if (id != null && (!id.equals(""))) {
                CarBase carBase = carBaseService.selectByPrimaryKey(id);
                if (carBase != null) {
                    String brandName = carBase.getBrandName();
                    String seriesName = carBase.getSeriesName();
                    String modelName = carBase.getModelName();
                    dealMap.put("series", brandName + "-" + seriesName); //10
                    dealMap.put("seriesName", modelName);  //11
                } else {
                    dealMap.put("series", "");
                    dealMap.put("seriesName", backDeal.getSeriesName());
                }
            } else {
                dealMap.put("series", "");
                dealMap.put("seriesName", backDeal.getSeriesName());
            }

            if (id != null && (!id.equals(""))) {
                dealMap.put("initialLicenceTime", backDeal.getInitialLicenceTime());  // 12
                dealMap.put("mileage", backDeal.getMileage());   //  13
                dealMap.put("evaluatePrice", backDeal.getEvaluatePrice()); //  14
            } else {
                dealMap.put("initialLicenceTime", "");
                dealMap.put("mileage", "");
                dealMap.put("evaluatePrice", "");
            }
            dealMap.put("price", backDeal.getPrice());  //15
            deals.add(dealMap);
        }

        interfaceResult.InterfaceResult200(deals);
        interfaceResult.setMsg("查询交易记录成功");

        return interfaceResult;
    }


    @GetMapping("/dealDetails/{vin}/{curPage}/{pageSize}")
    @OperationAnnotation(title = "根据车辆id查询车辆交易记录")
    public InterfaceResult dealDetails(@PathVariable String vin, @PathVariable Integer curPage, @PathVariable Integer pageSize) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        Map<String, Object> map = new HashMap<>();
        map.put("vin", vin);
        map.put("curPage", curPage);
        map.put("pageSize", pageSize);

        List<DealResponse> list = dealService.selectByVin(map);
        int count = dealService.countByVin(vin);
        interfaceResult.setTotal(count);
        interfaceResult.InterfaceResult200(list);
        interfaceResult.setMsg(" 查询交易记录成功");

        return interfaceResult;
    }

    /**
     * 查询开票记录
     *
     * @param invoice 车架号  商户  开票时间开始到结束  开票号
     * @return
     */
    @PostMapping("/selectInvoice")
//    @OperationAnnotation(title = "根据车辆id查询车辆交易记录")
    public InterfaceResult selectInvoice(@RequestBody SelectInvoice invoice, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        if (user != null) {
            invoice.setMarketId(user.getMarketId());
        }
        PageInfo pageInfo = dealService.selectInvoice(invoice);
//        int total = dealService.countInvoice(invoice);
//        interfaceResult.setTotal(total);
        interfaceResult.InterfaceResult200(pageInfo);
        interfaceResult.setMsg("开票查询成功");

        return interfaceResult;
    }

    /**
     * 查询开票记录  导出
     *
     * @param invoice 车架号  商户  开票时间开始到结束  开票号
     * @return
     */
    @PostMapping("/selectInvoiceExport")
//    @OperationAnnotation(title = "根据车辆id查询车辆交易记录")
    public InterfaceResult selectInvoiceExport(@RequestBody SelectInvoice invoice, HttpServletRequest request) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        if (user != null) {
            invoice.setMarketId(user.getMarketId());
        }
        List<Map> list = dealService.selectInvoiceExport(invoice);
        interfaceResult.InterfaceResult200(list);
        interfaceResult.setMsg("开票查询成功");

        return interfaceResult;
    }

}
