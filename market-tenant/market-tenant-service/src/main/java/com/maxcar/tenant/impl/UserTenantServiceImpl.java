package com.maxcar.tenant.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.StringUtil;
import com.maxcar.tenant.dao.UserTenantMapper;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.pojo.UserTenantExample;
import com.maxcar.tenant.service.UserTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */
@Service("userTenantService")
public class UserTenantServiceImpl extends BaseServiceImpl<UserTenant, String> implements UserTenantService {

    @Autowired
    private UserTenantMapper userTenantMapper;

    @Override
    public InterfaceResult addUserTenant(UserTenant userTenant) {
        InterfaceResult interfaceResult = new InterfaceResult();
        if (null != userTenant.getMarketId() && null != userTenant.getId()) {
            userTenant.setTenantNo(userTenant.getMarketId() + userTenant.getId());
        }
        int count = 0;
        count = userTenantMapper.insertSelective(userTenant);
        if (count > 0) {
            interfaceResult.InterfaceResult200("新增成功");
        } else {
            interfaceResult.InterfaceResult600("新增失败");
        }
        return interfaceResult;
    }

    @Override
    public PageInfo selectUserTenantList(UserTenant userTenant) {

        if (0 == userTenant.getCurrentPage() && 0 == userTenant.getPageSize()) {
            PageInfo pageInfo = new PageInfo(userTenantMapper.selectUserTenantList(userTenant));
            return pageInfo;
        }

        PageHelper.startPage(userTenant.getCurrentPage(), userTenant.getPageSize());
        List<UserTenant> lists = userTenantMapper.selectUserTenantList(userTenant);
        //调用合同查询区域的编号信息
        PageInfo pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    @Override
    public List<UserTenant> list(UserTenant userTenant) {
        UserTenantExample userTenantExample = new UserTenantExample();
        UserTenantExample.Criteria userTenantExample1 = userTenantExample.createCriteria();
        userTenantExample1.andIsvalidEqualTo(1);
        if (null != userTenant.getTenantName() && userTenant.getTenantName() != "") {
            userTenantExample1.andTenantNameLike(userTenant.getTenantName());
        }
        return userTenantMapper.selectByExample(userTenantExample);
    }

    @Override
    public UserTenant selectByPrimaryKey(String id) {
        return userTenantMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserTenant selectUserTenant(UserTenant userTenant) {
        return userTenantMapper.selectUserTenant(userTenant);
    }

    @Override
    public List<UserTenant> getAllTenantName(String marketId) {
        return userTenantMapper.getAllTenantName(marketId);
    }

    @Override
    public InterfaceResult updateUserTenantById(UserTenant userTenant) {
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        count = userTenantMapper.updateByPrimaryKeySelective(userTenant);
        if (count > 0) {
            interfaceResult.InterfaceResult200("更新成功");
        } else {
            interfaceResult.InterfaceResult600("无更新记录");
        }
        return interfaceResult;
    }

    @Override
    public List<UserTenant> getTenantNameLike(String tenantName) {
        return userTenantMapper.getTenantNameLike(tenantName);
    }

    /**
     * 获取市场商户总数
     * 沈中宗
     *
     * @param marketId 市场ID  areaName  区域id
     * @return 商户总个数
     */
    @Override
    public int countTenant(String marketId, String tenantId, String areaName) {
        UserTenantExample example = new UserTenantExample();
        UserTenantExample.Criteria criteria = example.createCriteria();
        criteria.andIsvalidEqualTo(1).andStatusNotEqualTo("2").andMarketIdEqualTo(marketId);
        if (StringUtil.isNotEmpty(tenantId)) {
            criteria.andIdEqualTo(tenantId);
        }
        if (StringUtil.isNotEmpty(areaName)) {
            criteria.andTenantAreaEqualTo(areaName);
        }
        return userTenantMapper.countByExample(example);
//        int count = userTenantMapper.countTenant(marketId);
//        return count;
    }

    /**
     * 根据区域名获取商户
     * Created by 沈中宗
     *
     * @param tenantArea
     * @return
     */
    @Override
    public List<UserTenant> selectAreaByName(String marketId, String tenantArea) {
        return userTenantMapper.selectAreaByName(marketId, tenantArea);
    }

    /**
     * 根据商户id获取商户名
     * Created by 沈中宗
     *
     * @param
     * @return 商户名
     */
    @Override
    public String selectByTenanId(String tenanId) {
        return userTenantMapper.selectByTenanId(tenanId);
    }

    /**
     * 查询出所有商户
     * 沈中宗
     *
     * @param
     * @return
     */
    @Override
    public List<UserTenant> selectAll(Integer pageNum, Integer pageSize, String tenant, String marketId) {
        return userTenantMapper.selectAll(pageNum, pageSize, marketId, tenant);
    }

    @Override
    public InterfaceResult getUserTenantCount(UserTenant userTenant) {
        InterfaceResult interfaceResult = new InterfaceResult();

        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        List<LinkedHashMap> aList = new ArrayList<>();
        List<LinkedHashMap> list = userTenantMapper.selectUserTenantCount(userTenant);
        for (LinkedHashMap map : list) {
            if ("1".equals(map.get("tenant_type").toString())) {
                i1 = Integer.valueOf(map.get("count").toString());
            }
            if ("2".equals(map.get("tenant_type").toString())) {
                i2 = Integer.valueOf(map.get("count").toString());
            }
            if ("3".equals(map.get("tenant_type").toString())) {
                i3 = Integer.valueOf(map.get("count").toString());
            }
            if ("4".equals(map.get("tenant_type").toString())) {
                i4 = Integer.valueOf(map.get("count").toString());
            }
        }

        LinkedHashMap<String, Object> aMap = new LinkedHashMap<>();
        aMap.put("count", i1);
        aMap.put("tenant_type", 1);
        aList.add(aMap);
        LinkedHashMap<String, Object> bMap = new LinkedHashMap<>();
        bMap.put("count", i2);
        bMap.put("tenant_type", 2);
        aList.add(bMap);
        LinkedHashMap<String, Object> cMap = new LinkedHashMap<>();
        cMap.put("count", i3);
        cMap.put("tenant_type", 3);
        aList.add(cMap);
        LinkedHashMap<String, Object> dMap = new LinkedHashMap<>();
        dMap.put("count", i4);
        dMap.put("tenant_type", 4);
        aList.add(dMap);

        int count = 0;
        for (LinkedHashMap m : list) {
            String o = m.get("count").toString();
            count += Integer.parseInt(o);
        }
        LinkedHashMap map = new LinkedHashMap();
        map.put("count", count);
        map.put("tenant_type", "tenantCounts");
        aList.add(map);
        interfaceResult.InterfaceResult200(aList);
        return interfaceResult;
    }

    /**
     * 查询出所有商户
     * 朱峰波
     *
     * @param
     * @return
     */
   /* @Override
    public List<UserTenant> selectAllList(String tenant, String marketId) {
		return userTenantMapper.selectAllList(marketId, tenant);
	}*/
    @Override
    public BaseDao<UserTenant, String> getBaseMapper() {
        return userTenantMapper;
    }

    /**
     * 根据区域Id查询列表
     * 朱峰波
     *
     * @param
     * @return
     */
    @Override
    public List<UserTenant> selectByTenantArea(String marketId, String tenantArea) {
        return userTenantMapper.selectByTenantArea(marketId, tenantArea);
    }

    @Override
    public int countTenantArea(String marketId, String areaName) {
        return userTenantMapper.countTenantArea(marketId, areaName);
    }


}
