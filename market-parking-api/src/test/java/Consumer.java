
import com.maxcar.user.service.UserService;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * @program: maxcar-util
 * @description: 消费者
 * @author: 罗顺锋
 * @create: 2018-04-25 14:18
 **/
public class Consumer {
	
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "dubbo/dubbox-customer.xml" });
        context.start();

        UserService userService = (UserService) context.getBean("userService");
        //返回String调用
        System.out.println(userService.selectByPrimaryKey("1")); //
        System.in.read();
    }
}
