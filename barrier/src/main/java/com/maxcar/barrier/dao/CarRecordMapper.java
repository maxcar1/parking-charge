package com.maxcar.barrier.dao;

import com.maxcar.barrier.pojo.CarRecord;
import org.springframework.stereotype.Component;

@Component
public interface CarRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(CarRecord record);

    int insertSelective(CarRecord record);

    CarRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CarRecord record);

    int updateByPrimaryKey(CarRecord record);
}