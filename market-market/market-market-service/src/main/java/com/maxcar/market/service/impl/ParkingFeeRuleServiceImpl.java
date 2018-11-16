package com.maxcar.market.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.DateUtils;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.dao.FeePeriodTimeMapper;
import com.maxcar.market.dao.ParkingFeeTotalMapper;
import com.maxcar.market.pojo.FeePeriodTime;
import com.maxcar.market.pojo.FeePeriodTimeExample;
import com.maxcar.market.pojo.ParkingFeeTotal;
import com.maxcar.market.pojo.ParkingFeeTotalExample;
import com.maxcar.market.service.ParkingFeeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("parkingFeeRuleService")
public class ParkingFeeRuleServiceImpl implements ParkingFeeRuleService {
    @Autowired
    private ParkingFeeTotalMapper parkingFeeTotalMapper;
    @Autowired
    private FeePeriodTimeMapper feePeriodTimeMapper;

    @Override
    public BigDecimal figureOutParkingFee(Date begin, Date payTime, String marketId, Integer carType, int num) throws Exception {
        /**
         * 南通第二阶段收费
         *
         */
        Date newBegin = DateUtils.addMinute(payTime,15);
        Date currentTime = Calendar.getInstance().getTime();
        ParkingFeeTotalExample example = new ParkingFeeTotalExample();
        example.createCriteria().andMarketIdEqualTo(marketId).andIsValidEqualTo(1)
                .andCarTypeEqualTo(carType);
        List<ParkingFeeTotal> parkingFeeTotals = parkingFeeTotalMapper.selectByExample(example);
        //读取计费时段配置
        FeePeriodTimeExample feePeriodTimeExample = new FeePeriodTimeExample();
        feePeriodTimeExample.createCriteria().andMarketIdEqualTo(marketId)
                .andIsValidEqualTo(1).andCarTypeEqualTo(carType);
        List<FeePeriodTime> feePeriodTimes = feePeriodTimeMapper.selectByExample(feePeriodTimeExample);

        BigDecimal total = new BigDecimal(0);
        if (null != feePeriodTimes && feePeriodTimes.size() > 0) {
            boolean isMax = false;
            int maxPrice = 0;
            ParkingFeeTotal parkingFeeTotal = parkingFeeTotals.get(0);
            if (null != parkingFeeTotal) {
                //如果调用此接口,则说明免费停车时间用完了，4小时免费，超过4小时每小时5块，超过48小时10块
                //设置了上限
                if (parkingFeeTotal.getIsLimit() == 1) {
                    if (parkingFeeTotal.getFeeLimit() != 0) {
                        isMax = true;
                        maxPrice = parkingFeeTotal.getFeeLimit();
                    }
                }
                //判断是否勾选超时计费,1为勾选
                if (parkingFeeTotal.getIsBeyond() == 1) {
                    long afterTime = parkingFeeTotal.getBeyondTime() * 60 * 60 * 1000;
                    if (afterTime != 0) {
                        //停车时长超过设置的时长
                        if (newBegin.getTime() - begin.getTime() > afterTime) {
                            /*//拆分两个阶段计算
                            Integer firstFee = 0;
                            Date secondStart = new Date(begin.getTime() + afterTime);
                            if (isMax) {
                                List<Map> firstSplitTime = DateUtils.getSplitTime(newBegin, secondStart);
                                if (firstSplitTime != null && firstSplitTime.size() > 0) {
                                    firstFee = getFrees(firstSplitTime, feePeriodTimes, true, maxPrice);
                                }
                            }
                            if (!isMax) {
                                firstFee = getSplitTime(newBegin, secondStart, feePeriodTimes);
                            }*/
                            //超出后的换算为小时
                            BigDecimal secondTime = new BigDecimal(currentTime.getTime() - newBegin.getTime());
                            BigDecimal result = secondTime.divide(new BigDecimal(1000 * 60 * 60), BigDecimal.ROUND_UP, 0);
                            Integer secondFee = result.intValue() * parkingFeeTotal.getBeyondFee();
                            total = new BigDecimal(secondFee);
                            return total;
                        }else{
                            List<Map> getSplitTime = DateUtils.getSplitTime(newBegin, currentTime);
                            //获取分段的时间，然后每个时间段跟规则对比，计算出金额
                            if (getSplitTime != null && getSplitTime.size() > 0) {
                                total = new BigDecimal(getFrees(getSplitTime, feePeriodTimes, isMax, maxPrice));
                                return total;
                            } else {
                                return total;
                            }
                        }
                    }
                }

            } else {
                total = new BigDecimal(getSplitTime(newBegin, currentTime, feePeriodTimes));
                return total;
            }
        }else{
            return total;
        }
        return total;
    }

    @Override
    public BigDecimal figureOutParkingFee(Date begin, Date end, String marketId, Integer carType) throws Exception {
        //根据marketId取规则
        ParkingFeeTotalExample example = new ParkingFeeTotalExample();
        if (carType == 0) {
            example.createCriteria().andMarketIdEqualTo(marketId).andIsValidEqualTo(1)
                    .andCarTypeEqualTo(1);
        } else {
            example.createCriteria().andMarketIdEqualTo(marketId).andIsValidEqualTo(1)
                    .andCarTypeEqualTo(carType);
        }
        List<ParkingFeeTotal> parkingFeeTotals = parkingFeeTotalMapper.selectByExample(example);

        //读取计费时段配置
        FeePeriodTimeExample feePeriodTimeExample = new FeePeriodTimeExample();
        feePeriodTimeExample.createCriteria().andMarketIdEqualTo(marketId)
                .andIsValidEqualTo(1).andCarTypeEqualTo(carType);
        List<FeePeriodTime> feePeriodTimes = feePeriodTimeMapper.selectByExample(feePeriodTimeExample);
        Date intime1 = null;
        BigDecimal total = new BigDecimal(0);
        if (null != feePeriodTimes && feePeriodTimes.size() > 0) {
            boolean isMax = false;
            int maxPrice = 0;
            ParkingFeeTotal parkingFeeTotal = parkingFeeTotals.get(0);
            if (null != parkingFeeTotal) {
                if (parkingFeeTotal.getIsFree() == 1) {
                    intime1 = DateUtils.addDate(begin, parkingFeeTotal.getFreeTime() * 60 * 1000);
                    if (end.getTime() - intime1.getTime() <= 0) {
                        return total;
                    }
                } else if(parkingFeeTotal.getIsFree() == 0){
                    intime1 = begin;
                }
                //设置了上限
                if (parkingFeeTotal.getIsLimit() == 1) {
                    if (parkingFeeTotal.getFeeLimit() != 0) {
                        isMax = true;
                        maxPrice = parkingFeeTotal.getFeeLimit();
                    }
                }
                //判断是否勾选超时计费,1为勾选
                if (parkingFeeTotal.getIsBeyond() == 1) {
                    long afterTime = parkingFeeTotal.getBeyondTime() * 60 * 60 * 1000;
                    if (afterTime != 0) {
                        //停车时长超过设置的时长
                        if (end.getTime() - begin.getTime() > afterTime) {
                            //拆分两个阶段计算
                            Integer firstFee = 0;
                            Date secondStart = new Date(begin.getTime() + afterTime);
                            if (isMax) {
                                List<Map> firstSplitTime = DateUtils.getSplitTime(intime1, secondStart);
                                if (firstSplitTime != null && firstSplitTime.size() > 0) {
                                    firstFee = getFrees(firstSplitTime, feePeriodTimes, true, maxPrice);
                                }
                            }
                            if (!isMax) {
                                firstFee = getSplitTime(intime1, secondStart, feePeriodTimes);
                            }
                            //超出后的换算为小时
                            BigDecimal secondTime = new BigDecimal(end.getTime() - secondStart.getTime());
                            BigDecimal result = secondTime.divide(new BigDecimal(1000 * 60 * 60), BigDecimal.ROUND_UP, 0);
                            Integer secondFee = result.intValue() * parkingFeeTotal.getBeyondFee();
                            total = new BigDecimal(firstFee + secondFee);
                            return total;
                        }
                    }
                }
                List<Map> getSplitTime = DateUtils.getSplitTime(intime1, end);
                //获取分段的时间，然后每个时间段跟规则对比，计算出金额
                if (getSplitTime != null && getSplitTime.size() > 0) {
                    total = new BigDecimal(getFrees(getSplitTime, feePeriodTimes, isMax, maxPrice));
                    return total;
                } else {
                    return total;
                }
            } else {
                total = new BigDecimal(getSplitTime(begin, end, feePeriodTimes));
                return total;
            }
        } else {
            if (null != parkingFeeTotals && parkingFeeTotals.size() == 1) {
                ParkingFeeTotal parkingFeeTotal = parkingFeeTotals.get(0);
                //计算时分秒
                Map map = DateUtils.getHMS(end, begin);
                Integer hour = (Integer) map.get("hour");
                Integer minute = (Integer) map.get("minute");
                Integer second = (Integer) map.get("second");
                //判断是否开启免费,分钟
                if (parkingFeeTotal.getIsFree() == 1) {
                    Integer freeTime = parkingFeeTotal.getFreeTime();
                    BigDecimal bd = new BigDecimal(hour);
                    //不足1分钟算1分钟,得到总分钟数
                    BigDecimal minu = bd.multiply(new BigDecimal(60))
                            .add(new BigDecimal(minute)).add(new BigDecimal(1));
                    System.out.println(minu.intValue());
                    if (freeTime >= minu.intValue()) {
                        //停车免费时长大于实际停车时长
                        return total;
                    } else {
                        return configureFee(parkingFeeTotal, minu, freeTime, end, begin, total);
                    }
                } else if(parkingFeeTotal.getIsFree() == 0){
                    //没有开启免费
                    if (parkingFeeTotal.getIsBeyond() == 1) {
                        //开启了超时,超出时长即为免费时长,走相同计费逻辑
                        Integer freeTime = parkingFeeTotal.getBeyondTime() * 60;
                        BigDecimal bd = new BigDecimal(hour);
                        //不足1分钟算1分钟,得到总分钟数
                        BigDecimal minu = bd.multiply(new BigDecimal(60))
                                .add(new BigDecimal(minute)).add(new BigDecimal(1));
                        if (freeTime >= minu.intValue()) {
                            //超过时长大于实际停车时长
                            return total;
                        } else {
                            return configureFee(parkingFeeTotal, minu, freeTime, end, begin, total);
                        }
                    } else if(parkingFeeTotal.getIsBeyond() == 0){
                        //没有开启超时
                        return total;
                    }
                }
            }
        }
        return total;
    }

    /**
     * 根据停车时间段获取 规则时间段里面的数据，计算成金额
     *
     * @param getSplitTime
     * @param ruleList
     * @return
     */
    public static int getFrees(List<Map> getSplitTime, List<FeePeriodTime> ruleList, boolean isMax, int maxPrice) {
        int frees = 0;
        for (Map map : getSplitTime) {
            int free = 0;
            for (FeePeriodTime feePeriodTime : ruleList) {
                try {
                    free += getFree(map, feePeriodTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (isMax) {
                if (maxPrice < free) {
                    free = maxPrice;
                }
            }
            frees += free;

        }
        return frees;
    }

    public static int getFree(Map map, FeePeriodTime feePeriodTime) throws ParseException {
        int free = 0;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day = "2018-10-01 ";//用于时间装换，没有实际意义，可以写死
        //停车时间
        String startTime = (String) map.get("startTime");
        long sPTime = format.parse(day + startTime).getTime() / 1000 / 60;
        String endTime = (String) map.get("endTime");
        long ePTime = format.parse(day + endTime).getTime() / 1000 / 60;
        //规则时间
        String sTime = feePeriodTime.getStartTime();
        String eTime = feePeriodTime.getEndTime();
        long sRTime = format.parse(day + sTime).getTime() / 1000 / 60;
        long eRTime = format.parse(day + eTime).getTime() / 1000 / 60;
        //计算多少个 30分钟
        /**
         * 1.停车开始时间结束时间都不在规则范围  return 0
         * 2.停车开始结束都在规则范围类   计算停了多少分 return 金额
         * 3.停车开始时间在规则范围类，结束时间不在  计算  开始时间 到规则结束时间 多少分
         * 4.停车开始时间不在规则范围类，结束时间在规则范围类，  计算  规则开始时间 到 停车结束时间 多少分
         * 5.停车开始时间小于规则开始时间，停车结束时间大于规则结束时间，计算 规则开始时间到规则结束时间
         */
        if ((sPTime <= sRTime && sPTime >= eRTime) && (ePTime <= sRTime && ePTime >= eRTime)) {
            free = 0;
        } else if ((sPTime >= sRTime && sPTime <= eRTime) && (ePTime >= sRTime && ePTime <= eRTime)) {
            free += countFree(sPTime, ePTime, feePeriodTime.getFeeHour());
        } else if ((sPTime >= sRTime && sPTime <= eRTime) && (ePTime <= sRTime || ePTime >= eRTime)) {
            free += countFree(sPTime, eRTime, feePeriodTime.getFeeHour());
        } else if ((sPTime <= sRTime || sPTime >= eRTime) && (ePTime >= sRTime && ePTime <= eRTime)) {
            free += countFree(sRTime, ePTime, feePeriodTime.getFeeHour());
        } else if (sPTime <= sRTime && ePTime >= eRTime) {
            free += countFree(sRTime, eRTime, feePeriodTime.getFeeHour());
        }
        return free;
    }

    /*
     * 计算停了多少小时 多少钱
     */
    public static int countFree(long start, long end, int price) {
        double b = new BigDecimal((float) (end - start) / 60).doubleValue();
        return (int) Math.ceil(b) * price;
    }

    private static BigDecimal configureFee(ParkingFeeTotal parkingFeeTotal
            , BigDecimal minu, Integer freeTime, Date end, Date begin, BigDecimal zero) throws Exception {
        int restMinute = 0;
        //判断是否有超时规则
        if (parkingFeeTotal.getIsBeyond() == 1) {
            int beyondTime = parkingFeeTotal.getBeyondTime();
            int beyondMoney = parkingFeeTotal.getBeyondFee();
            BigDecimal beTime = new BigDecimal(beyondTime);
            BigDecimal beTimeMinute = beTime.multiply(new BigDecimal(60));
            //超过了免费时长,计算出剩余分钟数,超出时间和免费比较，取值大者
            if (beTimeMinute.intValue() > freeTime){
                restMinute = minu.intValue() - beTimeMinute.intValue();
            }else{
                restMinute = minu.intValue() - freeTime;
            }
            //计算超时的钱
            BigDecimal big = new BigDecimal(restMinute);
            //分钟换算成小时，剩余1分钟也算1小时
            BigDecimal restHour = big.divide(new BigDecimal(60), BigDecimal.ROUND_UP);
            //是否开启每日收费上限
            if (parkingFeeTotal.getIsLimit() == 0) {
                //没有设置收费上限,直接算出金额
                BigDecimal money = new BigDecimal(beyondMoney);
                return restHour.multiply(money);
            } else if(parkingFeeTotal.getIsLimit() == 1){
                Integer limitMoney = parkingFeeTotal.getFeeLimit();
                Integer beyondM = parkingFeeTotal.getBeyondFee();
                //设置收费上限,拆分每天，计算金额
                //累加免费停车时长为新时间
                Date newStart = DateUtils.addMinute(begin, freeTime);
                //获取俩日期的0点时间段
                List list = DateUtils.getDiffDateTimeSlot(newStart, end);
                Integer totalF = 0;
                Integer totalFe = 0;
                for (Object o : list) {
                    Map mapTime = (Map) o;
                    String startTime = String.valueOf(mapTime.get("startTime"));
                    String endTime = String.valueOf(mapTime.get("endTime"));
                    Date startDate = DateUtils.LONG_DATE_FORMAT.parse(startTime);
                    Date endDate = DateUtils.LONG_DATE_FORMAT.parse(endTime);
                    //只取小时,不足一小时算一小时
                    Map mapH = DateUtils.getHMS(endDate, startDate);
                    Integer hourDate = (Integer) mapH.get("hour");
                    Integer minuteDate = (Integer) mapH.get("minute");
                    Integer second = (Integer) mapH.get("second");
                    BigDecimal totalHour = new BigDecimal(0);
                    if (minuteDate > 0 || second > 0) {
                        totalHour = new BigDecimal(hourDate).add(new BigDecimal(1));
                    } else if (minuteDate == 0) {
                        totalHour = new BigDecimal(hourDate);
                    }
                    //每天总时长乘以超时计费，得到每天的最终金额,比较每天的收费上限
                    BigDecimal totalFee = totalHour.multiply(new BigDecimal(beyondM));
                    Integer tf = null == totalFee ? 0 : totalFee.intValue();
                    totalF = tf > limitMoney ? limitMoney : tf;
                    totalFe = totalF + totalFe;
                }
                return new BigDecimal(totalFe);
            }
        }
            //没有超时计费,就算有上限也不收费
            return zero;
    }

    public static Integer getSplitTime(Date dateStart, Date dateEnd, List<FeePeriodTime> ruleList) {
        int totalPrice = 0;
        BigDecimal hour = new BigDecimal(0);
        if (null != ruleList && ruleList.size() > 0) {
            try {
                long days = DateUtils.daysBetween(dateStart, dateEnd);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                Calendar can = Calendar.getInstance();
                long times1 = 0;
                int priceSum = 0;
                for (FeePeriodTime feePeriodTime : ruleList) {
                    long sum1 = 0;
                    int price = feePeriodTime.getFeeHour();
                    if (days > 0) {
                        for (int i = 0; i <= days; i++) {
                            can.setTime(dateStart);
                            can.add(Calendar.DATE, i);
                            String start = sdf1.format(can.getTime()) + " " + feePeriodTime.getStartTime();
                            String end = sdf1.format(can.getTime()) + " " + feePeriodTime.getEndTime();
                            Date startD = sdf.parse(start);
                            Date endD = sdf.parse(end);
                            if (i == 0) {
                                if (dateStart.getTime() >= startD.getTime() && dateStart.getTime() < endD.getTime()) {
                                    times1 = (endD.getTime() - dateStart.getTime()) / (1000 * 60);
                                }
                                if (dateStart.getTime() < startD.getTime()) {
                                    times1 = (endD.getTime() - startD.getTime()) / (1000 * 60);
                                }
                                if (dateStart.getTime() >= endD.getTime()) {
                                    times1 = 0;
                                }
                            }
                            if (i == days) {
                                if (dateEnd.getTime() >= endD.getTime()) {
                                    times1 = (endD.getTime() - startD.getTime()) / (1000 * 60);
                                }
                                if (dateEnd.getTime() < endD.getTime() && dateEnd.getTime() > startD.getTime()) {
                                    times1 = (dateEnd.getTime() - startD.getTime()) / (1000 * 60);
                                }
                                if (dateEnd.getTime() <= startD.getTime()) {
                                    times1 = 0;
                                }
                            }
                            if (i != 0 && i != days) {
                                times1 = (endD.getTime() - startD.getTime()) / (1000 * 60);
                            }
                            sum1 += times1;
                        }
                    } else {
                        can.setTime(dateStart);
                        can.add(Calendar.DATE, 0);
                        String start = sdf1.format(can.getTime()) + " " + feePeriodTime.getStartTime();
                        String end = sdf1.format(can.getTime()) + " " + feePeriodTime.getEndTime();
                        Date startD = sdf.parse(start);
                        Date endD = sdf.parse(end);
                        if (dateEnd.getTime() <= startD.getTime()) {
                            times1 = 0;
                        }
                        if (dateStart.getTime() <= startD.getTime() && dateEnd.getTime() > startD.getTime()
                                && dateEnd.getTime() <= endD.getTime()) {
                            times1 = (dateEnd.getTime() - startD.getTime()) / (1000 * 60);
                        }
                        if (dateStart.getTime() <= startD.getTime() && dateEnd.getTime() > endD.getTime()) {
                            times1 = (endD.getTime() - startD.getTime()) / (1000 * 60);
                        }
                        if (dateStart.getTime() > startD.getTime() && dateStart.getTime() <= endD.getTime()
                                && dateEnd.getTime() > endD.getTime()) {
                            times1 = (endD.getTime() - dateStart.getTime()) / (1000 * 60);
                        }
                        if (dateStart.getTime() >= startD.getTime() && dateEnd.getTime() <= endD.getTime()) {
                            times1 = (dateEnd.getTime() - dateStart.getTime()) / (1000 * 60);
                        }
                        if (dateStart.getTime() >= endD.getTime()) {
                            times1 = 0;
                        }
                        sum1 = times1;
                    }
                    BigDecimal totalTimes = new BigDecimal(sum1);
                    hour = totalTimes.divide(new BigDecimal(60), BigDecimal.ROUND_UP, 0);
                    priceSum = hour.intValue() * price;
                    totalPrice += priceSum;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalPrice;
    }


    public static void main(String[] args) throws Exception {
        List<FeePeriodTime> feePeriodTimes = new ArrayList<>();
        FeePeriodTime feePeriodTime = new FeePeriodTime();
        feePeriodTime.setStartTime("10:22:34");
        feePeriodTime.setEndTime("16:18:09");
        feePeriodTimes.add(feePeriodTime);

        Date date1 = DateUtils.LONG_DATE_FORMAT.parse("2019-09-18 13:09:45");
        Date date3 = DateUtils.LONG_DATE_FORMAT.parse("2019-09-18 10:22:34");
        Date date2 = DateUtils.LONG_DATE_FORMAT.parse("2019-09-19 17:45:12");

        System.out.println(DateUtils.daysBetween(date3, date2));
        ;
        //     System.out.println(DateUtils.compareTime(date1,date3,Calendar.SECOND));
        //      List list = getDiffDateTimeSlot(date1,date2,feePeriodTimes);
        //      System.out.println(list);
    }

    @Override
    public InterfaceResult getTotalRule(String marketId, String userId) {
        InterfaceResult result = new InterfaceResult();
        JSONObject json = new JSONObject();
        ParkingFeeTotalExample example = new ParkingFeeTotalExample();
        example.createCriteria().andMarketIdEqualTo(marketId).andIsValidEqualTo(1);
        List<ParkingFeeTotal> parkingFeeTotals = parkingFeeTotalMapper.selectByExample(example);
        ParkingFeeTotal parkingFeeTotal = new ParkingFeeTotal();
        if (null != parkingFeeTotals && !parkingFeeTotals.isEmpty()) {
            json.put("parkingFeeTotal", parkingFeeTotals.get(0));
        } else {
            //如果没有规则,生成一条默认的总规则配置
            parkingFeeTotal.setId(UuidUtils.generateIdentifier());
            parkingFeeTotal.setIsFree(0);
            parkingFeeTotal.setFreeTime(0);
            parkingFeeTotal.setIsBeyond(0);
            parkingFeeTotal.setBeyondTime(0);
            parkingFeeTotal.setBeyondFee(0);
            parkingFeeTotal.setIsLimit(0);
            parkingFeeTotal.setFeeLimit(0);
            parkingFeeTotal.setMarketId(marketId);
            parkingFeeTotal.setInsertTime(Calendar.getInstance().getTime());
            parkingFeeTotal.setInsertOperator(userId);
            parkingFeeTotalMapper.insertSelective(parkingFeeTotal);
            json.put("parkingFeeTotal", parkingFeeTotal);
        }
        FeePeriodTimeExample feePeriodTimeExample = new FeePeriodTimeExample();
        feePeriodTimeExample.createCriteria().andMarketIdEqualTo(marketId).andIsValidEqualTo(1);
        List<FeePeriodTime> feePeriodTimes = feePeriodTimeMapper.selectByExample(feePeriodTimeExample);
        json.put("feePeriodTime", feePeriodTimes);
        result.InterfaceResult200(json);
        return result;
    }

    @Override
    public InterfaceResult addFeePeriodTime(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        String marketId = params.getString("marketId");
        String userId = params.getString("userId");
        FeePeriodTime feePeriodTime = new FeePeriodTime();
        feePeriodTime.setId(UuidUtils.generateIdentifier());
        feePeriodTime.setMarketId(marketId);
        feePeriodTime.setInsertOperator(userId);
        if (params.containsKey("startTime")) {
            feePeriodTime.setStartTime(params.getString("startTime"));
        }
        if (params.containsKey("endTime")) {
            feePeriodTime.setEndTime(params.getString("endTime"));
        }
        if (params.containsKey("feeHour")) {
            feePeriodTime.setFeeHour(params.getIntValue("feeHour"));
        }
        if (params.containsKey("carType")) {
            feePeriodTime.setCarType(params.getIntValue("carType"));
        }

        if (!checkIsExist(marketId, feePeriodTime, 1)) {
            int code = feePeriodTimeMapper.insertSelective(feePeriodTime);
            if (code == 1) {
                result.InterfaceResult200(null);
            } else {
                result.InterfaceResult500("新增计费时段失败!");
            }
        } else {
            result.InterfaceResult500("新增计费时段重复!");
        }
        return result;
    }

    @Override
    public InterfaceResult deleteFeePeriodTime(String marketId, String feePeriodTimeId, String userId) {
        InterfaceResult result = new InterfaceResult();
        FeePeriodTime feePeriodTime = new FeePeriodTime();
        feePeriodTime.setId(feePeriodTimeId);
        feePeriodTime.setMarketId(marketId);
        feePeriodTime.setIsValid(0);
        feePeriodTime.setUpdateOperator(userId);
        feePeriodTime.setUpdateTime(Calendar.getInstance().getTime());
        int code = feePeriodTimeMapper.updateByPrimaryKeySelective(feePeriodTime);
        if (code == 1) {
            result.InterfaceResult200(null);
        } else {
            result.InterfaceResult500("删除收费时段失败!");
        }
        return result;
    }

    @Override
    public InterfaceResult editFeePeriodTime(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        String marketId = params.getString("marketId");
        String userId = params.getString("userId");
        FeePeriodTime feePeriodTime = new FeePeriodTime();
        feePeriodTime.setUpdateTime(Calendar.getInstance().getTime());
        feePeriodTime.setUpdateOperator(userId);
        if (params.containsKey("startTime")) {
            feePeriodTime.setStartTime(params.getString("startTime"));
        }
        if (params.containsKey("endTime")) {
            feePeriodTime.setEndTime(params.getString("endTime"));
        }
        if (params.containsKey("feeHour")) {
            feePeriodTime.setFeeHour(params.getIntValue("feeHour"));
        }
        if (!checkIsExist(marketId, feePeriodTime, 0)) {
            int code = feePeriodTimeMapper.updateByPrimaryKeySelective(feePeriodTime);
            if (code == 1) {
                result.InterfaceResult200(null);
            } else {
                result.InterfaceResult500("修改收费时段失败");
            }
        } else {
            result.InterfaceResult500("修改的计费时段重复!");
        }
        return result;
    }

    @Override
    public InterfaceResult editTotalRule(JSONObject params) {
        InterfaceResult result = new InterfaceResult();
        String userId = params.getString("userId");
        ParkingFeeTotal parkingFeeTotal = JSONObject.toJavaObject(params, ParkingFeeTotal.class);
        parkingFeeTotal.setUpdateOperator(userId);
        parkingFeeTotal.setUpdateTime(Calendar.getInstance().getTime());
        int code = parkingFeeTotalMapper.updateByPrimaryKeySelective(parkingFeeTotal);
        if (code == 1) {
            result.InterfaceResult200(null);
        } else {
            result.InterfaceResult500("修改总规则失败");
        }
        return result;
    }

    @Override
    public InterfaceResult getFeePeriodTime(String marketId, String feePeriodTimeId, String userId) throws Exception {
        InterfaceResult result = new InterfaceResult();
        FeePeriodTimeExample example = new FeePeriodTimeExample();
        example.createCriteria().andIsValidEqualTo(1)
                .andMarketIdEqualTo(marketId).andIdEqualTo(feePeriodTimeId);
        List<FeePeriodTime> feePeriodTimes = feePeriodTimeMapper.selectByExample(example);
        if (null != feePeriodTimes && !feePeriodTimes.isEmpty()) {
            result.InterfaceResult200(feePeriodTimes.get(0));
        } else {
            result.InterfaceResult500("查询收费时段失败!");
        }
        return result;
    }

    private boolean checkIsExist(String marketId, FeePeriodTime feePeriodTime, int type) throws Exception {
        //查询收费时段记录,判断日期是否重合
        //type 1为新增判断 0为删除逻辑
        FeePeriodTimeExample example = new FeePeriodTimeExample();
        example.createCriteria().andIsValidEqualTo(1).andMarketIdEqualTo(marketId);
        List<FeePeriodTime> feePeriodTimes = feePeriodTimeMapper.selectByExample(example);
        boolean flag = false;
        if (null != feePeriodTimes) {
            if (type == 1) {
                //新增
                for (FeePeriodTime feePeriodTime1 : feePeriodTimes) {
                    Date startTime = DateUtils.StringToHMSDate(feePeriodTime1.getStartTime());
                    Date endTime = DateUtils.StringToHMSDate(feePeriodTime1.getEndTime());
                    Date st = DateUtils.StringToHMSDate(feePeriodTime.getStartTime());
                    if (DateUtils.isBetween(st, startTime, endTime, DateUtils.COMP_MODEL_TIME)) {
                        flag = true;
                        break;
                    }
                    if (DateUtils.isBetween(st, startTime, endTime, DateUtils.COMP_MODEL_TIME)) {
                        flag = true;
                        break;
                    }
                }
            } else if (type == 0) {
                //删除
                for (FeePeriodTime feePeriodTime1 : feePeriodTimes) {
                    if (!StringUtils.equals(feePeriodTime1.getId(), feePeriodTime.getId())) {
                        Date startTime = DateUtils.StringToHMSDate(feePeriodTime1.getStartTime());
                        Date endTime = DateUtils.StringToHMSDate(feePeriodTime1.getEndTime());
                        Date st = DateUtils.StringToHMSDate(feePeriodTime.getStartTime());
                        if (DateUtils.isBetween(st, startTime, endTime, DateUtils.COMP_MODEL_TIME)) {
                            flag = true;
                            break;
                        }
                        if (DateUtils.isBetween(st, startTime, endTime, DateUtils.COMP_MODEL_TIME)) {
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }

        return flag;
    }
}
