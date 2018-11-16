package com.maxcar.kafka.service;

/**
 * 监听云端服务
 * Created by Administrator on 2018/7/12.
 */

//import com.alibaba.fastjson.JSONObject;
import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.util.HttpClientUtil;
import com.maxcar.util.HttpClientUtils;
import com.maxcar.util.JsonTools;
import com.maxcar.util.PostParam;
import io.swagger.models.auth.In;
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

//    @Autowired
//    private MessageProducerService messageProducerService;


    @KafkaListener(topics = {"${kafka.consumer.topic010}"})
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

    public InterfaceResult processData(String json) {
        InterfaceResult result = new InterfaceResult();
        logger.info("开始处理数据-->{}", json);
        json.replace("\\","");
        try {

            PostParam postParam = JsonTools.toObj(json, PostParam.class);
            result = processData(postParam);//本地执行
            postParam.setInterfaceResult(result);//云端回执信息
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
                String doPutJson = HttpClientUtil.get(url, null,null);
//                com.alibaba.fastjson.JSONObject fromObject = com.alibaba.fastjson.JSONObject.parseObject(doPutJson);
//                result =  com.alibaba.fastjson.JSONObject.toJavaObject(fromObject, InterfaceResult.class);
            } else{
                String doPostJson = HttpClientUtils.doPostJson(url, String.valueOf(postParam.getData()));
                logger.info("服务器返回结果--{}", doPostJson);
//                JSONObject fromObject = net.sf.json.JSONObject.fromObject(doPostJson);
//                result = JsonTools.toObj(doPostJson,InterfaceResult.class);
//                JSONObject fromObject = com.alibaba.fastjson.JSONObject.parseObject(doPostJson);
                result = JsonTools.toObj(doPostJson.toString(),InterfaceResult.class);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.InterfaceResult500("处理失败");
//            return ResponseContent.error(3, "处理失败");
        }
        return result;
    }
}