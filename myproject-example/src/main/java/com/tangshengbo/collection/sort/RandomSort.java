package com.tangshengbo.collection.sort;

import java.util.*;

/**
 * Created by tangshengbo on 2017/3/19.
 */
public class RandomSort {

    public static void main(String[] args) {
        // 创建随机数生成器
        printRandomBySort();
    }

    private static void printRandomBySort() {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //0~9的随机数
            numbers.add(random.nextInt(10));
        }
        Collections.sort(numbers);
        for(int num : numbers) {
            System.out.println(num);
        }
    }
}
