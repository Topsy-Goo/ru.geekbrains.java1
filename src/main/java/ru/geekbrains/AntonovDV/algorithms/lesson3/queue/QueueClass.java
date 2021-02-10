package ru.geekbrains.AntonovDV.algorithms.lesson3.queue;

public class QueueClass<E> implements QueueInterface<E>
{
    private static final int INITIAL_HEAD = 0;
    protected final E[] data;
    protected int size;

    protected int head;
    protected int tail;

    @SuppressWarnings("unchecked")
    public QueueClass (int maxSize)
    {
        this.data = (E[]) new Object[maxSize];
        this.head = INITIAL_HEAD;
        this.tail = head -1;
    }

    @Override public boolean insert (E value)
    {
        if (isFull())   return false;

        if (++tail >= data.length)
            tail = 0;

        data[tail] = value;
        size++;
        return true;
    }// insert ()

    @Override public E remove ()
    {
        E value = null;
        if (!isEmpty())
        {
            value = data[head];
            data[head] = null;
            if (++head >= data.length)
                head = 0;
            size--;
        }
        return value;
    }// remove ()

    @Override public E peek()  {   return (isEmpty()) ? null : data[head];   }
    @Override public int size()    {   return size;   }
    @Override public boolean isEmpty()   {   return size == 0;   }
    @Override public boolean isFull()    {   return size == data.length;   }

    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder("[");
        int i = 0,
                pointer = head;
        while (i++ < size)
        {
            sb.append (data[pointer]);
            if (pointer != tail)
                sb.append(", ");

            if (++pointer >= data.length)
                pointer = 0;
        }
        sb.append(']');
        return sb.toString();
    }// toString ()


}// class QueueClass

