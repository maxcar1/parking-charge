package com.maxcar.market.model.response;

import com.maxcar.market.model.base.BaseResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllParkingFeeResponse extends BaseResponse implements Serializable {

    private static final long serialVersionUID = 6775176657485208886L;
    private List<AllParkingFeePack> list;

    private SumByParkingFeeDetailIdResponse sumByParkingFeeDetailIdResponse;

}
