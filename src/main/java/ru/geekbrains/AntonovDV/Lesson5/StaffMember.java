package ru.geekbrains.AntonovDV.Lesson5;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;


public class StaffMember
{
	String nameFIO;
	String position;
	String email;
	String phone;
	int salary;
	int age;

	public StaffMember (String nameFIO, String position, String email, String phone, int salary, int age)
	{
		String blank = "";

		this.nameFIO = (!nameFIO.isBlank()) ? nameFIO : blank;
		this.position = (!position.isBlank()) ? position : blank;
		this.email = (!email.isBlank()) ? email : blank;
		this.phone = (!phone.isBlank()) ? phone : blank;

		this.salary = salary;
		this.age = age;
	}


	@Override public String toString()
	{
		return nameFIO + " [возраст " + age + "]" + ", должность: " + position + " (" + salary + " руб.)" + ", email: " + email + ", т." + phone;
	}// toString


	public void showStaffMember()
	{
		System.out.print ("\n" + toString());
	}


	public static void main (String[] args)
	{
		StaffMember[] collective = new StaffMember[5];

		collective[0] = new StaffMember ("Бунша Иван Васильевич", "царь", "czar@zavod.ru", "322-01-01", MAX_VALUE, 52);
		collective[1] = new StaffMember ("Горбунков Семён Семёныч", "ст.бухгалтер", "samsam@zavod.ru", "322-22-33", 150, 44);
		collective[2] = new StaffMember ("Лидочка", "секретарь", "lidochka@zavod.ru", "322-55-55", 85, 22);
		collective[3] = new StaffMember ("Попович Алексей", "нач.охраны", "popovich@zavod.ru", "322-11-47", 150, 31);
		collective[4] = new StaffMember ("Тихон Ляксеич", "менеджер по чистоте", "dvornik@zavod.ru", "322-00-00", 24, 42);

		for (int i=0; i<collective.length; i++)
		{
			if (collective[i].age > 40)
				collective[i].showStaffMember();
		}
	}// main

}// class StaffMember
