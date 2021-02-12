package ru.geekbrains.AntonovDV.java2.lesson1;

import ru.geekbrains.AntonovDV.java2.lesson1.equipment.Equipment;
import ru.geekbrains.AntonovDV.java2.lesson1.equipment.JumpingWall;
import ru.geekbrains.AntonovDV.java2.lesson1.equipment.RunningTrack;
import ru.geekbrains.AntonovDV.java2.lesson1.sportsmen.Bot;
import ru.geekbrains.AntonovDV.java2.lesson1.sportsmen.Cat;
import ru.geekbrains.AntonovDV.java2.lesson1.sportsmen.Human;
import ru.geekbrains.AntonovDV.java2.lesson1.sportsmen.Sportsmen;

import java.util.Random;

public class Test
{
    public static void main(String[] args)
    {
        Random rnd = new Random();
        int distanceDelta = 500,
            minDistance = 500;
        int minHeight = 100,
            heightDelta = 100;

        Sportsmen[] sportsmen = {
                new Human("ААААА", rnd.nextInt(distanceDelta) + minDistance, rnd.nextInt (heightDelta) + minHeight),
                new Cat("БББББ", rnd.nextInt(distanceDelta) + minDistance, rnd.nextInt (heightDelta) + minHeight),
                new Bot(null, rnd.nextInt(distanceDelta) + minDistance, rnd.nextInt (heightDelta) + minHeight),
                };
        Equipment[] equipment = {
                new JumpingWall<> (rnd.nextInt (heightDelta) + minHeight, sportsmen),
                new RunningTrack<> (rnd.nextInt(distanceDelta) + minDistance, sportsmen),
                };

        for (Equipment eq : equipment)
        {
            System.out.println(eq);
            eq.doJump();
            eq.doRun();
        }

        System.out.println (Equipment.makeString (sportsmen, "\nК финишу пришли: "));
    }// main ()

}// class Test
