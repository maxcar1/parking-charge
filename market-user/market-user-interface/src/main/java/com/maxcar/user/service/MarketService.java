package com.maxcar.user.service;

import java.util.List;
import java.util.Map;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.Market;
/**
 * @Description: 市场服务层
 * @Param:
 * @return:
 * @Author: 罗顺锋
 * @Date: 2018/4/24
 */
public interface MarketService extends BaseService<Market,String> {
    
	 
     Market getMarket(String id);
	
     Market getMarketById(String id);

     //市场搜索接口
     public List<Market> searchMarket(Market market)throws Exception;

     /**
      * @Description: 新增或者修改市场
      * @Param: [user]
      * @return: boolean
      * @Author: 罗顺锋
      * @Date: 2018/4/25
      */
     public InterfaceResult saveOrUpdateMarket(Market market)throws Exception;
     /**
      * @Description: 修改市场状态，删除用户，启用，弃用用户，-1物理删除，0弃用，1启用
      * @Param: [status,id]
      * @return: boolean
      * @Author: 罗顺锋
      * @Date: 2018/4/25
      */
     public boolean updateMarketStatus(String id, Integer status)throws Exception;
}