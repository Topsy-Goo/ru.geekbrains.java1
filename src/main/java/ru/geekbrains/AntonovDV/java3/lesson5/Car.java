package ru.geekbrains.AntonovDV.java3.lesson5;

public class Car implements Runnable
{
    private static int CAR_NUMBER = 0;
    private final Race race;
    private final int speed;
    private String name;

    public String getName() {  return name;  }
    public int getSpeed()   {  return speed;  }

    public Car (Race race, int speed)
    {
        this.race = race;
        this.speed = speed;
        CAR_NUMBER++;
        this.name = "Участник #" + CAR_NUMBER;
        this.name = String.format("%s (ск.%d)", this.name, speed); //< для отладки
    }

    @Override public void run()
    {
        try
        {   System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
        }
        catch (Exception e) {  e.printStackTrace();   }

    //ждём когда все подтянуться, чтобы объявить о начале гонки:
        race.awaitOnStartBarrier();
        race.awaitOnStartBarrier();     //< FINISH_LOCK
        for (int i = 0; i < race.getStages().size(); i++)
        {
            race.getStages().get(i).go(this);
        }
    //достигнута финишная черта:
        //if (MainClass.finishLine.tryAcquire())        //< FINISH_LOCK
        //    System.out.println (name + " победил!");  //< FINISH_LOCK
        race.trySetWinner (name);                       //< FINISH_LOCK

    //ждём когда все подтянуться, чтобы объявить об окончании гонки:
        race.awaitOnFinishBarrier();
    }
}
