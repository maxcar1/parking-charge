package com.maxcar.stock.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.stock.dao.ShoppingGuideMapper;
import com.maxcar.stock.pojo.ShoppingGuide;
import com.maxcar.stock.service.ShoppingGuideService;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoqin
 * @Title: ShoppingGuideServiceImpl
 * @ProjectName idea-workspace
 * @Description: TODO
 * @date 2018/8/23 17:03
 */

@Service("shoppingGuideService")
public class ShoppingGuideServiceImpl implements ShoppingGuideService {

    @Autowired
    ShoppingGuideMapper shoppingGuideMapper;
    @Autowired
    UserTenantService userTenantService;

    @Override
    public PageInfo listExample(ShoppingGuide example){
        PageHelper.startPage(StringUtils.isBlank(example.getPageCount()) ? 1 : example.getPageCount(),
                StringUtils.isBlank(example.getPageSize()) ? 20 : example.getPageSize());
        List<String> tIds = new ArrayList<>();
        List<ShoppingGuide> list = new ArrayList<>();
        if (null != example && StringUtils.isNotBlank(example.getTenantName())){
            List<UserTenant> userTenants = userTenantService.getTenantNameLike(example.getTenantName());
            if(userTenants.size() > 0){
                for (UserTenant userTenant:userTenants){
                    tIds.add(userTenant.getId());
                }
                list = shoppingGuideMapper.listShoppingGuide(tIds);
            }else {

            }
        }else{
            list = shoppingGuideMapper.listShopping();
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    @Override
    public InterfaceResult addIntegralEvaluation(ShoppingGuide shoppingGuide){
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        count = shoppingGuideMapper.insertSelective(shoppingGuide);
        if (count>0){
            interfaceResult.InterfaceResult200("新增成功");
        }else {
            interfaceResult.InterfaceResult600("新增失败");
        }
        return interfaceResult;
    }
    @Override
    public Boolean updateByPrimaryKeySelective(ShoppingGuide shoppingGuide){
        return shoppingGuideMapper.updateByPrimaryKeySelective(shoppingGuide)==1;
    }
    @Override
    public InterfaceResult deleteByPrimaryKey(String id,int isValid){
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        ShoppingGuide shoppingGuide = new ShoppingGuide();
        shoppingGuide.setId(id);
        shoppingGuide.setIsValid(isValid);
        if(isValid==0){
            count= shoppingGuideMapper.updateByPrimaryKey(shoppingGuide);
        }else{
            count = shoppingGuideMapper.deleteByPrimaryKey(id);
        }
        if(count>0){
            interfaceResult.InterfaceResult200("删除成功");
        }else {
            interfaceResult.InterfaceResult600("无删除数据");
        }
        return  interfaceResult;
    }

}
