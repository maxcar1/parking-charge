package com.maxcar.user.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.OutMarketManage;
import com.maxcar.user.entity.OutMarketManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutMarketManageMapper extends BaseDao<OutMarketManage,String> {
    int countByExample(OutMarketManageExample example);

    int deleteByExample(OutMarketManageExample example);

    int deleteByPrimaryKey(String id);

    int insert(OutMarketManage record);

    int insertSelective(OutMarketManage record);

    List<OutMarketManage> selectByExample(OutMarketManageExample example);

    OutMarketManage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OutMarketManage record, @Param("example") OutMarketManageExample example);

    int updateByExample(@Param("record") OutMarketManage record, @Param("example") OutMarketManageExample example);

    int updateByPrimaryKeySelective(OutMarketManage record);

    int updateByPrimaryKey(OutMarketManage record);

    // 根据出场类型获取出场时间配置
    List<OutMarketManage> selectByOutMarketType(String type);
}