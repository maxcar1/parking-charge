package com.maxcar.web.exception;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.JsonTools;
import com.minlia.cloud.body.StatefulBody;
import com.minlia.cloud.body.impl.FailureResponseBody;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: maxcar-util
 * @description: 系统 捕获全局异常处理
 * @author: 罗顺锋
 * @create: 2018-04-27 10:10
 **/
@RestController
public class GlobalErrorController implements ErrorController{
    Logger logger = LoggerFactory.getLogger(GlobalErrorController.class);
    private static final String ERROR_PATH = "/error";
    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value=ERROR_PATH)
    public String handleError(HttpServletRequest request,WebRequest webRequest, HttpServletResponse response){
        InterfaceResult interfaceResult = new InterfaceResult();
        Map<String,Object> errorAttributes = getErrorAttributes(request, webRequest,true);
        String status = errorAttributes.get("status")+"";
        String path = (String)errorAttributes.get("path");
        String message = (String)errorAttributes.get("message");
        String trace = (String) errorAttributes.get("trace");//详细错误栈信息
        interfaceResult.setCode(status);
        interfaceResult.setMsg(message);
        interfaceResult.setPath(path);
        logger.error(JsonTools.toJson(interfaceResult));
        return JsonTools.toJson(interfaceResult);
//        return "errorpage/error";
    }

//    @RequestMapping(value = ERROR_PATH,  produces = {MediaType.APPLICATION_JSON_VALUE})
//    StatefulBody handleError(HttpServletRequest request, WebRequest webRequest,HttpServletResponse response) {
////        if(!EnvironmentUtils.isProduction()) {
//            return buildBody(request,webRequest,true);
////        }else{
////            return buildBody(request,false);
////        }
//    }
//    //组装错误类型
//    private StatefulBody buildBody(HttpServletRequest request,WebRequest webRequest,Boolean includeStackTrace){
//        Map<String,Object> errorAttributes = getErrorAttributes(request, webRequest,includeStackTrace);
//        Integer status=(Integer)errorAttributes.get("status");
//        String path=(String)errorAttributes.get("path");
//        String messageFound=(String)errorAttributes.get("message");
//        String message="";
//        String trace ="";
//        if(!StringUtils.isEmpty(path)){
//            message=String.format("Requested path %s with result %s",path,messageFound);
//        }
//        if(includeStackTrace) {
//            trace = (String) errorAttributes.get("trace");
//            if(!StringUtils.isEmpty(trace)) {
//                message += String.format(" and trace %s", trace);
//            }
//        }
//        return FailureResponseBody.builder().code(0).status(status).message(message).build();
//    }
    //从错误类型中获取map
    private Map<String, Object> getErrorAttributes(HttpServletRequest request,WebRequest webRequest, boolean includeStackTrace) {
        return errorAttributes.getErrorAttributes(webRequest,includeStackTrace);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}