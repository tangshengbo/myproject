package com.tangshengbo.collection;

/**
 * Created by TangShengBo on 2017-08-22.
 */
public class DynamicArray {

    private int[] nums = new int[10];

    private void addNum(int num) {
        int [] newNum = new int[nums.length + 1];
        System.arraycopy(nums, 0, newNum, 0, nums.length);
        newNum[nums.length] = num;
        nums = newNum;
        System.out.println("添加数组完成...............");
    }

    private void init() {
        for (int i = 0; i < 10; i++) {
            nums[i] = i;
        }
        System.out.println("初始化完成...............");
    }

    private void printArray() {
        for (int i = 0; i < nums.length ; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.init();
        for (int i = 0; i < 100; i++) {
            dynamicArray.addNum(i);
        }
        dynamicArray.printArray();
    }
}
