package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.util.StringUtil;
import com.maxcar.market.dao.AreaMapper;
import com.maxcar.market.dao.AreaShopMapper;
import com.maxcar.market.pojo.AreaExample;
import com.maxcar.market.model.request.GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest;
import com.maxcar.market.pojo.AreaShop;
import com.maxcar.market.pojo.AreaShopExample;
import com.maxcar.market.service.AreaShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-08-20 14:02
 * @description: ${description}
 **/
@Service("areaShopService")
public class AreaShopServiceImpl implements AreaShopService {
    @Autowired
    private AreaShopMapper areaShopMapper;

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public int insertAreashop(AreaShop areaShop) {
        return areaShopMapper.insertSelective(areaShop);
    }

    @Override
    public List<AreaShop> getAreaShopListByAreaId(String areaId, String marketId) {
        AreaShopExample areaShopExample = new AreaShopExample();
        AreaShopExample.Criteria criteria = areaShopExample.createCriteria();
        criteria.andAreaIdEqualTo(areaId).andIsvalidEqualTo(1).andMarketIdEqualTo(marketId);
        areaShopExample.setOrderByClause("update_time desc");
        return areaShopMapper.selectByExample(areaShopExample);
    }

    @Override
    public int updateRentById(String id, Integer status) {
        AreaShop areaShop = areaShopMapper.selectByPrimaryKey(id);
        if (areaShop != null) {
            areaShop.setRentStatus(status);
            areaShop.setUpdateTime(new Date());
            return areaShopMapper.updateByPrimaryKeySelective(areaShop);
        }
        return 0;
    }

    @Override
    public AreaShop getAreashop(String id) {
        return areaShopMapper.selectByPrimaryKey(id);
    }

    @Override
    public int countPark(String marketId,String tenantId,String areaName) {
        AreaShopExample areaShopExample = new AreaShopExample();
        AreaShopExample.Criteria criteria = areaShopExample.createCriteria();
        criteria.andIsvalidEqualTo(1).andMarketIdEqualTo(marketId);
        if(StringUtil.isNotEmpty(tenantId)) {
            criteria.andIdEqualTo(tenantId);
        }
        if(StringUtil.isNotEmpty(areaName)) {
//            criteria.andTenantAreaEqualTo(areaName);
        }
        return areaMapper.countPark(marketId);
    }

    @Override
    public int updateAreaShop(AreaShop areaShop) {
        return areaShopMapper.updateByPrimaryKeySelective(areaShop);
    }

    @Override
    public int deleteAreaShop(String id) {
        return areaShopMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<AreaShop> getAreaShopListByAreaId(AreaShop areaShop, String areaId) {
        PageHelper.startPage(areaShop.getCurrentPage(), areaShop.getPageSize());
        AreaShopExample areaShopExample = new AreaShopExample();
        AreaShopExample.Criteria criteria = areaShopExample.createCriteria();
        criteria.andAreaIdEqualTo(areaId).andIsvalidEqualTo(1).andMarketIdEqualTo(areaShop.getMarketId());
        areaShopExample.setOrderByClause("update_time desc");
        List<AreaShop> areaShops = areaShopMapper.selectByExample(areaShopExample);
        PageInfo<AreaShop> pageInfo = new PageInfo<>(areaShops);
        return pageInfo;
    }

    @Override
    public List<AreaShop> getAreaShopByRent(String areaId, int rentStaus, String marketId) {
        AreaShopExample areaShopExample = new AreaShopExample();
        AreaShopExample.Criteria criteria = areaShopExample.createCriteria();
        criteria.andAreaIdEqualTo(areaId)
                .andRentStatusEqualTo(rentStaus)
                .andIsvalidEqualTo(1)
                .andMarketIdEqualTo(marketId);
        areaShopExample.setOrderByClause("update_time desc");
        return areaShopMapper.selectByExample(areaShopExample);
    }

    @Override
    public double getAreaCountTotal(String areaId) {
        return areaShopMapper.getAreaCountTotal(areaId);
    }


    /**
     * param:
     * describe: 查询市场下车位和展厅的物业信息
     * create_date:  lxy   2018/9/26  14:31
     **/
    @Override
    public List<AreaShop> getCarSpaceAndOfficeByMarketId(GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest request) {
        return areaShopMapper.getCarSpaceAndOfficeByMarketId(request);
    }
}
