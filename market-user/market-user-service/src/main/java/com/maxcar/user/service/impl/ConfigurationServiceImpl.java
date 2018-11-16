package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.JsonTools;
import com.maxcar.redis.service.SsoService;
import com.maxcar.user.dao.ConfigurationMapper;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.ConfigurationExample;
import com.maxcar.user.service.ConfigurationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service("configurationService")
public class ConfigurationServiceImpl extends BaseServiceImpl<Configuration, String> implements ConfigurationService {
    @Autowired
    private ConfigurationMapper configurationMapper;
    @Autowired
    private SsoService ssoService;

    public BaseDao<Configuration, String> getBaseMapper() {
        return configurationMapper;
    }

    //初始化加载配置文件到缓存中,永久缓存
    @PostConstruct
    public void init() throws Exception {
        //得到总览和市场的指标线
//        Configuration record = new Configuration();
//        List<String> configurations = configurationMapper.searchConfiguration(record);
//        for(String configuration:configurations){
//            record.setConfigurationKey(configuration);
//            ConfigurationExample example = new ConfigurationExample();
//            example.createCriteria().andConfigurationKeyEqualTo(configuration).andIsvalidEqualTo(1);
//            ssoService.setStringKey(configuration, JsonTools.toJson(configurationMapper.selectByExample(example)),-1);
//        }
    }

    @Override
    public List<Configuration> searchConfigurationByMarketId(String marketId) throws Exception {
        ConfigurationExample configurationExample = new ConfigurationExample();
        ConfigurationExample.Criteria criteria = configurationExample.createCriteria();
        criteria.andIsvalidEqualTo(1);
        criteria.andMarketIdEqualTo(marketId);
        return configurationMapper.selectByExample(configurationExample);
    }

    @Override
    public List<Configuration> searchConfigurationByKey(String key) throws Exception {
        ConfigurationExample configurationExample = new ConfigurationExample();
        ConfigurationExample.Criteria criteria = configurationExample.createCriteria();
        criteria.andIsvalidEqualTo(1);
        criteria.andConfigurationKeyEqualTo(key);
        return configurationMapper.selectByExample(configurationExample);
    }

    @Override
    public List<Configuration> searchConfiguration(Configuration configuration) throws Exception {
        ConfigurationExample configurationExample = new ConfigurationExample();
        ConfigurationExample.Criteria criteria = configurationExample.createCriteria();
        criteria.andIsvalidEqualTo(1);
        /*if(configuration.getManagerFlag()!=null && configuration.getManagerFlag()==1) {
            criteria.andMarketIdEqualTo(configuration.getMarketId());
        }*/
        if (StringUtils.isNotEmpty(configuration.getConfigurationKey())) {
            criteria.andConfigurationKeyEqualTo(configuration.getConfigurationKey());
        }
        return configurationMapper.selectByExample(configurationExample);
    }

//    @Override
//    public List<String> searchOtherConfiguration() throws Exception {
//        Configuration record = new Configuration();
//        List<String> configurations = configurationMapper.searchConfiguration(record);
//        return configurations;
//    }

    @Override
    public List<Configuration> searchConfiguration(String configurationKey) throws Exception {
        ConfigurationExample configurationExample = new ConfigurationExample();
        ConfigurationExample.Criteria criteria = configurationExample.createCriteria();
        criteria.andIsvalidEqualTo(1).andConfigurationKeyEqualTo(configurationKey);
        configurationExample.setOrderByClause(" configuration_value asc");
        return configurationMapper.selectByExample(configurationExample);
    }

    @Override
    public Boolean updateConfiguration(Configuration configuration) throws Exception {
        return configurationMapper.updateByPrimaryKeySelective(configuration) == 1;
    }


    /**
     * param:
     * describe: 根据configuration_key 和 configuration_value 获取 configuration_name
     * create_date:  lxy   2018/9/7  9:56
     **/
    @Override
    public String getNameByKeyAndValue(String key, String value) {
        return configurationMapper.getNameByKeyAndValue(key, value);
    }


}