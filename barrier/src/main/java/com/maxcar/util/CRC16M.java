package com.maxcar.util;


import com.maxcar.base.util.StringUtils;

public class CRC16M {

    private static int[] strToToHexInt(String hexString) {
        hexString = hexString.replace(" ", "");
// 如果长度不是偶数，那么后面添加空格。
        if ((hexString.length() % 2) != 0) {
            hexString += " ";
        }
// 定义数组，长度为待转换字符串长度的一半。
        int[] returnBytes = new int[hexString.length() / 2];
        for (int i = 0; i < returnBytes.length; i++) {
            returnBytes[i] = (0xff & Integer.parseInt(hexString.substring(i * 2, i * 2 + 2), 16));
        }
        return returnBytes;
    }

    /**
     * ModBusCRC验证 输入十六进制返回十六进制数
     *
     * @param data
     * @return
     */
    public static String GetModBusCRC(String data) {
        long functionReturnValue = 0;
        long i = 0;

        long J = 0;
        int[] v = strToToHexInt(data);

        long CRC = 0;
        CRC = 0xffffL;
        for (i = 0; i <= (v).length - 1; i++) { // 2.把第一个8位二进制数据（既通讯信息帧的第一个字节）与16位的CRC寄存器的低8位相异或，把结果放于CRC寄存器；
            CRC = (CRC / 256) * 256L + (CRC % 256L) ^ v[(int) i];
            for (J = 0; J < 8; J++) { // 3.把CRC寄存器的内容右移一位（朝低位）用0填补最高位，并检查最低位；
// 4.如果最低位为0：重复第3步（再次右移一位）；
// 如果最低位为1：CRC寄存器与多项式A001（1010 0000 0000 0001）进行异或；
// 5.重复步骤3和4，直到右移8次，这样整个8位数据全部进行了处理；
                long d0 = 0;
                d0 = CRC & 1L;
                CRC = CRC / 2;
                if (d0 == 1)
                    CRC = CRC ^ 0xa001L;
            } // 6.重复步骤2到步骤5，进行通讯信息帧下一字节的处理；
        } // 7.最后得到的CRC寄存器内容即为：CRC码。
        CRC = CRC % 65536;
        functionReturnValue = CRC;
        Integer x = (int) functionReturnValue;
        String hex = x.toHexString(x);
        //CRC校验4位循环补0
        StringBuilder sb = new StringBuilder();
        if (hex.length() < 4){
            for (int k = 0;k<4-hex.length();k++){
                sb.append("0");
            }
            sb.append(hex);
        }else {
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        System.out.print(GetModBusCRC("4d43003001870c05D4FF373438594D430353595bceb19c00010001FFFF000B87FF0CBDFBD6B9D6D8B8B4C8EBB3A1"));
    }
}