package com.maxcar.market.service.impl;

import com.maxcar.market.dao.OpenBarrierRecordMapper;
import com.maxcar.market.pojo.OpenBarrierRecord;
import com.maxcar.market.pojo.OpenBarrierRecordExample;
import com.maxcar.market.service.OpenBarrierRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("openBarrierRecordService")
public class OpenBarrierRecordServiceImpl implements OpenBarrierRecordService {
    @Autowired
    private OpenBarrierRecordMapper openBarrierRecordMapper;

    @Override
    public int addRecord(OpenBarrierRecord openBarrierRecord) {
        return openBarrierRecordMapper.insertSelective(openBarrierRecord);
    }

    @Override
    public int getOpenBarrierCount(String marketId) {
        OpenBarrierRecordExample example=new OpenBarrierRecordExample();
        example.createCriteria().andMarketIdEqualTo(marketId).andIsValidEqualTo(1);
        return openBarrierRecordMapper.countByExample(example);
    }
}
