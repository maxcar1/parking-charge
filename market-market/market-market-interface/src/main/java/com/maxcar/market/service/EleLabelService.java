package com.maxcar.market.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.market.pojo.EleLabel;
import com.maxcar.market.pojo.EleLabelRecord;

import java.util.List;

public interface EleLabelService extends BaseService<EleLabel,String> {
    InterfaceResult saveEleLabel(EleLabel eleLabel,String userId,String marketId) throws Exception;

    InterfaceResult getEleLabel(JSONObject params) throws Exception;

    InterfaceResult updateEleLabel(EleLabel eleLabel) throws Exception;

    InterfaceResult getRecordList(JSONObject params) throws Exception;
}
