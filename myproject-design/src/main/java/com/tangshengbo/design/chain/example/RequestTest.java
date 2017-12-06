package com.tangshengbo.design.chain.example;

import java.util.Date;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class RequestTest {

    public static void main(String[] args) {
        Manager manager = getChainOfManager();

        Request request1 = new Request("请假", "小唐请假", 7, new Date());
        manager.requestApplication(request1);

        Request request2 = new Request("加薪", "小唐加薪", 1000, new Date());
        manager.requestApplication(request2);
    }

    private static Manager getChainOfManager() {
        Manager commonManager = new CommonManager("经理");
        Manager majordomo = new Majordomo("总监");
        Manager generalManager = new GeneralManager("总经理");
        Manager boss = new Boss("老板");

        //设置上级责任链
        commonManager.setSuperior(majordomo);
        majordomo.setSuperior(generalManager);
        generalManager.setSuperior(boss);
        return commonManager;
    }
}
