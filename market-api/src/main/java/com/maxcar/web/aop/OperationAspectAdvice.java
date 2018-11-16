package com.maxcar.web.aop;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.redis.service.SsoService;
import com.maxcar.redis.util.ProResult;
import com.maxcar.user.entity.OperationRecord;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OperationRecordService;
import com.maxcar.user.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @Description: 统一拦截系统操作日志
* @Param: 
* @return: 
* @Author: 罗顺锋
* @Date: 2018/5/17
*/
@Component
@Aspect
@Order(1)
public class OperationAspectAdvice {
    Logger logger = LoggerFactory.getLogger(OperationAspectAdvice.class);
    @Autowired
    private OperationRecordService operationRecordService;
    @Autowired
    private SsoService ssoService;
    @Autowired
    private UserService userService;

    /**
    * @Description: 前置通知，用来获取用户数据权限
    * @Param: [point]
    * @return: void
    * @Author: 罗顺锋
    * @Date: 2018/5/21
    */
    @Before("@annotation(OperationAnnotation)") 
    public void operationBefore(JoinPoint point)throws Exception{
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
//        OperationAnnotation operationAnnotation = className.getAnnotation(OperationAnnotation.class);
//        if (operationAnnotation != null ) {//方法级别不用此判断
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        InterfaceResult result = ssoService.getUserByToken(request.getHeader("userToken"));
        if (result != null && result.getCode().equals("200")) {
            String userId = result.getData().toString();
            User user = userService.selectByPrimaryKey(userId);
            //获得访问的方法名
            String methodName = point.getSignature().getName();
            //得到方法的参数的类型
            Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
            //这里处理数据权限，反向往入参加入数据范围，即市场id
//            Class parentClass = argClass[0].getSuperclass();
//            if(parentClass!=null) {
//                Field[] fields = parentClass.getDeclaredFields(); // 获取实体类的所有属性
//                List<String> marketIds = null;
//                Method m = parentClass.getDeclaredMethod("getMarketId" );
////                Field[] fields = parentClass.getDeclaredFields();
//                // 调用getter方法获取属性值
//                String marketId = (String)m.invoke(parentClass.newInstance());
//                if(StringUtils.isNotBlank(marketId)){
//                    marketIds = new ArrayList<>();
//                    marketIds.add(marketId);
//                }else{
//                    marketIds = dataFunctionService.selectFunctionMarketByUserId(user.getUserId());
//                }
//                Method sm = parentClass.getDeclaredMethod("setMarketIds" ,List.class);
//                sm.invoke(parentClass,marketIds);
//                Method mm = parentClass.getDeclaredMethod("getMarketIds" );
//                List marketIdss = (List) mm.invoke(parentClass);
//                System.out.println(marketIdss);
//            }
        }
    }

    @After("@annotation(OperationAnnotation)")   //后置通知，执行方法后执行操作记录
    public void operationAfter(JoinPoint point)throws Exception{
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
//        OperationAnnotation operationAnnotation = className.getAnnotation(OperationAnnotation.class);
//        if (operationAnnotation != null ) {//方法级别不用此判断
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        InterfaceResult result = ssoService.getUserByToken(request.getHeader("userToken"));
        if (result != null && result.getCode().equals("200")) {
            String res = result.getData().toString();
            User user = null;
            if (res.indexOf("userId")==-1) {
                String userId = res;
                user = userService.selectByPrimaryKey(userId);
            }else{
                user = JsonTools.toObj(res, User.class);
            }
            //获得访问的方法名
            String methodName = point.getSignature().getName();
            //得到方法的参数的类型
            Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
            Method method = className.getMethod(methodName, argClass);
            if (method.isAnnotationPresent(OperationAnnotation.class)) {
                OperationAnnotation annotation = method.getAnnotation(OperationAnnotation.class);
                String title = annotation.title();
                String info = annotation.info();
                logger.info("拦截用户操作行为，"+className.getName()+"标题："+title+",内容："+info);
                OperationRecord record = new OperationRecord();
                record.setOperationId(UuidUtils.generateIdentifier());
                record.setOperationType(annotation.type());
                if(user!=null) {
                    record.setMarketId(user.getMarketId());
                    record.setUserName(user.getUserName());
                    record.setUserId(user.getUserId());
                    record.setTrueName(user.getTrueName());
                }
                record.setOperationInfo(info.equals("")?title:info);
                record.setOperationTitle(title);
                record.setOperationUrl(request.getServletPath());
                operationRecordService.insertSelective(record);
            }
        }
    }
}
