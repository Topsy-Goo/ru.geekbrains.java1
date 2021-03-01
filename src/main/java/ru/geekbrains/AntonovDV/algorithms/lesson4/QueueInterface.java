package ru.geekbrains.AntonovDV.algorithms.lesson4;

// Я перенёс этот файл в ДЗ-4, т.к. на момент написания ДЗ-4 ветка_3 была не видна из ветки_4,
// а портить историю репозитория не хотелось. Файл перенесён без изменений (включая комментарии).

public interface QueueInterface<E>
{
    boolean insert (E value);

    E remove ();

    E peek ();  // Я переименовал этот метод из peekHead(), чтобы его название не совпадало
                // с похожим методом интерфейса DequeInterface

    int size ();

    boolean isEmpty ();

    boolean isFull ();


}// interface QueueInterface
