package com.maxcar.base.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarModel;
import com.maxcar.base.pojo.CarSeries;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.dasouche.Result;

import java.util.List;
import java.util.Map;

public interface DaSouCheService {

    public Result getAllService(int type, JSONObject params, List<String> listKey);

    CarBrand getCarBrand(String brandCode);

    CarSeries getCarSeries(String seriesCode);

    CarModel getCarModel(String modelCode);
    //根据品牌代码代码获取车系
    List<Map<String,Object>> getCarSeriesByBrandCode(String brandCode) throws Exception;

    //根据车系代码获取车型
    List<Map<String,Object>> getCarModelBySeriesCode(String seriesCode) throws Exception;

    //获取所有品牌
    List<CarBrand> getAllBrand();

    //根据品牌获取车系
    List<CarSeries> getAllSeries(String brandId);

    //根据车系获取车型
    List<CarModel> getAllModel(String seriesId);

    //根据车型码获取车辆详细配置信息
    InterfaceResult getDetailInfo(String scModelCode);

    public Result detailInfo(String scModelCode);

    public Result getCarListByVin(String vin);

    public Result getAreas(Integer cityCode);
}
