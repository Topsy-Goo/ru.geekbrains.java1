package ru.geekbrains.AntonovDV.java1.Lesson6.Animals;

// Типы доступа расставляем из расчёта, что все классы-наследники будут располагаться внутри одного пакета.
public class Cat extends Animal
{
	static int catsCounter = 0; // Все классы-наследники также будут кошками, поэтому тип доступа != private.

	public Cat (double age, String name)
	{
		super (false, age, name);
		maxRunDistance = 200;
		maxSwimDistance = 0;
		catsCounter ++;
	}

	public static int getCatsCounter()  {   return catsCounter;  } // static позволит проверить количество кошек даже при полном отсутствии таковых.

	@Override public void run (int distance)
	{
		if (distance < 0)	distance = 0;

		if (distance > maxRunDistance)
			distance = maxRunDistance;
		System.out.printf ("%s пробежал(а) %d метров%s.\n", name, distance, ((maxRunDistance > 0) ? "" : " (т.к. не умеет бегать)"));
	}

	@Override public void swim (int distance)
	{
		if (distance < 0)	distance = 0;

		if (distance > maxSwimDistance)
			distance = maxSwimDistance;
		System.out.printf ("%s проплыл(а) %d метров%s.\n", name, distance, ((maxSwimDistance > 0) ? "" : " (т.к. не умеет плавать)"));
	}

	@Override public Cat animalGone ()
	{
		super.animalGone();
		catsCounter --;
		return null;
	}

}
