package com.maxcar.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarModel;
import com.maxcar.base.pojo.CarSeries;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.base.util.dasouche.TimeStampUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 大搜车获取 品牌 车系 车型
 *
 * @author
 */
@RequestMapping("/app")
@RestController
public class DaSouCheDataController {

    @Autowired
    private DaSouCheService daSouCheService;
    @Autowired
    private CityService cityService;

    /**
     * 查询所有品牌车系
     *
     * @return
     */
    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    public InterfaceResult listBrands() {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<CarBrand> list = daSouCheService.getAllBrand();
            return isNULL(list);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500(e.getMessage());
            return interfaceResult;
        }
    }

    /**
     * 根据品牌查询所有车系
     *
     * @return
     */
    @RequestMapping(value = "/brands/{scBrandId}/series", method = RequestMethod.GET)
    public InterfaceResult listSeriesByBrand(@PathVariable("scBrandId") String scBrandId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<CarSeries> list = daSouCheService.getAllSeries(scBrandId);
            return isNULL(list);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500(e.getMessage());
            return interfaceResult;
        }
    }

    /**
     * 通过车系查车型
     *
     * @param seriesId
     * @return
     */
    @RequestMapping(value = "/series/{seriesId}/models", method = RequestMethod.GET)
    public InterfaceResult listModelBySeries(@PathVariable("seriesId") String seriesId) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<CarModel> list = daSouCheService.getAllModel(seriesId);
            return isNULL(list);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500(e.getMessage());
            return interfaceResult;
        }
    }

    /**
     * 车型编码获取⻋车型详细配置信息
     *
     * @param scModelCode
     * @return
     */
    @RequestMapping(value = "/models/{scModelCode}", method = RequestMethod.GET)
    public Result detailInfo(@PathVariable("scModelCode") String scModelCode) {
        Result result = new Result();
        try {
            result = daSouCheService.detailInfo(scModelCode);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultCode(500);
            result.setDatas(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/area/{cityCode}", method = RequestMethod.GET)
    public Result getAreas(@PathVariable("cityCode") Integer cityCode) {
        Result result = new Result();
        try {
            result = daSouCheService.getAreas(cityCode);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultCode(500);
            result.setDatas(e.getMessage());
        }
        return result;
    }

    /**
     * 根据vin码去大搜车拿相应的车辆数据
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/car/{vin}", method = RequestMethod.GET)
    public Result getCarListByVin(@PathVariable("vin") String vin) {
        Result result = new Result();
        try {
            result = daSouCheService.getCarListByVin(vin);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultCode(500);
            result.setDatas(e.getMessage());
        }
        return result;
    }

    /**
     * 查询二手车估价
     *
     * @param params
     * @return
     * @throws Exception TODO
     */
    @RequestMapping(value = "/car/price", method = RequestMethod.POST)
    public Result getUsedCarPrice(@RequestBody JSONObject params) throws Exception {
        Result result = new Result();
        String scModelCode = null;
        String scCityCode = null;
        Integer mileage = 0;
        String firstLicenseDate = null;
        Integer rank = 0;
        try {
            scModelCode = params.getString("scModelCode");
            if (null == scModelCode || "".equals(scModelCode)) throw new RuntimeException("scModelCode字段值缺失");
            scCityCode = params.getString("scCityCode");
            if (null == scCityCode || "".equals(scCityCode)) throw new RuntimeException("scCityCode字段值缺失");
            mileage = params.getInteger("mileage");
            if (null == mileage || mileage.intValue() == 0) throw new RuntimeException("mileage字段值缺失");
            firstLicenseDate = params.getString("firstLicenseDate");
            if (null == firstLicenseDate || "".equals(firstLicenseDate))
                throw new RuntimeException("firstLicenseDate字段值缺失");
            rank = params.getInteger("rank");
            if (null == rank || rank.intValue() == 0) {
                rank = 2;//默认为良好
            }
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
            result.setMessage(e.getMessage());
            return result;
        }
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("scModelCode", scModelCode);
        params.put("scCityCode", scCityCode);
        params.put("firstLicenseDate", firstLicenseDate);
        params.put("mileage", mileage);
        params.put("rank", rank);
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = daSouCheService.getAllService(9, params, listKey);
        return result;

    }

    /**
     * 判断是查出的数据是否为空
     *
     * @param object
     * @return
     */
    public InterfaceResult isNULL(Object object) {
        InterfaceResult interfaceResult = new InterfaceResult();
        if (null == object || "".equals(object)) {
            interfaceResult.InterfaceResult600("无查询记录");
        } else {
            interfaceResult.InterfaceResult200(object);
        }
        return interfaceResult;
    }
}
