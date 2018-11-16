package com.maxcar.stock.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckResultInfo {

	private static final long serialVersionUID = 1672569205110829222L;
	@JsonProperty("name")
	private String name;
	@JsonProperty("val")
	private String val;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
	
}
