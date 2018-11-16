package com.maxcar.redis.util;

import java.io.Serializable;

/**
 * @program: maxcar-util
 * @description: 操作对象返回类型
 * @author: 罗顺锋
 * @create: 2018-04-26 11:15
 **/
public class ProResult implements Serializable{
    private String code;
    private Object res;
    public ProResult(){}
    public ProResult(String code, Object res){
        this.code = code;
        this.res = res;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }

}
