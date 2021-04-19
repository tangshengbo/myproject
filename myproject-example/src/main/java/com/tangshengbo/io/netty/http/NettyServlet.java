package com.tangshengbo.io.netty.http;

public abstract class NettyServlet {
	
	public void service(NettyRequest request, NettyResponse response) throws Exception{
		
		//由service方法来决定，是调用doGet或者调用doPost
		if("GET".equalsIgnoreCase(request.getMethod())){
			doGet(request, response);
		}else{
			doPost(request, response);
		}

	}
	
	public abstract void doGet(NettyRequest request, NettyResponse response) throws Exception;
	
	public abstract void doPost(NettyRequest request, NettyResponse response) throws Exception;
	
}
