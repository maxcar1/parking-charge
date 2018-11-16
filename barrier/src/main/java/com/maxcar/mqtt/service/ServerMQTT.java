package com.maxcar.mqtt.service;

import com.maxcar.util.CRC16M;
import com.maxcar.util.Canstats;
import com.maxcar.util.HexUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.Date;

/**
 * Title:Server 这是发送消息的服务端
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * @author luoshunfeng
 */

public class ServerMQTT {

    Logger logger = LoggerFactory.getLogger(ServerMQTT.class);
    private MqttClient client;
    private MqttTopic mqttTopic;

    private MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTT(){
    }

    public void init(String topic)throws MqttException {

        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(Canstats.HOST, Canstats.clientid + String.format("%05d", Canstats.rand.nextInt(10000)), new MemoryPersistence());
        connect(topic);
    }

    /**
     *  用来连接服务器
     */
    private void connect(String topic) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(Canstats.userName);
        options.setPassword(Canstats.passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(2000);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            mqttTopic = client.getTopic(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }

    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException {
        String dzId = "05D1FF373438594D43035233";
        String outParam = "";
        String value1 = Canstats.headerBody;
        //字符串长度/2
        String value2 = "leng";//44字节
        //协议版本
        String value3 = Canstats.headerVersion;
        String value4 = Canstats.dzML;//下发数据
        int time = (int)(System.currentTimeMillis()/1000);
        String timeStamp = PushCallback.toHexString(time);
        //id长度+id号+时间戳+设备类型+程序版本+设备电量
        String value5 = PushCallback.toHexString(dzId.length()/2)+dzId+timeStamp+Canstats.dzType+Canstats.dzVersion+Canstats.dzPower;
        String value6 = "000B81";
        String value7 = "";
        String value8 = "";//欢迎词
        value7 = Canstats.yxcc;//允许开闸
        value8 = "欢迎光临";
        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
        outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length()/2+2));

        System.out.println(outParam + "------发送数据");

        String outHex = CRC16M.GetModBusCRC(outParam);


        outParam = outParam + outHex;
        System.out.println(outParam + "------发送数据2");
        send(outParam,"MQTT_YL_DZ0");
    }

    //主动触发开闸关闸
    public static void send(String outParam,String topic) {
        try {
            ServerMQTT server = new ServerMQTT();
            server.init(topic);
            server.message = new MqttMessage();
            server.message.setQos(Canstats.qos1);  //保证消息能到达一次
            server.message.setRetained(false);//是否保持连接，客户端会适时发送
            server.message.setPayload(PushCallback.toBytes(outParam));
            System.out.println(outParam + "------发送数据");
            server.publish(server.mqttTopic, server.message);
            System.out.println(server.message.isRetained() + "------ratained状态");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public void send(byte[] data,String topic) {
        try {
//            String TOPIC = (topic == null || topic.equals("")) ? TOPIC : topic;
//            topic11 = client.getTopic((topic == null || topic.equals("")) ? TOPIC : topic);
            ServerMQTT server = new ServerMQTT();
            server.init(topic);
            server.message = new MqttMessage();
            server.message.setQos(Canstats.qos1);  //保证消息能到达一次
            server.message.setRetained(false);//是否保持连接，客户端会适时发送
            server.message.setPayload(data);
            server.publish(server.mqttTopic, server.message);
            logger.info(server.message.isRetained() + "------ratained状态");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}