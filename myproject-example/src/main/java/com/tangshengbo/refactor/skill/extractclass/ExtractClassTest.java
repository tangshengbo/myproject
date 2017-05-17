package com.tangshengbo.refactor.skill.extractclass;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class ExtractClassTest {

    public static void main(String[] args) {
        TelephoneNumber telephoneNumber = new TelephoneNumber("021", "88888888");
        Person person = new Person("tang", telephoneNumber);
        System.out.println(person.getTelephoneNumber());

    }
}
