package com.multithreading.practice;

import java.util.concurrent.RecursiveTask;

/**
 * Задача для поиска максимального элемента в массиве с использованием ForkJoinPool.
 */
public class MaxTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start;
    private final int end;
    private static final int TRASHOLD = 2;

    public MaxTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int length = end - start;
        if (length <= TRASHOLD) {
            return findMax();
        } else {
            int mid = (end + start) / 2;
            MaxTask leftTask = new MaxTask(array, start, mid);
            MaxTask rightTask = new MaxTask(array, mid, end);

            leftTask.fork();

            int rightResult = rightTask.compute();
            int leftRestul = leftTask.join();

            return Math.max(rightResult, leftRestul);
        }
    }

    private int findMax() {
        return Math.max(array[start], array[end - 1]);
    }
}

