package ru.geekbrains.AntonovDV.algorithms.lesson2.lecture;

public interface ArrayInterface<E>
{
    boolean add(E value);

    E getAt(int index);

    boolean removeFirst(E value);

    E removeAt(int index);

    int indexOf(E value);

    boolean contains(E value);

    boolean isEmpty();

    int size();

    void display();

    //-------------------------------
    boolean insertAt(int index, E value);
    //-------------------------------
    //	void sortBubble ();
    //	void sortSelect ();
    //	void sortInsert ();

}// interface ArrayInterface
