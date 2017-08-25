package com.tangshengbo.datetime;


import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class DateTimeTest {

    public static void main(String[] args) {
        try {
            Date begin = DateUtils.parseDate("2016-08-22", new String[]{"yyyy-MM-dd"});
            Date end = DateUtils.parseDate("2016-08-22", new String[]{"yyyy-MM-dd"});
            List<String> dates = formatDate(begin, end);
            int count = 0;
            for (String date : dates) {
                System.out.println(date);
                count++;
            }
            System.out.println(count);

            Date date = DateUtils.parseDate("2017-08-27", new String[]{"yyyy-MM-dd"});
            String formatDate = formatDate(DateUtils.addDays(new Date(), -5));
            System.out.println(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        DateTimeTest dateTimeTest = new DateTimeTest();
//        dateTimeTest.dateDiff("2016-8-23 10:12", "2010-8-23 20:52", "yyyy-MM-dd HH:mm");
    }

    public void dateDiff(String startTime, String endTime, String format) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60; //一天的毫秒数
        long nh = 1000 * 60 * 60; //一小时的毫秒数
        long nm = 1000 * 60; //一分钟的毫秒数
        long ns = 1000; //一秒钟的毫秒数
        long diff;
        try {
            //获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff / nd; //计算差多少天
            long hour = diff % nd / nh; //计算差多少小时
            long min = diff % nd % nh / nm; //计算差多少分钟
            long sec = diff % nd % nh % nm / ns; //计算差多少秒
            //输出结果
            //System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");
            System.out.println(hour + "小时" + min + "分钟" + sec + "秒。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> formatDate(Date begin, Date end) {
        List<String> dates = new ArrayList<>();
        if (DateUtils.isSameDay(begin, end)) {
            System.out.println("日期不能相同");
            return dates;
        }

        if (begin.getTime() > end.getTime()) {
            System.out.println("前一个日期不能大于后一日期");
            return dates;
        }

        long beginTime = begin.getTime();
        long endTime = end.getTime();
        long oneDayTime = 1000 * 60 * 60 * 24L;
        long currentTIme = beginTime;
        while (currentTIme <= endTime) {
            dates.add(FastDateFormat.getInstance("yyyyMMdd")
                    .format(new Date(currentTIme)));
            currentTIme += oneDayTime;
        }
        return dates;
    }

    private static String formatDate(Date date) {
        long time = date.getTime();
        if (time >= new Date().getTime()) {
            throw new IllegalArgumentException("日期不能大于当前日期");
        }
        return FastDateFormat.getInstance("yyyyMMdd")
                .format(new Date(time));
    }
}
