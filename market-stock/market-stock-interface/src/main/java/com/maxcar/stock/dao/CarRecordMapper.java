package com.maxcar.stock.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.stock.pojo.CarRecord;
import com.maxcar.stock.pojo.CarRecordExample;
import java.util.List;

import com.maxcar.stock.vo.CarOut;
import org.apache.ibatis.annotations.Param;

public interface CarRecordMapper extends BaseDao<CarRecord,String>{
    int countByExample(CarRecordExample example);

    int deleteByExample(CarRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(CarRecord record);

    int insertSelective(CarRecord record);

    List<CarRecord> selectByExample(CarRecordExample example);
    List<CarRecord> listCarRecord(CarRecord record);

    List<CarRecord> listCarRecordCounts(CarRecord record);

    CarRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CarRecord record, @Param("example") CarRecordExample example);

    int updateByExample(@Param("record") CarRecord record, @Param("example") CarRecordExample example);

    int updateByPrimaryKeySelective(CarRecord record);

    int updateByPrimaryKey(CarRecord record);

    List<CarRecord> getCarRecordList(CarRecord carRecord);

    CarOut getCarOutMarketDate(@Param("vin") String vin);
}