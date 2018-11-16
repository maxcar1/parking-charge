package com.maxcar.stock.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.stock.pojo.CarPic;
import com.maxcar.stock.pojo.CarPicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarPicMapper extends BaseDao<CarPic,String> {
    int countByExample(CarPicExample example);

    int deleteByExample(CarPicExample example);

    int deleteByCarId(String carId);

    int deleteByPrimaryKey(String id);
    int insert(CarPic record);

    int insertSelective(CarPic record);
    int insertCarPicList(@Param("listCarPic") List<CarPic> listCarPic);
    
    List<CarPic> selectByExample(CarPicExample example);
    List<CarPic> listCarPic(CarPic record);

    CarPic selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CarPic record, @Param("example") CarPicExample example);

    int updateByExample(@Param("record") CarPic record, @Param("example") CarPicExample example);

    int updateByPrimaryKeySelective(CarPic record);

    int updateByPrimaryKey(CarPic record);

    CarPic selectByCarIdType(@Param("carId") String carId, @Param("type") int type);
}