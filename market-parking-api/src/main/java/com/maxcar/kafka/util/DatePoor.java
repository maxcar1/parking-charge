package com.maxcar.kafka.util;

import com.maxcar.base.util.UuidUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePoor {

	/**
	 * 判断两个时间之间的天数时间差
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static long getDatePoorDay(Date endDate, Date nowDate) {
	    long nd = 1000 * 24 * 60 * 60;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    return day;
	}
	/**
	 * 判断两个时间之间的小时时间差
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static long getDatePoorHour(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    return hour ;
	}
	/**
	 * 判断两个时间之间的分数时间差
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static long getDatePoorMin(Date endDate, Date nowDate) {
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    return min;
	}
	/**
	 * 判断两个时间相差多少天多小时多少分
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟";
	}
	/**
	 * 将日期格式 转换为 yyyy-MM-dd HH:mm:ss 的字符串
	 * 请选择合适的格式进行转换
	 * @param date
	 * @return
	 */
	public static String getStringForDate(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	public static String getStringForDateInt(Date date){
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(date);
		String uuid = UuidUtils.getUUID();
		return format + "@" +  uuid;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getStringForDateInt(new Date()));
		}
		
	}
	
	/**
	 * 将字符串日期格式 转换为 yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd Date日期格式
	 * 可能会报出 ParseException 请注意捕获
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateForString(String dateStr) throws ParseException{
		if(dateStr.length() > 10){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		}
		else{
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		}
	}
	
}
