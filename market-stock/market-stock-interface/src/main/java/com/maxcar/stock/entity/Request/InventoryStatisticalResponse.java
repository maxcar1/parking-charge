package com.maxcar.stock.entity.Request;

import java.io.Serializable;

public class InventoryStatisticalResponse implements Serializable {

    private static final long serialVersionUID = 8251026819619585727L;
    // 商品车库存总数
    private Integer inventoryTotal;

    // 商品车当前库存总数
    private Integer inventoryNow;

    // 商品车库存总价值
    private Double valuationTotal;

    // 商品车当前库存总价值
    private Double valuationNow;

    // 商品车当前库存总天数
    private Integer inventoryDayTotal;

    //  商品车当前平均库存天数
    private Integer avgDayTotal;

    //  商品车当前平均库存价值
    private Double avgValuationNow;

    public Integer getInventoryTotal() {
        return inventoryTotal;
    }

    public void setInventoryTotal(Integer inventoryTotal) {
        this.inventoryTotal = inventoryTotal;
    }

    public Integer getInventoryNow() {
        return inventoryNow;
    }

    public void setInventoryNow(Integer inventoryNow) {
        this.inventoryNow = inventoryNow;
    }

    public Double getValuationTotal() {
        return valuationTotal;
    }

    public void setValuationTotal(Double valuationTotal) {
        this.valuationTotal = valuationTotal;
    }

    public Double getValuationNow() {
        return valuationNow;
    }

    public void setValuationNow(Double valuationNow) {
        this.valuationNow = valuationNow;
    }

    public Integer getInventoryDayTotal() {
        return inventoryDayTotal;
    }

    public void setInventoryDayTotal(Integer inventoryDayTotal) {
        this.inventoryDayTotal = inventoryDayTotal;
    }

    public Integer getAvgDayTotal() {
        return avgDayTotal;
    }

    public void setAvgDayTotal(Integer avgDayTotal) {
        this.avgDayTotal = avgDayTotal;
    }

    public Double getAvgValuationNow() {
        return avgValuationNow;
    }

    public void setAvgValuationNow(Double avgValuationNow) {
        this.avgValuationNow = avgValuationNow;
    }
}
