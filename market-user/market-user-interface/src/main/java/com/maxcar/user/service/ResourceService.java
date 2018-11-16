package com.maxcar.user.service;

import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.Resource;

import java.util.List;

public interface ResourceService extends BaseService<Resource,String> {
    /**
    * @Description: 根据用户请求地址，获取该资源id
    * @Param: [url]
    * @return: java.lang.String
    * @Author: 罗顺锋
    * @Date: 2018/4/27
    */
    public String getResourceIdByUrl(String url);
}