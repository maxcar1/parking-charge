package com.maxcar.base.util;


public class MD5Util {

	
	/**
	 * 调用了MessageDigest库函数，并把byte数组结果转换成16进制 大写
	 * @param md5
	 * @return
	 */
	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString().toUpperCase();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(MD5("zhangsan12345678"));
	}
	
	/**
	 * 调用了MessageDigest库函数，并把byte数组结果转换成16进制 小写
	 * @param md5
	 * @return
	 */
	public static String MD52(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString().toLowerCase();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}
