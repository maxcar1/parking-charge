package com.maxcar.user.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.ConfigurationExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ConfigurationMapper extends BaseDao<Configuration, String> {
    int countByExample(ConfigurationExample example);

    int deleteByExample(ConfigurationExample example);

    int deleteByPrimaryKey(String configurationId);

    int insert(Configuration record);

    int insertSelective(Configuration record);

    List<Configuration> selectByExample(ConfigurationExample example);

    Configuration selectByPrimaryKey(String configurationId);

    int updateByExampleSelective(@Param("record") Configuration record, @Param("example") ConfigurationExample example);

    int updateByExample(@Param("record") Configuration record, @Param("example") ConfigurationExample example);

    int updateByPrimaryKeySelective(Configuration record);

    int updateByPrimaryKey(Configuration record);

    /**
     * @Description: 获取分类配置
     * @Param: []
     * @return: java.utils.List<com.maxcar.user.entity.Configuration>
     * @Author: 罗顺锋
     * @Date: 2018/5/25
     */
    List<String> searchConfiguration(Configuration record);

    /**
     * param:
     * describe: 根据 configuration_key 和 configuration_value 获取 configuration_name
     * create_date:  lxy   2018/9/7  10:08
     **/
    String getNameByKeyAndValue(@Param("key") String key, @Param("value") String value);
}