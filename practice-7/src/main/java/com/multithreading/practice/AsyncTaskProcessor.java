package com.multithreading.practice;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Класс для работы с асинхронными задачами через CompletableFuture.
 * <p>
 * Задание: Реализуйте методы так, чтобы все тесты проходили.
 * <p>
 * Подсказки:
 * - Используйте CompletableFuture.supplyAsync() для создания асинхронных задач
 * - thenApply() преобразует результат и возвращает новый CompletableFuture
 * - thenCompose() "разворачивает" вложенный CompletableFuture (аналог flatMap)
 * - thenCombine() комбинирует результаты двух независимых CompletableFuture
 * - allOf() ждет завершения всех задач
 * - exceptionally() обрабатывает только ошибки
 * - handle() обрабатывает и успех, и ошибку
 */
public class AsyncTaskProcessor {


    public CompletableFuture<String> createTransformationChain(String str) {
        return CompletableFuture.supplyAsync(() -> str)
                .thenApply(result -> result + " world")
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> createComposition(int number) {
        return CompletableFuture.supplyAsync(() -> number).thenCompose(this::getResultPlusNumber);
    }

    private CompletableFuture<String> getResultPlusNumber(Integer number) {
        return CompletableFuture.supplyAsync(() -> "Result: %s".formatted(number));
    }

    public CompletableFuture<String> combineResults(String hello, String world) {
        CompletableFuture<String> helloFeature = CompletableFuture.completedFuture(hello);
        CompletableFuture<String> worldFeature = CompletableFuture.completedFuture(world);

        return helloFeature.thenCombine(worldFeature, "%s %s"::formatted);
    }

    public CompletableFuture<Void> waitForAll(CompletableFuture<String> future1,
                                              CompletableFuture<String> future2,
                                              CompletableFuture<String> future3) {
        return CompletableFuture.allOf(future1, future2, future3);
    }

    public CompletableFuture<String> handleError(boolean handleErrorFlag) {
        CompletableFuture<String> future = CompletableFuture.failedFuture(new IllegalStateException());
        if (handleErrorFlag) {
            return future.exceptionally(ex -> "Обработано");
        }
        return future;
    }

    public CompletableFuture<String> handleSuccessOrError(boolean handleErrorFlag) {
        return CompletableFuture.supplyAsync(() -> {
                    if (handleErrorFlag) {
                        throw new IllegalStateException();
                    }
                    return "УСПЕХ";
                })
                .handle((result, ex) -> {
                    if (ex != null) {
                        return "Обработана ошибка";
                    }
                    return result;
                });
    }

    public void processResult(int input, Consumer<Integer> consumer) {
        CompletableFuture.supplyAsync(() -> input).thenAccept(consumer);
    }
}

