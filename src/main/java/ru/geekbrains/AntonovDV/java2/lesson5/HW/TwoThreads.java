package ru.geekbrains.AntonovDV.java2.lesson5.HW;

import java.util.Arrays;

public class TwoThreads
{
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;


//Создаём массив, заполняем его 1-цами, для каждой ячейки считаем новое значение по заданной в задании формуле.
    public static long threadArray (int size)
    {
        float[] arr = new float [size];
        Arrays.fill (arr, 1.0f);

        long start = System.currentTimeMillis(),
             finish;

        dowork (arr, 0);

        finish = System.currentTimeMillis();
        return finish - start;
    }//  threadArray ()


//Создаём массив, заполняем его 1-цами, делим на два равных массива, в каждом массиве для каждой ячейки считаем
// новое значение по заданной в задании формуле, склеиваем массивы.
    public static long thread2HalfArray (int size)
    {
        float[] arr = new float [size],
                half1 = new float[HALF],
                half2 = new float[HALF];
        Arrays.fill (arr, 1.0f);

        long startTime = System.currentTimeMillis(),
             finishTime;

        System.arraycopy (arr, 0,    half1, 0, HALF);
        System.arraycopy (arr, HALF, half2, 0, HALF);

        Thread thread1 = new Thread (() -> dowork (half1, 0));
        Thread thread2 = new Thread (() -> dowork (half2, HALF));
        thread1.start ();
        thread2.start ();

        try
        {
            thread1.join ();
            thread2.join ();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace ();
        }

        System.arraycopy (half1, 0, arr, 0,    HALF);
        System.arraycopy (half2, 0, arr, HALF, HALF);

        finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }// thread2HalfArray ()


// (Вспомогательный метод, выполняющий вычисления для каждой ячейки массива.)
    static void dowork (float[] arr, int start)
    {
        //synchronized (new Object()) < наверное, в таком подходе нет видимого смысла, но, теоретически, где-то в недрах sin или cos могут быть обращения с статическим переменным, и тогда…
        {
            for (int i=0, n= arr.length;   i < n;   i++)
            {
                float fi = (float)(i + start);
                arr[i] = (float)(arr[i]
                                     * Math.sin(0.2f + fi/5)
                                     * Math.cos(0.2f + fi/5)
                                     * Math.cos(0.4f + fi/2));
            }
        };
    }// dowork ()


    public static void main (String[] args)
    {
        System.out.printf ("\nВремя выполнения метода threadArray(%d) составило %d мс.\n", SIZE, threadArray (SIZE));
        System.out.printf ("\nВремя выполнения метода thread2HalfArray(%d) составило %d мс.\n", SIZE, thread2HalfArray (SIZE));

    }// main ()

}// class TwoThreads
