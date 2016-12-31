package com.tangshengbo.sort;

public class BubbleSortTest {

    public static void main(String[] args) {
        BubbleSortTest test = new BubbleSortTest();
        int[] arr = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        arr = test.bubbleSort(arr);
//		System.out.println("传统排序方法:");
//		for (int i : arr) {
//			System.out.print(i + " ,");
//		}
        long start = System.currentTimeMillis(); // 获取开始时间6588ms
        arr = test.improveBubbleSort2(arr);
        System.out.println("new排序方法:");
        for (int i : arr) {
            System.out.print(i + " ,");
        }
        long end = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序运行时间： " + (end - start) + "ms");

    }

    @SuppressWarnings("unused")
    private int[] bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                System.out.println(len - 1 - i);
                if (arr[j] > arr[j + 1]) { // 相邻元素两两对比
                    int temp = arr[j + 1]; // 元素交换
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    private int[] improveBubbleSort(int[] arr) {

        int i = arr.length - 1; // 初始时,最后位置保持不变
        while (i > 0) {
            int pos = 0; // 每趟开始时,无记录交换
            for (int j = 0; j < i; j++)
                if (arr[j] > arr[j + 1]) {
                    pos = j; // 记录交换的位置
                    System.out.println("记录交换的位置" + pos);
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            i = pos; // 为下一趟排序作准备
        }
        System.out.println("改进后冒泡排序耗时");
        return arr;
    }

    public int[] improveBubbleSort2(int[] arr) {
        int low = 0;
        int high = arr.length - 1; //设置变量的初始值
        int tmp, j;
        while (low < high) {
            for (j = low; j < high; ++j) //正向冒泡,找到最大者
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            --high;                 //修改high值, 前移一位
            for (j = high; j > low; --j) //反向冒泡,找到最小者
                if (arr[j] < arr[j - 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            ++low;                  //修改low值,后移一位
        }
        return arr;

    }

}
