package com.maxcar.market.service;

import com.maxcar.market.pojo.OpenBarrierRecord;

public interface OpenBarrierRecordService {
    int addRecord(OpenBarrierRecord openBarrierRecord);

    int getOpenBarrierCount(String marketId);
}
