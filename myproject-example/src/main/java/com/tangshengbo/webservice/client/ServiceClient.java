package com.tangshengbo.webservice.client;

import java.rmi.RemoteException;


public class ServiceClient {

	public static void main(String[] args) throws  RemoteException {
//	    Endpoint.publish("http://localhost:9003/Service/ServiceServer", new ServiceServer());
		
//		System.out.println("success");
//		String endpoint = "http://localhost:9002/Service/Servicehello?WSDL";
//		endpoint = "http://localhost:9003/Service/SeviceParamter?wsdl";
//		//直接引用远程的wsdl文件
//		//以下都是套路 
//		Service service = new Service();
//		Call call = (Call) service.createCall();
//		call.setTargetEndpointAddress(new java.net.URL(endpoint));
//		
//		call.setOperationName(new QName("http://webservice.example.com/", "serviceByWeb"));//WSDL里面描述的接口名称
//		call.addParameter(new QName("http://webservice.example.com/",  
//				"service"), XMLType.XSD_INTEGER, ParameterMode.IN);//接口的参数
//		call.setReturnType(XMLType.XSD_INTEGER);//设置返回类型 
//		String temp = "axis";
//		
//		Object[] paramter = new Object[] {"axis"};
//		Object result = (Object)call.invoke(paramter);
//		//给方法传递参数，并且调用方法
//		System.out.println("result is "+result);
		//ServiceServer server = new ServiceServerServiceLocator().getServiceServerPort();
		//System.out.println(server.addObj("tang"));
//		System.err.println(server.removeObj("tang"));
//		server.searchObj();
		

	}
}
