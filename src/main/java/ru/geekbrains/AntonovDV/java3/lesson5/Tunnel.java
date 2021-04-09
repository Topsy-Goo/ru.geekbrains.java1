package ru.geekbrains.AntonovDV.java3.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage
{
    Semaphore semaphore;
    public Tunnel (int throughput)                          //< FINISH_LOCK
    {   this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore (throughput, true);  //< FINISH_LOCK
    }

    @Override public void go(Car c) {
        try
        {   if (!semaphore.tryAcquire())
            {   System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
            }
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
        }
        catch (InterruptedException e) {  e.printStackTrace();  }
        finally
        {   System.out.println(c.getName() + " закончил этап: " + description);
            semaphore.release();                        //< FINISH_LOCK
        }
    }
}
