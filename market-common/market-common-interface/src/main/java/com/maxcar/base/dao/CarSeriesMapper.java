package com.maxcar.base.dao;

import com.maxcar.base.pojo.CarSeries;

import java.util.List;
import java.util.Map;

public interface CarSeriesMapper {
    CarSeries getCarSeries(String seriesCode);

    List<Map<String,Object>> getCarSeriesByBrandCode(String brandCode) throws Exception;

    List<CarSeries> getAllSeries(String brandId);
}
