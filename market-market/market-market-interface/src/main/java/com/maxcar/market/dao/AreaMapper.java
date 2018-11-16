package com.maxcar.market.dao;

import com.github.pagehelper.Page;
import com.maxcar.market.pojo.Area;
import com.maxcar.market.pojo.AreaExample;
import java.util.List;

import com.maxcar.market.vo.AreaVo;
import org.apache.ibatis.annotations.Param;

public interface AreaMapper {
	List<Area> selectAreaName(String marketId);
	
    int countByExample(AreaExample example);

    int deleteByExample(AreaExample example);

    int deleteByPrimaryKey(String id);

    int insert(Area record);

    int insertSelective(Area record);

    List<Area> selectByExample(AreaExample example);

    Area selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Area record, @Param("example") AreaExample example);

    int updateByExample(@Param("record") Area record, @Param("example") AreaExample example);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    List<AreaVo> selectArea(Area area);
    
    int countPark(@Param("marketId") String marketId);

    Double countTypeArea(@Param("marketId") String marketId);
}