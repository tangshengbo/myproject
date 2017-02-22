package com.tangshengbo.annotation;

import java.lang.annotation.*;

/**
 * Created by tangshengbo on 2017/2/21.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {

    String[] name();

    int age() default 18;

    PeopleKind kind() default PeopleKind.YELLOW;
}
