package com.maxcar.stock.service.impl;

import com.maxcar.base.util.Constants;
import com.maxcar.base.util.DatePoor;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.stock.dao.CarMapper;
import com.maxcar.stock.dao.CarRecordMapper;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.pojo.CarRecord;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.service.OutMarketCarService;
import com.maxcar.stock.vo.CarOut;
import com.maxcar.user.entity.OutMarketManage;
import com.maxcar.user.service.OutMarketManageService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemUpdateDelistingRequest;
import com.taobao.api.response.ItemUpdateDelistingResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @Author sunyazhou
 * @Date 2018/10/18 13:50
 * @desc
 */
@Service("outMarketCarService")
public class OutMarketCarServiceImpl implements OutMarketCarService {

    protected final Logger logger = Logger.getLogger(this.getClass());

    private static String[] status = {"3"}; // 出场状态

    private static String[] delAndSoldStatus = {"-1","4","5"};// 删除和售出状态

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private CarRecordMapper carRecordMapper;

    @Autowired
    private OutMarketManageService outMarketManageService;

    @Value("${kafka.producer.topic}")
    public String producerTopic;
    @Value("${kafka.consumer.topic006}")
    public String consumerTopic6;
    @Value("${kafka.consumer.topic007}")
    public String consumerTopic7;
    @Value("${kafka.consumer.topic008}")
    public String consumerTopic8;
    @Value("${kafka.consumer.topic010}")
    public String consumerTopic10;


    /**
     * 定时扫描删除车辆   并同步到本地车辆状态
     * @throws Exception
     */
    @Override
    public void delCarByOutMarketTime() throws Exception {

        // 获取所有配置出场时间控制的市场信息
        List<OutMarketManage> outMarketManages = outMarketManageService.selectByOutMarketType("1");
        if (outMarketManages.size() > 0){
            for (OutMarketManage o: outMarketManages) {
                // 获取该市场下出场信息的车辆
                List<Car> allOutMarketCar = getAllOutMarketCar(o.getMarketId(), status);
                // 获取出场车辆的最后一次出场记录
                List<CarOut> carOuts = getCarEndOutMarketDate(allOutMarketCar);
                if (carOuts != null && carOuts.size() >0){
                    for (CarOut c: carOuts) {
                        long diff =new Date().getTime() - c.getInsertTime().getTime();// 时间毫秒差
                        double outMarketTime = o.getOutMarketTime()*60*60*1000;// 允许出场的时间
                        if (diff >= outMarketTime ){
                            delLocalCar(c,o);// 删除车辆信息
                        }
                    }
                }
            }
        }else {
            return;
        }
    }


    /**
     * 同步删除本地车辆状态
     * 组装云端参数
     */
    public void delLocalCar(CarOut carOut,OutMarketManage outMarketManage){
        String id = carOut.getCarId();
        carService.deleteCarById(carOut.getCarId());
        String topic = consumerTopic7;
        switch (outMarketManage.getMarketId()) {
            case "006":
                topic = consumerTopic6;
                break;
            case "007":
                topic = consumerTopic7;
                break;
            case "008":
                topic = consumerTopic8;
                break;
            case "010":
                topic = consumerTopic10;
                break;
        }
        //同步删除本地车辆状态
        //组装云端参数
        PostParam postParam = new PostParam();
        postParam.setData(id);
        postParam.setMarket(outMarketManage.getMarketId());
        postParam.setUrl("/barrier/car/delete/" + id);
        postParam.setMethod("get");
        postParam.setOnlySend(false);
        postParam.setMessageTime(Constants.dateformat.format(new Date()));
        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);

    }


    /**
     * 定时下架淘宝
     * @throws Exception
     */
    @Override
    public void downTaoBao() throws Exception {
        // 查询状态为-1,4,5状态的车辆
        List<Car> cars = getAllOutMarketCar("010", delAndSoldStatus);
        if (cars != null && cars.size() > 0){
            for (Car c : cars) {
                if (c.getTaobaoId() != null && c.getTaobaoId() != ""){
                    downTaoBaoByTBid(c.getTaobaoId());
                }
            }
        }
    }


    /**
     * 查询市场下状态为指定的车辆的信息
     * @param marketId
     * @param status
     * @return
     */
    @Override
    public List<Car> getAllOutMarketCar(String marketId,String[] status) {
        Map map = new HashMap();
        map.put("marketId",marketId);
        map.put("status",status);
        List<Car> allOutMarketCar = carMapper.getAllMarketCarByStatus(map);
        return allOutMarketCar;
    }


    /**
     * 根据vin码查询车辆的最后一次出场信息
     * @param cars
     * @return
     */
    public List<CarOut> getCarEndOutMarketDate(List<Car> cars){
        if (cars != null){
            List<CarOut> list = new ArrayList<>();
            for (Car c: cars) {
                CarOut carOut = carRecordMapper.getCarOutMarketDate(c.getVin());
                if (carOut != null){
                    if (carOut.getType() == 1){
                        list.add(carOut);
                    }
                }
            }
            return list;
        }
        return null;
    }

    /**
     * 下架淘宝
     * @param taoBaoId
     */
    public void downTaoBaoByTBid(String taoBaoId){
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getResourceAsStream("/taobaoConfig.properties"));
            String APP_KEY = prop.getProperty("taobaoAppKey");
            String SECRET = prop.getProperty("taobaosecret");
            String API_URL = prop.getProperty("taobaoUploadUrl");
            String sessionKey = prop.getProperty("sessionKey");

            TaobaoClient client = new DefaultTaobaoClient(API_URL, APP_KEY, SECRET);
            ItemUpdateDelistingRequest req = new ItemUpdateDelistingRequest();
            req.setNumIid(Long.valueOf(taoBaoId));

            ItemUpdateDelistingResponse rsp = client.execute(req, sessionKey);
            logger.info("自动下架返回结果：" + rsp.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
