package com.maxcar.stock.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.util.*;
import com.maxcar.base.util.dasouche.HttpClientUtil;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.market.pojo.Area;
import com.maxcar.market.service.AreaService;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.stock.entity.Request.BarrierListCarRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalResponse;
import com.maxcar.stock.entity.Response.BarrierCarListResponse;
import com.maxcar.stock.entity.Response.ExportResponse;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.service.*;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.web.aop.OperationAnnotation;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemUpdateDelistingRequest;
import com.taobao.api.request.ItemUpdateListingRequest;
import com.taobao.api.response.ItemUpdateDelistingResponse;
import com.taobao.api.response.ItemUpdateListingResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author huangxu
 * @ClassName: CarController
 * @Description: 库存接口
 * @date 2018年8月16日 下午7:42:13
 */
@RestController
@RequestMapping("/api/car")
public class CarController extends BaseController {
    @Autowired
    CarService carService;
    @Autowired
    CarBaseService carBaseService;
    @Autowired
    CarPicService carPicService;
    @Autowired
    CarRecordService carRecordService;
    @Autowired
    CarCheckService carCheckService;
    @Autowired
    UserTenantService userTenantService;
    @Autowired
    DaSouCheService daSouCheService;
    @Autowired
    MessageProducerService messageProducerService;
    @Autowired
    private CarChannelService carChannelService;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ParkingFeeService parkingFeeService;



    /**
     * 获取库存列表
     * （因为参数过多，使用post）
     *
     * @param carVo
     * @return
     */
    @PostMapping("/list")
    InterfaceResult listCar(@RequestBody CarVo carVo, HttpServletRequest request) throws Exception {

        User user = getCurrentUser(request);
        if (null == user || user.getMarketId().isEmpty()) {
            return getInterfaceResult("200", "无法确认用户市场");
        }

        carVo.setMarketId(user.getMarketId());

        InterfaceResult result = new InterfaceResult();
        carVo.setCarType(1);
        carVo.setVin((carVo.getVin() == null || carVo.getVin().isEmpty()) ? null : carVo.getVin().trim());
        carVo.setCarNo((carVo.getCarNo() == null || carVo.getCarNo().isEmpty()) ? null : carVo.getCarNo().trim());


        PageInfo pageInfo = carService.listCarVo(carVo);
        List<CarVo> listCarVo = pageInfo.getList();
        Map m = new HashMap();
        if (CollectionUtil.listIsNotEmpty(listCarVo)) {
            int outStock = 0;
            int deleteStock = 0;
            for (CarVo car : listCarVo) {
                if (car != null && car.getTenant() != null) {
                    UserTenant tenant = userTenantService.selectByPrimaryKey(car.getTenant());
                    if (tenant != null) {
                        car.setTenantName(tenant.getTenantName());
                    }
                }
               /* if (car != null && StringUtils.isNotBlank(car.getId())) {
                    //查询图片
                    CarPic carPic = new CarPic();
                    carPic.setCarId(car.getId());
                     car.setListCarPic(carPicService.listCarPic(carPic));
                }*/
                if (car.getStockStatus().equals(3)) {
                    outStock++;
                }
                if (car.getStockStatus().equals(-1)) {
                    deleteStock++;
                }
            }

            m.put("countStock", pageInfo.getTotal() - deleteStock);//库存量
            m.put("sumStockPrice", carService.getSumMarketPrice(carVo));//库存价格
            m.put("outStock", outStock);//外出量
            m.put("inStock", pageInfo.getTotal() - deleteStock - outStock);
        } else {
            m.put("countStock", 0);//库存量
            m.put("sumStockPrice", 0);//库存价格
            m.put("outStock", 0);//外出量
            m.put("inStock", 0);
        }
        if (null != carVo.getTenant()) {
            InventoryStatisticalRequest inventoryStatisticalRequest = new InventoryStatisticalRequest();
            inventoryStatisticalRequest.setMarketId(user.getMarketId());
            inventoryStatisticalRequest.setTenantId(carVo.getTenant());

            InventoryStatisticalResponse response = carService.inventoryStatistical(inventoryStatisticalRequest);
            m.put("InventoryStatisticalResponse", response);
        }
        m.put("listCarVo", pageInfo);

        result.InterfaceResult200(m);
        return result;
    }

    /**
     * 导出
     *
     * @param carVo
     * @return
     */
    @PostMapping("/export")
    InterfaceResult export(@RequestBody CarVo carVo, HttpServletRequest request) throws Exception {

        User user = getCurrentUser(request);
        if (null == user || user.getMarketId().isEmpty()) {
            return getInterfaceResult("200", "无法确认用户市场");
        }

        carVo.setMarketId(user.getMarketId());

        InterfaceResult result = new InterfaceResult();
        carVo.setCarType(1);
        carVo.setVin((carVo.getVin() == null || carVo.getVin().isEmpty()) ? null : carVo.getVin().trim());
        carVo.setCarNo((carVo.getCarNo() == null || carVo.getCarNo().isEmpty()) ? null : carVo.getCarNo().trim());


        //List<ExportResponse> responses = carService.exportCarVo(carVo);
        List<CarVo> listCarVo = carService.exportCarVo(carVo);

        List<ExportResponse> list = new ArrayList<>();

        if (null == listCarVo || listCarVo.isEmpty()) {
            result.InterfaceResult200(list);
            return result;
        }

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
                //response.setStockDay(Magic.NUll);
                response.setRegisterTime(Magic.NUll);
            } else {
                // response.setStockDay(String.valueOf(DatePoor.getDatePoorDay(new Date(), x.getRegisterTime())));
                response.setRegisterTime(DatePoor.getStringForDate(x.getRegisterTime()));
            }

            response.setStockDay(x.getStockDays().toString());

            if (null == x.getCarStatus()) {
                response.setCarStatus(Magic.NUll);
            } else {
                String carStatus = configurationService.getNameByKeyAndValue("car_status", x.getCarStatus().toString());
                response.setCarStatus(null == carStatus ? Magic.NUll : carStatus);
            }

            if (null == x.getAreaId()) {
                response.setAreaName(Magic.NUll);
            } else {
                Area areaById = areaService.getAreaById(x.getAreaId());
                if (null == areaById || null == areaById.getName()) {
                    response.setAreaName(Magic.NUll);
                } else {
                    response.setAreaName(areaById.getName());
                }
            }

            response.setInitialLicenceTime(x.getInitialLicenceTime());
            response.setMileage(x.getMileage());
            response.setMarketPrice(x.getMarketPrice());
            response.setEvaluatePrice(x.getEvaluatePrice());
            response.setRemark(x.getRemark());

            response.setSeriesName(x.getBrandName() + "-" + x.getSeriesName());
            response.setModelName(x.getModelName());

            list.add(response);

        });

        result.InterfaceResult200(list);
        return result;
    }

    @GetMapping("/{id}")
    InterfaceResult getCar(@PathVariable String id, HttpServletRequest request) throws Exception {
        InterfaceResult result = new InterfaceResult();
        CarVo carVo = new CarVo();
        carVo.setId(id);

        User user = getCurrentUser(request);
        if (null == user || user.getMarketId().isEmpty()) {
            return getInterfaceResult("200", "无法确认用户市场");
        }

        carVo.setMarketId(user.getMarketId());
       /* carVo.setPageCount(pageCount);
        carVo.setPageSize(pageSize);*/
        PageInfo pageInfo = carService.listCarVo(carVo);
        List<CarVo> listCarVo = pageInfo.getList();
        Map m = new HashMap();

     /*   m.put("pageCount", pageCount);
        m.put("pageSize", pageSize);*/
        m.put("total", pageInfo.getTotal());

        if (CollectionUtil.listIsNotEmpty(listCarVo)) {
            for (CarVo car : listCarVo) {
                if (car != null && car.getTenant() != null) {
                    UserTenant tenant = userTenantService.selectByPrimaryKey(car.getTenant());
                    if (tenant != null) {
                        car.setTenantName(tenant.getTenantName());
                    }
                }

                if (null == car.getOwnerName() || car.getOwnerName().isEmpty()) {
                    car.setOwnerName(Magic.NUll);
                }

                if (null != car) {
                    //获取车辆详细参数-----大搜车那边的详细参数
                    String model_code = car.getModelCode();
                    InterfaceResult interfaceResult2 = daSouCheService.getDetailInfo(model_code);
                    //把消息车辆详情放入参数中
                    if (null != interfaceResult2 && "200".equals(interfaceResult2.getCode())) {
                        car.setDaSouChe(interfaceResult2.getData());
                        m.put("daSouChe", interfaceResult2.getData());
                    }
                }

                if (car != null && StringUtils.isNotBlank(car.getId())) {
                    //查询图片
                    CarPic carPic = new CarPic();
                    carPic.setCarId(car.getId());
                    m.put("listCarPic", carPicService.listCarPic(carPic));
                    //查询出入场记录
//					m.put("listCarRecord", carRecordService.listCarRecord(carVo.getRfid()));
                    //检测机构
                    CarCheck carCheck = carCheckService.getCarCheck(carVo.getId());
                    if (null == carCheck) {
                        carCheck = new CarCheck();
                        carCheck.setCompanyId(Magic.NUll);
                    }
                    m.put("carCheck", carCheck);
                }
            }
        }
        m.put("listCarVo", listCarVo);
/*

        InventoryStatisticalRequest inventoryStatisticalRequest = new InventoryStatisticalRequest();
        inventoryStatisticalRequest.setMarketId(user.getMarketId());
        inventoryStatisticalRequest.setTenantId(carVo.getTenant());

        InventoryStatisticalResponse response = carService.inventoryStatistical(id);
        m.put("InventoryStatisticalResponse", response);
*/

        result.InterfaceResult200(m);
        return result;
    }

    //获取出场记录
    @PostMapping("/{id}/record")
    InterfaceResult record(@PathVariable String id, @RequestBody CarRecord carRecord) {
        InterfaceResult interfaceResult = new InterfaceResult();
        Car car = carService.selectByPrimaryKey(id);
        if (null == car || StringUtils.isBlank(car.getVin())) {
            return getInterfaceResult("600", "Vin为null");
        }
        carRecord.setVin(car.getVin());
        PageInfo pageInfo = carRecordService.listCarRecord(carRecord, null);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }

    //从大搜车获取车辆详细信息
    @GetMapping("/detail/{id}")
    InterfaceResult detail(@PathVariable String id) {
        InterfaceResult interfaceResult = new InterfaceResult();
        CarBase carBase = carBaseService.selectByPrimaryKey(id);

        if (null != carBase) {
            //获取车辆详细参数-----大搜车那边的详细参数
            String model_code = carBase.getModelCode();
            interfaceResult = daSouCheService.getDetailInfo(model_code);
            //把消息车辆详情放入参数中
            carBase.setDaSouChe(interfaceResult.getData());
        }

        return getInterfaceResult("200", carBase);
    }

    /**
     * 新增库存
     * @param carVo
     * @return
     */
//	@PostMapping("")
//	InterfaceResult addCar(@RequestBody CarVo carVo) {
//		InterfaceResult result = new InterfaceResult();
//		if(carService.saveCarVO(carVo) != 1) {
//			result.InterfaceResult500("添加车辆信息失败");
//		}
//		return result;
//	}

    /**
     * 修改库存
     *
     * @param id    汽车id
     * @param carVo
     * @return
     */
    @PutMapping("/{id}")
    InterfaceResult updateCar(@PathVariable String id, @RequestBody CarVo carVo) {
        InterfaceResult result = new InterfaceResult();
        carVo.setId(id);
        if (carService.updateCarVO(carVo) != 1) {
            result.InterfaceResult500("修改车辆信息失败");
        }
        return result;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public InterfaceResult listCar(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        User user = super.getCurrentUser(request);
        carService.deleteCarById(id);
        String topic = topicService.getTopic(user.getMarketId());

        //同步删除本地车辆状态
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setData(id);
        postParam.setMarket(user.getMarketId());
        postParam.setUrl("/barrier/car/delete/" + id);
        postParam.setMethod("get");
        postParam.setOnlySend(false);
        postParam.setMessageTime(Constants.dateformat.format(new Date()));
        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

        return interfaceResult;
    }

    /**
     * 上传淘宝接口
     *
     * @param params
     * @return
     * @throws Exception
     */
    @PostMapping("/sendTaoBao")
    public InterfaceResult sendTaoBao(@RequestBody JSONObject params) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Properties prop = new Properties();
        CarInfo carInfo = new CarInfo();

        String sell_cid = "1396000473,1396000474,1396000475,1396000476";


        prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
        String url = prop.getProperty("taobaoApiUrl");
        String taobaoUrl = prop.getProperty("taobaoUrl");
        String carId = params.getString("id");
        Object carChannelId = params.get("carChannelId");
        String channelId = null;
        if(carChannelId!=null){
            channelId = carChannelId.toString();
        }
        carInfo = carService.getCarInfoById(carId);
        if (carInfo == null) {
            interfaceResult.InterfaceResult600("查无此车");
            return interfaceResult;
        }
        sell_cid += "," + carInfo.getBrand_code();
        if ("010".equals(carInfo.getMarket_id())) {
            //针对玉林市场
            carInfo.setAttribution("450900");
        }
        if (carInfo.getModel_name() != null && !"".equals(carInfo.getModel_name()) && carInfo.getModel_name().contains("款")) {
            //获取modelYear 为空不能上传
            carInfo.setModel_year(carInfo.getModel_name().substring(carInfo.getModel_name().indexOf("款") - 4, carInfo.getModel_name().indexOf("款") + 1));
        }
        if ("".equals(carInfo.getAttribution()) || carInfo.getAttribution() == null) {
            interfaceResult.setCode("600");
            interfaceResult.setMsg("汽车所属地为空");
            return interfaceResult;
        }
        if (carInfo.getTaobao_id() == null || carInfo.getTaobao_id().equals("")) {
            //判断是否为手动选择车型上传
            if (params.has("brandName")) {
                carInfo.setBrand_name(params.getString("brandName"));
                carInfo.setSeries_name(params.getString("seriesName"));
                carInfo.setModel_year(params.getString("yearName"));
                carInfo.setModel_name(params.getString("modelName"));
            }
            List<CarPic> picList = carService.getCarPic(carInfo.getId());

            JSONObject jsonPamars = JSONObject.fromObject(carInfo);
            jsonPamars.put("picList", picList);
            //String jsonPamars1 = jsonPamars + "";
            String jsonPamars1 = jsonPamars.toString();
            JSONObject json = JSONObject.fromObject(JsonUtils.convert(jsonPamars1));
            json.remove("requestUser");
            json.remove("requestMarket");
            json.remove("token");
            json.remove("operationTime");
            json.remove("updateTime");
            json.remove("registerTime");
            json.remove("operationUserId");
            json.remove("operationUserName");
            json.remove("publishTime");
            json.put("sellCid", sell_cid);
            json.put("market", carInfo.getMarket_id());
            json.remove("marketId");
            String jsonStr = json.toString().replace("src", "pic");
            // json.replace("src","pic");
            //上传淘宝

            logger.info("淘宝上传入参：" + jsonStr);
            String sendPostAjax = HttpClientUtils.sendPost(url + "cars", jsonStr);
            //System.out.println(json);
            logger.info("淘宝上传返回参数：" + sendPostAjax);
            //System.out.println(sendPostAjax);
            JSONObject jsonObject = JSONObject.fromObject(sendPostAjax);
            if (jsonObject.has("datas")) {
                interfaceResult.InterfaceResult200("");
                String taobaoId = (String) jsonObject.get("datas");
                String taobaoShopUrl = taobaoUrl + taobaoId;
                carInfo.setTaobao_id(taobaoId);
                carInfo.setTaobao_url(taobaoShopUrl);
                carService.modifyCarInfo(carInfo);
                // sync data to cloud
                        /*JSONObject jo = new JSONObject();
                        StockCarinfo onlyCarInfoById = impl.getOnlyCarInfoById(carInfo.getId());
                        jo.put("stock_carinfo", Collections.singletonList(onlyCarInfoById));
                        JSONObject object = new JSONObject();
                        object.put("syncDatas", jo);
                        KafkaUpProducer.sendUpMsg(object.toString());*/
                // JSONObject carParams = JSONObject.fromObject(carInfo);
                CarChannelRel carChannel = new CarChannelRel();

                if("".equals(channelId)||channelId==null){
                    carChannel.setShelfStatus(1);
                    carChannel.setCarId(carId);
                    carChannel.setChannelId("2");
                    carChannel.setIsvalid(1);
                    carChannel.setInsertTime(new Date());
                    carChannelService.insertChannel(carChannel);
                    interfaceResult.InterfaceResult200("上传成功");
                }

                return interfaceResult;
            } else {
                com.alibaba.fastjson.JSONObject js = com.alibaba.fastjson.JSONObject.parseObject(sendPostAjax);
                com.alibaba.fastjson.JSONObject js1 = com.alibaba.fastjson.JSONObject.parseObject(js.get("item").toString());
                com.alibaba.fastjson.JSONObject js2 = com.alibaba.fastjson.JSONObject.parseObject(js1.get("error_response").toString());
                //System.out.println(js2.get("sub_msg").toString());
                interfaceResult.InterfaceResult600(js2.get("sub_msg").toString());
                return interfaceResult;
            }
        } else {
            interfaceResult.InterfaceResult600("请勿重复上传");
        }

        return null;
    }

    @PostMapping("/getBrand")
    public InterfaceResult getBrand() throws Exception {
        InterfaceResult result = new InterfaceResult();
        List<TaoBaoCar> carList = null;

        carList = carService.getBrand();

        result.InterfaceResult200(carList);
        return result;
    }

    /**
     * 手动选择汽车车系、年款、车型
     *
     * @throws Exception
     */
    @PostMapping("/getTaoBaoCar")
    public InterfaceResult getTaoBaoCar(@RequestBody JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        List<TaoBaoCar> taoBaoCarList = null;

        String carId = (String) params.get("id");
        String brandName = (String) params.get("brandName");
        String seriesName = (String) params.get("seriesName");
        String yearName = (String) params.get("yearName");
        String modelName = (String) params.get("modelName");
        TaoBaoCar taoBaoCar = new TaoBaoCar();
        taoBaoCar.setBrandName(brandName);
        taoBaoCar.setSeriesName(seriesName);
        taoBaoCar.setYearName(yearName);
        taoBaoCar.setModelName(modelName);
        if (null != brandName || null != seriesName || null != yearName) {
            taoBaoCarList = carService.getTaoBaoCarByKey(taoBaoCar);
        }

        result.InterfaceResult200(taoBaoCarList);
        return result;
    }

    /**
     * 上架淘宝
     *
     * @throws Exception
     *//*
    @PostMapping("/taoBaoCarGround")
    public InterfaceResult taoBaoCarGround(@RequestBody JSONObject params) throws Exception {



        InterfaceResult result = new InterfaceResult();
        Properties prop = new Properties();

        prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
        String url = prop.getProperty("taobaoApiUrl");
        String sendPostAjax = HttpClientUtils.sendPost(url + "taoBaoCarGround", params.toString());
        JSONObject jsonObject = JSONObject.fromObject(sendPostAjax);
        if (jsonObject.get("resultCode").equals("200")) {
            result.setCode("200");
            result.setMsg("上架成功");
            return result;
        }

        return result;
    }*/

    //通过vin码查询检测信息
    @RequestMapping(value = "/getCheckData", method = RequestMethod.POST)
    public InterfaceResult getCheckData(@RequestBody JSONObject params) {
        InterfaceResult result = new InterfaceResult();
        Map bigMap = new HashMap();

        String wzjs = null;
        String vin = params.get("vin").toString();
        //String marketId = params.get("marketId").toString();
        try {
            String check = carCheckService.getCarCheckByVin(vin);
            if (check != null && !"".equals(check)) {
                wzjs = check;
            } else {

                //判断是否含有检测信息

                Properties prop = new Properties();


                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String time = formatter.format(new Date());

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

                prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
                String wzmcname = prop.getProperty("wzmcname");
                String wzurl = prop.getProperty("wzurl");
                String wzappid = prop.getProperty("wzappid");
                String wzappkey = prop.getProperty("wzappkey");

                StringBuffer md5 = new StringBuffer();
                String sign = md5.append(wzmcname).append(vin).append(wzappid).append(wzappkey).append(time).toString();
                sign = MD5Util.MD5(String.valueOf(md5)).toLowerCase();
                //String sign = FileUtils.sign(vin, time, wzappid, wzmcname, wzappkey);
                String url = wzurl + "?mcname=" + wzmcname + "&vin=" + params.get("vin") + "&sign=" + sign + "&key=" + wzappkey +
                        "&appid=" + wzappid;
                wzjs = HttpClientUtil.get(url, "utf-8", null);
                logger.info("请求维真接口返回参数" + wzjs);
                //查询结果插入表中
                CarCheck checkNew = new CarCheck();
                checkNew.setId(UuidUtils.getUUID());
                checkNew.setCompanyId("2");
                checkNew.setVin(vin);
                //checkNew.setCarId();
                checkNew.setData(wzjs);
                checkNew.setInsertTime(new Date());
                carCheckService.insertCarCheck(checkNew);
            }
            com.alibaba.fastjson.JSONObject js = com.alibaba.fastjson.JSONObject.parseObject(wzjs);
            if ("0".equals(js.get("code"))) {
                com.alibaba.fastjson.JSONObject js2 = com.alibaba.fastjson.JSONObject.parseObject(js.get("data").toString());
                com.alibaba.fastjson.JSONObject js3 = com.alibaba.fastjson.JSONObject.parseObject(js2.get("report").toString());
                CheckWZ checkResult = JsonTools.toObj(js3.toString(), CheckWZ.class);


                Map map1 = new HashMap();
                Map map2 = new HashMap();
                Map map3 = new HashMap();
                Map map4 = new HashMap();

                for (int i = 0; i < checkResult.getAccident().size(); i++) {
                    map1.put(checkResult.getAccident().get(i).getName(), checkResult.getAccident().get(i).getVal());

                }

                bigMap.put("accident", map1);
                for (int i = 0; i < checkResult.getBaseinfo().size(); i++) {

                    map2.put(checkResult.getBaseinfo().get(i).getName(), checkResult.getBaseinfo().get(i).getVal());
                }
                map2.put("检测时间", js2.get("created").toString());
                bigMap.put("base", map2);

                for (int i = 0; i < checkResult.getWater().size(); i++) {

                    map3.put(checkResult.getWater().get(i).getName(), checkResult.getWater().get(i).getVal());

                }
                bigMap.put("water", map3);

                for (int i = 0; i < checkResult.getFire().size(); i++) {

                    map4.put(checkResult.getFire().get(i).getName(), checkResult.getFire().get(i).getVal());

                }
                bigMap.put("fire", map4);

                result.InterfaceResult200(bigMap);
            }
        } catch (IOException e1) {

            e1.printStackTrace();
        }

        return result;
    }

    /**
     * 上架
     *
     * @return
     */
    @RequestMapping(value = "/groundCar", method = RequestMethod.POST)
    public InterfaceResult groundCar(@RequestBody com.alibaba.fastjson.JSONObject params) {
        InterfaceResult result = new InterfaceResult();
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
            CarChannelRel carChannelRel = new CarChannelRel();
            com.alibaba.fastjson.JSONArray carids = params.getJSONArray("carIds");
            com.alibaba.fastjson.JSONArray numIds = params.getJSONArray("taobaoIds");
            com.alibaba.fastjson.JSONArray channels = params.getJSONArray("channels");

            carChannelRel = carChannelService.getCarchannelRel(carids.get(0).toString(), channels.get(0).toString());

            String APP_KEY = prop.getProperty("taobaoAppKey");
            String SECRET = prop.getProperty("taobaosecret");
            String API_URL = prop.getProperty("taobaoUploadUrl");
            String sessionKey = prop.getProperty("sessionKey");

            TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
            ItemUpdateListingRequest req = new ItemUpdateListingRequest();
            req.setNumIid(Long.valueOf(numIds.get(0).toString()));
            req.setNum(2L);
            ItemUpdateListingResponse rsp = null;

            rsp = client.execute(req, sessionKey);
            logger.info("上架返回结果：" + rsp.getBody());
            if (rsp.getItem() != null) {
                //上架成功修改状态

                if (carChannelRel == null || carChannelRel.getId() == null) {
                    CarChannelRel carChannel = new CarChannelRel();
                    carChannel.setShelfStatus(2);
                    carChannel.setCarId(carids.get(0).toString());
                    carChannel.setChannelId(channels.get(0).toString());
                    carChannel.setIsvalid(1);
                    carChannel.setInsertTime(new Date());
                    carChannelService.insertChannel(carChannel);
                } else {
                    carChannelRel.setShelfStatus(2);

                    //carChannelRel.setId(channels.get(0).toString());
                    //carChannelRel.setCarId(carids.get(0).toString());
                    carChannelService.updateChanelByCarId(carChannelRel);
                }
                result.setCode("200");
                result.setMsg("上架成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 下架
     *
     * @return
     */
    @RequestMapping(value = "/downCar", method = RequestMethod.POST)
    public InterfaceResult downCar(@RequestBody com.alibaba.fastjson.JSONObject params) {
        InterfaceResult result = new InterfaceResult();
        Properties prop = new Properties();
        CarChannelRel carChannelRel = new CarChannelRel();
        try {
            prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
            com.alibaba.fastjson.JSONArray carids = params.getJSONArray("carIds");
            com.alibaba.fastjson.JSONArray numIds = params.getJSONArray("taobaoIds");
            com.alibaba.fastjson.JSONArray channels = params.getJSONArray("channels");
            carChannelRel = carChannelService.getCarchannelRel(carids.get(0).toString(), channels.get(0).toString());

            String APP_KEY = prop.getProperty("taobaoAppKey");
            String SECRET = prop.getProperty("taobaosecret");
            String API_URL = prop.getProperty("taobaoUploadUrl");
            String sessionKey = prop.getProperty("sessionKey");


            TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
            ItemUpdateDelistingRequest req = new ItemUpdateDelistingRequest();
            req.setNumIid(Long.valueOf(numIds.get(0).toString()));

            ItemUpdateDelistingResponse rsp = client.execute(req, sessionKey);
            logger.info("下架返回结果：" + rsp.getBody());
            if (rsp.getItem() != null) {
                //下架成功修改状态
                //CarChannelRel carChannel  = new CarChannelRel();
                carChannelRel.setShelfStatus(1);

                carChannelService.updateChanelByCarId(carChannelRel);
                result.setCode("200");
                result.setMsg("下架成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 配置黑白名单时查询车辆信息
     * @param barrierListCarRequest
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectCarByTenant")
    @OperationAnnotation(title = "设置道闸黑白名单时查询车辆")
    public InterfaceResult selectCarByTenant(@RequestBody BarrierListCarRequest barrierListCarRequest,HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        PageInfo pageInfo = carService.selectCarByTenant(barrierListCarRequest);
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }



}
