package ru.geekbrains.AntonovDV.Lesson4;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class TickTackToe
{
/** 1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку. <br><br>

	2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов. <br><br>

	3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
	Очень желательно не делать это просто набором условий для каждой из возможных ситуаций; <br><br>

	4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
*/
	protected char[][] map; //< квадратное игровое поле 3x3 или 5x5. Условимся считать его массивом строк.
	static final int SIZE = 15,  //< размер стороны игрового поля (3 или 5)
					 LINE = 4;  //< длина выигрышной последовательности (==3 для поля 3x3, или ==4 для поля 5x5)

	static final char   DOT_PLAYER = 'X',
						DOT_AI = 'O',
						DOT_EMPTY = '·';

	protected int turnCounter;    //< счётчик ходов (поможет закончить игру, если закончатся свободные клетки)
	static final boolean SOLID = true;
	protected int[][][] hotLines = {};  //< массив столбцов и диагоналей игрового поля, в которых может содержаться выигрышная
										//  последовательность. Т.к. размер игрового поля может быть изменён, то этот массив
										//  нужно пересчитывать для каждого размера.

	public void prepareMap ()
	{
		map = new char[SIZE][SIZE];

		for (int i=0; i<SIZE; i++)
			for (int j=0; j<SIZE; j++)
				map[i][j] = DOT_EMPTY;

		turnCounter = 0;
		buildHotLines();

		System.out.println ("\n\tКРЕСТИКИ-НОЛИКИ");
	}// prepareMap ()


//  Составляем массивы координат ячеек. Это будет массив двумерных массивов (или массив массивов координат;
//  или массивы координат, объединённые для удобства в массив).
	protected void buildHotLines ()
	{
		int i1;     //< количество элементов в hotLines

	//  Вычисляем размер массива :
		i1 = SIZE + 2 +    //< учитываем все столбцы и две главные диагонали
			 (SIZE - LINE) * 4 ;    //< учитываем количество диагоналей, параллельных главным диагоналям

		hotLines = new int [i1][][];

		int[][] line = null;

	//  Условимся, что координаты ячеек будут записываться так: [0] - координата строки, [1] - координата столбца.

	// Добавляем в hotLines столбцы:
		for (int j=0; j<SIZE; j++)
		{
			line = new int[SIZE][2];
			hotLines[--i1] = line;
			for (int i=0; i<SIZE; i++)
			{
				line[i][0] = i;
				line[i][1] = j;
			}
		}
	// Добавляем в hotLines главные и дополнительные диагонали (количество дополнительных диагоналей зависит от SIZE и LINE):
		for (int x=0;   x <= (SIZE-LINE);   x++)
		{
		// Диагональ TopLeft - RightBottom или диагональ над нею
			line = new int[SIZE-x][2];
			hotLines[--i1] = line;
			for (int i=0, j=x; j<SIZE; i++,j++)
			{
				line[i][0] = i;
				line[i][1] = j;
			}
		// Диагональ под диагональю TopLeft - RightBottom (если таковая есть)
			if (x>0)
			{
				line = new int[SIZE-x][2];
				hotLines[--i1] = line;
				for (int i=x, j=0; i<SIZE; i++,j++)
				{
					line[j][0] = i;
					line[j][1] = j;
				}
			}
/*		   0  1  2  3  4
		0  ·  ·  ·  W  W
		1  ·  ·  W  W  W
		2  ·  W  W  W  ·
		3  W  W  W  ·  ·
		4  W  W  ·  ·  ·    */

		// Диагональ BottomLeft - RightTop или диагональ над нею
			line = new int[SIZE-x][2];
			hotLines[--i1] = line;
			for (int i=0, j=SIZE-1-x;  j>=0;  i++,j--)
			{
				line[i][0] = i;
				line[i][1] = j;
			}
		// Диагональ под диагональю BottomLeft - RightTop (если таковая есть)
			if (x>0)
			{
				line = new int[SIZE-x][2];
				hotLines[--i1] = line;
				for (int i=x, j=SIZE-1, n=0;  i<SIZE;  i++,j--,n++)
				{
					line[n][0] = i;
					line[n][1] = j;
				}
			}
		}

		if (i1 != 0)	System.err.printf ("\n\tERROR @ buildHotLines() : i1(%d) != 0.\n", i1);
	}// buildHotLines ()


//  Составляем строку для вывода карты на экран. Аналог метода printMap(), но более удобный для отладки
//  (игровое поле можно будет увидеть в окне отладчика).
	public String ToString ()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("\n");
		for (int i=0; i<SIZE; i++)
			sb.append(String.format ("\t%d\t", i+1));

		for (int i=0; i<SIZE; i++)
		{
			sb.append(String.format ("\n\n%d", i+1));
			for (int j=0; j<SIZE; j++)
				sb.append(String.format ("\t%c\t", map[i][j]));
		}
		sb.append("\n");
		return sb.toString();
	}

	public void printMap ()	{   System.out.println (ToString ());   }


// Вспомогательный метод. Получает от пользователя введённое число в заданном диапазоне значений.
	protected int askInputInt (Scanner sc, int from, int upto)
	{
		int result = from;
		if (from <= upto)
		{
			do
			{	result = sc.nextInt();
			}
			while (result < from || result > upto);
		}
		return result;
	}


//  Игрок делает ход.
	protected void playerTurn (Scanner sc)
	{
		int x,y;
		do
		{
			System.out.printf ("Введите горизональную координату (от %d до %d) : ", 1, SIZE);
			y = askInputInt (sc, 1, SIZE);
			System.out.printf ("Введите вертикальальную координату (от %d до %d) : ", 1, SIZE);
			x = askInputInt (sc, 1, SIZE);
			if (map[x-1][y-1] == DOT_EMPTY)
				break;
			System.out.printf ("Клетка %d,%d уже занята. Укажите другую клетку.\n", x, y);
		}
		while (true);
		map[--x][--y] = DOT_PLAYER;
	}


//  ИИ делает ход.
	protected void aiTurn ()
	{
		if (turnCounter == 0) //< Бот ходит первым, поэтому у нас есть возможность разнообразить игру случайным (и простым) первым ходом.
		{
			Random rnd = new Random ();
			int point = rnd.nextInt (SIZE*SIZE);
			map [point / SIZE] [point % SIZE] = DOT_AI;
		}
		else
		// (Сейчас бот умеет только мешать игроку выигрывать. Собственные выгодные комбинации он использовать не умеет.)
		{
		// Перебираем все строки, столбцы и диагонали (далее -- линии) в поисках потенциально опасной последовательности
		// и вставляем в такую линию символ DOT_AI (символ вставляем в первую попавшуюся ячейку линии, без анализа):

			for (int m=LINE-1; m>1; m--) //< Сначала ищем самые длинные последовательности, потом -- последовательности покороче.
			{
				// сначала перебираем строки в map:
				for (int i=0; i<SIZE; i++)
				{
					if (checkLine (map[i], DOT_PLAYER) >= m
					&&
					insertIntoLine (DOT_AI, i))
						return;
				}
				// перебираем элементы hotLines:
				int index = 0;
				do
				{	if ((index = checkHotLines (DOT_PLAYER, m, index, !SOLID)) < 0)
						break;
					if (insertIntoHotLine (DOT_AI, index ++))
						return;
					// (Если в найденной строке нет свободного места, то продолжаем поиск в hotLines с того места, где остановились.)
				}
				while (true);
			}

		// Если испортить игру противнику не удалось, то просто ставим символ DOT_AI в первую попавшуюся свободную клетку
		// (по идее, мы сюда никогда не попадём):
		    for (int i=0; i<SIZE; i++)
			{
				for (int y=0; y<SIZE; y++)
				{
					if (map[i][y] == DOT_EMPTY)
					{
						map[i][y] = DOT_AI;
						return;
					}
				}
			}//*/
		}
	}// aiTurn ()

// Ищем в строке игрового поля свободную ячейку и вставляем в неё символ symbol.
	protected boolean insertIntoLine (char symbol, int line)
	{
		boolean boolOk = false;
		for (int i=0; i<SIZE; i++)
		{
			if (boolOk = map [line][i] == DOT_EMPTY)
			{
				map [line][i] = symbol;
				break;
			}
		}
		return boolOk;
	}// insertIntoLine ()

// Ищем в «горячей строке» свободную ячейку и вставляем в неё символ symbol.
	protected boolean insertIntoHotLine (char symbol, int line)
	{
		boolean boolOk = false;
		int row, cell;
		for (int i=0;   i < hotLines[line].length;   i++)
		{
			row = hotLines [line][i][0];
			cell = hotLines [line][i][1];
			if (boolOk = map[row][cell] == DOT_EMPTY)
			{
				map [row][cell] = symbol;
				break;
			}
		}
		return boolOk;
	}// insertIntoHotLine ()


// Проверяем, не содержит ли игровое поле выигрышную комбинацию. В качестве параметра передаётся символ игрока или ИИ, в
// зависимости от того, чью победу мы проверяем.
	boolean isVictory (char ch)
	{
	// Проверяем столбцы и диагонали (эту трудоёмкую операцию поручаем специальному механизму):
		boolean boolYes = checkHotLines (ch, LINE, 0, SOLID) >= 0;

	// проверяем строки (их можно передавать в проверяющую ф-цию как есть, т.к. они являются действительными одномерными массивами):
		for (int i=0; !boolYes && i<SIZE; i++)
		{
			boolYes = checkLineSolid (map[i], ch) >= LINE;
		}
		return boolYes;
	}// isVictory ()


// На игровом поле ищем последовательности длиной quantity, состоящие из символов symbol. Парметр solid определяет, должна ли искомая
// последовательность быть непрерывной. Возвращаем индекс в hotLines[] первой же «горячей строки», в которой искомая последовательность
// обнаружилась. Возвращаем -1, если искомая последовательность не была найдена.
	protected int checkHotLines (char symbol, int quantity, int i, boolean solid)
	{
		int index = -1;
		char[] arrch = null;

		// Проверяем столбцы и диагонали (для упрощения этой проверки мы перед началом игры создали массив координат ячеек,
		// входящих в эти столбци и диагонали):
		// (Почему индекс i передаётся через параметр, лучше всего видно в методе aiTurn().)
		if (i < 0) i = 0;
		for (;   i < hotLines.length;   i++)
		{
			final int[][] hline = hotLines[i];
			int n = hline.length;
			arrch = new char [n];

			// метод checkLineSolid() принимает только массивы char[], поэтому каждую «горячую линию» сперва превращаем в char[]:
			while (--n >= 0)
				arrch[n] = map [hline [n][0]] [hline [n][1]];

			int tmp = solid ? checkLineSolid (arrch, symbol) : checkLine (arrch, symbol);
			if (tmp >= quantity)
			{
				index = i;
				break;
			}
		}
		return index;
	}



//  Метод проверяет массив arrch на наличие в нём НЕПРЕРЫВНОЙ последовательности из символов dot.
//  Возвращает длину самой длинной найденной последовательности.
	protected int checkLineSolid (char[] arrch, char dot)
	{
		boolean boolOk = false;
		int max = 0,
			counter;

		if (arrch != null)
		{
			int i = arrch.length -1;
			do
			{	counter = 0;
				while (i >= 0) //< пропускаем лишние символы
				{
					if (arrch[i] == dot) break;
					i--;
				}
				while (i >= 0) //< считаем искомые символы, идущие подряд
				{
					if (arrch[i] != dot)	break;
					counter ++;
					i--;
				}
				if (counter > max)  max = counter; //< нам нужна только самая длинная последовательность искомых символов
			}
			while (i >= 0);
		}
		return max;//boolOk;
	}// checkLineSolid ()


// Метод просто подсчитывает количество символов dot в строке arrch. (Такой подход предпочтительнее при расчёте хода бота.)
	protected int checkLine (char[] arrch, char dot)
	{
		boolean boolOk = false;
		int counter = 0;

		if (arrch != null)
		for (int i=0; i<arrch.length; i++)
		{
			if (arrch[i] == dot)
				counter++;
		}
		return counter;
	} // checkLine ()




	public static void main(String[] args)
	{
		TickTackToe ttt = new TickTackToe();
		Scanner sc = new Scanner (System.in);
		boolean boolVoktory = false;

		ttt.prepareMap ();

		while (!boolVoktory && ttt.turnCounter < SIZE * SIZE)
		{
		//  Первым ходит бот. Сперва я сделал это, чтобы пореже перерисовывать карту, но позже убедился, что это вообще хорошая мысль.
			if (ttt.turnCounter % 2 == 1)
			{
				ttt.playerTurn (sc);
				if (boolVoktory = ttt.isVictory (DOT_PLAYER))
				{
					ttt.printMap (); //< Карту с выигрышной комбинацией нужно перерисовать.
					System.out.println ("Вы выиграли!");
				}
			}
			else
			{
				ttt.aiTurn ();
				ttt.printMap (); //< перерисовываем карту после хода бота (так меньше суеты на экране)
				if (boolVoktory = ttt.isVictory (DOT_AI))
					System.out.println ("Вы проиграли.");
			}
			ttt.turnCounter ++;
		}
		System.out.println ("Игра окончена!");

		sc.close();
	}// main ()


}// class TickTackToe
