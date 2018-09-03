package com.tangshengbo.arithmetic.loadbalance;

import cn.hutool.core.util.RandomUtil;
import jodd.util.ThreadUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Tangshengbo on 2018/4/13.
 */
public class RandomTest {

    private static final Logger logger = LoggerFactory.getLogger(RandomTest.class);

    @Test
    public void testGenerate() {
        System.out.println(String.class.isAssignableFrom(Object.class));
        System.out.println(Iterable.class.isAssignableFrom(Collection.class));
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        //生成6位验证码
        for (int i = 0; i < 1000; i++) {
//            logger.info("{}", Math.random() * 9000);
            int ran = (int) (Math.random() * 900000) + 100000;
            logger.info("{}", ran);
            if (set.contains(ran)) {
                list.add(ran);
                continue;
            }
            set.add(ran);
        }
        list.forEach(i -> logger.info("List:{}", i));
        logger.info("Set:{}", set.size());
    }

    @Test
    public void testRandomList() {
        List<String> careerCodeList = Arrays.asList("20000", "30000", "40000", "50000", "60000");
        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                logger.info("{}", RandomUtil.randomEle(careerCodeList));
                logger.info("{}", RandomUtil.simpleUUID());
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }

        logger.info("OK................");
        ThreadUtil.sleep(10000);
    }
}
