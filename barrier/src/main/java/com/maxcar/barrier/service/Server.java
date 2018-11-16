package com.maxcar.barrier.service;

import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.pojo.Car;
import com.maxcar.util.CRC16M;
import com.maxcar.util.HexUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

@Service("server")
public class Server {
    public static final int PORT = 2203;//监听的端口号
    private static final String between = "00000000000";
    private static String outParam = "";
    private Socket socket;
    DataInputStream dis = null;
    DataOutputStream out = null;
    boolean running = true;

    public void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                // 一旦有堵塞, 则表示服务器与客户端获得了连接
                Socket client = serverSocket.accept();
                // 处理这次连接
                new HandlerThread(client);
            }
        } catch (Exception e) {
            System.out.println("服务器异常: " + e.getMessage());
        }
    }

    private class HandlerThread implements Runnable {


        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        public void run() {
            try {
                dis = new DataInputStream(socket.getInputStream());
                byte[] bytes = null;
                int size = 0;
                while (running) {
                    bytes = new byte[1024]; // 假设发送的字节数不超过 1024 个
                    size = dis.read(bytes); // size 是读取到的字节数


                    String clientData = bytesToHex(bytes, 0, size).replaceAll(" ", "");//去空格
                    String hex = CRC16M.GetModBusCRC(clientData.substring(0, clientData.length() - 4));
                    String check = clientData.substring(clientData.length() - 4);
                /*
                校验数据是否正确
                 */
                    if (hex.equalsIgnoreCase(check)) {//校验数据准确性
                        BarrierService barrierService = ApplicationContextHolder.getBean("barrierService");
                        if (size == 34) {//初始配置参数
                            String setParam = clientData.substring(62, 64);
                            if (setParam.equals("02")) {
                                String ID = clientData.substring(30, 38);
                                String valueTen = Integer.parseInt(ID, 16) + "";
                                Barrier barrier = barrierService.selectByBarrierId(valueTen.substring(valueTen.length() - 6, valueTen.length()));
                                if (barrier != null) {

                                    //查出的十进制转为十六进制
                                    Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

                                    Integer years = c.get(Calendar.YEAR);
                                    String year = "0" + years.toHexString(years);
                                    Integer months = c.get(Calendar.MONTH);
                                    String month = "0" + months.toHexString(months);
                                    String date = toHexString(c.get(Calendar.DATE));
                                    String hour = toHexString(c.get(Calendar.HOUR_OF_DAY));
                                    String minute = toHexString(c.get(Calendar.MINUTE));
                                    String second = toHexString(c.get(Calendar.SECOND));
                                    String week = getWeekOfDate();

                                    String gateWapIp = barrier.getGatewayIp();//网关
                                    String subNetMask = barrier.getSubnetMask();//子网掩码
                                    String mscAddress = barrier.getMacAddress();//物理地址十六进制
                                    String clientIp = barrier.getClientIp();//道闸ip
                                    String clientPort = barrier.getClientPort();//道闸端口
                                    String serverIp = barrier.getServerIp();//服务端ip
                                    String serverPort = barrier.getServerPort();//服务器端口
                                    String[] array = gateWapIp.split("\\.");
                                    gateWapIp = "";
                                    for (int i = 0; i < array.length; i++) {
                                        gateWapIp += toHexString(Integer.parseInt(array[i]));
                                    }

                                    String[] array1 = subNetMask.split("\\.");
                                    subNetMask = "";
                                    for (int i = 0; i < array1.length; i++) {
                                        subNetMask += toHexString(Integer.parseInt(array1[i]));
                                    }

                                    String mscAdd = mscAddress.replaceAll(":", "");

                                    String[] array2 = clientIp.split("\\.");
                                    clientIp = "";
                                    for (int i = 0; i < array2.length; i++) {
                                        clientIp += toHexString(Integer.parseInt(array2[i]));
                                    }

                                    Integer port = (Integer.parseInt(clientPort));
                                    clientPort = port.toHexString(port);

                                    String[] array3 = serverIp.split("\\.");
                                    serverIp = "";
                                    for (int i = 0; i < array3.length; i++) {
                                        serverIp += toHexString(Integer.parseInt(array3[i]));
                                    }
                                    Integer port1 = (Integer.parseInt(serverPort));
                                    serverPort = port1.toHexString(port1);


                                    outParam = clientData.substring(0, 4) + "0051" + clientData.substring(8, 10) + "82" + clientData.substring(12, 58) + "003082" + year + month + date + hour + minute + second + week + clientData.substring(12, 38) + gateWapIp + subNetMask + mscAdd + clientIp + clientPort + serverIp + serverPort;
                                    System.out.println(outParam);
                                    String outHex = CRC16M.GetModBusCRC(outParam);
                                    outParam = outParam + outHex;
                                    System.out.println(outParam);
                                }
                            }

                        } else if (size == 81) {//请求开闸

                            String ID = clientData.substring(30, 38);//获取道闸唯一ID号
                            System.out.println("道闸ID号" + ID);
                            String valueTen = Integer.parseInt(ID, 16) + "";
                            Barrier barrier = barrierService.selectByBarrierId(valueTen.substring(valueTen.length() - 6, valueTen.length()));//查询欢迎词
                            System.out.println("接收到的byte数组的十六进制：" + clientData);
                            String value1 = clientData.substring(0, 4);
                            String value2 = "002C";//44字节
                            String value3 = clientData.substring(8, 10);
                            String value4 = "81";//下发数据
                            String value5 = clientData.substring(12, 58);
                            String value6 = "000B81";
                            String value7 = "";
                            String value8 = "";//欢迎词


                            String clientDatas = clientData.substring(66, 74);
                            int valueTens = Integer.parseInt(clientDatas, 16);
                            String values = "" + valueTens;
                            while (values.length() < 10) {
                                StringBuffer sb = new StringBuffer();
                                sb.append("0").append(values);// 左补0
                                values = sb.toString();
                            }
                            if (barrier == null) {
                                value7 = "FF08";
                                value8 = "配置有误";
                                outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//配置有误
                                String outHex = CRC16M.GetModBusCRC(outParam);
                                outParam = outParam + outHex;
                            } else if (barrier.getInOutType() == 0) {//道闸不限制
                                String marketId = barrier.getMarketId();
                                Car stockCarInfo = barrierService.selectByRFID(marketId + between + values);//查询是否允许开闸
                                if (stockCarInfo != null) {
                                    value7 = "8008";//允许开闸
                                    value8 = barrier.getStaticSpeech();
                                    outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);
                                    String outHex = CRC16M.GetModBusCRC(outParam);
                                    outParam = outParam + outHex;
                                    System.out.print("+++++++++" + outParam);
                                } else {
                                    value7 = "FF08";
                                    value8 = "无录入车";
                                    outParam = value1 + value2 + value3 + value4 + value5 + value6 + value7 + HexUtils.getHexResult(value8);//禁止出入
                                    String outHex = CRC16M.GetModBusCRC(outParam);
                                    outParam = outParam + outHex;
                                }
                            } else if (barrier.getInOutType() == 1) {//道闸限制车辆出入

                            }
                        }
                        out = new DataOutputStream(socket.getOutputStream());
                        // 只能输出byte数组，所以将字符串变为byte数组
//                    while(running){
//                        Thread.sleep(1000);
                        byte b[] = toBytes(outParam);
                        out.write(b, 0, b.length);
                        out.flush();
//                    }

                    } else {
                        //返回校验数据不正确
                    }
                }
//                dis.close();
            } catch (Exception e) {
                System.out.println("服务器 run 异常: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }


    }

    public void outStream() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            // 只能输出byte数组，所以将字符串变为byte数组
            String s = "4D43002C01810C05D1FF373438594D430352335B6A346800010001FFFF000B818008BBB6D3ADB9E2C1D925C2";
            outParam = s;
            byte b[] = toBytes(outParam);

            out.write(b, 0, b.length);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String bytesToHex(byte[] bytes, int begin, int end) {
        StringBuilder hexBuilder = new StringBuilder(2 * (end - begin));
        for (int i = begin; i < end; i++) {
            hexBuilder.append(Character.forDigit((bytes[i] & 0xF0) >> 4, 16)); // 转化高四位
            hexBuilder.append(Character.forDigit((bytes[i] & 0x0F), 16)); // 转化低四位
            hexBuilder.append(' '); // 加一个空格将每个字节分隔开
        }
        return hexBuilder.toString().toUpperCase();
    }

    public static String decimalToHex(int decimal) {
        String hex = "";
        while (decimal != 0) {
            int hexValue = decimal % 16;
            hex = toHexChar(hexValue) + hex;
            decimal = decimal / 16;
        }
        return hex;
    }

    //将0~15的十进制数转换成0~F的十六进制数
    public static char toHexChar(int hexValue) {
        if (hexValue <= 9 && hexValue >= 0)
            return (char) (hexValue + '0');
        else
            return (char) (hexValue - 10 + 'A');
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /*
    十进制转十六进制前面补0
     */
    public String toHexString(Integer times) {
        String time;
        if (times < 16) {
            time = "0" + times.toHexString(times);
        } else {
            time = times.toHexString(times);
        }
        return time;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param
     * @return 当前日期是星期几
     */
    public String getWeekOfDate() {
        Date date = new Date();
        String[] weekDays = {"07", "01", "02", "03", "04", "05", "06"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    public static void main(String[] args) {

//        System.out.println("服务器启动...\n");
        Server server = new Server();
        server.init();


    }

}
