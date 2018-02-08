package com.tangshengbo.net.address;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

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
        }
        logger.info("{}", InetAddress.getByName("www.qq.com").getHostAddress());
        logger.info("{}", InetAddress.getLocalHost());
        logger.info("{}", -255 & 0xff);

        byte[] address = {(byte) 180, (byte) 163, 26, 39};
        logger.info("{}", InetAddress.getByAddress(address).getHostName());
//        NetUtil.resolveIpAddress()
    }
}
