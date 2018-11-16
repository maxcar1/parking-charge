package com.maxcar.market.vo;

import com.maxcar.market.pojo.AreaShop;

/**
 * @author songxuefeng
 * @create 2018-08-27 16:37
 * @description: ${description}
 **/
public class AreaShopOp {
    private String op;//操作 add 新增 upd 修改 del 删除
    private AreaShop areaShop;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public AreaShop getAreaShop() {
        return areaShop;
    }

    public void setAreaShop(AreaShop areaShop) {
        this.areaShop = areaShop;
    }
}
