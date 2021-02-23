package ru.geekbrains.AntonovDV.algorithms.lesson6;

import java.util.Iterator;

public interface TreeInterface<E extends Comparable<? super E>> extends Iterable<E>
{
    boolean add (E vlaue);
    boolean contains (E value);
    boolean remove (E value);

    Iterator<E> iterator ();

    boolean isEmpty ();
    int size ();
    void display ();

}// interface TreeInterface
