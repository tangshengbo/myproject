package com.tangshengbo.tutorial.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jodd.io.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by TangShengBo on 2018/1/27.
 */
public class IpAddressCache {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateTest.class);

    private static final Map<String, IpAddress> IP_MAP = new HashMap<>();

    private static final String LOCAL_REAL_IP_KEY = "localRealIp";

    public static IpAddress getRealIp() {
        IpAddress ipAddress = IP_MAP.get(LOCAL_REAL_IP_KEY);
        if (Objects.isNull(ipAddress)) {
            synchronized (IpAddressCache.class) {
                try {
                    ipAddress = IP_MAP.get(LOCAL_REAL_IP_KEY);
                    if (Objects.isNull(ipAddress)) {
                        logger.warn("{} {}", Thread.currentThread().getName(), "获取本机真实IP");
                        Gson gson = new GsonBuilder().create();
                        String result = NetUtil.downloadString("http://ip.chinaz.com/getip.aspx");
                        ipAddress = gson.fromJson(result, IpAddress.class);
                        IP_MAP.put(LOCAL_REAL_IP_KEY, ipAddress);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ipAddress;
    }
}
