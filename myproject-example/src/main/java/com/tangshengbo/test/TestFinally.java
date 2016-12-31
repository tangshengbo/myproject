package com.tangshengbo.test;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestFinally {

    public static void main(String[] args) throws Exception {
        TestFinally testFinally = new TestFinally();
        //testFinally.urlCoding();
        //testFinally.eachSet();
        SimpleObject simpleObject = SimpleObject.getInstance();
        simpleObject.showMessage();


        ExecutorService service = Executors.newFixedThreadPool(10);
        service.shutdown();
        service.isShutdown();

        Account account = new Account();
        account.setBalance(1000);
        Lock lock = new ReentrantLock();

        Thread bank = new Thread(new Bank(account, lock));
        Thread company = new Thread(new Company(account, lock));
        bank.start();
        company.start();

        Thread.sleep(1000);
        System.out.println(account.getBalance() + "balance");


//		LinkedHashMap lhm = new LinkedHashMap();
//
//		ExecutorService service = Executors.newFixedThreadPool(10);
//		Account account = new Account();
//		account.setBalance(1000);
        /*
		 * for (int i = 0; i < 10; i++) { System.out.println("创建线程" + i);
		 * Runnable run = new Runnable() {
		 * 
		 * @Override public void run() { System.out.println("启动线程"); } }; //
		 * 在未来某个时间执行给定的命令 service.execute(run); } // 关闭启动线程 service.shutdown();
		 */
//		Bank bank = new Bank(account);
//		Company company = new Company(account);
//		Thread bankThread = new Thread(bank);
//		Thread companyThread = new Thread(company);
//		System.out.printf("Account : Final Balance: %f\n", account.getBalance());
//		companyThread.start();
//		bankThread.start();
//
//		Thread.sleep(1000);
//		System.out.printf("Account : Initial Balance: %f\n", account.getBalance());

        // 等待子线程结束，再继续执行下面的代码

        // HashMap map = new HashMap();
        // map.put(null, null);
        // Hashtable hashtable = new Hashtable<>();
        // hashtable.put(null, "fsfs");
        //

    }

    public void modifyObj(User user) {
        user = null;
    }

    public void eachSet() {
        Map map = new ConcurrentHashMap();
        Stack stack = new Stack();
        stack.push("tang");
        stack.push("sang");
        stack.push("bang");
//	    Queue queue = new ArrayDeque();
        List list = new ArrayList();
        map = Collections.synchronizedMap(new HashMap());
        map.put("23423", "fsfsd");
        map.put("13423", "fsfsd");
        map.put("33423", "fsfsd");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        //it.forEachRemaining(obj -> System.out.println("迭代集合元素：" + obj));  

    }

    public void socketCoding() {

        try {
            InetAddress address = InetAddress.getLocalHost();
            InetAddress.getByName("192.168.17.129").getHostAddress();
            System.out.println(address.getHostAddress());
            System.out.println(InetAddress.getByName("115.239.211.112").getHostAddress());

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void urlCoding() {

        try {
            URL url = new URL("http://www.baidu.com");
            System.out.println(url.getProtocol());
            System.out.println(url.getPort());
            System.out.println(url.getHost());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void totalNumber() {
        int[] arr = new int[]{8, 2, 1, 0, 3};
        int[] index = new int[]{2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3};
        String tel = "";
        for (int i : index) {
            tel += arr[i];

        }
        System.out.println("联系方式" + tel);
    }

    public void testString() {

        String string = new String();
        string.equals(null);


    }

}
