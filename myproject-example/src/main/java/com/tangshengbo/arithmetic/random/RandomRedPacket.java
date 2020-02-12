package com.tangshengbo.arithmetic.random;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.math.RandomUtils;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Tangshengbo on 2019/1/19
 */
public class RandomRedPacket {

    private int leftMoney;
    private int leftNum;
    private Random rnd;

    private static final int MAX_DIGITS = 4;

    private static final int BASE_MULTIPLE_VALUE = 10;


    public RandomRedPacket(int total, int num) {
        this.leftMoney = total;
        this.leftNum = num;
        this.rnd = new Random();
    }

    public synchronized int nextMoney() {
        if (this.leftNum <= 0) {
            throw new IllegalStateException("抢光了");
        }
        if (this.leftNum == 1) {
            return this.leftMoney;
        }
        double max = this.leftMoney / this.leftNum * 2d;
        int money = (int) (rnd.nextDouble() * max);
        money = Math.max(1, money);
        this.leftMoney -= money;
        this.leftNum--;
        return money;
    }

    static class Pair<U, V> {
        U first;
        V second;

        Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        public U getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        public static <U,V> Pair<U,V> makePair(U first, V second){
            return new Pair<>(first, second);
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    public static void main(String[] args) {
        RandomRedPacket redPacket = new RandomRedPacket(1000, 5);
        for (int i = 0; i < 5; i++) {
            System.out.print(redPacket.nextMoney() + "\t");
        }
        System.out.println();
        Pair<String, Integer> pair = new Pair<>("天天", 124);
        System.out.println(pair);

        System.out.println(Pair.makePair(124, "天天"));
    }

    private int extractDecimalDigits(BigDecimal bigDecimal) {
        int digits;
        int scale = bigDecimal.scale();
        if (scale == 0) {
            return 0;
        }
        digits = scale;
        if (digits > MAX_DIGITS) {
            digits = MAX_DIGITS;
        }
        return (int) Math.pow(BASE_MULTIPLE_VALUE, digits);
    }

    private BigDecimal randomRange(BigDecimal start, BigDecimal end) {
        int startDecimalDigits = extractDecimalDigits(start);
        int endDecimalDigits = extractDecimalDigits(end);
        int multipleValue = Math.max(startDecimalDigits, endDecimalDigits);

        int startNum = start.multiply(BigDecimal.valueOf(multipleValue)).intValue();
        int endNum = end.multiply(BigDecimal.valueOf(multipleValue)).intValue();
        int temp = startNum;
        if (endNum < startNum) {
            startNum = endNum;
            endNum = temp;
        }
        //产生随机数
        int result = RandomUtils.nextInt(endNum - startNum + 1) + startNum;
        return BigDecimal.valueOf(result).divide(BigDecimal.valueOf(multipleValue));
    }

}
