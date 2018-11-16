package com.maxcar.market.dao;

import com.maxcar.market.model.request.GetAllPayDetailRequest;
import com.maxcar.market.pojo.PropertyContractPayDetail;
import com.maxcar.market.pojo.PropertyContractPayDetailExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PropertyContractPayDetailMapper {
    int countByExample(PropertyContractPayDetailExample example);

    int deleteByExample(PropertyContractPayDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(PropertyContractPayDetail record);

    int insertSelective(PropertyContractPayDetail record);

    List<PropertyContractPayDetail> selectByExample(PropertyContractPayDetailExample example);

    PropertyContractPayDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PropertyContractPayDetail record, @Param("example") PropertyContractPayDetailExample example);

    int updateByExample(@Param("record") PropertyContractPayDetail record, @Param("example") PropertyContractPayDetailExample example);

    int updateByPrimaryKeySelective(PropertyContractPayDetail record);

    int updateByPrimaryKey(PropertyContractPayDetail record);

    /**
     * param:
     * describe: 查询合同缴费记录
     * create_date:  lxy   2018/8/20  14:51
     **/
    List<PropertyContractPayDetail> getAll(GetAllPayDetailRequest request);
}