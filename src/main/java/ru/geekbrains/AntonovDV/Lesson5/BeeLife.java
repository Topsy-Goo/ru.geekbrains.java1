package ru.geekbrains.AntonovDV.Lesson5;

import java.awt.*;
import java.util.Random;

public class BeeLife
{
	private Random rnd = null;
	private int positionX = 0, positionY = 0;
	private double  nectar = 0,
					honey = 0,
					honeyNeeded = 0;
	private final double factor = 0.72;

	public BeeLife (int honeyNeeded)
	{
		rnd = new Random();
		this.honeyNeeded = honeyNeeded;
	}

	private void doFlying ()
	{
		int deltaX = rnd.nextInt() * (rnd.nextBoolean() ? 1 : -1),
			deltaY = rnd.nextInt() * (rnd.nextBoolean() ? 1 : -1);

		positionX += deltaX;
		positionY += deltaY;
	}

	private double peakNectar ()	{   return rnd.nextDouble();    }
	private double makeHoney (double nectar)    {   return nectar * this.factor;    }

	public void doWorking ()
	{
		do
		{
			doFlying();
			honey += makeHoney (peakNectar());
		}
		while (honey < honeyNeeded);

		System.out.println (getPosition().toString());
	}

	public Point getPosition()  {   return new Point (positionX, positionY);     }

}
