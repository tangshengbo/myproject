package com.tangshengbo.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2019/12/10
 */
public final class DateFormatUtils {

    private DateFormatUtils() {
    }

    /**
     * 锁对象
     */
    private static final Object LOCK_OBJ = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static final Map<String, ThreadLocal<SimpleDateFormat>> SDF_MAP = new HashMap<>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern 日期格式
     * @return 日期格式器对象
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> threadLocal = SDF_MAP.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (Objects.isNull(threadLocal)) {
            synchronized (LOCK_OBJ) {
                threadLocal = SDF_MAP.get(pattern);
                if (Objects.isNull(threadLocal)) {
                    threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
                    SDF_MAP.put(pattern, threadLocal);
                }
            }
        }
        return threadLocal.get();
    }

    /**
     * 日期 -> 字符串
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 格式化后文本
     */
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    /**
     * 字符串 -> 日期
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return 日期对象
     * @throws ParseException 解析异常
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }

}
