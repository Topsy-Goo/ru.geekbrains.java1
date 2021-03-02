package ru.geekbrains.AntonovDV.java2.lesson5.лекция;

// Создание потока - Способ 1 (не очень популярный).
public class ThreadExtendsThreadClass
             extends Thread //< так наш класс становится потоком (для него переопределяем run()); Thread implements Runnable
{
/* При таком создании класса-потока мы никаких плюсов не получаем (пока непонятно, о чём речь).
   Этот метод редко испоьзуется: обычно в случаях, когда нужно доработать Tread.
*/

// Здесь мы МОЖЕМ реализовать run().
    @Override public void run () // всё содержимое этого метода будет выполняться в потоке class LectionClass
    {
        super.run (); //< необязательный вызов
        System.out.printf ("\tВыполнение потока ThreadExtendsThreadClass. (имя потока: %s).\n",
                           Thread.currentThread().getName());

    }// run ()

}// class ThreadExtendsThreadClass



