package com.multithreading.practice;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Задача для суммирования элементов массива с использованием ForkJoinPool.
 */
public class SumTask extends RecursiveTask<Long> {
    private final long[] array;
    private final int start;
    private final int end;
    private static final int TRASHOLD = 100;

    public SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= TRASHOLD) {
            return sum();
        } else {
            int mid = (end + start) / 2;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);

            final ForkJoinTask<Long> leftFork = left.fork();

            long rightResult = right.compute();
            long leftRestul = leftFork.join();

            return rightResult + leftRestul;
        }
    }

    private long sum() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }
}

