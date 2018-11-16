package com.maxcar.market.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.service.BaseService;
import com.maxcar.market.model.request.GetAllPayDetailRequest;
import com.maxcar.market.model.request.GetProPerContractPayAllRequest;
import com.maxcar.market.model.request.UpdatePayDetailRequest;
import com.maxcar.market.model.response.*;
import com.maxcar.market.pojo.PropertyContractPay;

import java.util.List;

public interface PropertyContractPayService extends BaseService<PropertyContractPay, String> {

    /**
     * param:
     * describe: 统计缴费记录
     * create_date:  lxy   2018/8/30  18:02
     **/
    SumContractPayPriceResponse sumContractPayPrice(String marketId);

    /**
     * param:
     * describe: 合同缴费
     * create_date:  lxy   2018/8/21  14:21
     **/
    boolean updatePayDetail(UpdatePayDetailRequest request);

    /**
     * param:
     * describe: 查看合同缴费记录
     * create_date:  lxy   2018/8/21  10:32
     **/
    PageInfo<GetAllPayDetailResponse> getAllPayDetail(GetAllPayDetailRequest request);

    /**
     * param:
     * describe: 查看合同缴费列表
     * create_date:  lxy   2018/8/21  9:58
     **/
    PageInfo<GetProPerContractPayAllResponse> getPropertyContractPayAll(GetProPerContractPayAllRequest request);


    /**
     * param:
     * describe: 生成缴费记录相关 可用做定时任务
     * create_date:  lxy   2018/8/21  9:37
     **/
    void addPropertyContractPay();


}
