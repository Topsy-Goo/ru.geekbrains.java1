package ru.geekbrains.AntonovDV.algorithms.lesson3_2.stack;


public class StackClass<E> implements StackInterface<E>
{

    private final E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public StackClass (int maxSize)
    {
        this.data = (E[]) new Object[maxSize];
    }

    @Override public boolean push (E value)
    {
        if (isFull()) return false;
        else
        {
            data[size++] = value;
            return true;
        }
    }// push ()

    @Override public E pop ()    {   return (!isEmpty()) ? data[--size] : null;   }
    @Override public E peek ()   {   return (!isEmpty()) ? data[size -1] : null;   }
    @Override public int size () {   return size;   }

    @Override public boolean isEmpty () {   return size == 0;   }
    @Override public boolean isFull ()  {   return size == data.length;   }
    @Override public void display ()    {   System.out.println(this);   }

    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while (i < size)
        {
            sb.append(data[i++]);
            if (i < size)
                sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }// toString ()


}// class StackClass
