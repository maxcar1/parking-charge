package com.maxcar.market.quartz;

/**
 * 策略模式核心类
 * Created by Administrator on 2018/5/31.
 */
public class SyncStrategy {
    public void doJob(SyncStrategyInterface syncStrategyInterface){
        syncStrategyInterface.doJob();
    }
}
