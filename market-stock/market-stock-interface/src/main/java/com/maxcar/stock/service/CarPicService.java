package com.maxcar.stock.service;

import java.util.List;

import com.maxcar.base.service.BaseService;
import com.maxcar.stock.pojo.CarPic;

public interface CarPicService extends BaseService<CarPic,String>{
	List <CarPic> listCarPic(CarPic carPic);
}
