package com.maxcar.barrier.service;

import com.maxcar.barrier.pojo.Car;
import com.maxcar.barrier.pojo.CarRecord;

public interface CarService {
    int insertSelective(Car carRecord);
    int updateByExampleSelective(Car carRecord);
    Integer updateCarStatus(Car car);
    Car selectByPrimaryKey(String id);
}
