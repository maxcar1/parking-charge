package com.maxcar.market.dao;

import com.maxcar.market.pojo.EleLabelRecord;
import com.maxcar.market.pojo.EleLabelRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EleLabelRecordMapper {
    int countByExample(EleLabelRecordExample example);

    int deleteByExample(EleLabelRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(EleLabelRecord record);

    int insertSelective(EleLabelRecord record);

    List<EleLabelRecord> selectByExample(EleLabelRecordExample example);

    EleLabelRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EleLabelRecord record, @Param("example") EleLabelRecordExample example);

    int updateByExample(@Param("record") EleLabelRecord record, @Param("example") EleLabelRecordExample example);

    int updateByPrimaryKeySelective(EleLabelRecord record);

    int updateByPrimaryKey(EleLabelRecord record);

    List<EleLabelRecord> getRecordList(EleLabelRecord eleLabelRecord);
}