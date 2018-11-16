package com.maxcar.market.dao;

import com.maxcar.market.pojo.DetailsEmergency;
import com.maxcar.market.pojo.OpenBarrierRecord;
import com.maxcar.market.pojo.OpenBarrierRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpenBarrierRecordMapper {
    int countByExample(OpenBarrierRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(OpenBarrierRecord record);

    int insertSelective(OpenBarrierRecord record);

    List<OpenBarrierRecord> selectByExample(OpenBarrierRecordExample example);

    OpenBarrierRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OpenBarrierRecord record, @Param("example") OpenBarrierRecordExample example);

    int updateByExample(@Param("record") OpenBarrierRecord record, @Param("example") OpenBarrierRecordExample example);

    int updateByPrimaryKeySelective(OpenBarrierRecord record);

    int updateByPrimaryKey(OpenBarrierRecord record);

    List<DetailsEmergency> selectByPrimaryMarketId(String marketId);

}