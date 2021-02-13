package ru.geekbrains.AntonovDV.algorithms.lesson4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLLClass<E> implements LLInterface<E>
{
    private Node<E> mostrecent = null;
    private int size = 0;

    private static class Node<E>
    {
        private final E value;
        private Node<E> next;

        Node (E value, Node<E> next)
        {
            this.value = value;
            this.next = next;
        }
    }// class Node

    @Override public Iterator<E> iterator ()
    {
        return new Iterator<E>()
        {
            private Node<E> node = isEmpty() ? null : mostrecent;

            @Override public boolean hasNext()   {   return node != null;   }
            @Override public E next() // отдаём значение ячейки, на которой стоим, и переходим на следующую
            {
                if (!hasNext())   throw new NoSuchElementException ("");
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }// iterator ()

    @Override public void insertFirst (E value)
    {
        mostrecent = new Node<> (value, mostrecent);
        size ++;
    }// insert ()

    @Override public E removeFirst ()
    {
        E value = null;
        if (!isEmpty())
        {
            Node<E> node = mostrecent;
            mostrecent = node.next;
            node.next = null;
            size --;
            value = node.value;
        }
        return value;
    }// removeMostRecent ()

    @Override public boolean remove (E value)
    {
        Node<E> node = mostrecent,
                prev = null;

//      if (!isEmpty())     // для отладки
        while (node != null)
        {
            if (node.value.equals (value))
            {
                if (prev != null)
                    prev.next = node.next;
                else
                    mostrecent = node.next;

                node.next = null;
                size --;
                break;
            }
            prev = node;
            node = node.next;
        }
        return node != null;
    }// remove ()

    @Override public boolean contains (E value)
    {
        Node<E> node = mostrecent;
//      if (!isEmpty())     // для отладки
        while (node != null)
        {
            if (node.value.equals (value))
                break;
            else node = node.next;
        }
        return node != null;
    }// contains ()

    @Override public int size ()        {   return size;   }
    @Override public void display ()    {   System.out.println (toString());   }
    @Override public boolean isEmpty ()
    {
        if (mostrecent == null && size == 0)    return true;
        if (mostrecent == null || size == 0)
            throw new RuntimeException ("ERROR @ isEmpty() : wrong condition detected.");
        return false;
    }

    @Override public E getFirst ()  {   return isEmpty() ? null : mostrecent.value;   }

    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder ("[");
        Node<E> node = mostrecent;
//      if (!isEmpty())     // для отладки
        while (node != null)
        {
            sb.append (node.value);
            if (node.next != null)
                sb.append (", ");
            node = node.next;
        }
        sb.append (']');
        return sb.toString ();
    }// toString ()


}// class SimpleLLClass
