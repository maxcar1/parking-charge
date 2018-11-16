package com.maxcar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 罗顺锋
 * springmvc 资源过滤器
 * 解决跨域问题
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private SystemInterceptor systemInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    /**
     * @Description: addPathPatterns 用于添加拦截规则
     * excludePathPatterns 用户排除拦截
     * @Param: [registry]
     * @return: void
     * @Author: 罗顺锋
     * @Date: 2018/5/18
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(systemInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/api-p/login","/error","/api-p/province","/api-p/city/province/**","/api-p/city/**","/api-p/ossInfo"
                        ,"/common/**","/api-p/wx/**"
                        //配置道闸不授权
                )
        ;
    }
//    registry.addInterceptor可以通过此方法添加拦截器, 可以是spring提供的或者自己添加的
}