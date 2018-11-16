package com.maxcar.web.event;

import com.maxcar.user.entity.OperationRecord;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Configuration;

/**
 * @program: maxcar-util
 * @description: 操作事件监听发布者
 * @author: 罗顺锋
 * @create: 2018-05-17 17:12
 **/
//@Configuration
public class OperationEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;
    public void methodToMonitor() {
        LoginEvent beginEvent = new LoginEvent(this, new OperationRecord());
        this.eventPublisher.publishEvent(beginEvent);
    }

    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
