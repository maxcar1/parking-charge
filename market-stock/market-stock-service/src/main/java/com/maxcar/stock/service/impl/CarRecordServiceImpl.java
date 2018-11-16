package com.maxcar.stock.service.impl;

import java.util.List;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.stock.dao.CarRecordMapper;
import com.maxcar.stock.pojo.CarRecord;
import com.maxcar.stock.service.CarRecordService;

@Service("carRecordService")
public class CarRecordServiceImpl extends BaseServiceImpl<CarRecord, String> implements CarRecordService {
    @Autowired
    CarRecordMapper carRecordMapper;

    @Override
    public PageInfo listCarRecord(CarRecord car, String rfid) {
        /*PageHelper.startPage(StringUtils.isBlank(car.getPageCount())?1 : car.getPageCount(),
				StringUtils.isBlank(car.getPageSize())?20 : car.getPageSize());*/
        PageHelper.startPage(car.getPageCount(), car.getPageSize());
        car.setRfid(rfid);
        PageHelper.startPage(car.getCurrentPage(), car.getPageSize());
        List<CarRecord> listCarRecord = carRecordMapper.listCarRecord(car);
        PageInfo pageInfo = new PageInfo(listCarRecord);
        return pageInfo;

    }

    @Override
    public PageInfo listCarRecordCounts(CarRecord carRecord) {
        PageHelper.startPage(carRecord.getCurrentPage(), carRecord.getPageSize());
        List<CarRecord> listCarRecordCounts = carRecordMapper.listCarRecordCounts(carRecord);
        PageInfo pageInfo = new PageInfo(listCarRecordCounts);
        return pageInfo;

    }

    @Override
    public PageInfo getCarRecordList(CarRecord carRecord) {
        PageHelper.startPage(carRecord.getCurrentPage(), carRecord.getPageSize());
        List<CarRecord> carRecordList = carRecordMapper.getCarRecordList(carRecord);
        PageInfo pageInfo = new PageInfo(carRecordList);
        return pageInfo;
    }


    @Override
    public BaseDao<CarRecord, String> getBaseMapper() {
        return carRecordMapper;
    }
}
