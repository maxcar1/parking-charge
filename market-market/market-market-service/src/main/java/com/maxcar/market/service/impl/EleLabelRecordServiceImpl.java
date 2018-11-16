package com.maxcar.market.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.BaseService;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.market.dao.EleLabelRecordMapper;
import com.maxcar.market.pojo.EleLabelRecord;
import com.maxcar.market.service.EleLabelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("eleLabelRecordService")
public class EleLabelRecordServiceImpl extends BaseServiceImpl<EleLabelRecord,String> implements EleLabelRecordService{

    @Autowired
    private EleLabelRecordMapper eleLabelRecordMapper;

    @Override
    public BaseDao<EleLabelRecord, String> getBaseMapper() {
        return null;
    }

    @Override
    public int insertEleLabelRecord(EleLabelRecord record) {
        return eleLabelRecordMapper.insertSelective(record);
    }

    ;
}
