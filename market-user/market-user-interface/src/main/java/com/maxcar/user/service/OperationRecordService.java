package com.maxcar.user.service;

import com.maxcar.base.pojo.PageBean;
import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.OperationRecord;
import com.maxcar.user.entity.OperationRecordExample;

import java.util.List;

public interface OperationRecordService extends BaseService<OperationRecord,String> {
//    int countByExample(OperationRecordExample example);
//
//    int deleteByExample(OperationRecordExample example);
//
//    int deleteByPrimaryKey(String operationId);
//
//    int insert(OperationRecord record);
//
//    int insertSelective(OperationRecord record);
//
//    List<OperationRecord> selectByExample(OperationRecordExample example);
//
//    OperationRecord selectByPrimaryKey(String operationId);
//
//    int updateByExampleSelective(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);
//
//    int updateByExample(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);
//
//    int updateByPrimaryKeySelective(OperationRecord record);
//
//    int updateByPrimaryKey(OperationRecord record);
    /**
    * @Description: 根据条件筛选操作日志列表
    * @Param: [operationRecord]
    * @return: java.util.List<com.maxcar.user.entity.OperationRecord>
    * @Author: 罗顺锋
    * @Date: 2018/5/18
    */
    public PageBean searchOperationRecord(OperationRecord operationRecord);
}