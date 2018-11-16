package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.market.dao.ParkingFeeDetailMapper;
import com.maxcar.market.model.request.ParkingFeeDetailListRequest;
import com.maxcar.market.model.response.ParkingFeeDetailListResponse;
import com.maxcar.market.model.response.ParkingFeePayDetailResponse;
import com.maxcar.market.pojo.ParkingFeeDetail;
import com.maxcar.market.service.ParkingFeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-10-24 19:14
 * @description: ${description}
 **/
@Service("parkingFeeDetailService")
public class ParkingFeeDetailServiceImpl implements ParkingFeeDetailService {
    @Autowired
    private ParkingFeeDetailMapper parkingFeeDetailMapper;

    @Override
    public PageInfo<ParkingFeeDetailListResponse> getParkingFreeList(ParkingFeeDetailListRequest parkingFeeDetailListRequest) {
        PageHelper.startPage(parkingFeeDetailListRequest.getCurrentPage(),parkingFeeDetailListRequest.getPageSize());
        List<ParkingFeeDetailListResponse> parkingFeeDetails=parkingFeeDetailMapper.getParkingFreeList(parkingFeeDetailListRequest);
        return new PageInfo<>(parkingFeeDetails);
    }

    @Override
    public ParkingFeePayDetailResponse getParkingFreePaySum(ParkingFeeDetailListRequest parkingFeeDetailListRequest) {
        return parkingFeeDetailMapper.getParkingFreePaySum(parkingFeeDetailListRequest);
    }
}
