package ru.geekbrains.AntonovDV.java3.lesson5;

import java.util.concurrent.Semaphore;

public class MainClass
{
    public static Semaphore finishLine; //< финишная черта
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        finishLine = new Semaphore(1, true);
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        race.awaitOnStartBarrier();   //< старт (собираем всех, включая основной поток, чтобы сделать объявление)
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        race.awaitOnFinishBarrier();  //< финиш (снова собираем всех)
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        MainClass.finishLine.release(); //< освобождаем финишную черту от победителя
    }
}
