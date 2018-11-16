package com.maxcar.hikvision.service;

import com.maxcar.barrier.pojo.BarrierCamera;
import com.maxcar.base.util.MD5Util;
import com.maxcar.hikvision.model.DeviceInfo;
import com.maxcar.hikvision.model.HCNetSDKLinux;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * linux64环境
 * yangshujun
 */
public class HikvisionLinuxService {

    final static Logger logger = LoggerFactory.getLogger(HikvisionLinuxService.class);

    //linux版本
    static HCNetSDKLinux hCNetSDK = HCNetSDKLinux.INSTANCE;
    static HCNetSDKLinux.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    static HCNetSDKLinux.NET_DVR_WORKSTATE_V30 m_strWorkstate; //DVR工作状态
    static HCNetSDKLinux.NET_DVR_JPEGPARA m_strJpeg;
    static NativeLong lUserID;//用户句柄
    static HashMap<String, DeviceInfo> m_hashDeviceInfo = new HashMap<String, DeviceInfo>();; // login info
    static HCNetSDKLinux.NET_DVR_USER_LOGIN_INFO struLoginInfo ;
    static HCNetSDKLinux.NET_DVR_DEVICEINFO_V40 struDeviceInfo;

    private static NativeLong LoginDevice(DeviceInfo deviceInfo){
        HCNetSDKLinux.NET_DVR_USER_LOGIN_INFO struLoginInfo = new HCNetSDKLinux.NET_DVR_USER_LOGIN_INFO();
        HCNetSDKLinux.NET_DVR_DEVICEINFO_V40 struDeviceInfo = new HCNetSDKLinux.NET_DVR_DEVICEINFO_V40();
        for (int i = 0; i < deviceInfo.getIP().length(); i++)
        {
            struLoginInfo.sDeviceAddress[i] = (byte) deviceInfo.getIP().charAt(i);
        }
        for (int i = 0; i < deviceInfo.getPassword().length(); i++)
        {
            struLoginInfo.sPassword[i] = (byte) deviceInfo.getPassword().charAt(i);
        }
        for (int i = 0; i < deviceInfo.getUserName().length(); i++)
        {
            struLoginInfo.sUserName[i] = (byte) deviceInfo.getUserName().charAt(i);
        }
        struLoginInfo.wPort = deviceInfo.getPort();
        struLoginInfo.write();
        return hCNetSDK.NET_DVR_Login_V40(struLoginInfo.getPointer(), struDeviceInfo.getPointer());
    }

    private static DeviceInfo cacheV4LoginInfo(BarrierCamera barrierCamera){
        //缓存设备登录信息
        String sDVRIP = barrierCamera.getDeviceIp();
        int iPort = Integer.valueOf(barrierCamera.getDevicePort());
        String userName = barrierCamera.getUserName();
        String password = barrierCamera.getPassword();
        String marketId = barrierCamera.getMarketId();
        //加密key ip+marketId md5加密组装
        String key = MD5Util.MD5(sDVRIP+marketId);
        DeviceInfo deviceInfo = null;
        if (m_hashDeviceInfo.containsKey(key)){
            //存在此key直接取登录信息
            deviceInfo = m_hashDeviceInfo.get(key);
            m_hashDeviceInfo.replace(key, deviceInfo);
        }else{
            struLoginInfo = new HCNetSDKLinux.NET_DVR_USER_LOGIN_INFO();
            struDeviceInfo = new HCNetSDKLinux.NET_DVR_DEVICEINFO_V40();
            Pointer PointerstruDeviceInfoV40 = struDeviceInfo.getPointer();
            Pointer PointerstruLoginInfo = struLoginInfo.getPointer();
            NativeLong nlUserID = hCNetSDK.NET_DVR_Login_V40(PointerstruLoginInfo, PointerstruDeviceInfoV40);
            logger.info("第一次初始化登录==>{}",nlUserID.longValue());
            deviceInfo = new DeviceInfo(sDVRIP, (short)iPort, userName,
                    password, struDeviceInfo.struDeviceV30, nlUserID);
            m_hashDeviceInfo.put(key, deviceInfo);
        }
        return deviceInfo;
    }

    public static Map requestDll(BarrierCamera barrierCamera) {
        Map map = new HashMap(2);
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (initSuc) {
            //参考海康威视官网SDK，模拟登录网络摄像机
            //    m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
            String path = barrierCamera.getPath();
            /*DeviceInfo deviceInfo = cacheV4LoginInfo(barrierCamera);
            long userID = deviceInfo.getNUserID().longValue();*/
            String sDVRIP = barrierCamera.getDeviceIp();
            int iPort = Integer.valueOf(barrierCamera.getDevicePort());
            String userName = barrierCamera.getUserName();
            String password = barrierCamera.getPassword();
            NativeLong nlUserID = LoginDevice(new DeviceInfo(sDVRIP, (short)iPort, userName,
                    password, null, null));

            logger.info("===摄像机登录的userId为==>{}",nlUserID);
            int userID = nlUserID.intValue();
            logger.info("摄像机登录的userId为==>{}",userID);
            try {
                //-1表示失败，其他值表示返回的用户ID值。返回UserID的取值范围为0~2047。
                if (userID == -1) {
                    //设备登录失败，数据上行请求，下行通知硬件并生成日志记录
                    logger.info("设备登录失败====== 市场==>{},设备ip===>{}",
                            barrierCamera.getMarketId(),barrierCamera.getDeviceIp());
                } else {
                    map = getImage(path);
                }
            }catch (Exception e){
                logger.error(e.toString());
            } finally{
                hCNetSDK.NET_DVR_Logout(lUserID);
                hCNetSDK.NET_DVR_Cleanup();
            }
        }
        return map;
    }

    public static Map getImage(String path){
        Map map = new HashMap(2);
        boolean flag = false;
        //DVR工作状态
        //    m_strWorkstate = new HCNetSDK.NET_DVR_WORKSTATE_V30();
        m_strWorkstate = new HCNetSDKLinux.NET_DVR_WORKSTATE_V30();

        if (hCNetSDK.NET_DVR_GetDVRWorkState_V30(lUserID, m_strWorkstate)) {
            long startTime = System.currentTimeMillis();
            //    m_strJpeg = new HCNetSDK.NET_DVR_JPEGPARA();
            m_strJpeg = new HCNetSDKLinux.NET_DVR_JPEGPARA();
            // 设置图片的分辨率
            m_strJpeg.wPicSize = 2;
            // 设置图片质量
            m_strJpeg.wPicQuality = 2;

            // 创建文件目录和文件
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            //按照每天设置一个目录
            StringBuilder sb = new StringBuilder();
            sb.append(path);
            sb.append(File.separator);
            sb.append(sdf1.format(date));
            File files = new File(sb.toString());
            if (!files.exists()) {
                files.mkdirs();
            }
            sb.append(File.separator);
            sb.append(sdf.format(date));
            sb.append(".jpg");
            // 抓图到内存，单帧数据捕获并保存成JPEG存放在指定的内存空间中
            //需要加入通道
            boolean is = hCNetSDK.NET_DVR_CaptureJPEGPicture(lUserID, new NativeLong(1), m_strJpeg,
                    sb.toString());
            if (is) {
                flag = true;
                map.put("imageName",sb.toString());
                logger.info("存储本地耗时：[{}ms]",System.currentTimeMillis() - startTime);
            } else {
                logger.info("抓取失败,错误代码：{}" , hCNetSDK.NET_DVR_GetLastError());
            }

        } else {
            // 返回Boolean值，判断是否获取设备能力
            logger.info("返回设备状态失败，错误代码==>{}",hCNetSDK.NET_DVR_GetLastError());
        }
        map.put("result",flag);
        return map;
    }


}
