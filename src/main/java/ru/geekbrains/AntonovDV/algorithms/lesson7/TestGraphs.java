package ru.geekbrains.AntonovDV.algorithms.lesson7;

public class TestGraphs
{

    public static void main (String[] args)
    {
        String[] strings = {"Москва", "Тула", "Липецк", "Воронеж", "Рязань",
                            "Тамбов", "Саратов", "Калуга", "Орёл", "Курск"};
        testGraph (strings);

    }// main ()

    public static void testGraph (String[] strings)
    {
        int gsize = strings.length;
        GraphClass graph = new GraphClass (gsize);

        for (String s : strings)
            graph.addVertex (s);

        graph.addEdge ("Москва","Тула");
        graph.addEdge ("Тула","Липецк");
        graph.addEdge ("Липецк","Воронеж");

        graph.addEdge ("Москва","Рязань");
        graph.addEdge ("Рязань","Тамбов");
        graph.addEdge ("Тамбов","Саратов");
        graph.addEdge ("Саратов","Воронеж");

        graph.addEdge ("Москва","Калуга");
        graph.addEdge ("Калуга","Орёл");
        graph.addEdge ("Орёл","Курск");
        graph.addEdge ("Курск","Воронеж");
        graph.display ();

        graph.bfs2 ("Москва","Воронеж");
        graph.unlinkAllVertices();

    }// testGraph ()

}// class TestGraphs
