package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.market.model.request.GetPropertyContractDetailListRequest;
import com.maxcar.market.pojo.PropertyContract;
import com.maxcar.market.pojo.PropertyContractDetail;
import com.maxcar.market.pojo.PropertyContractDetailExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PropertyContractDetailMapper extends BaseDao<PropertyContractDetail,String> {
    int countByExample(PropertyContractDetailExample example);

    int deleteByExample(PropertyContractDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(PropertyContractDetail record);

    int insertSelective(PropertyContractDetail record);

    List<PropertyContractDetail> selectByExample(PropertyContractDetailExample example);

    PropertyContractDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PropertyContractDetail record, @Param("example") PropertyContractDetailExample example);

    int updateByExample(@Param("record") PropertyContractDetail record, @Param("example") PropertyContractDetailExample example);

    int updateByPrimaryKeySelective(PropertyContractDetail record);

    int updateByPrimaryKey(PropertyContractDetail record);

    /**
     * param:
     * describe: 根据合同ID查询物业列表
     * create_date:  lxy   2018/8/16  18:10
     **/
    List<PropertyContractDetail> getAllByPropertyContractId(GetPropertyContractDetailListRequest request);

    List<PropertyContractDetail> getPropertyContractDetailListByTenantId(String tenantId);

    /**
     * param:
     * describe: 根据合同ID修改合同详情
     * create_date:  lxy   2018/8/16  19:27
     **/
    int updateDetailByPropertyContractId(PropertyContractDetail record);

}