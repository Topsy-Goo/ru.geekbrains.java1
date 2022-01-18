package ru.geekbrains.AntonovDV.algorithms.lesson5;

import ru.geekbrains.AntonovDV.algorithms.lesson2.lecture.ArrayClass;

public class Backpack<E extends Comparable <? super Thing>>
{
    private final int capacity;
    private int maxload = 0,
                maxprice = 0;
    private ArrayClass<Thing> things,
                              load = null;

    private final int initialSize; //< для отладки (стремимся к идеалу, когда количество перестановок равно факториалу размера массива)
    private int calls = 0, rearrangements = 0, fittings = 0;  //< для отладки


    public Backpack (int capacity, ArrayClass<Thing> things)
    {
        this.capacity = capacity;
        this.things = things;
        initialSize = things.size();

        anagramThings (things, 0);
        this.things = load;
    }


// Собственно рекурсивный метод. (Переменная start указывает элемент массива ooo, с которого начинается
// «подмассив», с которым работает метод на данной итерации рекурсии. )
    public void anagramThings (ArrayClass<Thing> ooo, final int start)
    {
/*  В основе метода лежит следующий рекурсивный подход: на каждой итерации сокращаем количество рассматтриваемых
    элементов массива. Я «отбрасывал» крайние левые элементы.

    Для получения всего многообразия перестановок элементов массива ooo используется следующий механизм:
    1) каждый уровень итерации рассматривает только хвост массива, укороченный на 1 в сравнении с длиной хвоста
    рассматриваемого уровнем выше. Т.е. чем глубже уровень, тем короче хвост. Нет смысла рассматривать хвост короче
    2-х элементов, за исключением случая, когда весь ooo состоит из одного элемента.

    2) На каждом уровне выполняется только циклическая перестановка элементов. Именно вложенные циклические сдвиги
    и обеспечивают всё многообразие перестановок в ooo. После каждого циклического сдвига на уровне L происходит
    переход на один уровень вниз. По возвращении оттуда снова происходит циклический сдвиг и новое погружение. И
    так до тех пор, пока элементы уровня L не займут те же позиции, которые одни занимали в момент нашего прихода
    на уровень L. (Псоледняя фраза очень важна: мы должны оставить уровень L в том виде, в каком он был до нашего
    прихода на него!)

    У меня основная трудность заключалась в том, чтобы обработать все перестановки, не пропустив ни одной. Я
    смог добиться этого только следующим образом: я взял за правило «спускаясь» на
    очередной уровень не возвращаться с него до тех пор, пока не приведу его в тот же вид, в каком он был до
    моего прихода. Например, если я рекурсивно спустился на уровень, где остались только два элемента, —
    например 4 и 12, — то я должен рассмотреть обе перестановки этих элементов ([4 12] и [12 4]), вернуть их в тот
    порядок, в котором они стояли ([4 12]), и только после этого возвращаться не уровень выше.
*/
        calls ++; //< это для проверки эффективности (учёт количества вызовов)
        if (ooo == null || start >= ooo.size())
            return;

        int gap = ooo.size() - (start+1);   //< количество элементов «между» start и size().
    //    Thing t = ooo.getAt(start);

        if (gap == 0) //< только для случаев, когда исходный массив содержит всего одну вещь.
        {
            //System.out.println (ooo);
            tryOnBackpack (ooo);
            rearrangements ++; //< это для проверки эффективности (учёт количества перестановок)
        }
        else if (gap == 1) //< рассматриваемый хвост массива ooo сейчас состоит из двух элементов (глубже
        {                  //  спускаться не будем). Этот случай выделен в отдельный if потому, что здесь
                           //  не нужно вызывать anagramThings(), но уже можно вызывать tryOnBackpack().
            //System.out.println (ooo);
            tryOnBackpack (ooo);            //< примеряем ВЕСЬ массив ooo к рюкзаку (не смотря на то, что мы переставляем элементы в хвосте массива ooo, к рюкзаку мы примеряем весь массив ooo, -- разумное упрощение и без того сложной задачи)
            ooo.add (ooo.removeAt (start)); //< собственно перестановка элементов (циклический сдвиг)
            rearrangements ++;

            //System.out.println (ooo);
            tryOnBackpack (ooo);
            ooo.add (ooo.removeAt (start)); //< возвращаем пару в прежнее состояние (шаг, на первый взгляд кажущийся бессмысленным)
            rearrangements ++;
        }
        else while (gap-- >= 0) //< условие цикла подобрано таким образом, чтобы спускаться на уровень ниже столько раз, сколько элементов осталось в хвосте ooo.
        {
            anagramThings (ooo, start +1); //< спускаемся на уровень ниже (чем ниже уровень, тем короче хвост ooo)
            ooo.add (ooo.removeAt (start));//< вернулись и делаем циклическую перестановку
            rearrangements ++;
        }
    }// anagramObjects ()


// (Вспомогательная.) Примеряем очередную перестановку к нашему рюкзаку и сравниваем с результатми предыдущих примерок.
    private void tryOnBackpack (ArrayClass<Thing> ooo)
    {
        int loadSum = 0,
            priceSum = 0;
        ArrayClass<Thing> things = new ArrayClass<>();

    // помещаем вещи в рюкзак в порядке их следования в массиве до тех пор, пока очередная вещь
    // не сможет поместиться:
        for (int i=0, n= ooo.size();    i<n;    i++)
        {
            Thing t = ooo.getAt(i);
            int w = t.getWeight();

            if (loadSum + w > capacity)
                break;
            priceSum += t.getPrice();
            loadSum += w;
            things.add(t);
        }
    // сохраняем результат примерки текущего порядка вещей к нащему рукзаку:
        if (priceSum > this.maxprice)
        {
            this.maxprice = priceSum;
            this.maxload = loadSum;
            this.load = things;
        }
        fittings ++;
    }// tryOnBackpack ()


    private static final String tostringFormat =
                "\nВ рюкзак поместились  (вес-цена): %s\n\t(общий вес %d, общая стоимость %d);" +
                "\nвызовов %d, перестановок %d, примерок %d (начальное количество вещей %d)";

    @Override public String toString ()
    {
        return String.format (tostringFormat,
                              ((things != null) ? things.toString() : "[]"),
                              maxload, maxprice,
                              calls, rearrangements, fittings,
                              initialSize);
    }// toString ()


}// Backpack

