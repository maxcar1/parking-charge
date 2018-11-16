package com.maxcar.stock.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.City;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.pojo.Province;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.ProvinceService;
import com.maxcar.base.util.*;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.model.request.GetPropertyContractDetailListRequest;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.pojo.EleLabelRecord;
import com.maxcar.market.pojo.PropertyContractDetail;
import com.maxcar.market.service.EleLabelRecordService;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆信息同步
 */
@RestController
@RequestMapping("/app")
public class SyncCarController extends BaseController {
	@Autowired
	CarService carService;
	@Autowired
	CarBaseService carBaseService;
	@Autowired
	CarPicService carPicService;
	@Autowired
	StaffService staffService;
	@Autowired
	private PropertyContractService propertyContractService;
	@Autowired
	CarRecordService carRecordService;
	@Autowired
	UserTenantService userTenantService;
	@Autowired
	MarketService marketService;
	@Autowired
	CityService cityService;
	@Autowired
	ProvinceService provinceService;
	@Autowired
	ConfigurationService configurationService;
	@Autowired
	MessageProducerService messageProducerService;
	@Autowired
	EleLabelRecordService eleLabelRecordService;

	@Value("${kafka.producer.topic}")
	private String topic;

	/**
	 * 验证app是否可以录车
	 *
	 * @param carCheck
	 * @return
	 */
	@RequestMapping(value = "/can/add", method = RequestMethod.POST)
	public InterfaceResult canAdd(@RequestBody CarChecks carCheck) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();

//		User user = getCurrentUser(request);
			logger.info("canAdd | params = {}" + carCheck);
			if (null == carCheck) {
				interfaceResult.InterfaceResult600("参数格式错误");
				return interfaceResult;
			}
			if (StringUtils.isEmpty(carCheck.getMarket())) {
				interfaceResult.InterfaceResult600("缺少市场参数");
				return interfaceResult;
			}
			Market m = marketService.getMarket(carCheck.getMarket());
			if (null == m) {
				interfaceResult.InterfaceResult600("市场不存在");
				return interfaceResult;
			}

			if (StringUtils.isEmpty(carCheck.getTenant())) {
				interfaceResult.InterfaceResult600("缺少商户参数");
				return interfaceResult;
			}
			UserTenant tenant = userTenantService.selectByPrimaryKey(carCheck.getTenant());
			if (null == tenant) {
				interfaceResult.InterfaceResult600("商户不存在");
				return interfaceResult;
			}
			Integer inventoryNumber = 0;
            GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest request = new GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest();
			request.setTenantId(carCheck.getTenant());
			request.setMarketId(carCheck.getMarket());
			/*request.setPageSize(100);
			request.setCurPage(1);*/
			/*List<PropertyContractDetail> lists = propertyContractService.getPropertyContractDetailListByTenantId(request);
			for (PropertyContractDetail list : lists) {
				if ((Magic.CONTRACT_CATEGORY_CAR_SPACE).equals(list.getContractCategory())) {
					inventoryNumber += list.getAreaTotal();
				}
				if(Magic.CONTRACT_CATEGORY_EXHIBITION.equals(list.getContractCategory())){
					double carAreaNum = list.getAreaTotal()/Magic.CAR_AREA;
					BigDecimal bd = new BigDecimal(carAreaNum).setScale(0, BigDecimal.ROUND_HALF_UP);
					inventoryNumber += Integer.parseInt(bd.toString());
				}
			}*/

            GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse response = propertyContractService.getCarTotalByMarketIdOrTenantIdOrAreaId(request);
            if(response != null){
                inventoryNumber = response.getCarTotal();//总车位数
				List<Configuration> configurationList = configurationService.searchConfiguration("car_num");
				for(Configuration c:configurationList){
					String value = c.getConfigurationValue();
					if(value.substring(0,1).equals("+")){
						inventoryNumber = inventoryNumber+Integer.parseInt(value.substring(1));
					}
				}
            }
			Car tenantIdCar = new Car();
		    tenantIdCar.setTenant(carCheck.getTenant());
			tenantIdCar.setMarketId(carCheck.getMarket());
			tenantIdCar.setCarType(1);
			Integer counts = 0;
			counts = carService.selectCountsByCar(tenantIdCar);
			Integer upCarnum = (counts == null ? 0 : counts);
			if (inventoryNumber <= upCarnum) {
				interfaceResult.InterfaceResult600("车位总数" + inventoryNumber + ", 已经全部使用，不允许继续录车");
				return interfaceResult;
			}
			interfaceResult.InterfaceResult200("ok");
		return interfaceResult;
	}
	//0录车，1该标签
	@RequestMapping(value = "/car/{type}", method = RequestMethod.POST)
	public InterfaceResult getCar(@RequestBody CarChecks carCheck,@PathVariable("type")Integer type) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();

			Map map;
			if (null == carCheck) {
				interfaceResult.InterfaceResult600("参数格式错误");
				return interfaceResult;
			}
			if (carCheck.getVin().isEmpty() || carCheck.getRfid().isEmpty()) {
				interfaceResult.InterfaceResult600("rfid/vin码缺一不可");
				return interfaceResult;
			}

			if (null != carCheck.getStaffId() && "" != carCheck.getStaffId()) {
				Car staffIdCar = new Car();
				staffIdCar.setStaffId(carCheck.getStaffId());
				staffIdCar.setMarketId(carCheck.getMarket());
				Integer counts = 0;
				counts = carService.selectCountsByCar(staffIdCar);
				Integer value = 2;

				/*List<Configuration> list = configurationService.searchConfigurationByMarketId(carCheck.getMarket());
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getConfigurationKey().equals("staff_car_market") ) {
						value = Integer.parseInt(list.get(i).getConfigurationValue());
					}
					if (list.get(i).getConfigurationKey().equals("staff_car_tenant") ) {
						value = Integer.parseInt(list.get(i).getConfigurationValue());
					}
				}*/
				if (counts >= value) {
					map = new HashMap();
					map.put("msg", "该员工已达绑定上限(" + value + "辆)");
					map.put("remark", 4);
					interfaceResult.InterfaceResult200(map);
					return interfaceResult;
				} else {
					Car carVin = new Car();
					carVin.setVin(carCheck.getVin());
					carVin.setMarketId(carCheck.getMarket());
					Car carRfid = new Car();
					carRfid.setMarketId(carCheck.getMarket());
					carRfid.setRfid(carCheck.getRfid());


					Car carByVin = carService.getCar(carVin);
					Car carByRfid = carService.getCar(carRfid);

					if (null == carByVin && null == carByRfid) {
						map = new HashMap();
						map.put("remark", 5);//新增
						interfaceResult.InterfaceResult200(map);
						return interfaceResult;
					}
					if (null == carByVin && null != carByRfid) {
						map = new HashMap();
						map.put("msg", "该rfid已绑定其他车辆，请更换rfid！");
						map.put("remark", 4);
						interfaceResult.InterfaceResult200(map);
						return interfaceResult;
					}
					if (null != carByVin) {
						if(carByVin.getCarType() ==1){
							interfaceResult.InterfaceResult600("该vin码属于商品车，不能进行员工绑定");
							return interfaceResult;
						}
						if (null != carByVin.getStaffId() && !carByVin.getStaffId().equals(carCheck.getStaffId())) {
							map = new HashMap();
							map.put("msg", "该车辆已被其他员工绑定");
							map.put("remark", 4);
							interfaceResult.InterfaceResult200(map);
							return interfaceResult;
						} else {
							if (carByVin.getRfid().equals(carCheck.getRfid())) {
								map = new HashMap();
								map.put("msg", "请不要重复绑定");
								map.put("remark", 4);
								interfaceResult.InterfaceResult200(map);
								return interfaceResult;
							} else {
								map = new HashMap();
								map.put("msg", "是否更换此rfid");
								map.put("remark", 6);//换绑rfid
								map.put("oldRfid", carByVin.getRfid());
								interfaceResult.InterfaceResult200(map);
								return interfaceResult;
							}
						}
					}
				}
			}
			Car carVin = new Car();
			carVin.setVin(carCheck.getVin());
			carVin.setMarketId(carCheck.getMarket());
			Car carRfid = new Car();
			carRfid.setMarketId(carCheck.getMarket());
			carRfid.setRfid(carCheck.getRfid());

			Car carByVin = carService.getCar(carVin);
			Car carByRfid = carService.getCar(carRfid);

			if (null == carByVin && null == carByRfid) {
				map = new HashMap();
				map.put("msg", "当前车辆未入库，是否新增？");
				map.put("remark", 0);
				interfaceResult.InterfaceResult200(map);
				return interfaceResult;
			}
			//修改车辆rfid验证类型
			if ( null != carByRfid) {
				map = new HashMap();
				map.put("msg", "该rfid已绑定其他车辆，请更换rfid！");
				map.put("remark", 3);
				interfaceResult.InterfaceResult200(map);
				return interfaceResult;
			}

			if (null != carByVin) {
				if (null == carByVin.getRfid() || "".equals(carByVin.getRfid())) {
					map = new HashMap();
					map.put("msg", "是否要绑定该rfid?");
					map.put("remark", 1);
					interfaceResult.InterfaceResult200(map);
					return interfaceResult;
				} else if (null != carByVin.getRfid() || !("".equals(carByVin.getRfid()))) {
					map = new HashMap();
					map.put("msg", "当前车辆已经绑定RFID标签，是否要更换？");
					map.put("remark", 2);
					map.put("oldRfid", carByVin.getRfid());
					interfaceResult.InterfaceResult200(map);
					return interfaceResult;
				}
			}

		/*interfaceResult.InterfaceResult200("ok");*/
		return interfaceResult;
	}

	/**
	 * 新增库存
	 * @param paramsJson
	 * @return
	 */
	@PostMapping("/add/car")
	public InterfaceResult addCar(@RequestBody @Valid CarParams paramsJson, BindingResult result, HttpServletRequest request) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();

			User user = super.getCurrentUser(request);
			//1表示商品车进行参数验证
			if(paramsJson.getType()==1){
				//参数验证
				if (result.hasErrors()) {
					for (ObjectError error : result.getAllErrors()) {
						interfaceResult.InterfaceResult600(error.getDefaultMessage());
						return interfaceResult;
					}
				}
			}
			if(StringUtil.isNotEmpty(paramsJson.getId())) {
				paramsJson.setFlag(false);
			}else{
				if(StringUtils.isNotBlank(paramsJson.getVin())){
					Car car = new Car();
					car.setVin(paramsJson.getVin());
					Car c = carService.getCar(car);
					if(c!=null){
						interfaceResult.InterfaceResult600("vin码已存在");
						return interfaceResult;
					}
					if(StringUtils.isNotBlank(paramsJson.getRfid())){
						car = carService.selectByRfid(paramsJson.getRfid());
						if(car!=null){
							interfaceResult.InterfaceResult600("rfid标签已存在");
							return interfaceResult;
						}
					}
				}
				paramsJson.setFlag(true);//新增
				paramsJson.setId(UuidUtils.generateIdentifier());//设置id
			}
			interfaceResult = carService.addCar(paramsJson);
			String topic = topicService.getTopic(user.getMarketId());
			//同步删除本地车辆状态
			//组装云端参数
			PostParam postParam = new PostParam();
			postParam.setData(JsonTools.toJson(interfaceResult.getData()));
			postParam.setMarket(user.getMarketId());
			postParam.setUrl("/barrier/car/saveCar");
			postParam.setOnlySend(false);
			postParam.setMessageTime(Constants.dateformat.format(new Date()));
			messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

		return interfaceResult;
	}

	/**
	 * 查询市场员工或商户员工(staffType:1市场员工，2：商户员工)
	 *
	 * @param staff
	 * @return
	 */
	@RequestMapping(value = "/staffList")
	public InterfaceResult staffList(@RequestBody Staff staff) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();

			List<Staff> list = staffService.selectByExample(staff);
			if (list.size() > 0) {
				interfaceResult.InterfaceResult200(list);
			} else {
				if(staff.getStaffType()==1){
					interfaceResult.InterfaceResult600("该市场不存在市场员工");
				}else{
					interfaceResult.InterfaceResult600("该商户暂无员工");
				}

			}

/*		interfaceResult.InterfaceResult403();*/
		return interfaceResult;
	}

	/**
	 * 车辆编辑查询车辆是否存在
	 *
	 * @param carCheck
	 * @return
	 */
	@RequestMapping(value = "/get/car", method = RequestMethod.POST)
	public InterfaceResult getCarInfoByUnique(@RequestBody CarChecks carCheck) {
		InterfaceResult interfaceResult = new InterfaceResult();
		// 映射车辆对象
		Car car = new Car();
		if (null != carCheck.getRfid() && "" != carCheck.getRfid()) {
			car.setRfid(carCheck.getRfid());
		} else if (null != carCheck.getVin() && "" != carCheck.getVin()) {
			car.setVin(carCheck.getVin());
		} else if (null != carCheck.getCarNo() && "" != carCheck.getCarNo()) {
			car.setCarNo(carCheck.getCarNo());
		}
		car.setMarketId(carCheck.getMarket());
		// 查找该车信息
		Car carTemp = carService.getCar(car);
		if (null == carTemp) {
			interfaceResult.InterfaceResult600("无此车辆信息");
			return interfaceResult;
		} else {
			CarBase carBase = carBaseService.selectByPrimaryKey(carTemp.getId());
			UserTenant userTenant = userTenantService.selectByPrimaryKey(carTemp.getTenant());
			if( userTenant== null){
				interfaceResult.InterfaceResult600("商户不存在，无法查询");
				return interfaceResult;
			}else{
				carBase.setTenantName(userTenant.getTenantName());
			}
			CarPic carPicTemp = new CarPic();
			carPicTemp.setCarId(carTemp.getId());
			List<CarPic> picList = carPicService.listCarPic(carPicTemp);
			carTemp.setCarPic(picList);
			Map map = new HashMap<>();
			//华港挂起状态存放redis,值为1挂起状态，0未挂起
//			String hangup = RedisUtil.getInstance().strings()
//					.get(carTemp.getId()+"_"+carTemp.getMarket());
//			if (StringUtils.equals("1", hangup)) {
//				carTemp.setHuagangStatus(1);
//			}else {
//				carTemp.setHuagangStatus(0);
//			}
			map.put("car", carTemp);
			map.put("carBase", carBase);
			interfaceResult.InterfaceResult200(map);
			return interfaceResult;
		}
	}

	/**
	 * 验证参数是否准确
	 *
	 * @param carParams
	 * @return
	 */
	@PostMapping("/checkParams")
	public InterfaceResult checkParams(@RequestBody CarParams carParams, BindingResult result) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();

			// 操作人的校验处理

			//参数验证
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					interfaceResult.InterfaceResult600(error.getDefaultMessage());
					return interfaceResult;
				}
			}

			if (false == ValidateUtils.isVin(carParams.getVin())) {
				interfaceResult.InterfaceResult600("Vin码必须是17位数字字母组合");
				return interfaceResult;
			}
			Car car = new Car();
			car.setVin(carParams.getVin());
			car.setMarketId(carParams.getMarket());
			Car car2 = carService.getCar(car);
			if (null != car2) {
				interfaceResult.InterfaceResult600("Vin码已经存在,请核实");
				return interfaceResult;
			}
			// 判断若传了RFID RFID不能有重复
			if (null != carParams.getRfid() && !("".equals(carParams.getRfid()))) {
				Car rfidCar = new Car();
				rfidCar.setRfid(carParams.getRfid());
				Car car3 = carService.getCar(rfidCar);
				if (null != car3) {
					interfaceResult.InterfaceResult600("此Rfid正在被使用,请核实");
					return interfaceResult;
				}
			}
			if (null != carParams.getNo() && !"".equals(carParams.getNo()) && false == ValidateUtils.isCarNo(carParams.getNo())) {
				interfaceResult.InterfaceResult600("车牌号不符合规则,例：京AJ13245");
				return interfaceResult;
			}
			// 市场id任何类型车都必须
			Market m = marketService.getMarket(carParams.getMarket());
			if (null == m) {
				interfaceResult.InterfaceResult600("所属市场不存在,请核实");
				return interfaceResult;
			}

			if (carParams.getType() > 3 || carParams.getType() < 1) {
				interfaceResult.InterfaceResult600("所属车辆类别未知,请核实");
				return interfaceResult;
			}
			// 商户所属车
			else if (carParams.getType() == 1 || carParams.getType() == 3) {
				// 校验所选商户是否为空以及是否存在
				UserTenant tenant = userTenantService.selectByPrimaryKey(carParams.getTenant());
				if (null == tenant) {
					interfaceResult.InterfaceResult600("所属商户不存在");
					return interfaceResult;
				}
				// 判断商户所在市场是否和选择市场一致
				if (!(tenant.getMarketId().equals(carParams.getMarket()))) {
					interfaceResult.InterfaceResult600("所选市场没有该商户,请核实");
					return interfaceResult;
				}
			}
			// 判断是否选择了车辆在场状态 1未入场 2入场
			if (carParams.getStatus() != 0) {
				if (carParams.getStatus() != 1 && carParams.getStatus() != 2) {
					interfaceResult.InterfaceResult600("车辆在场状态不合要求,只能是入场或未入场!");
					return interfaceResult;
				}
			}
			interfaceResult.InterfaceResult200("参数验证成功");
			return interfaceResult;

	}

	/**
	 * 换绑rfid和修改车辆信息验证
	 *
	 * @param carCheck
	 * @return
	 */
	@RequestMapping(value = "/can/modify", method = RequestMethod.POST)
	public InterfaceResult canModify(@RequestBody CarChecks carCheck) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();
		logger.info("canAdd | params = {}" + carCheck);
		if (null == carCheck) {
			interfaceResult.InterfaceResult600("参数格式错误");
			return interfaceResult;
		}
		if (StringUtils.isEmpty(carCheck.getMarket())) {
			interfaceResult.InterfaceResult600("缺少市场参数");
			return interfaceResult;
		}
		Market m = marketService.getMarket(carCheck.getMarket());
		if (null == m) {
			interfaceResult.InterfaceResult600("市场不存在");
			return interfaceResult;
		}
		if (StringUtils.isEmpty(carCheck.getTenant())) {
			interfaceResult.InterfaceResult600("缺少商户参数");
			return interfaceResult;
		}
		UserTenant tenant = userTenantService.selectByPrimaryKey(carCheck.getTenant());
		if (null == tenant) {
			interfaceResult.InterfaceResult600("商户不存在");
			return interfaceResult;
		}
		interfaceResult.InterfaceResult200("ok");
		return interfaceResult;
	}

	/**
	 * 修改RFID记录
	 *
	 * @param carRfidRecord
	 * @return
	 */
	@RequestMapping("/modify/rfid")
	public InterfaceResult addRfidRecord(@RequestBody CarRfidRecord carRfidRecord,HttpServletRequest request) throws Exception {
	InterfaceResult interfaceResult = new InterfaceResult();

			User user = super.getCurrentUser(request);
/*			JSONObject jo = KafkaUtil.removeJsonNull(params);
			Result result = messageProducerService.sendMarket("equipRecordService", "addRfidRecord", jo);
			return result;
			*/
//			CarRfidRecord rfidRecord = (CarRfidRecord)JSONObject.toBean(params, CarRfidRecord.class);
			// 操作人的校验处理
//			String requestUser = null;
//			try {
//				requestUser = params.getString("requestUser");
//				if(StringUtils.isBlank(requestUser)){
//					interfaceResult.InterfaceResult600("用户id不能为空");
//					return interfaceResult;
//				}
//				UserKey u = new UserKey();
//				u.setId(requestUser);
//				User userByIdUsername = userMarketService.getUserByIdUsername(u);
//				if(null == userByIdUsername) throw new RuntimeException("");
//			} catch (Exception e) {
//				interfaceResult.InterfaceResult600("用户身份校验失败");
//				return interfaceResult;
//			}


			//校验vin码和Rfid
			Car car = new Car();
			int count = 0;
			//参数校验
			if (StringUtils.isNotBlank(carRfidRecord.getVin())) {
				Car c = new Car();
				c.setVin(carRfidRecord.getVin());
				Car car1 = carService.getCar(c);
				if(car1 != null){
					if (StringUtils.isNotBlank(carRfidRecord.getNewRfid())) {
						//验证新的rfid是否被占用
						Car c2 = new Car();
						c2.setRfid(carRfidRecord.getNewRfid());
						Car car2 = carService.getCar(c2);
						if (null != car2) {
							interfaceResult.InterfaceResult600("新rfid已使用，请更换rfid");
							return interfaceResult;
						}
						if(StringUtils.isNotBlank(carRfidRecord.getTenant())){
							if(!carRfidRecord.getTenant().equals(car1.getTenant())){
								interfaceResult.InterfaceResult600("绑定商户有误，请核实！");
								return interfaceResult;
							}
						}
						car.setRfid(carRfidRecord.getNewRfid());
						car.setId(car1.getId());
						count = carService.updateByPrimaryKeySelective(car);
						if(count>0){
							EleLabelRecord eleLabelRecord = new EleLabelRecord();
							eleLabelRecord.setId(UuidUtils.generateIdentifier());
							eleLabelRecord.setNewRfid(carRfidRecord.getNewRfid());
							eleLabelRecord.setVin(car1.getVin());
							eleLabelRecord.setOldRfid(car1.getRfid());
							eleLabelRecord.setCarNumber(car1.getCarNo());
							eleLabelRecord.setMarketId(carRfidRecord.getMarket());
							eleLabelRecord.setOwnedTenant(car1.getTenant());
							eleLabelRecord.setOperationTime(new Date());
							User user1 = getCurrentUser(request);
							eleLabelRecord.setOperator(user1.getUserId());
							eleLabelRecord.setInsertOperator(user1.getUserId());
							int i = eleLabelRecordService.insertEleLabelRecord(eleLabelRecord);
							if(i>0){
								interfaceResult.InterfaceResult200("修改成功");
							}
							//同步删除本地车辆状态
							String topic = topicService.getTopic(user.getMarketId());
							//组装云端参数
							PostParam postParam = new PostParam();
							postParam.setData(JsonTools.toJson(car));
							postParam.setMarket(user.getMarketId());
							postParam.setUrl("/barrier/car/updateCarRfid");
							postParam.setOnlySend(false);
							postParam.setMessageTime(Constants.dateformat.format(new Date()));
							messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
						}
					}
				}
				return interfaceResult;
			}

			/*//修改车商
			if(!StringUtils.isBlank(carRfidRecord.getTenant())){
				Car car5 = new Car();
				car5.setId(carRfidRecord.getId());
				car5.setTenant(carRfidRecord.getTenant());
				count = carService.updateByPrimaryKeySelective(car5);
				if(count>0){
					interfaceResult.InterfaceResult200("修改成功");
				}
				return interfaceResult;
			}*/
			/*//修改库存状态
			if(tIsNotEmpty(carRfidRecord.getStockStatus())){
				Car car6 = new Car();
				car6.setId(carRfidRecord.getId());
				car6.setStockStatus(carRfidRecord.getStockStatus());
				count = carService.updateByPrimaryKeySelective(car6);
				if(count>0){
					interfaceResult.InterfaceResult200("修改成功");
				}
				return interfaceResult;
			}*/
//			PostParam params = new PostParam();
//			params.setUrl("/modify/add");
//			params.setOnlySend(false);
//			params.setMarket(carRfidRecord.getMarketId());
//			params.setData(carRfidRecord);
//			params.setTableName("car");

			return interfaceResult;


	}

//	public void syncDownload(PostParam postParam){
//		//同步删除本地车辆状态
//		//组装云端参数
//		postParam.setOnlySend(false);
//		postParam.setMessageTime(Constants.dateformat.format(new Date()));
//		messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
//	}
	/**
	 *分页出厂记录(近一个月)
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/car/carRecordList")
	public InterfaceResult getEntryRecordList(@RequestBody CarRecord carRecord, HttpServletRequest request) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();
		PageInfo pageInfo = carRecordService.getCarRecordList(carRecord);
		interfaceResult.InterfaceResult200(pageInfo);
		return interfaceResult;
	}

	/**
	 * 车架号、PFID、车牌查询
	 * @param car
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/car/carDetails")
	public InterfaceResult getEntryRecordList(@RequestBody Car car, HttpServletRequest request) throws Exception {
		InterfaceResult interfaceResult = new InterfaceResult();
		Car carDetails = carService.getCarDetails(car);
		if(carDetails == null){
			interfaceResult.InterfaceResult600("该车辆不存在");
			return interfaceResult;
		}
		CarTenantVo carTenantVo = new CarTenantVo();
		if (carDetails!=null){
			if(carDetails.getStaffId() != null){
				Staff staff = staffService.selectByPrimaryId(carDetails.getStaffId());
				carTenantVo.setStaffName(staff.getStaffName());
				carTenantVo.setStaffPhone(staff.getStaffPhone());
			}
			UserTenant tenantDetailes =userTenantService.selectByPrimaryKey(carDetails.getTenant());
			if(tenantDetailes != null){
				carTenantVo.setName(tenantDetailes.getTenantName());
				carTenantVo.setPhone(tenantDetailes.getTenantPhone());
			}
			carTenantVo.setCarNo(carDetails.getCarNo());
			carTenantVo.setVin(carDetails.getVin());
			carTenantVo.setNo(carDetails.getNo());
			carTenantVo.setRegisterTime(carDetails.getRegisterTime());
			carTenantVo.setType(carDetails.getCarType());
			carTenantVo.setRfid(carDetails.getRfid());
			carTenantVo.setStatus(carDetails.getCarStatus());
			carTenantVo.setStockStatus(carDetails.getStockStatus());
			interfaceResult.InterfaceResult200(carTenantVo);
		}
		return interfaceResult;
	}

	/**
	 * 根据市场ID获取省份及城市信息
	 * @param market
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/car/province")
	public InterfaceResult getProvinceDetails(@RequestBody Market market,HttpServletRequest request) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();
		Market marketDetails = marketService.getMarket(market.getId());
		if(marketDetails == null){
			interfaceResult.InterfaceResult500("该市场不存在");
		}
		City cityDetails = cityService.getCityById(marketDetails.getCity());
		if(cityDetails == null){
			interfaceResult.InterfaceResult500("不存在城市");
		}
		Province province = provinceService.getProvinceById(cityDetails.getProvince());
		if(province == null){
			interfaceResult.InterfaceResult500("不存在省份");
		}
		MarketCity marketCity = new MarketCity();
		marketCity.setMarketName(marketDetails.getName());
		marketCity.setProvinceName(province.getName());
		marketCity.setCityName(cityDetails.getName());
		interfaceResult.InterfaceResult200(marketCity);
		return interfaceResult;
	}

	/**
	 * 查找市场员工或车商员工
	 * @param staffId
	 * @param request
	 * @return
	 */
	@GetMapping("/staffDetails/{staffId}")
	public InterfaceResult getStaffDetails(@PathVariable("staffId") String staffId, HttpServletRequest request) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();
		Staff staff = staffService.selectByPrimaryId(staffId);
		if(staff == null){
			interfaceResult.InterfaceResult500("该员工不存在");
		}
		interfaceResult.InterfaceResult200(staff);
		return interfaceResult;
	}

	/**
	 * 修改商户或VIN
	 * @param carTenantVo
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyTenant")
	public InterfaceResult modifyTenant(@RequestBody CarTenantVo carTenantVo,HttpServletRequest request) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();
		User user = super.getCurrentUser(request);
		if (StringUtils.isNotBlank(carTenantVo.getTenant())) {
			UserTenant tenant = userTenantService.selectByPrimaryKey(carTenantVo.getTenant());
			if (null == tenant) {
				interfaceResult.InterfaceResult600("商户不存在");
				return interfaceResult;
			}
			Car car = new Car();
			car.setId(carTenantVo.getCarId());
			car.setTenant(carTenantVo.getTenant());
			int count = carService.updateByPrimaryKeySelective(car);
			if(count>0){
				interfaceResult.InterfaceResult200("修改成功");
			}else{
				interfaceResult.InterfaceResult500("操作失败");
			}
		}

		if(StringUtils.isNotBlank(carTenantVo.getVin())){
			Car car = new Car();
			car.setVin(carTenantVo.getVin());
			Car c = carService.getCar(car);
			if(c!=null){
				interfaceResult.InterfaceResult500("该vim码已存在，请更换");
				return interfaceResult;
			}
			/*Car car1 = new Car();
			car1.setId(carTenantVo.getCarId());
			car1.setVin(carTenantVo.getVin());
			int num = carService.updateByPrimaryKeySelective(car1);
			if(num>0){
				interfaceResult.InterfaceResult200("修改成功");
			}else{
				interfaceResult.InterfaceResult500("操作失败");
			}*/
//            String topic = super.consumerTopic7;
//            switch (user.getMarketId()) {
//                case "006":
//                    topic = super.consumerTopic6;
//                    break;
//                case "007":
//                    topic = super.consumerTopic7;
//                    break;
//                case "008":
//                    topic = super.consumerTopic8;
//                    break;
//                case "010":
//                    topic = super.consumerTopic10;
//                    break;
//            }
//            //同步删除本地车辆状态
//            //组装云端参数
//            PostParam postParam = new PostParam();
//            postParam.setData(JsonTools.toJson(car1));
//            postParam.setMarket(user.getMarketId());
//            postParam.setUrl("/barrier/car/saveCar");
//            postParam.setOnlySend(false);
//            postParam.setMessageTime(Constants.dateformat.format(new Date()));
//            messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
            }
        return interfaceResult;
	}
}
