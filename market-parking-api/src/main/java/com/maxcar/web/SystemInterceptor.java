package com.maxcar.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.JsonTools;
import com.maxcar.redis.service.SsoService;
import com.maxcar.redis.util.ProResult;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ResourceService;
import com.maxcar.user.service.RoleResourceService;
import com.maxcar.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * 罗顺锋
 * 拦截器实例,方法调用前中后
 */
@Component
public class SystemInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(SystemInterceptor.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private SsoService ssoService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        /*logger.info("用户认证方法调用之前");
        InterfaceResult interfaceResult = new InterfaceResult();
        String resource = request.getServletPath();
        logger.info("拦截的path为{}",resource);
        //设置返回中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //第一步，验证资源是否存在
//        String resourceId = validResource(resource);
//        if (StringUtils.isEmpty(resourceId)) {
//            interfaceResult.InterfaceResult404();
//        } else {
            //第二步从头部获取token信息，验证用户
            String userToken = request.getHeader("userToken");
            if (StringUtils.isNotEmpty(userToken)) {
                InterfaceResult result = ssoService.getUserByToken(userToken);
                if (result != null && result.getCode().equals("200")) {
                    ssoService.setKeyTime(userToken, -1);//时间周期往后设置
                    String res = result.getData().toString();
                    String userId = null;
                    User user = null;
                    if (res.indexOf("userId") == -1) {
                        userId = res;
                        user = userService.selectByPrimaryKey(userId);
                        if (user == null) {//用户不存在
                            interfaceResult.InterfaceResult403();
                        } else {
                            //第三步，这里验证角色权限
//                        if (validRoleResource(resourceId, resource)) {//有角色
                            return true;
//                        } else {
//                            interfaceResult.InterfaceResult401();
//                        }
                        }
                    }
                    return true;//外部请求直接放行
                } else {
                    interfaceResult.InterfaceResult403();
                }
            } else {
                interfaceResult.InterfaceResult403();
            }
//        }
        response.getWriter().write(JsonTools.toJson(interfaceResult));*/
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("用户认证方法调用之后");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        logger.info("在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
        logger.info("用户认证方法执行视图渲染完成以后");
    }
    /**
     * @Description: 验证资源是否存在
     * @Param: [resource]
     * @return: java.lang.String
     * @Author: 罗顺锋
     * @Date: 2018/4/27
     */
    public String validResource(String resource){
        return resourceService.getResourceIdByUrl(resource);
    }

    /**
     * 验证用户是否有该请求资源
     * @param roleId
     * @param resource
     * @return
     */
    public boolean validRoleResource(String roleId,String resource){
        int count = roleResourceService.ValidRoleResource(roleId,resource);
        return count==1;
    }
}