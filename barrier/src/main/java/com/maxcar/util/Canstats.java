package com.maxcar.util;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 配置类
 */
public class Canstats {
    public static final int qos0 = 0;//最多一次，有可能重复或丢失
    //    Client[Qos=1,DUP=0/*重复次数*/,MessageId=x] --->PUBLISH--> Server收到后，存储Message，发布，删除，向Client回发PUBACK
    public static final int qos1 = 1;//至少一次，有可能重复。
    public static final int qos2 = 2;//只有一次，确保消息只到达一次（用于比较严格的计费系统）。

    public static final String between = "00000000000";
    //禁止出厂，标记
    public static final String jzcc = "FF08";

    //允许出厂
    public static final String yxcc = "8008";


    public static Random rand = new Random();
    //服务地址
//    public static final String HOST = "tcp://192.168.7.130:1883";

//    public static final String HOST = "tcp://192.168.3.59:1883";

    public static final String HOST = "tcp://192.168.3.134:61613";

//    public static final String HOST = "tcp://192.168.3.99:1883";

    //监听topic
    public static final String UPTOPIC = "MQTT_YL_UP";

    //客户端id，随机数
    public static final String clientid = "clientid";
    public static final String serverclientid = "serverclientid";

    //连接用户名
    public static String userName = "admin";    //非必须
    //连接密码
    public static String passWord = "password";  //非必须


    public static String headerBody = "4d43";//协议头
    public static String headerVersion = "01";//协议版本号



    public static String dzType = "0001";//设备类型
    public static String dzVersion = "0001";//设备版本号
    //非电池供电
    public static String dzPower = "FFFF";//设备电量


    //非电池供电

    public static String dzML = "81";//请求道闸命令

    public static String dzOpen = "8180";//开闸命令

    public static String dzClose = "81FF";//开闸命令

    public static String dgMark_union_id = "88"; //unionId获取地感的标识

    public static String dgMark_card = "89"; //卡号获取地感的标识

    public static final  Integer deleteType = -1;//在场
    public static final  Integer inType = 1;//在场
    public static final   Integer outType = 3;//出场
    public static final    Integer saleType = 4;//售出
    public static final  Integer saleOutType = 5;//售出已出场
    public final static String KAFKA_SASS = "SASS";//卡夫卡数据服务类型

    public final static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
}
