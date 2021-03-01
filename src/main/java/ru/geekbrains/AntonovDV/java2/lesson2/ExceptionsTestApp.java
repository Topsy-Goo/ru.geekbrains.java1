package ru.geekbrains.AntonovDV.java2.lesson2;

import javax.swing.*;

public class ExceptionsTestApp
{
    private static final String errMsg_testSquareArraySize_prefix = "\nERROR @ testSquareArraySize(): ",
                                errMsg_testSquareArraySize_Array = "%sразмер массива не равен %d.",
                                errMsg_testSquareArraySize_nestedArray = "%sразмер вложенного массива [%d] не равен %d.";



    public static int testSquareArray(String [][] arrNxN, int size)
    {
        int sum = 0;

        if (size <= 0)
            throw new IllegalArgumentException();

        if (arrNxN == null || arrNxN.length != size)
            throw new MyArraySizeException (
                        String.format (errMsg_testSquareArraySize_Array,
                                        errMsg_testSquareArraySize_prefix, size));

        for (int i=0; i<size; i++)
        {
            String[] line = arrNxN[i];

            if (line == null || line.length != size)
                throw new MyArraySizeException (
                        String.format (errMsg_testSquareArraySize_nestedArray,
                                        errMsg_testSquareArraySize_prefix, i, size));
        }

        int row=0, column=0;
        try
        {
            for (;   row < size;   row++)
            {
                for (column=0;   column < size;   column++)
                    sum += Integer.parseInt (arrNxN[row][column]);
            }
        }
        catch (NumberFormatException nfex)
        {
            throw new MyArrayDataException (errMsg_testSquareArraySize_prefix, row, column, arrNxN[row][column]);
        }

        return sum;
    }// testSquareArraySize ()


    public static void main(String[] args)
    {
        String errorMsg;
        String[][] arrSS = {{"1","2","3","4"},
                            {"5","6","7","8"},
                            {"9","10","11","12"},
                            {"13","14","15","16"}};

        System.out.println();
        try
        {
            JOptionPane.showMessageDialog (null, "Сумма элментов массива : " + testSquareArray (arrSS, 4));
        }
        catch (MyArraySizeException se)
        {
            System.err.println (se);
        }
        catch (MyArrayDataException de)
        {
            System.err.println (de);
        }


    }// main ()

}// class ExceptionsTestApp
