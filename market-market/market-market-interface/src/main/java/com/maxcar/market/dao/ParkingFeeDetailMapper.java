package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.market.model.request.AllParkingFeeDetailRequest;
import com.maxcar.market.model.request.ParkingFeeDetailListRequest;
import com.maxcar.market.model.response.ParkingFeeDetailListResponse;
import com.maxcar.market.model.response.ParkingFeePayDetailResponse;
import com.maxcar.market.model.response.SumByParkingFeeDetailIdResponse;
import com.maxcar.market.pojo.ParkingFeeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkingFeeDetailMapper extends BaseDao<ParkingFeeDetail, String> {

    /**
     * param:
     * describe: 查看 指定收费班次 收费金额
     * create_date:  lxy   2018/8/28  15:47
     **/
    SumByParkingFeeDetailIdResponse sumByParkingFeeDetailId(@Param("marketId") String marketId, @Param("parkingFeeId") String parkingFeeId);

    /**
     * param:
     * describe: 查询收费详情列表
     * create_date:  lxy   2018/8/28  19:15
     **/
    List<ParkingFeeDetail> allParkingFeeDetail(AllParkingFeeDetailRequest request);

    /**
     * param:
     * describe: 根据UnionId 或者卡号 获取最近入场记录信息
     * create_date:  lxy   2018/8/30  11:43
     **/
    ParkingFeeDetail getParkingFeeDetailByUnionIdOrCardNo(@Param("unionId") String unionId, @Param("cardNo") String cardNo);

    public ParkingFeeDetail selectParkingFeeDetailByUnionId(String unionId);

    Integer sumFeeByThisShift(@Param("marketId") String marketId,@Param("parkingId") String parkingId);

    List<ParkingFeeDetail> getThisShiftRecord(ParkingFeeDetail parkingFeeDetail);

    ParkingFeeDetail getRecordByCardNoOrUnionId(ParkingFeeDetail parkingFeeDetail);

    public ParkingFeeDetail selectDetail(ParkingFeeDetail detail);

    List<ParkingFeeDetail> selectParkingFee(ParkingFeeDetail detail);

    ParkingFeePayDetailResponse getParkingFreePaySum(ParkingFeeDetailListRequest parkingFeeDetailListRequest);

    List<ParkingFeeDetailListResponse> getParkingFreeList(ParkingFeeDetailListRequest parkingFeeDetailListRequest);

   InterfaceResult selectByParkingFeeId(String parkingDetailId);
}
