package com.maxcar.tenant.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.tenant.pojo.TenantShop;
import com.maxcar.tenant.pojo.TenantShopExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TenantShopMapper extends BaseDao<TenantShop,String> {
    int countByExample(TenantShopExample example);

    int deleteByExample(TenantShopExample example);

    int deleteByPrimaryKey(String id);

    int insert(TenantShop record);

    int insertSelective(TenantShop record);

    List<TenantShop> selectByExample(TenantShopExample example);

    TenantShop selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TenantShop record, @Param("example") TenantShopExample example);

    int updateByExample(@Param("record") TenantShop record, @Param("example") TenantShopExample example);

    int updateByPrimaryKeySelective(TenantShop record);

    int updateByPrimaryKey(TenantShop record);
}