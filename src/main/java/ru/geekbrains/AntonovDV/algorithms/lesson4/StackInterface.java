package ru.geekbrains.AntonovDV.algorithms.lesson4;

// Я перенёс этот файл в ДЗ-4, т.к. на момент написания ДЗ-4 ветка_3 была не видна из ветки_4,
// а портить историю репозитория не хотелось. Файл перенесён без изменений.

public interface StackInterface<E>
{

    boolean push(E value);

    E pop();

    E peek();

    int size();

    boolean isEmpty();

    boolean isFull();

    void display();


}// interface StackInterface
