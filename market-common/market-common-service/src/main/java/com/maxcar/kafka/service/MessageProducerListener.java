package com.maxcar.kafka.service;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author 罗顺峰
 * @create 2018-07-16 15:52
 * @desc 消息发送监听器，监听返回结果成功or失败,配置到kafka生产者中
 **/
public class MessageProducerListener implements ProducerListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object data, RecordMetadata recordMetadata) {
        logger.info("kafka发布成功:主题={}:,kafka的消息={}", topic, data);
    }

    @Override
    public void onError(String topic, Integer partition, Object key, Object data, Exception e) {
        logger.info("kafka发布失败:主题={}:,kafka的消息={}", topic, data);
    }

    /**
     * 方法返回值代表是否启动kafkaProducer监听器
     * @return
     */
    @Override
    public boolean isInterestedInSuccess() {
        return true;
    }
}
