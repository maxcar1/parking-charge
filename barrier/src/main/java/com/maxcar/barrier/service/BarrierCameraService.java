package com.maxcar.barrier.service;


import com.maxcar.barrier.pojo.BarrierCamera;

import java.util.List;

public interface BarrierCameraService {
    List<BarrierCamera> selectCameraByBarrierId(BarrierCamera barrierCamera);
}
