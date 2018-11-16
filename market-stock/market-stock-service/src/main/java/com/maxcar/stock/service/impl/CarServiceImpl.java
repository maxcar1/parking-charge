package com.maxcar.stock.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.City;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.*;

import com.maxcar.base.util.dasouche.Result;

import com.maxcar.stock.dao.CarBaseMapper;
import com.maxcar.stock.dao.CarMapper;
import com.maxcar.stock.dao.CarPicMapper;
import com.maxcar.stock.entity.CarParams;
import com.maxcar.stock.entity.Request.BarrierListCarRequest;
import com.maxcar.stock.entity.Request.GetCarListByMarketIdAndTenantRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalResponse;
import com.maxcar.stock.entity.Response.BarrierCarListResponse;
import com.maxcar.stock.entity.Response.GetCarListByMarketIdAndTenantResponse;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.service.ConfigurationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.*;

/**
 * @author huangxu
 * @ClassName: CarServiceImpl
 * @Description: 库存服务
 * @date 2018年8月20日 上午11:39:09
 */
@Service("carService")
public class CarServiceImpl extends BaseServiceImpl<Car, String> implements CarService {
    @Autowired
    CarMapper carMapper;
    @Autowired
    CarBaseMapper carBaseMapper;
    @Autowired
    DaSouCheService daSouCheService;
    @Autowired
    CarPicMapper carPicMapper;
    //@Autowired
    // private AreaService areaService;
    @Autowired
    UserTenantService userTenantService;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private CityService cityService;

    protected final Logger logger = Logger.getLogger(CarServiceImpl.class);
    private static String wbUrl="https://yuncheshi.58.com/api/car/third_party/import?";
    @Override
    public BaseDao<Car, String> getBaseMapper() {
        return carMapper;
    }

    @Override
    public int insertSelective(Car record) {
        return carMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Car record) {
        return carMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByVin(Car record) {
        return carMapper.updateByVin(record);
    }

    @Override
    public List<Car> listCar(Car record) {
        return null;
    }

    @Override
    public PageInfo listCarVo(CarVo carVo) {
        PageHelper.startPage(StringUtils.isBlank(carVo.getCurrentPage()) ? 1 : carVo.getCurrentPage(),
                StringUtils.isBlank(carVo.getPageSize()) ? 20 : carVo.getPageSize());
        List<CarVo> listCarVo = carMapper.listCarVo(carVo);
        PageInfo pageInfo = new PageInfo(listCarVo);
        return pageInfo;

    }

    /**
     * param:
     * describe: 库存导出
     * create_date:  lxy   2018/9/11  15:31
     **/
    @Override
    public  List<CarVo> exportCarVo(CarVo carVo) {
        return carMapper.listCarVo(carVo);
/*
        List<CarVo> listCarVo = carMapper.listCarVo(carVo);

        if (null == listCarVo || listCarVo.isEmpty()) {
            return null;
        }

        List<ExportResponse> list = new ArrayList<>(listCarVo.size());

        listCarVo.forEach(x -> {

            ExportResponse response = new ExportResponse();

            response.setCarNo(x.getCarNo());
            response.setVin(x.getVin());
            UserTenant tenant = userTenantService.selectByPrimaryKey(x.getTenant());
            if (null != tenant) {
                response.setTenantName(null == tenant.getTenantName() ? Magic.NUll : tenant.getTenantName());
            } else {
                response.setTenantName(Magic.NUll);
            }

            if (null == x.getIsNewCar()) {
                response.setIsNewCar(Magic.NUll);
            } else {
                String isNewCar = configurationService.getNameByKeyAndValue("is_new_car", x.getIsNewCar().toString());
                response.setIsNewCar(null == isNewCar ? Magic.NUll : isNewCar);
            }

            if (null == x.getStockStatus()) {
                response.setStockStatus(Magic.NUll);
            } else {
                String stockStatus = configurationService.getNameByKeyAndValue("stock_status", x.getStockStatus().toString());
                response.setStockStatus(null == stockStatus ? Magic.NUll : stockStatus);
            }

            if (null == x.getRegisterTime()) {
                response.setStockDay(Magic.NUll);
                response.setRegisterTime(Magic.NUll);
            } else {
                response.setStockDay(String.valueOf(DatePoor.getDatePoorDay(new Date(), x.getRegisterTime())));
                response.setRegisterTime(DatePoor.getStringForDate(x.getRegisterTime()));
            }

            if (null == x.getCarStatus()) {
                response.setCarStatus(Magic.NUll);
            } else {
                String carStatus = configurationService.getNameByKeyAndValue("car_status", x.getStockStatus().toString());
                response.setCarStatus(null == carStatus ? Magic.NUll : carStatus);
            }

            if (null == x.getAreaId()) {
                response.setAreaName(Magic.NUll);
            } else {
                // Area areaById = areaService.getAreaById(x.getAreaId());
                response.setAreaName("");
            }

            response.setInitialLicenceTime(x.getInitialLicenceTime());
            response.setMileage(x.getMileage());
            response.setMarketPrice(x.getMarketPrice());
            response.setEvaluatePrice(x.getEvaluatePrice());
            response.setRemark(x.getRemark());

            response.setSeriesName(x.getSeriesName());
            response.setModelName(x.getModelName());

            list.add(response);

        });

        return list;*/
    }

    @Override
    public int saveCarVO(CarVo carVo) {
        int check = 1;
        try {
            carVo.setId(UuidUtils.getUUID());
            Car car = JSON.parseObject(JSON.toJSONString(carVo), new TypeReference<Car>() {
            });
            CarBaseWithBLOBs carBase = JSON.parseObject(JSON.toJSONString(carVo), new TypeReference<CarBaseWithBLOBs>() {
            });
            //处理图片
            List<CarPic> listCarPic = carVo.getListCarPic();
            if (CollectionUtil.listIsNotEmpty(listCarPic)) {
                for (CarPic carPic : listCarPic) {
                    carPic.setId(UuidUtils.getUUID());
                    carPic.setInsertTime(new Date());
                    carPic.setUpdateTime(new Date());
                    carPic.setUpdateOperator(carPic.getInsterOperator());
                    carPic.setCarId(carVo.getId());
                }
                carPicMapper.insertCarPicList(listCarPic);
            }

            if (carMapper.insertSelective(car) != 1) {
                check = 0;
            } else if (carBaseMapper.insertSelective(carBase) != 1) {
                check = 0;
            }
        } catch (Exception e) {
            check = 0;
        }
        return check;
    }

    @Override
    public Integer selectCountsByCar(Car car) {
        return carMapper.selectCountsByCar(car);
    }

    @Override
    public int updateCarVO(CarVo carVo) {
        int check = 1;
        try {
            Car car = JSON.parseObject(JSON.toJSONString(carVo), new TypeReference<Car>() {
            });
            CarBaseWithBLOBs carBase = JSON.parseObject(JSON.toJSONString(carVo), new TypeReference<CarBaseWithBLOBs>() {
            });
            carMapper.updateByPrimaryKeySelective(car);
            carBaseMapper.updateByPrimaryKeyWithBLOBs(carBase);

            //处理图片
            List<CarPic> listCarPic = carVo.getListCarPic();
            if (CollectionUtil.listIsNotEmpty(listCarPic)) {
                carPicMapper.deleteByCarId(carVo.getId());
                for (CarPic carPic : listCarPic) {
                    carPic.setId(UuidUtils.getUUID());
                    carPic.setInsertTime(new Date());
                    carPic.setUpdateTime(new Date());
                    carPic.setUpdateOperator(carPic.getInsterOperator());
                    carPic.setCarId(carVo.getId());
                }
                carPicMapper.insertCarPicList(listCarPic);
            }


        } catch (Exception e) {
            e.printStackTrace();
            check = 0;
        }
        return check;
    }

    @Override
    public double getSumMarketPrice(CarVo carVo) {
        return carMapper.getSumMarketPrice(carVo);
    }

    @Override
    public Car getCar(Car car) {
        return carMapper.getCar(car);
    }

    @Override
    public CarVo getCarVoById(String carId) {
        return carMapper.getCarVoById(carId);
    }

    @Override
    public Car getCarDetails(Car car) {
        Car cars = new Car();
        try {
            cars = carMapper.getCarDetails(car);
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
        }
        return cars;
    }

    @Override
    public PageInfo<CarVo> listCarChannelVo(CarVo carVo) {
        PageHelper.startPage(carVo.getCurrentPage(), carVo.getPageSize());
        List<CarVo> carVos = carMapper.listCarChannelVo(carVo);
        PageInfo<CarVo> pageInfo = new PageInfo<>(carVos);
        return pageInfo;
    }


    /**
     * 获取市场商品车总数
     * shenzhongzong
     *
     * @return
     */
    @Override
    public int countCarNum(String marketId, String tenantId, String areaName) {
        CarExample example = new CarExample();
        CarExample.Criteria criteria = example.createCriteria();
        //查询有效车辆，库存车辆，商铺车数量
        criteria.andIsvalidEqualTo(1).andStockStatusIn(Constants.stockList).andCarTypeEqualTo(1).andMarketIdEqualTo(marketId);
        if (StringUtil.isNotEmpty(tenantId)) {
            criteria.andTenantEqualTo(tenantId);
        }
        return carMapper.countByExample(example);
//        return carMapper.countCarNum(marketId);
    }


    @Override
    public int countRepertoryByTenantId(String id, String marketId) {
        return carMapper.countRepertoryByTenantId(id, marketId);
    }

    @Override
    public Car selectByRfid(String rfid) {
        CarExample example = new CarExample();
        example.createCriteria().andIsvalidEqualTo(1).andRfidEqualTo(rfid).andStockStatusNotIn(Constants.salesList);
        List<Car> cars = carMapper.selectByExample(example);
        return (cars != null && cars.size() > 0) ? cars.get(0) : null;
    }

    @Override
    public Car selectByVin(String vin) {
        CarExample example = new CarExample();
        //查询费销售状态下，vin码是否存在
        example.createCriteria().andIsvalidEqualTo(1).andVinEqualTo(vin).andStockStatusNotIn(Constants.salesList);
        List<Car> cars = carMapper.selectByExample(example);
        return (cars != null && cars.size() > 0) ? cars.get(0) : null;
    }

    @Override
    public InterfaceResult addCar(CarParams paramsJson) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        Car car = new Car();
        car.setId(paramsJson.getId());
//		CarBase carBase = new CarBase();
        CarBaseWithBLOBs carBaseWithBLOBs = new CarBaseWithBLOBs();
        carBaseWithBLOBs.setId(car.getId());
        car = copyCar(car, paramsJson);

        copyCarBase(carBaseWithBLOBs, paramsJson);
        //1表示商品车，进行图片保存
        if (paramsJson.getType() == 1) {
            copyCarPic(car, paramsJson);
        }
        interfaceResult.InterfaceResult200(car);
        return interfaceResult;
    }

    @Override
    public void deleteCarById(String id) {
        Car car = new Car();
        car.setId(id);
        car.setStockStatus(Constants.deleteType);
//        car.setIsvalid(0);//删除逻辑
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public Integer updateCarStatus(Car car) {
        Car carnew = new Car();
        carnew.setUpdateTime(new Date());
        carnew.setUpdateOperator("100");//自动
        carnew.setStockStatus(car.getStockStatus());
        carnew.setId(car.getId());
        return carMapper.updateByPrimaryKeySelective(carnew);
    }

    public Car copyCar(Car car, CarParams carParams) throws Exception {
        car.setIsvalid(1);
        //从时分秒开始
        car.setVin(carParams.getVin());
        UserTenant userTenant = new UserTenant();
        userTenant = userTenantService.selectByPrimaryKey(carParams.getTenant());
        if (userTenant != null) {
            car.setAreaId(userTenant.getTenantArea());
        }
        //上牌时间
        if (carParams.getInitialLicenceTimeStr() != null && !carParams.getInitialLicenceTimeStr().equals("")) {
            car.setInitialLicenceTime(DateUtils.parseDate(carParams.getInitialLicenceTimeStr(), "yyyy-MM-dd hh:mm:ss"));
        } else {
            car.setInitialLicenceTime(null);
        }
        car.setStockStatus(1);
        car.setCarStatus(carParams.getCarStatus());
        car.setLimitStatus(1);
        car.setRemark(carParams.getRemark());
        car.setAccidentType(carParams.getAccidentType());
        car.setCarType(carParams.getType());
        car.setIsNewCar(carParams.getIsNewCar());//是否新车 0：新车 1：旧车
        car.setRfid(carParams.getRfid());
        car.setStaffId(carParams.getStaffId());
        car.setNo(carParams.getNo());
        car.setOwnerName(carParams.getOwnerName());
        car.setOwnerIdcard(carParams.getOwnerIdcard());//车主姓名
        car.setRemark(carParams.getRemark());//备注
        car.setMarketId(carParams.getMarket());
        if (StringUtils.isNotBlank(carParams.getTenant())) {
            car.setTenant(carParams.getTenant());
        }
        if (carParams.isFlag()) {
            car.setRegisterTime(new Date());//录入时间
            car.setCarNo(carParams.getMarket() + car.getId());
            carMapper.insertSelective(car);
        } else {
            car.setUpdateTime(new Date());
            car.setUpdateOperator("100");
            carMapper.updateByPrimaryKeySelective(car);
        }
        return car;
    }

    public void copyCarBase(CarBaseWithBLOBs car, CarParams carParams) throws Exception {
        car.setBrandCode(carParams.getBrandCode());
        car.setBrandName(carParams.getBrandName());
        car.setModelCode(carParams.getModelCode());
        car.setModelName(carParams.getModelName());
        car.setAccidentType(carParams.getAccidentType());
//		car.setModelYear(carParams.);无
        if (carParams.getInitialLicenceTimeStr() != null && !carParams.getInitialLicenceTimeStr().equals("")) {
            car.setInitialLicenceTime(DateUtils.parseDate(carParams.getInitialLicenceTimeStr(), "yyyy-MM-dd hh:mm:ss"));
        } else {
            car.setInitialLicenceTime(null);
        }
        car.setGearBox(carParams.getAirConditionerControlType());//自动挡类型
//		car.setCheckCompanyId(carParams.getche);
        car.setColor(carParams.getColor());
        car.setSeatNumber(carParams.getSeats());//座位数
//		car.setDaSouChe(carParams.getda);
        car.setEngineVolumeUnitl(carParams.getDisplacement());//排量
        car.setEnvironmentalStandards(carParams.getEnvironmentalStandards());
        car.setEvaluatePrice(carParams.getEvaluatePrice());//估值
        car.setLevel(carParams.getLevel());//尺寸
        car.setSeriesCode(carParams.getSeries());//车系
        car.setSeriesName(carParams.getSeriesName());//车系名称
        car.setMileage(carParams.getMileage());//公里数
        car.setNewPrice(carParams.getNewcarPrice());
        car.setMarketPrice(carParams.getMarketPrice());//市场销售价
        car.setReservePrice(carParams.getReservePrice());//低价
        car.setFuelForm(carParams.getFuelForm());
        String model_code = car.getModelCode();
        InterfaceResult interfaceResult = daSouCheService.getDetailInfo(model_code);
//		car.setDaSouChe(interfaceResult.getData());
        car.setModelData(interfaceResult.getData()+"");//详细信息
        if(carParams.getAttribution() !=null && !carParams.getAttribution().equals("")){

            car.setAttribution(Integer.parseInt(carParams.getAttribution()));//车辆归属地
        }
        if (carParams.isFlag()) {
            carBaseMapper.insertSelective(car);
        } else {
            car.setUpdateTime(new Date());
            car.setUpdateOperator("100");
            carBaseMapper.updateByPrimaryKeySelective(car);
        }
    }
    @Override
    public Map<String, Object> findingCarDetails(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> tenantMap = new HashMap<>();
        UserTenant userTenant=new UserTenant();
        Car car = carMapper.selectByPrimaryKey(id);
        List<ArrayList> lists = new ArrayList<>();
        if (null != car) {
         }
        CarVehicle vehicle = carBaseMapper.selectById(id);

        CarPicExample carPicExample = new CarPicExample();
        carPicExample.createCriteria().andCarIdEqualTo(id);
        List<CarPic> carPicList = carPicMapper.selectByExample(carPicExample);
        vehicle.setTenantId(userTenant.getId());
        vehicle.setTenantName(userTenant.getTenantName());
        vehicle.setTenantPhone(userTenant.getTenantPhone());
        List list = new ArrayList();
        for(CarPic carPic : carPicList ) {
            list.add(carPic.getSrc());
        }
        vehicle.setSrcList(list);
        long libraryAge = DateUtils.daysBetween(vehicle.getRegisterTime(),new Date());
        vehicle.setLibraryAge(libraryAge);
        vehicle.setStockStatus(car.getStockStatus());
        map.put("carVehicle", vehicle);
        return map;
    }
    public void copyCarPic(Car car, CarParams carParams) throws Exception {
        List<HashMap> carVo = JsonTools.toObj(carParams.getPicList(), List.class);
        if(!carParams.isFlag()){
            carPicMapper.deleteByCarId(carParams.getId());
        }
        for (HashMap carsvo : carVo) {
            CarPic carPic = new CarPic();
            carPic.setId(UuidUtils.generateIdentifier());
            carPic.setCarId(car.getId());
            carPic.setSrc(carsvo.get("pic") + "");
            carPic.setType(carsvo.get("type") != null ? Integer.parseInt(carsvo.get("type") + "") : 0);
            carPicMapper.insertSelective(carPic);
           /* if (carParams.isFlag()) {
                carPicMapper.insertSelective(carPic);
            } else {
                CarPic c = new CarPic();
                c.setId(UuidUtils.generateIdentifier());
                c.setCarId(car.getId());
                c.setSrc(carsvo.get("pic") + "");
                c.setType(carsvo.get("type") != null ? Integer.parseInt(carsvo.get("type") + "") : 0);
                carPicMapper.insertSelective(carPic);
            }*/
        }
    }

//	public int getLevelByValue(String value){
//		int level = 1;
//		switch (value){
//			case "SUV":
//				level =1;
//				break;
//			case "MPV":
//				level =4;
//				break;
//			case "微小型车":
//				level =1;
//				break;
//			case "SUV":
//				level =1;
//				break;
//			case "SUV":
//				level =1;
//				break;
//			case "SUV":
//				level =1;
//				break;
//			case "SUV":
//				level =1;
//				break;
//		}
////		车型：1SUV 2三厢轿车 3 两厢轿车 4MVP 5跑车 6面包车 7 皮卡
//	}

    /**
     * param:
     * describe: 获取库存统计信息
     * create_date:  lxy   2018/9/12  11:51
     **/
    @Override
    public InventoryStatisticalResponse inventoryStatistical(InventoryStatisticalRequest request) {
        InventoryStatisticalResponse response = carMapper.inventoryStatistical(request);

        if (null == response) {
            return null;
        }
        int i = (response.getInventoryDayTotal() == 0 || 0 == response.getInventoryNow()) ? 0 : (response.getInventoryDayTotal() / response.getInventoryNow());
        double v = (response.getValuationNow() == 0 || 0 == response.getInventoryNow()) ? 0 : (response.getValuationNow() / response.getInventoryNow());

        response.setAvgDayTotal(i);
        response.setAvgValuationNow(v);

        return response;
    }


    @Override
    public List<TaoBaoCar> getBrand() {
        List<TaoBaoCar> carList = carMapper.getTaoBaoBrand();
        return carList;
    }

    @Override
    public List<TaoBaoCar> getTaoBaoCarByKey(TaoBaoCar taoBaoCar) {
        List<TaoBaoCar> carList = carMapper.getTaoBaoCarByKey(taoBaoCar);
        return carList;
    }

    @Override
    public CarInfo getCarInfoById(String carId) {
        CarInfo carInfo = carMapper.getCarInfoById(carId);
        return carInfo;
    }

    @Override
    public List<CarPic> getCarPic(String id) {
        List<CarPic> stockCarPicList = carMapper.getCarPic(id);
        return stockCarPicList;
    }

    @Override
    public void modifyCarInfo(CarInfo carInfo) {
        carMapper.modifyCarInfo(carInfo);
    }

    @Override
    public InterfaceResult uploadWb(CarInfo carInfo,List<CarPic> picList,String appid,String appSecret) {
        InterfaceResult res = new InterfaceResult();
        JSONObject json = new JSONObject();
        City city = cityService.getCityById(Integer.valueOf(carInfo.getAttribution()));
        String cityName = city.getName().replaceAll("市","");
        json.put("city_name",cityName);
        json.put("brand_name",carInfo.getBrand_name());
        json.put("series_name",carInfo.getSeries_name());
        json.put("model_name",carInfo.getModel_name());
        json.put("sell_price",carInfo.getMarket_price()*10000);
        //json.put("transfer_price","") 过户费
        //json.put("sell_price_ycs",)云车市价格（单位元）
        json.put("is_maintain",0); //是否定期4S店保养（0-未知，1-是，2-否）【未知请传0】
        json.put("is_deal_price",2);//是否包含过户费
        json.put("annual_check_time",new Date());
        json.put("traffic_time",new Date());
        json.put("insurance_time",new Date());
        json.put("license_reg_date",carInfo.getInitial_licence_timeStr());
        json.put("is_imported",0);
        json.put("kilometer",carInfo.getMileage());
        int standard = 0;
        String environment = carInfo.getEnvironmentalStandards().contains("(")?carInfo.getEnvironmentalStandards().substring(0,carInfo.getEnvironmentalStandards().indexOf("(")):carInfo.getEnvironmentalStandards();
        switch (environment){
            case "国I":
                standard=1;
                break;
            case "国II":
                standard=2;
                break;
            case "国III":
                standard=3;
                break;
            case "国IV":
                standard=4;
                break;
            case "国V":
                standard=5;
                break;
            case "欧I":
                standard=11;
                break;
            case "欧II":
                standard=12;
                break;
            case "欧III":
                standard=13;
                break;
            case "欧IV":
                standard=14;
                break;
            case "欧V":
                standard=13;
                break;

        }
        json.put("emission_standard",standard);
        String fuelType = carInfo.getFuel_form();
        int fuel = 0;
        switch (fuelType){
            case "汽油":
                fuel=1;
                break;
            case "柴油":
                fuel=2;
                break;
            case "插电式混合动力":
                fuel=4;
                break;
            case "纯电":
                fuel=3;
                break;
            case "油电混合":
                fuel=4;
                break;
        }
        json.put("fuel_type",fuel);
        json.put("displacement",fuel);
        json.put("vin",carInfo.getVin());
        String box = carInfo.getGearBox();
        int boxType = 0;
        switch (box){
            case "自动":
                boxType=2;
                break;
            case "手动":
                boxType=1;
                break;
        }
        if (box.contains("手自一体")){
            boxType=3;
        }
        if (box.contains("双离合")){
            boxType=4;
        }
        json.put("gear_box", boxType);
        json.put("use_type", 2);
        json.put("remark",carInfo.getModel_name());
        JSONArray imgJson = new JSONArray();
        //imgJson.isEmp
        //主图
        JSONObject mainImgJson = new JSONObject();
        JSONObject otherImgJson = new JSONObject();
        String otherImg = null;
        mainImgJson.put("type",15);
        for (int i =0; i<picList.size();i++){
            if (picList.get(i).getType()==1){
                mainImgJson.put("url",picList.get(i).getSrc());
            }else{
                otherImg+=picList.get(i).getSrc();
                otherImg+="|";
            }
        }
        otherImgJson.put("type",0);
        otherImgJson.put("url",otherImg);
        //其他
        imgJson.add(otherImgJson);
        imgJson.add(mainImgJson);
        json.put("img_url_v1",imgJson);
        json.put("phone",12);
        //处理颜色
        int colorType = dealColor(carInfo.getColor());
        
        json.put("color",colorType);
        json.put("user_type",2);
        json.put("appid",appid);
        json.put("appSecret",appSecret);
        Long time = System.currentTimeMillis()/1000;
        json.put("time",time);
        String sign = MD5Util.MD5(String.valueOf(time)+appSecret).toLowerCase();
        json.put("sign",sign);
        String result = HttpClientUtils.sendPost(wbUrl,json.toString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if(resultJson.get("code").equals(200)||resultJson.get("code")=="200"){
            res.setCode("200");
            JSONObject dataJson = JSONObject.parseObject(resultJson.get("data").toString());
            //接受58返回车id
            res.setData(dataJson.get("carId"));
        }else{
            res.setCode("600");
            res.setMsg(resultJson.get("msg").toString());
        }
        return res;
    }

    @Override
    public Result getAll(DpCar car) {
        Result result = new Result();
        if(car.getPageSize()==null){
            car.setPageSize(car.getCurPage()*10);
            car.setCurPage((car.getCurPage()-1)*10);
        }else{
            car.setPageSize(car.getCurPage()*car.getPageSize());
            car.setCurPage((car.getCurPage()-1)*car.getPageSize());
        }
        result.setDatas(carMapper.getNtCar(car));
        return result;
    }

    @Override
    public List<CarIcon> listCarIcon() {
        return carMapper.listCarIcon();
    }

    private int dealColor(String color) {
        int colorType = 0;
        String colorArray[] = {"黑","白","银","灰","栗","红","蓝","绿","黄","橙","棕","紫","粉","青","香槟","金"};
        for (int i = 0; i <colorArray.length ; i++) {
            if (color.contains(colorArray[i])){
                color=colorArray[i]+"色";
            }
        }
        switch (color){
            case "黑色":
                colorType=1;
                break;
            case "白色":
                colorType=2;
                break;
            case "银色":
                colorType=3;
                break;
            case "灰色":
                colorType=4;
                break;
            case "栗色":
                colorType=5;
                break;
            case "红色":
                colorType=6;
                break;
            case "蓝色":
                colorType=7;
                break;
            case "绿色":
                colorType=8;
            case "黄色":
                colorType=9;
            case "橙色":
                colorType=10;
                break;
            case "棕色":
                colorType=11;
            case "紫色":
                colorType=12;
                break;
            case "金色":
                colorType=13;
                break;
            case "青色":
                colorType=27;
                break;
            case "粉色":
                colorType=26;
                break;
            case "香槟色":
                colorType=28;
                break;
        }
        return  colorType;
    }


    @Override
    public PageInfo getCarList(CarVo carVo) {
        PageHelper.startPage(carVo.getCurrentPage(), carVo.getPageSize());
        List<CarVo> lists = carMapper.selectCarList(carVo);
        if (CollectionUtil.listIsNotEmpty(lists)) {
            for (int i = 0; i < lists.size(); i++) {
                CarPicExample carPicExample = new CarPicExample();
                String id = lists.get(i).getId() + "";
                carPicExample.createCriteria().andCarIdEqualTo(id).andTypeEqualTo(0);
                List<CarPic> carPicList = carPicMapper.selectByExample(carPicExample);
                if (CollectionUtil.listIsNotEmpty(carPicList)) {
                    lists.get(i).setSrc(carPicList.get(0).getSrc());
                }
                UserTenant userTenant = userTenantService.selectByPrimaryKey(lists.get(i).getTenant());
                if (null != userTenant && null != userTenant.getTenantName()) {
                    lists.get(i).setTenantName(userTenant.getTenantName());
                }
            }
        }
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public Map<String, Object> getCarDetail(String id) {
        Map<String, Object> map = new HashMap<>();
        Car car = carMapper.selectByPrimaryKey(id);
        if (null != car) {
            UserTenant userTenant = userTenantService.selectByPrimaryKey(car.getTenant());
            if (null != userTenant) {
                map.put("tenant", userTenant);
            }
        }

        CarBase carBase = carBaseMapper.selectByPrimaryKey(id);
        CarPicExample carPicExample = new CarPicExample();
        carPicExample.createCriteria().andCarIdEqualTo(id);
        List<CarPic> carPicList = carPicMapper.selectByExample(carPicExample);
        map.put("car", car);
        JSONObject json = new JSONObject();
        if (null != carBase) {
            City city = cityService.getCityById(carBase.getAttribution());
            json = (JSONObject) JSONObject.toJSON(carBase);
            if (city != null){
                json.put("cityName", city.getName());
            }
        }
        map.put("carBase", json);
        if (CollectionUtil.listIsNotEmpty(carPicList)) {
            map.put("carPicList", carPicList);
        }
        return map;
    }
    /**
     * param:
     * describe: 根据市场ID和商户ID 查询 库存车 可过户 列表
     * create_date:  lxy   2018/10/15  13:56
     **/
    @Override
    public InterfaceResult getCarListByMarketIdAndTenant(GetCarListByMarketIdAndTenantRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        PageHelper.startPage(request.getCurPage(), request.getPageSize());
        List<GetCarListByMarketIdAndTenantResponse> list = carMapper.getCarListByMarketIdAndTenant(request);

        PageInfo<GetCarListByMarketIdAndTenantResponse> pageInfo = new PageInfo<>(list);
        interfaceResult.InterfaceResult200(pageInfo);

        return interfaceResult;
    }

    /**
     * 道闸黑白名单添加车辆时调用
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo selectCarByTenant(BarrierListCarRequest request) throws Exception {
        PageHelper.startPage(request.getCurrentPage(),request.getPageSize());
        List<BarrierCarListResponse> barrierCarListResponses = carMapper.selectCarByTenant(request);
        PageInfo pageInfo = new PageInfo(barrierCarListResponses);
        return pageInfo;
    }


}