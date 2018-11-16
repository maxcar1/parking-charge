package com.maxcar.permission.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.Role;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.RoleResourceService;
import com.maxcar.user.service.RoleService;
import com.maxcar.user.service.UserService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description: 角色接口
 * @author: 罗顺锋
 * @create: 2018-05-03 11:49
 **/
@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @RequestMapping(value="/list")
    @OperationAnnotation(title = "查询角色列表")
    public InterfaceResult list(@RequestBody Role role,HttpServletRequest request, HttpServletResponse response)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        role.setMarketId(user.getMarketId());
        role.setManagerFlag(user.getManagerFlag());
        List<Role> users = roleService.searchRole(role);
        interfaceResult.InterfaceResult200(users);
        return interfaceResult;
    }

    /**
    * @Description: 修改或者删除角色接口
    * @Param: [role, request, response]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/4
    */
    @RequestMapping(value="/saveOrUpdate")
    @OperationAnnotation(type = "C",title = "新增或者修改角色")
    public InterfaceResult saveOrUpdate(@RequestBody Role role,HttpServletRequest request, HttpServletResponse response) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        if(role.getMarketId()==null)
            role.setMarketId(user.getMarketId());
        interfaceResult = roleService.saveOrUpdateRole(role);
        return interfaceResult;
    }

    /**
    * @Description: 根据id查询角色信息,通用，新增功能，新增传当前登录用户角色id
    * @Param: [id, request, response]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/4
    */
    @RequestMapping(value="/{id}")
    @OperationAnnotation(title = "查询角色详细信息")
    public InterfaceResult id(@PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        Role role = (user.getRoles()!=null&&user.getRoles().size()>0)?user.getRoles().get(0):null;
        interfaceResult.InterfaceResult200(roleService.selectRoleById(id,role.getRoleId()));
        return interfaceResult;
    }

    /**
     * @Description: 删除角色，启用，弃用用户，-1物理删除，0弃用，1启用
     * @Param: [id, isvalid, request, response],id多个情况用,分割
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/4/25
     */
    @RequestMapping(value="/{id}/{isvalid}",method = RequestMethod.GET)
    @OperationAnnotation(type = "U",title = "修改角色状态")
    public InterfaceResult delete(@PathVariable("id")String id,@PathVariable("isvalid")Integer isvalid,HttpServletRequest request, HttpServletResponse response) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            //插入用户
            interfaceResult = roleService.updateRoleStatus(id,isvalid);
        }catch (Exception ex){
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }
}
