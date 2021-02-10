package ru.geekbrains.AntonovDV.java1.lesson7;

import static java.lang.Integer.min;

public class Plate
{
	private int capacity = 10;
	private int food = 0;

	public Plate (int capacity)
	{
		if (capacity > 0) this.capacity = capacity;
	}

	public int getCapacity(){   return capacity;    }
	public int getFood()    {   return food;    }

	public void donate (int food)
	{
		if (food > 0)
		{
			int delta = capacity - this.food; //< Кол-во свободного места в тарелке.
			this.food += min(delta, food);
			System.out.printf ("\n\tВыдано %d единиц еды.", food);
		}
	}


	public int decreaseFood (int bite) // (Возвращаем количество съеденной еды.)
	{
		int delta = 0;
		if (bite > 0)
		{
			delta = min(food, bite);
			food -= delta;
		}
		return delta;
	}

	public void info()
	{
		System.out.printf ("\tВозможности тарелки: %d/%d.", food, capacity);
	}

}// class Plate
