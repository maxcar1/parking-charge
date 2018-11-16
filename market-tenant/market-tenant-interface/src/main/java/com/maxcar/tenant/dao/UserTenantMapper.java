package com.maxcar.tenant.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.tenant.entity.InvoiceDetailsResponse;
import com.maxcar.tenant.entity.TenantInvoiceResponse;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.pojo.UserTenantExample;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserTenantMapper extends BaseDao<UserTenant,String> {
    int countByExample(UserTenantExample example);

    int deleteByExample(UserTenantExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserTenant record);

    int insertSelective(UserTenant record);

    List<UserTenant> selectByExample(UserTenantExample example);

    UserTenant selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserTenant record, @Param("example") UserTenantExample example);

    int updateByExample(@Param("record") UserTenant record, @Param("example") UserTenantExample example);

    int updateByPrimaryKeySelective(UserTenant record);

    int updateByPrimaryKey(UserTenant record);


    List<UserTenant> selectUserTenantList(UserTenant record);

    List<LinkedHashMap> selectUserTenantCount(UserTenant userTenant);

    UserTenant selectUserTenant(UserTenant userTenant);

    List<UserTenant> getAllTenantName(String marketId);

    List<UserTenant> getTenantNameLike(@Param("tenantName") String tenantName);

    /**
     * 商户总数
     * @param marketId
     * @return
     */
    int countTenant(@Param("marketId") String marketId);

    /**
     * 通过区域获取商户的id
     * @param areaName
     * @return
     */
    List<UserTenant> selectAreaByName(@Param("marketId") String marketId, @Param("tenantArea")String tenantArea);

    /**
     * 通过商户id获取商户名
     * @param
     * @return
     */
    String selectByTenanId(@Param("tenanId") String tenanId);
    /**
     * 获取所有商户
     * @param
     * @return
     */
    List<UserTenant> selectAll(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize,@Param("marketId")String marketId,@Param("tenant")String tenant);

    /**
     * 获取所有商户
     * @param
     * @return
     */
    //List<UserTenant> selectAllList(@Param("marketId")String marketId,@Param("tenant")String tenant);
    
    /**
     * 根据区域id查询列表
     * @param
     * @return
     */
    List<UserTenant> selectByTenantArea(@Param("marketId") String marketId, @Param("tenantArea") String tenantArea);

    /**
     * 商户开票查询列表
     * @param map  当前页  页面大小  商户名  录入时间开始和结束
     * @return
     */
    List<TenantInvoiceResponse> selectTenantInvoice(Map<String,Object> map);

    /**
     * 开票详情
     * @param map  当前页  页面大小  商户Id
     * @return
     */
    List<InvoiceDetailsResponse> selectDetails(@Param("id") String id,@Param("CurPage") Integer CurPage,@Param("PageSize") Integer PageSize);

    /**
     * 商户开票车辆总价格
     * @param map  当前页  页面大小  商户名  录入时间开始和结束
     * @return
     */
    String selectTenantInvoiceSum(Map<String,Object> map);

    int countTenantArea(@Param("marketId") String marketId, @Param("areaName") String areaName);
}