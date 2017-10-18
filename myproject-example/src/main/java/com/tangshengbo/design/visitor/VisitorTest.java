package com.tangshengbo.design.visitor;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class VisitorTest {

    public static void main(String[] args) {
        List<Person> persons = Lists.newArrayList();
        Person man = new Man("男人", "成功");
        Person man1 = new Man("男人", "失败");
        Person man2 = new Man("男人", "恋爱");

        Person woman = new Woman("女人", "成功");
        Person woman1 = new Woman("女人", "失败");
        Person woman2 = new Woman("女人", "恋爱");

        persons.add(man);
        persons.add(man1);
        persons.add(man2);
        persons.add(woman);
        persons.add(woman1);
        persons.add(woman2);

        persons.forEach(person -> {
            System.out.println(person.getConclusion());
        });
    }
}
