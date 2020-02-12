package com.tangshengbo.datetime;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2017/8/25.
 */
public final class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String ISO_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO_DATE_PATTERN = "yyyy-MM-dd";

    private static final long ONE_DAY_TIME = 1000 * 60 * 60 * 24L;

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);

    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);



    /**
     * 获取执行日期date 为空默认取前一天
     *
     * @param dateStr 日期字符串
     * @return 日期类型
     */
    public static java.sql.Date getExecuteDate(String dateStr) {
        LocalDate localDateTime;
        if (StringUtils.isBlank(dateStr)) {
            //默认是前一天
            localDateTime = LocalDate.now().minusDays(1);
        } else {
            localDateTime = LocalDate.parse(dateStr, DATE_FORMATTER);
        }
        return java.sql.Date.valueOf(localDateTime);
    }

    /**
     * 获取减去指定天数日期
     *
     * @param date   日期
     * @param amount 减去的天数
     * @return 日期类型
     */
    public static java.sql.Date minusDays(java.sql.Date date, int amount) {
        long time = org.apache.commons.lang.time.DateUtils.addDays(date, -amount).getTime();
        return new java.sql.Date(time);
    }

    /**
     * 获取当前日期字符格式
     *
     * @return 日期字符串 (yyyy-MM-dd)
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }


    /**
     * 获取执行日期
     *
     * @param dateTimeStr 日期字符串
     * @return 日期类型
     */
    public static Timestamp getDateTime(String dateTimeStr) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * 获取当前日期字符格式
     *
     * @return 日期字符串 (yyyy-MM-dd HH:mm:ss)
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * 获取当前日期字符格式
     *
     * @param timestamp 时间类型
     * @return 日期字符串 (yyyy-MM-dd HH:mm:ss)
     */
    public static String getDateTimeStr(Timestamp timestamp) {
        return timestamp.toLocalDateTime().format(DATETIME_FORMATTER);
    }

    public static List<String> formatDate(Date begin, Date end) {
        List<String> dates = new ArrayList<>();
        long beginTime = begin.getTime();
        long endTime = end.getTime();
        if (beginTime > endTime) {
            throw new IllegalArgumentException("前一个日期不能大于后一日期");
        }

        long currentTIme = beginTime;
        while (currentTIme <= endTime) {
            dates.add(FastDateFormat.getInstance(DATE_PATTERN)
                    .format(new Date(currentTIme)));
            currentTIme += ONE_DAY_TIME;
        }
        return dates;
    }

    /**
     * 转换日期格式 示例(yyyyMMdd -> yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String convert(String date, String srcPattern, String destPattern) {
        return DateUtil.formatDate(DateUtil.dateFormat(date, srcPattern), destPattern);
    }

    public static String formatDate() {
        return FastDateFormat.getInstance(DATE_PATTERN)
                .format(getPreviousDay());
    }

    public static String formatDate(Date date, String pattern) {
        return FastDateFormat.getInstance(pattern)
                .format(date);
    }

    public static boolean isLatestWeek(String date) {
        return isLatestWeek(defaultDateFormat(date));
    }

    /**
     * 当前日期前一天
     *
     * @return
     */
    private static Date getPreviousDay() {
        return DateTime.now().minusDays(1).toDate();
    }

    /**
     * 当前日期前一周日期
     *
     * @return
     */
    private static Date getPreviousWeek() {
        return DateTime.now().minusDays(7).toDate();
    }

    /**
     * 当前日期某是否是在当前时间的一周之内
     *
     * @param date
     * @return
     */
    public static boolean isLatestWeek(Date date) {
        return date.getTime() > getPreviousWeek().getTime();
    }

    public static Date defaultDateFormat(String dateStr) {
        Date date = dateFormat(dateStr, DEFAULT_DATE_FORMAT);
        if (Objects.isNull(date)) {
            date = dateFormat(dateStr, DATE_PATTERN);
        }
        return date;
    }


    /**
     * 计算当前日期与{@code endDate}的间隔天数
     *
     * @param endDate
     * @return 间隔天数
     */
    public static long until(LocalDate endDate) {
        return LocalDate.now().until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 计算日期{@code startDate}与{@code endDate}的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return 间隔天数
     */
    public static long until(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }


    public static Date dateFormat(String dateStr, String format) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static long getDifferenceDays(Date minuend, Date subtrahend) {
        long diff = minuend.getTime() - subtrahend.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static long getDifferenceHours(Date minuend, Date subtrahend) {
        long diff = minuend.getTime() - subtrahend.getTime();
        return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
    }


    @Test
    public void testTruncate() {
        Date current = new Date();
        DateUtils.truncate(current, Calendar.YEAR); // 2017-01-01 00:00:00
        DateUtils.truncate(current, Calendar.MONTH); // 2017-06-01 00:00:00
        DateUtils.truncate(current, Calendar.HOUR_OF_DAY); // 2017-06-04 00:00:00
        DateUtils.truncate(current, Calendar.DAY_OF_MONTH); // 2017-06-04 00:00:00
        DateUtils.truncate(current, Calendar.HOUR); // 2017-06-04 00:00:00
        DateUtils.truncate(current, Calendar.MINUTE); // 2017-06-04 00:56:00
        DateUtils.truncate(current, Calendar.SECOND); // 2017-06-04 00:56:05
        logger.info("{}", DateFormatUtils.format(DateUtils.truncate(current, Calendar.MINUTE), ISO_DATETIME_PATTERN));
    }

    @Test
    public void testCeiling() {
        Date current = new Date();
        DateUtils.ceiling(new Date(), Calendar.YEAR); // 2018-01-01 00:00:00
        DateUtils.ceiling(new Date(), Calendar.MONTH); // 2017-07-01 00:00:00
        DateUtils.ceiling(new Date(), Calendar.HOUR_OF_DAY); // 2017-06-04 02:00:00
        DateUtils.ceiling(new Date(), Calendar.DAY_OF_MONTH); // 2017-06-05 00:00:00
        DateUtils.ceiling(new Date(), Calendar.HOUR); // 2017-06-04 02:00:00
        DateUtils.ceiling(new Date(), Calendar.MINUTE); // 2017-06-04 01:03:00
        DateUtils.ceiling(new Date(), Calendar.SECOND); // 2017-06-04 01:02:32
        logger.info("{}", DateFormatUtils.format(DateUtils.ceiling(current, Calendar.MINUTE), ISO_DATETIME_PATTERN));
    }

    @Test
    public void testTimestamp() {
        Timestamp dateTime = DateUtil.getDateTime(DateUtil.getCurrentDateTimeStr());
        logger.info("{}", dateTime.getTime());

        System.out.println(Math.abs(DateUtil.until(LocalDate.of(2019, 9, 23))));

        System.out.println(LocalDate.now().minusDays(30));

    }

}

