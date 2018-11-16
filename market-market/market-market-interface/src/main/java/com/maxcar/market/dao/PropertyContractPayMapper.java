package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.market.model.request.GetProPerContractPayAllRequest;
import com.maxcar.market.model.response.GetProPerContractPayAllResponse;
import com.maxcar.market.model.response.GetPropertyContractResponse;
import com.maxcar.market.model.response.SumByParkingFeeDetailIdResponse;
import com.maxcar.market.model.response.SumContractPayPriceResponse;
import com.maxcar.market.pojo.PropertyContract;
import com.maxcar.market.pojo.PropertyContractPay;
import com.maxcar.market.pojo.PropertyContractPayExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PropertyContractPayMapper extends BaseDao<PropertyContractPay,String> {
    int countByExample(PropertyContractPayExample example);

    int deleteByExample(PropertyContractPayExample example);

    int deleteByPrimaryKey(String id);

    int insert(PropertyContractPay record);

    int insertSelective(PropertyContractPay record);

    List<PropertyContractPay> selectByExample(PropertyContractPayExample example);

    PropertyContractPay selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PropertyContractPay record, @Param("example") PropertyContractPayExample example);

    int updateByExample(@Param("record") PropertyContractPay record, @Param("example") PropertyContractPayExample example);

    int updateByPrimaryKeySelective(PropertyContractPay record);

    int updateByPrimaryKey(PropertyContractPay record);

    /**
     * param:
     * describe: 获取合同缴费列表
     * create_date:  lxy   2018/8/17  16:02
     **/
    List<GetProPerContractPayAllResponse> getPayAll(GetProPerContractPayAllRequest request);

    /**
     * param:
     * describe: 根据合同id查询合同缴费详情
     * create_date:  lxy   2018/8/18  22:26
     **/
    PropertyContractPay selectByPropertyContractId(@Param("propertyContractId") String propertyContractId);

    /**
     * param:
     * describe: 统计合同收费
     * create_date:  lxy   2018/8/30  17:56 
     **/
    SumContractPayPriceResponse sumContractPayPrice(String marketId);
}