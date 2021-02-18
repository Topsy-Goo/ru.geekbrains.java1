package ru.geekbrains.AntonovDV.algorithms.lesson5;

import ru.geekbrains.AntonovDV.algorithms.lesson2.lecture.ArrayClass;

public class Backpack<E extends Comparable <? super Thing>>
{
    private final int capacity;
    private int maxload = 0,
                maxprice = 0;
    private ArrayClass<Thing> things,
                              load = null;

    private final int initialSize; //< для отладки (стремимся к идеалу, когда количество перестановок равно факториалу размера массива)
    private int calls = 0, rearrangements = 0, fittings = 0;  //< для отладки


    public Backpack (int capacity, ArrayClass<Thing> things)
    {
        this.capacity = capacity;
        this.things = things;
        initialSize = things.size();

        anagramThings (things, 0);
        this.things = load;
    }


// Собственно рекурсивный метод.
    public void anagramThings (ArrayClass<Thing> ooo, final int start)
    {
        calls ++;
        if (ooo == null || start >= ooo.size())
            return;

        int gap = ooo.size() - (start+1);   Thing t = ooo.getAt(start);

        if (gap == 0) //< для случаев, когда исходный массив содержит толькоодну вещь.
        {
            //System.out.println (ooo);
            tryOnBackpack (ooo);
            rearrangements ++;
        }
        else if (gap == 1)
        {
            //System.out.println (ooo);
            tryOnBackpack (ooo);
            ooo.add (ooo.removeAt (start));
            rearrangements ++;

            //System.out.println (ooo);
            tryOnBackpack (ooo);
            ooo.add (ooo.removeAt (start));
            rearrangements ++;
        }
        else while (gap-- >= 0)
        {
            anagramThings (ooo, start +1);
            ooo.add (ooo.removeAt (start));
            rearrangements ++;
        }
    }// anagramObjects ()


// (Вспомогательная.) Примеряем очередную перестановку к нашему рюкзаку и сравниваем с результатми предыдущих примерок.
    private void tryOnBackpack (ArrayClass<Thing> ooo)
    {
        int maxload = 0,
            maxprice = 0;
        ArrayClass<Thing> things = new ArrayClass<>();

    // помещаем вещи в рюкзак в порядке их следования в массиве до тех пор, пока очередная вещь
    // не сможет поместиться:
        for (int i=0, n= ooo.size();    i<n;    i++)
        {
            Thing t = ooo.getAt(i);
            int w = t.getWeight();

            if (maxload + w > capacity)
                break;
            maxprice += t.getPrice();
            maxload += w;
            things.add(t);
        }
    // сохраняем результат примерки текущего порядка вещей к нащему рукзаку:
        if (maxprice > this.maxprice)
        {
            this.maxprice = maxprice;
            this.maxload = maxload;
            this.load = things;
        }
        fittings ++;
    }// tryOnBackpack ()



    private static final String tostringFormat =
                "\nВ рюкзак поместились  (вес-цена): %s\n\t(общий вес %d, общая стоимость %d);" +
                "\nвызовов %d, перестановок %d, примерок %d (начальное количество вещей %d)";

    @Override public String toString ()
    {
        return String.format (tostringFormat,
                              ((things != null) ? things.toString() : "[]"),
                              maxload, maxprice,
                              calls, rearrangements, fittings,
                              initialSize);
    }// toString ()


}// Backpack

