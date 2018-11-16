package com.maxcar.barrier.controller;

import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.barrier.pojo.InterfaceResult;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.barrier.service.CarService;
import com.maxcar.mqtt.service.PushCallback;
import com.maxcar.mqtt.service.ServerMQTT;
import com.maxcar.util.CRC16M;
import com.maxcar.util.Canstats;
import com.maxcar.util.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/27.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/barrier")
public class BarrierController {
    public BarrierController() {
        System.out.print(111);
    }
    Logger logger = LoggerFactory.getLogger(BarrierController.class);
    @Autowired
    private BarrierService barrierService;
    @Autowired
    private CarService carService;


    @GetMapping("/car/delete/{id}")
    public void listCar(@PathVariable("id") String id,HttpServletRequest request) {
        try{
            barrierService.deleteCarById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @RequestMapping(value = "/getByRfid", method = RequestMethod.POST)
    public Car getByRfid(@RequestBody Car stockCarInfo, HttpServletRequest request, HttpServletResponse response) {
        String rfid = stockCarInfo.getRfid();
        return barrierService.selectByRFID(rfid);
    }

    /**
     * 根据道闸ID查询详情
     *
     * @param barrier
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/selectByBarrierId", method = RequestMethod.POST)
    public InterfaceResult selectByBarrierId(@RequestBody Barrier barrier, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Barrier barriers = barrierService.selectByBarrierId(barrier.getBarrierId());
            interfaceResult.InterfaceResult200(barriers);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;
    }

    /**
     * 查询道闸列表
     *
     * @return
     */
    @RequestMapping(value = "/selectBarrierList", method = RequestMethod.POST)
    public InterfaceResult selectBarrierList() {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            List<Barrier> list = barrierService.selectBarrierList();
            interfaceResult.InterfaceResult200(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;
    }


    /**
     * 修改或删除道闸配置（status 1为删除 0为修改）
     *
     * @param barrier
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateOrDelBarrier", method = RequestMethod.POST)
    public InterfaceResult updateOrDelBarrier(@RequestBody Barrier barrier, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            interfaceResult = barrierService.updateOrDelBarrier(barrier);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return interfaceResult;
    }
    //更新标签
    @RequestMapping(value = "car/updateCarRfid", method = RequestMethod.POST)
    public InterfaceResult updateCarRfid(@RequestBody Car car, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Car newCar = new Car();
            newCar.setId(car.getId());
            newCar.setRfid(car.getRfid());
            carService.updateByExampleSelective(newCar);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return interfaceResult;
    }


    /**
     * 保存车辆信息
     *
     * @param car
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/car/saveCar", method = RequestMethod.POST)
    public InterfaceResult saveCar(@RequestBody Car car, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Car newCar = carService.selectByPrimaryKey(car.getId());
            if(newCar!=null)
                carService.updateByExampleSelective(car);
            else
                carService.insertSelective(car);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return interfaceResult;
    }


    /**
     * 开闸
     * @param topic
     * @param dzId
     * @param type   0进场  -1应急出场 其他传的是金额,普通出场
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/open/{topic}/{dzId}/{type}", method = RequestMethod.GET)
    public InterfaceResult saveBarrier(@PathVariable("topic")String topic,
                                       @PathVariable("dzId")String dzId,
                                       @PathVariable("type")Integer type,
                                       HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
//            String dzId = "05D1FF373438594D43035233";
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
            if(type == -3){
                value8 = "请勿重复入场";
                value6 = "000B87";
                value4 = "87";
                value7 = "FF0C";
            }else if (type == -2){
                value8 = "欢迎光临";
            }else if(type == -1){
                value8 = "一路顺风";
            }else{
                value8 = "金额"+type+"元";
                byte[] b = value8.getBytes("gbk");
                //最小7字节,最大9字节,直接补0
                value7 = "FF0"+b.length;
            }
            outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
            outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length()/2+2));
            logger.info(outParam + "------发送数据");
            String outHex = CRC16M.GetModBusCRC(outParam);

            outParam = outParam + outHex;
            logger.info(outParam + "------发送数据2");
            ServerMQTT.send(outParam,topic);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("保存失败");
        }
        return interfaceResult;
    }

    /**
     * 微信公众号unionid下发
     * @param topic
     * @param dzId
     * @param type
     * @param id
     * @return
     */
    @GetMapping("/check/{topic}/{dzId}/{type}/{id}")
    public InterfaceResult checkGroundSense(@PathVariable("topic")String topic,
                                            @PathVariable("dzId")String dzId,
                                            @PathVariable("type")Integer type,
                                            @PathVariable("id")String id){
        InterfaceResult result = new InterfaceResult();
        try {
            String outParam = "";
            String value1 = Canstats.headerBody;
            //字符串长度/2
            String value2 = "leng";//44字节
            //协议版本
            String value3 = Canstats.headerVersion;
            String value4 = "";//下发数据
            int time = (int)(System.currentTimeMillis()/1000);
            String timeStamp = PushCallback.toHexString(time);
            //id长度+id号+时间戳+设备类型+程序版本+设备电量
            String value5 = PushCallback.toHexString(dzId.length()/2)+dzId+timeStamp+Canstats.dzType+Canstats.dzVersion+Canstats.dzPower;
            String value6 = "000B";
            String value7 = "";
            String value8 = "";//欢迎词
            if (type == -5){
                //卡号
                value4 = Canstats.dgMark_card;
            }else if(type == -4){
                //unionId
                value4 = Canstats.dgMark_union_id;
            }
            //-4标识下发检测是否压地感
            value8  = id;
            byte[] b = value8.getBytes("gbk");
            value7 = value4 + Integer.toHexString(b.length).toUpperCase();
            byte[] v6_length = (value7+value8).getBytes("gbk");
            String v6_len = Integer.toHexString(v6_length.length).toUpperCase();
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<4-v6_len.length();i++){
                sb.append("0");
            }
            value6 = sb.toString()+v6_len;
            outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
            outParam = outParam.replaceAll("leng", PushCallback.toHexStringBy0(outParam.length()/2+2));
            logger.info(outParam + "------发送数据");
            String outHex = CRC16M.GetModBusCRC(outParam);

            outParam = outParam + outHex;
            logger.info(outParam + "------发送数据2");
            ServerMQTT.send(outParam,topic);
        }catch (Exception e){
            result.InterfaceResult500("保存失败");
            e.printStackTrace();
        }
        return result;
    }

}
