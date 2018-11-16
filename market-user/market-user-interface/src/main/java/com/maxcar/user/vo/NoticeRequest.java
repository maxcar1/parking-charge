package com.maxcar.user.vo;

import java.io.Serializable;


public class NoticeRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Id;
	
	private String marketId;
	
	private String messageTitle;
	
	private String messageContent;
	
	private String pushTime;
	
	private String pushPeo;
	
	private String pushStatus;
	
	private Integer isvalid;
	/** 当前页 */
	private Integer curPage;
	/** 每页显示的个数 */
    private Integer pageSize=10;
    /** 当前页 */
    private Integer startRow;

	public Short getManagerFlag() {
		return managerFlag;
	}

	public void setManagerFlag(Short managerFlag) {
		this.managerFlag = managerFlag;
	}

	private Short managerFlag;//临时，用户是否为管理员属性


	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public Integer getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public String getPushPeo() {
		return pushPeo;
	}

	public void setPushPeo(String pushPeo) {
		this.pushPeo = pushPeo;
	}

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}


	



	
}
