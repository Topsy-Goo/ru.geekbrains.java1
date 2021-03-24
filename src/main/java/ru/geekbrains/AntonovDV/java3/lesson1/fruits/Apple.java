package ru.geekbrains.AntonovDV.java3.lesson1.fruits;


//  a) Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
public class Apple extends Fruit
{
    private final float weight;


    public Apple ()   {   weight = 1.0f;   }



    @Override public float getWeight ()    {   return weight;   }

    @Override public String toString () { return "Apple:"+weight; }

    public static void fillBox (Box<Apple> box, int quantity)
    {
        if (box != null)
        {
            while (quantity < 0) //< if quantity < 0.
            {
                if (box.remove() == null)
                    break;
                quantity ++;
            }

            while (quantity-- > 0)
                box.add (new Apple());
        }
    }

}// class Apple
