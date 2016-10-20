package com.tangshengbo.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RemoteTest {
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		    int port = 1099;
	        String url = "rmi://localhost:1099/demo.zookeeper.rmi.server.HelloServiceImpl";
	        LocateRegistry.createRegistry(port);
	        Naming.rebind(url, new RemoteServiceImpl());
	        System.out.println("OK........................");
	        
		
		
	}

}
