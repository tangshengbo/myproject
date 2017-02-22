package com.tangshengbo.annotation;

import java.lang.reflect.Method;

/**
 * Created by tangshengbo on 2017/2/21.
 */
public class AnnotationTest {

    public static void main(String[] args) {

        try {
            Description description;
            Class c = Class.forName("com.tangshengbo.annotation.People");

            boolean present = c.isAnnotationPresent(Description.class);
            if (present) {
                description = (Description) c.getAnnotation(Description.class);
                printAnnotationValue(description);
            }

            Method[] methods = c.getMethods();
            for (Method method : methods) {
                present = method.isAnnotationPresent(Description.class);
                if (present) {
                    description = method.getAnnotation(Description.class);
                   printAnnotationValue(description);
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void printAnnotationValue(Description description) {
        System.out.println(description.age());
        System.out.println(description.kind());
        String[] names = description.name();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
