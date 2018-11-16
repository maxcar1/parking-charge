package com.maxcar.stock.pojo;

import java.io.Serializable;

public class IllumineIconTitleUrl implements Serializable {
	private String title;
	private String url;
	public IllumineIconTitleUrl(String title, String url) {
		this.title = title;
		this.url = url;
	}
	public IllumineIconTitleUrl() {
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
	@Override
	public String toString() {
		return "IllumineIconTitleUrl [title=" + title + ", url=" + url + "]";
	}
}
