package com.tangshengbo.design.visitor.example;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class ObjectStructure {

    private List<Person> persons = Lists.newArrayList();

    public void attach(Person person) {
        persons.add(person);
    }

    public void detach(Person person) {
        if (persons.contains(person)) {
            persons.remove(person);
        }
    }

    public void display(Action visitor) {
        for (Person person : persons) {
            System.out.println(person.accept(visitor));
        }
    }
}
