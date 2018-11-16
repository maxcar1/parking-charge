package com.maxcar;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.service.TopicService;
import com.maxcar.market.model.request.AddContractRequest;
import com.maxcar.redis.service.SsoService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserRoleService;
import com.maxcar.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * @Description: 通用控制器，获取用户等
 * @Param:
 * @return:
 * @Author: 罗顺锋
 * @Date: 2018/4/26
 */
@RequestMapping("/api")
public class BaseController {
    protected final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private SsoService ssoService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private StaffService staffService;
    @Autowired
    protected TopicService topicService;

    /**
     * @Description：获取cookie中的token
     * @Author:罗顺锋
     * @Create:2017/3/29 11:17
     */
    public User getCurrentUser(HttpServletRequest request) throws Exception {
        User user = null;
        //第二步从头部获取token信息，验证用户
        String userToken = request.getHeader("userToken");
        if (StringUtils.isNotEmpty(userToken)) {
            InterfaceResult result = ssoService.getUserByToken(userToken);
            if (result != null && result.getCode().equals("200")) {
                String res = result.getData().toString();
                String userId = null;
                userId = res;
                if (null == userId || userId.isEmpty()) {
                    logger.error("userToken===" + userToken);
                    logger.error("result===" + result.toString());
                }
                user = userService.selectByPrimaryKey(userId);
                if (null == user) {
                    logger.error("user:null");
                }
                //根据登录的员工id查询员工姓名

                List<Staff> staffList = staffService.selectByStaffId(user.getStaffId());
//                            trueUser.setStaffName(staffList.get(0).getStaffName());
                if (null != staffList && !staffList.isEmpty()) {
                    user.setStaffName(staffList.get(0).getStaffName());
                }
                user.setRoles(userRoleService.selectRoleByUserId(userId));

            }
        }
        return user;
    }

    /**
     * @Description: 获取当前网络ip
     * @Param: [request]
     * @return: java.lang.String
     * @Author: 罗顺锋
     * @Date: 2018/4/28
     */
    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * param:
     * describe: 封装返回参数
     * create_date:  lxy   2018/8/16  12:06
     **/
    public InterfaceResult getInterfaceResult(String code, Object data) {
        InterfaceResult interfaceResult = new InterfaceResult();
        if ("200".equals(code)) {
            interfaceResult.InterfaceResult200(data);
        } else if ("406".equals(code)) {
            // 参数格式错误
            interfaceResult.InterfaceResult406();
        } else if ("600".equals(code)) {
            //自定义异常
            interfaceResult.InterfaceResult600(data.toString());
        } else {
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }


    /**
     * param:
     * describe: 数据不为null
     * create_date:  lxy   2018/8/17  9:39
     **/
    public static final <T> boolean tIsNotEmpty(T t) {
        return !tIsEmpty(t);
    }


    /**
     * param:
     * describe: 数据为null
     * create_date:  lxy   2018/8/17  9:39
     **/
    public static final <T> boolean tIsEmpty(T t) {
        if (null == t) {
            return true;
        }

        if (t instanceof Number) {
            return false;
        }

        if (t instanceof String) {
            return ((String) t).trim().isEmpty();
        }

        if (t instanceof Collection) {
            return ((Collection) t).isEmpty();
        }

        if (t.getClass().isArray()) {
            return Arrays.stream((Object[]) t).filter(x -> null != x).count() == 0L;
        }

        if (t instanceof Map) {
            return ((Map) t).isEmpty();
        }

        return true;
    }


    /**
     * param:
     * describe: 新增合同入参判断 是否不合法
     * create_date:  lxy   2018/10/18  11:27
     **/
    public InterfaceResult isAddContractRequest(AddContractRequest request) {
        // 商户数据不存在 参数不合法
        if (null == request.getUserTenantPack() || null == request.getPropertyContract()) {
            return getInterfaceResult("600", "请填写合同信息和商户信息");
        }
        // 商户名称和商户类型必传
        if (tIsEmpty(request.getUserTenantPack().getTenantName()) || tIsEmpty(request.getUserTenantPack().getTenantType())) {
            return getInterfaceResult("600", "请填写商户名称和商户类型");
        }

        // 根据类型判断参数 1  经纪人2 经纪公司  3 经销公司4 新车经销商  5 代理人 6 餐饮店  7 金融公司 8 其他
        // 1 2 3 4 6 需要物业  需要缴费(强制)  // 查看是否提交了物业信息
        if (Magic.TENANT_TYPE_1.equals(request.getUserTenantPack().getTenantType()) || Magic.TENANT_TYPE_2.equals(request.getUserTenantPack().getTenantType()) ||
                Magic.TENANT_TYPE_3.equals(request.getUserTenantPack().getTenantType()) || Magic.TENANT_TYPE_4.equals(request.getUserTenantPack().getTenantType()) ||
                Magic.TENANT_TYPE_6.equals(request.getUserTenantPack().getTenantType())) {

            if (tIsEmpty(request.getPropertyContractDetailList())) {
                return getInterfaceResult("600", "请填写物业类型");
            }
        }

        return getInterfaceResult("200", "请填写物业类型");
    }

    /**
     * param:
     * describe: 计算间隔天数
     * create_date:  lxy   2018/10/25  14:51
     **/
    public static int getIntervalDays(LocalDate beforeTime, LocalDate afterTime) {
        Period p = Period.between(beforeTime, afterTime);
        //  System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        return p.getDays();
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate beforeTime = LocalDate.now();

        Calendar cal = Calendar.getInstance();

        cal.setTime(  sdf.parse("2018-10-26"));
        LocalDate afterTime = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DATE));

        System.out.println(getIntervalDays(beforeTime, afterTime));

    }

}
