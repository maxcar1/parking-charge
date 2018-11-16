package com.maxcar.hikvision.model;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

/**
 * 海康威视核心sdk
 * win64下的版本
 * yangshujun
 */
public interface HCNetSDK extends StdCallLibrary {
    HCNetSDK INSTANCE = (HCNetSDK) Native.loadLibrary("C:\\Users\\Administrator\\Desktop\\123\\hkws\\HCNetSDK",
            HCNetSDK.class);

    public static final int SERIALNO_LEN = 48;   //序列号长度
    public static final int MAX_DISKNUM_V30 = 33;		//9000设备最大硬盘数/* 最多33个硬盘(包括16个内置SATA硬盘、1个eSATA硬盘和16个NFS盘) */
    public static final int MAX_ANALOG_CHANNUM = 32;    //最大32个模拟通道
    public static final int MAX_IP_CHANNEL = 32;   //允许加入的最多IP通道数
    public static final int MAX_CHANNUM_V30 = (MAX_ANALOG_CHANNUM + MAX_IP_CHANNEL);//64

    public static final int MAX_ANALOG_ALARMIN = 32;    //最大32路模拟报警输入
    public static final int MAX_IP_ALARMIN = 128;   //允许加入的最多报警输入数
    public static final int MAX_ALARMIN_V30 = (MAX_ANALOG_ALARMIN + MAX_IP_ALARMIN);//160

    public static final int MAX_AUDIO_V30 = 2;   //9000语音对讲通道数
    public static final int MAX_ANALOG_ALARMOUT = 32;    //最大32路模拟报警输出
    public static final int MAX_IP_ALARMOUT = 64; //允许加入的最多报警输出数
    public static final int MAX_ALARMOUT_V30 = (MAX_ANALOG_ALARMOUT + MAX_IP_ALARMOUT);//96

    public static final int MAX_LINK = 6;    //8000设备单通道最大视频流连接数

    boolean  NET_DVR_Init();

    //设备登录
    NativeLong NET_DVR_Login_V30(String sDVRIP, short wDVRPort, String sUserName, String sPassword, NET_DVR_DEVICEINFO_V30 lpDeviceInfo);

    boolean  NET_DVR_CaptureJPEGPicture(NativeLong lUserID, NativeLong lChannel, NET_DVR_JPEGPARA lpJpegPara, String sPicFileName);

    //JPEG抓图到内存
    boolean  NET_DVR_CaptureJPEGPicture_NEW(NativeLong lUserID, NativeLong lChannel, NET_DVR_JPEGPARA lpJpegPara, String sJpegPicBuffer, int dwPicSize, IntByReference lpSizeReturned);

    int  NET_DVR_GetLastError();

    boolean  NET_DVR_Logout(NativeLong lUserID);

    boolean  NET_DVR_Cleanup();

    boolean  NET_DVR_GetDVRWorkState_V30(NativeLong lUserID, NET_DVR_WORKSTATE_V30 lpWorkState);
    //NET_DVR_Login_V30()参数结构
    public static class NET_DVR_DEVICEINFO_V30 extends Structure {
        public  byte[] sSerialNumber = new byte[SERIALNO_LEN];  //序列号
        public  byte byAlarmInPortNum;		        //报警输入个数
        public  byte byAlarmOutPortNum;		        //报警输出个数
        public  byte byDiskNum;				    //硬盘个数
        public  byte byDVRType;				    //设备类型, 1:DVR 2:ATM DVR 3:DVS ......
        public  byte byChanNum;				    //模拟通道个数
        public  byte byStartChan;			        //起始通道号,例如DVS-1,DVR - 1
        public  byte byAudioChanNum;                //语音通道数
        public  byte byIPChanNum;					//最大数字通道个数
        public  byte[] byRes1 = new byte[24];					//保留
    }

    //图片质量
    public static class NET_DVR_JPEGPARA extends Structure {
        /*注意：当图像压缩分辨率为VGA时，支持0=CIF, 1=QCIF, 2=D1抓图，
        当分辨率为3=UXGA(1600x1200), 4=SVGA(800x600), 5=HD720p(1280x720),6=VGA,7=XVGA, 8=HD900p
        仅支持当前分辨率的抓图*/
        public short	wPicSize;				/* 0=CIF, 1=QCIF, 2=D1 3=UXGA(1600x1200), 4=SVGA(800x600), 5=HD720p(1280x720),6=VGA*/
        public short	wPicQuality;			/* 图片质量系数 0-最好 1-较好 2-一般 */
    }

    public static class NET_DVR_WORKSTATE_V30 extends Structure {//DVR工作状态(9000扩展)
        public int dwDeviceStatic; 	//设备的状态,0-正常,1-CPU占用率太高,超过85%,2-硬件错误,例如串口死掉
        public NET_DVR_DISKSTATE[]  struHardDiskStatic = new NET_DVR_DISKSTATE[MAX_DISKNUM_V30];
        public NET_DVR_CHANNELSTATE_V30[] struChanStatic = new NET_DVR_CHANNELSTATE_V30[MAX_CHANNUM_V30];//通道的状态
        public byte[]  byAlarmInStatic = new byte[MAX_ALARMIN_V30]; //报警端口的状态,0-没有报警,1-有报警
        public byte[]  byAlarmOutStatic = new byte[MAX_ALARMOUT_V30]; //报警输出端口的状态,0-没有输出,1-有报警输出
        public int  dwLocalDisplay;//本地显示状态,0-正常,1-不正常
        public  byte [] byAudioChanStatus = new byte[MAX_AUDIO_V30];//表示语音通道的状态 0-未使用，1-使用中, 0xff无效
        public  byte[]  byRes = new byte[10];
    }

    public static class NET_DVR_DISKSTATE extends Structure {//硬盘状态
        public int dwVolume;//硬盘的容量
        public int dwFreeSpace;//硬盘的剩余空间
        public int dwHardDiskStatic; //硬盘的状态,按位:1-休眠,2-不正常,3-休眠硬盘出错
    }


    public static class NET_DVR_CHANNELSTATE_V30 extends Structure {//通道状态(9000扩展)
        public byte byRecordStatic; //通道是否在录像,0-不录像,1-录像
        public byte bySignalStatic; //连接的信号状态,0-正常,1-信号丢失
        public byte byHardwareStatic;//通道硬件状态,0-正常,1-异常,例如DSP死掉
        public byte reservedData;		//保留
        public int dwBitRate;//实际码率
        public int dwLinkNum;//客户端连接的个数
        public NET_DVR_IPADDR[] struClientIP = new NET_DVR_IPADDR[MAX_LINK];//客户端的IP地址
        public  int dwIPLinkNum;//如果该通道为IP接入，那么表示IP接入当前的连接数
        public  byte[] byRes = new byte[12];
    }

    public static class NET_DVR_IPADDR extends Structure {
        public byte[] sIpV4 = new byte[16];
        public byte[] byRes = new byte[128];

        public String toString() {
            return "NET_DVR_IPADDR.sIpV4: " + new String(sIpV4) + "\n" + "NET_DVR_IPADDR.byRes: " + new String(byRes) + "\n";
        }
    }

}
