package com.tangshengbo.xml.sign;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Tangshengbo on 2017/12/21.
 */
public class SignUtil {

    private static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    //数字签名，密钥算法
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名
     *
     * @param data
     * @param privateKey 密钥
     * @return byte[] 数字签名
     */
    public static String sign(String data, String privateKey, String charset) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        //初始化Signature
        signature.initSign(priKey);
        //更新
        signature.update(data.getBytes(charset));
        return Base64.encode(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      待校验数据
     * @param sign      数字签名
     * @param publicKey 公钥
     * @return boolean 校验成功返回true，失败返回false
     */
    public static boolean verify(String data, String sign, String publicKey, String charset) throws Exception {
        //转换公钥材料
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        //初始化Signature
        signature.initVerify(pubKey);
        //更新
        signature.update(data.getBytes(charset));
        //验证
        return signature.verify(Base64.decode(sign));
    }
}
