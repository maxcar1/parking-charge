package com.maxcar.market.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.RegExpValidatorUtils;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.dao.EleLabelMapper;
import com.maxcar.market.dao.EleLabelRecordMapper;
import com.maxcar.market.pojo.EleLabel;
import com.maxcar.market.pojo.EleLabelRecord;
import com.maxcar.market.service.EleLabelService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * 电子标签记录和管理共用一个服务
 * yangsj
 */
@Service("eleLabelService")
public class EleLabelServiceImpl extends BaseServiceImpl<EleLabel, String> implements EleLabelService {
    @Autowired
    private EleLabelMapper eleLabelMapper;
    @Autowired
    private EleLabelRecordMapper eleLabelRecordMapper;
    @Autowired
    private StaffService staffService;
    @Autowired
    private UserTenantService userTenantService;
    @Autowired
    private UserService userService;

    private static StringBuilder splitDate = new StringBuilder();

    private static final String PAGE_NUM = "pageNum";
    private static final String PAGE_SIZE = "pageSize";

    @Override
    public InterfaceResult saveEleLabel(EleLabel eleLabel, String userId,String marketId) throws Exception{
        String msg = "";
        int code = 0;
        InterfaceResult interfaceResult = new InterfaceResult();
        if (StringUtils.isBlank(eleLabel.getUsePerson())) {
            msg = "请选择领用人!";
            interfaceResult.InterfaceResult600(msg);
            return interfaceResult;
        }
        if (null == eleLabel.getUseNumber() ||
                !RegExpValidatorUtils.IsIntNumber(eleLabel.getUseNumber())) {
            msg = "请输入正确的领取张数!";
            interfaceResult.InterfaceResult600(msg);
            return interfaceResult;
        }
        if (StringUtils.isBlank(eleLabel.getUseDate())) {
            msg = "请输入领用时间!";
            interfaceResult.InterfaceResult600(msg);
            return interfaceResult;
        }

        eleLabel.setIsValid(1);
        eleLabel.setInsertTime(Calendar.getInstance().getTime());
        eleLabel.setUseTime(DateUtils.getDateByStr(eleLabel.getUseDate()));
        eleLabel.setId(UuidUtils.generateIdentifier());
        eleLabel.setUpdateTime(Calendar.getInstance().getTime());
        eleLabel.setScrapNumber(0);
        eleLabel.setUsedNumber(0);
        eleLabel.setInsertOperator(userId);
        eleLabel.setIsValid(1);
        eleLabel.setMarketId(marketId);
        code = eleLabelMapper.insertSelective(eleLabel);
        if (code == 1) {
            return interfaceResult;
        }

        interfaceResult.InterfaceResult500();
        return interfaceResult;
    }

    @Override
    public InterfaceResult getEleLabel(JSONObject params) throws Exception{
        InterfaceResult result = new InterfaceResult();
        EleLabel eleLabel = new EleLabel();
        if (null != params && !params.isEmpty()) {
            eleLabel.setMarketId(params.getString("marketId"));
            //是否分页
            int isPage = params.getIntValue("isPage");
            eleLabel.setIsValid(1);
            if (params.containsKey("beginDate")) {
                //传入日期 2018-09-12 更改 2018-09-12 00:00:00
                splitBeginDate(params);
                eleLabel.setBeginDate(splitDate.toString());
            }
            if (params.containsKey("endDate")) {
                splitEndDate(params);
                eleLabel.setEndDate(splitDate.toString());
            }
            if (params.containsKey("usePerson")) {
                eleLabel.setUsePerson(params.getString("usePerson"));
            }
            List<Staff> staffList = staffService.getMarketStaff(1, (params.getString("marketId") + ""));
            if (isPage == 1){
                PageHelper.startPage(null == params.getInteger(PAGE_NUM) ?
                        1 : params.getInteger(PAGE_NUM), null == params.getInteger(PAGE_SIZE) ?
                        20 : params.getInteger(PAGE_SIZE));
                List<EleLabel> eleLabels = eleLabelMapper.getEleLabel(eleLabel);
                int useNumberTotal = 0;
                int scrapNumberTotal = 0;
                for (EleLabel label : eleLabels) {
                    useNumberTotal += label.getUseNumber();
                    scrapNumberTotal += label.getScrapNumber();
                    getStaffName(staffList,label);
                }
                PageInfo pageInfo = new PageInfo<>(eleLabels, null == params.getInteger(PAGE_SIZE) ?
                        20 : params.getInteger(PAGE_SIZE));
                JSONObject json = (JSONObject) JSONObject.toJSON(pageInfo);
                json.put("scrapNumberTotal", scrapNumberTotal);
                json.put("useNumberTotal", useNumberTotal);
                result.InterfaceResult200(json);
            }else if (isPage == 0){
                List<EleLabel> eleLabelList =  eleLabelMapper.getEleLabel(eleLabel);
                for (EleLabel label : eleLabelList) {
                    getStaffName(staffList,label);
                }
                result.InterfaceResult200(eleLabelList);
            }

        } else {
            result.InterfaceResult406();
        }
        return result;
    }

    private static void getStaffName(List<Staff> staffList,EleLabel label){
        for (Staff staff:staffList){
            if (StringUtils.equals(label.getUsePerson(),staff.getId())){
                label.setUsePerson(staff.getStaffName());
                continue;
            }
            if (StringUtils.equals(label.getUpdateOperator(),staff.getId())){
                label.setUpdateOperator(staff.getStaffName());
                continue;
            }
        }
    }

    @Override
    public InterfaceResult updateEleLabel(EleLabel eleLabel) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        String msg = "";
        if (StringUtils.isBlank(eleLabel.getUpdateOperator())) {
            msg = "请选择处理人!";
            interfaceResult.InterfaceResult600(msg);
            return interfaceResult;
        }
        if (null == eleLabel.getScrapNumber() ||
                !RegExpValidatorUtils.IsIntNumber(eleLabel.getScrapNumber())) {
            msg = "请输入正确的处理张数!";
            interfaceResult.InterfaceResult600(msg);
            return interfaceResult;
        }
        if (StringUtils.isBlank(eleLabel.getDealDate())) {
            msg = "请输入处理时间!";
            interfaceResult.InterfaceResult600(msg);
            return interfaceResult;
        }
        //处理张数不能大于领用张数
        eleLabel.setIsValid(1);
        eleLabel.setUpdateTime(DateUtils.getDateByStr(eleLabel.getDealDate()));
        List<EleLabel> eleLabels = eleLabelMapper.getEleLabel(eleLabel);
        if (null != eleLabels && eleLabels.size() == 1) {
            if (eleLabel.getScrapNumber() > eleLabels.get(0).getUseNumber()) {
                msg = "处理张数不能大于领用张数!";
                interfaceResult.InterfaceResult600(msg);
                return interfaceResult;
            }
        }
        int code = eleLabelMapper.updateByPrimaryKeySelective(eleLabel);
        if (code == 1) {
            return interfaceResult;
        }
        interfaceResult.InterfaceResult500();
        return interfaceResult;
    }

    @Override
    public InterfaceResult getRecordList(JSONObject params) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        EleLabelRecord eleLabelRecord = new EleLabelRecord();
        eleLabelRecord.setIsValid(1);
        if (null != params && !params.isEmpty()) {
            eleLabelRecord.setMarketId(params.getString("marketId"));
            if (params.containsKey("carNumber")) {
                eleLabelRecord.setCarNumber(params.getString("carNumber"));
            }
            if (params.containsKey("vin")) {
                eleLabelRecord.setVin(params.getString("vin"));
            }
            if (params.containsKey("ownedTenant")) {
                eleLabelRecord.setOwnedTenant(params.getString("ownedTenant"));
            }
            if (params.containsKey("beginDate")) {
                splitBeginDate(params);
                eleLabelRecord.setBeginDate(splitDate.toString());
            }
            if (params.containsKey("endDate")) {
                splitEndDate(params);
                eleLabelRecord.setEndDate(splitDate.toString());
            }
            int isPage = params.getIntValue("isPage");
            List<UserTenant> userTenants = userTenantService.getAllTenantName(params.getString("marketId"));
            List<Staff> staffList = staffService.getMarketStaff(1, params.getString("marketId"));
            User user = new User();
            user.setMarketId(params.getString("marketId"));

            List<User> users = userService.getUserList(user);
            if (isPage == 1){
                PageHelper.startPage(null == params.getInteger(PAGE_NUM) ?
                        1 : params.getInteger(PAGE_NUM), null == params.getInteger(PAGE_SIZE) ?
                        20 : params.getInteger(PAGE_SIZE));
                List<EleLabelRecord> eleLabelRecords = eleLabelRecordMapper.getRecordList(eleLabelRecord);
                changeIdToName(userTenants,staffList,eleLabelRecords,users);
                PageInfo pageInfo = new PageInfo<>(eleLabelRecords, null == params.getInteger(PAGE_SIZE) ?
                        20 : params.getInteger(PAGE_SIZE));
                interfaceResult.InterfaceResult200(pageInfo);
            }else if (isPage == 0){
                List<EleLabelRecord> eleLabelList =  eleLabelRecordMapper.getRecordList(eleLabelRecord);
                changeIdToName(userTenants,staffList,eleLabelList,users);
                interfaceResult.InterfaceResult200(eleLabelList);
            }

        } else {
            interfaceResult.InterfaceResult406();
        }
        return interfaceResult;
    }

    private void changeIdToName(List<UserTenant> userTenants,
                                List<Staff> staffList,
                                List<EleLabelRecord> eleLabelRecords,
                                List<User> users){
        for (EleLabelRecord record:eleLabelRecords){
            for (UserTenant user:userTenants){
                if (StringUtils.equals(record.getOwnedTenant(),user.getId())){
                    record.setOwnedTenant(user.getTenantName());
                    break;
                }
            }

            for (User user : users){
                if (StringUtils.equals(record.getOperator(),user.getUserId())){
                    for (Staff staff:staffList){
                        if (StringUtils.equals(user.getStaffId(),staff.getId())){
                            record.setOperator(staff.getStaffName());
                            break;
                        }
                    }
                }
            }
        }
    }
    @Override
    public BaseDao<EleLabel, String> getBaseMapper() {
        return eleLabelMapper;
    }

    private static void splitBeginDate(JSONObject params){
        if (splitDate.length() >0){
            splitDate.delete(0,splitDate.length());
        }
        splitDate.append(params.getString("beginDate")).append(" 00:00:00");
    }

    private static void splitEndDate(JSONObject params){
        if (splitDate.length() >0){
            splitDate.delete(0,splitDate.length());
        }
        splitDate.append(params.getString("endDate")).append(" 23:59:59");
    }
}
