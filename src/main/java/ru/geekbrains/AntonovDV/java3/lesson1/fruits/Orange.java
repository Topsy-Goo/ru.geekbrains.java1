package ru.geekbrains.AntonovDV.java3.lesson1.fruits;


//  a) Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
public class Orange extends Fruit
{
    private final float weight;


    public Orange ()   {   weight = 1.5f;   }



    @Override public float getWeight ()   {   return weight;   }

    @Override public String toString () { return "Orange:"+weight; }

    public static void fillBox (Box<Orange> box, int quantity)
    {
        if (box != null)
            while (quantity-- > 0)
                box.add(new Orange());
    }

}// class Orange
