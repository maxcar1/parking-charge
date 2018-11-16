package com.maxcar.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UuidUtils {
	public static String generateIdentifier(){
		 //String uuid = UUID.randomUUID().toString(); 
	     //return uuid ;
		 DateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 String uuid = format.format(new Date().getTime())+new Double(Math.random()*100000).intValue();
		 while(uuid.length()<22){
			 uuid = uuid + "0";
		 }
		 uuid=uuid.substring(2);
		 return uuid ;
	 }

	/**
	 * 产生num位的随机数
	 * @return
	 */
	public static String getRandByNum(int num){
		String length = "1";
		for(int i=0;i<num;i++){
			length += "0";
		}

		Random rad=new Random();

		String result  = rad.nextInt(Integer.parseInt(length)) +"";

		if(result.length()!=num){
			return getRandByNum(num);
		}
		return result;
	}

	public static String gettimeFormart(){
		 //String uuid = UUID.randomUUID().toString(); 
	     //return uuid ;
		 DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		 String uuid = format.format(new Date().getTime());
		 uuid=uuid.substring(2);
		 System.out.println(uuid);
		 return uuid ;
	 }
	public static String gettimeFormartyyyyMMddHHmmss(){
		 //String uuid = UUID.randomUUID().toString(); 
	     //return uuid ;
		 DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		 String uuid = format.format(new Date().getTime());
		 return uuid ;
	 }
	public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
   }

	public static void main(String[] args) {
		gettimeFormart();
		gettimeFormartyyyyMMddHHmmss();
		System.out.println(getRandByNum(5));
	}
}
