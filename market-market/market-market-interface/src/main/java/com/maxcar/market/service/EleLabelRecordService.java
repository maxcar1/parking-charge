package com.maxcar.market.service;

import com.maxcar.base.service.BaseService;
import com.maxcar.market.pojo.EleLabelRecord;

public interface EleLabelRecordService extends BaseService<EleLabelRecord,String>{

    int insertEleLabelRecord(EleLabelRecord record);

}
