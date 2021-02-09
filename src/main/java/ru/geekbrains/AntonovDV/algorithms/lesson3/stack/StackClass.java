package ru.geekbrains.AntonovDV.algorithms.lesson3.stack;

public class StackClass<E> implements StackInterface<E>
{

    private final E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public StackClass (int maxSize)
    {
        this.data = (E[]) new Object[maxSize];
    }

    @Override public void push(E value)
    {
        data[size++] = value;
    }

    @Override public E pop()
    {
        return data[--size];
    }

    @Override public E peek()
    {
        return data[size - 1];
    }

    @Override public int size()
    {
        return size;
    }

    @Override public boolean isEmpty()
    {
        return size == 0;
    }

    @Override public boolean isFull()
    {
        return size == data.length;
    }

    @Override public void display()
    {
        System.out.println(this);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++)
        {
            sb.append(data[i]);
            sb.append(", ");
        }
        if (size > 0)
        {
            sb.append(data[size - 1]);
        }
        sb.append("]");

        return sb.toString();
    }


}// class StackClass
