package com.multithreading.practice;

/**
 * Класс для демонстрации happens-before через Thread.start().
 */
public class DataHolder {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int i) {
        value = i;
    }
}

