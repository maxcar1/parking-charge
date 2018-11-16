package com.maxcar.market.service.mapperService;

import com.maxcar.market.dao.ParkingFeeDetailMapper;
import com.maxcar.market.model.request.AllParkingFeeDetailRequest;
import com.maxcar.market.model.response.SumByParkingFeeDetailIdResponse;
import com.maxcar.market.pojo.ParkingFeeDetail;
import com.maxcar.market.utils.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParkingFeeDetailMapperService {

    @Autowired
    private ParkingFeeDetailMapper parkingFeeDetailMapper;

    /**
     * param:
     * describe:  查看 指定收费班次 收费金额
     * create_date:  lxy   2018/8/28  15:48
     **/
    public SumByParkingFeeDetailIdResponse sumByParkingFeeDetailId(String marketId,String parkingFeeId) {

        return parkingFeeDetailMapper.sumByParkingFeeDetailId(marketId,parkingFeeId);
    }

    /**
     * param:
     * describe: 查看收费详情列表
     * create_date:  lxy   2018/8/28  19:16
     **/
    public List<ParkingFeeDetail> allParkingFeeDetail(AllParkingFeeDetailRequest request) {

        return parkingFeeDetailMapper.allParkingFeeDetail(request);
    }

    /**
     * param:
     * describe: 根据UnionId 或者卡号 获取最近入场记录信息
     * create_date:  lxy   2018/8/30  11:43
     **/
    public ParkingFeeDetail getParkingFeeDetailByUnionIdOrCardNo(String unionId, String cardNo) {
        return parkingFeeDetailMapper.getParkingFeeDetailByUnionIdOrCardNo(unionId, cardNo);
    }

    /**
     * param:
     * describe: 更新停车收费记录
     * create_date:  lxy   2018/8/30  14:59
     **/
    public boolean updateByPrimaryKeySelective(ParkingFeeDetail parkingFeeDetail) {
        return ToolUtils.isOperationSuccess(parkingFeeDetailMapper.updateByPrimaryKeySelective(parkingFeeDetail));
    }

    /**
     * param:
     * describe: 新增停车收费记录
     * create_date:  lxy   2018/8/30  15:00
     **/
    public boolean  insertSelective(ParkingFeeDetail parkingFeeDetail){
        return ToolUtils.isOperationSuccess(parkingFeeDetailMapper.insertSelective(parkingFeeDetail));
    }
}
