package com.maxcar.market.utils;


import com.maxcar.base.util.StringUtils;
import com.maxcar.market.model.base.BaseRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class ToolUtils {

    public static void main(String[] args) {

       /* System.out.println(gettimeFormart(null));*/

        try {
            /*int x = 3;
            int y =4;
            System.out.println(4/3);
            LocalDate beforeTime = LocalDate.now();
            System.out.println(beforeTime.toString());

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int year = cal.get(Calendar.YEAR);//获取年份
            int month = cal.get(Calendar.MONTH);//获取月份
            int day = cal.get(Calendar.DATE);//获取日

           //cal.add(Calendar.MONTH, );
            System.out.println(cal.get(Calendar.YEAR)+"-" +cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DATE));
            LocalDate afterTime = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            System.out.println(afterTime.toString());*/
          /*  LocalDate beforeTime = LocalDate.now();
            LocalDate afterTime = LocalDate.of(2018, 8, 26);*/
            LocalDate beforeTime = LocalDate.of(2016, 9, 1);
            LocalDate afterTime = LocalDate.of(2018, 10, 30);
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = beforeTime.atStartOfDay(zoneId);
            ZonedDateTime zdt2 = afterTime.atStartOfDay(zoneId);

            System.out.println(beforeTime);
            System.out.println(afterTime);
            //System.out.println(getMonth(Date.from(zdt.toInstant()), Date.from(zdt2.toInstant())));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * param:
     * describe: 获取停车收费班次信息
     * create_date:  lxy   2018/8/29  14:00
     **/
    public static String gettimeFormart(String shift) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");

        if (StringUtils.isBlank(shift) || !shift.substring(0, 8).equals(format.format(new Date()))) {

            return format.format(new Date()) + "0001";
        } else {
            // 判断日期是否正确
            return (Long.valueOf(shift) + 1) + "";
        }

    }

    /**
     * param:
     * describe: 计算数据起始值
     * create_date:  lxy   2018/8/28  16:03
     **/
    public static <T extends BaseRequest> T getStartRowAndEndRow(T t) {

        t.setStartRow(t.getCurPage() <= 1 ? 0 : (t.getCurPage() - 1) * t.getPageSize());

        if (t.getStartRow() >= t.getTotal()) {
            t.setCurPage(1);
            t.setPageSize(10);

            t.setStartRow(0);
            t.setEndRow(t.getTotal() - 1);

        } else {

            t.setEndRow(t.getCurPage() * t.getPageSize() > t.getTotal() ? t.getTotal() : t.getStartRow() + t.getPageSize() - 1);
        }

        t.setEndRow(t.getEndRow() + 1);
        return t;
    }


    /**
     * param:
     * describe: 查看时间间隔月数
     * create_date:  lxy   2018/8/20  16:07
     **/
   /* public static int getIntervalMonth(LocalDate beforeTime, LocalDate afterTime) {
        Period p = Period.between(beforeTime, afterTime);
        //  System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        return p.getMonths();
    }*/

    /**
     * 获取两个日期相差几个月
     *
     * @param beforeTime
     * @param afterTime
     * @return
     * @author 石冬冬-Heil Hilter(dd.shi02@zuche.com)
     * @date 2016-11-30 下午7:57:32
     */
    public static int getMonth(LocalDate beforeTime, LocalDate afterTime) {
        ZoneId zoneId = ZoneId.systemDefault();

        Date start = Date.from(beforeTime.atStartOfDay(zoneId).toInstant());
        Date end = Date.from(afterTime.atStartOfDay(zoneId).toInstant());

        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }


    /**
     * param:
     * describe: 查看间隔天数
     * create_date:  lxy   2018/8/23  14:24
     **/
    public static long getIntervalDay(LocalDate beforeTime, LocalDate afterTime) {
        // Period p = Period.between(beforeTime, afterTime);
        //  System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        return afterTime.toEpochDay() - beforeTime.toEpochDay();
    }


    /**
     * param:
     * describe: 获取七天后的时间
     * create_date:  lxy   2018/8/20  12:06
     **/
    public static Date getSevenDaysLater() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, date.get(Calendar.DATE) + 7);
        return date.getTime();
    }

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
