package com.tangshengbo.csv;

/**
 * 用来存储Excel标题的对象，通过该对象可以获取标题和方法的对应关系
 * add by tangshengbo
 */
public class CsvHeader implements Comparable<CsvHeader>{
	/**
	 * excel的标题名称
	 */
	private String title;
	/**
	 * 每一个标题的顺序
	 */
	private int order;
	/**
	 * 说对应方法名称
	 */
	private String fieldName;

	/**
	 * 字段对应的类型
	 */
	private String type;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	
	public int compareTo(CsvHeader o) {
		return order>o.order?1:(order<o.order?-1:0);
	}
	public CsvHeader(String title, int order, String fieldName,String type) {
		super();
		this.title = title;
		this.order = order;
		this.fieldName = fieldName;
		this.type = type;
	}
	@Override
	public String toString() {
		return "ExcelHeader [title=" + title + ", order=" + order
				+ ", methodName=" + fieldName + "]";
	}


	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
