package com.maxcar.base.util;

public abstract class Page {
	private Integer curPage;
	private Integer pageSize = 10;
	private Integer total;
	
	public Page(){}

	public Page(Integer curPage, Integer pageSize, Integer total) {
		super();
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.total = total;
	}
	
	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		if(null == curPage || "".equals(curPage)){
			this.curPage = 1;
		}else{
			this.curPage = curPage;
		}
		
	}

	public Integer getPageSize() {
			return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (null == pageSize || "".equals(pageSize)) {
			this.pageSize = 10;
		}
		else if(pageSize > 1000){
			throw new RuntimeException("页码条数太大");
		}
		else{
			this.pageSize = pageSize;
		}
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
