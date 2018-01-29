package com.tangshengbo.tutorial.http;

/**
 * Created by TangShengBo on 2018/1/27.
 */
public class IpAddressUtil {

    public static IpAddress resolveLocalIpAddress() {
        return IpAddressCache.getInnerIpAddress();
    }

    public static IpAddress resolveLocalRealIpAddress() {
        return IpAddressCache.getOuterIpAddress();
    }

    public static String resolveLocalRealIp() {
        return resolveLocalRealIpAddress().getIp();
    }

    public static String resolveLocalIp() {
        return resolveLocalIpAddress().getIp();
    }
}
