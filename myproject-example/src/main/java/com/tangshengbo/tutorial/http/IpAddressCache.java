package com.tangshengbo.tutorial.http;

import com.alibaba.dubbo.common.utils.NetUtils;
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

    private static final String IP_SITE = "http://ip.chinaz.com/getip.aspx";

    private static final String OUTER_IP_ADDRESS_KEY = "outer_ip_address_key";
    private static final String INNER_IP_ADDRESS_KEY = "inner_ip_address_key";

    /**
     * 获取本机外网IP
     *
     * @return
     */
    public static IpAddress getOuterIpAddress() {
        IpAddress ipAddress = IP_MAP.get(OUTER_IP_ADDRESS_KEY);
        if (Objects.isNull(ipAddress)) {
            synchronized (IpAddressCache.class) {
                try {
                    ipAddress = IP_MAP.get(OUTER_IP_ADDRESS_KEY);
                    if (Objects.isNull(ipAddress)) {
                        logger.warn("{} {}", Thread.currentThread().getName(), "获取本机外网IP");
                        Gson gson = new GsonBuilder().create();
                        String result = NetUtil.downloadString(IP_SITE);
//                        ipAddress = JSON.parseObject(result, new TypeReference<IpAddress>() {});
                        ipAddress = gson.fromJson(result, IpAddress.class);
                        IP_MAP.put(OUTER_IP_ADDRESS_KEY, ipAddress);
                    }
                } catch (IOException e) {
                    logger.error("获取本机真实IP异常:{}", e);
                }
            }
        }
        return ipAddress;
    }

    /**
     * 获取本机内网IP
     *
     * @return
     */
    public static IpAddress getInnerIpAddress() {
        IpAddress ipAddress = IP_MAP.get(INNER_IP_ADDRESS_KEY);
        if (Objects.isNull(ipAddress)) {
            synchronized (IpAddressCache.class) {
                ipAddress = IP_MAP.get(INNER_IP_ADDRESS_KEY);
                if (Objects.isNull(ipAddress)) {
                    logger.warn("{} {}", Thread.currentThread().getName(), "获取本机内网IP");
                    ipAddress = new IpAddress();
                    ipAddress.setIp(NetUtils.getLocalHost());
                    IP_MAP.put(INNER_IP_ADDRESS_KEY, ipAddress);
                }
            }
        }
        return ipAddress;
    }
}
