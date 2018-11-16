package com.maxcar.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcar.stock.dao.CarCheckMapper;
import com.maxcar.stock.pojo.CarCheck;
import com.maxcar.stock.service.CarCheckService;
@Service("carCheckService")
public class CarCheckServiceImpl implements CarCheckService {
	@Autowired
	CarCheckMapper carCheckMapper;
	@Override
	public CarCheck getCarCheck(String carId) {
		return carCheckMapper.getCarCheckByCarId(carId);
	}


	public String getCarCheckByVin(String vin) {
		return carCheckMapper.getCarCheckByVin(vin);
	}

	@Override
	public void insertCarCheck(CarCheck check) {
		carCheckMapper.insertSelective(check);
	}


}
