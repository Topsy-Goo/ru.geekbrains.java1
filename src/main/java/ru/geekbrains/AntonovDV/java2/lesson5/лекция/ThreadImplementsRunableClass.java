package ru.geekbrains.AntonovDV.java2.lesson5.лекция;

// Создание потока - Способ 2 (не самый лучший).
public class ThreadImplementsRunableClass
             implements Runnable
{
// Запуск такого потока возможен только через преобразование типа (см. Test.main()).
// При таком методе создания потока мы оставляем себе возможность наследования от какого-нибудь класса.



// Здесь мы ОБЯЗАНЫ реализовать run().
    @Override public void run ()
    {
        //super.run (); < здесь это уже невозможно
        System.out.printf ("\tВыполнение потока ThreadImplementsRunableClass (имя потока: %s).\n",
                           Thread.currentThread().getName());

    }// run ()


}// class ThreadImplementsRunableClass

