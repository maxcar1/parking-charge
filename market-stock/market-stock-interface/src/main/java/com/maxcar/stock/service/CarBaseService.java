package com.maxcar.stock.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.service.BaseService;
import com.maxcar.stock.entity.Request.SearchCarRequest;
import com.maxcar.stock.entity.Response.CarDetails;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.pojo.CarBaseWithBLOBs;

import javax.security.auth.login.Configuration;
import java.util.List;

/**
 * 库存详细信息服务
 */
public interface CarBaseService extends BaseService<CarBase,String> {
    int insertSelective(CarBaseWithBLOBs record);
    int updateByPrimaryKeyWithBLOBs(CarBaseWithBLOBs record);
    List<CarBaseWithBLOBs> listCarBase(CarBaseWithBLOBs record);
    PageInfo getScreen(CarBase carBase);

    /**
     * 新增
     * @param record
     * @return
     */
    int insertSelective(CarBase record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CarBase record);

    //获取分组
    List<Configuration> carLevelGroup(String type);

    CarDetails getCarBaseById(String id);

    PageInfo selectCarList(CarBase carBase);

    List listOfScreening(String marketId);

    PageInfo selectCarList(SearchCarRequest carRequest);

    int countCarList(SearchCarRequest carRequest);
}