package com.multithreading.practice;

import java.util.List;
import java.util.concurrent.*;

/**
 * Класс для обработки задач через ExecutorService.
 * <p>
 * Задание: Реализуйте методы так, чтобы все тесты проходили.
 * <p>
 * Подсказки:
 * - Используйте Executors.newFixedThreadPool() для создания пула потоков
 * - Не забывайте вызывать shutdown() и awaitTermination()
 * - Правильно обрабатывайте исключения при получении результатов Future
 */
public class TaskProcessor {
    private final ExecutorService executorService;

    public TaskProcessor(int poolSize) {
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public <T> Future<T> processTask(Callable<T> runnable) {
        return executorService.submit(runnable);
    }

    public boolean shutdownGracefully(int i, TimeUnit timeUnit) {
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(i, timeUnit)) {
                executorService.shutdownNow();

                if (!executorService.awaitTermination(i, timeUnit)) {
                    System.err.println("Executor did not terminate");
                    return false;
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            return false;
        }

        return true;
    }

    public boolean isTerminated() {
        return executorService.isTerminated();
    }
}

