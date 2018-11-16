package com.maxcar.market.dao;

import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.pojo.AreaShop;
import com.maxcar.market.pojo.AreaShopExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AreaShopMapper {
    int countByExample(AreaShopExample example);

    int deleteByExample(AreaShopExample example);

    int deleteByPrimaryKey(String id);

    int insert(AreaShop record);

    int insertSelective(AreaShop record);

    List<AreaShop> selectByExample(AreaShopExample example);

    AreaShop selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AreaShop record, @Param("example") AreaShopExample example);

    int updateByExample(@Param("record") AreaShop record, @Param("example") AreaShopExample example);

    int updateByPrimaryKeySelective(AreaShop record);

    int updateByPrimaryKey(AreaShop record);

    int countParkSpaceByType(@Param("type") int type);

    double getAreaCountTotal(String areaId);

    /**
     * param:
     * describe: 查询市场下车位和展厅的物业信息
     * create_date:  lxy   2018/9/26  14:31
     **/
    List<AreaShop> getCarSpaceAndOfficeByMarketId(GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest request);
}