package ru.geekbrains.AntonovDV.algorithms.lesson6;

import java.util.Random;

public class Test
{
    private static final int CICLES = 20,
                             LEVELS = 4,    //глубина не более 6 или 4 уровней
                             AMOUNT = (int) Math.pow (2, LEVELS) -1, // N = (2^L) -1
                             RANGE = LEVELS <= 4 ? 50 : 200;   // диапазон значений -100…+100 или -25…+25

/*  В комбинации LEVELS == 6, RANGE == 200 процент сбалансированных деревьев ни разу не был больше 0%.
    В комбинации LEVELS == 4, RANGE == 50 процент сбалансированных деревьев иногда бывает больше нуля.
*/
    public static void main (String[] args)
    {
        int balansed = 0;

        for (int i=0;   i<CICLES;   i++)
        {
            if (testTreeClass (AMOUNT))
                balansed ++;
        }
        double statistics = (double)balansed / (double)CICLES;
        System.out.printf (
            "\n\nКоличество сбалансированных деревьев (%d) составило %d%% от общего количества (%d) построенных деревьев.\n",
            balansed, Math.round (statistics * 100.0), CICLES);

    }// main ()


    public static boolean testTreeClass (int amount)
    {
        Random rnd = new Random ();
        TreeClass<Integer> tci = new TreeClass<> (LEVELS);
        boolean balanced;

        for (int i=0; i<amount; i++)  //< Исключаем зависания.
        //if (AMOUNT > RANGE)   throw new InvalidParameterException (String.format ("%d > %d", AMOUNT, RANGE));
        //while (tci.size() < amount) //< Добавляем все amount элементов.
        {
            Integer n = rnd.nextInt (RANGE) - RANGE/2; // устанавливаем середину диапазона на 0
            if (!tci.add (n))
                //System.out.print (".")
                ;
        }
        balanced = tci.isBalanced();

        System.out.printf ("\n  TreeClass (root %s)(%sbalanced):\n  %s",
                            tci.getRootValue(),
                            (balanced ? "" : "not "),
                            tci);
        return balanced;
    }// testTreeClass ()

}// class Test
