package ru.geekbrains.AntonovDV.Lesson6;

import ru.geekbrains.AntonovDV.Lesson6.Animals.Animal;
import ru.geekbrains.AntonovDV.Lesson6.Animals.Cat;
import ru.geekbrains.AntonovDV.Lesson6.Animals.Dog;

public class AnimalsApp
{

	public static void main(String[] args)
	{
		Dog tuzik = new Dog (1.5, "Тузик"),
			dozor = new Dog (6.2, "Дозор");
		Cat buska = new Cat (2.5, "Буська");

		System.out.printf ("\nВ соревнованиях участвуют животные : %d (собак %d, кошек %d).\n\n", Animal.getAnimalsCounter(), Dog.getDogsCounter(), Cat.getCatsCounter());

		tuzik.run (450);		tuzik.swim (124);
		dozor.run (500);        dozor.swim (10);
		buska.run (18000);		buska.swim (10);

/*		tuzik = tuzik.animalGone();
		dozor = dozor.animalGone();//*/
		buska = buska.animalGone();

		System.out.printf ("\nК финишу пришли животные : %d (собак %d, кошек %d).\n", Animal.getAnimalsCounter(), Dog.getDogsCounter(), Cat.getCatsCounter());

	}

}
