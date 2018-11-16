package com.maxcar.stock.service;

import com.maxcar.stock.pojo.CarCheck;

public interface CarCheckService {
	CarCheck getCarCheck(String carId);

	String getCarCheckByVin(String vin);

	void insertCarCheck(CarCheck check);
}
