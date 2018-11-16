package com.maxcar.hikvision.service;

import com.maxcar.barrier.pojo.BarrierCamera;
import com.maxcar.hikvision.model.HCNetSDK;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * win64环境
 * yangshujun
 */
public class HikvisionService {

    final static Logger logger = LoggerFactory.getLogger(HikvisionService.class);

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    //   static PlayCtrl playControl = PlayCtrl.INSTANCE;

    HCNetSDK.NET_DVR_JPEGPARA m_jpegpara;
    //用户句柄
    static NativeLong lUserID;
    //设备信息
    static HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;
    //DVR工作状态
    static  HCNetSDK.NET_DVR_WORKSTATE_V30 m_strWorkstate;
    static HCNetSDK.NET_DVR_JPEGPARA m_strJpeg;

    /*static final String m_sDeviceIP = "192.168.3.64";
    static final short iPort = 8000;
    static final String UserName = "admin";
    static final String Password = "maxcar2018";*/

    public static Map requestDll(BarrierCamera barrierCamera) {
        Map map = new HashMap(2);
        boolean flag = false;
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (initSuc) {
            String m_sDeviceIP = barrierCamera.getDeviceIp();
            Short iPort = Short.valueOf(barrierCamera.getDevicePort());
            String UserName = barrierCamera.getUserName();
            String Password = barrierCamera.getPassword();
            //参考海康威视官网SDK，模拟登录网络摄像机
            m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
            lUserID = hCNetSDK.NET_DVR_Login_V30(m_sDeviceIP,
                    iPort, UserName, new String(Password), m_strDeviceInfo);
            long userID = lUserID.longValue();
            System.out.println(userID);
            //-1表示失败，其他值表示返回的用户ID值。返回UserID的取值范围为0~2047。
            if (userID == -1) {
                //设备登录失败，数据上行请求，下行通知硬件并生成日志记录
                logger.info("设备登录失败====== 市场==>{},设备ip===>{}",
                        barrierCamera.getMarketId(),barrierCamera.getDeviceIp());
            } else {
                //DVR工作状态
                m_strWorkstate = new HCNetSDK.NET_DVR_WORKSTATE_V30();
                if (hCNetSDK.NET_DVR_GetDVRWorkState_V30(lUserID, m_strWorkstate)) {
                    // 获取IP接入配置参数
                    IntByReference intByReference = new IntByReference();
                    long startTime = System.currentTimeMillis();
                    m_strJpeg = new HCNetSDK.NET_DVR_JPEGPARA();
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
                    sb.append(barrierCamera.getPath());
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
                    boolean is = hCNetSDK.NET_DVR_CaptureJPEGPicture(lUserID, new NativeLong(1), m_strJpeg,
                            sb.toString());
                    if (is) {
                        flag = true;
                        map.put("imageName",sb.toString());
                        logger.info("抓取成功,存储本地耗时：[{}ms]", System.currentTimeMillis() - startTime);
                    } else {
                        System.out.println("抓取失败：" + hCNetSDK.NET_DVR_GetLastError());
                    }
                    map.put("result",flag);
                    hCNetSDK.NET_DVR_Logout(lUserID);
                    hCNetSDK.NET_DVR_Cleanup();

                } else {
                    // 返回Boolean值，判断是否获取设备能力
                    logger.error("返回设备状态失败");
                }

            }
        }
        return map;
    }
}
