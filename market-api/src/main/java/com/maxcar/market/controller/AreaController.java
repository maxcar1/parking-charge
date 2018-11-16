package com.maxcar.market.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.pojo.Area;
import com.maxcar.market.pojo.AreaShop;
import com.maxcar.market.service.AreaService;
import com.maxcar.market.service.AreaShopService;
import com.maxcar.market.vo.AreaVo;


import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @description: 区域管理
 * @author: songxuefeng
 * @create: 2018-08-17 11:29
 **/
@RequestMapping("/api/area")
@RestController
public class AreaController extends BaseController {
    @Autowired
    private AreaService areaService;

    @Autowired
    private AreaShopService areaShopService;

    /**
     * 添加区域
     * @param areaVo
     * @return
     * @throws Exception
     */
    @PostMapping("/add")
    @OperationAnnotation(title = "添加区域")
    public InterfaceResult addArea(@RequestBody AreaVo areaVo,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();

        Area area=new Area();
        User user = getCurrentUser(request);
        if (StringUtils.isNotBlank(areaVo.getName())){
            area.setName(areaVo.getName());
        }else {
            result.InterfaceResult600("区域名称不能为空");
            return result;
        }
        if (areaVo.getType()!=null){
            area.setType(areaVo.getType());
        }else {
            result.InterfaceResult600("物业类型不能为空");
            return result;
        }
        if (areaVo.getArea()!=null){
            area.setArea(areaVo.getArea());
        }else {
            result.InterfaceResult600("区域面积不能为空");
            return result;
        }

        if (area.getType()==1) {//车位

        }else {
            double total=0;
            for(AreaShop areaShop:areaVo.getAreaShops()){
                total+=areaShop.getParkSpace();
            }
            if (total>areaVo.getArea()){
                result.InterfaceResult600("商铺面积总和不能大于区域面积");
                return result;
            }
        }

        area.setId(UuidUtils.generateIdentifier());
        area.setMarketId(user.getMarketId());
        area.setInsertOperator(user.getUserId());
        area.setUpdateTime(new Date());
        if (areaService.addArea(area)!=1){
            result.InterfaceResult600("添加区域失败");
            return result;
        }
        for(AreaShop areaShop:areaVo.getAreaShops()){
            areaShop.setAreaId(area.getId());
            areaShop.setRentStatus(0);
            areaShop.setInsertOperator(user.getUserId());
            areaShop.setMarketId(user.getMarketId());
            areaShop.setId(UuidUtils.generateIdentifier());
            areaShop.setUpdateTime(new Date());
            areaShopService.insertAreashop(areaShop);
        }


        return result;

    }

    @PostMapping("/upd")
    @OperationAnnotation(title = "修改区域")
    public InterfaceResult updateArea(@RequestBody Area area,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();

        if (StringUtils.isNotBlank(area.getId())){
            if (area.getType()==1) {//车位
            }else {
                double count=areaShopService.getAreaCountTotal(area.getId());
                if (area.getArea()<count){
                    result.InterfaceResult600("商铺面积总和不能大于区域面积");
                    return result;
                }
            }

            User user = getCurrentUser(request);
            area.setUpdateOperator(user.getUserId());
            area.setUpdateTime(new Date());
            if (areaService.updateArea(area)!=1){
                result.InterfaceResult600("修改区域失败");
                return result;
            }

        }else {
            result.InterfaceResult600("区域id不能为空");
            return result;
        }


        return result;

    }

    @GetMapping("/get/{areaId}")
    @OperationAnnotation(title = "获取该区域及详细数量")
    public InterfaceResult getArea(@PathVariable String areaId,HttpServletRequest request) throws Exception{
        double rentNum=0;
        double notRentNum=0;
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);

        Area area=areaService.getAreaById(areaId);
        List<AreaShop> areaShops=areaShopService.getAreaShopListByAreaId(areaId,user.getMarketId());
        for (AreaShop areaShop:areaShops){
            if (areaShop.getRentStatus()==1){//已租
                rentNum=rentNum+areaShop.getParkSpace();
            }else {//未租
                notRentNum=notRentNum+areaShop.getParkSpace();
            }
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("area",area);
        jsonObject.put("rentNum",rentNum);
        jsonObject.put("notRentNum",notRentNum);
        jsonObject.put("totalRentNum",rentNum+notRentNum);
        jsonObject.put("areaShops",areaShops);

        result.InterfaceResult200(jsonObject);
        return result;
    }

    @PostMapping("/list")
    @OperationAnnotation(title = "获取区域列表")
    public InterfaceResult list(@RequestBody Area area,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        area.setMarketId(user.getMarketId());
        PageInfo<AreaVo> areaVos=areaService.listByArea(area);
        result.InterfaceResult200(areaVos);

        return result;
    }

    @GetMapping("/type/{type}")
    @OperationAnnotation(title = "根据物业类型获取哪些区域")
    public InterfaceResult getAreaBytype(@PathVariable int type,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        List<Area> areas=areaService.getAreaByType(type,user.getMarketId());
        result.InterfaceResult200(areas);
        return result;
    }

}
