package com.tangshengbo.webservice.server;

import javax.jws.WebService;
import java.util.ArrayList;

@WebService(targetNamespace = "http://client.webservice.example.com/")
public class ServiceServer {

    private static final ArrayList LIST = new ArrayList();

    public String addObj(String obj) {
        System.out.println("添加对象............");
        LIST.add(obj);
        System.out.println("添加对象............OK");
        return "OK";
    }

    public String modifyObj(String obj) {
        System.out.println("修改对象............OK");
        return "OK";
    }

    public String removeObj(String obj) {
        System.out.println("删除对象............");
        LIST.remove(obj);
        System.out.println("删除对象............OK");
        return "OK";
    }

    public void searchObj() {
        for (Object object : LIST) {
            System.out.println("查询对象。。。。。。。。。。" + object);
        }

    }


}
