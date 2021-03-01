package ru.geekbrains.AntonovDV.algorithms.lesson4;

import java.util.LinkedList;

public class TestLists
{

    public static void main(String[] args)
    {
        testSimpleLLClass (7);
        testDoubleEndedLLClass (7);
        testLLStackClass (7);
        testLLQueueClass (7);
    }// main ()


    static void testSimpleLLClass (int n)
    {
        LLInterface<Integer> sllc = new SimpleLLClass<>();

        System.out.println ("\nsllc = new SimpleLLClass<Integer>()\t:\t"+sllc);

        System.out.print ("iterator test (список пуст)\t:\t"); // тестируем итератор на пустом списке
        for (Integer i : sllc)
            System.out.print("."+i+'.');
        System.out.println();//*/

        sllc.insertFirst(-1);
        System.out.println ("sllc.insert (-1)\t:\t"+sllc);

        for (int i=0; i<n; i++)
            sllc.insertFirst(i);
        System.out.println ("sllc.insert (0…6)\t:\t"+sllc);

        System.out.print ("iterator test\t:\t");
        for (Integer i : sllc)
            System.out.print("."+i+'.');
        System.out.println();
        System.out.print ("iterator test\t:\t"); // тестируем повторное использование итератора
        for (Integer i : sllc)
            System.out.print("."+i+'.');
        System.out.println();

        sllc.removeFirst();
        System.out.println ("sllc.removeFirst()\t:\t"+sllc);
        sllc.removeFirst();
        System.out.println ("sllc.removeFirst()\t:\t"+sllc);

        System.out.println ("sllc.contains(2)\t:\t"+sllc.contains(2));

        sllc.remove(-1);
        System.out.println ("sllc.remove(-1)\t:\t"+sllc);
        sllc.remove(3);
        System.out.println ("sllc.remove(3)\t:\t"+sllc);
        sllc.remove(4);
        System.out.println ("sllc.remove(4)\t:\t"+sllc);

        System.out.println ("sllc.contains(2)\t:\t"+sllc.contains(2));
        System.out.println ("sllc.contains(-1)\t:\t"+sllc.contains(-1));
        System.out.println ("sllc.contains(0)\t:\t"+sllc.contains(0));

        while (!sllc.isEmpty())   sllc.removeFirst();
        System.out.println ("sllc - removeAll\t:\t"+sllc);

        System.out.println ("sllc.contains(2)\t:\t"+sllc.contains(2));

    }// testSimpleLLClass ()

    static void testDoubleEndedLLClass (int n)
    {
        DoubleEndedLLInterface<Integer> dellc = new DoubleEndedLLClass<>();

        System.out.println ("\ndellc = new DoubleEndedLLClass<Integer>()\t:\t"+dellc);

        System.out.print ("iterator test (список пуст)\t:\t"); // тестируем итератор на пустом списке
        for (Integer i : dellc)
            System.out.print("."+i+'.');
        System.out.println();

        dellc.insertFirst(100);
        System.out.println ("dellc.insert (100)\t:\t"+dellc);

        for (int i=0; i<n; i++)
        {
            dellc.insertFirst(i*(-1));
            dellc.insertLast(i);
        }
        System.out.println ("dellc.insert (0…6)\t:\t"+dellc);

        System.out.print ("iterator test\t:\t");
        for (Integer i : dellc)
            System.out.print("."+i+'.');
        System.out.println();
        System.out.print ("iterator test\t:\t"); // тестируем повторное использование итератора
        for (Integer i : dellc)
            System.out.print("."+i+'.');
        System.out.println();

        dellc.removeFirst();
        System.out.println ("dellc.removeFirst()\t:\t"+dellc);
        dellc.removeLast();
        System.out.println ("dellc.removeLast()\t:\t"+dellc);

        System.out.println ("dellc.contains(2)\t:\t"+dellc.contains(2));

        dellc.remove(-1);
        System.out.println ("dellc.remove(-1)\t:\t"+dellc);
        dellc.remove(0);
        System.out.println ("dellc.remove(0)\t:\t"+dellc);
        dellc.remove(-5);
        System.out.println ("dellc.remove(-5)\t:\t"+dellc);
        dellc.remove(5);
        System.out.println ("dellc.remove(5)\t:\t"+dellc);

        System.out.println ("dellc.contains(2)\t:\t"+dellc.contains(2));
        System.out.println ("dellc.contains(-1)\t:\t"+dellc.contains(-1));
        System.out.println ("dellc.contains(0)\t:\t"+dellc.contains(0));

        while (!dellc.isEmpty())   dellc.removeFirst();
        System.out.println ("dellc - removeAll\t:\t"+dellc);

        System.out.println ("dellc.contains(2)\t:\t"+dellc.contains(2));
    }// testDoubleEndedLLClass ()

    static void testLLStackClass (int n)
    {
        StackInterface<Integer> a = new LLStackClass<>();
        LLStackClass<Integer> _a_ = (LLStackClass<Integer>)a; //< чтобы избежать приведений в коде

        System.out.println("\n\ntestLLStackClass\n" + a);

        for (int i=0; i<n; i++)
            a.push (i);
        System.out.println(a);

        System.out.print ("iterator test\t:\t");
        for (Integer i : _a_)
            System.out.print("."+i+'.');
        System.out.println();

        System.out.println(a.peek());

        a.pop();
        a.pop();
        System.out.println(a);

        while (!a.isEmpty())
            a.pop();
        System.out.println(a);
    }// testLLStackClass ()

    static void testLLQueueClass (int n)
    {
        QueueInterface<Integer> a = new LLQueueClass<>();
        LLQueueClass<Integer> _a_ = (LLQueueClass<Integer>)a; //< чтобы избежать приведений в коде

        System.out.println("\n\ntestLLQueueClass\n" + a);

        for (int i=0; i<n; i++)
            a.insert (i);
        System.out.println(a);

        System.out.print ("iterator test\t:\t");
        for (Integer i : _a_)
            System.out.print("."+i+'.');
        System.out.println();

        a.remove();
        a.remove();
        System.out.println(a);

        while (!a.isEmpty())
            a.remove();
        System.out.println(a);
    }// testLLQueueClass ()


}// class TestLists
