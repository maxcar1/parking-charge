package com.maxcar.base.impl;

import java.util.List;

import javax.annotation.Resource;

import com.maxcar.base.dao.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maxcar.base.pojo.Province;
import com.maxcar.base.service.ProvinceService;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService{

	@Autowired
	private ProvinceMapper provinceMapper;
	
	@Override
	public List<Province> getProvince() {
		return provinceMapper.getProvince();
	}
	@Override
	public Province getProvinceById(Integer id) {
		return provinceMapper.selectByPrimaryKey(id);
	}

}
