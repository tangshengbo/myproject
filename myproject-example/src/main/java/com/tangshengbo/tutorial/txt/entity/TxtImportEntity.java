package com.tangshengbo.tutorial.txt.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class TxtImportEntity {

    /**
     * 对应name
     */
    protected String name;

    /**
     * 导出日期格式
     */
    private String format;

    /**
     * 对应字段
     */
    private Field field;

    /**
     * set/get方法
     */
    private Method method;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
