package ru.geekbrains.AntonovDV.algorithms.lesson5;

import ru.geekbrains.AntonovDV.algorithms.lesson2.lecture.ArrayClass;
import java.util.Random;
import static java.lang.Integer.max;
import static java.lang.Math.abs;

public class Test
{

    public static void main(String[] args)
    {
        System.out.println();
        System.out.println (calcPowerOf (-2, -1));
        backpack (5, 7);

    }// main ()

/*  1. Написать программу по возведению числа в степень с помощью рекурсии.

    Степень числа power — произвольное целое число; N — произвольное число (пренебрегаем погрешностью, возникающей
    при выполнении арифметических операций с double).

    Для удобства создан метод calcMultiplyNTimes(), умножающий одно число на другое заданное количество раз.
    Собственно, этот метод и вызывается рекурсивно.
*/
    public static double calcPowerOf (double N, int power)
    {
        if (power == 0)
        {
            if (N == 0)
                throw new ArithmeticException ();
            return 1;
        }
        if (power < 0)   N = 1/N;

        return calcMultiplyNTimes (N, N, abs(power));
    }// calcPowerOf ()

// (Вспомогательная.) Умножаем число N на число factor количество раз заданое в times.
    public static double calcMultiplyNTimes (double N, final double factor, int times)
    {
        if (times <= 0)
            throw new ArithmeticException ();

        if (N == 0)   return 0;
        if (factor == 1)   return N;

        return (times > 1) ? calcMultiplyNTimes (N * factor, factor, times-1) : N;
    }// calcMultiplyNTimes ()


/*  2. Написать программу «Задача о рюкзаке» с помощью рекурсии.

    «Условие: Имеется рюкзак с ограниченной вместимостью по массе; также имеется набор вещей с определенным весом и ценностью.
     Необходимо подобрать такой набор вещей, чтобы он помещался в рюкзаке и имел максимальную ценность (стоимость).»

    Для хранения элементов используется список  ArrayClass из 2-го урока.
    Класс Thing изображает вещь для помещения в рюкзак.
    В конструкторе рюкзак получает список вещей и загружает их в себя в соответствии с условием задачи.

    После завершения работы конструктора задача может считаться выполненной. Для демонстрации выполнения
    задачи на экран выводятся исходный список вещей (в формате вес-цена) и содержимое рюкзака (в том же формате).
*/
    public static void backpack (int amount, int capacity)
    {
    // Инициализация массива:
        Random rnd = new Random();
        int rangeWeight = 10,
            rangePrice = 10;

        ArrayClass<Thing> aT = new ArrayClass<>(amount);

    // заполняем массив случайными значениями
        for (int i=0; i<amount; i++)
        {
            int weight = max(rnd.nextInt (rangeWeight), 1),
                price = max(rnd.nextInt (rangePrice), 1);

            aT.add (new Thing (weight, price));
        }//*/

/*        aT.add (new Thing (9,7));
        aT.add (new Thing (8,6));
        aT.add (new Thing (4,1));
        aT.add (new Thing (2,1));
        aT.add (new Thing (1,2));
        aT.add (new Thing (6,5));
        aT.add (new Thing (7,8));
        //*/
        System.out.println ("\nИсходный список вещей (вес-цена): "+aT + String.format (";" +
                            "\nВместимость рюкзака: %d", capacity));

    //Кладём вещи в мешок:
        Backpack<Thing> bp = new Backpack<> (capacity, aT);

        System.out.println (bp);
    }// backpack ()


}// class Test

