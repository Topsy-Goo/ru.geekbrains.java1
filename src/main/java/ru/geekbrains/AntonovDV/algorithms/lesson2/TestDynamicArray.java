package ru.geekbrains.AntonovDV.algorithms.lesson2;

import ru.geekbrains.AntonovDV.algorithms.lesson2.lecture.ArrayClass;
import ru.geekbrains.AntonovDV.algorithms.lesson2.lecture.ArrayClassSorted;

import java.util.Random;

public class TestDynamicArray
{

    public static void main(String[] args)
    {
        int n = 1_000_000;
        ArrayClass<Integer> ac = new ArrayClass<>(n);
        //	ArrayClassSorted<Integer> acs = new ArrayClassSorted<>(n);
        Random rnd = new Random();

        for (int i = 0; i < n; i++)
        {
            ac.add(rnd.nextInt(n));
            //acs.add (rnd.nextInt (n));
            //if (i%10_000 == 0)	System.out.print(i+".");
        }

        System.out.println("\nraw");
        int sz = ac.size();
        for (int i = 0; i < 20; i++)            System.out.print(ac.getAt(i) + ".");
        System.out.println();
        for (int i = sz - 10; i < sz; i++)      System.out.print(ac.getAt(i) + ".");
        System.out.println();

    long start1 = System.currentTimeMillis(), stop1;
        //		ac.sortBubble();
        ac.sortSelect();
        //		ac.sortInsert();
    stop1 = System.currentTimeMillis();
    System.out.printf("\nСортировка массива из %d элементов заняла %d мс", n, (stop1 - start1));

        System.out.println();
        System.out.println("\nsorted");
        sz = ac.size();
        for (int i = 0; i < 20; i++)            System.out.print(ac.getAt(i) + ".");
        System.out.println();
        for (int i = sz - 10; i < sz; i++)      System.out.print(ac.getAt(i) + ".");

        System.out.println("\nac size = " + ac.size());
    }// main()

}// class TestDynamicArray

/*
ac.sortBubble();

Сортировка массива из 10000 элементов заняла 527 мс
Сортировка массива из 100000 элементов заняла 56518 мс
Сортировка массива из 1000000 элементов заняла ? мс (не дождался)

ac.sortSelect();

Сортировка массива из 10000 элементов заняла 153 мс
Сортировка массива из 100000 элементов заняла 13891 мс
Сортировка массива из 1000000 элементов заняла ? мс (не дождался)

ac.sortInsert();

Сортировка массива из 10000 элементов заняла 141 мс
Сортировка массива из 100000 элементов заняла 16506 мс
Сортировка массива из 1000000 элементов заняла ? мс (не дождался)

*/