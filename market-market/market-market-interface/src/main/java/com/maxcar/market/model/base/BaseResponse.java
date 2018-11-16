package com.maxcar.market.model.base;

import lombok.Data;

@Data
public class BaseResponse {

    private Integer pageNum;

    private Integer pageSize;

    private Integer total;
}
