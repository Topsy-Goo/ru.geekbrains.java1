package ru.geekbrains.AntonovDV.algorithms.lesson5;

public class Thing implements Comparable<Thing>
{
    private final int weight,
                      price;

    public Thing (int weight, int price)
    {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight ()  {   return weight;   }
    public int getPrice ()   {   return price;    }

    @Override public int compareTo (Thing t)    {   return 0;   }
    @Override public String toString ()
    {
        return String.format ("%d-%d", weight, price);  // String.valueOf (weight);//
    }

}// Thing
