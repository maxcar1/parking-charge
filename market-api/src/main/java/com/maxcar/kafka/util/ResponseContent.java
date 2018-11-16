package com.maxcar.kafka.util;

import net.sf.json.JSONObject;

import java.io.Serializable;

public class ResponseContent implements Serializable {
	private int resultCode;
	private String message;
	private String token;
	private JSONObject datas;

	public ResponseContent(int resultCode, String message) {
		this.resultCode = resultCode;
		this.message = message;
	}

	public ResponseContent() {
	}
	public static ResponseContent error(String message) {
		return error(2, message, null);
	}

	public static ResponseContent error(int resultCode, String message) {
		return error(resultCode, message, null);
	}

	public static ResponseContent error(int resultCode, String message, JSONObject datas) {
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
	public JSONObject getDatas() {
		return datas;
	}
	public void setDatas(JSONObject datas) {
		this.datas = datas;
	}	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
