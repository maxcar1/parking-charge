package com.maxcar.kafka.pojo;

import net.sf.json.JSONObject;

import java.io.Serializable;

/**
 * @author 罗顺峰
 * @create 2018-07-14 16:31
 * @desc 消息通用实体类
 **/
public class MessagePojo implements Serializable{

    private String market;
    private String serviceMethod;
    private String serviceName;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    private JSONObject data;
    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceParams() {
        return serviceParams;
    }

    public void setServiceParams(String serviceParams) {
        this.serviceParams = serviceParams;
    }

    private String serviceParams;
}
