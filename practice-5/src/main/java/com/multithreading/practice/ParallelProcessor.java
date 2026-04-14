package com.multithreading.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Класс для параллельной обработки данных.
 * <p>
 * Задание: Реализуйте метод processInParallel так, чтобы все тесты проходили.
 * <p>
 * Подсказки:
 * - Используйте ExecutorService для параллельного выполнения
 * - Преобразуйте каждый элемент входного списка в задачу
 * - Соберите результаты из Future
 */
public class ParallelProcessor {

    public List<Integer> processInParallel(List<Integer> input,
                                           Function<Integer, Integer> function,
                                           int poolSize) {
        final ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        try {
            final List<Future<Integer>> futures = new ArrayList<>();

            for (Integer item : input) {
                final Future<Integer> future = executorService.submit(() -> function.apply(item));
                futures.add(future);
            }

            final List<Integer> result = new ArrayList<>();
            for (Future<Integer> future : futures) {
                try {
                    result.add(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            return result;
        } finally {
            shutdownGracefully(executorService, 5, TimeUnit.SECONDS);
        }

    }

    public boolean shutdownGracefully(ExecutorService executorService, int i, TimeUnit timeUnit) {
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
}

