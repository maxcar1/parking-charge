package com.maxcar.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义监听器，用来监听系统启动，和注销
 */
@WebListener
public class SystemListener implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(SystemListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //下行代码负责初始化bean容器，否则无法获取springbean对象
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext())
                .getAutowireCapableBeanFactory().autowireBean(this);
        //设置全局变量
        logger.info("系统启动了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("系统注销了");
    }
}
