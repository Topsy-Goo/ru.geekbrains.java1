package ru.geekbrains.AntonovDV.algorithms.lesson2.lecture;

public class ArrayClassSorted<E extends Comparable<? super E>> extends ArrayClass<E>
/*	Запись
		E extends Comparable <? super E>
	означает, что E должен реализовывать Comparable сам или через любого из родителей.
*/
{
    public ArrayClassSorted()
    {
        this(0);
    }

    public ArrayClassSorted(int capacity)
    {
        super(capacity);
    }

    public ArrayClassSorted(int capacity, double gf)
    {
        super(capacity, gf);
    }
/*
	public E get (int index);
	public boolean removeFirst (E value);
	public E removeAt (int index);

	public boolean contains (E value);
	public boolean isEmpty ();
	public int size ();
	public void display ();
//*/

    @Override public boolean add(E value)
    {
        boolean boolOk = false;
        if (value != null)
        {
            int index = 0;
            for (int n = size(); index < n; index++)
            {
                if (getAt(index).compareTo(value) > 0)
                    break;
            }
            boolOk = super.insertAt(index, value);
        }
        return boolOk;
    }// add ()

    @Override public int indexOf(E value)
    {
        int head = 0, tail = size() - 1, index, cmp;
        while (head <= tail)
        {
            index = (head + tail) / 2;
            cmp = getAt(index).compareTo(value);

            if (cmp == 0)
            {
                // Одинаковые значения могут идти подряд. Нас интересует первое из них.
                if (index > 0)
                    while (getAt(index - 1).equals(value))
                        index--;
                return index;
            }
            if (cmp < 0)
                head = index + 1;
            else
                tail = index - 1;
        }
        return -1;
    }// indexOf ()

    //--------------------------------------------------------
    @Override public boolean insertAt(int index, E value)
    {
        return false;
    }

}// class ArrayClassSorted
