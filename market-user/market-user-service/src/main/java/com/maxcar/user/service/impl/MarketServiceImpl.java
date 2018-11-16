package com.maxcar.user.service.impl;

import java.util.List;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.City;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.CityService;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.user.dao.MarketMapper;
import com.maxcar.user.entity.MarketExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcar.user.entity.Market;
import com.maxcar.user.service.MarketService;


@Service("marketService")
public class MarketServiceImpl extends BaseServiceImpl<Market,String> implements MarketService {
    

	@Autowired
	private MarketMapper marketMapper;
	@Autowired
	private CityService cityService;

	@Override
	public BaseDao<Market, String> getBaseMapper() {
		return marketMapper;
	}
	
	@Override
	public Market getMarket(String id) {
		Market market = new Market();
		try {
			market =marketMapper.getMarket(id);
		} catch (Exception e) {
			Logger.getRootLogger().error(e,e);
		}
		return market;
	}
	


	@Override
	public List<Market> searchMarket(Market market)throws Exception{
		MarketExample example = new MarketExample();
		MarketExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(market.getName())){
			market.setName("%" + market.getName() + "%");
			criteria.andNameLike(market.getName());
		}
		if(StringUtils.isNotBlank(market.getCity())){
			criteria.andCityEqualTo(market.getCity());
		}
		example.setOrderByClause(" register_time desc");
		List<Market> marketList=marketMapper.selectByExample(example);
		
		for (int i=0;i<marketList.size();i++){
			City city=cityService.getCityById(marketList.get(i).getCity());
			if(city != null){
				marketList.get(i).setCityName(city.getName());
			}			
		}
		return marketList;	
	}

	@Override
	public InterfaceResult saveOrUpdateMarket(Market market) throws Exception{
		InterfaceResult interfaceResult = new InterfaceResult();
		int count = 0;
		String marketId = market.getId();
		//新增
		if(StringUtils.isBlank(marketId)){
			MarketExample example = new MarketExample();
			example.createCriteria().andNameEqualTo(market.getName()).andIsvalidEqualTo(1);
			List<Market> markets = marketMapper.selectByExample(example);	
			if(markets!=null && markets.size()>0){
				interfaceResult.InterfaceResult600("市场名已存在");
				return interfaceResult;
			}
			example = new MarketExample();
			example.createCriteria().andMarketNoEqualTo(market.getMarketNo()).andIsvalidEqualTo(1);
			markets = marketMapper.selectByExample(example);	
			if(markets!=null && markets.size()>0){
				interfaceResult.InterfaceResult600("市场编号已存在");
				return interfaceResult;
			}
			marketId = UuidUtils.generateIdentifier();
			market.setId(marketId);
			count = marketMapper.insertSelective(market);
		}else{//修改
			count = marketMapper.updateByPrimaryKeySelective(market);
		}
		return interfaceResult;
	}

	@Override
	public boolean updateMarketStatus(String ids,Integer status) throws Exception{
		int count = 0;
		for(String id:ids.split(",")) {
			if (status == -1) {
				count = marketMapper.deleteByPrimaryKey(id);
			} else {
				Market market = marketMapper.selectByPrimaryKey(id);
				if (market != null) {
					market.setIsvalid(status);
					count = marketMapper.updateByPrimaryKeySelective(market);
				}
			}
		}
		return count==1;
	}

	@Override
	public Market getMarketById(String id) {
		Market market = new Market();
		try {
			market =marketMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			Logger.getRootLogger().error(e,e);
		}
		return market;
	}
}