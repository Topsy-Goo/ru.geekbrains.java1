package ru.geekbrains.AntonovDV.java3.lesson5;

public class Car implements Runnable
{
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public String getName() {  return name;  }

    public int getSpeed()   {  return speed;  }

    public Car (Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.name = String.format("%s (ск.%d)", this.name, speed); //< для отладки
    }

    @Override public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        race.awaitOnStartBarrier();   //< старт
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (MainClass.finishLine.tryAcquire())   //< достигнута финишная черта (используем севафор)переменной, пусть будет)
            System.out.println(this.name + " победил!");
        race.awaitOnFinishBarrier();  //< финиш
    }
}
