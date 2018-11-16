package com.maxcar.barrier.service.impl;

import com.maxcar.barrier.dao.CarMapper;
import com.maxcar.barrier.dao.CarRecordMapper;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.barrier.pojo.CarRecord;
import com.maxcar.barrier.service.CarRecordService;
import com.maxcar.barrier.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public int insertSelective(Car carRecord) {
        return carMapper.insertSelective(carRecord);
    }
    @Override
    public Car selectByPrimaryKey(String id){
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Car carRecord) {
        return carMapper.updateByPrimaryKeySelective(carRecord);
    }

    @Override
    public Integer updateCarStatus(Car car) {
        Car carnew = new Car();
        carnew.setStockStatus(car.getStockStatus());
        carnew.setId(car.getId());
        return carMapper.updateByPrimaryKeySelective(carnew);
    }
}
