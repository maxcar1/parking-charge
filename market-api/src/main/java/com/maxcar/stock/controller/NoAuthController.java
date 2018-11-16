package com.maxcar.stock.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.City;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.pojo.Province;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.ProvinceService;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.ValidateUtils;
import com.maxcar.market.model.request.GetPropertyContractDetailListRequest;
import com.maxcar.market.pojo.PropertyContractDetail;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.stock.entity.CarChecks;
import com.maxcar.stock.entity.CarParams;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.stock.service.CarPicService;
import com.maxcar.stock.service.CarRecordService;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarTenantVo;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.vo.MarketCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *不需要授权接口
 */
@RestController
@RequestMapping("/common")
public class NoAuthController extends BaseController {
	@Autowired
	CarService carService;
	@Autowired
	CarRecordService carRecordService;


	/**
	 * 本地同步车辆状态到云端
	 * @param car
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/car/updateStatus")
	public InterfaceResult updateStatus(@RequestBody Car car,HttpServletRequest request){
		InterfaceResult interfaceResult = new InterfaceResult();
		int count = carService.updateCarStatus(car);
		int count2 = carRecordService.insertSelective(car.getCarRecord());
		return interfaceResult;
	}

//	/**
//	 * 本地同步车辆出入场数据到云端
//	 * @param carRecord
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/car/saveCarRecord")
//	public InterfaceResult saveCarRecord(@RequestBody CarRecord carRecord,HttpServletRequest request){
//		InterfaceResult interfaceResult = new InterfaceResult();
//		int count = carRecordService.insertSelective(carRecord);
//		return interfaceResult;
//	}
}
