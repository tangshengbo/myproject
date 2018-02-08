package com.tangshengbo.datetime;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2017/8/25.
 */
public final class DateUtils {

    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String ISO_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO_DATE_PATTERN = "yyyy-MM-dd";

    private static final long ONE_DAY_TIME = 1000 * 60 * 60 * 24L;



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
        return DateUtils.formatDate(DateUtils.dateFormat(date, srcPattern), destPattern);
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
}
