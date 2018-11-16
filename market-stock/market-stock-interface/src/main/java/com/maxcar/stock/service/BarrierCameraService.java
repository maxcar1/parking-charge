package com.maxcar.stock.service;

import com.maxcar.stock.pojo.BarrierCamera;

import java.util.List;

public interface BarrierCameraService {
    List<BarrierCamera> selectCameraByBarrierId(BarrierCamera barrierCamera);
}
