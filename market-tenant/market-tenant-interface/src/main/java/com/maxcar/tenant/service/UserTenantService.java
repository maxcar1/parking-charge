package com.maxcar.tenant.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.tenant.pojo.UserTenant;

import java.util.List;

/**
 * Created by chiyanlong on 2018/8/16.
 */
public interface UserTenantService extends BaseService<UserTenant,String> {

    PageInfo selectUserTenantList(UserTenant userTenant);

    List<UserTenant> list(UserTenant userTenant);

    InterfaceResult addUserTenant(UserTenant userTenant);

    InterfaceResult getUserTenantCount(UserTenant userTenant);
    
    UserTenant selectByPrimaryKey(String id);

    UserTenant selectUserTenant(UserTenant userTenant);

    List<UserTenant> getAllTenantName(String marketId);

    InterfaceResult updateUserTenantById(UserTenant userTenant);

    List<UserTenant> getTenantNameLike(String tenantName);

    /**
     * 获取市场商户总数
     * Created by 沈中宗
     * @param marketId  市场ID
     * @return  商户总数
     */
    int countTenant(String marketId,String tenantId,String areaName);

    /**
     * 根据区域Id获取商户
     * Created by 沈中宗
     * @param marketId
     * @param tenantArea
     * @return
     */
    List<UserTenant> selectAreaByName(String marketId,String tenantArea);

    /**
     * 根据商户id获取商户名
     * Created by 沈中宗
     * @param
     * @return  商户名
     */
    String selectByTenanId(String tenanId);
    /**
     * 分页获取所有商户
     * Created by 沈中宗
     * @param
     * @return  商户集合
     */
    List<UserTenant> selectAll(Integer pageNum, Integer pageSize, String tenant,String marketId);
    
    /**
     * 获取所有商户
     * Created by 朱峰波
     * @param
     * @return  商户集合
     */
    //List<UserTenant> selectAllList(String tenant,String marketId);
    
    
    /**
     * 根据区域Id查询列表
     * Created by 朱峰波
     * @param
     * @return  商户集合
     */
    List<UserTenant> selectByTenantArea(String marketId,String tenantArea);

    int countTenantArea(String marketId, String areaName);
}
