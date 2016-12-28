package com.tangshengbo.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {

    protected RemoteServiceImpl() throws RemoteException {
    }

    public String sayHello(String name) throws RemoteException {
        System.out.println("Hellow RMI" + name);
        return "hellow RMI Service";
    }

}
