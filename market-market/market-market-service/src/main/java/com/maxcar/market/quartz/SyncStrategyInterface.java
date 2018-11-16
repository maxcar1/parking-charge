package com.maxcar.market.quartz;

/**
 * 配置策略模式接口
 * Created by Administrator on 2018/5/31.
 */
public abstract class SyncStrategyInterface {
    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String reportDate;
    //需要执行的计划//传入统计时间，保证所有同步时间都在同一天。
    void doJob(){
    }
}
