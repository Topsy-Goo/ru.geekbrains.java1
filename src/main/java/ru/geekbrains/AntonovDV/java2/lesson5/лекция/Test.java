package ru.geekbrains.AntonovDV.java2.lesson5.лекция;

public class Test
{
    static int counter = 0;
    static int counter1 = 0;
    static int counter2 = 0;

    public static void dec1() {counter1--;}
    public static void inc1() {counter1++;}
//Модификатор synchronized заставляет обращающийся поток ждать, когда объект-хозяин метода освободится от
// работы с другим потоком. При таком подходе мы получаем упорядоченный доступ к методам, но лишаемся
// параллельности выполнения потоков.
    public synchronized static void dec2() {counter2--;}
    public synchronized static void inc2() {counter2++;}
//Чтобы избежать этот недостаток возможно следующее решение:

    final Object o1, o2; //< final гарантирует, что объекты не могут быть подменены в неподходящий момент
    public Test ()
    {
        o1 = new Object();
        o2 = new Object();
    }
    public void inc()
    {
        synchronized (o1) {counter ++;}
    }
    public void dec()
    {
        synchronized (o2) {counter --;}
        //^ синтаксическая конструкция означает : использование монитора указанного объекта для выполнения
        //  указанного блока кода. Такая синхронизация может применяться только к части метода.
    }
//С модификатором synchronized связана ещё одна тонкость: если у класса есть статический synchronized-метод, то
// следует помнить, что этот метод существует в единственном экземпляре. В этом случае в качестве монитора
// выступает сам класс, а не экземпляр класса.







    public static void main (String[] args) throws Exception //< для Thread.sleep()
    {
        System.out.println ("\nМногопоточность. START\n");

        ThreadExtendsThreadClass tetc = new ThreadExtendsThreadClass();
        tetc.start(); // запуск потока (будет выполняться метод tetc.run() )
        //tetc.run(); < это не запуск потока, а запуск метода, описанного в классе потока (в данном
        //              контексте метод будет выполняться в потоке main)


        Thread tirc = new Thread (new ThreadImplementsRunableClass ());
        tirc.start(); // запуск потока ThreadImplementsRunableClass возможен только через преобразование типа


    //Создание потока - Способ 3 (лучший из трёх).
        Thread t = new Thread (new Runnable ()
            {
                @Override public void run ()
                {
                    System.out.printf ("\tВыполнение потока Thread (new Runnable() {…}) (имя потока: %s).\n",
                                       Thread.currentThread().getName());
                }
            });
        t.start();

        t.join();
        System.out.printf ("\tВыполнение потока main() (имя потока: %s).\n", Thread.currentThread().getName());

    //Использование лямбда-выражений вместо прописывания анонимных классов и демонстрация работы модификатора synchronized:
        Thread tr1 = new Thread (()->
            {
                for (int i=0;  i<10000;  i++)
                {
                    inc1();
                    inc2();
                }
            });
        Thread tr2 = new Thread (()->
            {
                for (int i=0;  i<10000;  i++)
                {
                    dec1();
                    dec2();
                }
            });
        tr1.start();
        tr2.start();

        tr1.join ();
        tr2.join ();
        System.out.printf ("Демонстрация работы модификатора «synchronized»:\n" +
                           "\tcounter1 = %d, counter2 = %d\n", counter1, counter2);



    //Полезные методы:
        String threadName = Thread.currentThread().getName();
        Thread.sleep (20);   //< Приостановка потока на 20 мс (public static native)
        t.join();   //< заставляет вызывающий поток ждать завершение потока t (в данном случае main() будет ждать завершение t).




        System.out.println ("\nМногопоточность. END");
    }// main ()

}// class Test

/*  • Как завершать поток?
    • .
*/
