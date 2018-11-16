package com.maxcar.user.dao;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.user.entity.Organizations;
import com.maxcar.user.entity.OrganizationsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrganizationsMapper extends BaseDao<Organizations,String> {
    int countByExample(OrganizationsExample example);

    int deleteByExample(OrganizationsExample example);

    int deleteByPrimaryKey(String id);
    
    int updateByOrgId(String id);

    int insert(Organizations record);

    int insertSelective(Organizations record);

    List<Organizations> selectByExample(OrganizationsExample example);

    Organizations selectByPrimaryKey(String orgId);

    int updateByExampleSelective(@Param("record") Organizations record, @Param("example") OrganizationsExample example);

    int updateByExample(@Param("record") Organizations record, @Param("example") OrganizationsExample example);

    int updateByPrimaryKeySelective(Organizations record);

    int updateByPrimaryKey(String id);

    public List<Organizations> getOrganizationsByMarket(String marketId);

    public List<Organizations> getOrgByCodeAndMarketId(String marketId);
}
