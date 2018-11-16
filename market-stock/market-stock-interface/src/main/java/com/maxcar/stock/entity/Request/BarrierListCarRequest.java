package com.maxcar.stock.entity.Request;

import com.github.pagehelper.PageHelper;
import com.maxcar.base.pojo.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author sunyazhou
 * @Date 2018/10/23 13:59
 * @desc 道闸黑白名单添加车辆请求参数
 */
public class BarrierListCarRequest extends PageBean implements Serializable {

    private String tenantId;// 商户id
    private String brandName;// 品牌名称
    private String seriesName;// 车系名称


    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getTenantId() {

        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
