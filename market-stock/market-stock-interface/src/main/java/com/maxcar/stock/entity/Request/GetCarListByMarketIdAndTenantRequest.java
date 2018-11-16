package com.maxcar.stock.entity.Request;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class GetCarListByMarketIdAndTenantRequest implements Serializable {

    private static final long serialVersionUID = -8459125578571568153L;
    // 商户id
    private String tenant;

    // 市场ID
    private String marketId;

    @NotNull(message = "请求页数不能为null")
    private Integer curPage;

    @NotNull(message = "请求条数不能为null")
    private Integer pageSize;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
