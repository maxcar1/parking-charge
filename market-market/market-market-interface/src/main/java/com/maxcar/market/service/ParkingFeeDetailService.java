package com.maxcar.market.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.market.model.request.ParkingFeeDetailListRequest;
import com.maxcar.market.model.response.ParkingFeeDetailListResponse;
import com.maxcar.market.model.response.ParkingFeePayDetailResponse;

/**
 * @author songxuefeng
 * @create 2018-10-24 19:12
 * @description: ${description}
 **/
public interface ParkingFeeDetailService {
    PageInfo<ParkingFeeDetailListResponse> getParkingFreeList(ParkingFeeDetailListRequest parkingFeeDetailListRequest);

    ParkingFeePayDetailResponse getParkingFreePaySum(ParkingFeeDetailListRequest parkingFeeDetailListRequest);
}
