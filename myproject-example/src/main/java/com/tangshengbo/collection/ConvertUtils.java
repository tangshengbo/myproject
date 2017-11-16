package com.tangshengbo.collection;

import com.google.common.collect.Maps;
import jodd.util.ReflectUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by Tangshengbo on 2017/11/16.
 */
public final class ConvertUtils {

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
        Map<String, T> map = Maps.newHashMapWithExpectedSize(list.size());
        try {
            for (T t : list) {
                if (t instanceof String) {
                    map.put((String) t, t);
                } else {
                    Method method = ReflectUtil.findMethod(t.getClass(), keyMethodName);
                    String key = (String) method.invoke(t);
                    map.put(key, t);
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("List转换成Map 出现异常: %s", e));
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
