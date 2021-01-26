package ru.geekbrains.AntonovDV.Lesson5;

import java.awt.*;
import java.util.Random;

public class BeeLife
{
	Random rnd = null;
	protected int positionX = 0, positionY = 0;
	protected double    nectar = 0,
						honey = 0,
						honeyNeeded = 0;
	public final double factor = 0.72;

	public BeeLife (int honeyNeeded)
	{
		rnd = new Random();
		this.honeyNeeded = honeyNeeded;
	}

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


	public void doFlying ()
	{
		int deltaX = rnd.nextInt() * (rnd.nextBoolean() ? 1 : -1),
			deltaY = rnd.nextInt() * (rnd.nextBoolean() ? 1 : -1);

		positionX += deltaX;
		positionY += deltaY;
	}

	public double peakNectar ()	{   return rnd.nextDouble();    }
	protected double makeHoney (double nectar)    {   return nectar * this.factor;    }

	public Point getPosition()  {   return new Point (positionX, positionY);     }

}
