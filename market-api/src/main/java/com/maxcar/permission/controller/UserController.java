package com.maxcar.permission.controller;


import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.*;
import com.maxcar.user.service.*;
import com.maxcar.user.vo.ChangeUser;
import com.maxcar.web.aop.OperationAnnotation;
import org.aspectj.lang.reflect.NoSuchPointcutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 用户接口
 * @author: 罗顺锋
 * @create: 2018-04-24 14:49
 **/
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private OrganizationsService organizationsService;


    /**
     * @Description: 用户列表，用户按条件查询
     * @Param: [user, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/4/25
     */
    @RequestMapping(value = "/list")
    @OperationAnnotation(title = "查询用户列表")
    public InterfaceResult list(@RequestBody User user, HttpServletRequest request, HttpServletResponse response)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
//        try {
            User newUser = super.getCurrentUser(request);
            user.setMarketId(newUser.getMarketId());
            user.setManagerFlag(newUser.getManagerFlag());//设置当前用户是否为管理员
            List<User> users = userService.searchUser(user);
            interfaceResult.InterfaceResult200(users);
//        }catch (Exception ex){
//            interfaceResult.InterfaceResult500();
//        }
        return interfaceResult;
    }

    /**
     * @Description: 根据id查询用户信息
     * @Param: [id, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/3
     */
    @RequestMapping(value = "/{id}")
    @OperationAnnotation(title = "查询用户详细信息")
    public InterfaceResult list(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        InterfaceResult interfaceResult = new InterfaceResult();
//        try {
            User user = userService.selectByPrimaryKey(id);
            user.setUserPwd(null);
            if (user != null) {
                List<Role> roles = userRoleService.selectRoleByUserId(id);
                user.setRoles(roles);

                //查询员工信息
                Staff staff = staffService.selectByPrimaryKey(user.getStaffId());
                user.setStaffName(staff != null ? staff.getStaffName() : null);
//                List<DataFunction> userData = dataFunctionService.selectDataFunctionByUserId(id);
//                user.setDataFunction(userData);
                interfaceResult.InterfaceResult200(user);
            }
//        } catch (Exception ex) {
//            interfaceResult.InterfaceResult500();
//        }
        return interfaceResult;
    }

    /**
     * @Description: 新增或者修改用户，附带参数验证
     * @Param: [user, result, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/4/25
     */
    @OperationAnnotation(type = "C", title = "新增或者修改用户")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public InterfaceResult save(@RequestBody @Valid User user, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //参数验证
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                interfaceResult.InterfaceResult600(error.getDefaultMessage());
                return interfaceResult;
            }
        }
        User currentUser = super.getCurrentUser(request);
        if(user.getMarketId()==null){
            user.setMarketId(currentUser.getMarketId());
        }
        //操作新增，修改用户
        interfaceResult = userService.saveOrUpdateUser(user);
        return interfaceResult;
    }


    /**
     * @Description: 删除用户，启用，弃用用户，-1物理删除，0弃用，1启用
     * @Param: [id, isvalid, request, response],id多个情况用,分割
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/4/25
     */
    @RequestMapping(value = "/{id}/{isvalid}", method = RequestMethod.GET)
    @OperationAnnotation(type = "U", title = "修改用户状态")
    public InterfaceResult delete(@PathVariable("id") String id, @PathVariable("isvalid") Integer isvalid, HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = getCurrentUser(request);
        if (-1 == isvalid && id.equals(user.getUserId())) {
            return getInterfaceResult("600","无法删除当前登录用户");
        }
        InterfaceResult interfaceResult = new InterfaceResult();
//        try {
            //插入用户
            userService.updateUserStatus(id, isvalid);
            interfaceResult.InterfaceResult200(null);
//        } catch (Exception ex) {
//            interfaceResult.InterfaceResult500();
//        }
        return interfaceResult;
    }


    /**
     * @Description: 获取用户功能菜单
     * @Param: [request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/4/27
     */
    @RequestMapping(value = "/menus/{ifAll}")
    @OperationAnnotation(title = "查询用户菜单")
    public InterfaceResult list(@PathVariable("ifAll") Boolean ifAll, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        List<Resource> resources = roleResourceService.getResourcesByUserId(user.getUserId(), ifAll);
        interfaceResult.InterfaceResult200(resources);
        return interfaceResult;
    }

    /**
     * @Description: 重置密码
     * @Param: [id, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/3
     */
    @RequestMapping(value = "/reset/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    @OperationAnnotation(type = "U", title = "重置用户状密码")
    public InterfaceResult reset(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        userService.resetPassword(id);
        return interfaceResult;
    }

    /**
     * @Description: 修改密码
     * @Param: [oldPassWord, passWword, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/3
     */
    @RequestMapping(value = "/changepwd")
    @OperationAnnotation(type = "U", title = "修改用户密码")
    public InterfaceResult changepwd(@RequestBody @Valid ChangeUser changeUser, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //参数验证
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                interfaceResult.InterfaceResult600(error.getDefaultMessage());
                return interfaceResult;
            }
        }
        User user = super.getCurrentUser(request);
        changeUser.setUserId(user.getUserId());//设置修改账户
        interfaceResult = userService.changePassword(changeUser);
        return interfaceResult;
    }

    @RequestMapping(value = "/department/{staffId}")
    @OperationAnnotation(type = "U", title = "获取部门信息")
    public InterfaceResult department(@PathVariable("staffId")String staffId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Staff staff = staffService.selectByPrimaryKey(staffId);
            Organizations organizations = organizationsService.selectByPrimaryKey(staff.getGroupId());
        if(organizations!=null){
            staff.setGroupName(organizations.getOrgName());
        }
        interfaceResult.InterfaceResult200(staff);
        return interfaceResult;
    }
}
