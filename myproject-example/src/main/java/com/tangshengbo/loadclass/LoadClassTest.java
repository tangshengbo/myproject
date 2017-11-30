package com.tangshengbo.loadclass;

import com.tangshengbo.thread.Student;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by tangshengbo on 2017/3/1.
 */
public class LoadClassTest {

    public static void main(String[] args) throws ClassNotFoundException {
        //Class clazz = Class.forName("");
        getActualType();
    }

    public Person transmitObject() {
        Person person = new Person();
        person.setAge(10);
        person.setName("3322324234");
        return person;
    }

    private static void getActualType() {
        Type type = new ArrayList<Student>(){}.getClass().getGenericSuperclass();
        Type paramType = ((ParameterizedType) type).getActualTypeArguments()[0];
        System.out.println(paramType);
    }
}
