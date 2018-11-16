package com.maxcar.barrier.service;


import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.Car;

import java.util.List;

/**
 * @author
 * @create
 * @desc
 **/
public interface BarrierService {


    Car selectByRFID(String rfid);

    Barrier selectByBarrierId(String barrierId)throws Exception;

    List<Barrier> selectBarrierList(Barrier barrier);

    InterfaceResult updateOrDelBarrier(Barrier barrier);

    List<Barrier> selectBarrierByMarketId(String marketId);

    Barrier selectBarrierByIdAndMarketId(Barrier barrier);

    Barrier selectByBarrierId2(String brakeId);

    Barrier selectBarrierByRemoteHost(String remoteHost,String marketId);
}
