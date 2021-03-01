package ru.geekbrains.AntonovDV.algorithms.lesson4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleEndedLLClass<E> implements DoubleEndedLLInterface<E>
{
    // (java.util.LinkedList наследует только абстрактному классу и т.о. не наследует чьи-либо «стратегические» переменные.)
    private BdNode<E> first = null,
                    last = null;
    private int size = 0;


    private static class BdNode<E>
    {
        private final E value;
        private BdNode<E> next, prev;

        BdNode (E value, BdNode<E> prev, BdNode<E> next)
        {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }// class BdNode

    @Override public Iterator<E> iterator () // DoubleEndedLLInterface наследует Iterable через LLInterface
    {
        return new Iterator<E>()
        {
            private BdNode<E> node = isEmpty() ? null : first;

            @Override public boolean hasNext()  {   return node != null;   }
            @Override public E next () // отдаём значение ячейки, на которой стоим, и переходим на следующую
            {
                if (!hasNext())   throw new NoSuchElementException("");
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }// iterator ()


    @Override public void insertFirst (E value)
    {
        BdNode<E> node = new BdNode<> (value, null, first);

        if (!isEmpty())   first.prev = node;
        else
            last = node;
        first = node;
        size ++;
    }
    @Override public void insertLast (E value)
    {
        BdNode<E> node = new BdNode<> (value, last, null);

        if (!isEmpty())   last.next = node;
        else
            first = node;
        last = node;
        size ++;
    }

    @Override public E removeFirst ()
    {
        E value = null;
        if (!isEmpty())
        {
            BdNode<E> node = first;
            first = node.next;

            if (first != null)
                first.prev = null;
            else
                last = null; // элемент был единственный в списке

            node.next = null;
            size --;
            value = node.value;
        }
        return value;
    }
    @Override public E removeLast ()
    {
        E value = null;
        if (!isEmpty())
        {
            BdNode<E> node = last;
            last = node.prev;

            if (last != null)
                last.next = null;
            else
                first = null; // элемент был единственный в списке

            node.prev = null;
            size --;
            value = node.value;
        }
        return value;
    }

    @Override public boolean remove (E value)
    {
        BdNode<E> node = first;
//      if (!isEmpty())     // для отладки
        while (node != null)
        {
            if (node.value.equals (value))
            {
                if (node.prev != null)  // элемент НЕ является первым элементом списка
                    node.prev.next = node.next;
                else                    // елемент оказался первым в списке
                first = node.next;

                if (node.next != null)  // элемент НЕ является последним элементом списка
                    node.next.prev = node.prev;
                else                    // елемент оказался последним в списке
                last = node.prev;

                node.next = node.prev = null;
                size --;
                break;
            }
            node = node.next;
        }
        return node != null;
    }// remove ()

//-------- Следующие методы повторяют одноимённые им методы класса SimpleLLClass. -----------

    @Override public boolean contains (E value)
    {
        BdNode<E> node = first;
//      if (!isEmpty())     // для отладки
        while (node != null)
        {
            if (node.value.equals (value))
                break;
            else node = node.next;
        }
        return node != null;
    }// contains ()

    @Override public int size ()     {   return size;   }
    @Override public void display () {   System.out.print (toString());   }
    //@Override
    public E getFirst ()    {   return isEmpty() ? null : first.value;   }

    @Override public boolean isEmpty ()
    {
        if (first == null && last == null && size == 0)   return true;
        if (first == null || last == null || size == 0)
            throw new RuntimeException ("ERROR @ isEmpty() : wrong condition detected.");
        return false;
    }

    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder ("[");
        BdNode<E> node = first;
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


}// class DoubleEndedLLClass
