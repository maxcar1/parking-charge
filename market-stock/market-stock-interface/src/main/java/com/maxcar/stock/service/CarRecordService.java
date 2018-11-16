package com.maxcar.stock.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.service.BaseService;
import com.maxcar.stock.pojo.CarRecord;

public interface CarRecordService extends BaseService<CarRecord,String>{
	PageInfo listCarRecord(CarRecord car,String rfid);

	PageInfo listCarRecordCounts(CarRecord carRecord);

	PageInfo getCarRecordList(CarRecord carRecord);
}
