package ru.geekbrains.AntonovDV.lesson2;

import java.util.Arrays;

public class MainApp2
{
/*  6. Написать метод, в который передается непустой одномерный целочисленный массив, метод должен вернуть true,
	если в массиве есть место, в котором сумма левой и правой части массива равны.
	Примеры: checkBalance ([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
			 checkBalance ([1, 1, 1, || 2, 1]) → true,
			 (граница показана символами ||, эти символы в массив не входят).
*/
	public static boolean checkBalance (int[] arr)
	{
		boolean boolResult = false;
		int len;

		if (arr != null && (len = arr.length) > 1)
		{
			for (int i=0, sumLeft=0, sumRight=0;    i < len-1;      i++, sumLeft=0, sumRight=0)
			{
				for (int left = i; left >= 0; left--)
					sumLeft += arr[left];

				for (int right = i+1; right < len; right++)
					sumRight += arr[right];

//				System.out.println (sumLeft + " / " + sumRight);
				if (sumLeft == sumRight)
				{
					boolResult = true;
					break;
				}
			}
		}
		return boolResult;
	}// checkBalance()

/*  7. Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
	при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично. Для усложнения задачи
	нельзя пользоваться вспомогательными массивами.
	Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
			 [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
	При каком n в какую сторону сдвиг можете выбирать сами.
*/
	public static void shiftArrayByN (int[] arr, int n)
	{
	//  Если n > 0, то сдвигаем массив вправо, если n < 0, то -- влево.

		int len;
		if (arr == null || (len = arr.length) < 2)
			return;

	//  «Убираем» из n полные циклы, а отрицательное n приводим к положительному значению:
		n %= len;
		if (n < 0) n += len;

		while (n-- > 0)
		{
			for (int i=0, tmp;  i < len-1;  i++) //< цикл сдвигает масив вправо на 1 позицию
			{
				tmp = arr[i+1];
				arr[i+1] = arr[0];
				arr[0] = tmp;
			}
		}
	}// shiftArrayByN ()

	public static void main (String[] args)
	{
	/* 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
		С помощью цикла и условия заменить 0 на 1, 1 на 0;
	*/
		int[] arr1 = {0,1,1,1,0,0,1,0,1,1,0,1,0,0,0,0,0};
		System.out.println("\narr1 (berore) :\t" + Arrays.toString (arr1));
		for (int i=0; i < arr1.length; i++)
		{
			if (arr1[i] == 1)
			    arr1[i] = 0;
			else
				arr1[i] = 1;
		}
		System.out.println ("arr1 (after) :\t" + Arrays.toString (arr1));

	/* 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21.
	*/
		int[] arr2 = new int[8];
		for (int i=0; i < arr2.length; i++)
		{
			arr2[i] = i * 3;
		}
		System.out.println ("\narr2 : " + Arrays.toString (arr2));

	/*  3. Задать  массив  [  1,  5,  3,  2,  11,  4,  5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2.
	*/
		int[] arr3 = {1,  5,  3,  2,  11,  4,  5, 2, 4, 8, 9, 1};
		System.out.println("\narr3 (berore) :\t" + Arrays.toString (arr3));
		for (int i=0; i < arr3.length; i++)
		{
			if (arr3[i] < 6)    arr3[i] *= 2;
		}
		System.out.println ("arr3 (after) :\t" + Arrays.toString (arr3));

		/*  4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое) и
			с помощью цикла(-ов) заполнить его диагональные элементы единицами.
		*/
		System.out.println ("\nЗадание 4:");
		int n = 8;
		int[][] arr4 = new int[n][n];
		for (int i=0; i < n; i++)
		{
			for (int j=0, max=n-1; j < n; j++)
			{
				if (i == j || i == max-j)
					arr4[i][j] = 1;

				System.out.print (arr4[i][j] + " ");
			}
			System.out.println();
		}

		/*  5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета
		*/
		int[] arr5 = {2,12,-4,14,-1,8,0,4,7,};
		System.out.print ("\narr5 : " + Arrays.toString (arr5));
		int i = 0, max = i, min = i;
		for (; i < arr5.length; i++)
		{
			if (arr5[i] > arr5[max])    max = i;
			else
			if (arr5[i] < arr5[min])    min = i;
		}
		System.out.println (";\tarr5[max] = " + arr5[max] + "; arr5[min] = " + arr5[min]);

		/*  6. Написать метод, в который передается непустой одномерный целочисленный массив, метод должен вернуть true,
			если в массиве есть место, в котором сумма левой и правой части массива равны.
			Примеры: checkBalance ([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
					 checkBalance ([1, 1, 1, || 2, 1]) → true,
					 (граница показана символами ||, эти символы в массив не входят).
		*/
		int[] arr6 = {1, 1, 1, 2, 1};// {2, 2, 2, 1, 2, 2, 10, 1};// {2,1,1};// {1,1,2};// {1,1};// {};// {1};//
		System.out.println ("\narr6[] : " + Arrays.toString(arr6) + ";\tcheckBalance(arr6) returned " + checkBalance (arr6));

		/*  7. Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
			при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично. Для усложнения задачи
			нельзя пользоваться вспомогательными массивами.
			Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
					 [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
			При каком n в какую сторону сдвиг можете выбирать сами.
		*/
		int[] arr7 = {0, 1, 2, 3, 4, 5, 6, 7};// arr5;//
		int shift = -7;
		System.out.println ("\narr7 (berore) :\t" + Arrays.toString (arr7));
		shiftArrayByN (arr7, shift);
		System.out.println ("arr7 (after) :\t" + Arrays.toString (arr7) + "\t<-- shifted by " + shift);

	}// main()

}
