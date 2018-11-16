package com.maxcar.market.model.request;

import com.maxcar.market.model.base.BaseRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetPropertyContractDetailListRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = -2323228662881828072L;
    private String propertyContractId;

    private Integer propertyContractDetailStatus;

    private String tenantId;

    //到期时间
    private Date endTime;

    //扫面到期
    private Integer endTimeStatus;

    private String maketId;

    public String getMaketId() {
        return maketId;
    }

    public void setMaketId(String maketId) {
        this.maketId = maketId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPropertyContractId() {
        return propertyContractId;
    }

    public void setPropertyContractId(String propertyContractId) {
        this.propertyContractId = propertyContractId;
    }

    public Integer getPropertyContractDetailStatus() {
        return propertyContractDetailStatus;
    }

    public void setPropertyContractDetailStatus(Integer propertyContractDetailStatus) {
        this.propertyContractDetailStatus = propertyContractDetailStatus;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getEndTimeStatus() {
        return endTimeStatus;
    }

    public void setEndTimeStatus(Integer endTimeStatus) {
        this.endTimeStatus = endTimeStatus;
    }
}
