package com.tangshengbo.rabbit.test.two;

public class Test {

	public static void main(String[] args) throws Exception {
		Receiver receiver = new Receiver("testQueue");
		Thread receiverThread = new Thread(receiver);
		receiverThread.start();
		Sender sender = new Sender("testQueue");
		for (int i = 0; i < 5; i++) {
			MessageInfo messageInfo = new MessageInfo();
			messageInfo.setChannel("com.tangshengbo.service");
			messageInfo.setContent("msg" + i);
			sender.sendMessage(messageInfo);
            System.out.println("");
        }
	}
}
