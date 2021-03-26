package ru.geekbrains.AntonovDV.java3.lesson1.fruits;


//  a) Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
public class Orange extends Fruit
{
    protected final float weight; //< поменяли private на protected (по результатам разбора ДЗ)


    public Orange ()   {   weight = 1.5f;   }

    @Override public float getWeight ()   {   return weight;   }

    @Override public String toString () { return "Orange:"+weight; }

}// class Orange
