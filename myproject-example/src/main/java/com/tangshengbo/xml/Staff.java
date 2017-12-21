package com.tangshengbo.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tangshengbo on 2017/12/21.
 */
@XmlRootElement(name = "staff")
@XStreamAlias("staff")
public class Staff {
    @XStreamAlias("name")
    private String name;
    @XStreamAlias("age")// 职员名称
    private int age;
    @XStreamAlias("smoker")// 职员年龄
    private boolean smoker; // 是否为烟民

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSmoker() {
        return smoker;
    }

    @XmlAttribute
    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", smoker=" + smoker +
                '}';
    }
}