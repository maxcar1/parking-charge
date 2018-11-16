package com.maxcar.stock.service.impl;

import com.maxcar.stock.dao.BarrierCameraMapper;
import com.maxcar.stock.pojo.BarrierCamera;
import com.maxcar.stock.pojo.BarrierCameraExample;
import com.maxcar.stock.service.BarrierCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("barrierCameraService")
public class BarrierCameraServiceImpl implements BarrierCameraService {

    @Autowired
    BarrierCameraMapper barrierCameraMapper;

    @Override
    public List<BarrierCamera> selectCameraByBarrierId(BarrierCamera barrierCamera) {
        BarrierCameraExample barrierCameraExample = new BarrierCameraExample();
        barrierCameraExample.createCriteria().andBarrierIdEqualTo(barrierCamera.getBarrierId())
                .andMarketIdEqualTo(barrierCamera.getMarketId()).andIsValidEqualTo(1);
        return barrierCameraMapper.selectByExample(barrierCameraExample);
    }
}
