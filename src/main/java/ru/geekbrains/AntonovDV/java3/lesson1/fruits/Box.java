package ru.geekbrains.AntonovDV.java3.lesson1.fruits;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

//  b) Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
//  поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
public class Box<T extends Fruit>
{
    private final List<T> list;

    public Box ()
    {
//  c) Для хранения фруктов внутри коробки можно использовать ArrayList;
        list = new ArrayList<T>();
    }

    public int size ()   { return list.size(); }


//  d) Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их
//  количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);

    public double getWeight ()
    {
        double weight = 0.0;

        for (T t : list)
            weight += t.getWeight();

        return weight;
    }


//  e) Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
//  которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном
//  случае. Можно сравнивать коробки с яблоками и апельсинами;

    public boolean compare (Box<? extends Fruit> anotherBox)
    {
        return ((Double)getWeight()).equals (anotherBox.getWeight());
    }


//  f) Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним
//  про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в
//  текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;

    public void moveTo (Box<T> box)
    {
        if (box != null)
        {
            for (T t : list)
                box.add (t);

            list.clear();
        }
    }// moveTo ()


//  g) Не забываем про метод добавления фрукта в коробку.
    public boolean add (T t)
    {
        return list.add(t);
    }

// удаляем один верхний фрукт из коробки
    public T remove ()
    {
        T t = null;
        int index = size() -1;
        if (index >= 0)
            t = list.remove (index);
        return t;
    }

    @Override public String toString () { return list.toString(); } // для отладки

}// class Box
