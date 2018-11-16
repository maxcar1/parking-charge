/**
 * @program: maxcar-util
 * @description: 缓存服务启动类
 * @author: 罗顺锋
 * @create: 2018-04-19 18:14
 **/
public class StartCommon {
    public static void main(String[] args) {
        //通过dubbo提供的方式启动dubbo
        com.alibaba.dubbo.container.Main.main(args);
    }
}
