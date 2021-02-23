package ru.geekbrains.AntonovDV.algorithms.lesson6;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

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

        //for (int i=0;   i<CICLES;   i++)
        {
            if (testTreeClass (AMOUNT, LEVELS))
                balansed ++;
        }
        double statistics = (double)balansed / (double)CICLES;
        System.out.printf (
            "\n\nКоличество сбалансированных деревьев (%d) составило %d%% от общего количества (%d) построенных деревьев.\n",
            balansed, Math.round (statistics * 100.0), CICLES);

        //String text =
        //    "Сравним поиски по дереву, в неупорядоченном массиве и связанном списке. Если есть 1000000 элементов, " +
        //    "поиск в неупорядоченном массиве или связанном списке в среднем будет занимать 500000 сравнений, а в дереве " +
        //    "всего 20. В упорядоченном массиве поиск осуществляется быстро, но вставка потребует перемещения 500000 элементов. " +
        //    "А вставка элемента в дерево займет 20 операций сравнения и незначительное время на связывание. Аналогичным образом " +
        //    "можно сравнить операции удаления.";
        //testTreeString (text);

    }// main ()


    public static boolean testTreeClass (int amount, int levels)
    {
        Random rnd = new Random ();
        TreeClass<Integer> tci = new TreeClass<> (levels);
        boolean balanced;
/*
        StringBuilder sb = new StringBuilder ("[");
        SortedSet<Integer> ssi = new TreeSet<>();
        int[] ai = {72, 59, 48, 63, 55, 68, 4, 44, 2, 44, 80, 76, 80, 76, 61, 96, 12, 67, 87, 66, 41, 62, 9};//

        for (int i=0,sz=ai.length;  i<sz;  i++)
        {
            ssi.add (ai[i]);
            tci.add (ai[i]);
            sb.append (ai[i]);
            if (sz-1 > i) sb.append (", ");
        }
        sb.append (']');
        System.out.printf ("\n  StringBuilder:\n%s", sb);
        System.out.printf ("\n  TreeSet:\n%s", ssi);
//*/

        for (int i=0; i<amount; i++)  //< Исключаем зависания.
        //if (AMOUNT > RANGE)   throw new InvalidParameterException (String.format ("%d > %d", AMOUNT, RANGE));
        //while (tci.size() < amount) //< Добавляем все amount элементов.
        {
            Integer n = rnd.nextInt (RANGE) - RANGE/2; // устанавливаем середину диапазона на 0
            if (!tci.add (n))
                //System.out.print (".")
                ;
        }//*/
        balanced = tci.isBalanced();

        System.out.printf ("\n  TreeClass (root %s)(%sbalanced):\n%s",
                            tci.getRootValue(),
                            (balanced ? "" : "not "),
                            tci);
        return balanced;
    }// testTreeClass ()

    public static void testTreeString (String text)
    {
        if (text == null)   throw new InvalidParameterException ();

        String[] as = text.split ("[\\p{Space}\\p{Punct}\\p{Cntrl}]");
        TreeClass<String> tcs = new TreeClass<> (Integer.MAX_VALUE);

        for (String key : as)
        {
            if (key.isEmpty())  continue;
            key = key.trim().toLowerCase();

            tcs.add (key);
        }
        System.out.printf ("\nИсходная строка:\n«%s»\n\nРезультат работы программы:\n%s\n",
                           text, tcs);        ;
    }// testTreeString ()

}// class Test
