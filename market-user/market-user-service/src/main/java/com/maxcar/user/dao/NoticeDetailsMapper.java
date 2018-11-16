package com.maxcar.user.dao;

import com.maxcar.user.entity.NoticeDetails;
import com.maxcar.user.entity.NoticeDetailsExample;
import com.maxcar.user.vo.NoticeRequest;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeDetailsMapper {
	
	 /**
     * param:
     * describe: 获取系统消息
     * create_date:  zfb
     **/
	List<NoticeRequest> searchNoticeDetails(NoticeRequest notice);

	 /**
     * param:
     * describe: 获取系统消息总数
     * create_date:  zfb
     **/
	int countNoticeRequest(NoticeRequest notice);
	
	//撤回
	int updateById(String Id);
	
	//插入
	int insertAll(NoticeDetails notice);
	
    int countByExample(NoticeDetailsExample example);

    int deleteByExample(NoticeDetailsExample example);

    int deleteByPrimaryKey(String id);

    int insert(NoticeDetails record);

    int insertSelective(NoticeDetails record);

    List<NoticeDetails> selectByExample(NoticeDetailsExample example);

    NoticeDetails selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NoticeDetails record, @Param("example") NoticeDetailsExample example);

    int updateByExample(@Param("record") NoticeDetails record, @Param("example") NoticeDetailsExample example);

    int updateByPrimaryKeySelective(NoticeDetails record);

    int updateByPrimaryKey(NoticeDetails record);
}