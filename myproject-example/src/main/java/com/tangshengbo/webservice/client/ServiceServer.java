/**
 * ServiceServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tangshengbo.webservice.client;

public interface ServiceServer extends java.rmi.Remote {
    String addObj(String arg0) throws java.rmi.RemoteException;
    void searchObj() throws java.rmi.RemoteException;
    String removeObj(String arg0) throws java.rmi.RemoteException;
    String modifyObj(String arg0) throws java.rmi.RemoteException;
}
