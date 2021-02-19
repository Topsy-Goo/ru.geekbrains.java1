package ru.geekbrains.AntonovDV.java2.lesson3;

import java.util.*;

public class PhoneBookRecord implements Comparable<PhoneBookRecord>
{
    public static final String nameDEFAULT = "(no name)";
    private final String name;
    private final TreeSet<String> phoneNumbers;


    public PhoneBookRecord (String name, String phone)
    {
        this.name = checkName (name);
        phoneNumbers = new TreeSet<>();
        addPhone (phone);
    }

// Добавляет строку в список номеров.
    public void addPhone (String phone)
    {
        if (phone != null && !phone.isBlank ())      phoneNumbers.add (phone);
    }


    //public String getName ()    {   return name;   }

    public String[] getPhoneStrings ()
    {
        return phoneNumbers.toArray (new String[0]);
    }
    public Object[] getPhoneObjects ()
    {
        return phoneNumbers.toArray();
    }


    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder (name).append (":\tтт.\t");

        Iterator<String> it = phoneNumbers.iterator();
        while (it.hasNext())
        {
            sb.append (it.next());
            if (it.hasNext()) sb.append (", \t");
        }
        sb.append ('\n');

        return sb.toString ();
    }// toString ()


    @Override public int compareTo (PhoneBookRecord o)
    {
        return (o != null) ? this.name.compareTo (o.name) : 1;
    }

// (Вспомогательная.) Проверяет строку и, если она пустая или == null, то возвращает замену для неё.
    public static String checkName (String name)
    {
        return (name == null || name.isBlank()) ? nameDEFAULT : name;
    }

}// class PhoneBookRecord

