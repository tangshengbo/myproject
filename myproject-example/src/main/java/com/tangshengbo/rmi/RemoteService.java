package com.tangshengbo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 定义RMI接口
 * @author TangShengBo
 *
 */
public interface RemoteService extends Remote {

	 String sayHello(String name) throws RemoteException;

}
