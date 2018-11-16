package com.maxcar.stock.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.ShoppingGuide;

/**
 * @author luoqin
 * @Title: ShoppingGuideService
 * @ProjectName idea-workspace
 * @Description: TODO
 * @date 2018/8/23 15:28
 */
public interface ShoppingGuideService{
    PageInfo listExample(ShoppingGuide example);

    InterfaceResult addIntegralEvaluation(ShoppingGuide shoppingGuide);

    Boolean updateByPrimaryKeySelective(ShoppingGuide shoppingGuide);

    InterfaceResult deleteByPrimaryKey(String id,int isValid);
}
