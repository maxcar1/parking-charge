package com.maxcar.barrier.service.impl;

import com.maxcar.barrier.dao.CarRecordMapper;
import com.maxcar.barrier.pojo.CarRecord;
import com.maxcar.barrier.service.CarRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carRecordService")
public class CarRecordServiceImpl implements CarRecordService {
    @Autowired
    private CarRecordMapper carRecordMapper;

    @Override
    public int saveCarRecord(CarRecord carRecord) {
        return carRecordMapper.insertSelective(carRecord);
    }
}
