package ru.geekbrains.AntonovDV.java2.lesson1.equipment;

public interface Equipment
{
    default void doRun() {}
    default void doJump(){}

/*  (Я знаю, что описание метода в интерфейсе многим не нравится и что этот статический метод
    можно было бы вынести куда угодно, например, в класс Test, но логически место этому методу
    именно здесь.)

    Выводим на экран короткую строку onEquipment и список элементов data.     */
    static String makeString (Object[] data, String onEquipment)
    {
        StringBuilder sb = new StringBuilder(onEquipment);
        int counter = 0;

        for (Object i : data    /*int i=0, n=data.length;   i<n;   i++ */)
        {
            if (i != null)
            {
                if (counter > 0)
                    sb.append(", ");

                sb.append(i);
                counter ++;
            }
        }
        if (counter == 0)   sb.append("нет никого");
        sb.append('.');

        return sb.toString();
    }// makeString ()

}//
