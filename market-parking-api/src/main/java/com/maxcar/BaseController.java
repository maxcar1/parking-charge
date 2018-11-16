package com.maxcar;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.redis.service.SsoService;
import com.maxcar.user.entity.Staff;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.StaffService;
import com.maxcar.user.service.UserRoleService;
import com.maxcar.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 通用控制器，获取用户等
 * @Param:
 * @return:
 * @Author: 罗顺锋
 * @Date: 2018/4/26
 */
@RequestMapping("/api-p")
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SsoService ssoService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private StaffService staffService;

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

    public String getRemoteHost(HttpServletRequest request){
        //得到来访者的IP地址
        String remoteAddr = request.getRemoteAddr();
        logger.info("remoteAddr:{}",remoteAddr);
        return remoteAddr;
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


}
