package ru.geekbrains.AntonovDV.java2.lesson1.equipment;

import ru.geekbrains.AntonovDV.java2.lesson1.sportsmen.Sportsmen;
import java.util.Arrays;

public class JumpingWall<E extends Sportsmen> implements Equipment
{
    private static final String  formatJumpDone = "\t%s не смог прыгнуть. %s дисквалифицирован.\n",
                                formatJumpFailed = "\t%s успешно прыгнул.\n";

    private int height = 100; // сантиметры
    private Object[] data;

    public JumpingWall (int height, Sportsmen[] sportsmen)
    {
        if (height > 0)
            this.height = height;
        if (sportsmen != null)
            data = sportsmen;//Arrays.copyOf(sportsmen, sportsmen.length);
            //Вместо копирования массива используем ссылку на него, чтобы иметь возможность
            //дисквалифицировать спортменов.
    }

    public double getHeight ()  {   return height;   }  // на всякий случай

/*    @Override public void doRun()
    {
      for (Object i : data)
        {
            ((Sportsmen)i).run();
            System.out.println(" - не смог пробежать.");
        }
    }//*/

    @Override public void doJump()
    {
        for (int i=0, n=data.length;   i<n;   i++)
        {
            Sportsmen sportsman = (Sportsmen) data[i];
            if (sportsman != null)
            {
                String name = ((Sportsmen) data[i]).getName();

                if (height <= sportsman.jump())
                    System.out.printf (formatJumpFailed, name);
                else
                {
                    System.out.printf(formatJumpDone, name, sportsman.getName());
                    data[i] = null;
                }
            }
        }
/*      for (Object i : data)
        {
            ((Sportsmen)i).jump();
            System.out.println(" - успешно прыгнул.");
        }//*/
    }

    @Override public String toString()
    {
        return Equipment.makeString (data, "\nК прыжкам готовятся: ")
             + String.format("\tВысота: %d см.\n", height);
    }

}// class JumpingWall
