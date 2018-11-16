package com.maxcar.market.service;

import com.github.pagehelper.PageInfo;
import com.maxcar.market.pojo.Area;
import com.maxcar.market.vo.AreaVo;

import java.util.List;

public interface AreaService {
    int addArea(Area area);

    Area getAreaById(String areaId);

    PageInfo<AreaVo> listByArea(Area area);

    int updateArea(Area area);

    /**
     * 查询出所有的区域
     * shenzhongzong
     * @return
     * @param marketId
     */
    List<Area> selectAreaList(String marketId);

    List<Area> getAreaByType(int type, String marketId);

    Double countTypeArea(String marketId);
}
