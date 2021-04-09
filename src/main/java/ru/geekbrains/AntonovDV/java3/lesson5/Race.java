package ru.geekbrains.AntonovDV.java3.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Race
{
    private final ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    String winner;
    CyclicBarrier cyclic = new CyclicBarrier(MainClass.CARS_COUNT +1);
    private final Lock finish;                                  //< FINISH_LOCK

    public Race (Stage... stages)
    {   this.stages = new ArrayList<>(Arrays.asList(stages));
        this.finish = new ReentrantLock (true);                  //< FINISH_LOCK
    }

    public void awaitOnStartBarrier ()
    {   try { cyclic.await(); }
        catch(InterruptedException|BrokenBarrierException e){;}
    }

    public void awaitOnFinishBarrier ()
    {   try { cyclic.await(); }
        catch(InterruptedException|BrokenBarrierException e){;}
    }

    public void trySetWinner (String name)                      //< FINISH_LOCK
    {
        //(При захвате выполнение не останавливается, т.к. захватывается не блок кода, а какая-то штуковина, КС,
        // которая, к тому же, непонятно где находится. Доступ к этой вот штуковине и проверяется в условии.)
        if (finish.tryLock())
        {   if (winner == null) winner = name;
            finish.unlock();
            //((ReentrantLock)finish).isHeldByCurrentThread();
        }
    }
    public String getWinner ()    {   return winner;   }        //< FINISH_LOCK

    CountDownLatch cdl;
}
