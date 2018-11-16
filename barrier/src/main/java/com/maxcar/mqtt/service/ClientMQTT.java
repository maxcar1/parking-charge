package com.maxcar.mqtt.service;

import com.maxcar.util.Canstats;
import com.maxcar.util.UuidUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 模拟一个客户端接收消息
 * @author 罗顺锋
 *
 */
@Component
public class ClientMQTT {

    //类初始化执行
    @PostConstruct
    public void init() {
        validConnect(1000);
    }


    private static MqttClient client;

    Logger logger = LoggerFactory.getLogger(ClientMQTT.class);


    //设置在线标记
//    @Value("${mqtt.server.host}")
    private String mqttServerHost = Canstats.HOST;
//    @Value("${clientid}")
    private String clientId = "clientid" ;
//    @Value("${mqtt.push.topic}")
    private String topic = Canstats.UPTOPIC;
//    @Value("${mqtt.server.username}")
    private String username ="0";
//    @Value("${mqtt.server.password}")
    private String password = "1";

    public void connect() throws MqttException {
        //防止重复创建MQTTClient实例
            if (client==null) {
                // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
                clientId = UuidUtils.getRandByNum(5);
                client = new MqttClient(mqttServerHost, clientId, new MemoryPersistence());
                // 设置回调
                //传入自己，方便断线重连
                client.setCallback(new PushCallback(ClientMQTT.this));
            }
            //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
            if (!client.isConnected()) {
                client.connect(getOptions());
                logger.info("服务连接成功");
            }else {//这里的逻辑是如果连接成功就重新连接
                client.disconnect();
                client.connect(getOptions());
                logger.info("服务连接成功");
            }
            //设置道闸在线
//            isOnline = true;
            //订阅消息
            int[] Qos = {Canstats.qos2};
            String[] topic1 = {topic};
            client.subscribe(topic1, Qos);
            logger.info("已建立连接，监听" + topic);
//            MqttTopic topic = client.getTopic(Canstats.UPTOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            //遗嘱options.setWill(topic, "close".getBytes(), 2, true);
//            client.connect(getOptions());
    }


    //生成配置对象，用户名，密码等
    public MqttConnectOptions getOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        // MQTT的连接设置
        options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(false);
        // 设置连接的用户名
        options.setUserName(username);
        // 设置连接的密码
        options.setPassword(password.toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(2000);
        return options;
    }

    //断线重连机制，保证启动和运行过程中不中断服务,如果给定999以下参数，会只进行有限次数的重连，如果让系统不停重连需要number>999
    public void validConnect(int number) {
        for (int i = 0; i < number||number>999; i++) {
            try {
                connect();
//                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("连接失败,正在第"+i+"次尝试");
                continue;
            }
            return;
        }
        throw new RuntimeException("无法连接服务器");
    }
}