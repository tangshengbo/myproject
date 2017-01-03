package com.tangshengbo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/11/14.
 */
public class TestLogging {

    private static  Logger logger = LoggerFactory.getLogger(TestLogging.class);

    public static void main(String[] args) {
        // 记录error信息
        logger.error("[info message]{}","kkss");
        // 记录info，还可以传入参数
        logger.info("[info message]{},{},{},{}", "abc",122);

        // 记录deubg信息
        logger.debug("[debug message]");
        // 记录trace信息
        logger.trace("[trace message]");

        System.out.println("hello world");

    }

}
