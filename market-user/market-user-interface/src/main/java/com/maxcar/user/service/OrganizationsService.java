package com.maxcar.user.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.Organizations;

import java.util.List;

/**
* @Description: 机构服务
* @Param:
* @return:
* @Author: 罗顺锋
* @Date: 2018/5/16
*/
public interface OrganizationsService extends BaseService<Organizations,String> {


     /**
     * @Description: 机构列表
     * @Param: [market]
     * @return: java.util.List<com.maxcar.user.entity.Market>
     * @Author: 罗顺锋
     * @Date: 2018/5/16
     */
     public List<Organizations> searchOrganizations(Organizations organizations)throws Exception;

     /**
     * @Description: 新增或者修改机构
     * @Param: [organizations]
     * @return: com.maxcar.core.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/16
     */
     public InterfaceResult saveOrUpdateOrganizations(Organizations organizations)throws Exception;
     
//     public InterfaceResult delOrganizations(String id)throws Exception;
//     
//     public InterfaceResult updateByOrgId(String orgId)throws Exception;
     
       public InterfaceResult delOrUpdateOrgId(String id,int isvalid)throws Exception;

        List<Organizations> selectOrganizationsById(String id);

        List<Organizations> getOrganizationsByMarket(String marketId);

        List<Organizations> getOrgByCodeAndMarketId(String marketId);

    List<Organizations> getOrganizationsByMarketAndCode(String marketId, String code);
}