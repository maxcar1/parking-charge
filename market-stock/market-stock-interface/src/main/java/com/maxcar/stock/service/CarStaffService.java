package com.maxcar.stock.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.Car;

import java.util.List;
import java.util.Map;

public interface CarStaffService {
    InterfaceResult getCarList(JSONObject params) throws Exception;

    InterfaceResult getDetailInfo(JSONObject params) throws Exception;
}
