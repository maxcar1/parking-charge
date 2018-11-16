package com.maxcar.base.util.wechat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseService {
    protected final Log logger = LogFactory.getLog(this.getClass());

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ApplicationContext applicationContext;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected String getBasePath() {
        String path = request.getContextPath();
        int port = request.getServerPort();
        String basepath = "";
        if (port == 80) {
            basepath = request.getScheme() + "://" + request.getServerName() + path;
        } else {
            basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        }
        return basepath;
    }
}
