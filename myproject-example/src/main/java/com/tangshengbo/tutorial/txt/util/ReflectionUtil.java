package com.tangshengbo.tutorial.txt.util;

import com.tangshengbo.tutorial.txt.entity.TxtImportEntity;
import jodd.util.ReflectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class ReflectionUtil {

    private static String getSetMethodName(String field) {
        String str1 = field.substring(0, 1);
        String str2 = field.substring(1, field.length());
        return "set" + str1.toUpperCase() + str2;
    }

    public static Method getSetMethod(Class cls, String field) {
        return ReflectUtil.findMethod(cls, getSetMethodName(field));
    }

    public static Object newInstance(Class cls) throws ReflectiveOperationException {
        return ReflectUtil.newInstance(cls);
    }

    public static Field[] getAccessibleFields(Class cls) {
        return ReflectUtil.getAccessibleFields(cls);
    }

    /**
     * 根据返回类型获取返回值
     *
     * @param classType
     * @param column
     * @return
     */
    public static Object getValueByType(String classType, Object column, TxtImportEntity entity) {
        try {
            //过滤空和空字符串,如果基本类型null会在上层抛出,这里就不处理了
            if (column == null || StringUtils.isBlank(column.toString())) {
                return null;
            }
            if ("class java.util.Date".equals(classType) || "class java.sql.Date".equals(classType)) {
                column = DateUtils.parseDate((String) column, new String[]{entity.getFormat()});
            }
            if ("class java.lang.Boolean".equals(classType) || "boolean".equals(classType)) {
                return Boolean.valueOf(String.valueOf(column));
            }
            if ("class java.lang.Double".equals(classType) || "double".equals(classType)) {
                return Double.valueOf(String.valueOf(column));
            }
            if ("class java.lang.Long".equals(classType) || "long".equals(classType)) {
                try {
                    return Long.valueOf(String.valueOf(column));
                } catch (Exception e) {
                    //格式错误的时候,就用double,然后获取Int值
                    return Double.valueOf(String.valueOf(column)).longValue();
                }
            }
            if ("class java.lang.Float".equals(classType) || "float".equals(classType)) {
                return Float.valueOf(String.valueOf(column));
            }
            if ("class java.lang.Integer".equals(classType) || "int".equals(classType)) {
                try {
                    return Integer.valueOf(String.valueOf(column));
                } catch (Exception e) {
                    //格式错误的时候,就用double,然后获取Int值
                    return Double.valueOf(String.valueOf(column)).intValue();
                }
            }
            if ("class java.math.BigDecimal".equals(classType)) {
                return new BigDecimal(String.valueOf(column));
            }
            if ("class java.lang.String".equals(classType)) {
                return String.valueOf(column);
            }
            return column;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invoke(Object obj, Object value, Method method) throws ReflectiveOperationException {
        method.setAccessible(true);
        return method.invoke(obj, value);
    }
}
