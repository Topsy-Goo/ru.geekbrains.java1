package ru.geekbrains.AntonovDV.algorithms.lesson3.stack;

public interface StackInterface<E>
{

    void push(E value);

    E pop();

    E peek();

    int size();

    boolean isEmpty();

    boolean isFull();

    void display();


}// interface StackInterface
