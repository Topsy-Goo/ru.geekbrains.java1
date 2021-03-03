package ru.geekbrains.AntonovDV.algorithms.lesson8;

public interface HashMapInterface<K, V>
{

    void put (K key, V value);

    V get (K key);

    V remove (K key);

    int size ();

    boolean isEmpty ();

}// interface ChainedHashMapInterface
