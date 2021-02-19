package ru.geekbrains.AntonovDV.java2.lesson3;

import java.util.TreeSet;

public class PhoneBook
{
    private final TreeSet<PhoneBookRecord> book;


    public PhoneBook()
    {
        book = new TreeSet<>();
    }


// Создаёт (или дополняет) в книге запись с имененм name.
    public void add (String name, String phone)
    {
        PhoneBookRecord pbrNew = new PhoneBookRecord (name, phone),
                        pbrOld = book.ceiling (pbrNew);

        if (pbrOld != null && pbrNew.compareTo (pbrOld) == 0)
            pbrOld.addPhone (phone);
        else
            book.add (pbrNew);
    }// add ()


    public PhoneBookRecord[] getRecords ()
    {
        return book.toArray (new PhoneBookRecord[0]);
    }


// Возвращает из телефонной книги список тел.номеров для записи, соответствующей имени name.
// (Для удобного отображения этой информации на экране рекомендуется вызывать метод getFormated
// с тем же параетом.)
    public PhoneBookRecord get (String name)
    {
        PhoneBookRecord tmp = new PhoneBookRecord (name, ""),
                        pbrOld = book.ceiling (tmp);

        if (pbrOld != null && tmp.compareTo (pbrOld) == 0)
            return pbrOld;
        else
            return null;
    }// get ()


// Фозвращает форматированную строку-результат поиска по имени.
    public String getFormated (String name)
    {
        name = PhoneBookRecord.checkName (name);
        StringBuilder sb = new StringBuilder ("Выписка для абонента ");

        PhoneBookRecord pbr = get(name);
        if (pbr != null)
            sb.append (pbr.toString());
        else
            sb.append (name).append (":\tне найден(а).");

        return sb.toString();
    }// getFormated ()


    @Override public String toString ()
    {
        StringBuilder sb = new StringBuilder ("\nТелефонная книга: \n");

        if (book.isEmpty ())
            sb.append ("записей нет");
        else
        for (PhoneBookRecord rec : book)
            sb.append (rec);

        return sb.toString ();
    }// toString ()

// Делает то же самое, что и toString
    public void displayBook ()
    {
        PhoneBookRecord[] apbr = getRecords ();
        for (PhoneBookRecord pbr : apbr)
            System.out.print (pbr);
    }// displayBook ()

}// class PhoneBook
