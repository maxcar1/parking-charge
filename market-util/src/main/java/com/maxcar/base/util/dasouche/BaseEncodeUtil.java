package com.maxcar.base.util.dasouche;

import com.maxcar.base.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Base64;

public class BaseEncodeUtil {
            
	 /**
	  * Base64加密
	  * @param input
	  * @return
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 public static String encodeBase64(byte[] input) throws Exception{  
	     Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
	     Method mainMethod= clazz.getMethod("encode", byte[].class);  
	     mainMethod.setAccessible(true);  
	     Object retObj=mainMethod.invoke(null, new Object[]{input});  
	     return (String)retObj;  
	 }  
	 

		public static String encodeBase64ToString(String source) {
		     if (StringUtils.isEmpty(source)) {
		         return null;
	         }
	        Base64.Encoder encoder = Base64.getEncoder();
	        try {
	            String result = encoder.encodeToString(source.getBytes("UTF-8"));
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

		public static byte[] encodeBase64(String source) {
		     if (StringUtils.isEmpty(source)) {
		         return null;
	         }
	        Base64.Encoder encoder = Base64.getEncoder();
	        try {
	            byte[] result = encoder.encode(source.getBytes("UTF-8"));
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

		public static String decodeBase64(String source) {
		     if (StringUtils.isEmpty(source)) {
		         return null;
	         }
	        Base64.Decoder decoder = Base64.getDecoder();
	        try {
	            byte[] bytes = decoder.decode(source.getBytes("UTF-8"));
	            String result = new String(bytes, "UTF-8");
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

		public static String decodeBase64(byte[] bytes) {
		     if (bytes == null || bytes.length == 0) {
		         return null;
	         }
	        Base64.Decoder decoder = Base64.getDecoder();
	        try {
	            byte[] arr = decoder.decode(bytes);
	            String result = new String(arr, "UTF-8");
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	 public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		   String abc = "123";
		   String encodeBase64 = BaseEncodeUtil.encodeBase64(abc.getBytes("UTF-8"));
		   System.out.println(encodeBase64);
		   
	 }
	 
	 
	 
}  
