package com.maxcar.stock.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.CollectionUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.redis.service.SsoService;
import com.maxcar.stock.pojo.CarChannel;
import com.maxcar.stock.pojo.CarChannelRel;
import com.maxcar.stock.service.CarChannelService;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarChannelVo;
import com.maxcar.stock.vo.CarVo;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author songxuefeng
 * @create 2018-08-21 14:22
 * @description: 运营管理
 **/
@RequestMapping("/api/oper")
@RestController
public class OperationController extends BaseController {
    @Autowired
    CarService carService;
    @Autowired
    UserTenantService userTenantService;
    @Autowired
    SsoService ssoService;

    @Autowired
    private CarChannelService carChannelService;


    /**
     * 上下架操作列表
     * @return
     */
    @GetMapping("/{carId}")
    @OperationAnnotation(title = "获取车辆上架信息")
    public InterfaceResult upDownCarList(@PathVariable String carId,HttpServletRequest request)throws Exception{
        InterfaceResult result = new InterfaceResult();
        User user=getCurrentUser(request);
        CarVo carVo=carService.getCarVoById(carId);

        if (StringUtils.isNotBlank(carVo.getTenant())){
            UserTenant tenant = userTenantService.selectByPrimaryKey(carVo.getTenant());
            if(tenant != null) {
                carVo.setTenantName(tenant.getTenantName());
            }
        }

//        List<CarChannelVo> carChannels=carChannelService.getCarchannel(carId);
//        carVo.setCarChannelVos(carChannels);
        List<CarChannel> carChannels=carChannelService.findAll(user.getMarketId());

        List<CarChannelVo> carChannelVos=new ArrayList<>();
        CarChannelVo carChannelVo;
        for (CarChannel carChannel:carChannels){
            if ("淘宝".equals(carChannel.getChannelName())){
                CarChannelRel carChannelRel=carChannelService.getCarchannelRel(carId,carChannel.getId());
                carChannelVo=new CarChannelVo();
                carChannelVo.setChannelNo(carChannel.getChannelNo());
                carChannelVo.setChannelName(carChannel.getChannelName());
                carChannelVo.setId(carChannel.getId());
                if (carChannelRel!=null&&carChannelRel.getShelfStatus()==1){
                    carChannelVo.setShelfStatus(1);
                    carChannelVo.setShelfTime(carChannelRel.getUpdateTime());
                }else {
                    carChannelVo.setShelfStatus(0);
                }
                carChannelVos.add(carChannelVo);
            }
        }


        carVo.setCarChannelVos(carChannelVos);

        result.InterfaceResult200(carVo);
        return result;
    }

    /**
     * 上架
     * @return
     */
    @PutMapping("/upcar/{carId}/{channelId}")
    @OperationAnnotation(title = "上架")
    public InterfaceResult upCar(@PathVariable String carId,@PathVariable String channelId) throws Exception{
        InterfaceResult result = new InterfaceResult();
        carChannelService.upCar(carId,channelId);
        return result;
    }

    /**
     * 下架
     * @return
     */
    @PutMapping("/downcar/{carId}/{channelId}")
    @OperationAnnotation(title = "下架")
    public InterfaceResult downCar(@PathVariable String carId,@PathVariable String channelId) throws Exception{
         return carChannelService.downCar(carId,channelId);
    }

    /**
     *获取平台上架数量
     * @return
     */
    @GetMapping("/shelfnum")
    @OperationAnnotation(title = "获取平台上架数量")
    public InterfaceResult getCarShelfNum(HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        User user=getCurrentUser(request);
        List<CarChannelVo> carChannelVos=carChannelService.getCarShelfNum(user.getMarketId());
        int total=0;
        for (CarChannelVo carChannelVo :carChannelVos){
            total=total+carChannelVo.getShelfCount();
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("total",total);
        jsonObject.put("carChannels",carChannelVos);
        result.InterfaceResult200(jsonObject);
        return result;
    }

    /**
     * 获取所有平台
     * @return
     */
    @GetMapping("/channellist")
    @OperationAnnotation(title = "获取所有平台信息")
    public InterfaceResult getCarChannelList(HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        User user=getCurrentUser(request);
        List<CarChannel> carChannels=carChannelService.findAll(user.getMarketId());
        result.InterfaceResult200(carChannels);
        return result;
    }

    /**
     * 获取车辆信息
     * @param ids
     * @return
     */
    @PostMapping("/cars")
    @OperationAnnotation(title = "获取多台车辆信息")
    public InterfaceResult getCarByIds(@RequestBody List<String> ids) throws Exception{
        InterfaceResult result = new InterfaceResult();
        List<CarVo> carVos=new ArrayList<>();
        for (String id:ids){
            CarVo carVo=carService.getCarVoById(id);
            if (StringUtils.isNotBlank(carVo.getTenant())){
                UserTenant tenant = userTenantService.selectByPrimaryKey(carVo.getTenant());
                if(tenant != null) {
                    carVo.setTenantName(tenant.getTenantName());
                }
            }
            carVos.add(carVo);
        }
        result.InterfaceResult200(carVos);
        return result;
    }

    /**
     * 批量上架
     * @return
     */
    @PostMapping("/upbatch")
    @OperationAnnotation(title = "批量上架")
    public InterfaceResult upcarsbatch(@RequestBody JSONObject jsonObject) throws Exception{
        InterfaceResult result = new InterfaceResult();
        JSONArray carids=jsonObject.getJSONArray("carIds");
        JSONArray channelIds=jsonObject.getJSONArray("channelIds");

        for (int i=0;i<carids.size();i++){
            for (int j=0;j<channelIds.size();j++){
                carChannelService.upCar(carids.getString(i),channelIds.getString(j));
            }
        }
        return result;
    }

    /**
     * 批量下架
     * @return
     */
    @PostMapping("/downbatch")
    @OperationAnnotation(title = "批量下架")
    public InterfaceResult downcarsbatch(@RequestBody JSONObject jsonObject) throws Exception{
        InterfaceResult result = new InterfaceResult();
        JSONArray carids=jsonObject.getJSONArray("carIds");
        JSONArray channelIds=jsonObject.getJSONArray("channelIds");

        for (int i=0;i<carids.size();i++){
            for (int j=0;j<channelIds.size();j++){
                carChannelService.downCar(carids.getString(i),channelIds.getString(j));
            }
        }
        return result;
    }

    @PostMapping("/list")
    @OperationAnnotation(title = "车辆运营列表")
    public InterfaceResult carchannelOperList(@RequestBody CarVo carVo,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        User user=getCurrentUser(request);
        carVo.setMarketId(user.getMarketId());
        carVo.setCarType(1);

        Map m = new HashMap();
        PageInfo<CarVo> pageInfo=carService.listCarChannelVo(carVo);
        List<CarVo> listCarVo = pageInfo.getList();
        int outStock = 0;
        int deleteStock = 0;
        if(CollectionUtil.listIsNotEmpty(listCarVo)) {
            for (CarVo car:listCarVo){
                if (StringUtils.isNotBlank(car.getTenant())){
                    UserTenant tenant = userTenantService.selectByPrimaryKey(car.getTenant());
                    if(tenant != null) {
                        car.setTenantName(tenant.getTenantName());
                    }
                }

                if (car.getShelfStatus()==null){
                    car.setShelfStatus(0);
                }

//                if (car.getShelfNum()>0){//上架
//                    car.setShelfStatus(1);//设置上架状态
//                    List<CarChannelVo> carChannels=carChannelService.getCarchannelsByCar(car.getId());
//                    car.setCarChannelVos(carChannels);
//
//                }else {
//                    car.setShelfStatus(0);//设置上架状态
//                }
                if(car.getStockStatus().equals(3)) {
                    outStock ++;
                }
                if (car.getStockStatus().equals(-1)) {
                    deleteStock++;
                }
            }
        }
        m.put("listCarVo", pageInfo);
        m.put("countStock", pageInfo.getTotal() - deleteStock);//库存量
        m.put("sumStockPrice", carService.getSumMarketPrice(carVo));//库存价格

        //上架信息
        List<CarChannelVo> carChannelVos=carChannelService.getCarShelfNum(user.getMarketId());
        int total=0;
        for (CarChannelVo carChannelVo :carChannelVos){
            total=total+carChannelVo.getShelfCount();
        }
        m.put("shelftotal",total);
        m.put("carChannels",carChannelVos);


        result.InterfaceResult200(m);
        return result;
    }

}
