package com.multithreading.practice;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс для работы с виртуальными потоками (Java 21+).
 * 
 * Задание: Реализуйте метод executeWithVirtualThreads так, чтобы все тесты проходили.
 * 
 * Подсказки:
 * - Используйте Executors.newVirtualThreadPerTaskExecutor() для создания ExecutorService
 * - Виртуальные потоки идеальны для I/O-bound задач
 * - Не забывайте закрывать ExecutorService (try-with-resources)
 */
public class VirtualThreadExecutor {

    public int executeWithVirtualThreads(List<Runnable> tasks) {
        int count = 0;
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Runnable task : tasks) {
                executorService.submit(task);
                count++;
            }
        }
        return count;
    }
}

