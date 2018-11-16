package com.maxcar.stock.service.impl;

import java.util.List;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcar.stock.dao.CarPicMapper;
import com.maxcar.stock.pojo.CarPic;
import com.maxcar.stock.service.CarPicService;
@Service("carPicService")
public class CarPicServiceImpl extends BaseServiceImpl<CarPic,String> implements CarPicService {
	@Autowired CarPicMapper carPicMapper;
	@Override
	public List<CarPic> listCarPic(CarPic carPic) {
		return carPicMapper.listCarPic(carPic);
	}

	@Override
	public BaseDao<CarPic, String> getBaseMapper() {
		return carPicMapper;
	}
}
