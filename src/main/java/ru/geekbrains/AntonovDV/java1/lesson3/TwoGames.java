package ru.geekbrains.AntonovDV.java1.lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TwoGames
{
	protected int range = 10;   //< диапазон чисел, из которых нужно угадать одно число.
	protected int attempts = 3; //< количество попыток на угадывания числа из диапазона range.
	protected int lenMax = 0;    //< длина самого длинного слова в массиве arrWords
	protected String[] arrWords = {};

	TwoGames (String[] arr, int r)
	{
		if (arr != null)
		{
			this.arrWords = arr;

			for (int i=0, n= arr.length;  i<n;  i++)
				if (arr[i].length() > lenMax)
					lenMax = arr[i].length();
		}

		if (r > 0)
			range = r;
	}

/** #1. Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
	При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
	После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
*/
	public boolean runGuessNumber (Scanner sc, int number)
	{
		boolean boolTryAgain = false;

		if (sc != null && number >= 0 && number < this.range)
		{
			int iUserAnswer;

			System.out.printf ("\nС %d попыток угадайте число из диапазона от 0 до %d включительно.\n", this.attempts, this.range - 1);

			for (int i=1; i <= this.attempts; i++)
			{
				System.out.printf ("Попытка № %d (введите загаданное число и нажмите ENTER) :\t", i);
				iUserAnswer = sc.nextInt();

				if (iUserAnswer == number)
				{
					System.out.print ("Вы угадали… ((  ");
					break;
				}
				else System.out.print ("Загаданное число " + ((iUserAnswer < number) ? "больше":"меньше") + ".\n");
			}

			System.out.printf ("Загаданное число — %d.\nХотите сыграть ещё ? \n", number);
			do
			{
				System.out.print ("(1 = Да, 0 = Нет) : ");
				iUserAnswer = sc.nextInt();
			}
			while (iUserAnswer > 1 || 0 > iUserAnswer);

			boolTryAgain = iUserAnswer == 1;
		}
		sc.nextLine(); //< Съедаем пустую строку, чтобы она не попала в следующую игру (runGuessWord()).
		return boolTryAgain;
	}// runGuessNumber (n)

/** #2. Создать массив из слов
	String[] words = {
		"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic",
		"grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper",
		"pineapple", "pumpkin", "potato"}.

	При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом
	и сообщает, правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.

	apple – загаданное
	apricot - ответ игрока

		ap############# (15 символов, чтобы пользователь не мог узнать длину слова)

	Угаданные в прошлые ответы буквы запоминать не надо. То есть при следующем ответе:
	carpet (ковер, не фрукт, но это всего лишь пример), будет выведено:

		####e##########

	Для сравнения двух слов посимвольно можно пользоваться:
		String str = "apple";
		char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции

	Играем до тех пор, пока игрок не отгадает слово.
	Используем только буквы в нижнем регистре.
*/
	public void runGuessWord (Scanner sc, int index)
	{
		if (sc == null || index < 0 || arrWords == null || arrWords.length == 0 || arrWords.length <= index)
			return;

		char[] chWord = arrWords[index].toCharArray();
		char[] chMask = new char[lenMax];
		int lenWord = chWord.length;

		System.out.printf ("\nДан массив слов: \n" + Arrays.toString (this.arrWords) + "\n" +
							"Компьютер выбрал одно из этих слов. Угадайте его. " +
							"(Макс. длина слова в массиве составляет %d символов.)\n", lenMax);
		do
		{
			System.out.print ("Введите загаданное слово и нажмите ENTER: ");
			String strUserAnswer = sc.nextLine().toLowerCase();

			if (strUserAnswer.isBlank()) //< добавил возможность выйти из игры без угадывания слова
			   break;
			else
			if (strUserAnswer.equals (arrWords [index])) //< Слово введено верно.
			{
				System.out.println ("\tВы правильно угадали слово.");
				break;
			}
			else
			// Выводим на консоль только те буквы, которые пользователь угадал (вместо остальных выводим какой-нибудь
			// символ; общее количество символов в такой маске равно длине самого длинного слова массива):
			{
				char[] chInput = strUserAnswer.toCharArray();
				int lenInput = chInput.length;

				Arrays.fill (chMask, '_');

			// Выводим маску на экран (учитываем, что юзер мог ввести строку произвольной длины):
				for (int i=0;  i < lenMax;  i++)
				{
					if (i >= lenInput || i >= lenWord)
						break;

					if (chInput[i] == chWord[i])
						chMask[i] = chWord[i];
				}
				System.out.printf (" \tВы угадали следующие символы: %s\n", String.valueOf (chMask));
			}
		}
		while (true);
	}// runGuessWord()

	public static void main(String[] args)
	{
		int range = 10;
		String[] arr = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
						"garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
						"peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

		TwoGames victorina = new TwoGames (arr, range);

	// Инициализируем ввод с клавиатуры, который нам понадобится для обоих игр:
		Scanner sc = new Scanner(System.in);

	// Сначала угадываем число:
		Random rnd = new Random();
		do
		{
			if (! victorina.runGuessNumber (sc, rnd.nextInt (range)))
				break;
		}
		while (true);

	//Теперь угадываем слово:
		int index = rnd.nextInt (arr.length);
		victorina.runGuessWord (sc, index);

	// Завершаем работу программы:
		System.out.println ("\n\tGAME OVER");
		sc.close();
	}// main()

}// class TwoGames
