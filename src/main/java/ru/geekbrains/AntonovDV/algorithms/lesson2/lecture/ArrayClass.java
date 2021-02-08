package ru.geekbrains.AntonovDV.algorithms.lesson2.lecture;

import java.util.Arrays;

import static java.lang.Integer.max;

public class ArrayClass<E extends Comparable<? super E>> implements ArrayInterface<E>
{
    public static final int CAPACITY_MIN = 8, CAPACITY_DEFAULT = CAPACITY_MIN;
    private E[] data = null;
    private int size = 0;
    private double growfactor = 1.5;


    @SuppressWarnings("unchecked") public ArrayClass(int capacity)
    {
        data = (E[]) new Comparable[max(capacity, CAPACITY_DEFAULT)];
    }

    public ArrayClass()
    {
        this(0);
    }

    public ArrayClass(int capacity, double gf)
    {
        this(capacity);
        setGrowFactor(gf);
    }

    protected void checkIndex(int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("ERROR @ ArrayClass.checkIndex(" + index + ") call : wrong index passed in.");
    }// checkIndex ()

    public void setGrowFactor(double gf)
    {
        if (gf >= 1.0)
            growfactor = gf;
    }

    @Override public boolean add(E value)
    {
        return addValue(value);
    }// add ()

    private boolean addValue(E value) //< чтобы дочерный класс не вызывал рекурсивно свою версию add().
    {
        if (value != null)
        {
            ensureCapacity(size + 1);
            data[size++] = value;
            return true;
        }
        return false;
    }// addValue ()

    public void ensureCapacity(int ensure)
    {
        if (data.length < ensure)
            data = Arrays.copyOf(data, (int) (growfactor * ensure));
    }

    @Override public E getAt(int index)
    {
        checkIndex(index);
        return data[index];
    }// get ()

    @Override public E removeAt(int index)
    {
        checkIndex(index);
        E result = data[index];

        while (index < size)
            data[index] = data[++index];

        data[--size] = null;
        return result;
    }// remove (indes)

    @Override public boolean removeFirst(E value)
    {
        int index = indexOf(value);
        if (index >= 0)
        {
            removeAt(index);
            return true;
        }
        return false;
    }// remove (value)

    @Override public int indexOf(E value)
    {
        for (int i = 0; i < size; i++)
            if (data[i].equals(value))
                return i;
        return -1;
    }// indexOf ()

    @Override public boolean contains(E value)
    {
        return indexOf(value) >= 0;
    }

    @Override public boolean isEmpty()
    {
        return size <= 0;
    }

    @Override public int size()
    {
        return size;
    }

    @Override public void display()
    {
        System.out.println(this);
    }

    @Override public String toString()
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
    //---------------------------------------

    @Override public boolean insertAt(int index, E value)
    {
        boolean boolOk = false;
        if (index == size)
        {
            boolOk = addValue(value); //< чтобы дочерный класс не вызывал рекурсивно свою версию add().
        } else
            if (value != null && index >= 0 && index < size)
            {
                int i = size;
                ensureCapacity(size + 1);

                while (i > index)
                    data[i] = data[--i];

                size++;
                data[index] = value;
                boolOk = true;
            }
        return boolOk;
    }// insertAt ()

    //---------------------------------------
    void swap(int a, int WW)
    {
        E tmp = data[a];
        data[a] = data[WW];
        data[WW] = tmp;
    }// swap ()

    // Сортировка пузырьком: бежим по массиву сравнивая соседние элементы.
    //O(n*n) - сравнение
    //O(n*n) - обмен
    public void sortBubble()
    {
        for (int j = 1; j < size; j++)
        {
            for (int i = 0; i < size - j; i++)
                if (data[i].compareTo(data[i + 1]) > 0)
                    swap(i, i + 1);
            //			if (j%10_000 == 0) System.out.print(j+".");
        }
    }// sortBubble ()

    // Сртировка выборкой: сравниваем первый элемент со всеми.
    // O(n*n) - сравнение
    // O(n) - обмен
    public void sortSelect()
    {
        for (int j = 0, min; j < size; j++)
        {
            min = j; //< чтобы не перестановлять элементы во внутреннем цикле
            for (int i = j + 1; i < size; i++)
                if (data[min].compareTo(data[i]) > 0)
                    min = i;
            swap(min, j);
            //			if (j%10_000 == 0) System.out.print(j+".");
        }
    }// sortSelect ()

    // Вставляем элементы из правой неотсортированной части в левую отсортированную.
    // O(n*n) - сравнение
    // O(n) - обмен
    public void sortInsert()
    {
        for (int i = 1; i < size; i++)
        {
            E tmp = data[i];
            int j = i - 1;

            while (j >= 0 && data[j].compareTo(tmp) > 0)
                data[j + 1] = data[j--];

            data[j + 1] = tmp;
            //			if (i%10_000 == 0) System.out.print(i+".");
        }
    }// sortInsert ()

}// class ArrayClass
