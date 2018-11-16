package com.maxcar.tenant.controller;

import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.Constants;
import com.maxcar.base.util.MatrixToImageWriter;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.market.model.request.DealRequest;
import com.maxcar.market.model.request.GetPropertyContractDetailListRequest;
import com.maxcar.market.service.DealService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.stock.service.CarService;
import com.maxcar.tenant.pojo.IntegralStatistics;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.IntegralStatisticsService;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.User;
import com.maxcar.user.service.ConfigurationService;
import com.maxcar.web.aop.OperationAnnotation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by chiyanlong on 2018/8/16.
 */
@RestController
public class UserTenantController extends BaseController {
    @Autowired
    private UserTenantService userTenantService;
    @Autowired
    private IntegralStatisticsService integralStatisticsService;
    @Autowired
    private PropertyContractService propertyContractService;
    @Autowired
    private MessageProducerService messageProducerService;
    @Autowired
    private DealService dealService;
    @Autowired
    private CarService carService;
    @Autowired
    private ConfigurationService configurationService;


    /**
     * 新增商户列表 POST请求
     *
     * @param userTenant 商户实体类
     * @param request
     * @return
     */
    @RequestMapping(value = "/userTenant/addUserTenant")
    @OperationAnnotation(title = "新增商户列表")
    public InterfaceResult addUserTenant(@Valid @RequestBody UserTenant userTenant, BindingResult result, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //参数验证
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                interfaceResult.InterfaceResult600(error.getDefaultMessage());
                return interfaceResult;
            }
        }
        User user = getCurrentUser(request);
        userTenant.setId(UuidUtils.generateIdentifier());
        userTenant.setInsertTime(new Date());
        userTenant.setInsertOperator(user.getUserId());
        userTenant.setMarketId(user.getMarketId());
        interfaceResult = userTenantService.addUserTenant(userTenant);
        //上行数据
        JSONObject jo = new JSONObject();
        UserTenant userTenantSyncById = userTenantService.selectByPrimaryKey(userTenant.getId());
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(userTenantSyncById);
        jo.put("user_tenant", jsonArray);
        JSONObject object = new JSONObject();
        object.put("syncDatas", jo);
        messageProducerService.sendMessage("sync_upload_data_test2", jo, false,
                0, Constants.KAFKA_SASS);
        return interfaceResult;
    }

    /**
     * 查询商户信息POST请求
     *
     * @param userTenant 商户实体类
     * @param request
     * @return
     */
    @RequestMapping(value = "/userTenant/getUserTenant")
    @OperationAnnotation(title = "根据不同条件查询商户信息")
    public InterfaceResult getUserTenant(@RequestBody UserTenant userTenant, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        String marketId = getCurrentUser(request).getMarketId();
        userTenant.setMarketId(marketId);
        String tenantName = userTenant.getTenantName();
        if("".equals(tenantName) || tenantName == null){
            interfaceResult.InterfaceResult600("商户名称不能为空");
            return interfaceResult;
        }
        UserTenant tenant = userTenantService.selectUserTenant(userTenant);
        if (tenant == null) {
            interfaceResult.InterfaceResult600("无查询记录");
        } else {
            interfaceResult.InterfaceResult200(tenant);
        }
        return interfaceResult;
    }

    /**
     * 分页查询商户列表
     *
     * @param userTenant 商户实体类
     * @param request
     * @return
     */
    @RequestMapping(value = "/userTenant/getUserTenantList")
    @OperationAnnotation(title = "查询商户列表")
    public InterfaceResult getUserTenantList(@RequestBody UserTenant userTenant, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        if (user != null) {
            userTenant.setMarketId(user.getMarketId());
        }
        Map map = new HashMap();
        PageInfo pageInfo = userTenantService.selectUserTenantList(userTenant);
        interfaceResult = userTenantService.getUserTenantCount(userTenant);
        Object data = interfaceResult.getData();

        map.put("pageInfo", pageInfo);
        map.put("tenantCount", data);
        interfaceResult.InterfaceResult200(map);

        return interfaceResult;
    }

    /**
     * 查询商户总数量 POST请求 无传入参数
     *
     * @return
     */
    @RequestMapping(value = "/userTenant/getUserTenantCount")
    @OperationAnnotation(title = "查询统计总商户数量")
    public InterfaceResult getUserTenantCount(@RequestBody UserTenant userTenant, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        userTenant.setMarketId(user.getMarketId());
        interfaceResult = userTenantService.getUserTenantCount(userTenant);

        return interfaceResult;
    }

    /**
     * 根据商户id查询商户详情
     *
     * @param id 商户id
     * @return
     */
    @RequestMapping(value = "/userTenant/getUserTenantDetail/{id}/{currentPage}/{pageSize}")
    @OperationAnnotation(title = "查询商户详情")
    public InterfaceResult getUserTenantDetail(@PathVariable(value = "id") String id, @PathVariable(value = "currentPage") Integer currentPage, @PathVariable(value = "pageSize") Integer pageSize,HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        UserTenant userTenant = new UserTenant();
        Map map = new HashMap<>();
        userTenant.setId(id);
        userTenant.setMarketId(getCurrentUser(request).getMarketId());
        UserTenant tenant = userTenantService.selectUserTenant(userTenant);//商户信息
        interfaceResult.InterfaceResult200(tenant);

        return interfaceResult;
    }


    /**
     * 根据商户id查询商户交易信息
     *
     * @param id
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/userTenant/selectDeal/{id}/{currentPage}/{pageSize}")
    @OperationAnnotation(title = "查询商户详情交易信息")
    public InterfaceResult selectDeal(@PathVariable(value = "id") String id, @PathVariable(value = "currentPage") Integer currentPage, @PathVariable(value = "pageSize") Integer pageSize) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        DealRequest dealRequest = new DealRequest();
        dealRequest.setTenantId(id);
        dealRequest.setCurPage(currentPage);
        dealRequest.setPageSize(pageSize);
        Map<String, Object> dealMap = dealService.selectDeal(dealRequest);//查询交易信息
        interfaceResult.InterfaceResult200(dealMap);

        return interfaceResult;
    }


    /**
     * 根据商户id查询商户租赁信息
     *
     * @param id
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/userTenant/getPropertyContractDetailList/{id}/{currentPage}/{pageSize}")
    @OperationAnnotation(title = "查询商户详情租赁信息")
    public InterfaceResult getPropertyContractDetailList(@PathVariable(value = "id") String id, @PathVariable(value = "currentPage") Integer currentPage, @PathVariable(value = "pageSize") Integer pageSize) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        GetPropertyContractDetailListRequest request = new GetPropertyContractDetailListRequest();
        request.setTenantId(id);
        request.setCurPage(currentPage);
        request.setPageSize(pageSize);
        PageInfo pageInfoPropertyContract = propertyContractService.getPropertyContractDetailList(request);//租赁信息
        interfaceResult.InterfaceResult200(pageInfoPropertyContract);

        return interfaceResult;
    }

    /**
     * 根据商户id查询商户考核计分信息
     *
     * @param integralStatistics
     * @param request
     * @return
     */
    @RequestMapping(value = "/userTenant/selectIntegralStatistics")
    @OperationAnnotation(title = "查询商户详情考核计分信息")
    public InterfaceResult selectIntegralStatistics(@RequestBody IntegralStatistics integralStatistics, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        User user = getCurrentUser(request);
        UserTenant userTenant = new UserTenant();
        if (user != null) {
            userTenant.setMarketId(user.getMarketId());
        }
        userTenant.setId(integralStatistics.getTenantNo());
        UserTenant tenant = userTenantService.selectUserTenant(userTenant);//商户信息
        if (tenant != null) {
            if (null != tenant.getTenantArea()) {
                integralStatistics.setArea(tenant.getTenantArea());
                integralStatistics.setMarketId(tenant.getMarketId());
                Map map = integralStatisticsService.selectIntegralStatistics(integralStatistics);//积分统计
                interfaceResult.InterfaceResult200(map);
            } else {
                interfaceResult.InterfaceResult600("商户区域不能为null");
                return interfaceResult;
            }
        } else {
            interfaceResult.InterfaceResult600("该商户没有积分考核信息");
            return interfaceResult;
        }


        return interfaceResult;
    }

    /**
     * 修改商户表信息表 POST请求
     *
     * @param userTenant 商户实体
     * @param request
     * @return
     */
    @RequestMapping(value = "/userTenant/updateUserTenantById")
    @OperationAnnotation(title = "修改商户表信息表")
    public InterfaceResult updateUserTenantById(@RequestBody UserTenant userTenant, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();

        User user = super.getCurrentUser(request);
        userTenant.setUpdateTime(new Date());
        userTenant.setUpdateOperator(user.getUserId());
        interfaceResult = userTenantService.updateUserTenantById(userTenant);

        return interfaceResult;
    }




    /**
     * 生成商户二维码
     *
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/userTenant/buildQRCode/{id}")
    @OperationAnnotation(title = "商户生成二维码")
    public void buildQRCode(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        InterfaceResult interfaceResult = new InterfaceResult();
        int width = 400;    //二维码图片的宽
        int height = 400;   //二维码图片的高
        String format = "png";  //二维码图片的格式
        ServletOutputStream stream = null;

        UserTenant userTenant = userTenantService.selectByPrimaryKey(id);
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userTenant.getId());
        jsonObject.put("no", userTenant.getTenantNo());
        jsonObject.put("name", userTenant.getTenantName());
        jsonObject.put("phone", userTenant.getContactMobile());
        jsonObject.put("address", (null == userTenant.getTenantAddress()) ? "空地址" : userTenant.getTenantAddress());
        jsonObject.put("type", userTenant.getTenantType());
        stream = response.getOutputStream();
        BitMatrix bitMatrix = new MultiFormatWriter().encode(jsonObject.toString(), BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, format, stream);

        if (stream != null) {
            stream.flush();
            stream.close();
        }
    }

    /**
     * 查询商户列表(不分页)
     *
     * @param userTenant 商户实体类
     * @param request
     * @return
     */
    @RequestMapping(value = "/userTenant/list")
    @OperationAnnotation(title = "查询商户列表")
    public InterfaceResult list(@RequestBody UserTenant userTenant, HttpServletRequest request) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User user = getCurrentUser(request);
        userTenant.setMarketId(user.getMarketId());
        List<UserTenant> list = userTenantService.list(userTenant);
        interfaceResult.InterfaceResult200(list);
        return interfaceResult;
    }


}
