package com.tangshengbo.refactor.skill.extractclass;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class Person {

    private String name;

    private TelephoneNumber telephoneNumber;

    public Person(String name , TelephoneNumber telephoneNumber) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber.getTelephoneNumber();
    }

}
