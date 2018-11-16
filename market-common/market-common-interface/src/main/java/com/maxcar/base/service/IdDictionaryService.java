package com.maxcar.base.service;

/**
 * 生成主键id
* @ClassName: IdDictionaryService 
* @author huangxu 
* @date 2018年1月14日 上午11:32:32 
*
 */
public interface IdDictionaryService {
	String getNewId(String tableName, String marketId);
}
