package com.maxcar.market.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/6/4.
 */
//@Service
public class AsyncExecutorTaskService {
    public AsyncExecutorTaskService(){
        System.out.print(111);
    }
    @Autowired
    private TaskExecutor taskExecutor;
    public void doJob(SyncStrategyInterface SyncStrategyInterface) {
        this.taskExecutor.execute(new CutFilesThread(SyncStrategyInterface));
    }

    private class CutFilesThread implements Runnable {
        private com.maxcar.market.quartz.SyncStrategyInterface SyncStrategyInterface;
        private CutFilesThread(com.maxcar.market.quartz.SyncStrategyInterface SyncStrategyInterface) {
            super();
            this.SyncStrategyInterface = SyncStrategyInterface;
        }
        @Override
        public void run() {
            SyncContrat syncContrat = SyncStrategyInterface instanceof SyncContrat ? ((SyncContrat) SyncStrategyInterface) : null;
            System.out.println("执行数据同步.. run..."+syncContrat);
            SyncStrategyInterface.doJob();
        }
    }
}