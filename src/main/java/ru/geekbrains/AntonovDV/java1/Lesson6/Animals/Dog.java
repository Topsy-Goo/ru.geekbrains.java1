package ru.geekbrains.AntonovDV.java1.Lesson6.Animals;

// Типы доступа расставляем из расчёта, что все классы-наследники будут располагаться внутри одного пакета.
public class Dog extends Animal
{
	static int dogsCounter = 0;

	public Dog (double age, String name)
	{
		super (false, age, name);
		maxRunDistance = 500;
		maxSwimDistance = 10;
		dogsCounter ++;
	}

	public static int getDogsCounter() {   return dogsCounter;  }

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

	@Override public Dog animalGone ()
	{
		super.animalGone();
		dogsCounter --;
		return null;
	}

}
