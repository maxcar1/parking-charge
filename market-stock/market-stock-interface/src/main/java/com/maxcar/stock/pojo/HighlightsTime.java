package com.maxcar.stock.pojo;

import java.io.Serializable;
import java.util.List;

public class HighlightsTime implements Serializable {
	private Long time;
	private List<IllumineIconTitleUrl> list;
		
	public HighlightsTime(Long time, List<IllumineIconTitleUrl> list) {
		super();
		this.time = time;
		this.list = list;
	}
	
	public HighlightsTime() {
	}

	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public List<IllumineIconTitleUrl> getList() {
		return list;
	}
	public void setList(List<IllumineIconTitleUrl> list) {
		this.list = list;
	}
	
}
