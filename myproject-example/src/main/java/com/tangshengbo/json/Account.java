package com.tangshengbo.json;

import java.util.Arrays;
import java.util.Date;

public class Account {

    private Integer id;
    // @SerializedName("NAME")
    private String name;

    private String[] majoys;

	private Boolean hasGrilFriend;

	private Double money;

	private Date birthday;

	private int age;

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
