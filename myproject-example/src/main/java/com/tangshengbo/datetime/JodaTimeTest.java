package com.tangshengbo.datetime;

import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.*;

import java.util.Date;

/**
 * Created by Tangshengbo on 2017/9/6.
 */
public class JodaTimeTest {

    public static void main(String[] args) {

        DateTime dateTime = new DateTime();
        dateTime = dateTime.minusDays(100);
        System.out.println(dateTime.toString("yyyy-MM-dd"));



        System.out.println(DateTime.now().minusDays(3).toDate());
        System.out.println(formatDate());

        DateTime begin = new DateTime("2015-11-25");
        DateTime end = new DateTime();
        Duration d = new Duration(new DateTime("2015-11-25"), new DateTime());
        System.out.println(d.getStandardDays());
        Period p = new Period(begin, end, PeriodType.days());

//        diffDate(begin, end);
//
//        calculate();

    }

    private static String formatDate() {
        return FastDateFormat.getInstance("yyyyMMdd")
                .format(getPreviousDay());
    }

    private static Date getPreviousDay() {
        return DateTime.now().plusDays(-1).toDate();
    }

    private  static void diffDate(Date begin, Date end) {
        DateTime beginTime = new DateTime(begin);
        DateTime endTime = new DateTime(end);
        System.out.println("时间相差:");
        int day = Days.daysBetween(beginTime, endTime).getDays();
        int hour = Hours.hoursBetween(beginTime, endTime).getHours() % 24;
        int min = Minutes.minutesBetween(beginTime, endTime).getMinutes() % 60;
        int sec = Seconds.secondsBetween(beginTime, endTime).getSeconds() % 60;
        System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");
    }

    private static void calculate() {
        DateTime now = new DateTime();
        System.out.println("昨天:" + now.minusDays(1).toString("yyyy-MM-dd"));
        System.out.println("明天:" + now.plusDays(1).toString("yyyy-MM-dd"));
        System.out.println("一个月前:" + now.minusMonths(1).toString("yyyy-MM-dd"));
        System.out.println("三个月后:" + now.plusMonths(3).toString("yyyy-MM-dd"));
        System.out.println("两年前:" + now.minusYears(2).toString("yyyy-MM-dd"));
        System.out.println("五年后:" + now.plusYears(5).toString("yyyy-MM-dd"));
    }

    private static void create() {
        // 方法一：取系统点间
        DateTime dt1 = new DateTime();

        // 方法二：通过java.util.Date对象生成
        DateTime dt2 = new DateTime(new Date());

        // 方法三：指定年月日点分秒生成(参数依次是：年,月,日,时,分,秒,毫秒
        DateTime dt3 = new DateTime(2012, 5, 20, 13, 14, 0, 0);

        // 方法四：ISO8601形式生成
        DateTime dt4 = new DateTime("2012-05-20");
        DateTime dt5 = new DateTime("2012-05-20T13:14:00");

        // 只需要年月日的时候
        LocalDate localDate = new LocalDate(2016, 9, 6);// September 6, 2009

        // 只需要时分秒毫秒的时候
        LocalTime localTime = new LocalTime(13, 30, 26, 0);// 1:30:26PM
    }

}
