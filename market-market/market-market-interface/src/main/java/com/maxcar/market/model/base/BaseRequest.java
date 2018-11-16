package com.maxcar.market.model.base;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BaseRequest {

    @NotNull(message = "请求页数不能为null")
    private Integer curPage;
    @NotNull(message = "请求条数不能为null")
    private Integer pageSize;

    private Integer startRow;

    private Integer endRow;

    private Integer total;

    // 是否导出 1 为导出
    private boolean export;


}
