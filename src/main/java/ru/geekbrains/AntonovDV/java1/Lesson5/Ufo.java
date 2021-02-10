package ru.geekbrains.AntonovDV.java1.Lesson5;

import java.util.Random;

public class Ufo
{
	private String fromGalaxy = "";
	private String[] crew = {};
	private int peopleStolen;
	private int maxLoad;
	private Random rnd = null;


	public Ufo (String fromGalaxy, int maxLoad, String[] crew)
	{
		rnd = new Random();
		this.fromGalaxy = fromGalaxy;

		if (crew != null)
			this.crew = crew;

		this.peopleStolen = 0;
		this.maxLoad = maxLoad;
	}


	private void stealMorePeople (int range)
	{
		if (lookingForPeaple())
			peopleStolen += catchPeople (range);
	}
	private boolean lookingForPeaple ()	{	return rnd.nextBoolean();	}
	private int catchPeople (int range)	{	return rnd.nextInt (range); 	}
	private void freeSomeSpecimen (int some)	{	peopleStolen -= some;	}

	private void goToEarth()
	{
		System.out.println ("(Корабль пришельцев направляется к Земле (звучит песня злобных пришельцев).");
	}
	private void goHome()
	{
		System.out.println ("(Корабль пришельцев покидает Землю и убирается восвояси (звучит песня злобных пришельцев).");
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

}
