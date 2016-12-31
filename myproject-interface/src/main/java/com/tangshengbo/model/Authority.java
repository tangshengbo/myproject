package com.tangshengbo.model;

public class Authority {

	public static final String AUTHORITY_PREFIX = "ROLE_";

	private String id;

	private String name;

	private String url;

	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authority(String id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public String getPrefixedName() {
		return AUTHORITY_PREFIX + name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
