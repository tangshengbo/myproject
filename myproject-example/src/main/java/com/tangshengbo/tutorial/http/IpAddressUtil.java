package com.tangshengbo.tutorial.http;

import jodd.util.SystemUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

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

    /**
     * 获得主机IP
     *
     * @return String
     */
    public static boolean isWindowsOS() {
        return SystemUtil.getOsName().contains("windows");
    }

    /**
     * 获取本机ip地址，并自动区分Windows还是linux操作系统
     *
     * @return String
     */
    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            }
            // 如果是Linux操作系统
            else {
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = netInterfaces.nextElement();
                    // ----------特定情况，可以考虑用ni.getName判断
                    // 遍历所有ip
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
                                && !ip.getHostAddress().contains(":")) {
                            bFindIP = true;
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }
}


