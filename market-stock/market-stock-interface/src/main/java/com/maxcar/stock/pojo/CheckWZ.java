package com.maxcar.stock.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckWZ {

	private static final long serialVersionUID = 3556436319761354214L;
	@JsonProperty("baseinfo")
	private List<CheckResultInfo> baseinfo;
	
	@JsonProperty("city")
	private List<CheckResultInfo> city;
	
	
	@JsonProperty("fire")
	private List<CheckResultInfo> fire;
	
	@JsonProperty("water")
	private List<CheckResultInfo> water;
	
	
	@JsonProperty("accident")
	private List<CheckResultInfo> accident;


	public List<CheckResultInfo> getBaseinfo() {
		return baseinfo;
	}


	public void setBaseinfo(List<CheckResultInfo> baseinfo) {
		this.baseinfo = baseinfo;
	}


	public List<CheckResultInfo> getCity() {
		return city;
	}


	public void setCity(List<CheckResultInfo> city) {
		this.city = city;
	}


	public List<CheckResultInfo> getFire() {
		return fire;
	}


	public void setFire(List<CheckResultInfo> fire) {
		this.fire = fire;
	}


	public List<CheckResultInfo> getWater() {
		return water;
	}


	public void setWater(List<CheckResultInfo> water) {
		this.water = water;
	}


	public List<CheckResultInfo> getAccident() {
		return accident;
	}


	public void setAccident(List<CheckResultInfo> accident) {
		this.accident = accident;
	}
	
	
}
