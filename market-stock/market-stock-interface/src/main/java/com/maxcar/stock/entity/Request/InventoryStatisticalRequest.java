package com.maxcar.stock.entity.Request;

import java.io.Serializable;

public class InventoryStatisticalRequest implements Serializable {

    private static final long serialVersionUID = 1754501620737133392L;
    private String marketId;

    private String tenantId;

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
