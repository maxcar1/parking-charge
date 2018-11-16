package com.maxcar.stock.controller;


import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.pojo.Magic;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.pojo.Area;
import com.maxcar.market.service.AreaService;
import com.maxcar.stock.entity.Request.RequestStorageCapacity;
import com.maxcar.market.model.request.GetPropertyContractDetailListRequest;
import com.maxcar.market.pojo.PropertyContractDetail;
import com.maxcar.market.service.AreaShopService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.stock.entity.Response.ResponseStorageCapacity;
import com.maxcar.stock.service.CarService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenzhongzong
 * @ClassName: ResponseStorageCapacity
 * @Description: 库容接口
 * @date 2018年8月25日 下午2:16:12
 */

@RequestMapping("/api/StorageCapacity")
@RestController
public class StorageCapacityController extends BaseController {

    @Autowired
    UserTenantService userTenantService;

    @Autowired
    AreaShopService areaShopService;

    @Autowired
    CarService carService;

    @Autowired
    PropertyContractService propertyContractService;

    @Autowired
    AreaService areaService;

    /**
     * 按照区域或商户来查询库容列表
     *
     * @return
     */
    @PostMapping("/AllList")
    public InterfaceResult AllList(HttpServletRequest req, @RequestBody RequestStorageCapacity requestStorageCapacity) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(req);
        //  商户个数
//              int tenantCount = 1;
//              int parkCount = 0;
        String marketId = user.getMarketId();
        int tenantCount = userTenantService.countTenant(marketId,requestStorageCapacity.getTenant(), requestStorageCapacity.getAreaName());
        //  市场总车位数
        GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
        requests.setMarketId(marketId);
        if(StringUtil.isNotEmpty(requestStorageCapacity.getTenant())) {
            requests.setTenantId(requestStorageCapacity.getTenant());
        }
        if(StringUtil.isNotEmpty(requestStorageCapacity.getAreaName())){
            requests.setAreaId(requestStorageCapacity.getAreaName());
        }
        GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse responses = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
        int parkCount = (responses.getCarTotal() == null ? 0 : responses.getCarTotal() );

        //  商品车总库存量
        int carCount = carService.countCarNum(marketId, requestStorageCapacity.getTenant(), requestStorageCapacity.getAreaName());

        int count = userTenantService.countTenantArea(requestStorageCapacity.getMarketId(), requestStorageCapacity.getAreaName());

        //  返回的map
        Map<String, Object> map = new HashMap<>();

        map.put("tenantCount", tenantCount);
        map.put("parkCount", parkCount);
        map.put("carCount", carCount);

        //  查询出所有商户
        Integer pageNum = StringUtils.isBlank(requestStorageCapacity.getCurPage()) ? 1 : Integer.parseInt(requestStorageCapacity.getCurPage());
        Integer pageSize = StringUtils.isBlank(requestStorageCapacity.getPageSize()) ? 10 : Integer.parseInt(requestStorageCapacity.getPageSize());

        if (null == pageNum | "".equals(pageNum) || pageNum.intValue() <= 1) {
            pageNum = 0;
        } else {
            pageNum = (pageNum - 1) * pageSize;
        }
        List<UserTenant> userTenantsList;
        // 回传的对象
        ArrayList<ResponseStorageCapacity> RSCList = new ArrayList<>();
        //  判断商户id是否有值
        if (StringUtils.isNotBlank(requestStorageCapacity.getTenant())) {
            //  查询出所有商户
            userTenantsList = userTenantService.selectAll(pageNum, pageSize, requestStorageCapacity.getTenant(), requestStorageCapacity.getMarketId());
            count = 1;
            //  如果只有区域参数
        } else if (StringUtils.isNotBlank(requestStorageCapacity.getAreaName())) {

            userTenantsList = userTenantService.selectByTenantArea(requestStorageCapacity.getMarketId(), requestStorageCapacity.getAreaName());

            //  如果没有参数
        } else {
            //  查询出所有商户
            userTenantsList = userTenantService.selectAll(pageNum, pageSize, requestStorageCapacity.getTenant(), requestStorageCapacity.getMarketId());
        }

        //  遍历查询出的商户
        selectAll(marketId, requests, userTenantsList, RSCList);

        interfaceResult.setTotal(count);
        map.put("list", RSCList);

        //  剩余总车位数
        int residue = parkCount - carCount;
        //  车位总饱和度
        double pro = 0;
        if (parkCount != 0) {
            pro = ((double) carCount / parkCount);
        }
        double value = new BigDecimal((pro * 100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        String proportion = value + "%";

        map.put("residue", residue);
        map.put("proportion", proportion);

        interfaceResult.InterfaceResult200(map);
        interfaceResult.setMsg("库容列表查询成功");

        return interfaceResult;
    }

    private void selectAll(String marketId, GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest requests, List<UserTenant> userTenantsList, ArrayList<ResponseStorageCapacity> RSCList) throws Exception {
        for (UserTenant userTenant : userTenantsList) {
            //  回传的一列对象
            ResponseStorageCapacity storageCapacity = new ResponseStorageCapacity();
            //  区域名
            storageCapacity.setAreaName(userTenant.getName());
            //  商户名
            storageCapacity.setTenantName(userTenant.getTenantName());

            //  库存数
            int tenantCountNum = carService.countRepertoryByTenantId(userTenant.getId(), marketId);
            storageCapacity.setRepertoryCar(tenantCountNum);

            requests.setTenantId(userTenant.getId());
            GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse tenantIdOrAreaId = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(requests);
            storageCapacity.setExhibition(tenantIdOrAreaId == null ? 0 : tenantIdOrAreaId.getExhibitionNumber());
            storageCapacity.setCarPark(tenantIdOrAreaId == null ? 0 : tenantIdOrAreaId.getCarNumber());
            storageCapacity.setOffice(tenantIdOrAreaId == null ? 0 : tenantIdOrAreaId.getOffice());

            DecimalFormat df1 = new DecimalFormat("0");
            Integer carTotal = tenantIdOrAreaId == null ? 0 : tenantIdOrAreaId.getCarTotal();
            storageCapacity.setParkRemaining(carTotal - storageCapacity.getRepertoryCar());
            if (storageCapacity.getRepertoryCar() == 0) {
                storageCapacity.setParkPercent("0.0%");
            } else {
                if(carTotal == 0){
                    carTotal = 1;
                }
                double v = (double) storageCapacity.getRepertoryCar() / carTotal;
                double value = new BigDecimal(v * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                String pp = value + "%";
                storageCapacity.setParkPercent(pp);
            }
            RSCList.add(storageCapacity);
        }
    }

    /**
     * 查询区域下拉列表
     *
     * @return
     */
    @GetMapping("/Area")
    @OperationAnnotation(title = "查询区域列表")
    public InterfaceResult getArea(HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user=getCurrentUser(request);

        List<Area> areas = areaService.selectAreaList(user.getMarketId());
        interfaceResult.InterfaceResult200(areas);
        interfaceResult.setMsg("查询区域列表成功");

        return interfaceResult;
    }

    /**
     * 根据区域查询所在区域内的商户
     *
     * @return
     */
    @GetMapping("/Tenant/{tenantArea}/{marketId}")
    @OperationAnnotation(title = "查询区域商户")
    public InterfaceResult getTenant(@PathVariable String tenantArea, @PathVariable String marketId,HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        String userMarketId = user.getMarketId();
        List<UserTenant> userTenants = userTenantService.selectAreaByName(userMarketId, tenantArea);

        interfaceResult.InterfaceResult200(userTenants);
        interfaceResult.setMsg("查询区域商户成功");

        return interfaceResult;
    }


    //  封装参数的方法
//    private void tenantList(@RequestBody RequestStorageCapacity RSC, ArrayList<ResponseStorageCapacity> RSCList, UserTenant userTenant) {
//        String id = userTenant.getId();
//
//        //  回传的一列对象
//        ResponseStorageCapacity storageCapacity = new ResponseStorageCapacity();
//
//        //  区域名
//        storageCapacity.setAreaName(userTenant.getName());
//        //  商户名
//        storageCapacity.setTenantName(userTenant.getTenantName());
//
//        //  库存数
//        int tenantCountNum = carService.countRepertoryByTenantId(userTenant.getId(), RSC.getMarketId());
//        storageCapacity.setRepertoryCar(tenantCountNum);
//
////        PCDLR.setTenantId(id);
////        //  所有商户
////        PageInfo<PropertyContractDetail> propertyContractDetailList = propertyContractService.getPropertyContractDetailList(PCDLR);
////        List<PropertyContractDetail> list = propertyContractDetailList.getList();
////        if(list == null){
////            storageCapacity.setParkPercent("0.0%");
////            RSCList.add(storageCapacity);
////            return;
////        }
//
//        //  一个商户的信息
////        for (PropertyContractDetail detail : list) {
////            //  获取物业类型  车位 展厅还是办公室
////            Integer contractCategory = detail.getContractCategory();
////            //  获取区域面积或者停车位数量
////            double areaTotal = detail.getAreaTotal();
////            //  车位
////            if (Magic.CONTRACT_CATEGORY_CAR_SPACE.equals(contractCategory)) {
////                int carPark = storageCapacity.getCarPark();
////                carPark += areaTotal;
////                storageCapacity.setCarPark(carPark);
////                //  展厅
////            } else if (Magic.CONTRACT_CATEGORY_EXHIBITION.equals(contractCategory)) {
////                int exhibition = storageCapacity.getExhibition();
////                exhibition += areaTotal;
////                storageCapacity.setExhibition(exhibition);
////                //  办公室
////            } else if (Magic.CONTRACT_CATEGORY_OFFICE.equals(contractCategory)) {
////                int office = storageCapacity.getOffice();
////                office += areaTotal;
////                storageCapacity.setOffice(office);
////            }
////        }
//
//        double areaTotal = 0;
//        for (PropertyContractDetail detail : list) {
//            areaTotal += detail.getAreaTotal();
//        }
//        int carPark = storageCapacity.getCarPark();
//        areaTotal = areaTotal / Magic.CAR;
//        DecimalFormat df1 = new DecimalFormat("0");
//        String areaPark = df1.format(areaTotal);
//        carPark = (Integer.parseInt(areaPark) + carPark);
//        storageCapacity.setParkRemaining(carPark - storageCapacity.getRepertoryCar());
//        if (storageCapacity.getRepertoryCar() == 0) {
//            storageCapacity.setParkPercent("0.0%");
//        } else {
//            double v = (double) storageCapacity.getRepertoryCar() / carPark;
//            double value = new BigDecimal(v * 100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
//
//            String pp = value + "%";
//            storageCapacity.setParkPercent(pp);
//        }
//        RSCList.add(storageCapacity);
//    }

}
