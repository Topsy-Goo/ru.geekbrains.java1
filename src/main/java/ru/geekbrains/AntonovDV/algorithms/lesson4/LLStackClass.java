package ru.geekbrains.AntonovDV.algorithms.lesson4;

import java.util.LinkedList;

public class LLStackClass<E> extends SimpleLLClass<E> implements StackInterface<E>
{

    @Override public boolean push (E value)
    {
        super.insertFirst (value);
        return true;
    }
    @Override public E pop()    {   return super.removeFirst();   }
    @Override public E peek()   {   return super.getFirst();   }
    @Override public int size() {   return super.size();   }

    @Override public boolean isEmpty() {   return super.isEmpty();   }
    @Override public boolean isFull()  {   return false;   }
    @Override public void display()    {   super.display();   }
//*/

/*
    private final LLInterface<E> data;

    public LLStackClass ()   {   this.data = new SimpleLLClass<>();   }

    @Override public boolean push (E value)
    {
        data.insertFirst (value);
        return true;
    }
    @Override public E pop()    {   return data.removeFirst();   }
    @Override public E peek()   {   return data.getFirst();   }
    @Override public int size() {   return data.size();   }

    @Override public boolean isEmpty() {   return data.isEmpty();   }
    @Override public boolean isFull()  {   return false;   }
    @Override public void display()    {   data.display();   }
//*/

}// class LLStackClass
