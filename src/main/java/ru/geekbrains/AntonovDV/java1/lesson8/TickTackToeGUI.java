package ru.geekbrains.AntonovDV.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class TickTackToeGUI extends JFrame
{
//	public static final boolean DEBUG = true;
	protected char[][] map; //< квадратное игровое поле, поделённое на клетки (условимся считать его массивом строк).
	static final int GF_SIDE = 420, //< 60 -- общее кратное чисел 3,4,5,6 и 10.
					 STROKE_BOX = 1,
					 STROKE_MAX = 16,
					 SIZE_MAX = 10,
					 SIZE_MIN = 3,
					 LINE_MIN = SIZE_MIN;
	int SIZE = 5,  //< размер стороны игрового поля (количество клеток)
		LINE = 4;  //< длина выигрышной последовательности (количество клеток)

	static final char   DOT_PLAYER = 'X',
						DOT_AI = 'O',
						DOT_EMPTY = '·';
	public static final Color   colorGREEN = new Color (80, 170, 80),
								colorBLUE = new Color (50, 120, 170);

	int turnCounter;    //< счётчик ходов (поможет закончить игру, если закончатся свободные клетки)
	static final boolean SOLID = true; //< true == искать непрерывную последовательность символов
	int[][][] hotLines = {};  //< массив столбцов и диагоналей игрового поля, в которых может содержаться
							  //  выигрышная последовательность.
	final JPanel jpGamefield;
	boolean booAiFirst = false, //< определяет того, кто делает первый ход
			gameIsOn = false; //< индикатор (true == идёт игра)
	JButton btnSetSize,
			btnSetLine,
			btnAiFirst,
			btnNewGame;
	final String sBtnPlayer1st = "Первый ход ваш",  sBtnAi1st = "Первый ход ИИ",
				 sBtnFieldSize = "Размер поля ", sBtnLineLenth = "Длина линии ",
				 sMainWndTitle = " Крестики-нолики",
				 sPlayerWin = " - ВЫ ВЫИГРАЛИ !!!", sAiWin = " - ИИ ВЫИГРАЛ !!!", sDraw = " - НИЧЬЯ…",
				 sBtnNewGame = "Новая игра",
				 sBtnStart = "Старт"; //< В самом начале кнопка начала игры имеет это название.

	public TickTackToeGUI ()
	{
	// Окно:
		setBounds (500, 300, GF_SIDE+20, GF_SIDE+50+20+25); //< Задание размера окна (есть варианты с Rectangle и с x,y,W,H).
		setTitle (sMainWndTitle); //< Заголовок окна
		setDefaultCloseOperation (EXIT_ON_CLOSE); //< Задаём обработчик закрытия окна.
		//setResizable (false); -- я сохранил возможность изменения размера окна, т.к. ширина рамки окна у всех разная,
		// а поиски функции определения/изменения размера клиентской области окна затянулись.

	// Игровое поле (похожий способ, но с вложенным классом, нам показывали на курсе «Java. Быстрый старт»; он
	// позволит оставить весь код в одном файле):
		jpGamefield = new JPanel()
		{
	        @Override protected void paintComponent (Graphics g) //<
	        {
	            super.paintComponent (g);
	            onRepaint (g);
	            repaint();
		        }
		};
		this.add (jpGamefield, BorderLayout.CENTER);

		jpGamefield.addMouseListener (new MouseAdapter()
		{
			@Override public void mouseReleased (MouseEvent e)
			{
				if (gameIsOn && e.getButton() == MouseEvent.BUTTON1)
				{
					int cellside = GF_SIDE / SIZE;
					if (trySetDotToCell (e.getY() / cellside, e.getX() / cellside, DOT_PLAYER))
					{
						turnCounter ++;
						jpGamefield.repaint();
						afterTurnCheck (DOT_PLAYER);

						if (gameIsOn && aiTurn ()) //< сразу после хода игрока делаем ход ИИ
						{
						    turnCounter ++;
						    jpGamefield.repaint();
						    afterTurnCheck (DOT_AI);
			}	}	}	}
		});

	// Верхние кнопки:
		JPanel controlpanelNorth = new JPanel (new GridLayout (1, 2, 2, 2));
		add (controlpanelNorth, BorderLayout.NORTH);
		{
			btnSetSize = new JButton (sBtnFieldSize + SIZE+"x"+SIZE);
			btnSetLine = new JButton (sBtnLineLenth + LINE);
			controlpanelNorth.add (btnSetSize);
			controlpanelNorth.add (btnSetLine);

			btnSetSize.addActionListener (new ActionListener()
			{
				@Override public void actionPerformed (ActionEvent e)
				{
					onButtonSize(); //< Изменение размера игрового поля (текущая игра прекращается)
					gameOver ((char)0);
					jpGamefield.repaint();
				}
			});
			btnSetLine.addActionListener (new ActionListener()
			{
				@Override public void actionPerformed (ActionEvent e)
				{
					onButtonLine(1); //< Изменение длины выигрышной последовательности (текущая игра прекращается)
					gameOver ((char)0);
					jpGamefield.repaint();
				}
			});
		}
	// Нижние кнопки:
		JPanel controlpanelSouth = new JPanel (new GridLayout (1, 2, 2, 2));
		add (controlpanelSouth, BorderLayout.SOUTH);
		{
			btnNewGame = new JButton (sBtnStart);    //запуск новой игры
			btnAiFirst = new JButton (booAiFirst ? sBtnAi1st : sBtnPlayer1st);  //определяет, кто делает первый ход (текущая игра не прерывается)
			controlpanelSouth.add (btnAiFirst);
			controlpanelSouth.add (btnNewGame);

			btnNewGame.addActionListener (new ActionListener()
			{
				@Override public void actionPerformed (ActionEvent e)
				{
					onButtonNewGame();
				}
			});
			btnAiFirst.addActionListener (new ActionListener()
			{
				@Override public void actionPerformed (ActionEvent e)
				{
					onAiFirstButton();
				}
			});
		}
		prepareMap();
		setVisible (true); //< Показываем окно.
	}// TickTackToeGUI ()

	void onButtonNewGame () //< Обраотчик нажатия на кнопку
	{
		btnNewGame.setText (sBtnNewGame);
	    startGame ();
	}
	void startGame () // инициализация игры
	{
		gameIsOn = false;
		setTitle (sMainWndTitle);
		prepareMap();
		buildHotLines();
		turnCounter = 0;
		jpGamefield.repaint();
		gameIsOn = true;
	// Здесь же ИИ делает первый ход, если первый ход за ним; тест на победу делать нет смысла.
		if (booAiFirst && aiTurn())
	        turnCounter ++;
	}// startGame ()

// Проверяем результ хода игрока или бота (ничего не возвращаем, т.к. переменная gameIsOn будет при необходимости
// сброшена в gameOver()).
	void afterTurnCheck (char dot)
	{
		if (turnCounter >= LINE*2-1) //< тест на победу делаем только после определённого количества ходов
		{
			if (isVictory (dot))     gameOver (dot);
			else
			if (turnCounter >= SIZE * SIZE)	gameOver (DOT_EMPTY);
		}
	}// afterTurnCheck ()


	void gameOver (char dot)
	{
		gameIsOn = false;

		if (dot == DOT_PLAYER)
		{
			setTitle (sMainWndTitle + sPlayerWin);
		}
		else if (dot == DOT_AI)
		{
			setTitle (sMainWndTitle + sAiWin);
		}
		else if (dot == DOT_EMPTY) //< ничья
		{
			setTitle (sMainWndTitle + sDraw);
		}
		else if (dot == 0) //< остановка игры в результате изменения переменных SIZE или LINE
			prepareMap();
	}// gameOver ()

/*  Переопределяем игрока, который будет делать первый ход в следующей игре.
	Останавливаем игру, только если игровое поле чистое, чтобы пользователь не был обескуражен поведением программы.
	Если игра в разгаре, то лучше её не прерывать на случай, если кнопка нажата по ошибке, да и первый ход уже сделан. */
	void onAiFirstButton ()
	{
		booAiFirst = !booAiFirst;
		btnAiFirst.setText (booAiFirst ? sBtnAi1st : sBtnPlayer1st);
		if (turnCounter == 0)
			gameOver ((char)0);
	}// onAiFirstButton ()

// Увеличиваем размер игрового поля на 1 (циклично от 3 до макс.).
	void onButtonSize ()
	{
	    if (++SIZE > SIZE_MAX)  SIZE = SIZE_MIN;
		btnSetSize.setText (sBtnFieldSize + SIZE+"x"+SIZE);
	    onButtonLine(0);
	}

// Увеличиваем длину выигрышной линии на 1 или 0.
// 0 передаётся, когда нужно просто проверить корректность значения соответствующей переменной.
	void onButtonLine (int increment)
	{
		LINE += increment;
		if (LINE > SIZE) LINE = LINE_MIN;
		btnSetLine.setText (sBtnLineLenth + LINE);
	}

// Наш вклад в перерисовку игрового поля; рисуем каждую ячейку и её символ.
	void onRepaint (Graphics g)
	{
		int cellside = GF_SIDE / SIZE;
		for (int i=0; i<SIZE; i++)
		{
			for (int j=0; j<SIZE; j++)
			{
				drawCell (g, map[i][j], j*cellside, i*cellside); //< собственно отрисовка ячейки
			}
		}
	}// onRepaint ()

// Рисуем ячейку игрового поля; x и y задают пиксел-координату относительно лев.верх.угла поля.
	void drawCell (Graphics g, char dot, int x, int y)
	{
		int cellside = GF_SIDE / SIZE;

	// рисуем прямоугольник:
		((Graphics2D) g).setStroke (new BasicStroke (STROKE_BOX));
		g.setColor (Color.PINK);
		g.drawRect (x+2, y+2, cellside-2, cellside-2);

	// рисуем символ:
		int off = 20 - SIZE;
		((Graphics2D) g).setStroke (new BasicStroke (STROKE_MAX - SIZE));
		if (dot == DOT_PLAYER)
		{
			g.setColor (colorGREEN);
			g.drawLine (x+off, y+off, x+cellside -off, y+cellside -off);
			g.drawLine (x+cellside -off, y+off, x+off, y+cellside -off);
		}
		else if (dot == DOT_AI)
		{
			g.setColor (colorBLUE);
			g.drawOval (x+off, y+off, cellside -2*off, cellside -2*off);
		}
	}// drawCell ()


	public void prepareMap () //< (ре)инициализируем массив данных (для игрового поля; важно для правильной перерисовки)
	{
		map = new char [SIZE][SIZE];
		for (int i=0; i< SIZE; i++)
		{
			for (int j=0; j< SIZE; j++)		map[i][j] = DOT_EMPTY;
		}
	}// prepareMap ()

	boolean trySetDotToCell (int row, int column, char dot)
	{
		boolean boolOk = false;
		if (row >= 0 && row < SIZE && column >= 0 && column < SIZE)
		{
			if (boolOk = map[row][column] == DOT_EMPTY)
				map[row][column] = dot;
		}
		return boolOk;
	}// setDotToCell ()

//----------------------------------------------------------------------------

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
		for (int j=0; j< SIZE; j++)
		{
			line = new int[SIZE][2];
			hotLines[--i1] = line;
			for (int i=0; i< SIZE; i++)
			{
				line[i][0] = i;
				line[i][1] = j;
			}
		}
	// Добавляем в hotLines главные и дополнительные диагонали (количество дополнительных диагоналей зависит от SIZE и LINE):
		for (int x=0;   x <= (SIZE - LINE);   x++)
		{
		// Диагональ TopLeft - RightBottom или диагональ над нею
			line = new int[SIZE -x][2];
			hotLines[--i1] = line;
			for (int i=0, j=x; j< SIZE; i++,j++)
			{
				line[i][0] = i;
				line[i][1] = j;
			}
		// Диагональ под диагональю TopLeft - RightBottom (если таковая есть)
			if (x>0)
			{
				line = new int[SIZE -x][2];
				hotLines[--i1] = line;
				for (int i=x, j=0; i< SIZE; i++,j++)
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
			line = new int[SIZE -x][2];
			hotLines[--i1] = line;
			for (int i=0, j= SIZE -1-x;  j>=0;  i++,j--)
			{
				line[i][0] = i;
				line[i][1] = j;
			}
		// Диагональ под диагональю BottomLeft - RightTop (если таковая есть)
			if (x>0)
			{
				line = new int[SIZE -x][2];
				hotLines[--i1] = line;
				for (int i=x, j= SIZE -1, n=0;  i< SIZE;  i++,j--,n++)
				{
					line[n][0] = i;
					line[n][1] = j;
				}
			}
		}

		if (i1 != 0)	System.err.printf ("\n\tERROR @ buildHotLines() : i1(%d) != 0.\n", i1);
	}// buildHotLines ()


//  ИИ делает ход.
	protected boolean aiTurn ()
	{
		if (turnCounter == 0) //< Бот ходит первым, поэтому у нас есть возможность разнообразить игру случайным (и простым) первым ходом.
		{
			Random rnd = new Random ();
			int point = rnd.nextInt (SIZE * SIZE);
			map [point / SIZE] [point % SIZE] = DOT_AI;
			return true;
		}
		else if (turnCounter < SIZE*SIZE)
		// (Бот умеет только мешать игроку выигрывать. Собственные выгодные комбинации он использовать не умеет.)
		{
		// Перебираем все строки, столбцы и диагонали (далее -- линии) в поисках потенциально опасной последовательности
		// и вставляем в такую линию символ DOT_AI. Символ вставляем в первую попавшуюся ячейку линии, без анализа.
		// Сначала рассматриваем линии, в которых больше всего точек игрока, потом переходим к линиям, где точек игрока
		// поменьше.

			for (int m= LINE -1; m>1; m--)
			{
				// сначала перебираем строки в map (их нет в hotLines, т.к. их легко проверять):
				for (int i=0; i< SIZE; i++)
					if (checkLine (map[i], DOT_PLAYER) >= m   &&   insertIntoLine (DOT_AI, i))
						return true;

				// теперь перебираем элементы hotLines (index -- индекс линии в массиве hotLines):
				int index = 0;
				do
				{	if ((index = checkHotLines (DOT_PLAYER, m, index, !SOLID)) < 0) //< ищем линию с m точками противника
						break;

					if (insertIntoHotLine (DOT_AI, index ++)) //< вставляем в линию свою точку
						return true;
					// (Если в найденной строке нет свободного места, то продолжаем поиск в hotLines со следующей линии.)
				}
				while (true);
			}
		// Если испортить игру противнику не удалось, то просто ставим символ DOT_AI в первую попавшуюся
		// свободную клетку (по идее, мы сюда никогда не попадём):
		    for (int i=0; i< SIZE; i++)
				for (int y=0; y< SIZE; y++)
					if (map[i][y] == DOT_EMPTY)
					{
						map[i][y] = DOT_AI;
						return true;
					}
		}
		return false;
	}// aiTurn ()

// Ищем в строке игрового поля свободную ячейку и вставляем в неё символ symbol.
	protected boolean insertIntoLine (char symbol, int line)
	{
		boolean boolOk = false;
		for (int i=0; i< SIZE; i++)
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
		for (int i=0; !boolYes && i< SIZE; i++)
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
	}// checkHotLines ()


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
			for (char c : arrch)
				if (c == dot)
					counter++;
		return counter;
	} // checkLine ()


	public static void main(String[] args)
	{
		new TickTackToeGUI();
	}// main ()

}// class TickTackToeGUI

