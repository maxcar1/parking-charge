package com.maxcar.tenant.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DatePoor;
import com.maxcar.tenant.dao.IntegralDetailMapper;
import com.maxcar.tenant.dao.IntegralStatisticsMapper;
import com.maxcar.tenant.pojo.IntegralDetail;
import com.maxcar.tenant.pojo.IntegralStatistics;
import com.maxcar.tenant.pojo.IntegralStatisticsExample;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.IntegralStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.SampleModel;
import java.text.ParseException;
import java.util.*;

/**
 * Created by chiyanlong 2018/8/16.
 */
@Service("integralStatisticsService")
public class IntegralStatisticsServiceImpl extends BaseServiceImpl<IntegralStatistics,String> implements IntegralStatisticsService {

    @Autowired
    private IntegralStatisticsMapper integralStatisticsMapper;
    @Autowired
    private IntegralDetailMapper integralDetailMapper;

    @Override
    public Map selectIntegralStatistics(IntegralStatistics integralStatistics) throws ParseException {
        Map map = new HashMap();
        PageInfo pageInfo = selectIntegralStatisticsByMonth(integralStatistics); // 月份分页数据
        int score = integralStatisticsMapper.selectCountScore(integralStatistics);// 商户考核总积分
        integralStatistics.setMonths(0);
        IntegralStatistics tenantRowno = integralStatisticsMapper.selectTenantRowno(integralStatistics);
        Integer curMonthScore = 0;
        if (null != tenantRowno){
            curMonthScore = tenantRowno.getCountScore();// 本月积分
        }
        map.put("pageInfo",pageInfo);
        map.put("score",score);
        map.put("curMonthScore",curMonthScore);
        return map;
    }


    public PageInfo selectIntegralStatisticsByMonth(IntegralStatistics integralStatistics) throws ParseException {
        PageHelper.startPage(integralStatistics.getCurrentPage(),integralStatistics.getPageSize());
        List list = new ArrayList();
        int bigMonth = 12; // 默认查询13个月
        int smallMonth = 0;
        Date curDate = new Date();
        if (null != integralStatistics.getStartDate() && !"".equals(integralStatistics.getStartDate())){
           Date startDate = DatePoor.getDateForString(integralStatistics.getStartDate());
           Date endDate = DatePoor.getDateForString(integralStatistics.getEndDate());
            if (startDate.getTime() == endDate.getTime() && null != startDate){
                bigMonth = DatePoor.getMonthDiff(curDate, startDate);
                smallMonth = bigMonth;
            }else if (null != startDate){
                bigMonth = DatePoor.getMonthDiff(curDate, startDate);
                smallMonth = DatePoor.getMonthDiff(curDate, endDate);
            }
        }

        for (int i = smallMonth; i <= bigMonth; i++) {
            integralStatistics.setMonths(i);
            IntegralStatistics listTenant = integralStatisticsMapper.selectTenantRowno(integralStatistics);//查询市场排名
            IntegralStatistics listArea = integralStatisticsMapper.selectAreaRowno(integralStatistics);//查询区域排名
            if (listTenant != null && listArea != null) {
                listArea.setTenantRowno(listTenant.getTenantRowno());
                list.add(listArea);
            }
        }
        return new PageInfo(list);
    }

    @Override
    public InterfaceResult addIntegralStatistics(IntegralStatistics integralStatistics) {
        InterfaceResult interfaceResult=new InterfaceResult();
        int count=0;
        count=integralStatisticsMapper.insertSelective(integralStatistics);
        if (count>0){
            interfaceResult.InterfaceResult200("新增成功");
        }else {
            interfaceResult.InterfaceResult600("新增失败");
        }
        return interfaceResult;
    }

    @Override
    public InterfaceResult updateOrDelIntegralStatistics(IntegralStatistics integralStatistics) {
        InterfaceResult interfaceResult=new InterfaceResult();
        int count=0;
        if(integralStatistics.getIsvalid()==0){//逻辑删除
            count=integralStatisticsMapper.updateByPrimaryKey(integralStatistics);
        }else {
            count=integralStatisticsMapper.deleteByPrimaryKey(integralStatistics.getId());
        }
        if (count>0){
            interfaceResult.InterfaceResult200("成功");
        }else {
            interfaceResult.InterfaceResult600("失败");
        }
        return interfaceResult;
    }


    @Override
    public InterfaceResult scheduleAdd() {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<IntegralStatistics> list = integralDetailMapper.selectByNowMonth();
        integralStatisticsMapper.deleteMonthList(list);
        integralStatisticsMapper.insertMonthList(list);
        return null;
    }

    @Override
    public BaseDao<IntegralStatistics, String> getBaseMapper() {
        return integralStatisticsMapper;
    }
}
