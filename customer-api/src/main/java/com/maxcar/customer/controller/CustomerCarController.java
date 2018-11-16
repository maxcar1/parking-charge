package com.maxcar.customer.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarModel;
import com.maxcar.base.pojo.CarSeries;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.MD5Util;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.dasouche.HttpClientUtil;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.stock.entity.Response.CarDetails;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.pojo.CarCheck;
import com.maxcar.stock.pojo.CheckWZ;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.stock.service.CarCheckService;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 商户端车辆信息
 *
 * @author chiyanlong
 * @ClassName: CarController
 * @date 2018年9月28日 上午11:46:14
 */
@RestController
@RequestMapping("/we/customer")
public class CustomerCarController extends BaseController {

    @Autowired
    private CarService carService;
    @Autowired
    private DaSouCheService daSouCheService;

    @Autowired
    private CarCheckService carCheckService;

    @Value("${wzmcname}")
    private String wzmcname;
    @Value("${wzurl}")
    private String wzurl;
    @Value("${wzappid}")
    private String wzappid;
    @Value("${wzappkey}")
    private String wzappkey;
    /**
     * 在库商品车列表
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getCarList",method= RequestMethod.POST)
    public InterfaceResult getCarList(@RequestBody CarVo carVo, HttpServletRequest req) {
        InterfaceResult interfaceResult=new InterfaceResult();
        try {
            if (null !=carVo.getYearEnd()){
                carVo.setInitialLicenceTimeStart(getDateByYear(carVo.getYearEnd()));
            }
            if (null !=carVo.getYearStart()){
                carVo.setInitialLicenceTimeEnd(getDateByYear(carVo.getYearStart()));
            }
            if (null != carVo.getMileageStart()){
                carVo.setMileageStart(carVo.getMileageStart()* 10000);
            }
            if (null != carVo.getMileageEnd()){
                carVo.setMileageEnd(carVo.getMileageEnd() * 10000);
            }
            PageInfo pageInfo=carService.getCarList(carVo);
            interfaceResult.InterfaceResult200(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            interfaceResult.InterfaceResult500("服务器异常");
        }
        return interfaceResult;
    }
	/*
	 * 车辆详情
     *
     * @param id 车辆详情id
     * @param CarBase
     * @return
	 */
	@RequestMapping(value="/getCarDetail/{id}")
	public InterfaceResult getCarDetail(@PathVariable(value = "id") String id){
		InterfaceResult interfaceResult = new InterfaceResult();
		try {
            Map map = carService.getCarDetail(id);
            if (map == null) {
                interfaceResult.InterfaceResult600("无查询记录");
            } else {
                interfaceResult.InterfaceResult200(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;

	}

    /**
     * 获取所有品牌
     * @return
     */
    @RequestMapping(value="/getAllBrand")
    public InterfaceResult getAllBrand(){
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<CarBrand> carBrandList= daSouCheService.getAllBrand();
            interfaceResult.InterfaceResult200(carBrandList);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;

    }
    /**
     * 根据品牌获取所有车系
     * @return
     */
    @RequestMapping(value="/getAllSeries/{brandId}")
    public InterfaceResult getAllSeries(@PathVariable(value = "brandId") String brandId){
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<CarSeries> carSeriesList= daSouCheService.getAllSeries(brandId);
            interfaceResult.InterfaceResult200(carSeriesList);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;

    }

    /**
     * 根据车系获取所有车型
     * @return
     */
    @RequestMapping(value="/getAllModel/{seriesId}")
    public InterfaceResult getAllModel(@PathVariable(value = "seriesId") String seriesId){
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<CarModel> carModelList= daSouCheService.getAllModel(seriesId);
            interfaceResult.InterfaceResult200(carModelList);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;

    }


    public static Date getDateByYear(int number) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -number);
        Date y = c.getTime();
        return y;
    }

    /**
     * 车型编码获取⻋车型详细配置信息
     * @param scModelCode
     * @return
     */
    @RequestMapping(value = "/models/{scModelCode}", method = RequestMethod.GET)
    public InterfaceResult detailInfo(@PathVariable("scModelCode") String scModelCode) {
        InterfaceResult result = new InterfaceResult();
        try {
            Result res = daSouCheService.detailInfo(scModelCode);
            result.InterfaceResult200(res);
        }catch (Exception e){
            e.printStackTrace();
            result.InterfaceResult500("查询失败");
        }
        return result;
    }


    //通过vin码查询检测信息
    @GetMapping(value = "/check/{vin}/{marketId}/{carId}")
    public InterfaceResult getCheckData(@PathVariable("vin") String vin,
                                        @PathVariable("marketId") String marketId,
                                        @PathVariable("carId") String carId) {
        InterfaceResult result = new InterfaceResult();

        String wzjs = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String time = formatter.format(new Date());
        Map bigMap = new HashMap();
        try {

            CarCheck check = carCheckService.getCarCheck(carId);
            if(null != check){
                wzjs = check.getData();
            }else {
                StringBuffer md5 = new StringBuffer();
                String sign = md5.append(wzmcname).append(vin).append(wzappid).append(wzappkey).append(time).toString();
                sign = MD5Util.MD5(String.valueOf(md5)).toLowerCase();
                //String sign = FileUtils.sign(vin, time, wzappid, wzmcname, wzappkey);
                StringBuilder sb = new StringBuilder();
                sb.append(wzurl);
                sb.append("?mcname=");
                sb.append(wzmcname);
                sb.append("&vin=");
                sb.append(vin);
                sb.append("&sign=");
                sb.append(sign);
                sb.append("&key=");
                sb.append(wzappkey);
                sb.append("&appid=");
                sb.append(wzappid);

                wzjs = HttpClientUtil.get(sb.toString(), "utf-8", null);
                JSONObject json = JSONObject.fromObject(wzjs);
                if (null != json){
                    if (StringUtils.equals(json.getString("code"),"0")){
                        //查询结果插入表中
                        CarCheck checkNew = new CarCheck();
                        checkNew.setId(UuidUtils.getUUID());
                        checkNew.setCompanyId("2");
                        checkNew.setVin(vin);
                        checkNew.setCarId(carId);
                        checkNew.setData(wzjs);
                        checkNew.setMarketId(marketId);
                        checkNew.setInsertTime(new Date());
                        carCheckService.insertCarCheck(checkNew);
                    }
                }
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
            }else{
                result.InterfaceResult600(wzjs);
            }
        } catch (IOException e1) {

            e1.printStackTrace();
        }

        return result;
    }
}
