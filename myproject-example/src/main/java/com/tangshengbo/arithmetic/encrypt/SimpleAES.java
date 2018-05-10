package com.tangshengbo.arithmetic.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;


/**
 * Created by Tangshengbo on 2017/11/22.
 */
public class SimpleAES {

    private static final String Algorithm = "AES";
    //默认秘钥
    public static final String PWD = "&*($ABCDEF%&T";

    public static final String SALT = "Tang";

    /**
     * 加密
     *
     * @param plainText 明文
     * @param iv        16位的随机码
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText, String password) throws Exception {
        try {
            return toHex(encrypt(plainText.getBytes("UTF-8"), password));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 解密 以String密文输入,String明文输出
     *
     * @return
     * @throws Exception
     */
    public static String decrypt(String cipherText, String password) throws Exception {
        try {
            byte[] bytes = decrypt(hexTobytes(cipherText), password);
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 加密以byte[]明文输入,byte[]密文输出
     *
     * @param byteS
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] byteS, String pwd) throws Exception {

        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(Algorithm);

            SecretKeySpec keySpec = new SecretKeySpec(getKey(pwd), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            cipher = null;
        }

        return byteFina;
    }

    /**
     * 解密以byte[]密文输入,以byte[]明文输出
     *
     * @param byteD
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] byteD, String pwd) throws Exception {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance(Algorithm);

            SecretKeySpec keySpec = new SecretKeySpec(getKey(pwd), "AES");

            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byteFina = cipher.doFinal(byteD);

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    private static byte[] getKey(String password) throws UnsupportedEncodingException {
        // 使用256位密码
        if (password.length() > 16)
            password = password.substring(0, 16);
        else if (password.length() < 16) {
            int count = (16 - password.length());
            for (int i = 0; i < count; i++) {
                password += "0";
            }
        }
        return password.getBytes("UTF-8");
    }

    /**
     * Convert byte array to hex string
     */
    public static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 3);
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                sb.append("0");

            sb.append(Integer.toHexString(val));

        }

        return sb.toString();
    }

    /**
     * Convert hex string to byte array
     *
     * @param str
     * @return
     */
    public static byte[] hexTobytes(String str) {
        int l = str.length();
        if ((l % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数!");
        }
        byte[] bytes = new byte[l / 2];
        for (int i = 0; i < l; i = i + 2) {
            String item = str.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(item, 16);
        }
        return bytes;
    }

    public static void main(String[] args) {
        try {
            System.out.println(SimpleAES.decrypt("tangshengbo" + SALT, PWD));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
