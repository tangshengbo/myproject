package com.tangshengbo.thread.forkjoin;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Tangshengbo on 2018/2/7.
 */
public class MultithreadedMaxFinder {

    private static final Logger logger = LoggerFactory.getLogger(MultithreadedMaxFinder.class);

    public static int max(int[] data) throws InterruptedException, ExecutionException {
        if (data.length == 1) {
            return data[0];
        } else if (data.length == 0) {
            throw new IllegalArgumentException();
        }

        // split the job into 2 pieces
        FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);

        // spawn 2 threads
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        return Math.max(future1.get(), future2.get());
    }

    @Test
    public void testMax() throws InterruptedException, ExecutionException {
        int[] data = new int[20];
        Random rand = new Random();
        for (int i = 0; i < data.length ; i++) {
            data[i] = rand.nextInt(100);
        }
        logger.info("{}", Arrays.toString(data));
        int max = MultithreadedMaxFinder.max(data);
        logger.info("Max:{}", max);
    }
}
