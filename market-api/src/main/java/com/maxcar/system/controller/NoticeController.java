package com.maxcar.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.NoticeDetails;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.NoticeDetailsService;
import com.maxcar.user.vo.NoticeRequest;


/**
* @ClassName: NoticeController 
* @Description: 系统消息接口
* @author zhufengbo 
* @date 2018年8月25日 
*
 */
@RestController
@RequestMapping(value="/api/NoticeDetails")
public class NoticeController extends BaseController {
	
	@Autowired
	private NoticeDetailsService noticeDetailsService;
	
	 /**
	  * @Description: 消息列表
	  * @Param: 
	  * @return: 
	  * @Author: 朱峰波
	  */
	@RequestMapping(value="/getNotice")
	public InterfaceResult getNotice(@RequestBody NoticeRequest notice, HttpServletRequest request) throws Exception{
		User currentUser = getCurrentUser(request);
		InterfaceResult interfaceResult = new InterfaceResult();
		notice.setMarketId(currentUser.getMarketId());
		notice.setManagerFlag(currentUser.getManagerFlag());
		interfaceResult = noticeDetailsService.searchNotice(notice);
		return interfaceResult;
	}
	
	/**
	  * @Description: 新增或修改消息列表
	  * @Param: 
	  * @return: 
	  * @Author: 朱峰波
	  */
	@RequestMapping(value="/addOrUpdateNoticeDetails")
	public InterfaceResult addOrUpdateNoticeDetails(@RequestBody NoticeDetails notice , HttpServletRequest request) throws Exception{
		String marketId = getCurrentUser(request).getMarketId();
		notice.setMarketId(marketId);
		InterfaceResult interfaceResult=noticeDetailsService.addOrUpdateNoticeDetails(notice);
		return interfaceResult;
	}

	/**
	  * @Description: 撤回消息
	  * @Param: 
	  * @return: 
	  * @Author: 朱峰波
	  */
	@RequestMapping(value="/withdrawMessage/{Id}")
	public InterfaceResult withdrawMessage(@PathVariable("Id")String Id) throws Exception{
		
		InterfaceResult interfaceResult=noticeDetailsService.withdrawMessage(Id);
		
		return interfaceResult;
	}
	
	/**
	  * @Description: 根据Id查询列表
	  * @Param: 
	  * @return: 
	  * @Author: 朱峰波
	  */
	@RequestMapping(value="/selectOne/{Id}")
	public InterfaceResult selectOne(@PathVariable("Id")String Id) throws Exception{
		
		InterfaceResult interfaceResult=noticeDetailsService.selectOne(Id);
		
		return interfaceResult;
	}
	
}
