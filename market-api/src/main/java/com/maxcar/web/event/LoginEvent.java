package com.maxcar.web.event;

import com.maxcar.user.entity.OperationRecord;
import org.springframework.context.ApplicationEvent;

import java.util.EventObject;

/**
 * @program: maxcar-util
 * @description: 发布系统操作事件
 * @author: 罗顺锋
 * @create: 2018-05-17 17:05
 **/
public class LoginEvent extends ApplicationEvent{
    public OperationRecord getOperationRecord() {
        return operationRecord;
    }

    public void setOperationRecord(OperationRecord operationRecord) {
        this.operationRecord = operationRecord;
    }

    private OperationRecord operationRecord;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public LoginEvent(Object source, OperationRecord operationRecord) {
        super(source);
        this.operationRecord = operationRecord;
    }
}
