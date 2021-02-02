package ru.geekbrains.AntonovDV.lesson7;

public class MainApp
{

	public static void main(String[] args)
	{
		System.out.println("");

	// Кот Barsik пришёл домой:
		Cat barsik = new Cat ("Barsik", Cat.SEX_MALE, 250);
		Plate plate = new Plate (100);

		System.out.printf ("%s подходит к тарелке.", barsik.name);

		catsBanquet (plate, barsik);

	// К Barsik'у пришли друзья:
		System.out.printf ("\nК %s пришли друзья.\n", barsik.name);

		Cat[] cats = {new Cat ("Murzic", Cat.SEX_MALE, 250),
					  new Cat ("Blanka", Cat.SEX_FEMALE, 150),
					  new Cat (null, Cat.SEX_MALE, 450)};

		Plate dish = new Plate (650);
		catsBanquet (dish, cats);

	}// main

// Подсчитываем количество голодных кошек.
	static int anybodyHungry (Cat ... cats)
	{
		int hungryCats = 0;
		if (cats != null)
		{
			for (Cat cat : cats)
				if (cat.isHungry())
					hungryCats++;
		}
		return hungryCats;
	}// anybodyHungry ()


/** Кошки едят одновременно из одной тарелки: содержимое тарелки делится поровну на всех; если кому-то не хватило
	еды утолить голод, то он/она получают возможность сделать это на следующем заходе. Банкет продолжается до тех
	пор, пока все не насытятся.
*/
	static void catsBanquet (Plate plate, Cat ... cats)
	{
		int hungryCats = 0;

		for (Cat cat : cats)
			cat.info();

		plate.info();

		while ((hungryCats = anybodyHungry (cats)) > 0)
		{
			plate.donate (plate.getCapacity());
			plate.info();

			int bite = plate.getFood() / hungryCats; //< пусть это будут вежливые кошки (каждый будет съедать равную долю)

			for (Cat cat : cats)
			{
				if (cat.isHungry())
				{
					cat.eat (plate, bite);
					cat.info();
				}
			}
			plate.info();
		}
		System.out.println("");

	}// catsBanquet ()


}// class MainApp
