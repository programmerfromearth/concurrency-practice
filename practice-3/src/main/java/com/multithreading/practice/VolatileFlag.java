package com.multithreading.practice;

/**
 * Класс с volatile флагом для демонстрации видимости изменений.
 */
public class VolatileFlag {
    private int number;
    private volatile boolean ready;

    public void setReady(int i) {
        number = i;
        ready = true;
    }

    public int waitForReady() {
        while (!ready) {
            //empty cycle
        }
        return number;
    }
}

