package com.tangshengbo.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Account implements Serializable {

    private static final long serialVersionUID = -5999194121092645042L;

    private Integer id;

    @SerializedName(value = "name", alternate = {"user_name", "NAME"})
    private String name;

    private String[] majoys;

    private Boolean hasGrilFriend;

    private Double money;

    private Date birthday;

    private int age;

    private Object object;

    private List<String> titleList;

    private Map<String, Object> commentMap;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, Object> getCommentMap() {
        return commentMap;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public void setCommentMap(Map<String, Object> commentMap) {
        this.commentMap = commentMap;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public String[] getMajoys() {
        return majoys;
    }

    public void setMajoys(String[] majoys) {
        this.majoys = majoys;
    }

    public Boolean getHasGrilFriend() {
        return hasGrilFriend;
    }

    public void setHasGrilFriend(Boolean hasGrilFriend) {
        this.hasGrilFriend = hasGrilFriend;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", name=" + name + ", majoys=" + Arrays.toString(majoys) + ", hasGrilFriend="
                + hasGrilFriend + ", money=" + money + ", birthday=" + birthday + "]";
    }

}
