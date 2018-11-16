package com.maxcar.base.util;

/**
 * describe: data工具类
 * create_date: lxy 2018/10/12  14:04
 **/
public class ToolDataUtils {


    /**
     * param:
     * describe: 判断SQL操作成功
     * create_date:  lxy   2018/8/16  18:29
     **/
    public static boolean isOperationSuccess(int status) {

        if (1 <= status) {
            return true;
        }

        return false;
    }

    /**
     * param:
     * describe: 判断SQL操作失败
     * create_date:  lxy   2018/8/16  18:29
     **/
    public static boolean isOperationFail(int status) {
        return !isOperationSuccess(status);
    }
}
