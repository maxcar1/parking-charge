package com.maxcar.stock.dao;

import com.maxcar.stock.pojo.CarCheck;
import com.maxcar.stock.pojo.CarCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarCheckMapper {
    int countByExample(CarCheckExample example);

    int deleteByExample(CarCheckExample example);

    int deleteByPrimaryKey(String id);

    int insert(CarCheck record);

    int insertSelective(CarCheck record);

    List<CarCheck> selectByExampleWithBLOBs(CarCheckExample example);

    List<CarCheck> selectByExample(CarCheckExample example);

    CarCheck selectByPrimaryKey(String id);
    CarCheck getCarCheckByCarId(@Param("carId") String carId);

    int updateByExampleSelective(@Param("record") CarCheck record, @Param("example") CarCheckExample example);

    int updateByExampleWithBLOBs(@Param("record") CarCheck record, @Param("example") CarCheckExample example);

    int updateByExample(@Param("record") CarCheck record, @Param("example") CarCheckExample example);

    int updateByPrimaryKeySelective(CarCheck record);

    int updateByPrimaryKeyWithBLOBs(CarCheck record);

    int updateByPrimaryKey(CarCheck record);

    String getCarCheckByVin(String vin);

    int insertCarCheck(CarCheck check);
}