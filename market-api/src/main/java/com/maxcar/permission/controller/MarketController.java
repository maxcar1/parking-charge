package com.maxcar.permission.controller;


import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.Market;
import com.maxcar.user.service.MarketService;
import com.maxcar.web.aop.OperationAnnotation;
import com.maxcar.web.aop.OperationType;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 市场接口
 * @author: 罗顺锋
 * @create: 2018-04-24 19:49
 **/
@RestController
@RequestMapping("/api/market")
public class MarketController extends BaseController {
    @Autowired
    private MarketService marketService;
    /**
    * @Description: 市场列表，支持按条件查询
    * @Param: [market, request, response]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/4/25
    */
    @RequestMapping(value="/list")
    @OperationAnnotation(title = " 查询市场列表")
    public InterfaceResult list(@RequestBody Market market, HttpServletRequest request, HttpServletResponse response)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        List<Market> users = marketService.searchMarket(market);
        interfaceResult.InterfaceResult200(users);
        return interfaceResult;
    }
    /**
    * @Description: 获取市场详情
    * @Param: [id, request, response]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/3
    */
    @RequestMapping(value="/{id}")
    @OperationAnnotation(title = "查询市场详细信息")
    public InterfaceResult id(@PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Market market = marketService.selectByPrimaryKey(id);
       /* if (market.getPicture() !=null && market.getPicture() !=""){
            String picture=market.getPicture();
            JSONObject jsonObject = JSONObject.fromObject(picture);
            picture=jsonObject.getString("path");
            market.setPicture(picture);
        }
        if (market.getLogo() !=null && market.getLogo() !=""){
            String logo=market.getLogo();
            JSONObject jsonObject = JSONObject.fromObject(logo);
            logo=jsonObject.getString("path");
            market.setLogo(logo);
        }*/
        interfaceResult.InterfaceResult200(market);
        return interfaceResult;
    }
    /**
    * @Description: 新增或者修改市场，附带参数验证
    * @Param: [user, result, request, response]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/4/25
    */
    @RequestMapping(value="/saveOrUpdate",method = RequestMethod.POST)
    @OperationAnnotation(type = "C",title = "新增或者修改市场信息")
    public InterfaceResult save(@RequestBody Market market, BindingResult result,HttpServletRequest request, HttpServletResponse response)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //参数验证
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                interfaceResult.InterfaceResult600(error.getDefaultMessage());
                return interfaceResult;
            }
        }
        //操作用户
        interfaceResult = marketService.saveOrUpdateMarket(market);
        return interfaceResult;
    }

    /**
    * @Description: 删除用户，启用，弃用用户，-1物理删除，0弃用，1启用
    * @Param: [id, isvalid, request, response],id多个情况用,分割
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/4/25
    */

    @RequestMapping(value="/{id}/{isvalid}",method = RequestMethod.GET)
    @OperationAnnotation(type = "U",title = "修改市场状态")
    public InterfaceResult delete(@PathVariable("id")String id,@PathVariable("isvalid")Integer isvalid,HttpServletRequest request, HttpServletResponse response)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //插入用户
        marketService.updateMarketStatus(id,isvalid);
        return interfaceResult;
    }
    
    
}
