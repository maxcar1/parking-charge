package com.maxcar;


import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.service.ApplicationContextHolder;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.mqtt.service.ClientMQTT;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@MapperScan("com.maxcar.barrier.dao")
//@PropertySource(value = {"classpath:jdbc.properties"})
@ImportResource(locations = { "classpath:config/spring-mybatis.xml"})
@SpringBootApplication
public class BarrierApplication {

    public static void main(String[] args) {

        SpringApplication.run(BarrierApplication.class, args);
        System.out.println("服务器启动...\n");
//        Server server = new Server();
//        server.init();

        //开启mqtt监听器
//        ClientMQTT client = new ClientMQTT();
////        client.start();
//        BarrierService barrierService = ApplicationContextHolder.getBean("barrierService");
//
//        try {
//            Barrier barrier = barrierService.selectByBarrierId("05D1FF373438594D43035233");
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
    }

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOrigin("*");
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 重要
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return new CorsFilter(source);
    }
}