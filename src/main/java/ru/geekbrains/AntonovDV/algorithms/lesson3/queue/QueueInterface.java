package ru.geekbrains.AntonovDV.algorithms.lesson3.queue;

public interface QueueInterface<E>
{

    boolean insert (E value);

    E remove ();

    E peekHead ();

    int size ();

    boolean isEmpty ();

    boolean isFull ();


}// interface QueueInterface
