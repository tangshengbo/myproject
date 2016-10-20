package com.tangshengbo.model;

public class User {
	
	private String name;
	private Integer age;
	
	private Contact contact;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", contact=" + contact + "]";
	}
	
	
	

}
