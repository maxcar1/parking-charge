package com.maxcar.base.dao;

import com.maxcar.base.pojo.IdDictionary;
import com.maxcar.base.pojo.IdDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdDictionaryMapper {
    int countByExample(IdDictionaryExample example);

    int deleteByExample(IdDictionaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IdDictionary record);

    int insertSelective(IdDictionary record);

    List<IdDictionary> selectByExample(IdDictionaryExample example);

    IdDictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IdDictionary record, @Param("example") IdDictionaryExample example);

    int updateByExample(@Param("record") IdDictionary record, @Param("example") IdDictionaryExample example);

    int updateByPrimaryKeySelective(IdDictionary record);

    int updateByPrimaryKey(IdDictionary record);

    Integer getId(IdDictionary idDictionary);
    Integer add(IdDictionary idDictionary);
    Integer updatePkid(IdDictionary idDictionary);

}