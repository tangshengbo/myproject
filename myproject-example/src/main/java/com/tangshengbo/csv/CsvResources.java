package com.tangshengbo.csv;

import java.lang.annotation.*;

/**
 * 用来在对象的get方法上加入的annotation，
 * 通过该annotation说明某个属性所对应的标题.
 * add by tangshengbo
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface CsvResources {
    /**
     * 属性的标题名称
     * @return
     */
    String title();
    /**
     * 在excel的顺序
     * @return
     */
    int order() default 9999;

    /**
     * 字段数据类型
     * @return
     */
    String type() default "";

}