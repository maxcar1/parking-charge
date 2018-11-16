package com.maxcar.market.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.pojo.Area;
import com.maxcar.market.pojo.AreaShop;
import com.maxcar.market.service.AreaService;
import com.maxcar.market.service.AreaShopService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-08-28 9:37
 * @description: ${description}
 **/
@RequestMapping("/api/areashop")
@RestController
public class AreaShopController extends BaseController {
    @Autowired
    private AreaShopService areaShopService;

    @Autowired
    private AreaService areaService;

    @PutMapping("/upd")
    @OperationAnnotation(title = "更新区域商铺")
    public InterfaceResult updAreaShop(@RequestBody AreaShop areaShop,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();

        if (StringUtils.isNotBlank(areaShop.getId())){
            AreaShop areaShop1=areaShopService.getAreashop(areaShop.getId());
            Area area=areaService.getAreaById(areaShop1.getAreaId());
            if (area.getType()==1) {//车位
            }else {
                double count=areaShopService.getAreaCountTotal(areaShop1.getAreaId());
                if (count+areaShop.getParkSpace()-areaShop1.getParkSpace()>area.getArea()){
                    result.InterfaceResult600("商铺面积总和不能大于区域面积");
                    return result;
                }
            }
            User user = getCurrentUser(request);
            areaShop.setUpdateOperator(user.getUserId());
            areaShop.setUpdateTime(new Date());
            areaShopService.updateAreaShop(areaShop);
        }else {
            result.InterfaceResult600("更新失败");
        }
        return result;
    }

    @PostMapping("/add")
    @OperationAnnotation(title = "新增区域商铺")
    public InterfaceResult addAreaShop(@RequestBody AreaShop areaShop,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();

        if (StringUtils.isBlank(areaShop.getAreaNo())){
            result.InterfaceResult600("编号不能为空");
            return result;
        }
        if (areaShop.getParkSpace()==null){
            result.InterfaceResult600("车位或面积不能为空");
            return result;
        }

        if (StringUtils.isNotBlank(areaShop.getAreaId())){
            Area area=areaService.getAreaById(areaShop.getAreaId());
            User user = getCurrentUser(request);
            areaShop.setId(UuidUtils.generateIdentifier());
            areaShop.setInsertOperator(user.getUserId());
            areaShop.setMarketId(user.getMarketId());
            areaShop.setUpdateTime(new Date());
            if (area.getType()==1){//车位
                areaShopService.insertAreashop(areaShop);
            }else {
                double count=areaShopService.getAreaCountTotal(areaShop.getAreaId());
                //校验面积是否超过区域面积
                if (areaShop.getParkSpace()+count<=area.getArea()){//
                    areaShopService.insertAreashop(areaShop);
                }else {
                    result.InterfaceResult600("商铺面积总和不能大于区域面积");
                    return result;
                }
            }

        }else {
            result.InterfaceResult600("区域id不能为空");
        }
        return result;
    }

    @DeleteMapping ("/{areaShopId}")
    @OperationAnnotation(title = "删除区域商铺")
    public InterfaceResult delAreaShop(@PathVariable String areaShopId) throws Exception{
        InterfaceResult result = new InterfaceResult();
        areaShopService.deleteAreaShop(areaShopId);
        return result;
    }

    @PostMapping ("/list/{areaId}")
    @OperationAnnotation(title = "获取该区域所有商铺")
    public InterfaceResult getAreaShopList(@RequestBody AreaShop areaShop,@PathVariable String areaId,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        areaShop.setMarketId(user.getMarketId());
        PageInfo<AreaShop> areaShops=areaShopService.getAreaShopListByAreaId(areaShop,areaId);
        result.InterfaceResult200(areaShops);
        return result;
    }

    @GetMapping ("/rent/{areaId}")
    @OperationAnnotation(title = "获取该区域未被租用所有商铺")
    public InterfaceResult getAreaShopByRent(@PathVariable String areaId,HttpServletRequest request) throws Exception{
        InterfaceResult result = new InterfaceResult();
        User user = getCurrentUser(request);
        List<AreaShop> areaShops=areaShopService.getAreaShopByRent(areaId,0,user.getMarketId());
        result.InterfaceResult200(areaShops);
        return result;
    }
}
