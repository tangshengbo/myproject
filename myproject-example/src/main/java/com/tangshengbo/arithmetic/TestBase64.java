package com.tangshengbo.arithmetic;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by TangShengBo on 2017-05-19.
 */
public class TestBase64 {

    private static final Logger logger = LoggerFactory.getLogger(TestBase64.class);

    public static void main(String[] args) {
        testBase64Apache();
    }

    private static void testBase64Apache() {
        String str = "Hello World Apache";
        try {
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            System.out.println("Apache RESULT: En\t" + new String(encodeBase64));
            encodeBase64 = Base64.decodeBase64(encodeBase64);
            System.out.println("Apache RESULT: De\t" + new String(encodeBase64));
            System.out.println("=====================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testCollectionSort(ConcurrentHashMap concurrentHashMap) {
        concurrentHashMap.get("");
        String str2 = "a,b,c,,";
        String[] ary = str2.split(",");
        //预期大于 3，结果是 3
        System.out.println(ary.length);
        //定义一个数组
        Integer[] arr = {1, 3, 66, 4, 78, 55, 9, 4, 3, 99};
        //将数组转成集合
        List<Integer> list1 = Arrays.asList(arr);
        Set<Integer> set = new HashSet<Integer>(list1);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.addAll(set);
        System.err.println("去重:" + list2);
        Collections.sort(list2);
        System.out.println("排序:" + list2);
        Integer[] newArr = new Integer[list2.size()];
        newArr = list2.toArray(newArr);
        for (Integer integer : newArr) {
            System.out.println(integer);
        }
        concurrentHashMap.entrySet();
    }

    public void today() {
        if (isBusy()) {
            return;
        }

        if (isFree()) {
            return;
        }

        System.out.println("isHappy");
    }

    private boolean isFree() {
        return true;
    }

    private boolean isBusy() {
        return false;
    }

    private static int testFinally() {
        try {
            return 1;
        } finally {
            System.out.println("32353453443534534534534");
//            return 2;
        }
    }

    @Test
    public void encodeOrDecodeBinary() throws Exception {
        String str = "PGJlYW5zIHhtbG5zPSJodHRwOi8vd3d3LnNwcmluZ2ZyYW1ld29yay5vcmcvc2NoZW1hL2JlYW5zIg0KCSAgIHhtbG5zOmNvbnRleHQ9Imh0dHA6Ly93d3cuc3ByaW5nZnJhbWV3b3JrLm9yZy9zY2hlbWEvY29udGV4dCINCgkgICB4bWxuczpwPSJodHRwOi8vd3d3LnNwcmluZ2ZyYW1ld29yay5vcmcvc2NoZW1hL3AiDQoJICAgeG1sbnM6bXZjPSJodHRwOi8vd3d3LnNwcmluZ2ZyYW1ld29yay5vcmcvc2NoZW1hL212YyINCgkgICB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIg0KCSAgIHhtbG5zOmFvcD0iaHR0cDovL3d3dy5zcHJpbmdmcmFtZXdvcmsub3JnL3NjaGVtYS9hb3AiDQoJICAgeHNpOnNjaGVtYUxvY2F0aW9uPSJodHRwOi8vd3d3LnNwcmluZ2ZyYW1ld29yay5vcmcvc2NoZW1hL2JlYW5zDQogICAgICBodHRwOi8vd3d3LnNwcmluZ2ZyYW1ld29yay5vcmcvc2NoZW1hL2JlYW5zL3NwcmluZy1iZWFucy0zLjAueHNkDQogICAgICBodHRwOi8vd3d3LnNwcmluZ2ZyYW1ld29yay5vcmcvc2NoZW1hL2NvbnRleHQNCiAgICAgIGh0dHA6Ly93d3cuc3ByaW5nZnJhbWV3b3JrLm9yZy9zY2hlbWEvY29udGV4dC9zcHJpbmctY29udGV4dC54c2QNCiAgICAgIGh0dHA6Ly93d3cuc3ByaW5nZnJhbWV3b3JrLm9yZy9zY2hlbWEvbXZjDQogICAgICBodHRwOi8vd3d3LnNwcmluZ2ZyYW1ld29yay5vcmcvc2NoZW1hL212Yy9zcHJpbmctbXZjLTMuMC54c2QNCiAgICAgIGh0dHA6Ly93d3cuc3ByaW5nZnJhbWV3b3JrLm9yZy9zY2hlbWEvYW9wIGh0dHA6Ly93d3cuc3ByaW5nZnJhbWV3b3JrLm9yZy9zY2hlbWEvYW9wL3NwcmluZy1hb3AtMy4wLnhzZCAiPg0KDQoNCgk8IS0tIOWQr+WKqOazqOino+mpseWKqOeahFNwcmluZyBNVkPlip/og73vvIzms6jlhozor7fmsYJ1cmzlkozms6jop6NQT0pP57G75pa55rOV55qE5pig5bCELS0+DQoJPG12Yzphbm5vdGF0aW9uLWRyaXZlbiAvPg0KCTwhLS0g5ZCv5Yqo5YyF5omr5o+P5Yqf6IO977yM5Lul5L6/5rOo5YaM5bim5pyJQENvbnRyb2xsZXLjgIFAU2VydmljZeOAgUByZXBvc2l0b3J544CBQENvbXBvbmVudOetieazqOino+eahOexu+aIkOS4unNwcmluZ+eahGJlYW4gLS0+DQoJPGNvbnRleHQ6Y29tcG9uZW50LXNjYW4gYmFzZS1wYWNrYWdlPSJjb20udGFuZ3NoZW5nYm8uY29udHJvbGxlciIgLz4NCgk8IS0tIOWQr+WKqOWvuUBBc3BlY3RK5rOo6Kej55qE5pSv5oyBICAtLT4NCgk8YW9wOmFzcGVjdGotYXV0b3Byb3h5IHByb3h5LXRhcmdldC1jbGFzcz0idHJ1ZSIvPg0KCTwhLS0g5L2/55SoIFN3YWdnZXIgUmVzdGZ1bCBBUEnmlofmoaPml7bvvIzmt7vliqDmraTms6jop6MgLS0+DQoJPG12YzpkZWZhdWx0LXNlcnZsZXQtaGFuZGxlciAvPg0KCTxiZWFuIGNsYXNzPSJjb20udGFuZ3NoZW5nYm8uY29yZS5SZXN0QXBpQ29uZmlnIiAvPg0KCTxtdmM6cmVzb3VyY2VzIG1hcHBpbmc9InN3YWdnZXItdWkuaHRtbCIgbG9jYXRpb249ImNsYXNzcGF0aDovTUVUQS1JTkYvcmVzb3VyY2VzLyIvPg0KCTxtdmM6cmVzb3VyY2VzIG1hcHBpbmc9Ii93ZWJqYXJzLyoqIiBsb2NhdGlvbj0iY2xhc3NwYXRoOi9NRVRBLUlORi9yZXNvdXJjZXMvd2ViamFycy8iLz4NCgk8IS0tIOWvueaooeWei+inhuWbvuWQjeensOeahOino+aekO+8jOWcqOivt+axguaXtuaooeWei+inhuWbvuWQjeensOa3u+WKoOWJjeWQjue8gCAtLT4NCgk8YmVhbiBjbGFzcz0ib3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuc2VydmxldC52aWV3LkludGVybmFsUmVzb3VyY2VWaWV3UmVzb2x2ZXIiIHA6cHJlZml4PSIvV0VCLUlORi92aWV3LyIgcDpzdWZmaXg9Ii5qc3AiIC8+DQoJPGJlYW4gY2xhc3M9Im9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLnNlcnZsZXQubXZjLm1ldGhvZC5hbm5vdGF0aW9uLlJlcXVlc3RNYXBwaW5nSGFuZGxlckFkYXB0ZXIiPg0KCQk8cHJvcGVydHkgbmFtZT0ibWVzc2FnZUNvbnZlcnRlcnMiPg0KCQkJPGxpc3Q+DQoJCQkJPGJlYW4NCgkJCQkJCWNsYXNzPSJvcmcuc3ByaW5nZnJhbWV3b3JrLmh0dHAuY29udmVydGVyLmpzb24uTWFwcGluZ0phY2tzb24ySHR0cE1lc3NhZ2VDb252ZXJ0ZXIiPg0KCQkJCQk8cHJvcGVydHkgbmFtZT0ic3VwcG9ydGVkTWVkaWFUeXBlcyI+DQoJCQkJCQk8bGlzdD4NCgkJCQkJCQk8dmFsdWU+dGV4dC9odG1sO2NoYXJzZXQ9VVRGLTg8L3ZhbHVlPg0KCQkJCQkJCTx2YWx1ZT50ZXh0L3BsYWluO2NoYXJzZXQ9VVRGLTg8L3ZhbHVlPg0KCQkJCQkJCTx2YWx1ZT5hcHBsaWNhdGlvbi9qc29uO2NoYXJzZXQ9VVRGLTg8L3ZhbHVlPg0KCQkJCQkJPC9saXN0Pg0KCQkJCQk8L3Byb3BlcnR5Pg0KCQkJCTwvYmVhbj4NCgkJCTwvbGlzdD4NCgkJPC9wcm9wZXJ0eT4NCgk8L2JlYW4+DQoNCg0KCTwhLS0g5Y2V54us5Li6d2ViYXBwbGljYXRpb25Db250ZXh05rOo5YWlcGxhY2Vob2xkZXIgIC0tPg0KCTxjb250ZXh0OnByb3BlcnR5LXBsYWNlaG9sZGVyIGlnbm9yZS11bnJlc29sdmFibGU9InRydWUiDQoJCQkJCQkJCSAgbG9jYXRpb249ImNsYXNzcGF0aCo6Y29uZmlnLnByb3BlcnRpZXMiIC8+DQo8L2JlYW5zPg==";
        logger.info("{}", str);
        byte[] data = java.util.Base64.getDecoder().decode(str);
        FileUtils.writeByteArrayToFile(new File("E:/t.txt"), data);
    }

    @Test
    public void testBase64Java8() {
        String str = "Hello World java8";
        byte[] encodeBase64;
        byte[] decodeBase64;
        String encodeStr;
        String decodeStr;
        try {
            encodeBase64 = java.util.Base64.getEncoder().encode(str.getBytes("UTF-8"));
            encodeStr = new String(encodeBase64);
            System.out.println("Java8 RESULT: En\t" + encodeStr);
            decodeBase64 = java.util.Base64.getDecoder().decode(encodeStr);
            decodeStr = new String(decodeBase64);
            System.out.println("Java8 RESULT: De\t" + decodeStr);

            System.out.println("=====================================================================");
            String url = "http://www.runoob.com/java/java8-base64.html";
            encodeBase64 = java.util.Base64.getUrlEncoder().encode(url.getBytes());
            encodeStr = new String(encodeBase64);
            System.out.println("Java8 Url RESULT: En\t" + encodeStr);
            decodeBase64 = java.util.Base64.getUrlDecoder().decode(encodeStr);
            decodeStr = new String(decodeBase64);
            System.out.println("Java8 Url RESULT: De\t" + decodeStr);

            System.out.println("=====================================================================");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 5; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            String mime = stringBuilder.toString();
            encodeBase64 = java.util.Base64.getMimeEncoder().encode(mime.getBytes());
            encodeStr = new String(encodeBase64);
            System.out.println("Java8 Mime RESULT: En\t" + encodeStr);
            decodeBase64 = java.util.Base64.getMimeDecoder().decode(encodeStr);
            decodeStr = new String(decodeBase64);
            System.out.println("Java8 Mime RESULT: De\t" + decodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
