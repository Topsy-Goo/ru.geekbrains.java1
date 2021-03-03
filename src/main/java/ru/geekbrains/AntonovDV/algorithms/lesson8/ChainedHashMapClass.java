package ru.geekbrains.AntonovDV.algorithms.lesson8;

import java.util.*;

public class ChainedHashMapClass<K, V> implements HashMapInterface<K, V>
{
    private static final int SIZE_MIN = 16;
    private static final boolean REMOVE = true,
                                 GET = !REMOVE;
    Object[] map;
    private int size;


    private class MapEntry
    {
        K key;
        V value;
        int keyHashCode;

        MapEntry (K key, V value)
        {
            this.key = key;
            this.value = value;
            keyHashCode = keyHashCode (key);
        }

        @Override public boolean equals (Object o)
        {
            if (this == o)  return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapEntry e = (MapEntry)o;
            return this.key.equals (e.key);
        }

        @Override public String toString ()
        {
            return String.format ("<%s, %s>", key, value);
        }
    }// class MapEntry



    public ChainedHashMapClass (int size)
    {
        if (size < SIZE_MIN)    size = SIZE_MIN;

        map = new Object [size];
        Arrays.fill (map, null);
        this.size = 0;
    }


    private void checkKey (K key)
    {
        if (key == null)
            throw new IllegalArgumentException ("ERROR @ isKeyValid("+key+") : invalid key passed in.");
    }

    private int keyHashCode (K key)
    {
        return key.hashCode();
    }

    private int inmapIndex (K key)
    {
        return Math.abs(keyHashCode (key) % map.length);
    }


    @Override public void put (K key, V value)
    {
        //boolean boolOk = false;
        checkKey(key);

        MapEntry entry = new MapEntry (key, value);
        int inmapIndex = inmapIndex (key);
        LinkedList<MapEntry> basket = (LinkedList) map[inmapIndex];

        if (basket == null)
        {
            basket = new LinkedList<>();
            map[inmapIndex] = basket;
        }

        int inbasketIndex = basket.indexOf(entry);
        if (inbasketIndex >= 0)
        {
            basket.get(inbasketIndex).value = value;
        }
        else
        {
            basket.add (entry);
            size ++;
        }
        //return boolOk;
    }// put ()

// Содержит код общий для методов get() и remove().
    private V entryManager (K key, boolean remove)
    {
        V value = null;
        checkKey(key);
        if (size() > 0)
        {
            int inmapIndex = inmapIndex (key);
            LinkedList<MapEntry> basket = (LinkedList) map[inmapIndex];

            if (basket != null)
            {
                MapEntry entry = new MapEntry (key, null);
                int inbasketIndex = basket.indexOf(entry);

                if (inbasketIndex >= 0)
                {
                    value = basket.get(inbasketIndex).value;
                    if (remove == REMOVE)
                    {
                        basket.remove(inbasketIndex);
                    // Не уверен, что опустевшую корзину нужно удалять, но пусть в консоли пустые ячейки map выглядят одинаково.
                        if (basket.isEmpty())
                        {
                            map[inmapIndex] = null;
                            basket = null;
                        }
                    }
                }
            }
        }
        return value;
    }// entryManager ()


    @Override public V get (K key)   {   return entryManager (key, GET);   }

    @Override public V remove (K key)
    {
        V value = entryManager(key, REMOVE);
        if (value != null)  size --;
        return value;
    }// remove ()

    @Override public int size ()    {   return size;   }
    @Override public boolean isEmpty ()   {   return size() == 0;   }

    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder ("\n");
        for (Object list : map)
        {
            if (list == null)
                sb.append("---");
            else
                sb.append(list);

            sb.append('\n');
        }
        return sb.toString ();
    }// toString ()

}// class ChainedHashMapClass
