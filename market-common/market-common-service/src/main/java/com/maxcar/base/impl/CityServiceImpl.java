package com.maxcar.base.impl;

import java.util.List;

import javax.annotation.Resource;

import com.maxcar.base.dao.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maxcar.base.pojo.City;
import com.maxcar.base.service.CityService;
@Service("cityService")
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public List<City> getCity(Integer province) {
		return cityMapper.getCity(province);
//		return (List<City>) dao.findForList("com.maxcar.base.mapper.CityMapper.getCity", province);
	}

	@Override
	public City getCityById(Integer id) {
		return  cityMapper.selectByPrimaryKey(id);
//		return (City) dao.findForObject("com.maxcar.base.mapper.CityMapper.selectByPrimaryKey", id);
	}

	@Override
	public List<City> getAllMarketCity() {
		return  cityMapper.getAllMarketCity();
//		return (List<City>) dao.findForList("com.maxcar.base.mapper.CityMapper.getAllMarketCity", null);
	}

	@Override
	public String getProvinceAndCity(String cityId) {
		return cityMapper.getProvinceAndCity(cityId);
//		return (String)dao.findForObject("com.maxcar.base.mapper.CityMapper.getProvinceAndCity", cityId);
	}

}
