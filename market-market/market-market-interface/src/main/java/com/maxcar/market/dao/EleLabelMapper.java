package com.maxcar.market.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.market.pojo.EleLabel;
import com.maxcar.market.pojo.EleLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EleLabelMapper extends BaseDao<EleLabel,String> {
    int countByExample(EleLabelExample example);

    int deleteByExample(EleLabelExample example);

    int deleteByPrimaryKey(String id);

    int insert(EleLabel record);

    int insertSelective(EleLabel record);

    List<EleLabel> selectByExample(EleLabelExample example);

    EleLabel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EleLabel record, @Param("example") EleLabelExample example);

    int updateByExample(@Param("record") EleLabel record, @Param("example") EleLabelExample example);

    int updateByPrimaryKeySelective(EleLabel record);

    int updateByPrimaryKey(EleLabel record);

    List<EleLabel> getEleLabel(EleLabel eleLabel);
}