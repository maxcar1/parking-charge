package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.CheckCompany;
import com.maxcar.stock.pojo.CheckCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckCompanyMapper {
    int countByExample(CheckCompanyExample example);

    int deleteByExample(CheckCompanyExample example);

    int deleteByPrimaryKey(String id);

    int insert(CheckCompany record);

    int insertSelective(CheckCompany record);

    List<CheckCompany> selectByExample(CheckCompanyExample example);

    CheckCompany selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CheckCompany record, @Param("example") CheckCompanyExample example);

    int updateByExample(@Param("record") CheckCompany record, @Param("example") CheckCompanyExample example);

    int updateByPrimaryKeySelective(CheckCompany record);

    int updateByPrimaryKey(CheckCompany record);
}