package com.maxcar.permission.controller;


import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OrganizationsService;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 机构接口
 * @Param:
 * @return:
 * @Author: 罗顺锋
 * @Date: 2018/5/16
 */
@RestController
@RequestMapping("/api/org")
public class OrgController extends BaseController {
    @Autowired
    private OrganizationsService organizationsService;

    /**
     * @Description:机构列表，支持按条件查询
     * @Param: [market, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/4/25
     */
    @RequestMapping(value = "/list")
    @OperationAnnotation(title = "查询机构列表")
    public InterfaceResult list(@RequestBody Organizations organizations, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        organizations.setMarketId(user.getMarketId());
        organizations.setManagerFlag(user.getManagerFlag());//设置当前用户是否为管理员
        List<Organizations> users = organizationsService.searchOrganizations(organizations);
        interfaceResult.InterfaceResult200(users);
        return interfaceResult;
    }

    /**
     * @Description: 获取机构详情
     * @Param: [id, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/5/3
     */
    @RequestMapping(value = "/{id}")
    @OperationAnnotation(title = "查询机构详情")
    public InterfaceResult id(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        Organizations organizations = organizationsService.selectByPrimaryKey(id);
        interfaceResult.InterfaceResult200(organizations);
        return interfaceResult;
    }
    /**
     * 删除机构
     */
//    @RequestMapping(value="/delById/{orgId}")
//    @OperationAnnotation(title = "删除机构")
//    public InterfaceResult delById(@PathVariable("orgId")String orgId,HttpServletRequest req)throws Exception {
//        InterfaceResult interfaceResult = new InterfaceResult();
////        req.getParameter("id");
////        String id = req.getParameter("id");
//        interfaceResult = organizationsService.delOrganizations(orgId);
//        return interfaceResult;
//    }
//    /**
//     * 更新机构
//     */
//    @RequestMapping(value="/updateById")
//    @OperationAnnotation(title = "更新机构")
//    public InterfaceResult updateById(@RequestBody Organizations orgId,HttpServletRequest req)throws Exception {
//        InterfaceResult interfaceResult = new InterfaceResult();
//        
//        interfaceResult = organizationsService.updateByOrgId(orgId.getOrgId());
//        return interfaceResult;
//    }    

    /**
     * @Description: 新增或者修改机构，附带参数验证
     * @Param: [user, result, request, response]
     * @return: com.maxcar.base.pojo.InterfaceResult
     * @Author: 罗顺锋
     * @Date: 2018/4/25
     **/
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @OperationAnnotation(type = "C", title = "新增或者修改机构")
    public InterfaceResult save(@RequestBody @Valid Organizations organizations, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        //参数验证
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                interfaceResult.InterfaceResult600(error.getDefaultMessage());
                return interfaceResult;
            }
        }
        if(organizations.getMarketId()==null){
            organizations.setMarketId(user.getMarketId());
        }
        //操作用户
        interfaceResult = organizationsService.saveOrUpdateOrganizations(organizations);
        return interfaceResult;
    }


    @RequestMapping(value = "/delOrUpdateOrgId/{id}/{isvalid}")
    @OperationAnnotation(title = "删除或者修改机构")
    public InterfaceResult delOrUpdateOrgId(@PathVariable String id, @PathVariable int isvalid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        interfaceResult = organizationsService.delOrUpdateOrgId(id, isvalid);
        return interfaceResult;
    }

}