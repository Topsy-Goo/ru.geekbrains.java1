package ru.geekbrains.AntonovDV.java3.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Race
{
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race (Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    CyclicBarrier cyclic = new CyclicBarrier(MainClass.CARS_COUNT +1);

    public void awaitOnStartBarrier ()
    {   try { cyclic.await(); }
        catch(InterruptedException|BrokenBarrierException e){;}
    }

    public void awaitOnFinishBarrier ()
    {   try { cyclic.await(); }
        catch(InterruptedException|BrokenBarrierException e){;}
    }
}
