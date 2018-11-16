package com.maxcar.base.util.dasouche;


public class TimeStampUtils {
           
	/**
	 * 生成时间戳取后九位
	 * @return
	 */
	public static String getTimeId(){
		long timeMillis = System.currentTimeMillis();
		String times = String.valueOf(timeMillis);
		String timesId = times.substring(4, 13);
		return timesId;
	}
	/**
	 * 获取9位秒级时间戳
	 * @return
	 */
	public static String getTimeM(){
		long timeMillis = System.currentTimeMillis();
		String times = String.valueOf(timeMillis);
		String timesId = times.substring(0, 10);
		return timesId;
	}
	/**
	 * 生成时间戳取后三位
	 * @return
	 */
	public static String getTimeId2(){
		long timeMillis = System.currentTimeMillis();
		String times = String.valueOf(timeMillis);
		String timesId = times.substring(10, 13);
		return timesId;
	}
	
}
