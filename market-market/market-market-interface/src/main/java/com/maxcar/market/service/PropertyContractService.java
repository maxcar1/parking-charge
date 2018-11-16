package com.maxcar.market.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.PageBean;
import com.maxcar.base.service.BaseService;
import com.maxcar.market.model.request.*;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.model.response.GetPropertyContractAllPack;
import com.maxcar.market.model.response.GetPropertyContractAllResponse;
import com.maxcar.market.model.response.GetPropertyContractResponse;
import com.maxcar.market.pojo.PropertyContract;
import com.maxcar.market.pojo.PropertyContractDetail;
import sun.tools.jar.Main;

import java.util.List;

public interface PropertyContractService extends BaseService<PropertyContract, String> {

    /**
     * param:
     * describe: 新增合同
     * create_date:  lxy   2018/8/16  15:47
     **/
    boolean addPropertyContract(AddContractRequest request);

    /**
     * param:
     * describe: 导入合同列表
     * create_date:  lxy   2018/9/3  16:15
     **/
    List<GetPropertyContractAllPack> pageInfoGetPropertyContractAllResponse(GetPropertyContractAllRequest request);

    /**
     * param:
     * describe: 查看合同列表
     * create_date:  lxy   2018/8/16  19:12
     **/
    PageInfo<GetPropertyContractAllResponse> getPropertyContractAll(GetPropertyContractAllRequest request);

    /**
     * param:
     * describe: 修改合同信息
     * create_date:  lxy   2018/8/16  19:17
     **/
    boolean updatePropertyContract(UpdatePropertyContractRequest request);

    /**
     * param:
     * describe: 查看合同详情
     * create_date:  lxy   2018/8/17  11:50
     **/
    GetPropertyContractResponse getPropertyContract(GetPropertyContractRequest request);

    /**
     * param:
     * describe: 根据商户ID 查询正常订购物业信息
     * create_date:  lxy   2018/8/22  15:49
     **/
    PageInfo<PropertyContractDetail> getPropertyContractDetailList(GetPropertyContractDetailListRequest request);

    /**
     * param:
     * describe: 根据 市场ID 或者 商户ID  或者 区域ID 查询 车位信息 (三选其一)
     * create_date:  lxy   2018/9/26  11:15
     **/
    GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse getCarTotalByMarketIdOrTenantIdOrAreaId(GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest request) throws Exception;

    /**
     * param:
     * describe: 定时扫描 合同到期或终止
     * create_date:  lxy   2018/8/23  15:39
     **/
    void endPropertyContract() throws Exception;

}
