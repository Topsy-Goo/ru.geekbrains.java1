package ru.geekbrains.AntonovDV.java2.lesson2;

public class MyArrayDataException extends NumberFormatException
{
    private static final String formatErrMsg = "%sмассив содержит некорректное значение в ячейке [%d][%d] = %s.";

    public MyArrayDataException (String prefix, int row, int column, Object o)
    {
        super (String.format (formatErrMsg, prefix, row, column, o));
    }

}// class MyArrayDataException
