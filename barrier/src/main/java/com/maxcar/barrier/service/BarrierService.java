package com.maxcar.barrier.service;


import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.barrier.pojo.InterfaceResult;

import java.util.List;

/**
 * @author
 * @create
 * @desc
 **/
public interface BarrierService {


    int deleteCarById(String id);

    Car selectByRFID(String rfid);

    Barrier selectByBarrierId(String barrierId)throws Exception;

    List<Barrier> selectBarrierList();

    InterfaceResult updateOrDelBarrier(Barrier barrier);

    Barrier getBarrierInfoLike(String barrierId,String topic);
}
