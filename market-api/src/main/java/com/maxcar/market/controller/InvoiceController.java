package com.maxcar.market.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.model.request.GetAllTransactionRequest;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoicePurchase;
import com.maxcar.market.pojo.TradeInformation;
import com.maxcar.market.service.InvoicePurchaseService;
import com.maxcar.market.service.InvoiceService;
import com.maxcar.market.service.TransactionService;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.stock.service.CarService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Market;
import com.maxcar.user.service.MarketService;
import com.maxcar.web.aop.OperationAnnotation;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by chiyanlong on 2018/8/24.
 */
@RestController
public class InvoiceController extends BaseController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserTenantService userTenantService;
    @Autowired
    private InvoicePurchaseService invoicePurchaseService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CarBaseService carBaseService;


    /**
     * currentTime 默认传当日，当月，当年
     * status :1:按日查询，2：按月，3：按年
     * 查询统计商户发票 POST请求
     *
     * @return
     */
    @RequestMapping(value = "/invoice/getInvoiceCountList")
    @OperationAnnotation(title = "查询统计商户发票")
    public InterfaceResult getInvoiceCountList(@RequestBody Invoice invoice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        PageInfo pageInfo = invoiceService.selectInvoiceCountList(invoice);
        Invoice totalCounts = invoiceService.selectInvoiceTotalCount();
        Map map = new HashMap();
        map.put("InvoiceList", pageInfo);
        map.put("totalCounts", totalCounts);
        interfaceResult.InterfaceResult200(map);

        return interfaceResult;
    }

    /**
     * tenantId 商户id
     * currentTime 查询时间
     * status(Integer类型) :1:按日查询，2：按月，3：按年
     * 查询商户详情 POST请求
     *
     * @return
     */
    @RequestMapping(value = "/invoice/getInvoiceDetail")
    @OperationAnnotation(title = "查询商户详情")
    public InterfaceResult getInvoiceDetail(@RequestBody Invoice invoice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        PageInfo pageInfo = invoiceService.selectInvoiceDetail(invoice);
        interfaceResult.InterfaceResult200(pageInfo);

        return interfaceResult;
    }

    /**
     * @param invoice
     * @return 开票管理列表
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/getInvoiceList")
    @OperationAnnotation(title = "开票管理列表")
    public InterfaceResult getInvoiceList(@RequestBody Invoice invoice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        PageInfo pageInfo = invoiceService.getInvoiceList(invoice);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    /**
     * @param id 发票id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/getInvoiceDetailById/{id}")
    @OperationAnnotation(title = "查询发票详情")
    public InterfaceResult getInvoiceDetailById(@PathVariable(value = "id") String id) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Invoice invoice = invoiceService.selectInvoiceDetailById(id);//开票信息
        Market market = marketService.selectByPrimaryKey(invoice.getMarketId());//市场信息
        String tenantName = userTenantService.selectByTenanId(invoice.getTenantId());
        invoice.setTenantName(tenantName);
        Map<String, Object> map = new HashMap<>();
        map.put("invoice", invoice);
        map.put("market", market);
        interfaceResult.InterfaceResult200(map);
        return interfaceResult;
    }

    /**
     * @param vin
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/getCar/{vin}")
    @OperationAnnotation(title = "根据vin查询车辆信息")
    public InterfaceResult getCar(@PathVariable(value = "vin") String vin) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Car car = new Car();
        car.setVin(vin);
        Car cars = carService.getCar(car);
        if (null != cars) {
            String tenantName = userTenantService.selectByTenanId(cars.getTenant());
            cars.setTenantName(tenantName);
            interfaceResult.InterfaceResult200(cars);
        } else {
            interfaceResult.InterfaceResult600("查询无此车");
        }
        return interfaceResult;
    }

    /**
     * @param invoice
     * @return 作废发票 传id和invoiceStatus(1:待处理,2:已完成,0:作废)
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/modifyInvoice")
    @OperationAnnotation(title = "作废发票")
    public InterfaceResult modifyInvoice(@RequestBody Invoice invoice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        count = invoiceService.updateByPrimaryKeySelective(invoice);
        if (count > 0) {
            if (null != invoice.getCarId() && null != invoice.getCarStockStatus() && invoice.getCarId() != "") {
                Car car = new Car();
                car.setId(invoice.getCarId());
                car.setStockStatus(invoice.getCarStockStatus());//修改为开票之前的状态
                count = carService.updateByPrimaryKeySelective(car);
            }
            interfaceResult.InterfaceResult200(count);
        } else {
            interfaceResult.InterfaceResult600("作废失败");
        }
        return interfaceResult;
    }

    /**
     * @param invoice
     * @return 开票
     * @throws Exception
     */
    @RequestMapping(value = "/invoice/addInvoice")
    @OperationAnnotation(title = "新增开票")
    public InterfaceResult addInvoice(@RequestBody Invoice invoice) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        invoice.setId(UuidUtils.generateIdentifier());
        invoice.setBillTime(new Date());
        int count = 0;
        count = invoiceService.insertSelective(invoice);
        if (count == 0) {
            interfaceResult.InterfaceResult600("新增失败");
        } else {
            InvoicePurchase invoicePurchase = new InvoicePurchase();
            invoicePurchase.setId(invoice.getInvoicePurchaseId());
            invoicePurchase = invoicePurchaseService.selectByPrimaryKey(invoice.getInvoicePurchaseId());
            invoicePurchase.setPollResidue(invoicePurchase.getPollResidue() - 1);//剩余票号-1
            if (invoicePurchase.getInvoiceEndNo() == Integer.parseInt(invoice.getInvoiceNo())) {
                invoicePurchase.setStatus(2);//购票最后一张
                invoicePurchase.setInvoiceNo(invoicePurchase.getInvoiceEndNo());
            } else {
                invoicePurchase.setInvoiceNo(invoicePurchase.getInvoiceNo() + 1);//当前票号+1
            }
            invoicePurchaseService.updateByPrimaryKeySelective(invoicePurchase);
            if (invoice.getCarSources() == 1) {//库存车
                Car car = new Car();
                car.setId(invoice.getCarId());
                car.setStockStatus(4);//售出未出场状态
                carService.updateByPrimaryKeySelective(car);
            }
            interfaceResult.InterfaceResult200("新增成功");
        }
        return interfaceResult;
    }

    @RequestMapping(value = "/invoice/getAllTransaction")
    @OperationAnnotation(title = "查询交易列表")
    public InterfaceResult getAllTransaction(@RequestBody GetAllTransactionRequest request, HttpServletRequest servletRequest) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        String marketId = getCurrentUser(servletRequest).getMarketId();

//        String marketId = "010";   //   ------------------------------------------------------------------这里市场写死了
        request.setMarketId(marketId);

        PageInfo pageInfo = transactionService.getAllTransaction(request);
        interfaceResult.InterfaceResult200(pageInfo);

        return interfaceResult;
    }

    @RequestMapping(value = "/invoice/getAllTransactionExcel")
    @OperationAnnotation(title = "查询交易列表")
    public InterfaceResult getAllTransactionExcel(@RequestBody GetAllTransactionRequest request, HttpServletRequest servletRequest) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        String marketId = getCurrentUser(servletRequest).getMarketId();

//        String marketId = "010";   //   ------------------------------------------------------------------这里市场写死了\

        request.setMarketId(marketId);

        List<Map> invoices = transactionService.getAllTransactionExcel(request);
        interfaceResult.InterfaceResult200(invoices);

        return interfaceResult;
    }

    @GetMapping("/invoice/getTransactionDetails/{transactionId}")
//    @OperationAnnotation(title = "查询交易详情")
    public InterfaceResult getAllTransaction(@PathVariable("transactionId") String transactionId, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        String marketId = getCurrentUser(request).getMarketId();

//        String marketId = "010";   //   ------------------------------------------------------------------这里市场写死了

        Invoice invoice = transactionService.getTransactionDetails(marketId, transactionId);

        Market market = marketService.getMarket(marketId);

        CarBase carBase = null;
        if(invoice != null){
            String carId = invoice.getCarId();
            carBase = carBaseService.selectByPrimaryKey(carId);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("market", market);
        map.put("invoice", invoice);
        map.put("carBase", carBase);

        interfaceResult.InterfaceResult200(map);

        return interfaceResult;
    }

    @RequestMapping("/invoice/detailsExcel")
    @OperationAnnotation(title = "开票导出Excel")
    public InterfaceResult detailsExcel(@RequestBody Invoice invoice, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        String marketId = getCurrentUser(request).getMarketId();
        invoice.setMarketId(marketId);
        List<TradeInformation> tradeInformations = invoiceService.detailsExcel(invoice);

        interfaceResult.InterfaceResult200(tradeInformations);

        return interfaceResult;
    }








}
