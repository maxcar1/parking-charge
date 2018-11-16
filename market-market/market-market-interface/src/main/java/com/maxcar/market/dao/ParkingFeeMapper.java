package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.market.model.request.AllParkingFeeRequest;
import com.maxcar.market.pojo.ParkingFee;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ParkingFeeMapper extends BaseDao<ParkingFee, String> {

    /**
     * param:
     * describe: 查询停车收费列表
     * create_date:  lxy   2018/8/29  16:11 
     **/
    List<ParkingFee> allParkingFee(AllParkingFeeRequest request);

    /**
     * param:
     * describe: 查看最近一次停车收费信息
     * create_date:  lxy   2018/8/29  16:10 
     **/
    ParkingFee getMaxParkingFee();

    ParkingFee getCurrentNewRecord(@Param("barrierId") String barrierId, @Param("marketId") String marketId);

    ParkingFee getCurrentBarrierRecord(@Param("barrierId") String barrierId, @Param("marketId") String marketId);

    ParkingFee selectEmployeeNewRecord(ParkingFee parkingFee);
}
