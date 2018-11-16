package com.maxcar.permission.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.City;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Province;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.ProvinceService;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.redis.service.SsoService;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.entity.OperationRecord;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.OperationRecordService;
import com.maxcar.user.service.UserService;
import com.maxcar.web.aop.OperationAnnotation;
import com.maxcar.web.aop.OperationType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: maxcar-util
 * @description: 首页控制器
 * @author: 罗顺锋
 * @create: 2018-04-24 14:49
 **/
@RestController
//@OperationAnnotation
public class IndexController extends BaseController{

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private SsoService ssoService;
    @Autowired
    OperationRecordService operationRecordService;

    @Value("${OSS_ENDPOINT}")
    private String OSS_ENDPOINT;

    @Value("${OSS_ACCESSKEYID}")
    private String OSS_ACCESSKEYID;

    @Value("${OSS_ACCESSKEYSECRET}")
    private String OSS_ACCESSKEYSECRET;

    @Value("${OSS_OBJECTNAME}")
    private String OSS_OBJECTNAME;

    @Value("${OSS_REGION}")
    private String OSS_REGION;


    @RequestMapping(value={"/","/index"})
    public String index(String token, HttpServletRequest request) {
        try {
            User user = super.getCurrentUser(request);
            userService.selectByPrimaryKey("1");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Hello World!";
    }
    /**
     * 登录
     * @param user
     * @param result
     * @param request
     * @return
     */
    @RequestMapping(value={"/login"},method = RequestMethod.POST)
    public InterfaceResult login(@RequestBody User user, BindingResult result, HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            //参数验证
            if(StringUtils.isBlank(user.getUserName())){
                interfaceResult.InterfaceResult600("用户名不能为空");
                return interfaceResult;
            }
            if(StringUtils.isBlank(user.getUserPwd())){
                interfaceResult.InterfaceResult600("密码不能为空");
                return interfaceResult;
            }
            user.setLastLoginIp(super.getIpAddr(request));
            interfaceResult  = userService.login(user);
            if(interfaceResult.getCode().equals("200"))
                publishLogin(interfaceResult);
        }catch (Exception ex){
            ex.printStackTrace();
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }

    @RequestMapping(value={"/users"},method = RequestMethod.POST)
    public InterfaceResult users( HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {

            User user = super.getCurrentUser(request);
            if(null==user){
                interfaceResult.InterfaceResult600("请求失败");
            }else {
                interfaceResult.InterfaceResult200(user);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }

    //发布登录事件
    protected void publishLogin(InterfaceResult interfaceResult){
        Map map = (Map) interfaceResult.getData();
        User user = (User) map.get("userInfo");
        OperationRecord record = new OperationRecord();
        record.setOperationId(UuidUtils.generateIdentifier());
        record.setOperationType(OperationType.R.toString());
        record.setUserName(user.getUserName());
        record.setUserId(user.getUserId());
        record.setTrueName(user.getTrueName());
        record.setOperationInfo("用户登录");
        record.setOperationTitle("登录");
        record.setOperationUrl("/login");
        operationRecordService.insertSelective(record);
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping(value={"/logout"})
    @OperationAnnotation(title = "退出",info = "退出系统")
    public InterfaceResult logout(HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        user.setLastLoginIp(super.getIpAddr(request));
        interfaceResult = userService.logout(user);
        return interfaceResult;
    }
    /**
    * @Description: 获取上传凭证
    * @Param: [request]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/8
    */
    @RequestMapping(value={"/ossInfo"})
    @OperationAnnotation(title = "获取上传凭证")
    public InterfaceResult ossInfo(HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        OssBean ossBean = new OssBean();
        ossBean.setOssObjectName(OSS_OBJECTNAME);
        ossBean.setOssAccesskeyId(OSS_ACCESSKEYID);
        ossBean.setOssAccesskeySecret(OSS_ACCESSKEYSECRET);
        ossBean.setOssEndpoint(OSS_ENDPOINT);
        ossBean.setOssRegion(OSS_REGION);
        interfaceResult.InterfaceResult200(ossBean);
        return interfaceResult;
    }
    /**
    * @Description: 获取省集合
    * @Param: [request]
    * @return: com.maxcar.core.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/16
    */
    @RequestMapping(value={"/province"})
    @OperationAnnotation(title = "获取省集合")
    public InterfaceResult province(HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        List<Province> provinceList = provinceService.getProvince();
        for (Province province:provinceList){
            province.setLabel(province.getName());
            List<City> cities = cityService.getCity(province.getId());
            for (City city:cities){
                city.setLabel(city.getName());
            }
            province.setChildren(cities);
        }
        interfaceResult.InterfaceResult200(provinceList);
        return interfaceResult;
    }

    /**
    * @Description: 根据省获取市
    * @Param: [province, request]
    * @return: com.maxcar.core.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/16
    */
    @RequestMapping(value={"/city/province/{province}"})
    @OperationAnnotation(title = "获取某个省对应的市集合")
    public InterfaceResult city(@PathVariable("province")Integer province,HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        interfaceResult.InterfaceResult200(cityService.getCity(province));
        return interfaceResult;
    }
    /**
    * @Description: 根究市获取省
    * @Param: [id, request]
    * @return: com.maxcar.core.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/16
    */
    @RequestMapping(value={"/city/{id}"})
    @OperationAnnotation(title = "获取某个市详细信息，包括所属省份id")
    public InterfaceResult cityid(@PathVariable("id")Integer id,HttpServletRequest request)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = super.getCurrentUser(request);
        interfaceResult.InterfaceResult200(cityService.getCityById(id));
        return interfaceResult;
    }
    class OssBean{

        private String ossEndpoint;
        private String ossAccesskeyId;
        private String ossAccesskeySecret;
        private String ossObjectName;

        public String getOssEndpoint() {
            return ossEndpoint;
        }

        public void setOssEndpoint(String ossEndpoint) {
            this.ossEndpoint = ossEndpoint;
        }

        public String getOssAccesskeyId() {
            return ossAccesskeyId;
        }

        public void setOssAccesskeyId(String ossAccesskeyId) {
            this.ossAccesskeyId = ossAccesskeyId;
        }

        public String getOssAccesskeySecret() {
            return ossAccesskeySecret;
        }

        public void setOssAccesskeySecret(String ossAccesskeySecret) {
            this.ossAccesskeySecret = ossAccesskeySecret;
        }

        public String getOssObjectName() {
            return ossObjectName;
        }

        public void setOssObjectName(String ossObjectName) {
            this.ossObjectName = ossObjectName;
        }

        public String getOssRegion() {
            return ossRegion;
        }

        public void setOssRegion(String ossRegion) {
            this.ossRegion = ossRegion;
        }

        private String ossRegion;
    }
}

