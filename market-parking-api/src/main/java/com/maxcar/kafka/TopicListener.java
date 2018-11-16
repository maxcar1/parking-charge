package com.maxcar.kafka;

/**
 * Created by Administrator on 2018/7/12.
 */

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.dasouche.HttpClientUtil;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.kafka.util.HttpClientUtils;
import com.maxcar.kafka.util.ResponseContent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TopicListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*@Value("${kafka.producer.topic}")
    private String topic;*/
    @Value("${local.servers.parking.address}")
    private String address;

    @Autowired
    private MessageProducerService messageProducerService;


    @KafkaListener(topics = {"${kafka.producer.parking.topic}"})
    public void listener(ConsumerRecord<?, ?> record) {
        try {
            logger.info("kafka的key: " + record.key());
            logger.info("kafka的value: " + record.value().toString());
            processData(record.value() + "");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    /**
     * 反射执行对应请求
     *
     * @param json
     * @return
     */
    public InterfaceResult processData(String json) {
        InterfaceResult result = new InterfaceResult();
        logger.info("开始处理数据-->{}", json);
        json.replace("\\", "");
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
            String url = address + postParam.getUrl();
            if (method != null && method.equals("get")) {
                String doPutJson = HttpClientUtil.get(url, null, null);
                JSONObject fromObject = JSONObject.parseObject(doPutJson);
                result = JSONObject.toJavaObject(fromObject, InterfaceResult.class);
            } else if (StringUtils.equals("camera-get", method)) {
                //摄像机请求单独处理
                FileInputStream fis = (FileInputStream) postParam.getData();
                String imageUrl = getImageUrl(fis);
                JSONObject json = new JSONObject();
                json.put("imageUrl",imageUrl);
                String doPostJson = HttpClientUtils.doPostJson(url, json.toJSONString());
                JSONObject fromObject = JSONObject.parseObject(doPostJson);
                result = JSONObject.toJavaObject(fromObject, InterfaceResult.class);
            } else {
                String doPostJson = HttpClientUtils.doPostJson(url, String.valueOf(postParam.getData()));
                JSONObject fromObject = JSONObject.parseObject(doPostJson);
                result = JSONObject.toJavaObject(fromObject, InterfaceResult.class);
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

    public static String getImageUrl(FileInputStream fis) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf1.format(Calendar.getInstance().getTime());
        String imageName = sdf.format(Calendar.getInstance().getTime()) + UuidUtils.getRandByNum(3);
        StringBuilder path = new StringBuilder("/data/parking/image");
        path.append(File.separator);
        path.append(fileName);
        File f = new File(path.toString());
        if (!f.exists()) {
            f.mkdirs();
        }
        path.append(File.separator);
        path.append(imageName);
        path.append(".jpg");
        File file = new File(path.toString());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            // 每次读取的字节长度
            int n = 0;
            // 存储每次读取的内容
            byte[] bb = new byte[1024];
            while ((n = fis.read(bb)) != -1) {
                // 将读取的内容，写入到输出流当中
                fos.write(bb, 0, n);
            }
        } finally {
            if (null != fos) {
                fos.close();
            }
            if (null != fis) {
                fis.close();
            }
        }

        String yuming = "http://wxsf-t.maxcar.com.cn/";

        return yuming+"/"+fileName+"/"+imageName+".jpg";

    }

    public static void main(String[] args) {
        String json = "{\"serviceMethod\":\"invalidApply\",\"serviceName\":\"customerService\",\"serviceParams\":{\"carId\":\"001105\",\"token\":\"c47acb7f8b70336a19c7abad129939d8c716bbe9\",\"user\":\"0012\",\"market\":\"001\"},\"type\":\"reflect\"}";
//        JSONObject jsonObject = JSONObject.fromObject(json);
        System.out.print(json.replace("\\", ""));
        String results = HttpClientUtils.doPostJson("http://47.96.29.224:2222/maxcar/customer/service", json);
        ResponseContent result = JsonTools.toObj(results, ResponseContent.class);
    }
}