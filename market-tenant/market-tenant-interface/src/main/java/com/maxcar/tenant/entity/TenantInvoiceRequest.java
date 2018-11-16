package com.maxcar.tenant.entity;

import java.io.Serializable;

public class TenantInvoiceRequest  implements Serializable {
    private static final long serialVersionUID = 8899542013276165848L;

    private String tenantName;
    private String time;
    private int curPage;
    private int pageSize;
    private String marketId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
}
