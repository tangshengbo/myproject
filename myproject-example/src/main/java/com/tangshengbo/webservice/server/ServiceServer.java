package com.tangshengbo.webservice.server;

import java.util.ArrayList;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(targetNamespace="http://client.webservice.example.com/")
public class ServiceServer {
	private static final ArrayList list = new ArrayList();

	public String addObj(String obj) {
		System.out.println("添加对象............");
		list.add(obj);
		System.out.println("添加对象............OK");
		return "OK";
	}

	public String modifyObj(String obj) {
		System.out.println("修改对象............OK");
		return "OK";
	}

	public String removeObj(String obj) {
		System.out.println("删除对象............");
		list.remove(obj);
		System.out.println("删除对象............OK");
		return "OK";
	}

	public void searchObj() {
		for (Object object : list) {
			System.out.println("查询对象。。。。。。。。。。" + object);
		}

	}

	

}
