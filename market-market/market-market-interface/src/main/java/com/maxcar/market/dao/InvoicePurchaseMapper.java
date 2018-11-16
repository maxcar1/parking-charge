package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.market.pojo.InvoicePurchase;
import com.maxcar.market.pojo.InvoicePurchaseExample;
import com.maxcar.market.vo.InvoicePurchaseRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvoicePurchaseMapper extends BaseDao<InvoicePurchase, String> {
    int countByExample(InvoicePurchaseExample example);

    int deleteByExample(InvoicePurchaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(InvoicePurchase record);

    int insertSelective(InvoicePurchase record);

    List<InvoicePurchase> selectByExample(InvoicePurchaseExample example);

    InvoicePurchase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InvoicePurchase record, @Param("example") InvoicePurchaseExample example);

    int updateByExample(@Param("record") InvoicePurchase record, @Param("example") InvoicePurchaseExample example);

    int updateByPrimaryKeySelective(InvoicePurchase record);

    int updateByPrimaryKey(InvoicePurchase record);

    List<InvoicePurchase> selectTicketList(InvoicePurchaseRequest invoicePurchaseRequest);

    int updateTicketById(String id);

    List<InvoicePurchase> selectTicketName(@Param("marketId") String marketId);
}