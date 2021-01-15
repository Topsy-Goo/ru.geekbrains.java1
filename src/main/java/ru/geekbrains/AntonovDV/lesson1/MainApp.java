package ru.geekbrains.AntonovDV.lesson1;

//  1. Создать пустой проект в IntelliJ IDEA и прописать метод main()
public class MainApp
{
	/**  2. Создать переменные всех пройденных типов данных и инициализировать их значения.
	*/
	byte bt = 3;
	short sh = 2;
	int i = 1;
	long lg = 4;

	float fl = 5.0f;
	double db = 6.0;

	char ch = 'a';
	boolean bl = true;
	String str = "string";

	/** 3.  Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат, где a, b, c, d – аргументы
		этого метода, имеющие тип float.
	*/
	public static float someMethod_abcd (float a, float b, float c, float d)
	{
		return a * (b + (c / d));
	}

	/** 4. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10
		до 20 (включительно), если да – вернуть true, в противном случае – false.
	*/
	public static boolean isSumInRangeOf_10_20 (int e, int f)
	{
		int sum = e + f;
		return sum >= 10 && sum <= 20;
	}

	/** 5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
		положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
	*/
	public static void printOut_NegativeOrPositive (int g)
	{
		System.out.println ("Передано " + ((g >= 0) ? "положительное" : "отрицательное") + " число.");
	}

	/** 6. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число
		отрицательное, и вернуть false если положительное.
	*/
	public static boolean isNegative (int h)
	{
		return h < 0;
	}

	/** 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя.
		Метод должен вывести в консоль сообщение «Привет, указанное_имя!».
	*/
	public static void printHelloJack (String jack)
	{
		System.out.println ("«Привет, " + jack + "!»");
	}

	/** 8. Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
		Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
	*/
	public static void isLeapYear (int year)
	{
		boolean bl = false;

		if (year % 4 == 0)
		{
			if (year % 100 == 0)
			{
				bl = year % 400 == 0;
			}
			else bl = true;
		}

		System.out.println (year + " год високосный? - " + (bl ? "да" : "нет"));
	}


	public static void main(String[] args)
	{
		System.out.println("Hello, World!!!");

		//3.
		float fl = someMethod_abcd (1.0f, 2.0f, 3.2f, 0.8f);
		System.out.println ("a * (b + (c / d)) = " + fl);

		//4.
		boolean bl = isSumInRangeOf_10_20 (15, 9);
		System.out.println (bl);

		//5.
		printOut_NegativeOrPositive (-8);

		//6.
		System.out.println (isNegative (6));

		//7.
		printHelloJack ("Джек");

		//8.
		isLeapYear (2020);
		isLeapYear (1900);
		isLeapYear (2000);

	}
}
