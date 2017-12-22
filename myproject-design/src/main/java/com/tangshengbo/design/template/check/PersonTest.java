package com.tangshengbo.design.template.check;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangShengBo on 2017-09-16.
 */
public class PersonTest {

    public static void main(String[] args) {
        PersonTest test = new PersonTest();
        Person person = new Person(2, 11);
        System.out.println(person.toString());
        test.changeField(person);
        System.out.println(person.toString());
        System.out.println(List.class.isAssignableFrom(ArrayList.class));
    }

    private void change(Person person) {
        person = new Person(1, 12);
    }

    private void changeField(Person person) {
        person.setAge(10);
    }
}
