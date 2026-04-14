package com.multithreading.practice;

import java.util.concurrent.CompletableFuture;

/**
 * Класс для работы с асинхронными задачами через CompletableFuture.
 * 
 * Задание: Реализуйте методы так, чтобы все тесты проходили.
 * 
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
    

}

