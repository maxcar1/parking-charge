package com.maxcar.kafka.service.impl;

import com.maxcar.base.util.dasouche.Result;
import com.maxcar.kafka.service.MessageProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @author 罗顺峰
 * @create 2018-07-14 16:10
 * @desc 卡夫卡消息提供者实现类
 **/
@Service("messageProducerService")
public class MessageProducerServiceImpl implements MessageProducerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private KafkaTemplate kafkaTemplate;


    @Value("${kafka.producer.topic}")
    private String upTopic;

    /**
     * kafka发送消息模板
     * @param topic 主题
     * @param data    数据
     * @param ifPartition 是否使用分区 0是\1不是
     * @param partitionNum 分区数 如果否使用分区为0,分区数必须大于0
     * @param role 角色用你来生产动态的key:bbc app erp..sass.
     */
    public Result sendMessage(String topic, Object data, Boolean ifPartition, Integer partitionNum, String role){
        topic = topic.equals("-1")?upTopic:topic;
        String key = role+"-"+data.hashCode();
//        String valueString = JSON.toJSONString(data);
        if(ifPartition){
            //表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partitionIndex, key, data.toString());
            Result res = checkProRecord(result);
            return res;
        }else{
//            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, valueString);
            kafkaTemplate.send(topic, key, data.toString());
//            Result res = checkProRecord(result);
            return Result.ok();
        }
    }

    /**
     * 根据key值获取分区索引
     * @param key
     * @param partitionNum
     * @return
     */
    private int getPartitionIndex(String key, int partitionNum){
        if (key == null) {
            Random random = new Random();
            return random.nextInt(partitionNum);
        }
        else {
            int result = Math.abs(key.hashCode())%partitionNum;
            return result;
        }
    }

    /**
     * 检查发送返回结果record
     * @param res
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Result checkProRecord(ListenableFuture<SendResult<String, String>> res){
        if(res!=null){
            try {
                SendResult r = res.get();//检查result结果集
                /*检查recordMetadata的offset数据，不检查producerRecord*/
                Long offsetIndex = r.getRecordMetadata().offset();
                if(offsetIndex!=null && offsetIndex>=0){
                    return Result.ok();
                }else{
                    return Result.error("未查到返回数据的offset");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Result.error("发送消息超时");
            } catch (ExecutionException e) {
                e.printStackTrace();
                return Result.error("发送消息超时");
            }
        }else{
            return Result.error("未查询到返回结果");
        }
    }
}
