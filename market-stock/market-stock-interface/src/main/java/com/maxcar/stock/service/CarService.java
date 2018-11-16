package com.maxcar.stock.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.stock.entity.CarParams;
import com.maxcar.stock.entity.Request.BarrierListCarRequest;
import com.maxcar.stock.entity.Request.GetCarListByMarketIdAndTenantRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalResponse;
import com.maxcar.stock.entity.Response.BarrierCarListResponse;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.vo.CarVo;

import java.util.List;
import java.util.Map;


/**
 * 库存车基础信息服务
 */
public interface CarService extends BaseService<Car,String>{
    /**
     * 新增
     * @param record
     * @return
     */
	int insertSelective(Car record);

    /**
     * 修改
     * @param record
     * @return
     */
	int updateByPrimaryKeySelective(Car record);

    int updateByVin(Car record);

    /**
     * 基础信息查询
     * @param record
     * @return
     */
    List <Car> listCar(Car record);

    List<CarVo> exportCarVo(CarVo carVo);

    PageInfo listCarVo(CarVo carVo) ;
    int saveCarVO(CarVo carVo);

    Integer selectCountsByCar(Car car);
    int updateCarVO(CarVo carVo);
    /**
     * 
     * @return
     */
    double getSumMarketPrice(CarVo carVo);
    /**
     * 获取车辆简单信息信息
     * 用于道闸等快速查询
     * 仅支持  vin，id，rfid 查询
     * @param car
     * @return
     */
    Car getCar(Car car);

    CarVo getCarVoById(String carId);

    Car getCarDetails(Car car);

    PageInfo<CarVo> listCarChannelVo(CarVo carVo);


    /**
     * 市场商品车总数
     * shenzhongzong
     *
     * @return
     *
     */
    int countCarNum(String marketId,String tenantId,String areaName);
    /**
     * 商户总库存数
     * shenzhongzong
     *
     * @return
     *
     */
    int countRepertoryByTenantId(String id, String marketId);



    //根据rfid查询车辆信息
    Car selectByRfid(String rfid);
    Car selectByVin(String vin);


    InterfaceResult addCar(CarParams paramsJson) throws Exception;


    /**
     * 删除车辆信息
     * @param id
     */
    void deleteCarById(String id);


    //修改车辆状态
    Integer updateCarStatus(Car car);

    /**
     * param:
     * describe: 获取库存统计信息
     * create_date:  lxy   2018/9/12  11:51
     **/
    InventoryStatisticalResponse inventoryStatistical(InventoryStatisticalRequest request);

    /**
     * 获取车牌信息
     * @return
     */
    List<TaoBaoCar> getBrand();

    List<TaoBaoCar> getTaoBaoCarByKey(TaoBaoCar taoBaoCar);

    CarInfo getCarInfoById(String carId);

    List<CarPic> getCarPic(String id);

    PageInfo getCarList(CarVo carVo);

    void modifyCarInfo(CarInfo carInfo);


    InterfaceResult uploadWb(CarInfo carInfo,List<CarPic> picList,String appid,String appSecret);

    /**
     * 南通大屏
     * @param car
     * @return
     */
    Result getAll(DpCar car);

    List<CarIcon> listCarIcon();

    Map<String,Object> getCarDetail(String id);
    /**
     * 商户APP找车详情
     * @param Id
     * @return
     */
    Map<String,Object> findingCarDetails(String Id);

    /**
     * param:
     * describe: 根据市场ID和商户ID 查询 库存车 可过户 列表
     * create_date:  lxy   2018/10/15  13:56
     **/
    InterfaceResult  getCarListByMarketIdAndTenant(GetCarListByMarketIdAndTenantRequest request);

    /**
     * 根据商户和品牌型号查询车辆信息
     * @param request
     * @return
     */
    PageInfo selectCarByTenant(BarrierListCarRequest request) throws Exception;
}
