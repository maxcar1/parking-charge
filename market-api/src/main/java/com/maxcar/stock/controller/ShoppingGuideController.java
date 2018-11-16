package com.maxcar.stock.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.CollectionUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.stock.pojo.ShoppingGuide;
import com.maxcar.stock.service.ShoppingGuideService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author luoqin
 * @Title: ShoppingGuideController
 * @ProjectName idea-workspace
 * @Description: TODO
 * @date 2018/8/23 14:09
 */
@RequestMapping("/api")
@RestController

public class ShoppingGuideController extends BaseController {

    @Autowired
    ShoppingGuideService shoppingGuideService;
    @Autowired
    UserTenantService userTenantService;

    @PostMapping("/list")
    public InterfaceResult listExample(@RequestBody ShoppingGuide example) {
        InterfaceResult interfaceResult = new InterfaceResult();
        PageInfo pageInfo = shoppingGuideService.listExample(example);
        List<ShoppingGuide> listExample = pageInfo.getList();
        if (CollectionUtil.listIsNotEmpty(listExample)) {
            for (ShoppingGuide shoppingGuide : listExample) {
                if (shoppingGuide != null && StringUtils.isNotBlank(shoppingGuide.getTenantId())) {
                    UserTenant tenant = userTenantService.selectByPrimaryKey(shoppingGuide.getTenantId());

                    shoppingGuide.setTenantName(tenant.getTenantName());
                }
            }
        }
        interfaceResult.InterfaceResult200(pageInfo);
        return interfaceResult;
    }
    @PostMapping("/add")
    public InterfaceResult add(@RequestBody ShoppingGuide shoppingGuide, HttpServletRequest request){
        InterfaceResult interfaceResult =new InterfaceResult();
        try{
            User user = super.getCurrentUser(request);
            shoppingGuide.setId(UuidUtils.generateIdentifier());
            interfaceResult=shoppingGuideService.addIntegralEvaluation(shoppingGuide);
        }catch (Exception ex){
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("导购统计新增失败");
        }
        return interfaceResult;
    }
    @PostMapping("update")
    public InterfaceResult update(@RequestBody ShoppingGuide shoppingGuide,HttpServletRequest request, HttpServletResponse response){
        InterfaceResult interfaceResult = new InterfaceResult();
        shoppingGuideService.updateByPrimaryKeySelective(shoppingGuide);
        return interfaceResult;

    }
    @RequestMapping("/deleteByPrimaryKey/{id}/{isValid}")//isValid=1,物理删除，isValid=0,逻辑删
    public InterfaceResult deleteByPrimaryKey(@PathVariable(value="id") String id,@PathVariable(value="isValid")int isValid) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            ShoppingGuide shoppingGuide = new ShoppingGuide();
            interfaceResult = shoppingGuideService.deleteByPrimaryKey(id, isValid);
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("删除出错");
        }
        return interfaceResult;
    }

}