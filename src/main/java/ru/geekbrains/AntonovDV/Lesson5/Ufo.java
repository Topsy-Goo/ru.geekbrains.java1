package ru.geekbrains.AntonovDV.Lesson5;

import java.util.Random;

public class Ufo
{
	protected String fromGalaxy = "";
	protected String[] crew = {};
	protected int peopleStolen;
	protected int maxLoad;
	Random rnd = null;


	public Ufo (String fromGalaxy, int maxLoad, String[] crew)
	{
		rnd = new Random();
		this.fromGalaxy = fromGalaxy;

		if (crew != null)
			this.crew = crew;

		this.peopleStolen = 0;
		this.maxLoad = maxLoad;
	}


	public void beAnAlien()
	{
		goToEarth();

		do
		{
		    stealMorePeople (maxLoad);
		}
		while (peopleStolen < maxLoad);

		if (peopleStolen > maxLoad)
			freeSomeSpecimen (peopleStolen - maxLoad);

		goHome();
	}

	public int getPeopleStolen ()    {    return peopleStolen;    }
	public int getMaxLoad ()    {    return peopleStolen;    }

	public void stealMorePeople (int range)
	{
		if (lookingForPeaple())
			peopleStolen += catchPeople (range);
	}

	public boolean lookingForPeaple ()	{	return rnd.nextBoolean();	}
	public int catchPeople (int range)	{	return rnd.nextInt (range); 	}

	void freeSomeSpecimen (int some)	{	peopleStolen -= some;	}

	public void goToEarth()
	{
		System.out.println ("(Корабль пришельцев направляется к Земле (звучит песня злобных пришельцев).");
	}

	public void goHome()
	{
		System.out.println ("(Корабль пришельцев покидает Землю и убирается восвояси (звучит песня злобных пришельцев).");
	}

}
