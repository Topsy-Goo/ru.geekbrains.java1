package ru.geekbrains.AntonovDV.java1.lesson7;

import static java.lang.Integer.min;

public class Cat
{
	public static final boolean SEX_MALE = true, SEX_FEMALE = !SEX_MALE;
	public final String name;
	public final boolean sex;
	public final String title;
	private int appetite = 1;


	public Cat (String name, boolean sex, int appetite)
	{
		this.name = (name != null) ? name : "(nameless cat)";
		this.sex = sex;
		if (appetite >= 0) this.appetite = appetite;
		title = sex==SEX_MALE ? "Кот" : "Кошка";
		System.out.printf ("%s %s уже здесь.\n", title, this.name);
	}

	public int getAppetite()    {   return appetite;    }
	public boolean isHungry()   {   return appetite > 0;   }

	public void info()
	{
		System.out.printf ("\n%s оценивает свой аппетит на %d баллов.", name, appetite);
	}

	public void eat (Plate p, int bite)
	{
		if (p != null && bite > 0)
		{
			bite = min(bite, appetite);
			int delta = p.decreaseFood (bite);
			appetite -= delta;
			System.out.printf ("\n%s осилил %d ед.еды.", name, delta);
		}
	}


}// class Cat
