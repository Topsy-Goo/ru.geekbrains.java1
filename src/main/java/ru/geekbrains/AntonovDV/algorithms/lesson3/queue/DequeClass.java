package ru.geekbrains.AntonovDV.algorithms.lesson3.queue;

public class DequeClass<E> extends QueueClass<E> implements DequeInterface<E>
{
/*  «…Дек это интерфейс, который расширяет ф-нал нашей очереди
      и в котором будут новые 4 метода
      Написать класс реализующий этот интерфейс и наследующий от сласса-очереди. …»
*/

    public DequeClass (int maxSize)    {   super (maxSize);   }

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

    @Override public E peekTail()  {   return (isEmpty()) ? null : data[tail];   }
    @Override public E peekHead()  {   return (isEmpty()) ? null : data[head];   }

    @Override public E peek()    {   return peekHead();   }
    @Override public E remove () {   return removeHead();   }
    @Override public boolean insert (E value)    {   return insertTail (value);   }


}// class DequeClass
