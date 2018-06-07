package com.tangshengbo.arithmetic;

import com.google.common.collect.Lists;
import com.tangshengbo.util.StringHelper;
import jodd.util.StringPool;
import jodd.util.StringUtil;
import jodd.util.ThreadUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Tangshengbo on 2017/10/19.
 */
public class TestString {

    private static final int size = 50000;
    private static final Logger logger = LoggerFactory.getLogger(TestString.class);

    @Test
    public void testPlusStr() {
        TestString testString = new TestString();
        testString.testPlus();
        testString.testConcat();
        testString.testJoin();
        testString.testStringBuffer();
        testString.testStringBuilder();
    }

    private void testPlus() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            s = s + String.valueOf(i);
        }
        long te = System.currentTimeMillis();
        System.out.println(String.format("+ cost %s ms", te - ts));
    }

    private void testConcat() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            s = s.concat(String.valueOf(i));
        }
        long te = System.currentTimeMillis();
        System.out.println(String.format("concat cost %s ms", te - ts));
    }

    private void testJoin() {
        List<String> list = new ArrayList<String>();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i));
        }
        StringUtils.join(list, "");
        long te = System.currentTimeMillis();
        System.out.println(String.format("StringUtils.join cost %s ms", te - ts));
    }

    private void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        System.out.println(String.format("StringBuffer cost %s ms", te - ts));
    }

    private void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        System.out.println(String.format("StringBuilder cost %s ms", te - ts));
    }

    private void breakMultipleLoop() {
        //跳出多重循环
        label:
        for (int i = 0; i < 100; i++) {
            System.out.println("i:" + i);
            for (int j = 0; j < 100; j++) {
                System.out.println("j:" + j);
                break label;
            }
        }
        System.out.println("label:break");
    }

    public String reverseStr(String targetStr) {
        StringBuffer sb = new StringBuffer(targetStr);
        sb.reverse();
        return sb.toString();
    }

    public String reverseStrByCharArray(String targetStr) {
        if (targetStr == null) {
            return "";
        }
        char[] chars = targetStr.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        System.out.println(reverse.valueOf(chars, 0, 5));
        return reverse;
    }

    public String replaceStr(int index, String srcStr, String targetStr) {
        StringBuffer sb = new StringBuffer(srcStr);
        sb.replace(index, srcStr.length(), targetStr);
        return sb.toString();
    }

    public String replaceEncode(String targetStr, String encodeType) {
        if (targetStr == null) {
            return "";
        }
        if (encodeType == null) {
            return "";
        }
        String newStr;
        try {
            newStr = new String(targetStr.getBytes(), encodeType);
        } catch (UnsupportedEncodingException e) {
            return "不支持编码格式";
        }
        return newStr;
    }

    private static void joinStr() {
        List<String> names = Lists.newArrayList();
        names.add("tang");
        names.add("sheng");
        names.add("bo");
        System.out.println(String.join("-", names));
    }

    @Test
    public void spiltStr() {
        String str = "20180204215639,3000000255,BILL99,201802042156208685744129299,67280482,08,1766.00,CNY,1023640565,HB_TZR_2248484,6225880119473735,CMBC,02,1766.00,,,,,,,,,,";
        String line = "AP01|HMP|551403582724||011520882503||2017-06-01 15:14:03||2017-06-01 15:20:06|单笔||288000|200|杨娜||||621558*********9129|成功|201706011515471618136836||";
//        line = "TP01|APS|540803516809|540356516323|021408833313||2017-06-02 14:08:03||2017-06-02 14:08:06|单笔||480000|0|郭艳||||623668*********1550|调用通用转账接口失败外呼CCBS返回错误信息:E3002,账户暂停非柜脇201706021405400832416486||";
//        line = "AP01|HMP|535817512325||021404817778||2017-06-02 13:58:17||2017-06-02 14:04:53|单笔||1440000|200|文艳||||622262*********5963|成功|201706021400012170193341||";
        String separator = StringPool.PIPE;
        logger.info(Arrays.toString(line.split(separator, -1)));
        logger.info(Arrays.toString(StringUtils.split(line, separator)));
        logger.info(Arrays.toString(StringUtil.split(line, separator)) + "\t" + StringUtil.split(line, separator).length);
        System.out.println(StringPool.COMMA.charAt(0));
        logger.info(StringUtil.removeChars(str, StringPool.COMMA.charAt(0), '.'));

        printSpilt(str, ",");
    }

    private void printSpilt(String str, String separator) {
        logger.info(Arrays.toString(str.split(separator, -1)));
        logger.info(Arrays.toString(StringUtils.split(str, separator)));
        logger.info(Arrays.toString(StringUtil.split(str, separator)));
    }

    private static void replaceStr() {
        String str = "20170906,3000000182,1001215854,HB_TZR_3261041,433.00,CNY,,,,,,,";
        System.out.println(str.replace("20170906", "201701113"));
        System.out.println(StringUtils.join(str.split(",", -1), "-"));
        System.out.println(StringUtils.replace(str, "20170906", "201701113"));
    }

    private static void subStr() {
        String str = StringHelper.concat("tang", "sheng", "bo");
        int offset = 0;
        System.out.println(str.substring(offset, offset + 4));
    }

    @Test
    public void testUUID() {
        Runnable r = () -> {
//            String s = UUID.randomUUID().toString();
//            String s1 = s.replaceAll("-", "");
//            logger.info("{}", s1);
////            logger.info("{}", s);
//            s = s.substring(s.lastIndexOf("-") + 1) + ".back";
//            logger.info("{}", s);
            logger.info("{}", random());
        };
        for (int i = 0; i < 10000; i++) {
            new Thread(r).start();
        }
        ThreadUtil.sleep(10000);

    }

    @Test
    public void testIsNumber() {
        String str = "3242342223";
        logger.info("{}",  StringUtils.isNotBlank(str) && StringUtils.isNumeric(str));
        str = "testIsNumber";
        logger.info("{}", StringUtils.capitalize(str));
    }

    private int random() {
        return (int) (Math.random() * 9000) + 1000;
    }
}


