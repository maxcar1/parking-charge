package com.maxcar.user.service;


import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.Configuration;

import java.util.List;
import java.util.Map;

public interface ConfigurationService extends BaseService<Configuration,String> {
    /**
    * @Description: 所有参数列表,可以根据市场查询
    * @Param: [configuration]
    * @return: java.utils.List<com.maxcar.permission.pojo.Configuration>
    * @Author: 罗顺锋
    * @Date: 2018/5/14
    */
    public List<Configuration> searchConfiguration(Configuration configuration)throws Exception;

    /**
     * 根据市场id获取对应的四根线
     * @param marketId
     * @return
     * @throws Exception
     */
    public List<Configuration> searchConfigurationByMarketId(String marketId) throws Exception;
    /**
    * @Description:
    * @Param: []
    * @return: java.utils.List<com.maxcar.user.entity.Configuration>
    * @Author: 罗顺锋
    * @Date: 2018/5/25
    */
    public List<Configuration> searchConfigurationByKey(String key)throws Exception;
    /**
    * @Description: 根据key获取配置项集合
    * @Param: [configurationKey]
    * @return: java.utils.List<com.maxcar.user.entity.Configuration>
    * @Author: 罗顺锋
    * @Date: 2018/5/25
    */
    public List<Configuration> searchConfiguration(String configurationKey)throws Exception;

    /**
     * 更新参数设置
     * @param configuration
     * @return
     * @throws Exception
     */
    public Boolean updateConfiguration(Configuration configuration)throws Exception;

    /**
     * param:
     * describe: 根据key和value获取name
     * create_date:  lxy   2018/9/7  10:10 
     **/
    String getNameByKeyAndValue(String key,String value);
}