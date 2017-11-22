package com.tangshengbo.arithmetic.encrypt;

import jodd.util.ThreadUtil;

import java.io.IOException;

/**
 * Created by Tangshengbo on 2017/11/22.
 */
public class EncryptTest {

    public static void main(String[] args) {
        String content = "唐声波";
        System.out.println(EncryptUtil.encodeMD5String(content));
        System.out.println(EncryptUtil.encodeSHAString(content));
        ThreadUtil.sleep(1000);
        String result = EncryptUtil.encodeBase64String(content);
        System.out.println(result);
        try {
            System.out.println(EncryptUtil.decodeBase64String(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
