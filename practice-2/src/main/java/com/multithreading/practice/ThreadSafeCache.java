package com.multithreading.practice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Потокобезопасный кэш с использованием ReadWriteLock.
 */
public class ThreadSafeCache<K, V> {
    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();


    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public V get(K key) {
        lock.readLock().lock();
        try {
           return cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }
}

