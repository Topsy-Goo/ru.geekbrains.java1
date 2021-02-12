package ru.geekbrains.AntonovDV.java2.lesson1.equipment;

import ru.geekbrains.AntonovDV.java2.lesson1.sportsmen.Sportsmen;
import java.util.Arrays;

public class RunningTrack<E extends Sportsmen> implements Equipment
{
    private static final String  formatRunDone = "\t%s не смог пробежать. %s дисквалифицирован.\n",
                                formatRunFailed = "\t%s успешно пробежал.\n";

    private int distance = 1000;
    private Object[] data;

    public RunningTrack (int distance, Sportsmen[] sportsmen)
    {
        if (distance > 0)
            this.distance = distance;
        if (sportsmen != null)
            data = sportsmen;//Arrays.copyOf (sportsmen, sportsmen.length);
    }

    public int getDistance ()   {   return distance;    }  // на всякий случай

    @Override public void doRun()
    {
        for (int i=0, n=data.length;   i<n;   i++)
        {
            Sportsmen sportsman = (Sportsmen) data[i];
            if (sportsman != null)
            {
                String name = ((Sportsmen) data[i]).getName();

                if (distance <= sportsman.run())
                    System.out.printf (formatRunFailed, name);
                else
                {
                    System.out.printf (formatRunDone, name, sportsman.getName());
                    data[i] = null;
                }
            }
        }
    }// doRun()

/*    @Override public void doJump()
    {
      for (Object i : data)
        {
            ((Sportsmen)i).jump();
            System.out.println(" - не смог прыгнуть.");
        }
    }//*/

    @Override public String toString ()
    {
        return Equipment.makeString (data, "\nНа беговой дорожке: ")
             + String.format("\tДистанция: %d метров.\n", distance);
    }

}// RunningTrack
