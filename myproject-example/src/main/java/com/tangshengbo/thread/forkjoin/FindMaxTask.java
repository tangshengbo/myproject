package com.tangshengbo.thread.forkjoin;

import java.util.concurrent.Callable;

/**
 * Created by Tangshengbo on 2018/2/7.
 */
public class FindMaxTask implements Callable<Integer> {

    private int[] data;
    private int start;
    private int end;

    FindMaxTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    public Integer call() {
        int max = 0;
        for (int i = start; i < end; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }
}
