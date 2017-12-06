package com.tangshengbo.design.proxy.log;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-09-12.
 */
public class CheckServiceImpl implements CheckService {

    @Override
    public Map<String, Object> recharge(String fileName) {
        System.out.println("充值对账开始............");
        Map<String, Object> resultMap = Maps.newHashMap();
        try {
            TimeUnit.SECONDS.sleep(2);
            resultMap.put("00000", "成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
            resultMap.put("00001", "失败");
        }
        System.out.println("充值对账结束............");
        return resultMap;
    }

    @Override
    public Map<String, Object> extract(String fileName) {
        System.out.println("提现对账开始............");
        Map<String, Object> resultMap = Maps.newHashMap();
        try {
            TimeUnit.SECONDS.sleep(4);
            resultMap.put("00000", "成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultMap.put("00001", "失败");
        System.out.println("提现对账结束............");
        return resultMap;
    }
}
