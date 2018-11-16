package com.maxcar.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.user.dao.NoticeDetailsMapper;
import com.maxcar.user.entity.NoticeDetails;
import com.maxcar.user.service.NoticeDetailsService;
import com.maxcar.user.vo.NoticeRequest;

/**
 * @description:系统消息
 * @author: zhufengbo
 * @create: 2018-08-25
 **/
@Service("noticeDetailsService")
public class NoticeDetailsServiceImpl extends BaseServiceImpl<NoticeDetails, String> implements NoticeDetailsService{
	@Autowired
	private NoticeDetailsMapper noticeDetailsMapper;
	
	@Override
	public BaseDao<NoticeDetails, String> getBaseMapper() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 消息列表条件查询
	 * @param NoticeRequest
	 * @return
	 */
	@Override
	public InterfaceResult searchNotice(NoticeRequest notice) {
		InterfaceResult interfaceResult=new InterfaceResult();
		try {
			if(notice.getCurPage()!=null&&notice.getPageSize()!=null&&notice.getPageSize()!=0){
				notice.setStartRow((notice.getCurPage()-1)*notice.getPageSize());
			}
			if (notice.getMessageTitle()!=null) {
				//去字符串全部空格
				String messageTitle = notice.getMessageTitle().replaceAll("\\s*", "");
				notice.setMessageTitle(messageTitle);
			}
			if (notice.getPushPeo() != null) {
				//去字符串全部空格
				String pushPeo = notice.getPushPeo().replaceAll("\\s*", "");
				notice.setPushPeo(pushPeo);
			}
			if (notice.getPushStatus() != null) {
				notice.setPushStatus(notice.getPushStatus());
			}
			if (notice.getPushTime() != null) {
				notice.setPushTime(notice.getPushTime());
			}
			
			
			
			List<NoticeRequest> list=noticeDetailsMapper.searchNoticeDetails(notice);
			for (NoticeRequest noticeRequest : list) {
				if (noticeRequest.getPushTime()!=null) {
					String pushTime = noticeRequest.getPushTime();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = format.parse(pushTime);
					String format2 = format.format(date);
					noticeRequest.setPushTime(format2);
				}
			}
			Integer count=noticeDetailsMapper.countNoticeRequest(notice);
			interfaceResult.setTotal(count);
			Map<String, Object> map=new HashMap<>();
			map.put("list", list);
			map.put("curPage",notice.getCurPage());
			map.put("pageSize",notice.getPageSize());
			map.put("total",count);
			interfaceResult.InterfaceResult200(map);
			return interfaceResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			interfaceResult.InterfaceResult500();
			return interfaceResult;
		}
		
	}

	
	/**
	 * 新增或编辑消息列表
	 * @param NoticeDetails
	 * @return
	 */
	@Override
	public InterfaceResult addOrUpdateNoticeDetails(NoticeDetails notice) {
		InterfaceResult interfaceResult=new InterfaceResult();
		try {
			if(StringUtils.isNotBlank(notice.getId())){
				notice.setPushStatus(1);
				
				Date day = new Date();    
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				notice.setPushTime(df.format(day));
					
				Integer num1 = noticeDetailsMapper.updateByPrimaryKeySelective(notice);
				if(num1 > 0){
					interfaceResult.InterfaceResult200("修改成功");
				}else{
					interfaceResult.InterfaceResult500("修改失败");
				}
			}else{
				notice.setId(UuidUtils.getUUID());
				Integer num=noticeDetailsMapper.insertSelective(notice);
				notice.setPushPeo(notice.getPushPeo());
				if (num>0) {
					interfaceResult.InterfaceResult200("插入成功");
				}else{
					interfaceResult.InterfaceResult500("插入失败");
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			interfaceResult.InterfaceResult500("请求失败");
			return interfaceResult;
		}
		interfaceResult.InterfaceResult200("请求成功");
		return interfaceResult;
	}

	
	/**
	 * 撤回消息
	 * @param Id
	 * @return
	 */
	@Override
	public InterfaceResult withdrawMessage(String Id) {
		
		InterfaceResult interfaceResult=new InterfaceResult();
		
		try {
			Integer num=noticeDetailsMapper.updateById(Id);
			if(num==1){
				interfaceResult.InterfaceResult200("撤回消息成功");
			}else{
				interfaceResult.InterfaceResult500("撤回消息失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			interfaceResult.InterfaceResult500("撤回消息失败");
			return interfaceResult;
		}
			interfaceResult.InterfaceResult200("撤回消息成功");
			return interfaceResult;
	}

	
	/**
	 * 根据Id查询列表
	 * @param NoticeRequest
	 * @return
	 */
	@Override
	public InterfaceResult selectOne(String Id) {
		InterfaceResult interfaceResult=new InterfaceResult();
		
		try {
			NoticeDetails noticeDetails=noticeDetailsMapper.selectByPrimaryKey(Id);
			
			if(noticeDetails!=null){
				interfaceResult.setTotal(1);
				interfaceResult.InterfaceResult200(noticeDetails);
				return interfaceResult;
			}else{
				interfaceResult.InterfaceResult500("查看失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			interfaceResult.InterfaceResult500("查看失败");
			return interfaceResult;
		}
			interfaceResult.InterfaceResult200("查看成功");
			return interfaceResult;
	}



}
