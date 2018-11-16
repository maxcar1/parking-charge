package com.maxcar.util;

import com.maxcar.barrier.pojo.InterfaceResult;

import java.io.Serializable;

/**
 * 下发数据接口请求数据
 * Created by Administrator on 2018/9/4.
 */
public class PostParam implements Serializable{
    private String method;//请求方法
    private String url;//请求地址
    private String market;//市场
    private String messageTime;//数据时间
    private Object data;//shuju
    public boolean isOnlySend() {
        return onlySend;
    }

    public void setOnlySend(boolean onlySend) {
        this.onlySend = onlySend;
    }

    private boolean onlySend;//是否只是下发，不需要毁掉

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String tableName;//shuju
    public InterfaceResult getInterfaceResult() {
        return interfaceResult;
    }

    public void setInterfaceResult(InterfaceResult interfaceResult) {
        this.interfaceResult = interfaceResult;
    }

    private InterfaceResult interfaceResult;//返回结果

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
