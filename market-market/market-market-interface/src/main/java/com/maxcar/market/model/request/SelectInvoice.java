package com.maxcar.market.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SelectInvoice implements Serializable {

    private static final long serialVersionUID = -7122664853473995671L;
    @NotNull(message = "市场id")
    private String marketId;
    private String vin;
    private int tenantType;
    private String billTimeStart;
    private String billTimeEnd;
    private String invoiceNo;
    private int curPage;
    private int pageSize;
    private String tenantId;

}
