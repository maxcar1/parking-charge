package com.maxcar.barrier.service.impl;

import com.maxcar.barrier.dao.BarrierMapper;
import com.maxcar.barrier.dao.CarMapper;
import com.maxcar.barrier.pojo.*;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.util.Canstats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Service("barrierService")
public class BarrierServiceImpl implements BarrierService {
    public BarrierServiceImpl() {
        System.out.print(111);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CarMapper carMapper;

    @Resource
    private BarrierMapper barrierMapper;

    @Override
    public int deleteCarById(String id) {
        Car car = new Car();
        car.setId(id);
        car.setStockStatus(Canstats.deleteType);
//        car.setIsvalid(0);//删除逻辑
        return carMapper.updateByPrimaryKeySelective(car);
//        return carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Car selectByRFID(String rfid) {
        CarExample example = new CarExample();
        example.createCriteria().andIsvalidEqualTo(1).andRfidEqualTo(rfid);
        List<Car> cars = carMapper.selectByExample(example);
//        StockCarInfo stockCarInfo = stockCarInfoMapper.selectByRFID(rfid);
        return (cars!=null&cars.size()>0)?cars.get(0):null;
    }

    @Override
    public Barrier selectByBarrierId(String barrierId) throws Exception{
        Barrier barrier = barrierMapper.selectByBarrierId(barrierId);
//        List arrayList = new ArrayList();
//        if(barrier!=null) {
//            String[] ss = barrier.getInOutCar().split(",");
//            for (int j = 0; j < ss.length; j++) {
//                arrayList.add(ss[j]);
//            }
//            barrier.setInOutCarList(arrayList);
//        }
        return barrier;
    }

    @Override
    public List<Barrier> selectBarrierList() {
//        List<Barrier> list = barrierMapper.selectBarrierList();
//        ;
//        List arrayList = null;
//        for (int i = 0; i < list.size(); i++) {
//            String[] ss = list.get(i).getInOutCar().split(",");
//            arrayList = new ArrayList();
//            for (int j = 0; j < ss.length; j++) {
//                arrayList.add(ss[j]);
//            }
//            list.get(i).setInOutCarList(arrayList);
//        }
        return null;
//        return list;
    }

    @Override
    public Barrier getBarrierInfoLike(String barrierId, String topic) {
        return barrierMapper.getBarrierInfoLike(barrierId,topic);
    }

    @Override
    public InterfaceResult updateOrDelBarrier(Barrier barrier) {
        InterfaceResult interfaceResult = new InterfaceResult();
//        if (barrier.getStatus() != null && barrier.getStatus().equals("1")) {//  1为删除 0为修改
//            int counts = barrierMapper.deleteByBarrierId(barrier.getBarrierId());
//            if (counts > 0) {
//                interfaceResult.setMsg("删除成功");
//            } else {
//                interfaceResult.setMsg("删除失败");
//            }
//        } else {
            int counts = barrierMapper.updateByPrimaryKeySelective(barrier);
            if (counts > 0) {
                interfaceResult.setMsg("更新成功");
            } else {
                interfaceResult.setMsg("更新失败");
            }
//        }
        return interfaceResult;
    }


}
