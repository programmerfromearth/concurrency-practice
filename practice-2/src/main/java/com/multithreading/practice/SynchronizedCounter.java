package com.multithreading.practice;

/**
 * Потокобезопасный счетчик с использованием synchronized.
 */
public class SynchronizedCounter {
    private int count;
    private final Object lock = new  Object();


    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}

