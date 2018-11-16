package com.maxcar.barrier.service.impl;

import com.maxcar.barrier.dao.BarrierMapper;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.BarrierExample;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.dao.CarMapper;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Service("barrierService")
public class BarrierServiceImpl implements BarrierService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CarService carService;

    @Resource
    private BarrierMapper barrierMapper;

    @Override
    public Car selectByRFID(String rfid) {
        Car car = carService.selectByRfid(rfid);
        return car;
    }

    @Override
    public Barrier selectByBarrierId(String barrierId) throws Exception {
        Barrier barrier = barrierMapper.selectByBarrierId(barrierId);
        List arrayList = new ArrayList();
        if (barrier != null) {

            String[] ss = barrier.getInOutCar().split(",");
            for (int j = 0; j < ss.length; j++) {
                arrayList.add(ss[j]);
            }
            barrier.setInOutCarList(arrayList);
        }
        return barrier;
    }

    @Override
    public List<Barrier> selectBarrierList(Barrier barrier) {
        BarrierExample barrierExample = new BarrierExample();
        BarrierExample.Criteria criteria = barrierExample.createCriteria();
        criteria.andIsvalidEqualTo(1);
        if (barrier.getManagerFlag() != null && barrier.getManagerFlag() == 1) {
            criteria.andMarketIdEqualTo(barrier.getMarketId());
        }

        List<Barrier> list = barrierMapper.selectByExample(barrierExample);
//        ;        List<Barrier> list = barrierMapper.selectBarrierList();

//        List arrayList = null;
//        for (int i = 0; i < list.size(); i++) {
//            String[] ss = list.get(i).getInOutCar().split(",");
//            arrayList = new ArrayList();
//            for (int j = 0; j < ss.length; j++) {
//                arrayList.add(ss[j]);
//            }
//            list.get(i).setInOutCarList(arrayList);
//        }
        return list;
    }

    @Override
    public InterfaceResult updateOrDelBarrier(Barrier barrier) {
        InterfaceResult interfaceResult = new InterfaceResult();
        int counts = barrierMapper.updateByBarrierId(barrier);
        if (counts > 0) {
            interfaceResult.InterfaceResult200("更新成功");
        } else {
            interfaceResult.InterfaceResult600("更新失败");
        }
        return interfaceResult;

    }

    @Override
    public List<Barrier> selectBarrierByMarketId(String marketId) {
        return barrierMapper.selectBarrierByMarketId(marketId);
    }

    @Override
    public Barrier selectBarrierByIdAndMarketId(Barrier barrier) {
        return barrierMapper.selectBarrierByIdAndMarketId(barrier);
    }

    @Override
    public Barrier selectByBarrierId2(String brakeId) {
        return barrierMapper.selectByBarrierId(brakeId);
    }

    @Override
    public Barrier selectBarrierByRemoteHost(String remoteHost,String marketId) {
        return barrierMapper.selectBarrierByRemoteHost(remoteHost,marketId);
    }
}
