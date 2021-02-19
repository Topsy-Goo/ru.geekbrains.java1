package ru.geekbrains.AntonovDV.java2.lesson3;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SimplePhoneBook
{
    private final Map<String, TreeSet<String>> book;

    public SimplePhoneBook ()
    {
        book = new TreeMap<>();
    }


    public void add (String name, String phone)
    {
        if (name == null || name.isBlank ())
            return;

        TreeSet<String> ts;
        if (!book.containsKey (name))
        {
            ts = new TreeSet<> ();
            book.put (name, ts);
        }
        else
            ts = book.get (name);

        if (phone != null)   ts.add (phone);
    }// add ()


    public TreeSet<String> get (String name)
    {
        return (name != null && book.containsKey (name))
                ? book.get (name)
                : null;
    }// get ()


    public void remove (String name)
    {
        if (name != null)    book.remove (name);
    }// remove ()


    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder("\nSimplePhoneBook:\n");

        if (book.isEmpty ())
            sb.append ("\tempty");
        else
        for (String key : book.keySet ())
        {
            sb.append ('\t').
               append (key).
               append (":\t").
               append (book.get(key).toString()).
               append ('\n');
        }
        return sb.toString();
    }// toString ()


    public void displayRecords ()
    {
        Object[] oo = book.entrySet().toArray();
        for (Object o : oo)
            System.out.println (o);
    }// displayRecords ()

}// class SimplePhoneBook
