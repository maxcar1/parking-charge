package com.maxcar.market.model.request;

import com.maxcar.market.pojo.PropertyContract;
import com.maxcar.market.pojo.PropertyContractDetail;
import lombok.Data;
import java.io.Serializable;
import java.util.List;


@Data
public class AddContractRequest implements Serializable {


    private static final long serialVersionUID = -260972120419307818L;
    private UserTenantPack userTenantPack;

    private PropertyContract propertyContract;

    private List<PropertyContractDetail> propertyContractDetailList;

}
