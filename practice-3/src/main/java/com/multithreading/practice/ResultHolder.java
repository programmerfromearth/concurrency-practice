package com.multithreading.practice;

/**
 * Класс для демонстрации happens-before через Thread.join().
 */
public class ResultHolder {
    private int number;

    public void setResult(int i) {
        number = i;
    }

    public int getResult() {
        return number;
    }
}

