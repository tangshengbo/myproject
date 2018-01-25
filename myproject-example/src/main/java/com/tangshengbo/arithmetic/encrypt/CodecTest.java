package com.tangshengbo.arithmetic.encrypt;

import jodd.util.StringPool;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TangShengBo on 2018/1/25.
 */
public class CodecTest {

    private static final Logger logger = LoggerFactory.getLogger(CodecTest.class);

    @Test
    public void testBase64() throws Exception {
        logger.info("{}", "==============Base64================");
        byte[] data = "tangshengbo".getBytes();
        Base64 base64 = new Base64();
        String encode = base64.encodeAsString(data);
        logger.info("{}", encode);
        logger.info("{}", IOUtils.toString(base64.decode(encode), StringPool.UTF_8));
    }

    @Test
    public void testMD5() {
        logger.info("{}", "==============MD5================");
        String result = DigestUtils.md5Hex("tangshengbo");
        logger.info("{}", result);
        logger.info("{}", DigestUtils.md2Hex("tangshengbo"));
    }

    @Test
    public void testURLCodec() throws Exception {
        logger.info("{}", "==============URLCodec================");
        URLCodec codec = new URLCodec(StringPool.UTF_8);
        String data = "唐声波";
        String encode = codec.encode(data);
        logger.info("{}", encode);
        logger.info("{}", codec.decode(encode));
    }
}
