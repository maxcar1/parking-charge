package com.maxcar.market.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.market.dao.ParkingFeeIntegralMapper;
import com.maxcar.market.pojo.ParkingFeeIntegral;
import com.maxcar.market.service.ParkingFeeIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author sunyazhou
 * @Date 2018/11/8 14:56
 * @desc
 */
@Service("parkingFeeIntegralService")
public class ParkingFeeIntegralServiceImpl extends BaseServiceImpl<ParkingFeeIntegral,String> implements ParkingFeeIntegralService {


    @Autowired
    private ParkingFeeIntegralMapper parkingFeeIntegralMapper;

    @Override
    public BaseDao<ParkingFeeIntegral, String> getBaseMapper() {
        return parkingFeeIntegralMapper;
    }

    @Override
    public ParkingFeeIntegral selectIntegralByDetailId(String parkingFeeDetailId) {
        return parkingFeeIntegralMapper.selectIntegralByDetailId(parkingFeeDetailId);
    }
}
