package com.maxcar.base.util;

import java.util.List;
/**
 * 集合处理公共类
* @ClassName: CollectionUtil 
* @author huangxu 
* @date 2017年11月17日 上午10:54:16 
*
 */
public class CollectionUtil {
	/**
	 * 判断list集合是否为空
	 * return true 不为空
	 * @param list
	 * @return
	 */
	public static <T> boolean listIsNotEmpty(List<T> list) {
		if(list != null && list.size() >0) {
			return true;
		}
		return false;
	}
	
}
