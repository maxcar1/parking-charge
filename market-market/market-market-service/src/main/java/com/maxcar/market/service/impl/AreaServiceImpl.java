package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.market.dao.AreaMapper;
import com.maxcar.market.pojo.Area;
import com.maxcar.market.pojo.AreaExample;
import com.maxcar.market.service.AreaService;
import com.maxcar.market.vo.AreaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:区域
 * @author: songxuefeng
 * @create: 2018-08-17 15:48
 **/
@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public int addArea(Area area) {
        return areaMapper.insertSelective(area);
    }

    @Override
    public Area getAreaById(String areaId) {
        return areaMapper.selectByPrimaryKey(areaId);
    }

    @Override
    public PageInfo<AreaVo> listByArea(Area area) {
        PageHelper.startPage(area.getCurrentPage(), area.getPageSize());
        List<AreaVo> areas=areaMapper.selectArea(area);
        PageInfo<AreaVo> pageInfo = new PageInfo<>(areas);
        return pageInfo;
    }

    @Override
    public int updateArea(Area area) {
        return areaMapper.updateByPrimaryKeySelective(area);
    }

   @Override
    public List<Area> selectAreaList(String marketId) {
        return areaMapper.selectAreaName(marketId);
    }

    @Override
    public List<Area> getAreaByType(int type, String marketId) {
        AreaExample areaExample=new AreaExample();
        areaExample.createCriteria()
                .andTypeEqualTo(type)
                .andMarketIdEqualTo(marketId)
                .andIsvalidEqualTo(1);
        return areaMapper.selectByExample(areaExample);
    }

    @Override
    public Double countTypeArea(String marketId) {
        return areaMapper.countTypeArea(marketId);
    }

}
