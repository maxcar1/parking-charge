package com.maxcar.market.service.mapperService;

import com.maxcar.base.util.Constants;
import com.maxcar.base.util.DateUtils;
import com.maxcar.market.dao.PropertyContractDetailMapper;
import com.maxcar.market.dao.PropertyContractMapper;
import com.maxcar.base.pojo.Magic;
import com.maxcar.market.model.request.GetPropertyContractAllRequest;
import com.maxcar.market.model.request.GetPropertyContractDetailListRequest;
import com.maxcar.market.model.response.GetPropertyContractAllResponse;
import com.maxcar.market.pojo.AreaShop;
import com.maxcar.market.pojo.PropertyContract;
import com.maxcar.market.pojo.PropertyContractDetail;
import com.maxcar.market.service.AreaShopService;
import com.maxcar.market.utils.ToolUtils;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyContractDetailMapperService {

    @Autowired
    private PropertyContractDetailMapper propertyContractDetailMapper;

    @Autowired
    private PropertyContractMapper propertyContractMapper;

    @Autowired
    private UserTenantService userTenantService;

    @Autowired
    private AreaShopService areaShopService;

    /**
     * param:
     * describe: 新增合同详情
     * create_date:  lxy   2018/8/20  11:24
     **/
    public boolean insertSelective(PropertyContractDetail propertyContractDetail) {
        return ToolUtils.isOperationSuccess(propertyContractDetailMapper.insertSelective(propertyContractDetail));
    }

    /**
     * param:
     * describe: 根据合同ID修改合同详情信息 用于终止合同
     * create_date:  lxy   2018/8/18  21:55
     **/
    public boolean updateDetailByPropertyContractId(PropertyContractDetail propertyContractDetail) {
        return ToolUtils.isOperationSuccess(propertyContractDetailMapper.updateDetailByPropertyContractId(propertyContractDetail));
    }

    /**
     * param:
     * describe: 根据条件查询合同详情列表
     * create_date:  lxy   2018/8/18  21:45
     **/
    public List<PropertyContractDetail> getPropertyContractDetailList(GetPropertyContractDetailListRequest request) {

        // 查看合同的物业列表
        List<PropertyContractDetail> list = propertyContractDetailMapper.getAllByPropertyContractId(request);

        if (null == list || list.isEmpty()) {
            return null;
        }

        list.forEach(x -> {
            /*String areaName = "";
            int areaTotal = 0;
            // 获取物业相关信息
            String[] areaIds = x.getAreaId().split(",");

            for (int i = 0; i < areaIds.length; i++) {
                // 修改物业状态
                AreaShop areashop = areaShopService.getAreashop(areaIds[i]);
                if (null != areashop) {
                    areaName += areashop.getAreaNo();
                    areaTotal += areashop.getParkSpace();
                    if (i < areaIds.length - 1) {
                        areaName += ",";
                    }
                }
            }*/

            AreaShop areashop = areaShopService.getAreashop(x.getAreaId());

            x.setAreaName(null == areashop ? "" : areashop.getAreaNo());
            x.setAreaTotal(null == areashop ? 0 : areashop.getParkSpace());
            x.setArea(null == areashop ? "" : areashop.getAreaId());
            x.setAreaStatus(null == areashop ? null : areashop.getRentStatus());

        });

        return list;
    }


    /**
     * param:
     * describe: 查看合同详情 封装区域信息
     * create_date:  lxy   2018/8/21  11:05
     **/
    public PropertyContractDetail getPropertyContractDetailById(String propertyContractDetailId) {

        PropertyContractDetail propertyContractDetail = propertyContractDetailMapper.selectByPrimaryKey(propertyContractDetailId);

        if (null == propertyContractDetail) {
            return null;
        }
        //封装区域信息

       /* String areaName = "";
        int areaTotal = 0;
        // 获取物业相关信息
        String[] areaIds = propertyContractDetail.getAreaId().split(",");

        for (int i = 0; i < areaIds.length; i++) {
            //  获取物业状态
            AreaShop areashop = areaShopService.getAreashop(areaIds[i]);
            if (null != areashop) {
                areaName += areashop.getAreaNo();
                areaTotal += areashop.getParkSpace();
                if (i < areaIds.length - 1) {
                    areaName += ",";
                }
            }
        }*/
        AreaShop areashop = areaShopService.getAreashop(propertyContractDetail.getAreaId());
        propertyContractDetail.setAreaName(null == areashop ? "" : areashop.getAreaNo());
        propertyContractDetail.setAreaTotal(null == areashop ? 0 : areashop.getParkSpace());
        propertyContractDetail.setArea(null == areashop ? "" : areashop.getAreaId());
        return propertyContractDetail;

    }


    /**
     * param:
     * describe: 定时扫描合同到期or终止时间 调用
     * create_date:  lxy   2018/8/22  16:06
     **/
    public void endPropertyContract() throws Exception {
        // 扫描合同到期日期
        GetPropertyContractDetailListRequest endTimerequest = new GetPropertyContractDetailListRequest();
        // 合同正常的
        endTimerequest.setPropertyContractDetailStatus(Magic.CONTRACT_STATUS_NORMAL);
        // 合同到期日期小于今天的
        endTimerequest.setEndTime(DateUtils.parseDate(Constants.dateformat.format(new Date()), "yyyy-MM-dd hh:mm:ss"));
        // 2 表示 合同到期日期小于今天
        endTimerequest.setEndTimeStatus(2);

        endContract(endTimerequest, Magic.CONTRACT_STATUS_END_TIME_LIMIT);

        // 扫描合同终止日期
        GetPropertyContractDetailListRequest endRequest = new GetPropertyContractDetailListRequest();
        // 合同正常的
        endRequest.setPropertyContractDetailStatus(Magic.CONTRACT_STATUS_NORMAL);
        // 合同到期日期小于今天的
        endRequest.setEndTime(DateUtils.parseDate(Constants.dateformat.format(new Date()), "yyyy-MM-dd hh:mm:ss"));
        // 2 表示 合同终止日期小于今天
        endRequest.setEndTimeStatus(3);

        endContract(endRequest, Magic.CONTRACT_STATUS_END);
    }

    /**
     * param:
     * describe: 终止或到期合同
     * create_date:  lxy   2018/8/23  15:59
     **/
    private void endContract(GetPropertyContractDetailListRequest request, Integer status) {
        List<PropertyContractDetail> list = propertyContractDetailMapper.getAllByPropertyContractId(request);
        // 根据子合同 更新主合同状态
        if (null != list && !list.isEmpty()) {
            //每更改一个子合同状态需要查询主合同下是否还有其他子合同状态 如果其他子合同没有正常的 则合同状态为到期
            list.forEach(x -> {
                // 更改子合同状态
                PropertyContractDetail updatePropertyContractDetail = new PropertyContractDetail();
                updatePropertyContractDetail.setId(x.getId());
                updatePropertyContractDetail.setPropertyContractDetailStatus(status);
                updatePropertyContractDetail.setUpdateTime(new Date());

                if (ToolUtils.isOperationSuccess(propertyContractDetailMapper.updateByPrimaryKeySelective(updatePropertyContractDetail))) {
                    // 释放物业状态
                    String[] areaIds = x.getAreaId().split(",");
                    for (int i = 0; i < areaIds.length; i++) {
                        areaShopService.updateRentById(areaIds[i], Magic.AREA_SHOP_RENT_STATUS_NOTRENT);
                    }
                    // 更新主合同状态
                    updatePropertyContractDetail(x.getPropertyContractId(), status);
                }

            });
        }
    }


    /**
     * param:
     * describe: 查看并更新主合同状态
     * create_date:  lxy   2018/8/23  14:40
     **/
    public void updatePropertyContractDetail(String propertyContractId, Integer status) {
        // 查看该合同下子合同状态是否存在正常的 不存在修改主合同状态
        GetPropertyContractDetailListRequest request = new GetPropertyContractDetailListRequest();
        request.setPropertyContractDetailStatus(Magic.CONTRACT_STATUS_NORMAL);
        request.setPropertyContractId(propertyContractId);
        List<PropertyContractDetail> list = propertyContractDetailMapper.getAllByPropertyContractId(request);
        if (null == list || list.isEmpty()) {
            // 没有正常的子合同 更新主合同
            PropertyContract propertyContract = propertyContractMapper.selectByPrimaryKey(propertyContractId);

            if (null != propertyContract && !status.equals(propertyContract.getStatus())) {
                // 该主合同下没有正常的合同 修改合同状态
                PropertyContract record = new PropertyContract();
                record.setId(propertyContractId);
                record.setStatus(status);
                record.setUpdateTime(new Date());
                if (ToolUtils.isOperationSuccess(propertyContractMapper.updateByPrimaryKeySelective(record))) {
                    // 更改商户信息
                    updateTenant(propertyContract.getTenantId(), status);
                }
            }
        }
        return;
    }

    /**
     * param:
     * describe: 查看并更新商户状态
     * create_date:  lxy   2018/8/23  14:44
     **/
    private void updateTenant(String tenantId, Integer status) {
        // 查看该商户下是否存在正常的合同 不存在修改商户信息
        GetPropertyContractAllRequest request = new GetPropertyContractAllRequest();
        request.setTenantId(tenantId);
        request.setPropertyContractStatus(Magic.CONTRACT_STATUS_NORMAL);
        List<GetPropertyContractAllResponse> all = propertyContractMapper.getAll(request);
        if (null == all || all.isEmpty()) {
            //没有正常的主合同 更新商户
            UserTenant tenant = new UserTenant();
            tenant.setId(tenantId);
            UserTenant userTenant = userTenantService.selectUserTenant(tenant);
            if (null != userTenant && !status.equals(userTenant.getStatus())) {
                UserTenant updateUserTenant = new UserTenant();

                updateUserTenant.setId(userTenant.getId());
                updateUserTenant.setStatus(status.toString());

                userTenantService.updateUserTenantById(updateUserTenant);
            }
        }
        return;
    }

}
