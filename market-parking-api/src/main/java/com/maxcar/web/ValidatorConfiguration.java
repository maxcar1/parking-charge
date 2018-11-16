package com.maxcar.web;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @program: maxcar-util
 * @description: hibernate Validator服务器验证处理器
 * 快速失败返回模式：
 * @author: 罗顺锋
 * @create: 2018-04-25 17:17
 **/
@Configuration
public class ValidatorConfiguration {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        /**设置validator模式为快速失败返回*/
        postProcessor.setValidator(validator());
//                <!-- 校验器的工作模式:
//        validator.fail_fast:快速失败返回模式(只要有一个验证失败，则返回异常)
//        validator.normal:普通模式(会校验完所有的属性，然后返回所有的验证失败信息)

        return postProcessor;
    }

    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}