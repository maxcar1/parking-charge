package com.maxcar.base.impl;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.dao.CarBrandMapper;
import com.maxcar.base.dao.CarModelMapper;
import com.maxcar.base.dao.CarSeriesMapper;
import com.maxcar.base.pojo.*;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.DaSouCheService;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.dasouche.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("daSouCheService")
public class DaSouCheServiceImpl implements DaSouCheService {
    @Autowired
    CarBrandMapper carBrandMapper;
    @Autowired
    CarSeriesMapper carSeriesMapper;
    @Autowired
    CarModelMapper carModelMapper;
    @Autowired
    CityService cityService;
    private static final Logger logger = LoggerFactory.getLogger(DaSouCheServiceImpl.class);
    @Value("${app.key}")
    private String appKey;
    @Value("${app.secret}")
    private String appSecret;
    @Value("${base.url}")
    private String url;
    @Value("${detail.url}")
    private String url2;
    @Value("${brands.url}")
    private String url3;
    @Value("${sc.brand.code.url}")
    private String url4;
    @Value("${sc.series.code.url}")
    private String url5;
    @Value("${sc.model.code.url}")
    private String url6;
    @Value("${price.url}")
    private String url7;
    @Value("${brand.dictionaries.ur}")
    private String url8;
    @Value("${used.car.price}")
    private String url9;
    @Value("${areas}")
    private String url10;
    @Override
    public Result getAllService(int type, JSONObject params, List<String> listKey) {
        Result result = new Result();
        result.setResultCode(ResultCode.SERVICE_ERROR.getCode());
        String newUrl = "";
        //根据type 判断是哪个服务
        switch(type)
        {
            case 1:
                newUrl = url;
                break;
            case 2:
                newUrl = url2;
                break;
            case 3:
                newUrl = url3;
                break;
            case 4:
                newUrl = url4;
                newUrl = newUrl.replaceAll("xxx",params.get("scBrandCode").toString());
                break;
            case 5:
                newUrl = url5;
                newUrl = newUrl.replaceAll("xxx",params.get("scSeriesCode").toString());
                break;
            case 6:
                newUrl = url6;
                newUrl = newUrl.replaceAll("xxx",params.get("scBrandCode").toString());
                break;
            case 7:
                newUrl = url7;
                break;
            case 8:
                newUrl = url8;
                break;
            case 9:
                newUrl = url9;
                break;
            case 10:
                newUrl = url10;
                break;
        }
        if(null == newUrl || "".equals(newUrl)){
            return null;
        }
        logger.info("URL:" + newUrl);
        StringBuffer sb = new StringBuffer();
        sb.append("appKey=");
        sb.append(appKey);
        //循环遍历塞入查询条件
        for (String string : listKey) {
            sb.append("&");
            sb.append(string);
            sb.append("=");
            sb.append(params.get(string));
        }
        logger.info("###########sb{}", sb);
        String s = "";
        try {
            String signStringBase64 = BaseEncodeUtil.encodeBase64(sb.toString().getBytes("UTF-8"));
            String sign = appSecret+":"+signStringBase64;
            // sha1 算法计算出最终 sign
            sign = SHAUtil.getSha1(sign);
            //
            logger.info("准备发送数据到大搜车：" + sign);
            s = HttpClientUtil.get(newUrl+sb, "utf-8",sign);
            logger.info("#########大搜车返回结果：" + s);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        if (StringUtils.isNotBlank(s)) {
            JSONObject oo = JSONObject.parseObject(s);
            if (oo.getString("status").equals("200")) {
                result.setResultCode(0);
                if(type == 8) {
                    List<Map> list = new ArrayList<Map>();
                    JSONObject j = oo.getJSONObject("data");
                    Iterator keys = j.keySet().iterator();
                    while(keys.hasNext()){
                        Map map = new HashMap();
                        String key = keys.next().toString();
                        map.put("index", key);
                        map.put("list", j.get(key));
                        list.add(map);
                    }
                    result.setList(list);

                }else if ( type == 4 || type == 5) {
                    result.setList(oo.get("data"));
                    return result;
                } else {
                    result.setItem(oo.get("data"));
                }
                result.setResultCode(ResultCode.GET_SUCCESS.getCode());
                return result;
            }
        }
        return result;
    }

    @Override
    public CarBrand getCarBrand(String brandCode) {
        return carBrandMapper.getCarBrand(brandCode);
    }

    @Override
    public CarSeries getCarSeries(String seriesCode) {
        return carSeriesMapper.getCarSeries(seriesCode);
    }

    @Override
    public CarModel getCarModel(String modelCode) {
        return carModelMapper.getCarModel(modelCode);
    }

    @Override
    public List<Map<String, Object>> getCarSeriesByBrandCode(String brandCode) throws Exception {
        return carSeriesMapper.getCarSeriesByBrandCode(brandCode);
    }

    @Override
    public List<Map<String, Object>> getCarModelBySeriesCode(String seriesCode) throws Exception {
        return carModelMapper.getCarModelBySeriesCode(seriesCode);
    }

    @Override
    public List<CarBrand> getAllBrand() {
        return carBrandMapper.getAllBrand();
    }

    @Override
    public List<CarSeries> getAllSeries(String brandId) {
        return carSeriesMapper.getAllSeries(brandId);
    }

    @Override
    public List<CarModel> getAllModel(String seriesId) {
        return carModelMapper.getAllModel(seriesId);
    }

    @Override
    public InterfaceResult getDetailInfo(String scModelCode) {
        InterfaceResult interfaceResult = new InterfaceResult();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("scModelCode", scModelCode);
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        Result result = getAllService(2, params, listKey);
        if(result != null){
            interfaceResult = getDifferentResult(2,result);
        }else{
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }

    private static InterfaceResult getDifferentResult(int type,Result result){
        InterfaceResult interfaceResult = new InterfaceResult();
        if (type == 8 || type == 4 || type == 5){
            interfaceResult.InterfaceResult200(result.getList());
        }else{
            interfaceResult.InterfaceResult200(result.getItem());
        }
        return interfaceResult;
    }

    @Override
    public Result detailInfo(String scModelCode) {
        Result result = new Result();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("scModelCode", scModelCode);
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = getAllService(2, params, listKey);
        if (result != null) {
            result.setResultCode(200);
        }
        return result;
    }

    @Override
    public Result getCarListByVin(String vin) {
        Result result = new Result();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        params.put("vinCode", vin);
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = getAllService(1, params, listKey);
        return result;
    }

    @Override
    public Result getAreas(Integer cityCode) {
        Result result = new Result();
        JSONObject params = new JSONObject();
        params.put("timestamp", TimeStampUtils.getTimeM());
        if (StringUtils.isNotBlank(cityCode)) {
            City city = cityService.getCityById(cityCode);
            params.put("name", city.getName());
        }
        // 循环遍历json字符串 参数
        Iterator<?> it = params.keySet().iterator();
        // 遍历json数据，添加到Map对象
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            listKey.add(key);
        }
        Collections.sort(listKey);
        result = getAllService(10, params, listKey);
        result.setResultCode(200);
        return result;
    }
}
