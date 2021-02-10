package ru.geekbrains.AntonovDV.algorithms.lesson3.queue;

public interface DequeInterface<E>
{
    // Мне показалось, что лучше не привязываться к понятиям «право» и «лево», и вместо этого
    // оперировать понятиями «голова» и «хвост».

    boolean insertHead (E value);

    boolean insertTail (E value);

    E removeHead ();

    E removeTail ();

    E peekHead ();

    E peekTail ();


    int size();

    boolean isEmpty();

    boolean isFull();

}// interface DequeInterface

//