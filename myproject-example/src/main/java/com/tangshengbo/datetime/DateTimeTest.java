package com.tangshengbo.datetime;


import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Administrator on 2016/11/30.
 */
public class DateTimeTest {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeTest.class);

    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String ISO_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO_DATE_PATTERN = "yyyy-MM-dd";

    public static void main(String[] args) {
//        try {
//            Date begin = DateUtils.parseDate("2016-08-22", new String[]{"yyyy-MM-dd"});
//            Date end = DateUtils.parseDate("2016-08-22", new String[]{"yyyy-MM-dd"});
//            List<String> dates = formatDate(begin, end);
//            int count = 0;
//            for (String date : dates) {
//                System.out.println(date);
//                count++;
//            }
//            System.out.println(count);
//
//            Date date = DateUtils.parseDate("2017-08-27", new String[]{"yyyy-MM-dd"});
//            String formatDate = formatDate(DateUtils.addDays(new Date(), -5));
//            System.out.println(formatDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
////        DateTimeTest dateTimeTest = new DateTimeTest();
//        dateDiff("2015-11-25 10:12", "2017-9-6 20:52", "yyyy-MM-dd HH:mm");
        isSameDay();

    }

    @Test
    public void testDefaultDateFormat() {
        logger.info("yyyyMMdd:{}", defaultDateFormat("20171217"));
        logger.info("yyyyMMddHHmmss:{}", defaultDateFormat("20171217121910"));
        logger.info("yyyy-MM-dd HH:mm:ss:{}", defaultDateFormat("2017-12-17 12:19:10"));
        logger.info("yyyy-MM-dd:{}", defaultDateFormat("2017-12-17"));
    }

    public static Date defaultDateFormat(String dateStr) {
        Date date;
        try {
            String[] patterns = new String[]{DEFAULT_DATE_FORMAT, DATE_PATTERN, ISO_DATETIME_PATTERN, ISO_DATE_PATTERN};
            date = DateUtils.parseDate(dateStr, patterns);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static void dateDiff(String startTime, String endTime, String format) {
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
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");
//            System.out.println(hour + "小时" + min + "分钟" + sec + "秒。");
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

    private static void isSameDay() {
        try {
            Date date1 = DateUtils.parseDate("2017-10-20 00:00:00", new String[]{"yyyy-MM-dd HH:mm:ss"});
            Date date2 = DateUtils.parseDate("2017-10-20 0:33:12", new String[]{"yyyy-MM-dd HH:mm:ss"});
            System.out.println(DateUtils.isSameDay(date1, date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static final List<LocalDate> dateCache = new ArrayList<>();


    /**
     * 通过俩个日期获取中间的日期的集合
     *
     * @param startLocalDate 开始日期
     * @param endLocalDate   结束日期
     * @return 日期集合
     */
    public static List<LocalDate> getDateIntervalList(LocalDate startLocalDate, LocalDate endLocalDate) {
        if (Objects.isNull(startLocalDate) || Objects.isNull(endLocalDate)) {
            return Collections.emptyList();
        }
        if (dateCache.isEmpty()) {
            List<LocalDate> daysBetweenDates = getDaysBetweenDates(startLocalDate, endLocalDate);
            dateCache.addAll(daysBetweenDates);
            return daysBetweenDates;
        }
        LocalDate minLocalDate = dateCache.get(0);
        LocalDate maxLocalDate = dateCache.get(dateCache.size() - 1);
        if (minLocalDate.compareTo(startLocalDate) > 0) {
            dateCache.addAll(0, getDaysBetweenDates(
                    startLocalDate, minLocalDate.minusDays(1)));
        }
        if (maxLocalDate.compareTo(endLocalDate) < 0) {
            dateCache.addAll(getDaysBetweenDates(maxLocalDate.plusDays(1), endLocalDate));
        }

        int startIndex = dateCache.indexOf(startLocalDate);
        int endIndex = dateCache.indexOf(endLocalDate);
        return dateCache.subList(startIndex, endIndex + 1);
    }

    /**
     * 获取日期区间之间所有日期集合
     *
     * @param startLocalDate 开始日期
     * @param endLocalDate   结束日期
     * @return 日期区间之间所有日期集合
     */
    private static List<LocalDate> getDaysBetweenDates(LocalDate startLocalDate, LocalDate endLocalDate) {
        List<LocalDate> dateList = new ArrayList<>();
        for (LocalDate localDate = startLocalDate; localDate.compareTo(endLocalDate) <= 0;
             localDate = localDate.plusDays(1)) {
            dateList.add(localDate);
        }
        return dateList;
    }
}
