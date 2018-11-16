package com.maxcar.market.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.pojo.AreaShop;

import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-08-20 11:58
 * @desc
 **/
public interface AreaShopService {
    int insertAreashop(AreaShop areaShop);

    List<AreaShop> getAreaShopListByAreaId(String areaId, String marketId);

    /**
     * @param id
     * @param status 0 未租 1 已租
     * @return
     */
    int updateRentById(String id, Integer status);

    AreaShop getAreashop(String id);

    /**
     * 市场停车位总数
     *
     * @return
     */
    int countPark(String marketId,String tenantId,String areaName);

    int updateAreaShop(AreaShop areaShop);

    int deleteAreaShop(String id);

    PageInfo<AreaShop> getAreaShopListByAreaId(AreaShop areaShop, String areaId);

    List<AreaShop> getAreaShopByRent(String areaId, int rentStaus, String marketId);

    /**
     * 获取该区域下商铺车位或面积总和
     *
     * @param areaId
     * @return
     */
    double getAreaCountTotal(String areaId);

    /**
     * param:
     * describe: 查询市场下车位和展厅的物业信息
     * create_date:  lxy   2018/9/26  14:31
     **/
    List<AreaShop> getCarSpaceAndOfficeByMarketId(GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest request);
}
