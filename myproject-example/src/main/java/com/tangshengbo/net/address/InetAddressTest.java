package com.tangshengbo.net.address;

import com.google.common.collect.Lists;
import jodd.io.NetUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by Tangshengbo on 2018/2/8.
 */
public class InetAddressTest {

    private static final Logger logger = LoggerFactory.getLogger(InetAddressTest.class);

    @Test
    public void testInetAddress() throws Exception {
        InetAddress[] inetAddresses = InetAddress.getAllByName("www.houbank.com");
        for (InetAddress inetAddress : inetAddresses) {
            logger.info("{}", inetAddress.getHostAddress());
            logger.info("{}", numericToTextFormat(inetAddress.getAddress()));
            logger.info("{}", toUnsigned(inetAddress.getAddress()));
        }
        logger.info("{}", InetAddress.getByName("www.qq.com").getHostAddress());
        logger.info("{}", InetAddress.getLocalHost());
        logger.info("{}", -255 & 0xff);

        byte[] address = {(byte) 180, (byte) 163, 26, 39};
        logger.info("{}", InetAddress.getByAddress(address).getHostName());

        logger.info("{}", InetAddress.getByName("180.163.26.39"));
    }

    @Test
    public void testIPCharacteristics() throws Exception {
        InetAddress address = InetAddress.getByName("localhost");

        if (address.isAnyLocalAddress()) {
            System.out.println(address + " is a wildcard address.");
        }
        if (address.isLoopbackAddress()) {
            System.out.println(address + " is loopback address.");
        }
        if (address.isLinkLocalAddress()) {
            System.out.println(address + " is a link-local address.");
        } else if (address.isSiteLocalAddress()) {
            System.out.println(address + " is a site-local address.");
        } else {
            System.out.println(address + " is a global address.");
        }

        if (address.isMulticastAddress()) {
            if (address.isMCGlobal()) {
                System.out.println(address + " is a global multicast address.");
            } else if (address.isMCOrgLocal()) {
                System.out.println(address
                        + " is an organization wide multicast address.");
            } else if (address.isMCSiteLocal()) {
                System.out.println(address + " is a site wide multicastaddress.");
            } else if (address.isMCLinkLocal()) {
                System.out.println(address + " is a subnet wide multicastaddress.");
            } else if (address.isMCNodeLocal()) {
                System.out.println(address
                        + " is an interface-local multicast address.");
            } else {
                System.out.println(address + " is an unknown multicastaddress type.");
            }
        } else {
            System.out.println(address + " is a unicast address.");
        }
    }

    @Test
    public void testReachable() throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        logger.info("{}", inetAddress.isReachable(10000000));
    }

    @Test
    public void testNetWorkInterface() throws Exception {
        logger.info("{}", getLocalIPList());
        logger.info("{}", getLocalIP());
        NetworkInterface networkInterface = NetworkInterface.getByName("wlan0");
        logger.info("{}", networkInterface.getDisplayName());
        logger.info("{}", networkInterface.getInetAddresses().nextElement().getHostAddress());
    }

    /**
     * 获取本机所有ip
     *
     * @return
     * @throws Exception
     */
    private List<String> getLocalIPList() throws Exception {
        Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        List<String> localIPList = Lists.newArrayList();
        while (interfaceEnumeration.hasMoreElements()) {
            //获取本机网卡接口
            NetworkInterface networkInterface = interfaceEnumeration.nextElement();
            networkInterface.getInterfaceAddresses().forEach(interfaceAddress -> {
                        String hostAddress = interfaceAddress.getAddress().getHostAddress();
                        logger.info("hostAddress:{}", hostAddress);
                        if (validateHostIp(hostAddress)) {
                            localIPList.add(hostAddress);
                        }
                    }
            );
        }
        return localIPList;
    }

    public Set<String> getLocalIP() throws Exception {
        String address = InetAddress.getLocalHost().getHostName();
        return Stream.of(InetAddress.getAllByName(address))
                .map(InetAddress::getHostAddress)
                .filter(this::validateHostIp)
                .collect(Collectors.toSet());
    }


    private boolean validateHostIp(String hostAddress) {
        return NetUtil.validateAgaintIPAdressV4Format(hostAddress) && !NetUtil.LOCAL_IP.equals(hostAddress);
    }

    /**
     * 有符号字节转换成无符号 (0xff = 1111 1111)
     *
     * @param src
     * @return
     */
    private static String numericToTextFormat(byte[] src) {
        logger.info("{}", Arrays.toString(src));
        return (src[0] & 0xff) + "." + (src[1] & 0xff) + "." + (src[2] & 0xff) + "." + (src[3] & 0xff);
    }

    /**
     * 有符号字节转换成无符号int
     *
     * @param src
     * @return
     */
    private static String toUnsigned(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (byte b : src) {
            sb.append(b < 0 ? b + 256 : b).append(".");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
