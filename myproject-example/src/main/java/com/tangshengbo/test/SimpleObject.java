package com.tangshengbo.test;

public class SimpleObject {
	private static SimpleObject simpleObject = new SimpleObject();
	private SimpleObject(){}
	public static SimpleObject getInstance(){
		return simpleObject;
	}
	public void showMessage(){
		System.out.println("Hello world");
	}

}
