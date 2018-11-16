package com.maxcar.base.util.pay;

import java.security.MessageDigest;

public class PayUtil {

    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    public static String toHexValue(byte[] messageDigest) {
        if (messageDigest == null) {
            return "";
        } else {
            StringBuilder hexValue = new StringBuilder();
            byte[] var5 = messageDigest;
            int var4 = messageDigest.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                byte aMessageDigest = var5[var3];
                int val = 255 & aMessageDigest;
                if (val < 16) {
                    hexValue.append("0");
                }

                hexValue.append(Integer.toHexString(val));
            }

            return hexValue.toString();
        }
    }
}
