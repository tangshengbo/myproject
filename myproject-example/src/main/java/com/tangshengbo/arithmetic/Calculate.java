package com.tangshengbo.arithmetic;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tangshengbo on 2017/3/17.
 */
public class Calculate {

    private static double num;
    private static Scanner in;

    public static void main(String[] args) {
        calcNum();
        BigDecimal old = new BigDecimal(8030.86);
        BigDecimal newNum = new BigDecimal(8030.86000);
        System.out.println(old.equals(newNum));
    }

    private static void calcNum() {
//        in = new Scanner(System.in);
//        System.out.print("请输入一个浮点数：");
//        num = in.nextDouble();
//        new MathRound(num).invoke();
//        Person person = new Person();
        List<String> names = Lists.newArrayList();
        names.add("tang");
        names.add("shengbo");
        names.add("tangshengbo");
//        for (String name : names) {
//            names.remove(name);
//        }
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
}
