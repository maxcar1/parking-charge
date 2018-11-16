package com.maxcar.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	 * 判断两个日期的月数差
	 * @param d1 结束日期
	 * @param d2 开始日期
	 * @return
	 */
	public static int getMonthDiff(Date d1, Date d2) {  
        Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        c1.setTime(d1);  
        c2.setTime(d2);  
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;  
        int year1 = c1.get(Calendar.YEAR);  
        int year2 = c2.get(Calendar.YEAR);  
        int month1 = c1.get(Calendar.MONTH);  
        int month2 = c2.get(Calendar.MONTH);  
        int day1 = c1.get(Calendar.DAY_OF_MONTH);  
        int day2 = c2.get(Calendar.DAY_OF_MONTH);  
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30  
        int yearInterval = year1 - year2;  
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数  
        if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;  
        // 获取月数差值  
        int monthInterval =  (month1 + 12) - month2  ;  
        if(day1 < day2) monthInterval --;  
        monthInterval %= 12;  
        return yearInterval * 12 + monthInterval;  
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
	public static String getStringForDateByFormat(Date date,String format){
		return new SimpleDateFormat(format).format(date);
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
		}else if(dateStr.length() == 5){
			return new SimpleDateFormat("HH:mm").parse(dateStr);
		}else if (dateStr.length() == 7){
			return new SimpleDateFormat("yyyy-MM").parse(dateStr);
		}
		else{
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		}
	}
	
	
	/**
	  * 判断是否为月末
	  * true 是月末
	  * false 不是
	  * @param date
	  * @return
	  */
	 public static boolean isLastDayOfMonth(Date date) { 
	        Calendar calendar = Calendar.getInstance(); 
	        calendar.setTime(date); 
	        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); 
	        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
	            return true; 
	        } 
	        return false; 
	    } 
	 /**
	  * 指定时间之前或者之后多少毫秒的时间
	  * @param date
	  * @param milliSecond
	  * @return
	  */
	 public static Date addDate(Date date ,int milliSecond) {
		 Date afterDate = new Date(date.getTime() + milliSecond);
		 return afterDate;
	 }
}
