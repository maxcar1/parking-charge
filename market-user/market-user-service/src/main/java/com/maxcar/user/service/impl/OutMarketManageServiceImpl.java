package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.StringUtil;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.user.dao.OutMarketManageMapper;
import com.maxcar.user.entity.OutMarketManage;
import com.maxcar.user.entity.OutMarketManageExample;
import com.maxcar.user.service.OutMarketManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author sunyazhou
 * @Date 2018/10/17 17:49
 * @desc
 */
@Service("outMarketManageService")
public class OutMarketManageServiceImpl extends BaseServiceImpl<OutMarketManage,String> implements OutMarketManageService {

    @Autowired
    private OutMarketManageMapper outMarketManageMapper;

    @Override
    public BaseDao<OutMarketManage, String> getBaseMapper() {
        return outMarketManageMapper;
    }

    @Override
    public int insertSelective(OutMarketManage record) {
        record.setId(UuidUtils.gettimeFormartyyyyMMddHHmmss());
        record.setUpdateTime(new Date());
        return outMarketManageMapper.insertSelective(record);
    }

    @Override
    public OutMarketManage selectByMarket(String marketId) {
        OutMarketManageExample example = new OutMarketManageExample();
        OutMarketManageExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(marketId)){
            criteria.andMarketIdEqualTo(marketId);
        }
        criteria.andIsvalidEqualTo(1);
        List<OutMarketManage> outMarketManages = outMarketManageMapper.selectByExample(example);
        if (outMarketManages.size() == 1){
            return outMarketManages.get(0);
        }
        return null;
    }

    @Override
    public int updateByMarketId(OutMarketManage marketManage) {
        int i = 0;
        if (marketManage != null){
            // 不控制
            if (marketManage.getOutMarketType().equals(0)){
                marketManage.setOutMarketTime(null);
                marketManage.setUpdateTime(new Date());
            }
            // 审批出场
            if (marketManage.getOutMarketType().equals(2)){
                marketManage.setOutMarketTime(null);
                marketManage.setUpdateTime(new Date());
            }
            OutMarketManageExample example = new OutMarketManageExample();
            OutMarketManageExample.Criteria criteria = example.createCriteria();
            if (StringUtil.isNotEmpty(marketManage.getMarketId())){
                criteria.andMarketIdEqualTo(marketManage.getMarketId());
            }
            i = outMarketManageMapper.updateByExampleSelective(marketManage, example);
        }
        return i;
    }

    @Override
    public List<OutMarketManage> selectByOutMarketType(String type) {
        return outMarketManageMapper.selectByOutMarketType(type);
    }
}
