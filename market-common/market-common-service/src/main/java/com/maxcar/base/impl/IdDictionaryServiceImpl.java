package com.maxcar.base.impl;
import com.maxcar.base.dao.IdDictionaryMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxcar.base.pojo.IdDictionary;
import com.maxcar.base.service.IdDictionaryService;
@Service("idDictionaryService")
public class IdDictionaryServiceImpl implements IdDictionaryService {
	@Autowired
	private IdDictionaryMapper idDictionaryMapper;

	@Override
	public String getNewId(String tableName, String marketId) {
			 IdDictionary dictionary = new IdDictionary();
			 String id = null;
			 try {
				 dictionary.setMarketId(marketId);
				 dictionary.setTableName(tableName);
				 Integer version = idDictionaryMapper.getId(dictionary);
				 if(null == version || version.intValue() == 0){
					    dictionary.setId(1);
						dictionary.setPkid(marketId+1);
						dictionary.setVersion(1);
//						dao.save("com.maxcar.base.mapper.IdDictionaryMapper.add", dictionary);
					 idDictionaryMapper.add(dictionary);
					}else{
						dictionary.setPkid(marketId+(version+1));
//						dao.update("com.maxcar.base.mapper.IdDictionaryMapper.updatePkid", dictionary);
					 	idDictionaryMapper.updatePkid(dictionary);
					}
				  id = dictionary.getPkid();
			 } catch (Exception e) {
				Logger.getRootLogger().error(e, e);
			}
			 return id;
	}
}
