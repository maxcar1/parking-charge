package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.market.model.request.DealRequest;
import com.maxcar.market.model.request.SelectInvoice;
import com.maxcar.market.model.response.DealResponse;
import com.maxcar.market.pojo.Invoice;
import com.maxcar.market.pojo.InvoiceExample;
import java.util.List;
import java.util.Map;

import com.maxcar.market.pojo.TradeInformation;
import org.apache.ibatis.annotations.Param;

public interface InvoiceMapper extends BaseDao<Invoice, String> {

    int countByExample(InvoiceExample example);

    int deleteByExample(InvoiceExample example);

    int deleteByPrimaryKey(String id);

    int insert(Invoice record);

    int insertSelective(Invoice record);

    List<Invoice> selectByExample(InvoiceExample example);

    Invoice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    int updateByExample(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    int updateByPrimaryKeySelective(Invoice record);

    int updateByPrimaryKey(Invoice record);

    List<Invoice> selectInvoiceCountList(Invoice record);

    List<Invoice> selectInvoiceDetail(Invoice record);

    Invoice selectInvoiceTotalCount();

    Invoice selectInvoiceDetailById(String id);

    /**
     * 查询交易列表
     * shenzhongzong
     * @param deal
     * @return
     */
    List<DealResponse> selectDeal(DealRequest deal);

    /**
     * 市场总交易量
     * shenzhongzong
     * @return
     */
    int countDealNum(DealRequest deal);

    /**
     * 交易总价值
     * shenzhongzong
     * @return
     */
    Double sumDealMoney(DealRequest deal);
    /**
     * 库存车交易量
     * shenzhongzong
     * @return
     */
    int countStockCarNum(DealRequest deal);
    /**
     * 库存车交易总价值
     * shenzhongzong
     * @return
     */
    Double sumStockCarMoney(DealRequest deal);
    /**
     * 查询交易列表  总数
     * shenzhongzong
     * @param deal
     * @return
     */
    int countDeal(DealRequest deal);

    List<DealResponse> selectByVin(Map map);

    List<DealResponse> selectInvoice(SelectInvoice invoice);

    int countInvoice(SelectInvoice invoice);

    List<DealResponse> selectDealExport(DealRequest deal);

    List<DealResponse> selectInvoiceExport(SelectInvoice invoice);

    int countByVin(String vin);

    Map<String,Object> selectRanking(@Param("tenantId") String tenantId , @Param("marketId") String marketId);

    List<TradeInformation> detailsExcel(Invoice invoice);

    List<Invoice> selectInvoice2(SelectInvoice invoice);
}