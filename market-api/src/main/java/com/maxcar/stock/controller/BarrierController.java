package com.maxcar.stock.controller;

import com.maxcar.BaseController;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.JsonTools;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.service.CarService;
import com.maxcar.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/27.
 */
@RestController
@RequestMapping("/api/barrier")
public class BarrierController extends BaseController {
    Logger logger = LoggerFactory.getLogger(BarrierController.class);
    @Autowired
    private BarrierService barrierService;

    @Autowired
    private CarService carService;
    @Autowired
    MessageProducerService messageProducerService;

    @RequestMapping(value = "/getByRfid", method = RequestMethod.POST)
    @ResponseBody
    public Car getByRfid(@RequestBody Car stockCarInfo, HttpServletRequest request, HttpServletResponse response) {
        String rfid = stockCarInfo.getRfid();
        return carService.selectByRfid(stockCarInfo.getRfid());
    }
    /**
     * 根据道闸ID查询详情
     * @param barrier
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/selectByBarrierId", method = RequestMethod.POST)
    @ResponseBody
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
    @ResponseBody
    public InterfaceResult selectBarrierList(@RequestBody Barrier barrier,HttpServletRequest request) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        barrier.setMarketId(user.getMarketId());
        barrier.setManagerFlag(user.getManagerFlag());
        List<Barrier> list = barrierService.selectBarrierList(barrier);
        interfaceResult.InterfaceResult200(list);
        return interfaceResult;
    }

/*    @RequestMapping(value = "/addBarrier", method = RequestMethod.POST)
    @ResponseBody
    public InterfaceResult addBarrier(@RequestBody Barrier barrier, HttpServletRequest request, HttpServletResponse response) {

        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Barrier barriers=barrierService.addBarrier(barrier);
            interfaceResult.InterfaceResult200(barriers);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("新增失败");
        }
        return interfaceResult;
    }*/

    /**
     * 修改或删除道闸配置（Isvalid 1为删除 0为修改）
     *
     * @param barrier
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateOrDelBarrier", method = RequestMethod.POST)
    @ResponseBody
    public InterfaceResult updateOrDelBarrier(@RequestBody Barrier barrier, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            User user = super.getCurrentUser(request);
            interfaceResult = barrierService.updateOrDelBarrier(barrier);
            String topic = topicService.getTopic(user.getMarketId());
            //同步删除本地车辆状态
            //组装云端参数
            PostParam postParam = new PostParam();
            postParam.setData(JsonTools.toJson(barrier));
            postParam.setMarket(user.getMarketId());
            postParam.setUrl("/barrier/updateOrDelBarrier");
            postParam.setOnlySend(false);
            postParam.setMessageTime(Constants.dateformat.format(new Date()));
            messageProducerService.sendMessage(topic, JsonTools.toJson(postParam),false,0,Constants.KAFKA_SASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return interfaceResult;
    }
/*    @RequestMapping(value = "/saveBarrier", method = RequestMethod.POST)
    @ResponseBody
    public InterfaceResult saveBarrier(@RequestBody Barrier barrier, HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            Barrier barriers=barrierService.saveBarrier(barrier);
            interfaceResult.setData(barriers);
            interfaceResult.setMsg("保存成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("保存失败");
        }
        return interfaceResult;
    }*/


}
