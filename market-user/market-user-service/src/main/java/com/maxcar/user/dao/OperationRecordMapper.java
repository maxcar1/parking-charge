package com.maxcar.user.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.OperationRecord;
import com.maxcar.user.entity.OperationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationRecordMapper extends BaseDao<OperationRecord,String>{
    int countByExample(OperationRecordExample example);

    int deleteByExample(OperationRecordExample example);

    int deleteByPrimaryKey(String operationId);

    int insert(OperationRecord record);

    int insertSelective(OperationRecord record);

    List<OperationRecord> selectByExample(OperationRecordExample example);

    OperationRecord selectByPrimaryKey(String operationId);

    int updateByExampleSelective(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);

    int updateByExample(@Param("record") OperationRecord record, @Param("example") OperationRecordExample example);

    int updateByPrimaryKeySelective(OperationRecord record);

    int updateByPrimaryKey(OperationRecord record);
}