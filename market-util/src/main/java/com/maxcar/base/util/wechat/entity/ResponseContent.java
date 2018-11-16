package com.maxcar.base.util.wechat.entity;

import java.io.Serializable;

public class ResponseContent implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
//	private static final long serialVersionUID = 1L;
    private int resultCode;
    private String message;
    private String token;
    //	private JSONObject datas;
    private Object datas;

    public static ResponseContent error(String message) {
        return error(2, message, null);
    }

    public static ResponseContent error(int resultCode, String message) {
        return error(resultCode, message, null);
    }

    public static ResponseContent error(int resultCode, String message, Object datas) {
        ResponseContent rc = new ResponseContent();
        rc.setResultCode(resultCode);
        rc.setMessage(message);
        rc.setDatas(datas);
        return rc;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resulCode) {
        this.resultCode = resulCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
