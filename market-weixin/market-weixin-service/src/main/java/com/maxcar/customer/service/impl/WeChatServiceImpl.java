package com.maxcar.customer.service.impl;

import com.alibaba.fastjson.JSON;
import com.maxcar.base.service.IdDictionaryService;
import com.maxcar.base.util.MD5Util;
import com.maxcar.base.util.RedisUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.wechat.entity.ExtAccessToken;
import com.maxcar.base.util.wechat.entity.WeixinUtil;
import com.maxcar.customer.service.WeChatService;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Customer;
import com.maxcar.user.entity.Market;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.service.CustomerService;
import com.maxcar.user.service.MarketService;
import com.maxcar.user.service.StaffService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {
    @Autowired
    private MarketService marketService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private UserTenantService userTenantService;
    @Autowired
    private IdDictionaryService idDictionaryService;

    @Override
    public JSONObject getOpenId(JSONObject request) {
        String tenantUserInfo;
        String customerInfo;
        ExtAccessToken ext;
        JSONObject result = new JSONObject();

        Logger.getRootLogger().info("开始打印接收参数");
        String marketId = request.getString("market");
        if (StringUtils.isBlank(marketId)) {
            result.put("param_Error", "缺少marketId");
            return result;
        }
        Logger.getRootLogger().info("marketId = " + marketId);
        Market market = marketService.getMarketById(marketId);
        if (market == null) {
            result.put("data_Error", "该市场不存在");
            return result;
        }
        Logger.getRootLogger().info("getMarket->" + market.getId());

        String customer = request.getString("customer");
        if (StringUtils.isBlank(customer)) {
            result.put("param_Error", "缺少customer");
            return result;
        }
        Logger.getRootLogger().info("customer = " + customer);

        if (!(customer.equals("0")) && !(customer.equals("1"))) {
            result.put("param_Error", "无效的customer");
            return result;
        }

        String code = request.getString("code");
        if (StringUtils.isBlank(code)) {
            result.put("param_Error", "缺少code");
            return result;
        }
        Logger.getRootLogger().info("code = " + code);

        ext = new ExtAccessToken();

        //商管家授权
        if (customer.equals("0")) {
            Logger.getRootLogger().info("进入商管家授权====");
            if (StringUtils.isBlank(market.getAppid())) {
                result.put("data_Error", "当前市场未配置appId");
                return result;
            }

            ext.setAppid(market.getAppid());

            if (StringUtils.isBlank(market.getAppsecret())) {
                result.put("data_Error", "当前市场未配置appsecret");
                return result;
            }

            ext.setApp_secret(market.getAppsecret());

            // 请求微信
            tenantUserInfo = WeixinUtil.getBaseInfoTenant(market.getId(), code, ext);
            if (StringUtils.isNotBlank(tenantUserInfo)) {
                Logger.getRootLogger().info(">>>>>>>微信用户信息请求成功!>>>>"+tenantUserInfo);
                try {
                    // 处理返回来的信息
                    JSONObject baseObj = JSONObject.fromObject(tenantUserInfo);
                    Staff queryByOpenId = new Staff();
                    queryByOpenId.setOpenId(baseObj.getString("openid"));
                    Logger.getRootLogger().info("openid====1==========>"+baseObj.getString("openid"));
                    List<Staff> staffList = staffService.selectByExample(queryByOpenId);
                    if (staffList != null && staffList.size() > 0 && staffList.get(0).getGroupId() != null) {
                        Logger.getRootLogger().info(">>>>>>>员工信息请求成功===>"+staffList.get(0).getStaffName());
                        String tenantName = userTenantService.selectByTenanId(staffList.get(0).getGroupId());
                        if (tenantName != null) {
                            result.put("tenantName", tenantName);
                        }
                        result.put("tenant", staffList.get(0).getGroupId());
                        result.put("isadmin", staffList.get(0).getIsAdmin() + "");
                        result.put("tenantUser", staffList.get(0).getId());
                    }
                    result.put("userinfo", tenantUserInfo);
                    Logger.getRootLogger().info("openid===2===========>"+baseObj.getString("openid"));
                    result.put("openid", baseObj.getString("openid"));
                    return result;
                } catch (Exception e) {
                    Logger.getRootLogger().error(e, e);
                }

            }
        }

        //消费者授权
        if (customer.equals("1")) {
            if (StringUtils.isNotBlank(market.getAppid2())) {
                ext.setAppid2(market.getAppid2());
            } else {
                result.put("data_Error", "当前市场未配置appid2");
                return result;
            }
            if (StringUtils.isNotBlank(market.getAppsecret2())) {
                ext.setApp_secret2(market.getAppsecret2());
            } else {
                result.put("data_Error", "当前市场未配置appsecret2");
                return result;
            }
            if (StringUtils.isNotBlank(ext.getAppid2()) && StringUtils.isNotBlank(ext.getApp_secret2())) {
                Logger.getRootLogger().info("code--->" + code);
                customerInfo = WeixinUtil.getBaseInfoIcar(market.getId(), code, ext);
                if (StringUtils.isNotBlank(customerInfo)) {
                    try {
                        // 处理返回来的信息
                        JSONObject baseObj = JSONObject.fromObject(customerInfo);
                        if (baseObj.getString("subscribe").equals("0")) {
                            result.put("subscribe", "0");
                            return result;
                        }
                        Customer entity = new Customer();
                        entity.setOpenId(baseObj.getString("openid"));
                        Customer info = customerService.getCustomer(entity);
                        if (null == info) {
                            String id = idDictionaryService.getNewId("customer", marketId);
                            Logger.getRootLogger().info("id: " + id);
                            info = new Customer();
                            info.setOpenId(baseObj.getString("openid"));
                            info.setNickname(baseObj.getString("nickname"));
                            info.setAvatar(baseObj.getString("headimgurl"));
                            info.setSex(baseObj.getString("sex"));
                            info.setId(id);
                            customerService.saveCustomer(info);
                        }
                        result.put("openid", baseObj.getString("openid"));
                        result.put("userinfo", customerInfo);
                        Logger.getRootLogger().info("customer: " + info.getId());
                        result.put("Customer", info.getId());
                        return result;
                    } catch (Exception e) {
                        Logger.getRootLogger().error(e, e);
                    }
                }
            }

        }
        return null;
    }

    @Override
    public String getToken(String code, String openId) {
        try {
            String token = MD5Util.MD5(code + openId);
            //保存到redis缓存，定时2小时
            Map<String, String> map = new HashMap<String, String>();
            map.put("openId", openId);
            RedisUtil.getInstance().strings().set(token, JSON.toJSONString(map));
            RedisUtil.getInstance().keys().expire(token, 60 * 120);
            return token;
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
            return "";
        }
    }

    public static void main(String[] args) {
        String token = MD5Util.MD5("123" + "oQdokxGFIYBuh6BQjdI91nm_8rpw");
        Map<String, String> map = new HashMap<String, String>();
        map.put("openId", "oQdokxGFIYBuh6BQjdI91nm_8rpw");
        RedisUtil.getInstance().strings().set(token, JSON.toJSONString(map));
//		RedisUtil.getInstance().keys().expire(token, 60 * 120); 
    }

}
