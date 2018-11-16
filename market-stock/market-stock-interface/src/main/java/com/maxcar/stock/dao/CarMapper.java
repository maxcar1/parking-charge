package com.maxcar.stock.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.stock.entity.Request.BarrierListCarRequest;
import com.maxcar.stock.entity.Request.GetCarListByMarketIdAndTenantRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalRequest;
import com.maxcar.stock.entity.Request.InventoryStatisticalResponse;
import com.maxcar.stock.entity.Response.BarrierCarListResponse;
import com.maxcar.stock.entity.Response.GetCarListByMarketIdAndTenantResponse;
import com.maxcar.stock.pojo.*;
import com.maxcar.stock.vo.CarVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarMapper extends BaseDao<Car, String> {

    Integer selectCountsByCar(Car car);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String id);

    CarVehicle selectCarByid(String id);

    int updateByPrimaryKeySelective(Car car);

    int updateByVin(Car car);

    List<CarVo> listCarVo(CarVo carVo);

    double getSumMarketPrice(CarVo carVo);

    Car getCar(Car record);

    CarVo getCarVoById(String id);

    List<Car> getCarList(Map<String, Object> car);

    List<Car> getCarListByStaffId(List<String> staffIds);

    List<CarVo> listCarChannelVo(CarVo carVo);

    /**
     * 市场商品车总数
     * shenzhongzong
     *
     * @return
     */
    int countCarNum(@Param("marketId") String marketId);

    /**
     * 商户商品车总数
     * shenzhongzong
     *
     * @return
     */
    int countRepertoryByTenantId(@Param("id") String id, @Param("marketId") String marketId);


    List<Car> selectByExample(CarExample example);

    /**
     * param:
     * describe: 获取库存统计信息
     * create_date:  lxy   2018/9/12  11:51
     **/
    InventoryStatisticalResponse inventoryStatistical(InventoryStatisticalRequest request);

    public Car getCarDetails(Car car);

    List<TaoBaoCar> getTaoBaoBrand();

    List<TaoBaoCar> getTaoBaoCarByKey(TaoBaoCar taoBaoCar);

    CarInfo getCarInfoById(String carId);

    List<CarPic> getCarPic(String id);

    void modifyCarInfo(CarInfo carInfo);

    List<Car> getCarListByTenant(List<String> tenants);


    int countByExample(CarExample example);

    List<CarVo> selectCarList(CarVo carVo);

    /**
     * param:
     * describe: 根据市场ID和商户ID 查询 库存车 可过户 列表
     * create_date:  lxy   2018/10/15  13:56 
     **/
   List<GetCarListByMarketIdAndTenantResponse> getCarListByMarketIdAndTenant(GetCarListByMarketIdAndTenantRequest request);

   List<DpCar> getNtCar(DpCar car);

   List<CarIcon> listCarIcon();

   List<Car> getAllMarketCarByStatus(Map map);

   int deleteByVinAndRfid(CarRecord c);

    /**
     * 道闸黑白名单配置时添加车辆信息时返回的数据
     * @param request
     * @return
     */
   List<BarrierCarListResponse> selectCarByTenant(BarrierListCarRequest request);
}