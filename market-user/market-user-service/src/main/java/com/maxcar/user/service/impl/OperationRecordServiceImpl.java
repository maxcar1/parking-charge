package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.PageBean;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.user.dao.OperationRecordMapper;
import com.maxcar.user.entity.OperationRecord;
import com.maxcar.user.entity.OperationRecordExample;
import com.maxcar.user.service.OperationRecordService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("operationRecordService")
public class OperationRecordServiceImpl extends BaseServiceImpl<OperationRecord,String> implements OperationRecordService{

    @Autowired
    private OperationRecordMapper operationRecordMapper;
    @Override
    public BaseDao<OperationRecord, String> getBaseMapper() {
        return operationRecordMapper;
    }

    @Override
    public PageBean searchOperationRecord(OperationRecord operationRecord) {
        PageBean page = operationRecord.getPageBean();
        OperationRecordExample example = new OperationRecordExample();
        example.setOrderByClause(" register_time desc");
        OperationRecordExample.Criteria criteria = example.createCriteria();
        criteria.andIsvalidEqualTo(1);
        if(operationRecord.getManagerFlag()!=null && operationRecord.getManagerFlag()==1) {
            criteria.andMarketIdEqualTo(operationRecord.getMarketId());
        }
        if(operationRecord.getBeginTime()!=null){
            criteria.andRegisterTimeBetween(operationRecord.getBeginTime(), operationRecord.getEndTime()!=null?DateUtils.addDays(operationRecord.getEndTime(),1):operationRecord.getBeginTime());
        }
        if(StringUtils.isNotEmpty(operationRecord.getTrueName())){
            operationRecord.setTrueName("%" + operationRecord.getTrueName() + "%");
            criteria.andTrueNameLike(operationRecord.getTrueName());
        }
        page.setRecordCount(operationRecordMapper.countByExample(example));
        page = new PageBean(page.getCurrentPage(),page.getPageSize(),page.getRecordCount());//初始化
        
        example.setBeginPageIndex(page.getBeginPageIndex());//设置开始和结束
        example.setEndPageIndex(operationRecord.getPageBean().getPageSize());
        List<OperationRecord> operationRecords = operationRecordMapper.selectByExample(example);
        page.setRecordList(operationRecords);
        return page;
    }
}