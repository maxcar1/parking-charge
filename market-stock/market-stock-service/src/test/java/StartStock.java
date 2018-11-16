/**
 * @program: maxcar-util
 * @description: 缓存服务启动类
 * @author: 罗顺锋
 * @create: 2018-04-19 18:14
 **/
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class StartStock {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        UserService userService = (UserService)applicationContext.getBean("userService");

        com.alibaba.dubbo.container.Main.main(args);
//		System.out.println(userService);
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
