package com.tangshengbo.arithmetic;

import com.tangshengbo.loadclass.Person;

import java.util.Scanner;

/**
 * Created by tangshengbo on 2017/3/17.
 */
public class Calculate {

    private static double num;
    private static Scanner in;

    public static void main(String[] args) {
        calcNum();
    }

    private static void calcNum() {
        in = new Scanner(System.in);
        System.out.print("请输入一个浮点数：");
        num = in.nextDouble();
        new MathRound(num).invoke();
        Person person = new Person();


        calc();
    }

    private static class MathRound {
        private double num;

        public MathRound(double num) {
            this.num = num;
        }

        public void invoke() {
            double cnum = Math.ceil(num);
            System.out.println("大于" + num + "的最小数：" + cnum);
            double fnum =  Math.floor(num);
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
        System.out.println("a:\t" + a + "\t+ b:\t" + b + "=" +sum);
    }
}
