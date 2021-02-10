package ru.geekbrains.AntonovDV.java1.Lesson6.Animals;

public abstract class Animal
{
	private static int animalsCounter = 0; // Прячем этот счётчик, коль у нас есть такая возможность.
	private boolean wild;
	String name = "";
	double ade = 0.0;
	int maxRunDistance, maxSwimDistance;

	//Animal(){}
	public Animal (boolean wild, double age, String name)
	{
		this.wild = wild;
		if (age > 0.0) this.ade = age;
		if (name != null) this.name = name;
		animalsCounter ++;
	}

	public static int getAnimalsCounter()    {   return animalsCounter;   }

	abstract void run (int distance);
	abstract void swim (int distance);

	public Animal animalGone () // Вместо деструктора.
	{
		animalsCounter --;
		System.out.printf ("\n%s дисквалифицирован.", name);
		return null;
	}

}
