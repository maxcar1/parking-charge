package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.ShoppingGuide;
import com.maxcar.stock.pojo.ShoppingGuideExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShoppingGuideMapper {
    int countByExample(ShoppingGuideExample example);

    int deleteByExample(ShoppingGuideExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShoppingGuide record);

    int insertSelective(ShoppingGuide record);

    List<ShoppingGuide> selectByExample(ShoppingGuideExample example);

    ShoppingGuide selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShoppingGuide record, @Param("example") ShoppingGuideExample example);

    int updateByExample(@Param("record") ShoppingGuide record, @Param("example") ShoppingGuideExample example);

    int updateByPrimaryKeySelective(ShoppingGuide record);

    int updateByPrimaryKey(ShoppingGuide record);

    List<ShoppingGuide> listShoppingGuide(List<String> tIds);

    List<ShoppingGuide> listShopping();
}