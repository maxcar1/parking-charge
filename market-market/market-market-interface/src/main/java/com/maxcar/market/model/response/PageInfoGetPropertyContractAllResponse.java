package com.maxcar.market.model.response;

import com.maxcar.market.model.base.BaseResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageInfoGetPropertyContractAllResponse extends BaseResponse implements Serializable {

    private List<GetPropertyContractAllPack> packList;

}
