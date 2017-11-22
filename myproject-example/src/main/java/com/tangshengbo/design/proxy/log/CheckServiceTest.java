package com.tangshengbo.design.proxy.log;

/**
 * Created by TangShengBo on 2017-09-12.
 */
public class CheckServiceTest {

    public static void main(String[] args) {
        CheckLogProxy logProxy = new CheckLogProxy();
        CheckService checkService = (CheckService)logProxy.getInstance(new CheckServiceImpl());
        checkService.recharge("D://tang.txt");
        checkService.extract("D://xxx.txt");
    }
}
