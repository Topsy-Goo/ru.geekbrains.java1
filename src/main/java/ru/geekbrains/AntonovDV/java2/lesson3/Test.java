package ru.geekbrains.AntonovDV.java2.lesson3;

import java.util.*;

public class Test
{

    public static void main (String[] args)
    {
        String text = "1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся)." +
                      " Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не" +
                      " считаем). Посчитать сколько раз встречается каждое слово.";

        //wordsCalculation(text);
        testSimplePhoneBook();
        //testPhoneBook();
    }// main ()


    public static void wordsCalculation (String text)
    {
        String[] sWords = text.split ("[\\p{Space}\\p{Punct}\\p{Cntrl}]");
        Map<String, Integer> hmapWords = new LinkedHashMap<> (sWords.length, 1.0F);
        List<String> list = new ArrayList<> (sWords.length); // исследоваие

        for (String key : sWords)
        {
            if (key.isEmpty())  continue;
            key = key.trim().toLowerCase();

            Integer value = hmapWords.get (key);

            if (value != null)
                hmapWords.replace (key, value +1);
            else
                hmapWords.put (key, 1);
            list.add (key); // исследоваие
        }
        list.sort (new Comparator<String> () { // исследоваие
                       @Override public int compare (String o1, String o2)
                       {
                           return o1.compareTo (o2);
                       }
                   });
        System.out.printf ("\nИсходная строка:\n«%s»\n\nРезультат работы программы:\n%s\n", text, hmapWords);
        System.out.println ("\nSorted ArrayList:\n"+list);
    }// wordsCalculation ()


/*  2. Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
    В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать
    номер телефона по фамилии.
    Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
    тогда при запросе такой фамилии должны выводиться все телефоны.

    Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с
    пользователем через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного
    справочника.
*/
    public static void testSimplePhoneBook ()
    {
        String[] names =  {"Bob", "Rob", "Mike", "Poul", "Jorge", "Sam"};
        String[] phones = {"123", "456", "0183", null, "00100", "222"};
        SimplePhoneBook book = new SimplePhoneBook();
        System.out.println ("\n"+book);

        for (int i=0, n=names.length;   i<n;   i++)
            book.add (names[i], phones[i]);

        System.out.print (book);
        book.add ("Mike", "0184");
        book.add ("Poul", "+9999");
        book.add ("Sam", "2222");
        book.add ("Sam", "2222");
        book.remove ("Bob");
        System.out.println (book);

        System.out.println ("Выписка для Mike :\t" + book.get ("Mike"));
        System.out.println ("Выписка для Karl :\t" + book.get ("Karl"));

        System.out.println ("\ndisplayRecords() (поочерёдно выводим неформатированные запсси):");
        book.displayRecords();
    }// testSimplePhoneBook ()


    public static void testPhoneBook ()
    {
        String[] names =  {"Bob", "Rob", "Mike", "Poul", "Jorge", "Sam"};
        String[] phones = {"123", "456", "0183", null, "00100", "222"};
        PhoneBook book = new PhoneBook();
        System.out.println ("\n"+book);

        for (int i=0, n=names.length;   i<n;   i++)
            book.add (names[i], phones[i]);

        System.out.print (book);
        book.add ("Mike", "0184");
        book.add ("Poul", "+9999");
        book.add ("Sam", "2222");
        book.add ("Sam", "2222");
        System.out.println (book);

        System.out.print (book.getFormated ("Mike"));
        System.out.println (book.getFormated ("Karl"));

        System.out.println ("\ngetPhoneStrings() : ");
        String[] ars = book.get("Mike").getPhoneStrings();
        for (String s : ars)
            System.out.print ("-"+ s + "-");

        System.out.println ("\ngetPhoneObjects() : ");
        Object[] aro = book.get("Mike").getPhoneObjects();
        for (Object o : aro)
            System.out.print ("-"+ o + "-");

    }// testPhoneBook ()


}// class Test
