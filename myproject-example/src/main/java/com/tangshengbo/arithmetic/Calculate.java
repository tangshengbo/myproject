package com.tangshengbo.arithmetic;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by tangshengbo on 2017/3/17.
 */
public class Calculate {

    private static final String DEFAULT_NUMBER_PATTERN = "#,##0.00";
    private static final Logger logger = LoggerFactory.getLogger(Calculate.class);
    private static double num;
    private static Scanner in;

    public static void main(String[] args) throws Exception {
        BigDecimal old = new BigDecimal(8030.86);
        BigDecimal newNum = new BigDecimal(8030);
        System.out.println(old.equals(newNum));
        newNum = newNum.multiply(BigDecimal.valueOf(0.5));
        System.out.println(newNum);
    }

    private static void calcNum() {
        in = new Scanner(System.in);
        System.out.print("请输入一个浮点数：");
        num = in.nextDouble();
        new MathRound(num).invoke();
        int positiveNumber = 0;
        int negativeNumber = 0;
        int zero = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的数字:输入9结束");
        while (scanner.hasNext()) {
            int inputNumber = scanner.nextInt();
            if (inputNumber > 0) {
                positiveNumber++;
                System.out.println("正数");
            }
            if (inputNumber < 0) {
                negativeNumber++;
                System.out.println("负数");
            }
            if (inputNumber == 0) {
                zero++;
                System.out.println("零");
            }

            if (inputNumber == 9) {
                System.out.println("结束.............");
                break;
            }
        }
        System.out.println("输入的正数" + positiveNumber + "\t 负数" + negativeNumber + "\t 零" + zero);
//        calc();
    }

    private static class MathRound {
        private double num;

        public MathRound(double num) {
            this.num = num;
        }

        public void invoke() {
            double cnum = Math.ceil(num);
            System.out.println("大于" + num + "的最小数：" + cnum);
            double fnum = Math.floor(num);
            System.out.println("小于" + num + "的最大数：" + fnum);
            double rnum = Math.rint(num);
            System.out.println(num + "四舍五入得12到浮点数：" + rnum);
            long lnum = Math.round(num);
            System.out.println(num + "四舍五入得到长整数：" + lnum);
        }
    }

    public static void calc() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int sum = a + b;
        System.out.println("a:\t" + a + "\t+ b:\t" + b + "=" + sum);
    }

    private static void parse() {
        // 本地格式的解析
        NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
        Number numb1;
        String result = "";
        try {
            numb1 = numberFormat1.parse("122,123,12.23");
            result = String.valueOf(numb1);
            System.out.println(BigDecimal.valueOf(Double.valueOf(result))
                    .divide(BigDecimal.valueOf(100))
                    .setScale(2, BigDecimal.ROUND_HALF_UP));
        } catch (ParseException e1) {
            System.out.println(e1);

        }
        System.out.println(result);
    }

    /**
     * 按标准化的数字表现方式格式化浮点型数字样式,默认格式（"#,##0.00")
     *
     * @param source 被格式化的字符
     */
    public static Double parse(String source) throws ParseException {
        return parse(source, DEFAULT_NUMBER_PATTERN);
    }

    /**
     * 按标准化的数字表现方式格式化浮点型数字样式
     *
     * @param source  被格式化的字符
     * @param pattern 格式化的模板
     */
    public static Double parse(String source, String pattern) throws ParseException {
        if (Objects.isNull(source) || StringUtils.isBlank(pattern)) {
            return null;
        } else {
            DecimalFormat df = new DecimalFormat(pattern);
            return Double.valueOf(String.valueOf(df.parse(source)));
        }
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     */
    public static double changeF2Y(long amount) {
        BigDecimal decimal = BigDecimal.valueOf(amount).divide(new BigDecimal(100));
        return decimal.doubleValue();
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     */
    public static double changeF2Y(double amount) {
        BigDecimal decimal = BigDecimal.valueOf(amount).divide(new BigDecimal(100));
        return decimal.doubleValue();
    }

    /**
     * 判断参数为0 返回指定的字符串
     * @param str String类型参数
     * @param defaultStr 默认字符串
     * @return 指定的字符串
     */
    public static String defaultIfZero(String str, String defaultStr) {
        if (defaultStr.equals(str)) {
            return str;
        }
        try {
            float floatValue = Float.parseFloat(str);
            if (Float.compare(floatValue, 0.0F) == 0) {
                return defaultStr;
            }
        } catch (NumberFormatException e) {
            return defaultStr;
        }
        return str;
    }


    @Test
    public void testMultiply() {
        BigDecimal amount = BigDecimal.valueOf(6000);
        double rate = 4.0;
        double riskReserveFee =
                amount.multiply(BigDecimal.valueOf(rate))
                        .divide(new BigDecimal(100)).doubleValue();
        logger.info("{}", riskReserveFee);
    }
}
