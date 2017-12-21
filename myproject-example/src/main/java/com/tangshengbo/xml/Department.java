package com.tangshengbo.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/12/21.
 */
@XmlRootElement(name = "department")
@XStreamAlias("department")
public class Department {

    @XStreamAlias("name")
    private String name;

    @XStreamAlias("staffs")
    private List<Staff> staffs;

    private String sign;

    public String getSign() {
        return sign;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    @XmlElement(name = "staff")
    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    @XmlElement
    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", staffs=" + staffs +
                ", sign='" + sign + '\'' +
                '}';
    }
}