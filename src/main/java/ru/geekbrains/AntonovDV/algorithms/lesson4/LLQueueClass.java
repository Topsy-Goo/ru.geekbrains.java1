package ru.geekbrains.AntonovDV.algorithms.lesson4;

public class LLQueueClass<E> extends DoubleEndedLLClass<E> implements QueueInterface<E>
{

    @Override public boolean insert (E value)
    {
        super.insertLast(value);
        return true;
    }

    @Override public E remove()  {   return super.removeFirst();   }
    @Override public E peek()    {   return super.getFirst();   }

    @Override public boolean isFull()    {   return false;   }
    @Override public int size()  {   return super.size();   }

}// class LLQueueClass
