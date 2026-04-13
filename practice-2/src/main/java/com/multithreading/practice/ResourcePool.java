package com.multithreading.practice;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Пул ресурсов с использованием Semaphore.
 */
public class ResourcePool {
    private final int poolSize;
    private final Semaphore semaphore;

    public ResourcePool(int poolSize) {
        this.poolSize = poolSize;
        semaphore = new Semaphore(poolSize);
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
    }

    public int getActiveCount() {
        return poolSize - semaphore.availablePermits();
    }

    public void release() {
        semaphore.release();
    }
}

