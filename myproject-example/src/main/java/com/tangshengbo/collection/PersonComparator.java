package com.tangshengbo.collection;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/27.
 */
public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }

    public static void main(String[] args) {
        List<Person> persons = Lists.newArrayList();
        persons.add(new Person("xujian", 20));
        persons.add(new Person("xiewei", 10));
        System.out.println("排序前");
        for (Person person : persons) {
            System.out.print(person.getName() + ":" + person.getAge());
        }
        Collections.sort(persons, new PersonComparator());
        System.out.println("------------------------------");
        System.out.println("\n排序后");
        for (Person person : persons) {
            System.out.print(person.getName() + ":" + person.getAge());
        }
    }
}
