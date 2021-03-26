package ru.geekbrains.AntonovDV.java3.lesson1.fruits;


//  a) Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
public class Apple extends Fruit
{
    protected final float weight; //< поменяли private на protected (по результатам разбора ДЗ)


    public Apple ()   {   weight = 1.0f;   }

    @Override public float getWeight ()    {   return weight;   }

    @Override public String toString () { return "Apple:"+weight; }

}// class Apple
