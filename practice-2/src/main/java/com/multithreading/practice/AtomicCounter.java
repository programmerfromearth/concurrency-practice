package com.multithreading.practice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Потокобезопасный счетчик с использованием AtomicInteger.
 */
public class AtomicCounter {
    private final AtomicInteger atomicInteger = new  AtomicInteger(0);


    public void increment() {
        atomicInteger.incrementAndGet();
    }

    public int getCount() {
        return atomicInteger.get();
    }
}

