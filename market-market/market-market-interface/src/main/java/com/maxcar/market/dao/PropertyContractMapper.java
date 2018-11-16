package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.market.model.request.GetPropertyContractAllRequest;
import com.maxcar.market.model.response.GetPropertyContractAllResponse;
import com.maxcar.market.pojo.PropertyContract;
import com.maxcar.market.pojo.PropertyContractExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PropertyContractMapper extends BaseDao<PropertyContract, String> {
    int countByExample(PropertyContractExample example);

    int deleteByExample(PropertyContractExample example);

    int deleteByPrimaryKey(String id);

    int insert(PropertyContract record);

    int insertSelective(PropertyContract record);

    List<PropertyContract> selectByExample(PropertyContractExample example);

    PropertyContract selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PropertyContract record, @Param("example") PropertyContractExample example);

    int updateByExample(@Param("record") PropertyContract record, @Param("example") PropertyContractExample example);

    int updateByPrimaryKeySelective(PropertyContract record);

    int updateByPrimaryKey(PropertyContract record);

    /**
     * param:
     * describe: 查看 合同列表
     * create_date:  lxy   2018/8/16  16:24
     **/
    List<GetPropertyContractAllResponse> getAll(GetPropertyContractAllRequest request);

    /**
     * param:
     * describe: 查看当天最大合同编号
     * create_date:  lxy   2018/9/13  15:40
     **/
    String MaxContractNoByMarketId(String marketId);
}