package com.maxcar.mqtt.service;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.barrier.pojo.*;
import com.maxcar.barrier.service.*;
import com.maxcar.base.util.StringUtils;
import com.maxcar.hikvision.service.HikvisionService;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.util.CRC16M;
import com.maxcar.util.Canstats;
import com.maxcar.util.HexUtils;
import com.maxcar.util.PostParam;
import com.maxcar.util.*;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 发布消息的回调类
 * <p>
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。
 * 必须在回调类中实现三个方法：
 * <p>
 * public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。
 * <p>
 * public void connectionLost(Throwable cause)在断开连接时调用。
 * <p>
 * public void deliveryComplete(MqttDeliveryToken token))
 * 接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。
 * 由 MqttClient.connect 激活此回调。
 */
public class PushCallback implements MqttCallback {


    private String keepPath = "/data/parking/image";

    SimpleDateFormat fmt1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public PushCallback() {

    }
    //客户端监听类
    ClientMQTT service;

    public PushCallback(ClientMQTT service) {
        this.service = service;
    }

    Logger logger = LoggerFactory.getLogger(PushCallback.class);

    //断线处理方法
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        logger.info("消费端连接断开，可以做重连");
        while (true) {
            try {//如果没有发生异常说明连接成功，如果发生异常，则死循环
                Thread.sleep(1000);
                service.init();
                break;
            } catch (Exception e) {
                continue;
            }
        }
    }
    //
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("deliveryComplete---------" + token.isComplete());
    }
    //处理方法主体
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            // subscribe后得到的消息会执行到这里面
            logger.info("接收消息主题 : " + topic);
//            logger.info("接收消息Qos : " + message.getQos());
 //           logger.info("接收消息message : " + String.valueOf(message.getPayload()));
            String str = new String(message.getPayload());
            logger.info("接收消息转换前内容 : " + str);
            //第一步过滤链接请求
            if (str.replace(" ", "").indexOf("101800044D51545") == -1) {
                String data = bytesToHex(message.getPayload());
                logger.info("转换后消息内容 : " + data);
                //操作数据除了前六位
                String clientData = data.substring(6,data.length());
                String hex = CRC16M.GetModBusCRC(clientData.substring(0, clientData.length() - 4));
                String check = clientData.substring(clientData.length() - 4);
                //校验数据是否正确
                logger.info("签名前：" + check+"签名后:"+hex);
                if (hex.equalsIgnoreCase(check)) {//校验数据准确性
                    String outParam = "";
                    //请求初始化配置参数,获取请求类型01rfid，02请求参数配置
                    String codeType = clientData.substring(10, 12);
                    logger.info("查询配置开始时间：" + fmt1.format(new Date()));
                    BarrierService barrierService = ApplicationContextHolder.getBean("barrierService");
                    String barrierId = clientData.substring(14, 38);
                    Barrier barrier = barrierService.selectByBarrierId(barrierId.toUpperCase());
                    logger.info(barrierId.toUpperCase()+"是否查到配置"+barrier+"查询配置结束时间：" + fmt1.format(new Date()));
                    if (barrier == null) {//配置有误
                        outParam = failDz(clientData);
                    } else {
                        switch (codeType){
                            case "01":
                                //请求开闸
                                Map map = openDz(clientData, barrier);
                                outParam = map.get("outParam") + "";
                                responseMQTT(barrier,outParam);
                                if (map.get("stockCarInfo") != null) {
                                    Car car = (Car) map.get("stockCarInfo");
                                    uploadData(car);//请求云端
                                }
                                break;
                            case "02":
                                //初始配置参数
                                outParam = initDz(clientData, barrier);
                                responseMQTT(barrier,outParam);
                                break;
                            case "07":
                                //ic卡处理
                                //截取卡号10位数
                                String cardNo16 = clientData.substring(66, 76);
                                /*doCard(barrier,cardNo16);*/
                                testResponse(barrier);
                                break;
                            case "08":
                                //微信unionid处理
                                //根据硬件发过来的判断
                                String key = clientData.substring(64,66);
                                Integer keyLen = Integer.valueOf(key,16) * 2;
                                String id = clientData.substring(66,66 + keyLen);
                                logger.info("道闸发送的unionid或者key:{}", HexUtils.convertHexToString(id));
                                switch (barrier.getInOutType()){
                                    case 0:
                                        uploadRequestCloudIn(barrier, id,1);
                                        break;
                                    case 1:
                                        uploadRequestCloudOut(barrier,id,1);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "09":
                                //刷卡出场回执asc ii码
                                String key1 = clientData.substring(64,66);
                                Integer keyLen1 = Integer.valueOf(key1,16) * 2;
                                String cardNo = clientData.substring(66,66 + keyLen1);
                                doCard(barrier,HexUtils.convertHexToString(cardNo));
                                break;
                            default:
                                break;
                        }
                    }
                }else{
                    logger.info("=====签名错误处理开始,消息二次发送====");
                    //如果签名错误,取data前六位
                    String barrierId = data.substring(0,6);
                    //拆分topic
                    String[] topics = topic.split("_");
                    if (topic.length() > 3){
                        String t = topics[0]+"_"+topics[1];
                        BarrierService barrierService = ApplicationContextHolder.getBean("barrierService");
                        Barrier barrier = barrierService.getBarrierInfoLike(barrierId,t);
                        String outParam = "";
                        String value1 = Canstats.headerBody;
                        //字符串长度/2
                        String value2 = "leng";//44字节
                        //协议版本
                        String value3 = Canstats.headerVersion;
                        String value4 = "8A";//下发数据
                        int time = (int)(System.currentTimeMillis()/1000);
                        String timeStamp = PushCallback.toHexString(time);
                        //id长度+id号+时间戳+设备类型+程序版本+设备电量
                        //12位数
                        String value5 = PushCallback.toHexString(barrier.getBarrierId().length()/2)+barrier.getBarrierId()+timeStamp+Canstats.dzType+Canstats.dzVersion+Canstats.dzPower;
                        String value6 = "000B8A";
                        String value7 = "";
                        value7 = Canstats.yxcc;//允许开闸
                        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7;
                        outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length()/2+2));
                        logger.info(outParam + "------发送数据");
                        String outHex = CRC16M.GetModBusCRC(outParam);

                        outParam = outParam + outHex;
                        logger.info(outParam + "------发送数据2");
                        ServerMQTT.send(outParam,barrier.getMqttTopic());
                    }

                }

            }else{
                logger.info("错误的请求");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void testResponse(Barrier barrier){
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
        String value5 = PushCallback.toHexString(barrier.getBarrierId().length()/2)+barrier.getBarrierId()+timeStamp+Canstats.dzType+Canstats.dzVersion+Canstats.dzPower;
        String value6 = "000B81";
        String value7 = "";
        String value8 = "欢迎光临";//欢迎词
        value7 = Canstats.yxcc;//允许开闸
        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
        outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length()/2+2));
        logger.info(outParam + "------发送数据");
        String outHex = CRC16M.GetModBusCRC(outParam);

        outParam = outParam + outHex;
        logger.info(outParam + "------发送数据2");
        ServerMQTT.send(outParam,"MQTT_YL_DZ0");
    }

    private void responseMQTT(Barrier barrier,String outParam){
        //回执MQTT消息统一处理
        if (barrier != null && barrier.getMqttTopic() != null) {
            byte b[] = toBytes(outParam);
            ServerMQTT serverMQTT = new ServerMQTT();
            logger.info(barrier.getMqttTopic() + "huifu消息内容：" + outParam);
            serverMQTT.send(b, barrier.getMqttTopic());
        }
    }

    private void doCard(Barrier barrier,String cardNo16) throws Exception{
        logger.info("道闸发送的卡号:{}", cardNo16);
        switch (barrier.getInOutType()){
            case 0:
                //入口
                uploadRequestCloudIn(barrier, cardNo16,0);
                break;
            case 1:
                //出口
                uploadRequestCloudOut(barrier, cardNo16,0);
                break;
            default:
                break;
        }
    }


    private void uploadRequestCloudOut(Barrier barrier, String key,int type) throws Exception {
        //刷卡开闸,扫码上行
        MessageProducerService messageProducerService = ApplicationContextHolder.getBean("messageProducerService");
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setMarket(barrier.getMarketId());
        StringBuilder url = new StringBuilder();
        url.append("/api-p/wx/out/");
        url.append(key);
        url.append("/");
        url.append(barrier.getBarrierId());
        url.append("/");
        //0 刷卡  1 微信上行的key
        url.append(type);
        url.append("/");
        url.append(barrier.getMarketId());
        postParam.setUrl(url.toString());
        postParam.setOnlySend(false);
        postParam.setMethod("get");
        postParam.setMessageTime(Canstats.dateformat.format(new Date()));
        logger.info("道闸开始发送上行消息至停车收费系统：{}", JsonTools.toJson(postParam));
        messageProducerService.sendMessage("-2",
                JsonTools.toJson(postParam), false, 0, Canstats.KAFKA_SASS);
    }

    private void uploadRequestCloudIn(Barrier barrier, String cardNo,int type) throws Exception{
        //上传数据到云端
        MessageProducerService messageProducerService = ApplicationContextHolder.getBean("messageProducerService");
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setMarket(barrier.getMarketId());
        StringBuilder url = new StringBuilder();
        url.append("/api-p/wx/in/");
        url.append(barrier.getMarketId());
        url.append("/");
        url.append(cardNo);
        url.append("/");
        url.append(barrier.getBarrierId());
        url.append("/");
        //0 刷卡  1 unionId
        url.append(type);
        BarrierCameraService barrierCameraService = ApplicationContextHolder.getBean("barrierCameraService");
        BarrierCamera barrierCamera = new BarrierCamera();
        barrierCamera.setBarrierId(barrier.getBarrierId());
        barrierCamera.setMarketId(barrier.getMarketId());
        List<BarrierCamera> barrierCameras = barrierCameraService.selectCameraByBarrierId(barrierCamera);
        if (null != barrierCameras && barrierCameras.size() > 0){
            BarrierCamera camera = barrierCameras.get(0);
            camera.setPath(keepPath);
            Map map = HikvisionService.requestDll(camera);
            boolean result = (Boolean) map.get("result");
            if (result){
                File file = new File(String.valueOf(map.get("imageName")));
                FileInputStream fis = new FileInputStream(file);
                postParam.setData(fis);
                postParam.setUrl(url.toString());
                postParam.setOnlySend(false);
                postParam.setMethod("camera-get");
                postParam.setMessageTime(Canstats.dateformat.format(new Date()));
                logger.info("道闸开始发送上行消息至停车收费系统：{}", JsonTools.toJson(postParam));
                messageProducerService.sendMessage("-2",
                        JsonTools.toJson(postParam), false, 0, Canstats.KAFKA_SASS);
            }else{
                logger.info("====摄像机抓拍失败====");
            }
        }else{
            logger.info("======请检查摄像机配置表====");
        }
    }

    //同步数据到云端
    public void uploadData(Car car) {
        MessageProducerService messageProducerService = ApplicationContextHolder.getBean("messageProducerService");
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setData(JsonTools.toJson(car));
        postParam.setMarket(car.getMarketId());
        postParam.setUrl("/common/car/updateStatus");
        postParam.setOnlySend(false);
        postParam.setMessageTime(Canstats.dateformat.format(new Date()));
        //发送数据
        logger.info("道闸开始发送消息到云端：");
        messageProducerService.sendMessage("-1", JsonTools.toJson(postParam), false, 0, Canstats.KAFKA_SASS);
    }
    //找不到道闸配置，由于找不到主板id因此无法发送消息
    public String failDz(String clientData) {
        String outParam = "";
        String value1 = clientData.substring(0, 4);
        String value2 = "002C";//44字节
        String value3 = clientData.substring(8, 10);
        String value4 = "81";//下发数据
        String value5 = clientData.substring(12, 58);
        String value6 = "000B81";
        String value7 = Canstats.jzcc;
        String value8 = "系统错误";
        outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//禁止出入
        String outHex = CRC16M.GetModBusCRC(outParam);
        outParam = outParam + outHex;
        return outParam;
    }
    //配置失败，中途道闸id被删除等原因,只给时间
    public String initDz(String clientData, Barrier barrier) {
        String outParam = "";
        //查出的十进制转为十六进制
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        Integer years = c.get(Calendar.YEAR);
        String year = "0" + years.toHexString(years);
        Integer months = c.get(Calendar.MONTH);
        String month = "0" + months.toHexString(months);
        String date = toHexString(c.get(Calendar.DATE));
        String hour = toHexString(c.get(Calendar.HOUR_OF_DAY));
        String minute = toHexString(c.get(Calendar.MINUTE));
        String second = toHexString(c.get(Calendar.SECOND));
        String week = getWeekOfDate();

        String gateWapIp = barrier.getGatewayIp();//网关
        String subNetMask = barrier.getSubnetMask();//子网掩码
        String mscAddress = barrier.getMacAddress();//物理地址十六进制
        String clientIp = barrier.getClientIp();//道闸ip
        String clientPort = barrier.getClientPort();//道闸端口
        String serverIp = barrier.getServerIp();//服务端ip
        String serverPort = barrier.getServerPort();//服务器端口
        String[] array = gateWapIp.split("\\.");
        gateWapIp = "";
        for (int i = 0; i < array.length; i++) {
            gateWapIp += toHexString(Integer.parseInt(array[i]));
        }

        String[] array1 = subNetMask.split("\\.");
        subNetMask = "";
        for (int i = 0; i < array1.length; i++) {
            subNetMask += toHexString(Integer.parseInt(array1[i]));
        }

        String mscAdd = mscAddress.replaceAll(":", "");

        String[] array2 = clientIp.split("\\.");
        clientIp = "";
        for (int i = 0; i < array2.length; i++) {
            clientIp += toHexString(Integer.parseInt(array2[i]));
        }

        Integer port = (Integer.parseInt(clientPort));
        clientPort = toHexStringByPort(port);

        String[] array3 = serverIp.split("\\.");
        serverIp = "";
        for (int i = 0; i < array3.length; i++) {
            serverIp += toHexString(Integer.parseInt(array3[i]));
        }
        Integer port1 = (Integer.parseInt(serverPort));
        serverPort = toHexStringByPort(port1);


        outParam = clientData.substring(0, 4) + "0051" + clientData.substring(8, 10) + "82" + clientData.substring(12, 58) + "003082" + year + month + date + hour + minute + second + week + clientData.substring(12, 38) + gateWapIp + subNetMask + mscAdd + clientIp + clientPort + serverIp + serverPort;
        logger.info(outParam);
        String outHex = CRC16M.GetModBusCRC(outParam);
        outParam = outParam + outHex;
        return outParam;
    }

    //道闸验证
    public Map openDz(String clientData, Barrier barrier) throws Exception {
        Map map = new HashMap();
        logger.info("道闸验证消息 : " + clientData);
        BarrierService barrierService = ApplicationContextHolder.getBean("barrierService");
        String outParam = "";
        String value1 = clientData.substring(0, 4);
        String value2 = "002C";//44字节
        String value3 = clientData.substring(8, 10);
        String value4 = "81";//下发数据
        String value5 = clientData.substring(12, 58);
        String value6 = "000B81";
        String value7 = "";
        String value8 = "";//欢迎词

        String clientDatas = clientData.substring(66, 74);
        if (clientData.equals("aaaaaaaa")) {//错误的rfid

        }
        long valueTens = Long.parseLong(clientDatas, 16);
        String values = "" + valueTens;
        while (values.length() < 10) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(values);// 左补0
            values = sb.toString();
        }
        String marketId = barrier.getMarketId();
        logger.info("rfid" + marketId + Canstats.between + values);
        logger.info("道闸是否受限制：" + !barrier.getStatus().equals("0"));
        if (barrier.getStatus().equals("0")) {//道闸不限制
            logger.info("查询车辆开始时间：" + fmt1.format(new Date()));
            Car stockCarInfo = barrierService.selectByRFID(marketId + Canstats.between + values);//查询是否允许开闸
            logger.info("查询车辆结束时间：" + fmt1.format(new Date()));
            map.put("stockCarInfo", stockCarInfo);
            if (stockCarInfo != null) {
                value7 = Canstats.yxcc;//允许开闸
                value8 = barrier.getStaticSpeech();
                outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
                String outHex = CRC16M.GetModBusCRC(outParam);
                outParam = outParam + outHex;
                logger.info("车辆进场记录已生成");
                if(barrier.getInOutType() == 0) {//入口
                    stockCarInfo.setStockStatus(Canstats.inType);//如果是入场改状态为已入场，反之为已出场
                }else{
                    if (stockCarInfo.getStockStatus() == Canstats.saleType)//售出未出场，把状态改为已出场
                        stockCarInfo.setStockStatus(Canstats.saleOutType);
                    else
                        stockCarInfo.setStockStatus(Canstats.outType);
                }
                initCarStatus(stockCarInfo, barrier);
            } else {
                value7 = Canstats.jzcc;
                value8 = "查无此车";
                outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//禁止出入
                String outHex = CRC16M.GetModBusCRC(outParam);
                outParam = outParam + outHex;
            }
        } else if (barrier.getStatus().equals("1")) {//道闸限制车辆出入

            logger.info("查询车辆开始时间：" + fmt1.format(new Date()));
            Car stockCarInfo = barrierService.selectByRFID(marketId + Canstats.between + values);//查询是否允许开闸
            logger.info("查询车辆结束时间：" + fmt1.format(new Date()));
//            logger.info("车辆信息:" + JsonTools.toJson(stockCarInfo));
            if (stockCarInfo != null) {
                map.put("stockCarInfo", stockCarInfo);
                Map remap = valid(stockCarInfo, barrier);
                value7 = Canstats.yxcc;//允许开闸
                if ((Boolean) remap.get("flag")) {
                    value7 = Canstats.jzcc;//禁止开闸
                }
                value8 = remap.get("title") + "";
                outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
                String outHex = CRC16M.GetModBusCRC(outParam);
                outParam = outParam + outHex;
                initCarStatus(stockCarInfo, barrier);
            } else {
                value7 = Canstats.jzcc;
                value8 = "查无此车";
                outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//禁止出入
                String outHex = CRC16M.GetModBusCRC(outParam);
                outParam = outParam + outHex;
            }
            map.put("stockCarInfo", stockCarInfo);
            logger.info("车辆进场记录已生成:" + outParam);
        }
        map.put("outParam", outParam);
        return map;
    }

    //验证车辆是否可以入场，其中库存车能正常出入，售出已出场和删除车不能入，售出未出场只能出
    public Map valid(Car car, Barrier barrier) {
        Map map = new HashMap();
        String title = barrier.getStaticSpeech();
        map.put("flag", false);
        if(barrier.getInOutType() == 0) {//入口
            switch (car.getStockStatus()){
                case -1://删除车
                    title = "车已删除";
                    map.put("flag", true);
                    break;
                case 5://售出车
                    title = "车已售出";
                    map.put("flag", true);
                    break;
                case 4://售出未出厂
                    title = "车已售出";
                    map.put("flag", true);
                    break;
                default:
                    car.setStockStatus(Canstats.inType);//如果是入场改状态为已入场，反之为已出场
                    break;
            }
        }else{//出口，未来质押车做验证
            if(car.getStockStatus() == Canstats.saleType)//售出未出场，把状态改为已出场
                car.setStockStatus(Canstats.saleOutType);
            else
                car.setStockStatus(Canstats.outType);
        }
        logger.info(title + car.getStockStatus() + "==" + barrier.getInOutType() + "车已删除");
        map.put("title", title);
        map.put("car",car);//设置车辆最新状态
        return map;
    }

    //修改车状态，
    public Car initCarStatus(Car car, Barrier barrier) {
        CarRecordService carRecordService = ApplicationContextHolder.getBean("carRecordService");
        CarRecord carRecord = new CarRecord();
        carRecord.setChannel(barrier.getBarrierId());
        carRecord.setChannelName(barrier.getBarrierPosition());
        carRecord.setId(UuidUtils.generateIdentifier());
        carRecord.setMarketId(car.getMarketId());
        carRecord.setCarNo(car.getId());
        carRecord.setRfid(car.getRfid());
        carRecord.setType(barrier.getInOutType());
        carRecord.setVin(car.getVin());
        carRecordService.saveCarRecord(carRecord);//插入出场入场记录
        CarService carService = ApplicationContextHolder.getBean("carService");
        car.setCarRecord(carRecord);
        carService.updateCarStatus(car);//更新车状态
        logger.info("车辆状态已更新:" + car.getStockStatus());
        return car;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate() {
        Date date = new Date();
        String[] weekDays = {"07", "01", "02", "03", "04", "05", "06"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    //端口号补零
    private static String toHexStringByPort(Integer str) {
        String returnStr = str + "";
        if (str < 4096) {
            returnStr = "0" + str.toHexString(str);
        } else {
            returnStr = str.toHexString(str);
        }
        return returnStr;
    }
    /**
     * hex字符串转byte数组
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=hexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }


    /**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte
     */
    public static byte hexToByte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }

    private String bytesToHex(byte[] bytes, int begin, int end) {
        StringBuilder hexBuilder = new StringBuilder(2 * (end - begin));
        for (int i = begin; i < end; i++) {
            hexBuilder.append(Character.forDigit((bytes[i] & 0xF0) >> 4, 16)); // 转化高四位
            hexBuilder.append(Character.forDigit((bytes[i] & 0x0F), 16)); // 转化低四位
            hexBuilder.append(' '); // 加一个空格将每个字节分隔开
        }
        return hexBuilder.toString().toUpperCase();
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 字节数组转16进制
     * @param bytes 需要转换的byte数组
     * @return  转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /*
    十进制转十六进制前面补0
     */
    public static String toHexString(Integer times) {
        String time;
        if (times < 16) {
            time = "0" + times.toHexString(times);
        } else {
            time = times.toHexString(times);
        }
        return time;
    }

    /*
    十进制转十六进制前面补0
     */
    public static String toHexStringBy0(Integer times) {
        String time;
        if (times <= 99) {
            time = "00" + times.toHexString(times);
        } else {
            time = times.toHexString(times);
        }
        return time;
    }

    //将指定byte数组以16进制的形式打印到控制台
    public static void printHexString(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
//            logger.info(hex.toUpperCase() );
        }

    }

    public static void main(String[] args) {
        byte[] by = hexToByteArray("4d43002701010c05daff373438594d430352395bbc497e0001ecd7a8848b7dc000c000c000c000");
        for(int i=0;i<by.length;i++){
            System.out.println(by[i]);
        }
    }
}