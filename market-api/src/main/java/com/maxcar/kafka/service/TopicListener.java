package com.maxcar.kafka.service;

/**
 * Created by Administrator on 2018/7/12.
 */

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.util.HttpClientUtils;
import com.maxcar.kafka.util.ResponseContent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;


public class TopicListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${kafka.producer.topic}")
    private String topic;
    @Value("${local.servers.address}")
    private String address;

    @Autowired
    private MessageProducerService messageProducerService;


    @KafkaListener(topics = {"${kafka.producer.topic}"})
    public void listener(ConsumerRecord<?, ?> record) {
        try {
            logger.info("kafka的key: " + record.key());
            logger.info("kafka的value: " + record.value().toString());
            processData(record.value()+"");
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    /**
     * 反射执行对应请求
     * @param json
     * @return
     */
    public InterfaceResult processData(String json) {
        InterfaceResult result = new InterfaceResult();
        logger.info("开始处理数据-->{}", json);
        json.replace("\\","");
        logger.info("处理后数据-->{}", json);
        try {

            PostParam postParam = JSONObject.parseObject(json, PostParam.class);
            result = processData(postParam);//本地执行
            postParam.setInterfaceResult(result);//云端回执信息
//            messageProducerService.sendMessage(topic,JsonTools.toJson(postParam),false,0, Constants.KAFKA_SASS);
            if (result != null && result.getCode().equals("200")) {

                logger.info(json + "数据处理成功-->{}", result.getMsg());
            } else {
                logger.info(json + "数据处理失败--{}", result.getMsg());
            }
        } catch (Exception e) {
            result.InterfaceResult500("处理失败");
//            return new ResponseContent(3, "处理失败");
        }
        return result;
    }

    private InterfaceResult processData(PostParam postParam) {
        InterfaceResult result = new InterfaceResult();
        try {
            String method = postParam.getMethod();
            String url = address+postParam.getUrl();
            if (method !=null && method.equals("get")) {
                String doPutJson = HttpClientUtils.doPutJson(postParam.getUrl(), String.valueOf(postParam.getData()));
                com.alibaba.fastjson.JSONObject fromObject = com.alibaba.fastjson.JSONObject.parseObject(doPutJson);
                result =  com.alibaba.fastjson.JSONObject.toJavaObject(fromObject, InterfaceResult.class);
            } else{
                String doPostJson = HttpClientUtils.doPostJson(url, String.valueOf(postParam.getData()));
                com.alibaba.fastjson.JSONObject fromObject = com.alibaba.fastjson.JSONObject.parseObject(doPostJson);
                result =  com.alibaba.fastjson.JSONObject.toJavaObject(fromObject, InterfaceResult.class);
            }
//            if(result.getCode().equals("200")){
//                messageProducerService.sendMessage(topic,result.getData(), false, 0, Constants.KAFKA_SASS);
//            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.InterfaceResult500("处理失败");
//            return ResponseContent.error(3, "处理失败");
        }
        return result;
    }

    public static void main(String[] args){
        String json = "{\"serviceMethod\":\"invalidApply\",\"serviceName\":\"customerService\",\"serviceParams\":{\"carId\":\"001105\",\"token\":\"c47acb7f8b70336a19c7abad129939d8c716bbe9\",\"user\":\"0012\",\"market\":\"001\"},\"type\":\"reflect\"}";
//        JSONObject jsonObject = JSONObject.fromObject(json);
        System.out.print(json.replace("\\",""));
        String results = HttpClientUtils.doPostJson("http://47.96.29.224:2222/maxcar/customer/service",json);
        ResponseContent result = JsonTools.toObj(results,ResponseContent.class);
    }
}