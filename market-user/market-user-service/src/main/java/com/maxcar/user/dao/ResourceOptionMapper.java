package com.maxcar.user.dao;

import com.maxcar.user.entity.ResourceOption;
import com.maxcar.user.entity.ResourceOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceOptionMapper {
    int countByExample(ResourceOptionExample example);

    int deleteByExample(ResourceOptionExample example);

    int deleteByPrimaryKey(String optionId);

    int insert(ResourceOption record);

    int insertSelective(ResourceOption record);

    List<ResourceOption> selectByExample(ResourceOptionExample example);

    ResourceOption selectByPrimaryKey(String optionId);

    int updateByExampleSelective(@Param("record") ResourceOption record, @Param("example") ResourceOptionExample example);

    int updateByExample(@Param("record") ResourceOption record, @Param("example") ResourceOptionExample example);

    int updateByPrimaryKeySelective(ResourceOption record);

    int updateByPrimaryKey(ResourceOption record);
}