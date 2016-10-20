package com.tangshengbo.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.xml.ws.Service;

public class RemoteClientTest {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String url = "rmi://localhost:1099/demo.zookeeper.rmi.server.HelloServiceImpl";
		RemoteService helloService = (RemoteService) Naming.lookup(url);
		for (int i = 0; i <= 10000; i++) {
			String result = helloService.sayHello("Jack");
			System.out.println(result);
		}
	}

}
