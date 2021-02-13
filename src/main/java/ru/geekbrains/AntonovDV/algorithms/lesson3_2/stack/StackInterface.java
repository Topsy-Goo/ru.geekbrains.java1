package ru.geekbrains.AntonovDV.algorithms.lesson3_2.stack;

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
