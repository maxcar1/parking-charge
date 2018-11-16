package com.maxcar.web.aop;

import io.swagger.models.auth.In;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Description: 操作记录注解
* @Param:
* @return:
* @Author: 罗顺锋
* @Date: 2018/5/17
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperationAnnotation {
    String type() default "R";//CURD
    String url() default "";//不需要强制指定
    String title() default "标题";
    String info() default "";
}