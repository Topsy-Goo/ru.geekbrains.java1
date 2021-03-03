package ru.geekbrains.AntonovDV.algorithms.lesson8;

public class CHMTest
{

    public static void main (String[] args)
    {
        testChainedMap (20);

    }// main ()

// (size задяёт размер map)
    public static void testChainedMap (int size)
    {
        ChainedHashMapClass<String, Integer> cmsf = new ChainedHashMapClass<>(size);

        cmsf.put ("public", 1);
        cmsf.put ("static", 2);
        cmsf.put ("void", 3);
        cmsf.put ("Git", 4);
        cmsf.put ("testChainedMap", 5);
        // 5
        cmsf.put ("ChainedHashMapClass", 6);
        cmsf.put ("String", 7);
        cmsf.put ("Float", 8);
        cmsf.put ("args", 9);
        cmsf.put ("cmsf", 10);
        // 10
        cmsf.put ("new", 11);
        cmsf.put ("TODO", 12);
        cmsf.put ("println", 13);
        cmsf.put ("class", 14);
        cmsf.put ("CHMTest", 15);
        // 15
        cmsf.put ("lesson8", 16); // 16
        cmsf.put ("lesson8", 17); // 16
        cmsf.put ("several", 18);
        cmsf.put ("Staging", 19);
        cmsf.put ("everything", 20);
        cmsf.put ("to", 21);
        // 20

        System.out.printf ("\nСписок (size %d) :\n%s", cmsf.size(), cmsf);

        System.out.printf ("Пробуем получить элемент %s : получаем %s\n", "to", cmsf.get("to"));
        System.out.printf ("Пробуем получить элемент %s : получаем %s\n", "too", cmsf.get("too"));

        cmsf.remove("lesson8");
        System.out.printf ("Пробуем удалить элемент %s и выводим список (size %d) :\n%s",
                           "lesson8", cmsf.size(), cmsf);

    }// testChainedMap ()

}// class CHMTest
