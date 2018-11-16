package com.maxcar.base.service;

import com.maxcar.base.pojo.Province;

import java.util.List;

/**
 * 省接口
* @ClassName: ProvinceService 
* @author huangxu 
* @date 2018年1月14日 上午11:32:01 
*
 */
public interface ProvinceService {
	List<Province> getProvince();
	Province getProvinceById(Integer id);
}
