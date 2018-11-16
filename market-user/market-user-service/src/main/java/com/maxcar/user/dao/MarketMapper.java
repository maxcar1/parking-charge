package com.maxcar.user.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.MarketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketMapper extends BaseDao<Market,String> {
    int countByExample(MarketExample example);

    int deleteByExample(MarketExample example);

    int deleteByPrimaryKey(String id);

    int insert(Market record);

    int insertSelective(Market record);

    List<Market> selectByExample(MarketExample example);

    Market selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Market record, @Param("example") MarketExample example);

    int updateByExample(@Param("record") Market record, @Param("example") MarketExample example);

    int updateByPrimaryKeySelective(Market record);

    int updateByPrimaryKey(Market record);
    
    Market getMarket(String id);
}