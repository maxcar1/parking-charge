package com.maxcar.stock.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DateUtils;
import com.maxcar.stock.dao.CarBaseMapper;
import com.maxcar.stock.dao.CarPicMapper;
import com.maxcar.stock.entity.Request.SearchCarRequest;
import com.maxcar.stock.entity.Response.CarBaseResponse;
import com.maxcar.stock.entity.Response.CarDetails;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.pojo.CarBaseExample;
import com.maxcar.stock.pojo.CarBaseWithBLOBs;
import com.maxcar.stock.pojo.CarPic;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.stock.vo.CarBaseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("carBaseService")
public class CarBaseServiceImpl extends BaseServiceImpl<CarBase,String> implements CarBaseService {
    @Autowired
    CarBaseMapper carBaseMapper;

    @Autowired
    private CarPicMapper carPicMapper;

    @Override
    public int insertSelective(CarBaseWithBLOBs record) {
        return carBaseMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(CarBaseWithBLOBs record) {
        return carBaseMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public List<CarBaseWithBLOBs> listCarBase(CarBaseWithBLOBs record) {
//    	carBaseMapper.
        return carBaseMapper.listCarBase(record);
    }

    @Override
    public BaseDao<CarBase, String> getBaseMapper() {
        return carBaseMapper;
    }

    @Override
    public List<Configuration> carLevelGroup(String type) {
        return carBaseMapper.selectCarGroup(type);
    }

    @Override
    public CarDetails getCarBaseById(String id) {
        return carBaseMapper.getCarBaseById(id);
    }

    @Override
    public PageInfo getScreen(CarBase carBase) {
        CarBaseExample example = new CarBaseExample();
        CarBaseExample.Criteria criteria = example.createCriteria();
        if (null != carBase.getBrandCode() && carBase.getBrandCode() != "") {
            criteria.andBrandCodeEqualTo(carBase.getBrandCode());
        }
        if (null != carBase.getSeriesCode() && carBase.getSeriesCode() != "") {
            criteria.andSeriesCodeEqualTo(carBase.getSeriesCode());
        }
        if (null != carBase.getMarketPriceStart() && null != carBase.getMarketPriceEnd()) {
            criteria.andMarketPriceBetween(carBase.getMarketPriceStart(), carBase.getMarketPriceEnd());
        }
        if (null != carBase.getMileageStart() && null != carBase.getMileageEnd()) {
            criteria.andMarketPriceBetween(carBase.getMileageStart(), carBase.getMileageEnd());
        }
        if (null != carBase.getLevel() && StringUtils.isNotEmpty(carBase.getLevel())) {
//            carBase.setLevel("%" + carBase.getLevel() + "%");
            criteria.andLevelLike(carBase.getLevel());
        }
        if (null != carBase.getColor() && StringUtils.isNotEmpty(carBase.getColor())) {
//            carBase.setColor("%" + carBase.getColor() + "%");
            criteria.andColorLike(carBase.getColor());
        }
        if (null != carBase.getEnvironmentalStandards() && StringUtils.isNotEmpty(carBase.getEnvironmentalStandards())) {
//            carBase.setEnvironmentalStandards("%" + carBase.getEnvironmentalStandards() + "%");
            criteria.andEnvironmentalStandardsLike(carBase.getEnvironmentalStandards());
        }
        if (null != carBase.getGearBox() && StringUtils.isNotEmpty(carBase.getGearBox())) {
//            carBase.setGearBox("%" + carBase.getGearBox() + "%");
            criteria.andGearBoxLike(carBase.getGearBox());
        }
        if (null != carBase.getInitialLicenceTime()) {
            criteria.andInitialLicenceTimeBetween(DateUtils.getFirstDateOfMonth(carBase.getInitialLicenceTime()), DateUtils.getLastDateOfMonth(carBase.getInitialLicenceTime()));
        }
        PageHelper.startPage(carBase.getCurrentPage(), carBase.getPageSize());
        List<CarBase> lists = carBaseMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public PageInfo selectCarList(CarBase carBase) {
        CarBaseExample example = new CarBaseExample();
        CarBaseExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(carBase.getCurrentPage(), carBase.getPageSize());
        List<CarBaseResponse> list = carBaseMapper.selectByList(carBase);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List listOfScreening(String marketId) {
        Map<String, Object> map = new HashMap<>();
        CarBase carBase = new CarBase();
        carBase.setMarketId(marketId);
        List<CarBaseResponse> resList = carBaseMapper.selectByList(carBase);
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> brandCodeMap = new HashMap<>();
        for (CarBaseResponse car : resList) {
            brandCodeMap.put(car.getBrandCode(), car.getBrandName());
        }
        Map<String,Object> map2 = new HashMap<>();
        if (!brandCodeMap.isEmpty()){
            for (Map.Entry mapItem:brandCodeMap.entrySet()) {
                if (null != mapItem.getKey()){
                    List<Map<String, String>> seriesList = new ArrayList<>();
                    for (CarBaseResponse car : resList) {
                        if (mapItem.getKey().equals(car.getBrandCode())) {
                            Map map1 = new HashMap<String, String>();
                            map1.put(car.getSeriesCode(), car.getSeriesName());
                            seriesList.add(map1);
                        }
                    }
                    map2.put("“seriesList”", seriesList);
                }
            }
        }
        list.add(map2);
        return list;

    }

    @Override
    public PageInfo selectCarList(SearchCarRequest carRequest) {
        PageHelper.startPage(carRequest.getCurrentPage(), carRequest.getPageSize());

        if (carRequest.getAutoAgeStart() != null) {
            int autoAgeStart = carRequest.getAutoAgeStart();
            carRequest.setInitialLicenceEndTime(DateUtils.addYears(new Date(), -autoAgeStart));
        }

        if (carRequest.getAutoAgeEnd() != null) {
            int autoAgeEnd = carRequest.getAutoAgeEnd();
            carRequest.setInitialLicenceStartTime(DateUtils.addYears(new Date(), -autoAgeEnd));
        }

        List<CarBase> list = carBaseMapper.queryCarList(carRequest);
        List<CarBaseVo> carBaseVos = new ArrayList<>();
        for (CarBase car : list) {
            CarBaseVo carBaseVo = new CarBaseVo();
            BeanUtils.copyProperties(car, carBaseVo);
            CarPic carPic = carPicMapper.selectByCarIdType(carBaseVo.getId(), 0);
            if (carPic != null) {
                carBaseVo.setSrc(carPic.getSrc());
            }
            carBaseVos.add(carBaseVo);
        }

        return new PageInfo(carBaseVos);
    }

    @Override
    public int countCarList(SearchCarRequest carRequest) {
        PageHelper.startPage(carRequest.getCurrentPage(), carRequest.getPageSize());

        if (carRequest.getAutoAgeStart() != null) {
            int autoAgeStart = carRequest.getAutoAgeStart();
            carRequest.setInitialLicenceEndTime(DateUtils.addYears(new Date(), -autoAgeStart));
        }

        if (carRequest.getAutoAgeEnd() != null) {
            int autoAgeEnd = carRequest.getAutoAgeEnd();
            carRequest.setInitialLicenceStartTime(DateUtils.addYears(new Date(), -autoAgeEnd));
        }

        return carBaseMapper.countCarList(carRequest);
    }
}
