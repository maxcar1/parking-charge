package com.maxcar.stock.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtils;
import com.maxcar.stock.dao.CarMapper;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.service.CarStaffService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.pojo.UserTenantExample;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.user.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 商户或市场员工车管理service
 * yangsj
 */
@Service("carStaffService")
public class CarStaffServiceImpl implements CarStaffService {
    private static final String TENANT_NAME = "tenantName";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String TOTAL_PARKING_NUM = "totalParkingNum";
    private static final String REST_PARKING_NUM = "restParkingNum";
    private static final String BAR = "--";
    @Autowired
    private CarMapper carMapper;

    @Autowired
    private OrganizationsService organizationsService;

    @Autowired
    private UserTenantService userTenantService;

    @Autowired
    private StaffService staffService;

    @Override
    public InterfaceResult getCarList(JSONObject params) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<Map<String, Object>> result = new ArrayList<>();
        JSONObject list = new JSONObject();
        List<Car> cars = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        if (null != params && !params.isEmpty()) {
            Integer pageNum = null == params.getInteger("pageNum") ?
                    1 : params.getInteger("pageNum");
            Integer pageSize = null == params.getInteger("pageSize") ?
                    20 : params.getInteger("pageSize");
            int firstPage = (pageNum-1)*pageSize +1;
            int endPage = pageNum*pageSize;
            String marketId = params.getString("marketId");
            if (params.containsKey("tenantName") && StringUtils.isNotBlank(params.getString("tenantName"))) {
                if (!params.containsKey("carType") || StringUtils.isBlank(params.getString("carType")) || params.getInteger("carType") == 3) {
                    //第一种情况,只查商户名,或者查询了商户车类型的车
                    List<UserTenant> userTenants = userTenantService.getAllTenantName(marketId);
                    List<String> tenantIds = new ArrayList<>();
                    List<UserTenant> tenants = new ArrayList<>();
                    userTenants.forEach(tenant -> {
                        if (tenant.getTenantName().contains(params.getString("tenantName"))) {
                            tenantIds.add(tenant.getId());
                            tenants.add(tenant);
                        }
                    });
                    if (!tenantIds.isEmpty()){
                        cars = carMapper.getCarListByTenant(tenantIds);
                        result = groupByTenant(cars, tenants, marketId);
                    }
                    list.put("total",result.size());
                    list.put("pageNum",pageNum);
                    list.put("pageSize",pageSize);
                    if (result.size() < endPage){
                        result = result.subList(firstPage-1,result.size());
                    }else{
                        result = result.subList(firstPage-1,endPage);
                    }
                    list.put("list",result);
                    interfaceResult.InterfaceResult200(list);
                    return interfaceResult;
                } else if (params.getInteger("carType") == 2) {
                    //查询了车类型为市场车 直接返回空数据
                    list.put("pageNum",pageNum);
                    list.put("pageSize",pageSize);
                    list.put("list",result);
                    list.put("total",result.size());
                    interfaceResult.InterfaceResult200(list);
                    return interfaceResult;
                }
            }

            if (params.containsKey("carType") && StringUtils.isNotBlank(params.getString("carType"))) {
                int carType = params.getInteger("carType");
                paramMap.put("carType", carType);
                cars = carMapper.getCarList(paramMap);
                if (carType == 2){
                    //查询当前市场所有的部门
                    List<Organizations> organizations = organizationsService.getOrganizationsByMarket(marketId);
                    result = groupByOrg(cars,organizations,marketId);
                    list.put("total",result.size());
                    list.put("pageNum",pageNum);
                    list.put("pageSize",pageSize);
                    if (result.size() < endPage){
                        result = result.subList(firstPage-1,result.size());
                    }else{
                        result = result.subList(firstPage-1,endPage);
                    }
                    list.put("list",result);
                    interfaceResult.InterfaceResult200(list);
                    return interfaceResult;
                }else if(carType == 3){
                    //查询当前市场的商户
                    List<UserTenant> userTenants = userTenantService.getAllTenantName(marketId);
                    result = groupByTenant(cars, userTenants, marketId);
                    list.put("total",result.size());
                    list.put("pageNum",pageNum);
                    list.put("pageSize",pageSize);
                    if (result.size() < endPage){
                        result = result.subList(firstPage-1,result.size());
                    }else{
                        result = result.subList(firstPage-1,endPage);
                    }
                    list.put("list",result);
                    interfaceResult.InterfaceResult200(list);
                    return interfaceResult;
                }
            }
            try {
                cars = carMapper.getCarList(paramMap);
                result = getGroupList(cars, marketId);
                //list分组算法
               /* List<Map<String, Object>> resultMap = groupByList(cars);
                result = checkCarStaff(params, resultMap);*/
            } catch (Exception e) {
                e.printStackTrace();
                interfaceResult.InterfaceResult500();
                return interfaceResult;
            }
            list.put("total",result.size());
            list.put("pageNum",pageNum);
            list.put("pageSize",pageSize);
            if (result.size() < endPage){
                result = result.subList(firstPage-1,result.size());
            }else{
                result = result.subList(firstPage-1,endPage);
            }
            list.put("list",result);
            interfaceResult.InterfaceResult200(list);
        } else {
            interfaceResult.InterfaceResult406();
        }
        return interfaceResult;
    }


    private List<Map<String, Object>> getGroupList(List<Car> cars, String marketId) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        //查询当前市场下所有的商户
        List<UserTenant> tenantNames = userTenantService.getAllTenantName(marketId);
        result.addAll(groupByTenant(cars, tenantNames, marketId));
        //查询当前市场所有的部门
        List<Organizations> organizations = organizationsService.getOrganizationsByMarket(marketId);
        result.addAll(groupByOrg(cars,organizations,marketId));
        return result;
    }

    private List<Map<String, Object>> groupByOrg(List<Car> cars,List<Organizations> organizations,String marketId){
        List<Map<String, Object>> result = new ArrayList<>();
        for(Organizations org : organizations){
            Map<String, Object> record = new HashMap<>();
            record.put("departmentId", org.getOrgId());
            record.put("departmentName", org.getOrgName());
            record.put("tenantName", BAR);
            //某市场下所有的员工车辆
            List<Staff> staffList = staffService.getMarketStaffByOrgId(1, marketId,org.getOrgId());
            if (staffList.size() > 0){
                for (Staff staff : staffList){
                    if (StringUtils.equals(staff.getGroupId(),org.getOrgId())){
                        if (cars.size() > 0){
                            int usedTenantNum = 0;
                            for (Car car : cars){
                                if (StringUtils.equals(car.getStaffId(),staff.getId())){
                                    usedTenantNum = null == record.get("usedParkingNum")?0: (Integer) record.get("usedParkingNum");
                                    usedTenantNum++;
                                    record.put("usedParkingNum", usedTenantNum);
                                }
                            }
                        }
                    }
                }
                record.put("totalParkingNum", staffList.size() * 2);
                Integer usedTenantNum = null == record.get("usedParkingNum")?0: (Integer) record.get("usedParkingNum");
                record.put("usedParkingNum", usedTenantNum);
                record.put("restParkingNum", staffList.size() * 2 - usedTenantNum);
            }else{
                record.put("totalParkingNum", 0);
                record.put("usedParkingNum", 0);
                record.put("restParkingNum", 0);
            }
            record.put("carType", 2);
            result.add(record);
        };
        return result;
    }

    private List<Map<String, Object>> groupByTenant(List<Car> cars, List<UserTenant> tenants, String marketId) {
        List<Map<String, Object>> result = new ArrayList<>();
        for(UserTenant userTenant : tenants){
            Map<String, Object> record = new HashMap<>();
            record.put("tenantId", userTenant.getId());
            record.put("tenantName", userTenant.getTenantName());
            record.put("departmentName", BAR);
            //某商户下所有的员工车辆
            List<Staff> staffList = staffService.getMarketStaffByOrgId(2, marketId,userTenant.getId());
            if (staffList.size() > 0){
                for (Staff staff : staffList){
                    if (StringUtils.equals(staff.getGroupId(),userTenant.getId())){
                        if (cars.size() > 0){
                            int usedTenantNum = 0;
                            for (Car car : cars){
                                if (StringUtils.equals(car.getStaffId(),staff.getId())){
                                    usedTenantNum = null == record.get("usedParkingNum")?0: (Integer) record.get("usedParkingNum");
                                    usedTenantNum++;
                                    record.put("usedParkingNum", usedTenantNum);
                                }
                            }
                        }
                    }
                }
                record.put("totalParkingNum", staffList.size() * 2);
                Integer usedTenantNum = null == record.get("usedParkingNum")?0: (Integer) record.get("usedParkingNum");
                record.put("usedParkingNum", usedTenantNum);
                record.put("restParkingNum", staffList.size() * 2 - usedTenantNum);
            }else{
                record.put("totalParkingNum", 0);
                record.put("usedParkingNum", 0);
                record.put("restParkingNum", 0);
            }
            record.put("carType", 3);
            result.add(record);
        };
        return result;
    }

    @Override
    public InterfaceResult getDetailInfo(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        Map<String, Object> paramMap = new HashMap<>();
        List list = new ArrayList();
        if (null != params && !params.isEmpty()) {
            Integer pageNum = null == params.getInteger("pageNum") ?
                    1 : params.getInteger("pageNum");
            Integer pageSize = null == params.getInteger("pageSize") ?
                    20 : params.getInteger("pageSize");
            int firstPage = (pageNum-1)*pageSize +1;
            int endPage = pageNum*pageSize;
            if (params.containsKey("groupId")) {
                paramMap.put("tenant", params.getString("groupId"));
            } else {
                result.InterfaceResult600("部门或商户id不能为空!");
                return result;
            }
            if(params.containsKey("carType")){
                paramMap.put("carType", params.getString("carType"));
            }
            //2市场3商户
            List<Car> cars = new ArrayList<>();
            if (params.getIntValue("carType") == 3){
                List<UserTenant> tenantNames = userTenantService.getAllTenantName(params.getString("marketId"));
                String tenantId = params.getString("groupId");
                List<String> tenantIds = new ArrayList<>();
                List<Staff> staffList = staffService.getMarketStaff(2, params.getString("marketId"));
                tenantIds.add(tenantId);
                cars = carMapper.getCarListByTenant(tenantIds);
                for (Car car : cars) {
                    String insertTime = DateUtils.SHORT_DATE_FORMAT.format(car.getInsertTime());
                    car.setInsertTimeStr(insertTime);
                    JSONObject json = (JSONObject) JSONObject.toJSON(car);
                    for (Staff staff : staffList) {
                        if (StringUtils.equals(car.getStaffId(), staff.getId())) {
                            json.put("staffName", staff.getStaffName());
                            break;
                        }
                    }
                    for (UserTenant ut : tenantNames) {
                        if (StringUtils.equals(ut.getId(), tenantId)) {
                            json.put(DEPARTMENT_NAME, BAR);
                            json.put(TENANT_NAME, ut.getTenantName());
                            break;
                        }
                    }
                    list.add(json);
                }
            }else{
                //市场车
                String tenantId = params.getString("groupId");
                //1市场员工;2车商员工
                List<String> staffIds = new ArrayList<>();
                List<Organizations> organizations = organizationsService.getOrganizationsByMarket(params.getString("marketId"));
                List<Staff> staffList = staffService.getMarketStaff(1, params.getString("marketId"));
                for (Staff staff : staffList){
                    if (StringUtils.equals(staff.getGroupId(),tenantId)){
                        staffIds.add(staff.getId());
                    }
                }
                if (!staffIds.isEmpty()){
                    cars = carMapper.getCarListByStaffId(staffIds);
                }
                if (!cars.isEmpty()){
                    for (Car car : cars) {
                        String insertTime = DateUtils.SHORT_DATE_FORMAT.format(car.getInsertTime());
                        car.setInsertTimeStr(insertTime);
                        JSONObject json = (JSONObject) JSONObject.toJSON(car);
                        for (Staff staff : staffList) {
                            if (StringUtils.equals(car.getStaffId(), staff.getId())) {
                                json.put("staffName", staff.getStaffName());
                                break;
                            }
                        }
                        for (Organizations org : organizations) {
                            if (StringUtils.equals(org.getOrgId(), tenantId)) {
                                json.put(DEPARTMENT_NAME, org.getOrgName());
                                json.put(TENANT_NAME, BAR);
                                break;
                            }
                        }
                        list.add(json);
                    }
                }
            }
            JSONObject res = new JSONObject();
            res.put("total",list.size());
            res.put("pageNum",pageNum);
            res.put("pageSize",pageSize);
            if (list.size() < endPage){
                list = list.subList(firstPage-1,list.size());
            }else{
                list = list.subList(firstPage-1,endPage);
            }
            res.put("list",list);
            result.InterfaceResult200(res);
        } else {
            result.InterfaceResult406();
        }
        return result;
    }

    private static List<Map<String, Object>> groupByList(List<Car> cars) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> tenantIds = new HashSet<>();
        //车辆类型 1：商品车 2 商户车 3市场车
        //第一步：set的元素不会重复，取出所有商户id或者是部门id
        cars.forEach(car -> {
            tenantIds.add(car.getTenant());
        });
        //第二步，外层遍历所有商户或部门，内层遍历所有数据
        for (String tenantId : tenantIds) {
            int usedTenantNum = 0;
            int usedMarketNum = 0;
            for (Car car : cars) {
                //第三步，控制开关，如果tenant匹配则进入后续增加逻辑
                boolean flag = false;
                Map<String, Object> mapObj = new HashMap<>();
                if (null != car.getTenant() && StringUtils.equals(car.getTenant(), tenantId)) {
                    //商户车累加计算
                    if (car.getCarType() == 2) {
                        usedTenantNum++;
                        mapObj.put("usedTenantNum", usedTenantNum);
                    }
                    //市场车累加计算
                    if (car.getCarType() == 3) {
                        usedMarketNum++;
                        mapObj.put("usedMarketNum", usedMarketNum);
                    }
                    mapObj.put("carType", car.getCarType());
                    flag = true;
                }
                if (flag) {
                    mapObj.put("tenantId", tenantId);
                    //第四步，一层开关控制会出现数据重复,再加一层控制开关
                    // 在原有商户id基础上修改数量
                    boolean status = false;
                    if (!result.isEmpty()) {
                        for (Map<String, Object> mmp : result) {
                            //如果tenant匹配，覆盖原有的数量值
                            if (StringUtils.equals(String.valueOf(mmp.get("tenantId")), tenantId)) {
                                mmp.put("usedMarketNum", usedMarketNum);
                                mmp.put("usedTenantNum", usedTenantNum);
                                status = true;
                            }
                        }
                    }
                    //如果没有匹配的则新增一条
                    if (!status) {
                        result.add(mapObj);
                    }
                }
            }
        }
        return result;
    }


    private List<Map<String, Object>> checkCarStaff(JSONObject params,
                                                    List<Map<String, Object>> result) throws Exception {
        //查询所有部门和商户
        List<Organizations> organizations = organizationsService.searchOrganizations(new Organizations());
        List<UserTenant> tenantNames = userTenantService.getAllTenantName(params.getString("marketId"));
        //是否有商户名称查询控制开关
        boolean flag = false;
        if (params.containsKey("tenantName")) {
            flag = true;
            String tenantName = params.getString("tenantName");
            for (int i = 0; i < tenantNames.size(); i++) {
                if (!tenantNames.get(i).getTenantName().contains(tenantName)) {
                    tenantNames.remove(i);
                    i--;
                }
            }
        }
        //遍历集合对象
        for (Map<String, Object> map : result) {
            Integer carType = (Integer) map.get("carType");
            String groupId = String.valueOf(map.get("tenantId"));
            //如果是商户车,匹配商户名称
            if (carType == 2) {
                Integer usedTenantNum = (Integer) map.get("usedTenantNum");
                for (UserTenant userTenant : tenantNames) {
                    if (StringUtils.equals(groupId, userTenant.getId())) {
                        map.put(TENANT_NAME, userTenant.getTenantName());
                        map.put(DEPARTMENT_NAME, BAR);
                        map.put(TOTAL_PARKING_NUM, 10);
                        map.put(REST_PARKING_NUM, 10 - usedTenantNum);
                    }
                }
            }
            //有商户查询，规避部门数据
            if (!flag) {
                //如果是市场车,匹配部门名称
                if (carType == 3) {
                    for (Organizations org : organizations) {
                        if (StringUtils.equals(groupId, org.getOrgId())) {
                            map.put(TENANT_NAME, BAR);
                            map.put(DEPARTMENT_NAME, org.getOrgName());
                            map.put(TOTAL_PARKING_NUM, BAR);
                            map.put(REST_PARKING_NUM, BAR);
                        }
                    }
                }
            }
        }
        //没有商户名称的数据，就移除
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).containsKey("tenantName")) {
                result.remove(i);
                i--;
            }
        }
        return result;
    }
}
