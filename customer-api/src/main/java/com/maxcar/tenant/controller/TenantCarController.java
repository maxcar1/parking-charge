package com.maxcar.tenant.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.pojo.Car;
import com.maxcar.stock.service.CarService;
import com.maxcar.stock.vo.CarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.stock.dao.CarBaseMapper;
import com.maxcar.stock.pojo.CarBase;
import com.maxcar.stock.service.CarBaseService;
import com.maxcar.user.entity.Staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 商户端车辆信息
 *
 * @author chiyanlong
 * @ClassName: CarController
 * @date 2018年9月28日 上午11:46:14
 */
@RestController
@RequestMapping("/we/tenant")
public class TenantCarController extends BaseController {

    @Autowired
    private CarService carService;

	@Autowired
	private CarBaseService carBaseService;


    /**
     * 在库商品车列表
     *
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCarList", method = RequestMethod.POST)
    public InterfaceResult getCarList(@RequestBody CarVo car, HttpServletRequest req) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            PageInfo pageInfo = carService.getCarList(car);
            interfaceResult.InterfaceResult200(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500("服务器异常");
        }
        return interfaceResult;
    }

    /*
     * 车辆详情
     *
     * @param id 车辆详情id
     * @param CarBase
     * @return
	 */
    @RequestMapping(value = "/getCarDetail/{id}")
    public InterfaceResult getCarDetail(@PathVariable(value = "id") String id){
        InterfaceResult interfaceResult = new InterfaceResult();
		try {
            Map carBase = carService.getCarDetail(id);
            if (carBase == null) {
                interfaceResult.InterfaceResult600("无查询记录");
            } else {
                interfaceResult.InterfaceResult200(carBase);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            interfaceResult.InterfaceResult500("查询失败");
        }
        return interfaceResult;

	}
}
