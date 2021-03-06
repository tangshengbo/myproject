package com.tangshengbo.arithmetic.encrypt;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Tangshengbo on 2017/11/22.
 */
public final class EncryptUtil {

    // 密码盐
    public static final String PWDSALT = "PAY";

    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private EncryptUtil() {
    }

    /**
     * 用MD5算法进行加密
     *
     * @param str 需要加密的字符串
     * @return MD5加密后的结果
     */
    public static String encodeMD5String(String str) {
        return encode(str, "MD5");
    }

    /**
     * 用SHA算法进行加密
     *
     * @param str 需要加密的字符串
     * @return SHA加密后的结果
     */
    public static String encodeSHAString(String str) {
        return encode(str, "SHA");
    }

    /**
     * 用base64算法进行加密
     *
     * @param str 需要加密的字符串
     * @return base64加密后的结果
     */
    public static String encodeBase64String(String str) {
        return new String(Base64.getEncoder().encode(str.getBytes()));
    }

    /**
     * 用base64算法进行解密
     *
     * @param str 需要解密的字符串
     * @return base64解密后的结果
     * @throws IOException
     */
    public static String decodeBase64String(String str) throws IOException {
        return new String(Base64.getDecoder().decode(str));
    }

    private static String encode(String str, String method) {
        MessageDigest mdInst = null;
        // 把密文转换成十六进制的字符串形式
        // 单线程用StringBuilder，速度快 多线程用stringbuffer，安全
        StringBuffer sb = new StringBuffer();
        try {
            // 获得MD5摘要算法的 MessageDigest对象
            mdInst = MessageDigest.getInstance(method);
            // 使用指定的字节更新摘要
            mdInst.update(str.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            for (int i = 0; i < md.length; i++) {
                int tmp = md[i];
                if (tmp < 0) {
                    tmp += 256;
                }
                if (tmp < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(tmp));
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
        return sb.toString();
    }
}
