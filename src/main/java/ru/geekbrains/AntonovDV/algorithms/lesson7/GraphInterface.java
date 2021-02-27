package ru.geekbrains.AntonovDV.algorithms.lesson7;

public interface GraphInterface
{

    boolean addVertex (String label);

    boolean addEdge (String from, String to);

    int addEdges (String from, String ... toothers);

    int getSize ();

    void display ();

    /**
     * англ. Depth-first search, DFS
     */
    void dfs (String start);

    /**
     * англ. breadth-first search, BFS
     */
    void bfs (String start);
    void bfs2 (String start, String end);   //< вариант для ДЗ

}// interface GraphInterface
