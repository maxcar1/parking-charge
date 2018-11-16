package com.maxcar.base.impl;


import javax.annotation.Resource;

import com.maxcar.base.dao.ColorMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maxcar.base.pojo.Color;
import com.maxcar.base.service.ColorService;
/**
 * 颜色维护表
* @ClassName: ColorServiceImpl 
* @author huangxu 
* @date 2018年1月20日 上午10:43:43 
*
 */
@Service("colorService")
public class ColorServiceImpl implements ColorService{

	@Autowired
	private ColorMapper colorMapper;
	
	@Override
	public Color getColor(Color color) {
		Color colors = new Color();
		try {
			colors = colorMapper.getColor(color);
		} catch (Exception e) {
			Logger.getRootLogger().error(e,e);
		}
		return colors;
	}
	
	

}
