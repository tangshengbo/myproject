package com.tangshengbo.tutorial.txt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TxtTitle {

    String value();

    String format() default "yyyy-MM-dd HH:mm:ss";

    String defaultValue() default "";
}
