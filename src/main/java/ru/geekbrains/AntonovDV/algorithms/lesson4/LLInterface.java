package ru.geekbrains.AntonovDV.algorithms.lesson4;

public interface LLInterface<E> extends Iterable<E>
{

    void insertFirst (E vlaue);
    E removeFirst ();
    boolean remove (E value);
    boolean contains (E value);

    int size ();
    boolean isEmpty ();
    void display ();

    E getFirst ();

}// interface LLInterface
