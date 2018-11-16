package com.maxcar.base.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * yangshujun
 * 获取本电脑的mac地址
 * 如果连接的无线，就是无线网的mac
 * 如果连的是网线，就是以太网的mac
 */
public class LocalMacUtil {
    public static void main(String[] args) throws UnknownHostException, SocketException {

        //得到IP，输出PC-201309011313/122.206.73.83
        getLocalMac();
    }
    public static String getLocalMac() throws SocketException,UnknownHostException {
        //获取网卡，获取地址
        InetAddress ia = InetAddress.getLocalHost();
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<mac.length; i++) {
            if(i!=0) {
                sb.append(":");
            }
            //字节转换为整数
            int temp = mac[i]&0xff;
            String str = Integer.toHexString(temp);
            if(str.length()==1) {
                sb.append("0"+str);
            }else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }
}
