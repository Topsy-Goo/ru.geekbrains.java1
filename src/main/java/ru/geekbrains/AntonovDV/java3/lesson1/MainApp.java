package ru.geekbrains.AntonovDV.java3.lesson1;

import ru.geekbrains.AntonovDV.java3.lesson1.fruits.Apple;
import ru.geekbrains.AntonovDV.java3.lesson1.fruits.Box;
import ru.geekbrains.AntonovDV.java3.lesson1.fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;

public class MainApp
{

//  1.   Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа).
//      (При разборе ДЗ преподаватель сказал, что женерики здесь не нужны.)
    public static <T> void swapObjects (T[] array, int i, int j)
    {
        if (array != null  &&
            i >= 0  &&
            j >= 0  &&
            array.length > i  &&
            array.length > j)
        {
            T tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }// swapObjects ()

//  2.   Написать метод, который преобразует массив в ArrayList.
    public static <E> ArrayList<E> arrayToArrayList (E[] array)
    {
        return (array != null)
               ? new ArrayList<>(Arrays.asList(array))
               : null;
    }// arrayToArrayList ()


/*  3.  Задача:
    a) Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
    b) Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
       поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
    c) Для хранения фруктов внутри коробки можно использовать ArrayList;
    d) Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их
       количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
    e) Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
       которую подадут в compare() в качестве параметра.
        true – если их массы равны,
        false в противоположном случае.
       Можно сравнивать коробки с яблоками и апельсинами;
    f) Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним
       про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в
       текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
    g) Не забываем про метод добавления фрукта в коробку.

*/


    public static void main (String[] args)
    {
        String[] as = {"red", "green", "blue"};
        Integer[] ai = {1,20,3,100};

    // Иллюстрация к заданию 1:
        System.out.println ("\nИллюстрация к заданию 1");
        System.out.println (Arrays.asList(as));
        System.out.println (Arrays.asList(ai));
        swapObjects (as, 1, 2);
        swapObjects (ai, 0, 3);
        System.out.println (Arrays.asList(as));
        System.out.println (Arrays.asList(ai));

    // Иллюстрация к заданию 2:
        System.out.println ("\nИллюстрация к заданию 2");
        System.out.println (arrayToArrayList(ai));
        System.out.println (arrayToArrayList(as));

    // Иллюстрация к заданию 3:
        System.out.println ("\nИллюстрация к заданию 3");
        Box<Apple> applebox = new Box<>();
        Box<Orange> orangebox = new Box<>();
        Apple apple = new Apple();
        Orange orange = new Orange();

        applebox.fillBox (apple, 4);    //< вызывает Box.add(new Apple()) N раз
        orangebox.fillBox (orange, 5);  //< вызывает Box.add(new Orange()) N раз
        //Apple.fillBox (orangebox, 2); < сыпать апельсины к яблокам не позволяет компилятор

            System.out.printf ("\napplebox : %s (quantity %d, weight = %f)",
                               applebox, applebox.size(), applebox.getWeight());
            System.out.printf ("\norangebox : %s (quantity %d, weight = %f)",
                               orangebox, orangebox.size(), orangebox.getWeight());

            System.out.printf ("\n\ncomparison (orangebox vs applebox) : %b\n", orangebox.compare(applebox));

        applebox.fillBox (apple, -2);
            System.out.printf ("\napplebox : %s (quantity %d)", applebox, applebox.size());

        Box<Apple> applebox2 = new Box<>();
        applebox2.fillBox (apple, 3);
            System.out.printf ("\napplebox2 : %s (quantity %d)", applebox2, applebox2.size());

        applebox.moveTo(applebox2);
            System.out.printf ("\napplebox2 : %s (quantity %d)", applebox2, applebox2.size());
            System.out.printf ("\napplebox : %s (quantity %d)", applebox, applebox.size());

        //applebox.moveTo(orangebox);   < сыпать апельсины к яблокам не позволяет компилятор

    }// main ()


}// class MainApp
