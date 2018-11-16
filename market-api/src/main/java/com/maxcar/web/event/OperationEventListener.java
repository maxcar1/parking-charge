package com.maxcar.web.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import sun.rmi.runtime.Log;

import java.util.EventObject;

/**
 * @program: maxcar-util
 * @description: 操作监听器
 * @author: 罗顺锋
 * @create: 2018-05-17 17:31
 **/
//@Configuration
public class OperationEventListener  implements ApplicationListener {
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof LoginEvent) {//如果是登录事件
            System.out.println("11111");
        }
    }
}
