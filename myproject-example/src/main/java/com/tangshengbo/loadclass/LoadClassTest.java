package com.tangshengbo.loadclass;

import org.apache.commons.lang.StringUtils;

/**
 * Created by tangshengbo on 2017/3/1.
 */
public class LoadClassTest {

    public static void main(String[] args) throws ClassNotFoundException {

        //Class clazz = Class.forName("");

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        //LoadClassTest.class.getClass();
        Thread.currentThread().isInterrupted();

        //Class clazz = getClass().getClassLoader().loadClass("");
        int count = StringUtils.countMatches("lllslslllslllsllsllls", "s");
        System.out.println(count);
        LoadClassTest classTest = new LoadClassTest();
        Person person = new Person("lls", 123);
        Person newPerson = classTest.transmitObject();
        System.out.println(person.hashCode() + "\t" + newPerson.hashCode());
        System.out.println(person.toString());
    }

    public Person transmitObject() {
        Person person = new Person();
        person.setAge(10);
        person.setName("3322324234");
        //跳出多重循环
        label:
        for (int i = 0; i < 100; i++) {
            System.out.println("i:" + i);
            for (int j = 0; j < 100; j++) {
                System.out.println("j:" + j);
                break label;
            }
        }
        System.out.println("label:break");
        return person;
    }
}
