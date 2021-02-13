package ru.geekbrains.AntonovDV.algorithms.lesson3_2.queue;


// Нельзя наследовать DequeClass от QueueClass! Просто нельзя и всё!
// Они даже интерфейсы разные реализуют и делают это не для того, чтобы наследовать доур от друга.
// И, если всё сделать правильно, то не придётся использовать одну струтуру данных на двоих!

public class DequeClass<E> /*extends QueueClass<E>*/ implements DequeInterface<E>
{
    private static final int INITIAL_HEAD = 0;
    private final E[] data;
    private int size;

    private int head;
    private int tail;

/*  «…Дек это интерфейс, который расширяет ф-нал нашей очереди
      и в котором будут новые 4 метода
      Написать класс реализующий этот интерфейс и наследующий от сласса-очереди. …»
*/
    public DequeClass (int maxSize)
    {
        this.data = (E[]) new Object[maxSize];
        this.head = INITIAL_HEAD;
        this.tail = head -1;
    }

    @Override public boolean insertTail (E value) // (аналогично QueueClass.insert (v) )
    {
        if (isFull())   return false;

        if (++tail >= data.length)
            tail = 0;

        data[tail] = value;
        size++;
        return true;
    }// insertTail ()

    @Override public boolean insertHead (E value)
    {
        if (isFull())   return false;

        if (--head < 0)
            head = data.length -1;

        data[head] = value;
        size++;
        return true;
    }// insertHead ()

    @Override public E removeHead () // (аналогично QueueClass.remove () )
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
    }// removeHead ()

    @Override public E removeTail ()
    {
        E value = null;
        if (!isEmpty())
        {
            value = data[tail];
            data[tail] = null;
            if (--tail < 0)
                tail = data.length -1;
            size--;
        }
        return value;
    }// removeTail ()

    @Override public E peekTail ()  {   return (isEmpty()) ? null : data[tail];   }
    @Override public E peekHead ()  {   return (isEmpty()) ? null : data[head];   }

//----------- следующие методы пригодятся при использовании Deque в качестве обычной очереди:

    @Override public E peek ()    {   return peekHead();   }
    @Override public E remove ()  {   return removeHead();   }
    @Override public boolean insert (E value)    {   return insertTail (value);   }

    @Override public int size ()    {   return size;   }
    @Override public boolean isEmpty ()   {   return size <= 0;   }
    @Override public boolean isFull ()    {   return size >= data.length;   }


}// class DequeClass
