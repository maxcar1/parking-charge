package com.maxcar.util;

import java.io.UnsupportedEncodingException;

/**
 * 汉字转十六进制
 * Created by 池彦龙 on 2018/8/8.
 */
public class HexUtils {

    private static String hexString = "0123456789ABCDEF";

    public static String encodeCN(String data) {
        byte[] bytes;
        try {
            bytes = data.getBytes("gbk");
            StringBuilder sb = new StringBuilder(bytes.length * 2);

            for (int i = 0; i < bytes.length; i++) {
                sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
                sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encodeStr(String data) {
        String result = "";
        byte[] bytes;
        try {
            bytes = data.getBytes("gbk");
            for (int i = 0; i < bytes.length; i++) {
                result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判定是否为中文汉字
     *
     * @param data
     * @return
     */
    public static boolean isCN(String data) {
        boolean flag = false;
        String regex = "^[\u4e00-\u9fa5]*$";
        if (data.matches(regex)) {
            flag = true;
        }
        return flag;
    }

    public static String getHexResult(String targetStr) {
        StringBuilder hexStr = new StringBuilder();
        int len = targetStr.length();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                char tempStr = targetStr.charAt(i);
                String data = String.valueOf(tempStr);
                if (isCN(data)) {
                    hexStr.append(encodeCN(data));
                } else {
                    hexStr.append(encodeStr(data));
                }
            }
        }
        return hexStr.toString();
    }

    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for( int i=0; i<hex.length()-1; i+=2 ){

            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    public static String convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString(chars[i]));
        }

        return hex.toString();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(convertStringToHex("R9MC \"\u0001\u0002\f\u0005��748YMC\u0003R9[�4� \u0001 \u0001�� \u0001\u0002|l"));
    }
}
