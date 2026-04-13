package com.multithreading.practice;

/**
 * Singleton с правильным Double-Checked Locking и volatile.
 */
public class Singleton {
    private static volatile Singleton INSTANCE;

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}

