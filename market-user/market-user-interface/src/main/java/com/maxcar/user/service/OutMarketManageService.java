package com.maxcar.user.service;

import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.OutMarketManage;

import java.util.List;

/**
 * @Author sunyazhou
 * @Date 2018/10/17 17:46
 * @desc
 */
public interface OutMarketManageService extends BaseService<OutMarketManage,String> {

    @Override
    int insertSelective(OutMarketManage record);

    OutMarketManage selectByMarket(String marketId);

    int updateByMarketId(OutMarketManage marketManage);

    List<OutMarketManage> selectByOutMarketType(String type);
}
