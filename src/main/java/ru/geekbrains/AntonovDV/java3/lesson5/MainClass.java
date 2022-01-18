package ru.geekbrains.AntonovDV.java3.lesson5;

public class MainClass
{
// FINISH_LOCK - означает, что код добавлен/исправлен после разбора ДЗ (просто меняем финиш с семафора на лок)
    //public static Semaphore finishLine; //< финишная черта    //< FINISH_LOCK
    public static final int CARS_COUNT = 4;

    public static void main(String[] args)
    {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        //finishLine = new Semaphore(1, true);                  //< FINISH_LOCK
        Race race = new Race(new Road(60), new Tunnel (MainClass.CARS_COUNT / 2), new Road(40));     //< FINISH_LOCK

        for (int i = 0; i < CARS_COUNT; i++)
        {
            Car car = new Car(race, 20 + (int) (Math.random() * 10));
            new Thread(car).start();
        }

        race.awaitOnStartBarrier();   //< старт (собираем всех, включая основной поток, чтобы сделать объявление)
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        race.awaitOnStartBarrier();     //< FINISH_LOCK

        race.awaitOnFinishBarrier();  //< финиш (снова собираем всех)
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        //освобождаем финишную черту от победителя:
        //MainClass.finishLine.release();           //< FINISH_LOCK
        System.out.println (race.getWinner() + " победил!");  //< FINISH_LOCK
    }
}
