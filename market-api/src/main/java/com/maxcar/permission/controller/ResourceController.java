package com.maxcar.permission.controller;


import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.ResourceOption;
import com.maxcar.user.entity.Role;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.RoleResourceOptionService;
import com.maxcar.user.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 资源控制器
 * @author: 罗顺锋
 * @create: 2018-05-11 14:49
 **/
@RestController
@RequestMapping("/api/resource")
public class ResourceController extends BaseController {
    Logger logger = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    private RoleResourceOptionService roleResourceOptionService;


    @Autowired
    private UserRoleService userRoleService;
    /**
    * @Description: 根据角色id和资源id查询资源下角色对应数据访问权限
    * @Param: [id, request, response]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/11
    */
//    @RequestMapping(value="/data/{id}")
//    public InterfaceResult data(@PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
//        InterfaceResult interfaceResult = new InterfaceResult();
//        User user = super.getCurrentUser(request);
//        List<Role> roles = userRoleService.selectRoleByUserId(user.getUserId());
//        if(roles!=null&&roles.size()>0) {
//            List<ResourceData> resourceData = roleResourceDataService.getResourceDataByRoleIdAndResourceId(roles.get(0).getRoleId(), id);
//            interfaceResult.InterfaceResult200(resourceData);
//        }
//        return interfaceResult;
//    }

    /**
     * @Description: 根据id查询资源对应操作权限
     * @Param: [id, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/3
     */
    @RequestMapping(value="/option/{id}")
    public InterfaceResult option(@PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response)throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        List<Role> roles = userRoleService.selectRoleByUserId(user.getUserId());
        if(roles!=null&&roles.size()>0) { List<ResourceOption> resourceData = roleResourceOptionService.getResourceOptionByRoleIdAndResourceId(roles.get(0).getRoleId(),roles.get(0).getRoleId(), id);
            interfaceResult.InterfaceResult200(resourceData);
        }
        logger.info("查询成功");
        return interfaceResult;
    }
}
