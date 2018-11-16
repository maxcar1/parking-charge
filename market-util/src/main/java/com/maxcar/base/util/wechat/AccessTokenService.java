package com.maxcar.base.util.wechat;


import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AccessTokenService {

    public static void init() {
        //先将市场的appid 和 sercet获取出来 方便后续定时刷新
        TokenThread.refeshMarket();
        //初始化成功之后开启每120分钟执行校验一次的操作 使用 ScheduledThreadPoolExecutor来实现 暂设置为1个容量
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);
        // 计量单位为秒 此处 设置为  0秒后开始执行此任务 后隔7200秒再继续执行获取 AccessToken
        stpe.scheduleWithFixedDelay(new TokenThread(), 0, 6000, TimeUnit.SECONDS);
    }

}