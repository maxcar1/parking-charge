package com.maxcar.kafka.controller;

import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.util.Canstats;
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
    public InterfaceResult sendMsg(@PathVariable("topic")String topic, @PathVariable("data")String data){
        InterfaceResult result = new InterfaceResult();
        try {
            result = messageProducerService.sendMessage(topic,data,false,0, Canstats.KAFKA_SASS);
        } catch (Exception e) {
            logger.error("发送kafka失败", e);

        }
        return result;
    }
    @RequestMapping(value="/send")
    public InterfaceResult sendMsg(@RequestBody String data){
        InterfaceResult result = new InterfaceResult();
        try {
            result = messageProducerService.sendMessage(defaultTopic,data,false,0, Canstats.KAFKA_SASS);
            return result;
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
        }
        return result;
    }
}
