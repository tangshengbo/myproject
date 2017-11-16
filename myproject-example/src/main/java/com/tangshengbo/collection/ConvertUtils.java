package com.tangshengbo.collection;

import jodd.util.ReflectUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tangshengbo on 2017/11/16.
 */
public final class ConvertUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    /**
     * 将List转换成Map 使用String作为key
     *
     * @param list      原数据
     * @param fieldName key字段名
     * @param <T>       目标数据
     * @return
     */
    public static <T> Map<String, T> convertMap(List<T> list, String fieldName) {
        String keyMethodName = parGetName(fieldName);
        Map<String, T> map = new HashMap<>(list.size());
        try {
            for (T t : list) {
                Method method = ReflectUtil.findMethod(t.getClass(), keyMethodName);
                String key = (String) method.invoke(t);
                map.put(key, t);
            }
        } catch (Exception e) {
            logger.error("List转换成Map 出现异常: {}", e);
        }
        return map;
    }

    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    private static String parGetName(String fieldName) {
        if (StringUtils.isEmpty(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }
}
