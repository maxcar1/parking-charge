package com.maxcar.market.quartz;

import com.maxcar.market.service.PropertyContractPayService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.stock.service.OutMarketCarService;
import com.maxcar.user.service.OutMarketManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @program: maxcar-util
 * @description: 定时扫描任务
 * @author: 罗顺锋
 * @create: 2018-04-23 09:50
 **/
public class SyncContrat extends SyncStrategyInterface{

    private static final Logger logger = LoggerFactory.getLogger(SyncContrat.class);

    @Autowired
    private PropertyContractPayService propertyContractPayService = (PropertyContractPayService) SpringApplication.getBean("propertyContractPayServiceImpl");

    @Autowired
    private PropertyContractService propertyContractService = (PropertyContractService) SpringApplication.getBean("PropertyContractServiceImpl");

    @Autowired
    private OutMarketCarService outMarketCarService = (OutMarketCarService) SpringApplication.getBean("OutMarketCarServiceImpl");

    public void doJob() {
        try {
            propertyContractPayService.addPropertyContractPay();
            propertyContractService.endPropertyContract();
            outMarketCarService.delCarByOutMarketTime();
            outMarketCarService.downTaoBao();
        } catch (Exception e) {
            logger.error("每月统计数据出错");
            e.printStackTrace();
        }
    }
}
