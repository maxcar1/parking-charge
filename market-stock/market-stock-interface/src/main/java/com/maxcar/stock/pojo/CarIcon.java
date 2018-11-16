package com.maxcar.stock.pojo;

import java.io.Serializable;

//图标
public class CarIcon implements Serializable {
	private  String id;
	private  String title;
	private  String url;
	private String field;
	private Integer orders;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	@Override
	public String toString() {
		return "CarIcon [id=" + id + ", title=" + title + ", url=" + url
				+ ", field=" + field + "]";
	}
}
