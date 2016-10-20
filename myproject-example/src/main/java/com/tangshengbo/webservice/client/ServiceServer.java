/**
 * ServiceServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tangshengbo.webservice.client;

public interface ServiceServer extends java.rmi.Remote {
    public String addObj(String arg0) throws java.rmi.RemoteException;
    public void searchObj() throws java.rmi.RemoteException;
    public String removeObj(String arg0) throws java.rmi.RemoteException;
    public String modifyObj(String arg0) throws java.rmi.RemoteException;
}
