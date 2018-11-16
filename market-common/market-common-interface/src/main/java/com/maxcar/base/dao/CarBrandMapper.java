package com.maxcar.base.dao;

import com.maxcar.base.pojo.CarBrand;

import java.util.List;

public interface CarBrandMapper {
    CarBrand getCarBrand(String brandCode);

    List<CarBrand> getAllBrand();
}
