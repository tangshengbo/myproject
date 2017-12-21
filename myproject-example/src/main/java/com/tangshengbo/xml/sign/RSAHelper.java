package com.tangshengbo.xml.sign;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tangshengbo on 2017/12/21.
 */
public class RSAHelper {

    //数字签名，密钥算法
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA密钥长度，RSA算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;
    //公钥
    private static final String PUBLIC_KEY = "RSAPublicKey";

    //私钥
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 初始化密钥对
     *
     * @return Map 甲方密钥的Map
     */
    public static Map<String, Object> initKey() throws Exception {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //将密钥存储在map中
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥map
     * @return byte[] 私钥
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encode(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥map
     * @return byte[] 公钥
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encode(key.getEncoded());
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //初始化密钥
        //生成密钥对
        Map<String, Object> keyMap = RSAHelper.initKey();
        //公钥
        String publicKey = RSAHelper.getPublicKey(keyMap);
        //私钥
        String privateKey = RSAHelper.getPrivateKey(keyMap);

        System.out.println("公钥：/n" + publicKey);
        System.out.println("私钥：/n" + privateKey);

        System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");
        String str = "RSA数字签名算法";
        System.out.println("原文:" + str);
//        //甲方进行数据的加密
//        String sign = RSAHelper.sign(str, privateKey, "UTF-8");
//        System.out.println("产生签名：" + sign);
//        //验证签名
//        boolean status = RSAHelper.verify(str, publicKey, sign, "UTF-8");
//        System.out.println("状态：" + status + "/n/n");
//
//        JAXBUtil<Department> departmentJAXBUtil = new JAXBUtil<>();
//        String xml = departmentJAXBUtil.marshal(getSimpleDepartment(null));
//        System.out.println(xml);
//        sign = RSAHelper.sign(xml, privateKey, "UTF-8");
//        System.out.println("产生签名：" + sign);
//        //验证签名
//        status = RSAHelper.verify(xml, publicKey, sign, "UTF-8");
//        System.out.println("状态：" + status + "/n/n");
//        sign = departmentJAXBUtil.marshal(getSimpleDepartment(sign));
//        System.out.println(sign);
    }
}
