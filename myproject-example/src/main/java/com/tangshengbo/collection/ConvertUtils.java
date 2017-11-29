package com.tangshengbo.collection;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Tangshengbo on 2017/11/16.
 */
public final class ConvertUtils {

    private ConvertUtils() {
    }

    /**
     * 将List转换成Map 使用String作为key
     *
     * @param list      原数据
     * @param fieldName key字段名
     * @param <T>       目标数据
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> toMap(List<T> list, String fieldName) {
        Map<String, T> map = Maps.newHashMapWithExpectedSize(list.size());
        String keyMethodName = getMethodName(fieldName);
        Method method = null;
        Class c;
        try {
            c = list.get(0).getClass();
            if (!Objects.equals(c, String.class) && c != null) {
                method = c.getMethod(keyMethodName);
                method.setAccessible(true);
            }

        } catch (NoSuchMethodException e) {
            System.out.println(String.format("List转换成Map 出现异常: %s", e));
        }
        try {
            for (T t : list) {
                if (t instanceof String) {
                    map.put((String) t, t);
                } else {
                    assert method != null;
                    String key = (String) method.invoke(t);
                    map.put(key, t);
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("List转换成Map 出现异常: %s", e));
            throw new RuntimeException("List转换成Map 出现异常", e);
        }
        return map;
    }

    public static <T> Map<String, T> toMapByLambda(List<T> list, Function<T, String> f) {
        return list.stream()
                .collect(LinkedHashMap<String, T>::new,
                        (m, c) ->
                                m.put(f.apply(c), c),
                        (m, u) -> {
                        });
    }

    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    private static String getMethodName(String fieldName) {
        if (StringUtils.isEmpty(fieldName)) {
            return "";
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否是大写字母
     *
     * @param c
     * @return
     */
    private static boolean isUp(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

    /**
     * java对象属性转换为数据库字段，如userName-->user_name
     *
     * @param field
     * @return
     */
    public static String fieldToColumn(String field) {
        if (StringUtils.isEmpty(field)) {
            return "";
        }
        char[] chars = field.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (isUp(c)) {
                sb.append("_").append(String.valueOf(c).toLowerCase());
            } else {
                sb.append(c);
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 将数据库字段转换为java属性，如user_name-->userName
     *
     * @param column 字段名
     * @return
     */
    public static String columnToField(String column) {
        if (StringUtils.isEmpty(column)) {
            return "";
        }
        column = column.toLowerCase();
        char[] chars = column.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                int j = i + 1;
                if (j < chars.length) {
                    sb.append(String.valueOf(chars[j]).toUpperCase());
                    i++;
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
