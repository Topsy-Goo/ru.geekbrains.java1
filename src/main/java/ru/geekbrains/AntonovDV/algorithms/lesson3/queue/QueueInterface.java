package ru.geekbrains.AntonovDV.algorithms.lesson3.queue;

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


//