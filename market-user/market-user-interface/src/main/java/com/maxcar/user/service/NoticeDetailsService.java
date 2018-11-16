package com.maxcar.user.service;



import org.omg.CORBA.PUBLIC_MEMBER;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.NoticeDetails;
import com.maxcar.user.vo.NoticeRequest;

public interface NoticeDetailsService extends BaseService<NoticeDetails, String>{
	
	/**
     * @Description: 按条件查询消息记录
     * @Param: [NoticeRequest]
     * @Author: 朱峰波
     */
	public InterfaceResult searchNotice(NoticeRequest notice);
	
	/**
     * @Description: 新增或修改消息
     * @Param: [NoticeDetails]
     * @Author: 朱峰波
     */
	public InterfaceResult addOrUpdateNoticeDetails(NoticeDetails notice);
	
	/**
     * @Description: 根据Id查询消息详情
     * @Param: [Id]
     * @Author: 朱峰波
     */
	public InterfaceResult selectOne(String Id); 
	
	/**
     * @Description: 根据Id撤回消息
     * @Param: [Id]
     * @Author: 朱峰波
     */
	public InterfaceResult withdrawMessage(String Id);
}
