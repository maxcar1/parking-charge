package com.maxcar.kafka.controller;

import com.maxcar.base.util.Constants;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.kafka.service.MessageProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/7/11.
 */
@RestController
@RequestMapping("/kafka")
public class KaFkaController {

    Logger logger = LoggerFactory.getLogger(KaFkaController.class);
    @Value("${kafka.producer.topic}")
    private String defaultTopic;
    @Resource
    private MessageProducerService messageProducerService;

    @RequestMapping(value="/send/{topic}/{data}")
    public Result sendMsg(@PathVariable("topic")String topic, @PathVariable("data")String data){
        try {
            Result result = messageProducerService.sendMessage(topic,data,false,0, Constants.KAFKA_SASS);
            return result;
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
        }
        return Result.error("发送kafka失败");
    }
    @RequestMapping(value="/send")
    public Result sendMsg(@RequestBody String data){
        try {
            Result result = messageProducerService.sendMessage(defaultTopic,data,false,0, Constants.KAFKA_SASS);
            return result;
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
        }
        return Result.error("发送kafka失败");
    }
}
